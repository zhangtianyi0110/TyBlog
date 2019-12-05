package com.ty.blog.entity;

import com.ty.blog.constant.TableNameConsts;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@ApiModel("角色实体")
@Getter
@Setter
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
  private Long roleId;

  /**
   * 角色名
   */
  @ApiModelProperty(value = "角色名", required = true)
  @Column(name = "role_name", unique = true, nullable = false)
  private String roleName;

  /**
   * 角色描述
   */
  @ApiModelProperty(value = "角色描述")
  @Column(name = "role_desc")
  private String roleDesc;

  /**
   * 维护用户与角色关系
   * 角色放弃维护权
   */
  @ApiModelProperty(value = "维护用户与角色关系")
  @Builder.Default
  @ManyToMany(mappedBy = "roles")
  private Set<User> users = new HashSet<>();

  /**
   * 维护角色和权限关系
   * 角色为维护权主控方
   */
  @ApiModelProperty(value = "维护角色和权限关系")
  @Builder.Default
  @ManyToMany(targetEntity = Perm.class)
  @JoinTable(name = TableNameConsts.TY_ROLE_PERM,
          joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "role_id")},
          inverseJoinColumns = {@JoinColumn(name = "perm_id", referencedColumnName = "perm_id")})
  private Set<Perm> perms = new HashSet<>();

  /**
   * 创建时间
   */
  @ApiModelProperty("创建时间")
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "create_time", nullable = false, length = 30)
  private Date createTime;

  /**
   * 最后修改时间
   */
  @ApiModelProperty("最后修改时间")
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "modify_time", nullable = false)
  private Date modifyTime;

}
