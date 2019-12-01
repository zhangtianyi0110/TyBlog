package com.ty.blog.dao;

import com.ty.blog.entity.Perm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *  @ClassName: PermDao
 *  @Description: 权限持久层
 *  @author: zhangtianyi
 *  @Date: 2019-09-19 21:53
 *
 */
@Repository
public interface PermDao extends JpaRepository<Perm, Integer> {


}
