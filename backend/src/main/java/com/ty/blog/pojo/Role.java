package com.ty.blog.pojo;

import com.ty.blog.constant.TableNameConsts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

  @ManyToMany
  @JoinTable(name = "tb_user_role", joinColumns = @JoinColumn(name = "role_id"),
          inverseJoinColumns = @JoinColumn(name = "user_id"))
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
