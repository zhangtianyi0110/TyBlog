package com.ty.blog.handler;

import com.ty.blog.entity.ResponseData;
import com.ty.blog.util.ResponseUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: ShiroExceptionHandler
 * @Description: shiro异常全局处理
 * @author zhangtainyi
 * @date 2019/9/17 16:19
 *
 */
@RestControllerAdvice
public class ShiroExceptionHandler {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 对shiro认证抛出的异常统一处理
     */
    @ExceptionHandler(AuthenticationException.class)
    public ResponseData handleAuthenticationException(HttpServletRequest request,
                                                      HttpServletResponse response, Exception e) throws IOException {

        //系统异常打印
        log.error(e.getMessage());
        if(e instanceof UnknownAccountException){
            return ResponseUtil.failure(401,"用户名不存在");
        }else if(e instanceof IncorrectCredentialsException){
            return ResponseUtil.failure(401,"用户名或密码错误");
        }
        //异常回传信息
        return ResponseUtil.failure(401,"认证失败！");
    }
    /**
     * 对shiro授权抛出的异常统一处理
     */
    @ExceptionHandler(AuthorizationException.class)
    public ResponseData handleAuthorizationException(HttpServletRequest request,
                                               HttpServletResponse response, Exception e) throws IOException {
        //系统异常打印
        log.error(e.getMessage());
        return ResponseUtil.failure(403,"权限不足！");
    }

}
