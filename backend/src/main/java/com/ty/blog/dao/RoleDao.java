package com.ty.blog.dao;

import com.ty.blog.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *  @ClassName: RoleDao
 *  @Description: 角色实体持久层
 *  @author: zhangtianyi
 *  @Date: 2019-09-19 21:53
 *
 */
@Repository
public interface RoleDao extends JpaRepository<Role, Long> {


}
