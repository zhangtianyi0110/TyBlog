package com.ty.blog.controller;

import com.ty.blog.base.BaseController;
import com.ty.blog.pojo.Category;
import com.ty.blog.pojo.ResponseData;
import com.ty.blog.util.ResponseUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: CategoryController
 * @Description: 分类功能controller
 * @author zhangtainyi
 * @date 2019/11/20 16:22
 *
 */
@RestController
@RequestMapping("categories")
public class CategoryController extends BaseController {

    /**
     * 获取某个用户的所有分类
     * @param username
     * @return
     */
    @GetMapping("{username}")
    @RequiresAuthentication
    public ResponseData getCategories(@PathVariable("username") String username){
        List<Category> categories = categoryService.getCategories(username);
        return ResponseUtil.success(categories);
    }

//    @GetMapping("tyblog")
//    @RequiresAuthentication
//    public List<Category> getCategories(){
//
//        return categoryService.getCategories(null);
//    }

}
