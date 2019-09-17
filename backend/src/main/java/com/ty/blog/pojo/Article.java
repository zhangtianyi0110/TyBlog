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
 * @ClassName: Article
 * @Description: 文章实体类
 * @author zhangtainyi
 * @date 2019/9/11 14:53
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = TableNameConsts.TY_ARTICLE)
@Table(name = TableNameConsts.TY_ARTICLE)
public class Article implements Serializable {

    /**
     * 主键自增
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * 文章id
     */
    @Column(name = "article_id", unique = true, nullable = false)
    private Long articleId;

    /**
     * 作者
     */
    @Column(name = "author", nullable = false)
    private String author;

    /**
     * 原作者
     */
    @Column(name = "original_author", nullable = false)
    private String originalAuthor;

    /**
     * 文章标题
     */
    @Column(name = "article_title", nullable = false)
    private String articleTitle;

    /**
     * 文章摘要
     */
    @Lob
    @Column(name = "article_summary", nullable = false, columnDefinition = "TEXT")
    private String articleSummary;

    /**
     * 文章内容(TEXT类型)
     */
    @Lob
    @Column(name = "article_content", nullable = false, columnDefinition = "TEXT")
    private String articleContent;

    /**
     * 文章标签
     */
    @Column(name = "article_tags", nullable = false)
    private String articleTags;

    /**
     * 文章类型
     */
    @Column(name = "article_type", nullable = false)
    private String articleType;

    /**
     * 文章分类
     */
    @Column(name = "article_categories", nullable = false)
    private String articleCategories;

    /**
     * 发布日期
     */
    @Column(name = "publish_date", nullable = false)
    private String publishDate;

    /**
     * 更新文章日期
     */
    @Column(name = "update_date", nullable = false)
    private String updateDate;

    /**
     * 文章url
     */
    @Column(name = "article_url", nullable = false)
    private String articleUrl;

    /**
     * 文章点赞数
     */
    @Column(name = "likes", nullable = false)
    private Long likes;

    /**
     * 文章被点击次数
     */
    @Column(name = "read_count", nullable = false)
    private Long readCount;

    /**
     * 文章首图URL
     */
    @Column(name = "article_img")
    private String articleImg;

    /**
     * 文章是否可以评论，0不能1可以
     */
    @Column(name = "is_comment", nullable = false, length = 1)
    private String isComment;

    /**
     * 文章浏览密码，没有密码表示公开
     */
    @Column(name = "read_password")
    private String readPassword;

    /**
     * 文章是否可以浏览 0不可以，1表示公开
     */
    @Column(name = "is_read", nullable = false, length = 1)
    private String isRead;

    /**
     * 文章状态,0草稿1已发布
     */
    @Column(name = "article_state", nullable = false, length = 1)
    private String articleState;


    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time",nullable = false)
    private Date createTime;

    /**
     * 最后修改时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_time",nullable = false)
    private Date modifyTime;

}
