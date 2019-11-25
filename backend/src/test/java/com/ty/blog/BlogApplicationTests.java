package com.ty.blog;

import com.ty.blog.constant.RelationTypeConsts;
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
import com.ty.blog.pojo.Article;
import com.ty.blog.pojo.Category;
import com.ty.blog.pojo.Comment;
import com.ty.blog.pojo.Link;
import com.ty.blog.pojo.Perm;
import com.ty.blog.pojo.Relation;
import com.ty.blog.pojo.Role;
import com.ty.blog.pojo.Tag;
import com.ty.blog.pojo.User;
import com.ty.blog.service.UserService;
import com.ty.blog.shiro.jwt.JwtRedisCache;
import com.ty.blog.util.MaxIdUtil;
import com.ty.blog.util.Md5Util;
import com.ty.blog.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.persistence.Entity;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {

    @Resource
    protected UserService userService;
    @Resource
    protected UserDao userDao;
    @Resource
    protected RoleDao roleDao;
    @Resource
    protected PermDao permDao;
    @Resource
    protected RelationDao relationDao;
    @Resource
    protected ArticleDao articleDao;
    @Resource
    protected CategoryDao categoryDao;
    @Resource
    protected TagDao tagDao;
    @Resource
    protected CommentDao commentDao;
    @Resource
    protected LinkDao linkDao;

    @Autowired
    JwtRedisCache jwtRedisCache;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    MaxIdUtil maxIdUtil;

    @Test
    public void contextLoads() {
        Annotation annotation = null;
        try {
            annotation = new User().getClass().getAnnotation(Entity.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String name = ((Entity) annotation).name();
        System.out.println(name);
        Field[] declaredFields = annotation.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {

            System.out.println(declaredField.getName());
        }

        System.out.println(annotation.getClass().getDeclaredFields());
    }

    /**
     * 添加测试信息
     */
    @Test
    public void addTestInfo(){
        User user = User.builder()
                .username("tyblog")
                .password(Md5Util.encrypt("123456", "tyblog"))
                .gender("男")
                .name("张小生")
                .nickname("tyblog")
                .birthday(new Date())
                .email("123@qq.com")
                .profile("hello world")
                .avatarUrl("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1719911752,2197523375&fm=26&gp=0.jpg")
                .githubId("zhangtianyi0110")
                .githubUrl("https://github.com/zhangtianyi0110")
                .lastLoginTime(new Date())
                .createTime(new Date())
                .modifyTime(new Date()).build();
        userDao.saveAndFlush(user);
        //角色
        Role role = Role.builder()
                .createTime(new Date())
                .modifyTime(new Date())
                .roleName("blogger").build();
        roleDao.saveAndFlush(role);
        //权限
        Perm perm = Perm.builder()
                .createTime(new Date())
                .modifyTime(new Date())
                .perm("article:*").build();
        permDao.saveAndFlush(perm);
        //文章
        Article article = Article.builder()
                .id(maxIdUtil.getMaxId(TableNameConsts.TY_ARTICLE))
                .author(user.getUsername())
                .originalAuthor("")
                .articleTitle("Hello World")
                .articleSummary("Hello World")
                .articleContent("你好世界")
                .articleTags("hello1")
                .articleType("hello2")
                .articleCategories("java")
                .publishDate(new Date())
                .updateDate(new Date())
                .articleUrl("")
                .likes(11)
                .readCount(1111)
                .articleImg("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1719911752,2197523375&fm=26&gp=0.jpg")
                .isComment("0")
                .readPassword("")
                .isRead("1")
                .articleState("1")
                .createTime(new Date())
                .modifyTime(new Date()).build();
        articleDao.saveAndFlush(article);
        //分类
        List<String> categoryNames = Arrays.asList(new String[]{"category1", "category2", "category3"});
        categoryNames.forEach(categoryName -> {
            Category category = Category.builder()
                    .categoryName(categoryName)
                    .categoryDesc("编程")
                    .createTime(new Date())
                    .modifyTime(new Date()).build();
            categoryDao.saveAndFlush(category);
            Relation relation = Relation.builder().createTime(new Date()).modifyTime(new Date())
                    .relationType(RelationTypeConsts.ARTICLE_CATEGORY)
                    .code1(categoryName).code2(article.getId() + "").build();
            relationDao.saveAndFlush(relation);
        });

        //标签
        List<String> tagNames = Arrays.asList(new String[]{"tag1", "tag2", "tag2"});
        tagNames.forEach(tagName -> {
            Tag tag = Tag.builder()
                    .tagName(tagName)
                    .createTime(new Date())
                    .modifyTime(new Date()).build();
            tagDao.saveAndFlush(tag);
            Relation relation = Relation.builder().createTime(new Date()).modifyTime(new Date())
                    .relationType(RelationTypeConsts.ARTICLE_CATEGORY)
                    .code1(tagName).code2(article.getId() + "").build();
            relationDao.saveAndFlush(relation);
        });

        //评论
        Comment comment = Comment.builder()
                .commentNickname("tyblog")
                .commentNicknameUrl("")
                .commentNicknameUrl("")
                .commentParentId(1)
                .createTime(new Date())
                .modifyTime(new Date()).build();
        commentDao.saveAndFlush(comment);
        //友情链接
        Link link = Link.builder()
                .linkTitle("红杏出墙")
                .linkAddress("www.baidu.com")
                .linkDesc("红杏出墙desc")
                .linkOrder(5)
                .isTop("1")
                .createTime(new Date())
                .modifyTime(new Date()).build();
        linkDao.saveAndFlush(link);


        relationDao.saveAndFlush(Relation.builder().createTime(new Date()).modifyTime(new Date())
                .relationType(RelationTypeConsts.USER_ROLE).code1("tyblog").code2("blogger").build());
        relationDao.saveAndFlush(Relation.builder().createTime(new Date()).modifyTime(new Date())
                .relationType(RelationTypeConsts.ROLE_PERM).code1("blogger").code2("article:*").build());


    }


    @Test
    public void getPermByUsername(){
        System.out.println(maxIdUtil.getMaxId(TableNameConsts.TY_RELATION));
    }

}
