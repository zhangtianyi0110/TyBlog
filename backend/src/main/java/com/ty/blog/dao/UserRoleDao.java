package com.ty.blog.dao;

import com.ty.blog.pojo.UserRoleRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRoleDao extends JpaRepository<UserRoleRelation, Long> {

    Set<UserRoleRelation> findAllByUsername(String username);

}
