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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: Comment
 * @Description: 评论表
 * @author zhangtainyi
 * @date 2019/9/17 16:56
 *
 */
@ApiModel("评论实体对象")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = TableNameConsts.TY_COMMENT)
@Table(name = TableNameConsts.TY_COMMENT)
public class Comment implements Serializable {

    /**
     * 主键自增
     */
    @ApiModelProperty(value = "评论表主键", hidden = true)
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    /**
     * 评论人
     */
    @ApiModelProperty(value = "评论与用户表外键")
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    /**
     * 文章
     */
    @ApiModelProperty(value = "评论与文章外键")
    @ManyToOne(targetEntity = Article.class)
    @JoinColumn(name = "article_id", referencedColumnName = "article_id")
    private Article article;

    /**
     * 评论等级，一级二级
     */
    @ApiModelProperty(value = "评论等级，一级二级")
    @Column(nullable = false)
    private Integer commentLevel;

    /**
     * 父评论
     */
    @ApiModelProperty(value = "父评论")
    @OneToOne(targetEntity = Comment.class)
    @JoinColumn(name = "comment_parent_id", referencedColumnName = "comment_id")
    private Comment commentParent;


    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createTime;

    /**
     * 最后修改时间
     */
    @ApiModelProperty(value = "最后修改时间")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date modifyTime;

}
