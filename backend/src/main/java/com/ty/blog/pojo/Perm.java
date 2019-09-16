package com.ty.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
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
@Entity(name = "ty_perm")
@Table(name = "ty_perm")
public class Perm implements Serializable {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private int id;
  @Column(name = "perm",unique = true,nullable = false)
  private String perm;


}
