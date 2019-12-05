package com.ty.blog.dao;

import com.ty.blog.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: ArticleDao
 * @Description: 分类实体持久层
 * @author zhangtainyi
 * @date 2019/11/20 16:21
 *
 */
@Repository
public interface TagDao extends JpaRepository<Tag, Long> {



}
