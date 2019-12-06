package com.ty.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@ApiModel("权限实体")
@Getter
@Setter
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
  private Long permId;

  /**
   * 权限名
   */
  @ApiModelProperty(value = "权限名", required = true)
  @Column(unique = true, nullable = false)
  private String permName;

  /**
   * 权限描述
   */
  @ApiModelProperty("权限描述")
  @Column(unique = true, nullable = false)
  private String permDesc;

  /**
   * 维护权限与角色关系
   * 权限方放弃维护权
   */
  @ApiModelProperty("维护权限与角色关系")
  @Builder.Default
  @JsonIgnore
  @ManyToMany(mappedBy = "perms")
  private Set<Role> roles = new HashSet<>();

  /**
   * 维护权限与用户关系
   * 权限方放弃维护权
   */
  @ApiModelProperty("维护权限与用户关系")
  @Builder.Default
  @JsonIgnore
  @ManyToMany(mappedBy = "perms")
  private Set<User> users = new HashSet<>();

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
