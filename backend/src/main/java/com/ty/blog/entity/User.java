package com.ty.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ty.blog.constant.TableNameConsts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    @Column(name = "user_id")
    private Integer id;

    /**
     * 用户名
     */
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    /**
     * 密码
     */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * 用户角色两者的关系表，由两者的主键ID组成,
     * joinColumns指定主表的外键,
     * inverseJoinColumns指定匹配表的外键
     * 用户拥有的不同角色
     * @Builder.Default:支持默认值设置的builder
     */
    @Builder.Default
    @ManyToMany(targetEntity = Role.class)
    @JoinTable(name = TableNameConsts.TY_USER_ROLE,
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "role_id")})
    private Set<Role> roles = new HashSet<>();

    /**
     * 用户的额外权限
     * 用户权限两者的关系表，由两者的主键ID组成,
     * joinColumns指定主表的外键,
     * inverseJoinColumns指定匹配表的外键
     * 用户拥有的不同角色
     * @Builder.Default:支持默认值设置的builder
     */
    @Builder.Default
    @ManyToMany(targetEntity = Perm.class)
    @JoinTable(name = TableNameConsts.TY_USER_PERM,
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "perm_id", referencedColumnName = "perm_id")})
    private Set<Role> perms = new HashSet<>();

    /**
     * 性别
     */
    @Column(name = "gender", nullable = false, length = 10)
    private String gender;

    /**
     * 名字
     */
    @Column(name = "name")
    private String name;

    /**
     * 昵称
     */
    @Column(name = "nickname", nullable = false)
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
    @Column(name = "profile", length = 1000)
    private String profile;

    /**
     * 头像url地址
     */
    @Column(name = "avatar_url", length = 10000)
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_login_time")
    private Date lastLoginTime;

    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false, length = 30)
    private Date createTime;

    /**
     * 最后修改时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_time", nullable = false)
    private Date modifyTime;



}
