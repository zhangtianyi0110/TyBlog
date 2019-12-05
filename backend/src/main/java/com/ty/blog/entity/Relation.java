package com.ty.blog.entity;

import com.ty.blog.constant.TableNameConsts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: Relation
 * @Description: 关系表
 * @author zhangtainyi
 * @date 2019/9/17 11:06
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = TableNameConsts.TY_RELATION)
@Table(name = TableNameConsts.TY_RELATION)
public class Relation implements Serializable {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;

  /**
   * 关系类型，格式：（表名_表名）
   */
  @Column(name = "relation_type", nullable = false)
  private String relationType;

  /**
   * 关系码值1(第一个表内字段)
   */
  @Column(name = "code_1", nullable = false)
  private String code1;

  /**
   * 关系码值1描述(第一个表内字段)
   */
  @Column(name = "code_1_desc")
  private String code1Desc;

  /**
   * 关系码值2(第二个表内字段)
   */
  @Column(name = "code_2", nullable = false)
  private String code2;

  /**
   * 关系码值2描述(第二个表内字段)
   */
  @Column(name = "code_2_desc")
  private String code2Desc;

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
