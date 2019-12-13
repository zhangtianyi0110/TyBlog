package com.ty.blog;

import com.ty.blog.dao.ArticleDao;
import com.ty.blog.dao.CategoryDao;
import com.ty.blog.dao.CommentDao;
import com.ty.blog.dao.LinkDao;
import com.ty.blog.dao.PermDao;
import com.ty.blog.dao.RoleDao;
import com.ty.blog.dao.TagDao;
import com.ty.blog.dao.UserDao;
import com.ty.blog.entity.Article;
import com.ty.blog.entity.Category;
import com.ty.blog.entity.Comment;
import com.ty.blog.entity.Link;
import com.ty.blog.entity.Perm;
import com.ty.blog.entity.Role;
import com.ty.blog.entity.Tag;
import com.ty.blog.entity.User;
import com.ty.blog.shiro.jwt.JwtConfig;
import com.ty.blog.util.Md5Util;
import com.ty.blog.util.SpringContextUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextListener;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: BlogApplication
 * @Description: 启动类
 * @author zhangtainyi
 * @date 2019/9/17 16:48
 *
 */
@SpringBootApplication
@EnableConfigurationProperties({JwtConfig.class})
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
        //添加测试数据
        SpringContextUtils.getBean(BlogApplication.class).AddTestData();
    }

    /**
     * 监听用于静态获取request等
     * @return
     */
    @Bean
    public RequestContextListener requestContextListener(){
        return new RequestContextListener();
    }

    @Transactional(rollbackFor = Exception.class)
    public void AddTestData() {
        List<User> all = SpringContextUtils.getBean(UserDao.class).findAll();
        if(CollectionUtils.isEmpty(all)){
            //用户
            User user = User.builder()
                    .username("tyblog")
                    .password(Md5Util.encrypt("123456", "tyblog"))
                    .gender("男")
                    .name("张小生")
                    .nickname("tyblog")
                    .birthday(new Date())
                    .email("tyblog@qq.com")
                    .profile("hello world,我是tyblog。")
                    .avatarUrl("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1719911752,2197523375&fm=26&gp=0.jpg")
                    .githubId("zhangtianyi0110")
                    .githubUrl("https://github.com/zhangtianyi0110")
                    .isAdmin(true)
                    .lastLoginTime(new Date())
                    .createTime(new Date())
                    .modifyTime(new Date()).build();
            //角色
            Role role = Role.builder()
                    .createTime(new Date())
                    .modifyTime(new Date())
                    .roleName("blogger")
                    .roleDesc("博主").build();
            //角色权限
            Perm perm = Perm.builder()
                    .createTime(new Date())
                    .modifyTime(new Date())
                    .permName("article:*")
                    .permDesc("文章类增删改查").build();
            //额外权限
            Perm extraPerm = Perm.builder()
                    .createTime(new Date())
                    .modifyTime(new Date())
                    .permName("user:get")
                    .permDesc("用户类获取信息").build();

            //分类
            Category category = Category.builder()
                    .categoryDesc("java是编程语言")
                    .categoryName("java")
                    .createTime(new Date())
                    .modifyTime(new Date()).build();

            //标签
            Tag tag = Tag.builder()
                    .tagName("编程语言")
                    .tagDesc("java是最流行的")
                    .createTime(new Date())
                    .modifyTime(new Date())
                    .build();

            //评论
            Comment comment = Comment.builder()
                    .commentLevel(1)
                    .user(user)
                    .createTime(new Date())
                    .modifyTime(new Date())
                    .build();

            //文章
            Article article = Article.builder()
                    .articleImg("https://cn.bing.com/images/search?view=detailV2&ccid=o0Ov0qoG&id=FF52E0729CE40E754D08E887B5E5CF20E4894827&thid=OIP.o0Ov0qoGyGtwYpmSIRaO3QAAAA&mediaurl=https%3a%2f%2fst3.depositphotos.com%2f1762148%2f18651%2fi%2f450%2fdepositphotos_186510766-stock-photo-tengboche-monastery-biggest-monastery-way.jpg&exph=300&expw=450&q=tengboche%e4%bf%ae%e9%81%93%e9%99%a2&simid=607999216156739789&selectedIndex=0")
//                    .articleUrl("/article/1")
                    .title("hello world")
                    .summary("hello world")
                    .mdContent("hello word")
                    .htmlContent("hello word")
                    .isComment(true)
                    .isRead(true)
                    .likes(10)
                    .publishDate(new Date())
                    .readCount(10)
                    .state(1)
                    .createTime(new Date())
                    .updateDate(new Date())
                    .modifyTime(new Date())
                    .readPassword(null)
                    .build();

            //维护关系
            user.getRoles().add(role);
            user.getPerms().add(extraPerm);
    //        user.getArticles().add(article);
    //        user.getComments().add(comment);
            role.getUsers().add(user);
    //        role.getPerms().add(perm);
    //        perm.getRoles().add(role);
    //        extraPerm.getUsers().add(user);


            article.setAuthor(user);
            article.setOriginalAuthor(user);
            article.setCategory(category);
            article.getTags().add(tag);
    //        article.getComments().add(comment);

    //        category.getArticles().add(article);

    //        tag.getArticles().add(article);

            comment.setArticle(article);
            comment.setUser(user);

            Link link = Link.builder()
                    .isTop(true)
                    .linkAddress("www.baidu.com")
                    .linkDesc("百度一下你就知道")
                    .linkTitle("百度")
                    .createTime(new Date())
                    .modifyTime(new Date())
                    .build();


            SpringContextUtils.getBean(UserDao.class).save(user);
            SpringContextUtils.getBean(RoleDao.class).save(role);
            SpringContextUtils.getBean(PermDao.class).save(perm);
            SpringContextUtils.getBean(PermDao.class).save(extraPerm);

            SpringContextUtils.getBean(ArticleDao.class).save(article);
            SpringContextUtils.getBean(CategoryDao.class).save(category);
            SpringContextUtils.getBean(TagDao.class).save(tag);
            SpringContextUtils.getBean(CommentDao.class).save(comment);
            SpringContextUtils.getBean(LinkDao.class).save(link);
        }
    }

}
