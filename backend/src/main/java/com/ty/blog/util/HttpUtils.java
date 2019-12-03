package com.ty.blog.util;

import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  @ClassName: HttpUtils
 *  @Description: 获取request、response、session的工具方法
 *  @author zhangtianyi
 *  @Date 2019/12/3 17:06
 */
public class HttpUtils {

    /**
     * 获取request
     * @return
     */
    public static HttpServletRequest getRequest(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        return servletRequestAttributes.getRequest();
    }

    /**
     * 获取response
     * @return
     */
    public static HttpServletResponse getResponse(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        return servletRequestAttributes.getResponse();
    }

    /**
     * 获取session
     * @return
     */
    public static HttpServletSession getSession(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        return (HttpServletSession) servletRequestAttributes.getRequest().getSession();
    }
}
