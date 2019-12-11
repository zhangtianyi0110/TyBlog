package com.ty.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ty.blog.constant.TableNameConsts;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
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
@ApiModel("用户实体")
@Getter
@Setter
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", required = true)
    @NotBlank(message = "用户名不能为空")
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码,长度大于6", required = true, allowableValues = "123456")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "密码不能为空")
    @Length(min = 6, message = "密码不能少于6位")
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    @Builder.Default
    @Column(name = "gender", nullable = false, length = 10)
    private String gender = "不详";

    /**
     * 名字
     */
    @ApiModelProperty(value = "名字")
    @Column(name = "name")
    private String name;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称", allowableValues = "皮卡丘")
    @Column(name = "nickname")
    private String nickname;

    /**
     * 生日
     */
    @ApiModelProperty(value = "生日")
    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    @Column(name = "email")
    private String email;

    /**
     * 个人简介
     */
    @ApiModelProperty("个人简介")
    @Column(name = "profile")
    private String profile;

    /**
     * 头像url地址
     */
    @ApiModelProperty("头像url地址")
    @Lob
    @Column(name = "avatar_url", columnDefinition = "TEXT")
    private String avatarUrl;

    /**
     * github用户名
     */
    @ApiModelProperty("github用户名")
    @Column(name = "github_id")
    private String githubId;

    /**
     * github链接
     */
    @ApiModelProperty("github链接")
    @Lob
    @Column(name = "github_url", columnDefinition = "TEXT")
    private String githubUrl;

    /**
     * 用户是否是超级管理员
     */
    @ApiModelProperty("用户是否是超级管理员")
    @Builder.Default
    @Lob
    @Column(name = "is_admin")
    private Boolean isAdmin = false;

    /**
     * 用户角色两者的关系表，由两者的主键ID组成,
     * joinColumns指定主表的外键,
     * inverseJoinColumns指定匹配表的外键
     * 用户拥有的不同角色
     * Builder.Default:支持默认值设置的builder
     */
    @ApiModelProperty("用户角色集合")
    @Builder.Default
    @JsonIgnore
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
     * Builder.Default:支持默认值设置的builder
     */
    @ApiModelProperty("用户除角色拥有的权限之外的权限集合")
    @Builder.Default
    @JsonIgnore
    @ManyToMany(targetEntity = Perm.class)
    @JoinTable(name = TableNameConsts.TY_USER_PERM,
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "perm_id", referencedColumnName = "perm_id")})
    private Set<Perm> perms = new HashSet<>();

    /**
     * 用户所有文章
     */
    @ApiModelProperty("用户名下所有的文章")
    @Builder.Default
    @JsonIgnore
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private Set<Article> articles = new HashSet<>();

    /**
     * 用户名下所有的评论，删除用户保留评论
     */
    @ApiModelProperty("用户名下所有的评论")
    @Builder.Default
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<Comment> comments = new HashSet<>();

    /**
     * 最后一次登录时间
     */
    @ApiModelProperty("最后一次登录时间")
    @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_login_time")
    private Date lastLoginTime;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false)
    private Date createTime;

    /**
     * 最后修改时间
     */
    @ApiModelProperty("最后修改时间")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_time", nullable = false)
    private Date modifyTime;



}
