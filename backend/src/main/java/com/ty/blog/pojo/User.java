package com.ty.blog.pojo;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName: User
 * @Description: 用户实体类
 * @author zhangtainyi
 * @date 2019/9/16 11:51
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ty_user")
@Table(name = "ty_user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;//主键
    @Column(name = "username",unique = true,nullable = false)
    private String username;//用户名
    @Column(name = "password",nullable = false)
    private String password;//密码
    @Column(name = "gender",nullable = false,length = 10)
    private String gender;//性别
    @Column(name = "name",nullable = false)
    private String name;//名字
    @Column(name = "nickname",nullable = false)
    private String nickname;//昵称
    @Column(name = "birthday",nullable = false)
    private String birthday;//生日
    @Column(name = "email",nullable = false)
    private String email;//邮箱
    @Column(name = "personal_profile",nullable = false,length = 255)
    private String personalProfile;//个人简介
    @Column(name = "avatar_url",length = 255)
    private String avatarUrl;//头像url地址
    @Column(name = "github_id",length = 255)
    private String githubId;//github用户名
    @Column(name = "last_login_time",nullable = false,length = 30)
    private String lastLoginTime;//最后一次登录时间
    @Column(name = "last_update_time",nullable = false,length = 30)
    private String lastUpdateTime;//最后修改时间
    @Column(name = "create_time",nullable = false,length = 30)
    private String createTime;//创建时间

}
