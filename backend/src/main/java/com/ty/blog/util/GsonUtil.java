package com.ty.blog.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @ClassName: GsonUtil
 * @Description: Gson工具类
 * @author zhangtainyi
 * @date 2019/11/20 16:44
 *
 */
public class GsonUtil {
    private static GsonBuilder gsonBuilder = new GsonBuilder();

    public static Gson formatDate(String pattern){
        return gsonBuilder.setDateFormat(pattern).create();
    }
}
