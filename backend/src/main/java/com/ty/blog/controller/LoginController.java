package com.ty.blog.controller;

import com.ty.blog.base.BaseController;
import com.ty.blog.constant.SecurityConsts;
import com.ty.blog.entity.ResponseData;
import com.ty.blog.entity.User;
import com.ty.blog.shiro.jwt.JwtConfig;
import com.ty.blog.shiro.jwt.JwtRedisCache;
import com.ty.blog.shiro.jwt.JwtUtil;
import com.ty.blog.util.Md5Util;
import com.ty.blog.util.ResponseUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

/**
 * @ClassName: LoginController
 * @Description: 登录功能controller
 * @author zhangtainyi
 * @date 2019/9/17 16:22
 *
 */
@RestController
@RequestMapping("user")
//@CrossOrigin(maxAge = 3600)
public class LoginController extends BaseController {


    @Autowired
    private JwtRedisCache jwtRedisCache;
    @Autowired
    private JwtConfig jwtConfig;

//    @GetMapping("/login")
//    public String login(){
//        //如果已经认证通过，直接跳转到首页
//        if (SecurityUtils.getSubject().isAuthenticated()) {
//            return "redirect:/index";
//        }
//        return "login";
//    }

    /**
     * 登录
     * @param user 获取用户的用户名和密码
     * @return token
     * @throws Exception
     */
    @PostMapping("/login")
    @ResponseBody
    public ResponseData login(@Valid @RequestBody User user) throws Exception{
        log.info("开始认证...");
        String username = user.getUsername();
        String password = Md5Util.encrypt(user.getPassword(),username);
        User realUser = userService.findByUsername(username);

        if (realUser == null) {
            throw new UnknownAccountException();
        } else {
            String realPassword = userService.findByUsername(username).getPassword();
            if (realPassword==null && !realPassword.equals(password)) {
                throw new IncorrectCredentialsException();
            }
        }
        String currentTimeMillis = String.valueOf(System.currentTimeMillis());
        //生成签名
        String jwt = JwtUtil.sign(user.getUsername(), currentTimeMillis);
        //将token存入redis
        jwtRedisCache.put(SecurityConsts.USERNAME_TOKEN + username, jwt, jwtConfig.getTokenExpireTime());
        //将时间戳存入缓存
        jwtRedisCache.put(SecurityConsts.REFRESH_TOKEN + username, currentTimeMillis, jwtConfig.getRefreshTokenExpireTime());
        //将ip存入，防止其他用户使用token侵入
        jwtRedisCache.put(SecurityConsts.IP_TOKEN + username, JwtUtil.getIpAddress(request), jwtConfig.getRefreshTokenExpireTime());
        log.info("当前用户登录ip为:"+JwtUtil.getIpAddress(request));
        log.info("结束认证...");
        return ResponseUtil.success("登录成功", jwt);
    }

    /**
     * 获取用户信息
     * @param token 用户token
     * @return
     */
    @GetMapping("/info")
    @RequiresAuthentication
    public ResponseData info(String token){
        Map<String, Object> info = userService.getInfo(token);
        return ResponseUtil.success(info);
    }

    /**
     * 登出
     * @return
     */
    @PostMapping("/logout")
    public ResponseData logout(){
        Subject subject = SecurityUtils.getSubject();
        String token = (String) subject.getPrincipal();
        String username = JwtUtil.getUsername(token);
        //清除redis缓存
        jwtRedisCache.put(SecurityConsts.USERNAME_TOKEN + username, null, 0);
        jwtRedisCache.put(SecurityConsts.REFRESH_TOKEN + username, null, 0);
        jwtRedisCache.put(SecurityConsts.IP_TOKEN + username, null, 0);
        //登出
        SecurityUtils.getSubject().logout();
        return ResponseUtil.success("注销成功");
    }


    @GetMapping("/article")
    @RequiresAuthentication
    public ResponseData article(){
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return ResponseUtil.success("You are already logged in", null);
        } else {
            return ResponseUtil.success("You are guest", null);
        }
    }


    @RequestMapping("/index")
    @RequiresPermissions("user:get")
    public ResponseData index(Model model) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("user", user);
        return ResponseUtil.success("success", null);
    }
}
