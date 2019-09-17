package com.ty.blog;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/** 
 * @ClassName: ServletInitializer
 * @Description: (用一句话描述)
 * @author zhangtainyi
 * @date 2019/9/17 16:49
 *
 */
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BlogApplication.class);
    }

}
