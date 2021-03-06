package com.ty.blog.dao;

import com.ty.blog.entity.Article;
import com.ty.blog.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public interface ArticleDao extends JpaRepository<Article, Long> {

    /**
     * 通过用户名查询文章集合，不分页
     * @param author 用户笔名
     * @return 该作者所有文章集合
     */
    List<Article> findAllByAuthor(User author);

    /**
     * 通过用户名查询文章集合，分页
     * @param author 用户笔名
     * @return 该作者所有文章集合
     */
    Page<Article> findAllByAuthor(User author, Pageable pageRequest);


    /**
     * 通过标题查找文章
     * @param title 标题
     * @return
     */
    Article findByTitle(String title);


}
