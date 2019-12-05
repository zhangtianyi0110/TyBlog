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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: Link
 * @Description: 友情链接表
 * @author zhangtainyi
 * @date 2019/9/17 10:24
 *
 */
@ApiModel("友情链接实体对象")
@Getter
@Setter
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
    @Column(name = "link_id")
    private Long linkId;

    /**
     * 链接标题
     */
    @ApiModelProperty(value = "链接标题")
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
