package com.ty.blog;

import com.hankcs.hanlp.HanLP;
import com.ty.blog.constant.TableNameConsts;
import com.ty.blog.dao.ArticleDao;
import com.ty.blog.dao.CategoryDao;
import com.ty.blog.dao.CommentDao;
import com.ty.blog.dao.LinkDao;
import com.ty.blog.dao.PermDao;
import com.ty.blog.dao.RelationDao;
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
import com.ty.blog.redis.RedisUtil;
import com.ty.blog.service.UserService;
import com.ty.blog.shiro.jwt.JwtRedisCache;
import com.ty.blog.util.BlogUtil;
import com.ty.blog.util.MaxIdUtil;
import com.ty.blog.util.Md5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {

    @Autowired
    protected UserService userService;
    @Autowired
    protected UserDao userDao;
    @Autowired
    protected RoleDao roleDao;
    @Autowired
    protected PermDao permDao;
    @Autowired
    protected RelationDao relationDao;
    @Autowired
    protected ArticleDao articleDao;
    @Autowired
    protected CategoryDao categoryDao;
    @Autowired
    protected TagDao tagDao;
    @Autowired
    protected CommentDao commentDao;
    @Autowired
    protected LinkDao linkDao;

    @Autowired
    JwtRedisCache jwtRedisCache;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    MaxIdUtil maxIdUtil;

    static User user;
    static Role role;
    static Perm perm;
    static Perm extraPerm;
    static Category category;
    static Article article;
    static Tag tag;
    static Comment comment;
    static Link link;

    static {
        //用户
        user = User.builder()
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
        role = Role.builder()
                .createTime(new Date())
                .modifyTime(new Date())
                .roleName("blogger")
                .roleDesc("博主").build();
        //角色权限
        perm = Perm.builder()
                .createTime(new Date())
                .modifyTime(new Date())
                .permName("article:*")
                .permDesc("文章类增删改查").build();
        //额外权限
        extraPerm = Perm.builder()
                .createTime(new Date())
                .modifyTime(new Date())
                .permName("user:get")
                .permDesc("用户类获取信息").build();
        //维护关系
        user.getRoles().add(role);
        user.getPerms().add(extraPerm);
        role.getUsers().add(user);
//        role.getPerms().add(perm);
//        perm.getRoles().add(role);
//        extraPerm.getUsers().add(user);

        //分类
        category = Category.builder()
                .categoryDesc("java是编程语言")
                .categoryName("java")
                .createTime(new Date())
                .modifyTime(new Date()).build();

        //标签
        tag = Tag.builder()
                .tagName("编程语言")
                .tagDesc("java是最流行的")
                .createTime(new Date())
                .modifyTime(new Date())
                .build();

        //评论
        comment = Comment.builder()
                .commentLevel(1)
                .user(user)
                .createTime(new Date())
                .modifyTime(new Date())
                .build();

        //文章
        article =  Article.builder()
                .articleImg("https://cn.bing.com/images/search?view=detailV2&ccid=o0Ov0qoG&id=FF52E0729CE40E754D08E887B5E5CF20E4894827&thid=OIP.o0Ov0qoGyGtwYpmSIRaO3QAAAA&mediaurl=https%3a%2f%2fst3.depositphotos.com%2f1762148%2f18651%2fi%2f450%2fdepositphotos_186510766-stock-photo-tengboche-monastery-biggest-monastery-way.jpg&exph=300&expw=450&q=tengboche%e4%bf%ae%e9%81%93%e9%99%a2&simid=607999216156739789&selectedIndex=0")
//                .articleUrl("/article/1")
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
        article.setAuthor(user);
        article.setOriginalAuthor(user);
        article.setCategory(category);
        article.getTags().add(tag);
        article.getComments().add(comment);

//        user.getArticles().add(article);
        user.getComments().add(comment);

//        category.getArticles().add(article);
//
//        tag.getArticles().add(article);
//
//        comment.setArticle(article);
//        comment.setUser(user);

        link = Link.builder()
                .isTop(true)
                .linkAddress("www.baidu.com")
                .linkDesc("百度一下你就知道")
                .linkTitle("百度")
                .createTime(new Date())
                .modifyTime(new Date())
                .build();

    }

    @Test
    public void contextLoads() {
        String document = "算法可大致分为基本算法、数据结构的算法、数论算法、计算几何的算法、图的算法、动态规划以及数值分析、加密算法、排序算法、检索算法、随机化算法、并行算法、厄米变形模型、随机森林算法。\n" +
                "算法可以宽泛的分为三类，\n" +
                "一，有限的确定性算法，这类算法在有限的一段时间内终止。他们可能要花很长时间来执行指定的任务，但仍将在一定的时间内终止。这类算法得出的结果常取决于输入值。\n" +
                "二，有限的非确定算法，这类算法在有限的时间内终止。然而，对于一个（或一些）给定的数值，算法的结果并不是唯一的或确定的。\n" +
                "三，无限的算法，是那些由于没有定义终止定义条件，或定义的条件无法由输入的数据满足而不终止运行的算法。通常，无限算法的产生是由于未能确定的定义终止条件。";
        List<String> sentenceList = HanLP.extractSummary(document, 3);
        System.out.println(sentenceList);
    }

    @Test
    public void test2() throws IOException {
//        File file = new File("/static/article/img");
//        System.out.println(file.exists());
//        Resource resource = new ClassPathResource("static/article/img/default.jpg");
//        File sourceFile =  resource.getFile();

        System.out.println(BlogUtil.getArticleImg());

    }

    public static void main(String[] args) {
        String s = "<p>ssss</p><p>ssss</p>";
        System.out.println(s.replaceAll("</?[^>]+>",""));
    }

    /**
     * 添加用户角色权限数据
     */
    @Test
    @Transactional
    @Rollback(false)
    public void addTestInfo(){

        userDao.save(user);
        roleDao.save(role);
        permDao.save(perm);
        permDao.save(extraPerm);

        articleDao.save(article);
        categoryDao.save(category);
        tagDao.save(tag);
        commentDao.save(comment);
        linkDao.save(link);

    }


    @Test
    public void getPermByUsername(){
        System.out.println(maxIdUtil.getMaxId(TableNameConsts.TY_RELATION));
    }

}
