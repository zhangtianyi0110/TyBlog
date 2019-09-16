package com.ty.blog.service;

import com.ty.blog.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserRoleService extends BaseService {

    public Set<String> getRolesByUsername(String username){

        return userRoleDao.findAllByUsername(username)
                .stream().map(role->role.getRoleName())
                .collect(Collectors.toSet());
    }
}
