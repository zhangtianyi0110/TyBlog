package com.ty.blog.controller;

import com.ty.blog.base.BaseController;
import com.ty.blog.constant.SecurityConsts;
import com.ty.blog.entity.ResponseData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 *  @ClassName: ErrorController
 *  @Description: 非法请求转发
 *  @author zty
 *  @Date 2019/12/2 17:50
 */
@RestController
@RequestMapping("error")
public class ErrorController extends BaseController {

    /**
     * 重新抛出filter发生的异常
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("rethrow")
    public ResponseData forbid(HttpServletRequest request) throws Exception {
        ResponseData responseData = (ResponseData) request.getAttribute(SecurityConsts.FILTER_EXCEPTION);
        throw (Exception) responseData.getData();
    }
}
