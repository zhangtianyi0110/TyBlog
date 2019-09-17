package com.ty.blog;

import com.ty.blog.dao.UserDao;
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
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {

    @Resource
    protected UserService userService;
    @Resource
    protected UserDao userDao;

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
    public void addUser(){
        User user = User.builder().username("zzz").password("123456").gender("男").name("张小生").nickname("tyking").birthday(new Date())
                .email("123@qq.com").personalProfile("hello world").avatarUrl("baidu.com").githubId("zty111").githubUrl("google.com")
                .lastLoginTime(new Date()).createTime(new Date()).modifyTime(new Date()).build();
        userDao.saveAndFlush(user);

    }


    @Test
    public void getPermByUsername(){

    }

}
