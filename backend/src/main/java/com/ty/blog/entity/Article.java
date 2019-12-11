package com.ty.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName: Article
 * @Description: 文章实体类
 * @author zhangtainyi
 * @date 2019/9/11 14:53
 *
 */
@ApiModel("文章实体对象")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = TableNameConsts.TY_ARTICLE)
@Table(name = TableNameConsts.TY_ARTICLE)
public class Article implements Serializable {

    /**
     * 主键自增,文章id
     */
    @ApiModelProperty(value = "主键自增,文章id", hidden = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long articleId;

    /**
     * 文章标题
     */
    @ApiModelProperty(value = "文章标题", required = true)
    @Column(nullable = false)
    private String title;

    /**
     * 文章摘要
     */
    @ApiModelProperty(value = "文章摘要", required = true)
    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String summary;

    /**
     * 文章内容md格式(TEXT类型)
     */
    @ApiModelProperty(value = "文章内容md格式(TEXT类型)")
    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String mdContent;

    /**
     * 文章内容html格式(TEXT类型)
     */
    @ApiModelProperty(value = "文章内容html格式(TEXT类型)")
    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String htmlContent;

    /**
     * 发布日期
     */
    @ApiModelProperty(value = "发布日期")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date publishDate;

    /**
     * 更新文章日期
     */
    @ApiModelProperty(value = "更新文章日期")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date updateDate;

    /**
     * 文章url
     */
    @ApiModelProperty(value = "文章url")
    @Column(nullable = false)
    private String articleUrl;

    /**
     * 文章点赞数
     */
    @ApiModelProperty(value = "文章点赞数")
    @Builder.Default
    @Column(nullable = false)
    private Integer likes = 0;

    /**
     * 文章被点击次数
     */
    @ApiModelProperty(value = "文章被点击次数")
    @Builder.Default
    @Column(nullable = false)
    private Integer readCount = 0;

    /**
     * 文章首图URL
     */
    @ApiModelProperty(value = "文章首图URL")
    @Column(length = 10000)
    private String articleImg;

    /**
     * 文章是否可以评论，默认可以
     */
    @ApiModelProperty(value = "文章是否可以评论，默认可以")
    @Builder.Default
    @Column(nullable = false)
    private Boolean isComment = true;

    /**
     * 文章浏览密码，没有密码表示公开
     */
    @ApiModelProperty(value = "文章浏览密码，没有密码表示公开")
    @Column
    private String readPassword;

    /**
     * 文章是否可以浏览,默认可以
     */
    @ApiModelProperty(value = "文章是否可以浏览,默认可以")
    @Builder.Default
    @Column(nullable = false)
    private Boolean isRead = true;

    /**
     * 文章状态,0草稿1已发布
     */
    @ApiModelProperty(value = "文章状态,0草稿1已发布")
    @Column(nullable = false)
    private Integer state;

    /**
     * 作者,多对一，设置外键
     */
    @ApiModelProperty(value = "作者,多对一，设置外键", required = true)
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "author_id", referencedColumnName = "user_id")
    private User author;

    /**
     * 原作者
     */
    @ApiModelProperty(value = "原作者,多对一，设置外键")
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "original_author_id")
    private User originalAuthor;

    /**
     * 文章标签,多对多关系
     */
    @ApiModelProperty(value = "文章标签外键，多对多关系")
    @Builder.Default
    @JsonIgnore
    @ManyToMany(targetEntity = Tag.class)
    @JoinTable(name = TableNameConsts.TY_ARTICLE_TAG,
            joinColumns = {@JoinColumn(name = "article_id", referencedColumnName = "article_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "tag_id")})
    private Set<Tag> tags = new HashSet<>();

    /**
     * 文章分类,多对一
     */
    @ApiModelProperty(value = "文章分类")
    @JsonFormat
    @ManyToOne(targetEntity = Category.class)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    /**
     * 文章评论关系，一对多，评论维护关系
     */
    @ApiModelProperty(value = "文章评论外键，一对多")
    @Builder.Default
    @JsonIgnore
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();


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
