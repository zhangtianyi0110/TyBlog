package com.ty.blog.base;

import com.ty.blog.dao.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BaseService {
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    protected UserDao userDao;
    @Resource
    protected UserRoleDao userRoleDao;
    @Resource
    protected UserPermDao userPermDao;
}
