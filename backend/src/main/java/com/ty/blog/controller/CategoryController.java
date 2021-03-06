package com.ty.blog.controller;

import com.ty.blog.base.BaseController;
import com.ty.blog.entity.Category;
import com.ty.blog.entity.ResponseData;
import com.ty.blog.util.ResponseUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

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
     * @param userId
     * @return
     */
    @ApiOperation("获取某个用户的所有分类")
    @GetMapping("users/{userId}")
    @RequiresAuthentication
    public ResponseData getCategories(@PathVariable("userId") Long userId){
        Set<Category> categories = categoryService.getCategories(userId);
        return ResponseUtil.success(categories);
    }

}
