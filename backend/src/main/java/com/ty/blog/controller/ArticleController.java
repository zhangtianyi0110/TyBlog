package com.ty.blog.controller;

import com.ty.blog.base.BaseController;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Integer getCount(@PathVariable("name") String author){
        return articleService.getArticlesByAuthor(author).size();
    }
}
