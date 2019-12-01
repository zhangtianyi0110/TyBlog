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
 * @ClassName: Role
 * @Description: 角色实体
 * @author zhangtainyi
 * @date 2019/9/16 11:51
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = TableNameConsts.TY_ROLE)
@Table(name = TableNameConsts.TY_ROLE)
public class Role implements Serializable {

  /**
   * 主键自增
   */
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  @Column(name = "role_id")
  private int id;

  /**
   * 角色名
   */
  @Column(name = "role_name", unique = true, nullable = false)
  private String roleName;

  /**
   * 角色描述
   */
  @Column(name = "role_desc")
  private String roleDesc;

  /**
   * 维护用户与角色关系
   * 角色放弃维护权
   */
  @Builder.Default
  @ManyToMany(mappedBy = "roles")
  private Set<User> users = new HashSet<>();

  /**
   * 维护角色和权限关系
   * 角色为维护权主控方
   */
  @Builder.Default
  @ManyToMany(targetEntity = Perm.class)
  @JoinTable(name = TableNameConsts.TY_ROLE_PERM,
          joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "role_id")},
          inverseJoinColumns = {@JoinColumn(name = "perm_id", referencedColumnName = "perm_id")})
  private Set<Perm> perms = new HashSet<>();

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
