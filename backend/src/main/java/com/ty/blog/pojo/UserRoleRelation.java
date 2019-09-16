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
@Entity(name = "ty_user_role_relation")
@Table(name = "ty_user_role_relation")
public class UserRoleRelation implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;//主键
    @Column(name = "username",unique = true,nullable = false)
    private String username;//用户名
    @Column(name = "role_name",unique = true,nullable = false)
    private String roleName;//用户名
}
