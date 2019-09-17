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
 * @ClassName: Category
 * @Description: 分类表
 * @author zhangtainyi
 * @date 2019/9/17 16:56
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = TableNameConsts.TY_CATEGORY)
@Table(name = TableNameConsts.TY_CATEGORY)
public class Category implements Serializable {

    /**
     * 主键自增
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * 分类名
     */
    @Column(name = "category_name",unique = true,nullable = false)
    private String categoryName;

    /**
     * 分类描述
     */
    @Column(name = "category_desc",nullable = false)
    private String categoryDesc;

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
