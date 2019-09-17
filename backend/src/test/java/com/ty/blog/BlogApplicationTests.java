package com.ty.blog;

import com.ty.blog.pojo.User;
import com.ty.blog.service.UserService;
import com.ty.blog.shiro.jwt.JwtRedisCache;
import com.ty.blog.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.persistence.Entity;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {

    @Resource
    protected UserService userService;


    @Autowired
    JwtRedisCache jwtRedisCache;
    @Autowired
    RedisUtil redisUtil;

    @Test
    public void contextLoads() {
        Annotation annotation = null;
        try {
            annotation = new User().getClass().getAnnotation(Entity.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String name = ((Entity) annotation).name();
        System.out.println(name);
        Field[] declaredFields = annotation.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {

            System.out.println(declaredField.getName());
        }

        System.out.println(annotation.getClass().getDeclaredFields());
    }
    @Test
    public void test1(){



    }


    @Test
    public void getPermByUsername(){

    }

}
