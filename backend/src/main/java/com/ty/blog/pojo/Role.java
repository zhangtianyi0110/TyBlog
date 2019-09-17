package com.ty.blog.pojo;

import com.ty.blog.constant.TableNameConsts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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
  @Column(name = "role_name",unique = true,nullable = false)
  private String roleName;

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
