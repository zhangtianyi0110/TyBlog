package com.ty.blog.dao;

import com.ty.blog.pojo.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: ArticleDao
 * @Description: 文章实体持久层
 * @author zhangtainyi
 * @date 2019/11/20 16:21
 *
 */
@Repository
public interface ArticleDao extends JpaRepository<Article, Integer> {

    /**
     * 通过用户名查询用户对象
     * @param author 用户笔名
     * @return 该作者所有文章集合
     */
    List<Article> findAllByAuthor(String author);


}
