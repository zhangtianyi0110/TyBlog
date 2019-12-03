package com.ty.blog.controller;

import com.ty.blog.base.BaseController;
import com.ty.blog.entity.ResponseData;
import com.ty.blog.util.ResponseUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  @ClassName: ErrorController
 *  @Description: 非法请求转发
 *  @author zty
 *  @Date 2019/12/2 17:50
 */
@RestController("unauthorized")
public class ErrorController extends BaseController {

//    @GetMapping("{message}")
//    public ResponseData forbid(@PathVariable String message) {
//        return ResponseUtil.failure((int)request.getAttribute("code"), message);
//    }
    @RequestMapping("rethrow")
    public ResponseData forbid() {
        String message = (String) request.getAttribute("message");

        return ResponseUtil.failure((int)request.getAttribute("code"), message);
    }
}
