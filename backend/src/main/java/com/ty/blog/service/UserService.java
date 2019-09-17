package com.ty.blog.service;

import com.ty.blog.base.BaseService;
import com.ty.blog.pojo.User;
import org.springframework.stereotype.Service;

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

//        String relationType1 =

        return userDao.findPermsByUsername(username);
    }

}
