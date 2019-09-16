package com.ty.blog.base;

import com.ty.blog.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class BaseController {
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    protected HttpServletRequest request;
    @Resource
    protected HttpServletResponse response;
    @Resource
    protected HttpSession session;

    @Resource
    protected UserService userService;
    @Resource
    protected UserRoleService userRoleService;
    @Resource
    protected UserPermService userPermService;

}
