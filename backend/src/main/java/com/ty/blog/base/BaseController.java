package com.ty.blog.base;

import com.ty.blog.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @ClassName: BaseController
 * @Description: controller层父类，初始化一些业务层bean和http对象
 * @author zhangtainyi
 * @date 2019/9/17 16:22
 *
 */
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


}
