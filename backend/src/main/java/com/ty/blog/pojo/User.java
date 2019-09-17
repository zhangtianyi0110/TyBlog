package com.ty.blog.pojo;

import com.ty.blog.constant.TableNameConsts;
import lombok.*;
import javax.persistence.*;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.Date;

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
@Entity(name = TableNameConsts.TY_USER)
@Table(name = TableNameConsts.TY_USER)
public class User implements Serializable {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户名
     */
    @Column(name = "username",unique = true,nullable = false)
    private String username;

    /**
     * 密码
     */
    @Column(name = "password",nullable = false)
    private String password;

    /**
     * 性别
     */
    @Column(name = "gender",nullable = false,length = 10)
    private String gender;

    /**
     * 名字
     */
    @Column(name = "name")
    private String name;

    /**
     * 昵称
     */
    @Column(name = "nickname",nullable = false)
    private String nickname;

    /**
     * 生日
     */
    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;

    /**
     * 邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 个人简介
     */
    @Column(name = "personal_profile")
    private String personalProfile;

    /**
     * 头像url地址
     */
    @Column(name = "avatar_url")
    private String avatarUrl;

    /**
     * github用户名
     */
    @Column(name = "github_id")
    private String githubId;

    /**
     * github链接
     */
    @Column(name = "github_url")
    private String githubUrl;

    /**
     * 最后一次登录时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_login_time")
    private Date lastLoginTime;

    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time",nullable = false,length = 30)
    private Date createTime;

    /**
     * 最后修改时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_time",nullable = false)
    private Date modifyTime;



}
