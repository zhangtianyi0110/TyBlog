package com.ty.blog.shiro.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *  @ClassName: JwtConfig
 *  @Description: jwt配置类，配置文件为jwt.properties
 *  @author zhangtianyi
 *  @Date 2019/12/2 14:10
 */
@Data
@Configuration
@PropertySource("classpath:properties/jwt.properties")
@ConfigurationProperties(prefix = "token")
public class JwtConfig {
    /**
     * token过期时间，单位分钟
     */
    private Integer tokenExpireTime;

    /**
     * 刷新Token过期时间，单位分钟
     */
    private Integer refreshTokenExpireTime;

    /**
     * Shiro缓存有效期，单位分钟
     */
    private Integer shiroCacheExpireTime;

    /**
     * token加密密钥
     */
    private String secretKey;


}