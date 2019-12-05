package com.ty.blog.dao;

import com.ty.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: ArticleDao
 * @Description: 文章实体持久层
 * @author zhangtainyi
 * @date 2019/11/20 16:21
 *
 */
@Repository
public interface CommentDao extends JpaRepository<Comment, Long> {



}
