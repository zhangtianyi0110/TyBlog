package com.ty.blog.dao;

import com.ty.blog.constant.RelationTypeConsts;
import com.ty.blog.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @ClassName: UserDao
 * @Description: 用户实体持久层
 * @author zhangtainyi
 * @date 2019/9/17 16:21
 *
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> {

    /**
     * 通过用户名查询用户对象
     * @param username 用户名
     * @return 用户对象
     */
    User findByUsername(String username);

    /**
     * 通过用户名获取用户所有权限
     * @param username 用户名
     * @return
     */
    @Query(value = "select code_2 from ty_relation where type = " + RelationTypeConsts.ROLE_PERM + " and code_1 " +
            "in (select code_2 from ty_relation where type = " + RelationTypeConsts.USER_ROLE + " and code_1 = :username) " +
            "UNION select code_2 from ty_relation where type= " + RelationTypeConsts.USER_PERM + " and username = :username"
            , nativeQuery = true)
    Set<String> findPermsByUsername(@Param("username") String username);

    /**
     * 通过用户名获取用户所有角色
     * @param username 用户名
     * @return
     */
    @Query(value = "select code_2 from ty_relation " +
            "where type = " + RelationTypeConsts.USER_ROLE +
            " and code_1 = :username", nativeQuery = true)
    Set<String> findRolesByUsername(@Param("username") String username);
}
