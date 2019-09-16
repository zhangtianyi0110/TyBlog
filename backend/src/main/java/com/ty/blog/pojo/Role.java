package com.ty.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

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
@Entity(name = "ty_role")
@Table(name = "ty_role")
public class Role implements Serializable {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private int id;
  @Column(name = "role_name",unique = true,nullable = false)
  private String roleName;

}
