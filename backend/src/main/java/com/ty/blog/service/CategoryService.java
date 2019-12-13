package com.ty.blog.service;

import com.ty.blog.base.BaseService;
import com.ty.blog.entity.Article;
import com.ty.blog.entity.Category;
import com.ty.blog.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @ClassName: CategoryService
 * @Description: 文章分类的业务类
 * @author zhangtainyi
 * @date 2019/9/17 11:16
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CategoryService extends BaseService {

    /**
     * 根据用户id获取用户对应的所有分类
     * @param userId 用户id
     * @return
     */
    public Set<Category> getCategories(Long userId){
        User curUser = userDao.findById(userId).get();
        Set<Article> articles = curUser.getArticles();
        Set<Category> categories = articles.stream()
                .map(article -> article.getCategory())
                .collect(Collectors.toSet());
        return categories;
    }
}
