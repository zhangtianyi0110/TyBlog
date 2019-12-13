package com.ty.blog.util;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Random;

/**
 *  @ClassName: BlogUtil
 *  @Description: tyblog工具类
 *  @author zhangtianyi
 *  @Date 2019/12/13 9:24
 */
public class BlogUtil {

    /**
     * 获取文章首图URL，随机获取
     * @return
     */
    public static String getArticleImg(){
        String dir = "/static/article/img";
        File[] files = SpringContextUtils.getResource(dir).listFiles();
        HttpServletRequest request = HttpUtil.getRequest();
        StringBuffer url = new StringBuffer();
        url.append(request.getScheme())
                .append("://")
                .append(request.getServerName())
                .append(":")
                .append(request.getServerPort())
                .append(request.getContextPath());
        if(files.length <= 1){
            return url.append("/article/img/").append(files[0].getName()).toString();
        }
        int index = new Random().nextInt(files.length - 1);
        String filePath = files[index].getName();
        return url.append("/article/img/").append(filePath).toString();
    }

}
