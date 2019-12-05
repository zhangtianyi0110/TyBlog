package com.ty.blog.dao;

import com.ty.blog.entity.Category;
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
public interface CategoryDao extends JpaRepository<Category, Long> {

    /**
     * 根据分类名字获取分类对象
     * @param categoryName 分类名字
     * @return
     */
    Category findByCategoryName(String categoryName);

}
