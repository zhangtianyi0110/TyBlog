package com.ty.blog.config;

import com.ty.blog.filter.ExceptionFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 *  @ClassName: WebMvcConfig
 *  @Description: 配置异常过滤器为过滤器链上第一个
 *  @author zhangtianyi
 *  @Date 2019/12/3 14:54
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Bean
    public ExceptionFilter exceptionFilter() {
        return new ExceptionFilter();
    }

    /**
     * 将异常过滤器注册为第一个
     * @return
     */
    @Bean
    public FilterRegistrationBean registerExceptionFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(exceptionFilter());
        bean.addUrlPatterns("/*");
        bean.setOrder(1);
        return bean;
    }
}