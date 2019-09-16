package com.ty.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

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
@Entity(name = "ty_article")
@Table(name = "ty_article")
public class Article implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;//主键

    @Column(name = "article_id", unique = true, nullable = false)
    private Long articleId;//文章id

    @Column(name = "author", nullable = false)
    private String author;//作者

    @Column(name = "original_author", nullable = false)
    private String originalAuthor;//原作者

    @Column(name = "article_title", nullable = false)
    private String articleTitle;//文章标题

    @Column(name = "article_summary", nullable = false)
    private String articleSummary;//文章摘要

    @Lob
    @Column(name = "article_content", columnDefinition = "TEXT")
    private String articleContent;//文章内容(TEXT类型)

    @Column(name = "article_tags", nullable = false)
    private String articleTags;//文章标签

    @Column(name = "article_type", nullable = false)
    private String articleType;//文章类型

    @Column(name = "article_categories", nullable = false)
    private String articleCategories;//文章分类

    @Column(name = "publish_date", nullable = false)
    private String publishDate;//发布日期

    @Column(name = "update_date", nullable = false)
    private String updateDate;//更新文章日期

    @Column(name = "article_url", nullable = false)
    private String articleUrl;//文章url

    @Column(name = "likes", nullable = false)
    private Long likes;//文章点赞数

    @Column(name = "read_count", nullable = false)
    private Long readCount;//文章被点击次数

    @Column(name = "last_login_time", nullable = false)
    private String lastLoginTime;//最后一次登录时间

    @Column(name = "last_update_time", nullable = false)
    private String lastUpdateTime;//最后修改时间

    @Column(name = "create_time", nullable = false)
    private String createTime;//创建时间

}
