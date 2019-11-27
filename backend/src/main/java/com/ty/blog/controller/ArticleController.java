package com.ty.blog.controller;

import com.ty.blog.base.BaseController;
import com.ty.blog.pojo.ResponseData;
import com.ty.blog.util.ResponseUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: ArticleController
 * @Description: 文章功能controller
 * @author zhangtainyi
 * @date 2019/11/20 16:22
 *
 */
@RestController
@RequestMapping("articles")
public class ArticleController extends BaseController {

    /**
     * 获取某个作者文章总数
     * @param author
     * @return
     */
    @GetMapping("count/{name}")
    @RequiresAuthentication
    public ResponseData getCount(@PathVariable("name") String author){
        return ResponseUtil.success(articleService.getArticlesByAuthor(author).size());
    }

    /**
     * 保存文章
     * @param map
     * @return
     */
    @PostMapping
    public ResponseData saveArticle(@RequestBody Map<String, String> mmp){

//        Map<String, Object> o = new Gson().fromJson(mmp, new TypeToken<Map<String, Object>>() {
//        }.getType());

        Map<String, Object> map = new HashMap<>();
        boolean flag = articleService.saveArticle(map);
        if(flag){
            if("1".equals(map.get("articleState"))){
                return ResponseUtil.success("文章发布成功");
            }
            return ResponseUtil.success("草稿保存成功");
        }
        return ResponseUtil.failure(500, "发布失败");
    }

    /**
     * 上传图片
     * @param req
     * @param image
     * @return
     */
    @PostMapping(value = "/uploadArticleImg")
    public ResponseData uploadArticleImg(HttpServletRequest req, MultipartFile image) {
        return articleService.uploadArticleImg(req, image);
    }

}
