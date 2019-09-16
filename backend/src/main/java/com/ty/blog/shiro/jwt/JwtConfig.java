package com.ty.blog.shiro.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:properties/jwt.properties")
@ConfigurationProperties(prefix = "token")
@Configuration
@Data
public class JwtConfig {
    //token过期时间，单位分钟
//    @Value("${token.tokenExpireTime}")
    private Integer tokenExpireTime;
    
    //刷新Token过期时间，单位分钟
//    @Value("${token.refreshTokenExpireTime}")
    private Integer refreshTokenExpireTime;
    
    //Shiro缓存有效期，单位分钟
//    @Value("${token.shiroCacheExpireTime}")
    private Integer shiroCacheExpireTime;
    
    //token加密密钥
//    @Value("${token.secretKey}")
    private String secretKey;



}