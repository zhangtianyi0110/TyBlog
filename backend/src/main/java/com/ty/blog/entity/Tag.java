package com.ty.blog.entity;

import com.ty.blog.constant.TableNameConsts;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName: Tag
 * @Description: 标签表
 * @author zhangtainyi
 * @date 2019/9/17 10:24
 *
 */
@ApiModel("标签实体对象")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = TableNameConsts.TY_TAG)
@Table(name = TableNameConsts.TY_TAG)
public class Tag implements Serializable {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long tagId;

    /**
     * 标签名
     */
    @ApiModelProperty(value = "标签名")
    @Column(unique = true, nullable = false)
    private String tagName;

    /**
     * 标签描述
     */
    @ApiModelProperty(value = "标签名描述")
    @Lob
    @Column(name = "category_desc", columnDefinition = "TEXT")
    private String tagDesc;

    /**
     * 文章与标签关系，多对多，文章维护关系
     */
    @ApiModelProperty(value = "文章，多对多")
    @Builder.Default
    @ManyToMany(mappedBy = "tags")
    private Set<Article> articles = new HashSet<>();

    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false)
    private Date createTime;

    /**
     * 最后修改时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_time", nullable = false)
    private Date modifyTime;

}
