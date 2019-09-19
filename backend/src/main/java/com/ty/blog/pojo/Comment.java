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
 * @ClassName: Comment
 * @Description: 评论表
 * @author zhangtainyi
 * @date 2019/9/17 16:56
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = TableNameConsts.TY_COMMENT)
@Table(name = TableNameConsts.TY_COMMENT)
public class Comment implements Serializable {

    /**
     * 主键自增
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * 评论人用户名
     */
    @Column(name = "comment_username", nullable = false)
    private Long commentUsername;

    /**
     * 评论人链接
     */
    @Column(name = "comment_username_url", nullable = false)
    private Long commentUsernameUrl;

    /**
     * 父评论id
     */
    @Column(name = "comment_parent_id", nullable = false)
    private Long commentParentId;

    /**
     * 父评论人链接
     */
    @Column(name = "comment_parent_username", nullable = false)
    private Long commentParentUsername;


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