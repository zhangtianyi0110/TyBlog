package com.ty.blog.service;

import com.ty.blog.base.BaseService;
import com.ty.blog.entity.User;
import com.ty.blog.shiro.jwt.JwtUtil;
import com.ty.blog.util.JacksonUtil;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @ClassName: UserService
 * @Description: 用户的业务类
 * @author zhangtainyi
 * @date 2019/9/17 11:16
 *
 */
@Service
public class UserService extends BaseService {

    public void updateLastLoginTime(User user){
        user.setLastLoginTime(Calendar.getInstance().getTime());
        user.setModifyTime(new Date());
        userDao.saveAndFlush(user);
    }

    /**
     * 根据用户名获取用户对象
     * @param username
     * @return
     */
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    /**
     * 根据用户名获取用户对应角色
     * @param user 用户对象
     * @return
     */
    public Set<String> getRolesByUsername(User user){

        Set<String> roles = user.getRoles().stream()
                .map(role -> role.getRoleName()).collect(Collectors.toSet());

        return roles;
    }

    /**
     * 根据用户名获取用户对应的所有权限
     * @param user 用户对象
     * @return
     */
    public Set<String> getPermsByUsername(User user){

        Set<String> perms = new HashSet<>();
        user.getRoles().forEach(role -> {
            perms.addAll(role.getPerms()
                    .stream()
                    .map(perm -> perm.getPermName())
                    .collect(Collectors.toSet()));
        });
        perms.addAll(user.getPerms()
                .stream()
                .map(perm -> perm.getPermName())
                .collect(Collectors.toSet()));

        return perms;
    }

    /**
     * 获取用户的信息包括权限角色和基本信息
     * @param token 用户token
     * @return
     */
    public Map<String, Object> getInfo(String token){

        String username = JwtUtil.getUsername(token);
        Map<String, Object> info = new HashMap<>();
        User user = findByUsername(username);
        Set<String> roles = getRolesByUsername(user);
        Set<String> perms = getPermsByUsername(user);
        info.put("user", user);
        info.put("roles", roles);
        info.put("perms", perms);
        log.info("用户信息:" + JacksonUtil.formatDate(info, "yyyy年MM月dd日 HH:mm:ss"));
        return info;
    }

}
