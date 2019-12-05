package com.ty.blog.shiro;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.ty.blog.constant.SecurityConsts;
import com.ty.blog.entity.User;
import com.ty.blog.service.UserService;
import com.ty.blog.shiro.jwt.JwtRedisCache;
import com.ty.blog.shiro.jwt.JwtToken;
import com.ty.blog.shiro.jwt.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * @ClassName: ShiroRealm
 * @Description: 自定义Realm
 * @author zhangtainyi
 * @date 2019/8/27 15:29
 *
 */
public class ShiroRealm extends AuthorizingRealm {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private JwtRedisCache jwtRedisCache;
    @Autowired
    private UserService userService;

    /**
     * 大坑，需要重写这个方法
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }


    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException{
        log.info("-----doGetAuthenticationInfo 开始-----");
        // 这里的 token是从 JwtFilter 的 executeLogin 方法传递过来的
        String token = (String) authenticationToken.getCredentials();
        // 1.从token中获取用户名，因为用户名不是私密直接获取
        String username = JwtUtil.getUsername(token);
        // 2.从redis中取token
        String redisToken = (String) jwtRedisCache.get(SecurityConsts.USERNAME_TOKEN + username);
        if(StringUtils.isEmpty(redisToken)){
            if(userService.findByUsername(username) == null){
                //3.redis没有通过用户名到数据库中获取
                throw new AuthenticationException("非法token");
            }
        } else if(!redisToken.equals(token)){
            throw new AuthenticationException("非法token");
        }

        //获取ip与redis中ip对比
        String redisIp = (String) jwtRedisCache.get(SecurityConsts.IP_TOKEN + username);
        if(redisIp == null){
            throw new AuthenticationException("ip已过期，请重新登录！");
        } else if(!JwtUtil.getIpAddress(request).equals(redisIp)){
            throw new AuthenticationException("不是正常ip，token可能被盗用");
        }

        // 验证token异常抛出
        String refreshTokenCacheKey = SecurityConsts.REFRESH_TOKEN + username;
        if (JwtUtil.verify(token) && jwtRedisCache.get(refreshTokenCacheKey)!=null) {
            String redisCurrentTimeMillis = (String) jwtRedisCache.get(refreshTokenCacheKey);
            // 获取AccessToken时间戳，与RefreshToken的时间戳对比
            if (JwtUtil.getClaim(token, SecurityConsts.CURRENT_TIME_MILLIS).equals(redisCurrentTimeMillis)) {
                log.info("-----doGetAuthenticationInfo 结束-----");
                return new SimpleAuthenticationInfo(token, token, getName());
            }
        }

        throw new TokenExpiredException("Token过期");
    }

    /**
     * 获取权限
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //1.从主体传过来的认证信息中，获取用户对象
        String token = (String)principalCollection.getPrimaryPrincipal();
        User user = userService.findByUsername(JwtUtil.getUsername(token));
        //通过用户名到数据库获取角色和权限(通过user对象导航查询)
        Set<String> roles = userService.getRolesByUsername(user);
        Set<String> perms = userService.getPermsByUsername(user);
        //构造对象返回加上角色权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //角色
        authorizationInfo.setRoles(roles);
        //权限
        authorizationInfo.setStringPermissions(perms);
        return authorizationInfo;

    }
}