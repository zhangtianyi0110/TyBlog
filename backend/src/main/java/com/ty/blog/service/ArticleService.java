package com.ty.blog.service;

import com.hankcs.hanlp.HanLP;
import com.ty.blog.base.BaseService;
import com.ty.blog.entity.Article;
import com.ty.blog.entity.Comment;
import com.ty.blog.entity.ResponseData;
import com.ty.blog.entity.Tag;
import com.ty.blog.entity.User;
import com.ty.blog.util.BlogUtil;
import com.ty.blog.util.JacksonUtil;
import com.ty.blog.util.ResponseUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @ClassName: UserService
 * @Description: 用户的业务类
 * @author zhangtainyi
 * @date 2019/9/17 11:16
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ArticleService extends BaseService {

    @Autowired
    private TagService tagService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 获取作者所有的文章列表,不分页
     * @param userId
     * @return
     */
    public List<Article> getAllByUserId(Long userId){
        User author = userDao.findById(userId).get();
        return articleDao.findAllByAuthor(author);
    }

    /**
     * 获取指定文章的所有评论
     * @param articleId
     * @return
     */
    public Set<Comment> getArticleComments(Long articleId){
        return articleDao.findById(articleId).get().getComments();
    }

    /**
     * 获取作者所有的文章列表,分页
     * @param userId 作者名
     * @param state 文章状态
     * @param pageable 分页对象
     * @return
     */
    public List<Article> getArticlesByAuthor(Long userId, Integer state, Pageable pageable) {

        User author = userDao.findById(userId).get();
        //如果sort不为null，就需要排序，默认倒序
        if(pageable.getSort() != Sort.unsorted()){
            pageable.getSort().descending();
        }
        List<Article> articles = articleDao.findAllByAuthor(author, pageable).getContent();
        if(state == 0){
            return articles.stream().filter(article -> article.getState() == 0).collect(Collectors.toList());
        }else if(state == 1){
            return articles.stream().filter(article -> article.getState() == 1).collect(Collectors.toList());
        }else if(state == 2){
            return articles.stream().filter(article -> article.getState() == 2).collect(Collectors.toList());
        }
        return articleDao.findAllByAuthor(author, pageable).getContent();
    }


    /**
     * 保存文章
     * @param map
     * @return
     */
    public boolean postArticle(Map<String, Object> map){
        Map<String, Object> articleMap = (Map<String, Object>) map.get("article");
        Article article = JacksonUtil.mapToBean(articleMap, Article.class);
        article.setSummary(HanLP.extractSummary(article.getHtmlContent().replaceAll("</?[^>]+>",""), 3).toString());
        article.setPublishDate(Calendar.getInstance().getTime());
        article.setUpdateDate(Calendar.getInstance().getTime());
        if (StringUtils.isNotEmpty((String) articleMap.get("articleImg"))) {
            article.setArticleImg((String) articleMap.get("articleImg"));
        } else {
            article.setArticleImg(BlogUtil.getArticleImg());
        }
        User author = userDao.findById(Long.valueOf(String.valueOf(map.get("author")))).get();
        article.setAuthor(author);
        if (map.containsKey("originalAuthor")) {
            article.setOriginalAuthor(userDao.findById(Long.valueOf(String.valueOf(map.get("originalAuthor")))).get());
        } else {
            article.setOriginalAuthor(author);
        }
        article.setTags(tagService.saveTags(new HashSet((List<String>) map.get("tags"))));
        article.setCategory(categoryDao.findById(Long.valueOf(String.valueOf(article.getCategory().getCategoryId()))).get());
        article.setCreateTime(Calendar.getInstance().getTime());
        article.setModifyTime(Calendar.getInstance().getTime());

        articleDao.saveAndFlush(article);
        return true;
    }

    /**
     * 编辑文章
     * @param map
     * @return
     */
    public boolean putArticle(Article article){
        return true;
    }

    /**
     * 上传图片
     * @param request
     * @param image
     * @return
     */
    public ResponseData uploadArticleImg(HttpServletRequest request, MultipartFile image){
        StringBuffer url = new StringBuffer();
        String filePath = "/blogimg/" + new SimpleDateFormat("yyyyMMdd").format(new Date());
        String imgFolderPath = request.getServletContext().getRealPath(filePath);
        File imgFolder = new File(imgFolderPath);
        if (!imgFolder.exists()) {
            imgFolder.mkdirs();
        }
        url.append(request.getScheme())
                .append("://")
                .append(request.getServerName())
                .append(":")
                .append(request.getServerPort())
                .append(request.getContextPath())
                .append(filePath);
        String imgName = UUID.randomUUID() + "_" + image.getOriginalFilename().replaceAll(" ", "");
        try {
            IOUtils.write(image.getBytes(), new FileOutputStream(new File(imgFolder, imgName)));
            url.append("/").append(imgName);
            return ResponseUtil.success("响应成功", url.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseUtil.failure(500, "上传失败");
    }


    /**
     * 通过标题查询指定文章
     * @param title
     * @return
     */
    public Article getArticlesByTitle(String title){
        return articleDao.findByTitle(title);
    }


    /**
     * 通过文章id获取文章标签
     * @param articleId
     * @return
     */
    public Set<Tag> getTagsByArticleId(Long articleId){
        return articleDao.findById(articleId).get().getTags();
    }
}
