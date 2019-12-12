package com.ty.blog.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * @ClassName: JacksonUtil
 * @Description: Jackson工具类
 * @author zhangtainyi
 * @date 2019/11/20 16:44
 *
 */
public class JacksonUtil {
    private static ObjectMapper jsonMapper = new JsonMapper();

    /**
     * 将bean转化为json，并且时间转化为指定格式
     * @param object
     * @param pattern
     * @return
     */
    public static String formatDate(Object object, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String json = null;
        try {
            json = jsonMapper.setDateFormat(simpleDateFormat).writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * map转bean
     * @param map
     * @param clazz
     * @return
     */
    public static <T> T mapToBean(Map<String, Object> map, Class<T> clazz){
        String json = null;
        T obj = null;
        try {
            json =  jsonMapper.writeValueAsString(map);
            obj = jsonMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
