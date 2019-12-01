package com.ty.blog.dao;

import com.ty.blog.entity.Link;
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
public interface LinkDao extends JpaRepository<Link, Integer> {



}
