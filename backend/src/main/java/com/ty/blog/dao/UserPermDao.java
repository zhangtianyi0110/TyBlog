package com.ty.blog.dao;

import com.ty.blog.pojo.UserPermRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserPermDao extends JpaRepository<UserPermRelation, Long> {

    @Query(value = "select perm from ty_role_perm_relation where role_name " +
            "in (select role_name from ty_user_role_relation where username = :username) " +
            "UNION select perm from ty_user_perm_relation where username = :username"
            ,nativeQuery = true)
    Set<String> findAllPermsByUsername(@Param("username") String username);
}
