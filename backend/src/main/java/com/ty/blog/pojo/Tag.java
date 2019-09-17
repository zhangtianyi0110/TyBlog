package com.ty.blog.pojo;

import com.ty.blog.constant.TableNameConsts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName: Tag
 * @Description: 标签表
 * @author zhangtainyi
 * @date 2019/9/17 10:24
 *
 */
@Data
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
    private Long id;

    /**
     * 标签名
     */
    @Column(name = "tag_name",unique = true,nullable = false)
    private String tagName;

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
