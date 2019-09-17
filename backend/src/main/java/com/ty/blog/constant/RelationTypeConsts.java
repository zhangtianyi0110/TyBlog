package com.ty.blog.constant;

/**
 * @ClassName: RelationTypeConsts
 * @Description: 关系表中关系类型常量
 * @author zhangtainyi
 * @date 2019/9/17 14:58
 *
 */
public class RelationTypeConsts {

    /**
     * 下划线
     */
    public static final String UNDER_LINE = "_";

    /**
     * 用户角色关系
     */
    public static final String USER_ROLE = TableNameConsts.TY_USER + UNDER_LINE + TableNameConsts.TY_ROLE;

    /**
     * 角色权限关系
     */
    public static final String ROLE_PERM = TableNameConsts.TY_ROLE + UNDER_LINE + TableNameConsts.TY_PERM;

    /**
     * 用户额外权限关系
     */
    public static final String USER_PERM = TableNameConsts.TY_USER + UNDER_LINE + TableNameConsts.TY_PERM;

    /**
     * 标签文章关系
     */
    public static final String TAG_ARTICLE = TableNameConsts.TY_TAG + UNDER_LINE + TableNameConsts.TY_ARTICLE;

    /**
     * 分类文章关系
     */
    public static final String CATEGORY_ARTICLE = TableNameConsts.TY_CATEGORY + UNDER_LINE + TableNameConsts.TY_ARTICLE;

    /**
     * 文章评论关系
     */
    public static final String ARTICLE_COMMENT = TableNameConsts.TY_ARTICLE + UNDER_LINE + TableNameConsts.TY_COMMENT;

}
