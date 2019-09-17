package com.ty.blog.pojo;

import com.ty.blog.constant.TableNameConsts;
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
     * 文章是否可以评论，0不能1可以
     */
    @Column(name = "is_comment", nullable = false)
    private Long isComment;

    /**
     * 文章浏览密码
     */
    @Column(name = "read_password", nullable = false)
    private String readPassword;

    /**
     * 文章状态，是否私密,0私密1公开
     */
    @Column(name = "article_state", nullable = false)
    private Long articleState;

    /**
     * 最后一次登录时间
     */
    @Column(name = "last_login_time", nullable = false)
    private String lastLoginTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time",nullable = false,length = 30)
    private String createTime;

    /**
     * 最后修改时间
     */
    @Column(name = "modify_time",nullable = false,length = 30)
    private String modifyTime;

}
