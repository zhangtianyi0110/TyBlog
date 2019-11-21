package com.ty.blog.service;

import com.ty.blog.base.BaseService;
import com.ty.blog.pojo.Article;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: UserService
 * @Description: 用户的业务类
 * @author zhangtainyi
 * @date 2019/9/17 11:16
 *
 */
@Service
public class ArticleService extends BaseService {

    /**
     * 获取作者所有的文章列表
     * @param author 作者名
     * @return
     */
    public List<Article> getArticlesByAuthor(String author) {
        List<Article> articles = articleDao.findAllByAuthor(author);
        return articles;
    }
}
