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
import com.ty.blog.entity.Perm;
import com.ty.blog.entity.Role;
import com.ty.blog.entity.User;
import com.ty.blog.redis.RedisUtil;
import com.ty.blog.service.UserService;
import com.ty.blog.shiro.jwt.JwtRedisCache;
import com.ty.blog.util.MaxIdUtil;
import com.ty.blog.util.Md5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
        role.getPerms().add(perm);
        perm.getRoles().add(role);
        extraPerm.getUsers().add(user);

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

    /**
     * 添加用户角色权限数据
     */
    @Test
    @Transactional
    @Rollback(false)
    public void addUserRolePermInfo(){
        userDao.save(user);
        roleDao.save(role);
        permDao.save(perm);
        permDao.save(extraPerm);

    }

    @Test
    public void test(){
        Optional<User> user = userDao.findById(1L);
        System.out.println(user.get().getName());
    }

    /**
     * 添加测试信息
     */
//    @Test
//    public void addTestInfo(){
//        User user = User.builder()
//                .username("tyblog")
//                .password(Md5Util.encrypt("123456", "tyblog"))
//                .gender("男")
//                .name("张小生")
//                .nickname("tyblog")
//                .birthday(new Date())
//                .email("123@qq.com")
//                .profile("hello world")
//                .avatarUrl("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1719911752,2197523375&fm=26&gp=0.jpg")
//                .githubId("zhangtianyi0110")
//                .githubUrl("https://github.com/zhangtianyi0110")
//                .lastLoginTime(new Date())
//                .createTime(new Date())
//                .modifyTime(new Date()).build();
//        userDao.saveAndFlush(user);
//        //角色
//        Role role = Role.builder()
//                .createTime(new Date())
//                .modifyTime(new Date())
//                .roleName("blogger").build();
//        roleDao.saveAndFlush(role);
//        //权限
//        Perm perm = Perm.builder()
//                .createTime(new Date())
//                .modifyTime(new Date())
//                .perm("article:*").build();
//        permDao.saveAndFlush(perm);
//        //文章
//        Article article = Article.builder()
//                .id(maxIdUtil.getMaxId(TableNameConsts.TY_ARTICLE))
//                .author(user.getUsername())
//                .originalAuthor("")
//                .title("Hello World")
//                .summary("Hello World")
//                .mdContent("你好世界")
//                .htmlContent("n你好世界")
//                .tags("hello1")
//                .articleType("hello2")
//                .articleCategories("java")
//                .publishDate(new Date())
//                .updateDate(new Date())
//                .articleUrl("")
//                .likes(11)
//                .readCount(1111)
//                .articleImg("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1719911752,2197523375&fm=26&gp=0.jpg")
//                .isComment("0")
//                .readPassword("")
//                .isRead("1")
//                .articleState("1")
//                .createTime(new Date())
//                .modifyTime(new Date()).build();
//        articleDao.saveAndFlush(article);
//        //分类
//        List<String> categoryNames = Arrays.asList(new String[]{"category1", "category2", "category3"});
//        categoryNames.forEach(categoryName -> {
//            Category category = Category.builder()
//                    .categoryName(categoryName)
//                    .categoryDesc("编程")
//                    .createTime(new Date())
//                    .modifyTime(new Date()).build();
//            categoryDao.saveAndFlush(category);
//            Relation relation = Relation.builder().createTime(new Date()).modifyTime(new Date())
//                    .relationType(RelationTypeConsts.ARTICLE_CATEGORY)
//                    .code1(categoryName).code2(article.getId() + "").build();
//            relationDao.saveAndFlush(relation);
//        });
//
//        //标签
//        List<String> tagNames = Arrays.asList(new String[]{"tag1", "tag2", "tag2"});
//        tagNames.forEach(tagName -> {
//            Tag tag = Tag.builder()
//                    .tagName(tagName)
//                    .createTime(new Date())
//                    .modifyTime(new Date()).build();
//            tagDao.saveAndFlush(tag);
//            Relation relation = Relation.builder().createTime(new Date()).modifyTime(new Date())
//                    .relationType(RelationTypeConsts.ARTICLE_CATEGORY)
//                    .code1(tagName).code2(article.getId() + "").build();
//            relationDao.saveAndFlush(relation);
//        });
//
//        //评论
//        Comment comment = Comment.builder()
//                .commentNickname("tyblog")
//                .commentNicknameUrl("")
//                .commentNicknameUrl("")
//                .commentParentId(1)
//                .createTime(new Date())
//                .modifyTime(new Date()).build();
//        commentDao.saveAndFlush(comment);
//        //友情链接
//        Link link = Link.builder()
//                .linkTitle("红杏出墙")
//                .linkAddress("www.baidu.com")
//                .linkDesc("红杏出墙desc")
//                .linkOrder(5)
//                .isTop("1")
//                .createTime(new Date())
//                .modifyTime(new Date()).build();
//        linkDao.saveAndFlush(link);
//
//
//        relationDao.saveAndFlush(Relation.builder().createTime(new Date()).modifyTime(new Date())
//                .relationType(RelationTypeConsts.USER_ROLE).code1("tyblog").code2("blogger").build());
//        relationDao.saveAndFlush(Relation.builder().createTime(new Date()).modifyTime(new Date())
//                .relationType(RelationTypeConsts.ROLE_PERM).code1("blogger").code2("article:*").build());
//
//
//    }

    @Test
    public void getPermByUsername(){
        System.out.println(maxIdUtil.getMaxId(TableNameConsts.TY_RELATION));
    }

}
