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

//    @GetMapping("{message}")
//    public ResponseData forbid(@PathVariable String message) {
//        return ResponseUtil.failure((int)request.getAttribute("code"), message);
//    }
    @RequestMapping("rethrow")
    public ResponseData forbid(HttpServletRequest request) throws Exception {
        ResponseData responseData = (ResponseData) request.getAttribute(SecurityConsts.FILTER_EXCEPTION);

        throw (Exception) responseData.getData();
    }
}
