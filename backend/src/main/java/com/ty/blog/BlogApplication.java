package com.ty.blog;

import com.ty.blog.shiro.jwt.JwtConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @ClassName: BlogApplication
 * @Description: 启动类
 * @author zhangtainyi
 * @date 2019/9/17 16:48
 *
 */
@SpringBootApplication
@EnableConfigurationProperties({JwtConfig.class})
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

}
