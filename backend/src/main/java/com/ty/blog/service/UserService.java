package com.ty.blog.service;

import com.google.gson.Gson;
import com.ty.blog.base.BaseService;
import com.ty.blog.pojo.User;
import com.ty.blog.shiro.jwt.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName: UserService
 * @Description: 用户的业务类
 * @author zhangtainyi
 * @date 2019/9/17 11:16
 *
 */
@Service
public class UserService extends BaseService {

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
     * @param username 用户名
     * @return
     */
    public Set<String> getRolesByUsername(String username){

        return userDao.findRolesByUsername(username);
    }

    /**
     * 根据用户名获取用户对应的所有权限
     * @param username
     * @return
     */
    public Set<String> getPermByUsername(String username){

        return userDao.findPermsByUsername(username);
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
        Set<String> roles = getRolesByUsername(username);
        Set<String> perms = getPermByUsername(username);
        info.put("user", user);
        info.put("roles", roles);
        info.put("perms", roles);
        log.info("用户信息:" + new Gson().toJson(info));
        return info;
    }


}
