package com.ty.blog.base;

import com.ty.blog.dao.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName: BaseService
 * @Description: service类父类，初始化一些持久层bean
 * @author zhangtainyi
 * @date 2019/9/17 16:23
 *
 */
@Service
public class BaseService {
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    protected UserDao userDao;
    @Resource
    protected RelationDao relationDao;
    @Resource
    protected ArticleDao articleDao;
    @Resource
    protected CategoryDao categoryDao;
}
