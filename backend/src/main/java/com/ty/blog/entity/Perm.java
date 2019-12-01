package com.ty.blog.entity;

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
 * @ClassName: Perm
 * @Description: 权限实体
 * @author zhangtainyi
 * @date 2019/9/16 11:51
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = TableNameConsts.TY_PERM)
@Table(name = TableNameConsts.TY_PERM)
public class Perm implements Serializable {

  /**
   * 主键自增
   */
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  @Column(name = "perm_id")
  private int id;

  /**
   * 权限名
   */
  @Column(name = "perm", unique = true, nullable = false)
  private String perm;

  /**
   * 权限描述
   */
  @Column(name = "perm_desc", unique = true, nullable = false)
  private String permDesc;

  /**
   * 维护权限与角色关系
   * 权限方放弃维护权
   */
  @Builder.Default
  @ManyToMany(mappedBy = "perms")
  private Set<Role> roles = new HashSet<>();

  /**
   * 维护权限与用户关系
   * 权限方放弃维护权
   */
  @Builder.Default
  @ManyToMany(mappedBy = "perms")
  private Set<User> users = new HashSet<>();

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
