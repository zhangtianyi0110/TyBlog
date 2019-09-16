package com.ty.blog.service;

import com.ty.blog.base.BaseService;
import com.ty.blog.pojo.User;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService {

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
