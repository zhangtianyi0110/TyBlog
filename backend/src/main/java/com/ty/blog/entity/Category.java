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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName: Category
 * @Description: 分类表
 * @author zhangtainyi
 * @date 2019/9/17 16:56
 *
 */
@ApiModel("分类实体对象")
@Getter
@Setter
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
    @Column(name = "category_id")
    private Long categoryId;

    /**
     * 分类名
     */
    @Column(name = "category_name", unique = true, nullable = false)
    private String categoryName;

    /**
     * 分类描述
     */
    @Lob
    @Column(name = "category_desc", columnDefinition = "TEXT")
    private String categoryDesc;

    /**
     * 分类与文章关系，一对多关系，文章维护关系
     */
    @ApiModelProperty(value = "分类与文章关系，一对多关系，文章维护关系")
    @Builder.Default
    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<Article> articles = new HashSet<>();

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
