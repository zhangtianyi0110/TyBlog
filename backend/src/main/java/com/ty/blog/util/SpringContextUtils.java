package com.ty.blog.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 *  @ClassName: SpringContextUtils
 *  @Description: 获取Spring容器中bean工具类
 *  @author zhangtianyi
 *  @Date 2019/12/11 14:23
 */
@Component("springContextUtils")
public class SpringContextUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanId) {
        return (T) applicationContext.getBean(beanId);
    }

    public static <T> T getBean(Class<T> requiredType) {
        return (T) applicationContext.getBean(requiredType);
    }


    /**
     * Spring容器启动后，会把 applicationContext 给自动注入进来，
     * 然后我们把 applicationContext 赋值到静态变量中，方便后续拿到容器对象
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtils.applicationContext = applicationContext;
    }

    /**
     * 获取resource下的文件对象
     * @param sourcePath resource下的文件路径
     * @return
     */
    public static File getResource(String sourcePath){
        Resource resource = new ClassPathResource(sourcePath);
        File sourceFile = null;
        try {
            sourceFile = resource.getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sourceFile;
    }
}