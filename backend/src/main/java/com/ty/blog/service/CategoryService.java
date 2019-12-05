package com.ty.blog.service;

import com.ty.blog.base.BaseService;
import com.ty.blog.constant.RelationTypeConsts;
import com.ty.blog.entity.Article;
import com.ty.blog.entity.Category;
import com.ty.blog.entity.Relation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: CategoryService
 * @Description: 文章分类的业务类
 * @author zhangtainyi
 * @date 2019/9/17 11:16
 *
 */
@Service
public class CategoryService extends BaseService {

    /**
     * 根据用户名获取用户对应的所有分类
     * @param username 用户名
     * @return
     */
    public List<Category> getCategories(String username){

        List<Article> articles = articleDao.findAllByAuthor(username);
        List<String> articleIds = articles.stream()
                .map(article -> String.valueOf(article.getArticleId()))
                .collect(Collectors.toList());
        List<Relation> relations = relationDao
                .findAllByRelationTypeAndCode1In(RelationTypeConsts.ARTICLE_CATEGORY, articleIds);

        List<String> categoryNames = relations.stream()
                .map(relation -> relation.getCode2())
                .collect(Collectors.toList());
        List<Category> categories = new ArrayList<>();
        categoryNames.forEach(categoryName -> {
            categories.add(categoryDao.findByCategoryName(categoryName));
        });
        return categories;
    }
}
