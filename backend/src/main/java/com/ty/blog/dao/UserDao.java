package com.ty.blog.dao;

import com.ty.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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


}
