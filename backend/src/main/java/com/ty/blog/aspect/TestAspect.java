package com.ty.blog.aspect;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAspect {
    @Before("execution(public * com.ty.blog.controller.*.*(..))")
    public void test(){
        System.out.println("测试切面");
    }
}
