package com.ty.blog;

import com.ty.blog.dao.UserPermDao;
import com.ty.blog.dao.UserRoleDao;
import com.ty.blog.pojo.UserRoleRelation;
import com.ty.blog.service.*;
import com.ty.blog.shiro.jwt.JwtRedisCache;
import com.ty.blog.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Set;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {

    @Resource
    protected UserService userService;
    @Resource
    protected UserRoleService userRoleService;
    @Resource
    protected UserPermService userPermService;

    @Resource
    protected UserRoleDao userRoleDao;
    @Resource
    protected UserPermDao userPermDao;

    @Autowired
    JwtRedisCache jwtRedisCache;
    @Autowired
    RedisUtil redisUtil;

    @Test
    public void contextLoads() {

    }
    @Test
    public void test1(){
        Set<UserRoleRelation> admins = userRoleDao.findAllByUsername("admin");
        Set<String> collect = admins.stream().map(admin -> admin.getRoleName()).collect(Collectors.toSet());
        collect.forEach(c -> {
            System.out.println(c);
        });
    }


    @Test
    public void getPermByUsername(){
        String username = "admin";
        Set<String> perms =  userPermService.getPermByUsername(username);
        perms.forEach(perm -> {
            System.out.println(perm);
        });
    }

}
