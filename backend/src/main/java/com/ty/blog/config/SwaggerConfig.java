package com.ty.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * @ClassName: SwaggerConfig
 * @Description: swagger配置类
 * @author zhangtainyi
 * @date 2019/11/28 14:53
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "TyBlog 项目集成 Swagger 实例文档",
                "SpringBoot + Vue 前后分离blog项目，欢迎大家访问我的博客网站：https://zhangtianyi.vip",
                "API V1.0",
                "Terms of service",
                new Contact("zzz", "https://zhangtianyi.vip", "zhangtianyi0110@gmail.com"),
                "Apache", "http://www.apache.org/", Collections.emptyList());
    }

}