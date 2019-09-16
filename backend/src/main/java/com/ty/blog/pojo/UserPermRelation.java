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
@Entity(name = "ty_user_perm_relation")
@Table(name = "ty_user_perm_relation")
public class UserPermRelation implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;//主键
    @Column(name = "username",unique = true,nullable = false)
    private String username;//用户名
    @Column(name = "perm",unique = true,nullable = false)
    private String perm;//权限名
}
