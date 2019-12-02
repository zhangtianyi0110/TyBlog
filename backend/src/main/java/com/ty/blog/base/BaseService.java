package com.ty.blog.base;

import com.ty.blog.dao.ArticleDao;
import com.ty.blog.dao.CategoryDao;
import com.ty.blog.dao.RelationDao;
import com.ty.blog.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    protected UserDao userDao;
    @Autowired
    protected RelationDao relationDao;
    @Autowired
    protected ArticleDao articleDao;
    @Autowired
    protected CategoryDao categoryDao;
}
