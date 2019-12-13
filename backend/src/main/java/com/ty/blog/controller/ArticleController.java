package com.ty.blog.controller;

import com.ty.blog.base.BaseController;
import com.ty.blog.entity.ResponseData;
import com.ty.blog.util.BlogUtil;
import com.ty.blog.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @ClassName: ArticleController
 * @Description: 文章功能controller
 * @author zhangtainyi
 * @date 2019/11/20 16:22
 *
 */
@Api(tags = "文章处理相关接口", description = "提供文章相关的 Rest API")
@RestController
@RequestMapping("articles")
public class ArticleController extends BaseController {

    /**
     * 获取某个作者文章总数
     * @param author
     * @return
     */
    @ApiOperation("获取指定作者的文章总数量")
    @GetMapping("count/{name}")
    @RequiresAuthentication
    public ResponseData getCount(@PathVariable("name") String author){
        return ResponseUtil.success(articleService.getArticlesByAuthor(author).size());
    }

    @ApiOperation("获取指定作者的文章总数量")
    @GetMapping("{name}")
    @RequiresAuthentication
    public ResponseData getArticles(@PathVariable("name") String author){
        return ResponseUtil.success(articleService.getArticlesByAuthor(author).size());
    }

    /**
     * 随机获取指定文章配图
     * @return
     */
    @ApiOperation("随机获取指定文章配图")
    @GetMapping("img")
    @RequiresAuthentication
    public ResponseData getArticleImg(){
        return ResponseUtil.success("响应成功", BlogUtil.getArticleImg());
    }

    /**
     * 保存文章
     * @param map 文章要素
     * @return
     */
    @ApiOperation("发布文章/草稿")
    @PostMapping
    @RequiresAuthentication
    public ResponseData saveArticle(@RequestBody Map<String, Object> map){

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
     * 上传文章内图片
     * @param image
     * @return
     */
    @PostMapping(value = "/articleImg")
    public ResponseData uploadArticleImg(MultipartFile image) {
        return articleService.uploadArticleImg(request, image);
    }


}
