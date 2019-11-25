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
 * @ClassName: Link
 * @Description: 友情链接表
 * @author zhangtainyi
 * @date 2019/9/17 10:24
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = TableNameConsts.TY_LINK)
@Table(name = TableNameConsts.TY_LINK)
public class Link implements Serializable {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    /**
     * 链接标题
     */
    @Column(name = "link_title", unique = true, nullable = false)
    private String linkTitle;

    /**
     * 链接地址
     */
    @Column(name = "link_address", unique = true, nullable = false)
    private String linkAddress;

    /**
     * 链接描述
     */
    @Lob
    @Column(name = "link_desc", columnDefinition = "TEXT")
    private String linkDesc;

    /**
     * 链接序号，大的排前面，默认5
     */
    @Column(name = "link_order", nullable = false)
    private Integer linkOrder = 5;

    /**
     * 是否置顶，多个置顶取链接序号大的
     * 0否1是
     */
    @Column(name = "is_top", nullable = false)
    private String isTop;

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
