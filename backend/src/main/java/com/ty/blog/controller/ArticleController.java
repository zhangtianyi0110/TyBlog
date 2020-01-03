package com.ty.blog.controller;

import com.ty.blog.base.BaseController;
import com.ty.blog.entity.Article;
import com.ty.blog.entity.ResponseData;
import com.ty.blog.util.BlogUtil;
import com.ty.blog.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
     * @param userId
     * @return
     */
    @ApiOperation("获取指定作者的文章总数量")
    @GetMapping("count/users/{userId}")
    public ResponseData getCount(@PathVariable("userId") Long userId){
        return ResponseUtil.success(articleService.getAllByUserId(userId).size());
    }

    /**
     * 获取指定作者的所有文章
     * @param userId
     * @return
     */
    @ApiOperation("获取指定作者的所有文章")
    @GetMapping("users/{userId}")
    public ResponseData getArticles(@PathVariable("userId") Long userId){
        return ResponseUtil.success(articleService.getAllByUserId(userId));
    }

    /**
     * 获取指定作者指定状态的所有已发布文章，分页
     * @param userId 用户id
     * @param state 文章状态
     * @param pageable  分页对象
     * @return
     */
    @ApiOperation("获取指定作者指定状态的所有文章，分页")
    @GetMapping("users/{userId}/states/{state}")
    public ResponseData getArticles(@PathVariable("userId") Long userId,
                                    @PathVariable("state") Integer state,
                                    Pageable pageable){
        return ResponseUtil.success(articleService.getArticlesByAuthor(userId, state, pageable));
    }

    /**
     * 获取文章所有评论
     * @param articleId
     * @return
     */
    @ApiOperation("获取文章所有评论")
    @GetMapping("/{articleId}/comments")
    public ResponseData getArticleComments(@PathVariable("articleId") Long articleId){
        return ResponseUtil.success(articleService.getArticleComments(articleId));
    }


    /**
     * 通过标题获取指定状态文章
     * @param title
     * @return
     */
    @ApiOperation("通过标题获取指定状态文章")
    @GetMapping("/titles/{title}")
    public ResponseData getArticlesByTitle(@PathVariable("title") String title){
        Article article = articleService.getArticlesByTitle(title);
        return ResponseUtil.success(article);
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
    public ResponseData postArticle(@RequestBody Map<String, Object> map){

        boolean flag = articleService.postArticle(map);
        if(flag){
            if("1".equals(map.get("articleState"))){
                return ResponseUtil.success("文章发布成功");
            }
            return ResponseUtil.success("草稿保存成功");
        }
        return ResponseUtil.failure(500, "发布失败");
    }


    /**
     * 编辑文章
     * @param map
     * @return
     */
    @ApiOperation("编辑文章")
    @PutMapping
    @RequiresAuthentication
    public ResponseData putArticleImg(@RequestBody Map<String, Object> map) {
        boolean flag = articleService.postArticle(map);
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
    @ApiOperation("上传文章内图片")
    @PostMapping(value = "/articleImg")
    @RequiresAuthentication
    public ResponseData uploadArticleImg(MultipartFile image) {
        return articleService.uploadArticleImg(request, image);
    }


    /**
     * 通过文章id获取文章标签
     * @param articleId
     * @return
     */
    @ApiOperation("通过文章id获取文章标签")
    @GetMapping(value = "{articleId}/tags")
    @RequiresAuthentication
    public ResponseData getTagsByArticleId(@PathVariable("articleId") Long articleId) {
        return ResponseUtil.success(articleService.getTagsByArticleId(articleId));
    }


    /**
     * 通过文章id改变文章state
     * @param articleId
     * @param state 2回收站
     * @return
     */
    @ApiOperation("通过文章id改变文章state")
    @PutMapping(value = "{articleId}/states/{state}")
    @RequiresAuthentication
    public ResponseData putStateByArticleId(@PathVariable("articleId") Long articleId,
                                           @PathVariable("state") Integer state) {
        return ResponseUtil.success(articleService.putStateByArticleId(articleId, state));
    }




}
