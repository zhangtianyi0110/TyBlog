package com.ty.blog.shiro;

import com.ty.blog.shiro.cache.ShiroRedisCacheManager;
import com.ty.blog.shiro.jwt.JwtFilter;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;

/**
 *  @ClassName: ShiroConfig
 *  @Description: Shiro配置类
 *  @author: zhangtianyi
 *  @Date: 2019-09-04 21:02
 *  @DependsOn: 在JwtProperties后加载
 */
@Configuration
@DependsOn({"jwtConfig","redisConfig"})
public class ShiroConfig {
    private Logger log = LoggerFactory.getLogger(ShiroConfig.class);

    @Autowired
    private JwtFilter jwtFilter;
    @Autowired
    private ShiroRedisCacheManager shiroRedisCacheManager;

    /**
     * 将自定义realm让spring管理
     * @return 自定义Realm管理器
     */
    @Bean
    public ShiroRealm customRealm(){
        ShiroRealm customRealm = new ShiroRealm();
        log.info("加载ShiroRealm完成...");
        return customRealm;
    }


    /**
     * 注入自定义realm、EhCacheManager/ReidsCacheManager对象
     * @return SecurityManager
     */
    @Bean
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //注入自定义Realm
        securityManager.setRealm(customRealm());
        //注入RedisCacheManager
        securityManager.setCacheManager(shiroRedisCacheManager);
        return securityManager;
    }


    /**
     * 开启shiro注解
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new
                AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 配置shiro的web过滤器,是shiro的核心配置,shiro的所有功能都基于这个对象
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //无权限跳转
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized/**");
        // 在 Shiro过滤器链上加入 JwtFilter
        LinkedHashMap<String, Filter> filters = new LinkedHashMap<>();
        filters.put("jwt", jwtFilter);
        shiroFilterFactoryBean.setFilters(filters);

        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //放行不需要认证的接口
        filterChainDefinitionMap.put("/logout","logout");
        filterChainDefinitionMap.put("/login","anon");
        filterChainDefinitionMap.put("/unauthorized/**","anon");
        filterChainDefinitionMap.put("/**", "jwt");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }
}

