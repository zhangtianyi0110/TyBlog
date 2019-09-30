package com.ty.blog.util;

import com.ty.blog.constant.ResponseCodeConsts;
import com.ty.blog.pojo.ResponseData;

/**
 * @ClassName: ResponseUtil
 * @Description: 通用返回工具类
 * @author zhangtainyi
 * @date 2019/9/17 16:45
 *
 */
public class ResponseUtil {


    /**
     * 成功，无数据返回
     * @return
     */
    public static ResponseData success(){

        return new ResponseData(ResponseCodeConsts.SUCCESS,"响应成功",null);
    }

    /**
     * 成功，自定义返回信息
     * @param msg 自定义返回信息
     * @return
     */
    public static ResponseData success(String msg){
        return new ResponseData(ResponseCodeConsts.SUCCESS,msg,null);
    }

    /**
     * 成功，自定义返回数据
     * @param data 返回数据
     * @return
     */
    public static ResponseData success(Object data){
        return new ResponseData(ResponseCodeConsts.SUCCESS,"响应成功",data);
    }

    /**
     * 成功，自定义返回信息和数据
     * @param msg 返回信息
     * @param data 返回数据
     * @return
     */
    public static ResponseData success(String msg, Object data){
        return new ResponseData(ResponseCodeConsts.SUCCESS,msg,data);
    }

    /**
     * 成功，自定义返回对象
     * @param responseData
     * @return
     */
    public static ResponseData success(ResponseData responseData){
        return responseData;
    }

    /**
     * 失败，自定义返回码和信息
     * @param code 返回码
     * @param msg 返回信息
     * @return
     */
    public static ResponseData failure(int code, String msg){
        return new ResponseData(code,msg,null);
    }

}
