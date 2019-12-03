package com.ty.blog.filter;

import com.ty.blog.constant.SecurityConsts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 *  @ClassName: ExceptionFilter
 *  @Description: 处理过滤器异常的过滤器，位于过滤器链第一位
 *  @author zhangtianyi
 *  @Date 2019/12/3 14:26
 */
public class ExceptionFilter implements Filter {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("异常过滤器 init...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 是否已经放有异常栈, 避免循环堆栈溢出
        boolean isRethrow = !Objects.isNull(request.getAttribute(SecurityConsts.FILTER_EXCEPTION));
        if (isRethrow) {
            chain.doFilter(request, response);
            return;
        }
        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            // 发生异常，保存异常栈
            request.setAttribute(SecurityConsts.FILTER_EXCEPTION, e);
            request.getRequestDispatcher(request.getRequestURI()).forward(request, response);
        }

    }

    @Override
    public void destroy() {

    }
}
