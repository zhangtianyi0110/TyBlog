package com.ty.blog.service;

import com.ty.blog.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserPermService extends BaseService {

    public Set<String> getPermByUsername(String username){
        return userPermDao.findAllPermsByUsername(username);
    }
}
