package com.ty.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ty_role_perm_relation")
@Table(name = "ty_role_perm_relation")
public class RolePermRelation implements Serializable {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private int id;
  @Column(name = "role_name",unique = true,nullable = false)
  private String roleName;//角色名
  @Column(name = "perm",unique = true,nullable = false)
  private String perm;//权限名

}
