package com.ty.blog.constant;
/**
 *  @ClassName: ResponseCodeConsts
 *  @Description: 自定义响应返回码
 *  @author: zhangtianyi
 *  @Date: 2019-09-26 22:49
 *
 */
public class ResponseCodeConsts {
    /**
     * 成功返回200
     */
    public static final int SUCCESS = 20000;
    /**
     * 非法token返回401
     */
    public static final int ILLEGAL_TOKEN = 50008;
    /**
     * 非常用设备返回402
     */
    public static final int ILLEGAL_CLIENT = 50012;
    /**
     * token过期返回403
     */
    public static final int EXPIRED_TOKEN = 50014;
}
