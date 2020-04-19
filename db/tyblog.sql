/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : tyblog

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 19/04/2020 19:37:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ty_article
-- ----------------------------
DROP TABLE IF EXISTS `ty_article`;
CREATE TABLE `ty_article`  (
  `article_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `summary` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `md_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `html_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `publish_date` datetime(0) NOT NULL,
  `update_date` datetime(0) NOT NULL,
  `likes` int(11) NOT NULL,
  `read_count` int(11) NOT NULL,
  `article_img` varchar(10000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_comment` bit(1) NOT NULL,
  `read_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_read` bit(1) NOT NULL,
  `state` int(11) NOT NULL,
  `create_time` datetime(0) NOT NULL,
  `modify_time` datetime(0) NOT NULL,
  `author_id` bigint(20) NULL DEFAULT NULL,
  `original_author_id` bigint(20) NULL DEFAULT NULL,
  `category_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`article_id`) USING BTREE,
  INDEX `FKmucyw06xj1rb7go7g2orik4ot`(`author_id`) USING BTREE,
  INDEX `FKtas0scm7cy69oxl4ccrwifwvm`(`original_author_id`) USING BTREE,
  INDEX `FKp0xr1hatj49wq7sm9vp98cf6j`(`category_id`) USING BTREE,
  CONSTRAINT `FKmucyw06xj1rb7go7g2orik4ot` FOREIGN KEY (`author_id`) REFERENCES `ty_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKp0xr1hatj49wq7sm9vp98cf6j` FOREIGN KEY (`category_id`) REFERENCES `ty_category` (`category_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKtas0scm7cy69oxl4ccrwifwvm` FOREIGN KEY (`original_author_id`) REFERENCES `ty_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ty_article
-- ----------------------------
INSERT INTO `ty_article` VALUES (1, 'hello world', 'hello world', 'hello word', 'hello word', '2020-02-13 04:25:25', '2020-02-13 04:25:25', 10, 10, 'http://127.0.0.1:8002/dev-api/article/img/default.jpg', b'1', NULL, b'1', 1, '2020-02-13 04:25:25', '2020-02-13 04:25:25', 1, 1, 1);
INSERT INTO `ty_article` VALUES (2, '随便写写', '[Java1234大萨达阿萨德阿萨德啊]', 'Java1234**大萨达阿萨德阿萨德啊**', '<p>Java1234<strong>大萨达阿萨德阿萨德啊</strong></p>\n', '2020-02-13 04:25:50', '2020-02-13 04:25:50', 0, 0, 'http://127.0.0.1:8002/dev-api/article/img/default.jpg', b'1', '', b'1', 1, '2020-02-13 04:25:50', '2020-02-13 04:25:50', 1, 1, 1);

-- ----------------------------
-- Table structure for ty_article_tag
-- ----------------------------
DROP TABLE IF EXISTS `ty_article_tag`;
CREATE TABLE `ty_article_tag`  (
  `article_id` bigint(20) NOT NULL,
  `tag_id` bigint(20) NOT NULL,
  PRIMARY KEY (`article_id`, `tag_id`) USING BTREE,
  INDEX `FKcm12f20c2vla2a1cqwtnmlrpk`(`tag_id`) USING BTREE,
  CONSTRAINT `FKcm12f20c2vla2a1cqwtnmlrpk` FOREIGN KEY (`tag_id`) REFERENCES `ty_tag` (`tag_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKhmfpmlqod73x6gdk4q8akw6f5` FOREIGN KEY (`article_id`) REFERENCES `ty_article` (`article_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ty_article_tag
-- ----------------------------
INSERT INTO `ty_article_tag` VALUES (1, 1);
INSERT INTO `ty_article_tag` VALUES (2, 2);
INSERT INTO `ty_article_tag` VALUES (2, 3);
INSERT INTO `ty_article_tag` VALUES (2, 4);
INSERT INTO `ty_article_tag` VALUES (2, 5);

-- ----------------------------
-- Table structure for ty_category
-- ----------------------------
DROP TABLE IF EXISTS `ty_category`;
CREATE TABLE `ty_category`  (
  `category_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `category_desc` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `create_time` datetime(0) NOT NULL,
  `modify_time` datetime(0) NOT NULL,
  PRIMARY KEY (`category_id`) USING BTREE,
  UNIQUE INDEX `UK_rhr7ie4taj3u6vkq50fhj3h0x`(`category_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ty_category
-- ----------------------------
INSERT INTO `ty_category` VALUES (1, 'java', 'java是编程语言', '2020-02-13 04:25:25', '2020-02-13 04:25:25');

-- ----------------------------
-- Table structure for ty_comment
-- ----------------------------
DROP TABLE IF EXISTS `ty_comment`;
CREATE TABLE `ty_comment`  (
  `comment_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment_level` int(11) NOT NULL,
  `create_time` datetime(0) NOT NULL,
  `modify_time` datetime(0) NOT NULL,
  `user_id` bigint(20) NULL DEFAULT NULL,
  `article_id` bigint(20) NULL DEFAULT NULL,
  `comment_parent_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`comment_id`) USING BTREE,
  INDEX `FK3orrmhr1h3b3bln3b4i5pkhf`(`user_id`) USING BTREE,
  INDEX `FKaxwh1itnho1tseey9f746ffqe`(`article_id`) USING BTREE,
  INDEX `FKl23qukxnd9380frc3pvdmtav8`(`comment_parent_id`) USING BTREE,
  CONSTRAINT `FK3orrmhr1h3b3bln3b4i5pkhf` FOREIGN KEY (`user_id`) REFERENCES `ty_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKaxwh1itnho1tseey9f746ffqe` FOREIGN KEY (`article_id`) REFERENCES `ty_article` (`article_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKl23qukxnd9380frc3pvdmtav8` FOREIGN KEY (`comment_parent_id`) REFERENCES `ty_comment` (`comment_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ty_comment
-- ----------------------------
INSERT INTO `ty_comment` VALUES (1, 1, '2020-02-13 04:25:25', '2020-02-13 04:25:25', 1, 1, NULL);

-- ----------------------------
-- Table structure for ty_link
-- ----------------------------
DROP TABLE IF EXISTS `ty_link`;
CREATE TABLE `ty_link`  (
  `link_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `link_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `link_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `link_desc` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `link_order` int(11) NOT NULL,
  `is_top` bit(1) NOT NULL,
  `create_time` datetime(0) NOT NULL,
  `modify_time` datetime(0) NOT NULL,
  PRIMARY KEY (`link_id`) USING BTREE,
  UNIQUE INDEX `UK_6ms2bgjiiuyd6stoxqf5k3fag`(`link_title`) USING BTREE,
  UNIQUE INDEX `UK_o0gs1i451n2m86d06pmpql38h`(`link_address`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ty_link
-- ----------------------------
INSERT INTO `ty_link` VALUES (1, '百度', 'www.baidu.com', '百度一下你就知道', 5, b'1', '2020-02-13 04:25:25', '2020-02-13 04:25:25');

-- ----------------------------
-- Table structure for ty_perm
-- ----------------------------
DROP TABLE IF EXISTS `ty_perm`;
CREATE TABLE `ty_perm`  (
  `perm_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `perm_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `perm_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime(0) NOT NULL,
  `modify_time` datetime(0) NOT NULL,
  PRIMARY KEY (`perm_id`) USING BTREE,
  UNIQUE INDEX `UK_m5dehcc09asdxsc4qrskf26d0`(`perm_name`) USING BTREE,
  UNIQUE INDEX `UK_fw0jxildd1apot39k00i6784t`(`perm_desc`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ty_perm
-- ----------------------------
INSERT INTO `ty_perm` VALUES (1, 'article:*', '文章类增删改查', '2020-02-13 04:25:25', '2020-02-13 04:25:25');
INSERT INTO `ty_perm` VALUES (2, 'user:get', '用户类获取信息', '2020-02-13 04:25:25', '2020-02-13 04:25:25');

-- ----------------------------
-- Table structure for ty_relation
-- ----------------------------
DROP TABLE IF EXISTS `ty_relation`;
CREATE TABLE `ty_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `relation_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `code_1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `code_1_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `code_2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `code_2_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NOT NULL,
  `modify_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_role
-- ----------------------------
DROP TABLE IF EXISTS `ty_role`;
CREATE TABLE `ty_role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NOT NULL,
  `modify_time` datetime(0) NOT NULL,
  PRIMARY KEY (`role_id`) USING BTREE,
  UNIQUE INDEX `UK_qsit2e8thirvqkcltndkgf32c`(`role_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ty_role
-- ----------------------------
INSERT INTO `ty_role` VALUES (1, 'blogger', '博主', '2020-02-13 04:25:25', '2020-02-13 04:25:25');

-- ----------------------------
-- Table structure for ty_role_perm
-- ----------------------------
DROP TABLE IF EXISTS `ty_role_perm`;
CREATE TABLE `ty_role_perm`  (
  `role_id` bigint(20) NOT NULL,
  `perm_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`, `perm_id`) USING BTREE,
  INDEX `FKb3ty2bselpk2f8na9axnj6y4b`(`perm_id`) USING BTREE,
  CONSTRAINT `FK6nfplsecr1af1bmnxwiv0x9yp` FOREIGN KEY (`role_id`) REFERENCES `ty_role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKb3ty2bselpk2f8na9axnj6y4b` FOREIGN KEY (`perm_id`) REFERENCES `ty_perm` (`perm_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_tag
-- ----------------------------
DROP TABLE IF EXISTS `ty_tag`;
CREATE TABLE `ty_tag`  (
  `tag_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `category_desc` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `create_time` datetime(0) NOT NULL,
  `modify_time` datetime(0) NOT NULL,
  PRIMARY KEY (`tag_id`) USING BTREE,
  UNIQUE INDEX `UK_20u38ksv4tnnjvpe50nl8ktbw`(`tag_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ty_tag
-- ----------------------------
INSERT INTO `ty_tag` VALUES (1, '编程语言', 'java是最流行的', '2020-02-13 04:25:25', '2020-02-13 04:25:25');
INSERT INTO `ty_tag` VALUES (2, '标签二', NULL, '2020-02-13 04:25:50', '2020-02-13 04:25:50');
INSERT INTO `ty_tag` VALUES (3, '大叔大婶', NULL, '2020-02-13 04:25:50', '2020-02-13 04:25:50');
INSERT INTO `ty_tag` VALUES (4, '标签三', NULL, '2020-02-13 04:25:50', '2020-02-13 04:25:50');
INSERT INTO `ty_tag` VALUES (5, '标签一', NULL, '2020-02-13 04:25:50', '2020-02-13 04:25:50');

-- ----------------------------
-- Table structure for ty_user
-- ----------------------------
DROP TABLE IF EXISTS `ty_user`;
CREATE TABLE `ty_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gender` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthday` date NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `profile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `avatar_url` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `github_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `github_url` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `is_admin` longblob NULL,
  `last_login_time` datetime(0) NULL DEFAULT NULL,
  `create_time` datetime(0) NOT NULL,
  `modify_time` datetime(0) NOT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `UK_pvkpjr6cokdcp9jadtekgmi83`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ty_user
-- ----------------------------
INSERT INTO `ty_user` VALUES (1, 'tyblog', '20e3c9afeb79d34972e138ca1bdc3271', '男', '张小生', 'tyblog', '2020-02-13', 'tyblog@qq.com', 'hello world,我是tyblog。', 'https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1719911752,2197523375&fm=26&gp=0.jpg', 'zhangtianyi0110', 'https://github.com/zhangtianyi0110', 0xACED0005737200116A6176612E6C616E672E426F6F6C65616ECD207280D59CFAEE0200015A000576616C7565787001, '2020-04-19 11:30:13', '2020-02-13 04:25:25', '2020-04-19 11:30:13');

-- ----------------------------
-- Table structure for ty_user_perm
-- ----------------------------
DROP TABLE IF EXISTS `ty_user_perm`;
CREATE TABLE `ty_user_perm`  (
  `user_id` bigint(20) NOT NULL,
  `perm_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`, `perm_id`) USING BTREE,
  INDEX `FK30tm03ud48w3fmuepsw3wr0wh`(`perm_id`) USING BTREE,
  CONSTRAINT `FK30tm03ud48w3fmuepsw3wr0wh` FOREIGN KEY (`perm_id`) REFERENCES `ty_perm` (`perm_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKa21p0bjp590682biccntyx83d` FOREIGN KEY (`user_id`) REFERENCES `ty_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ty_user_perm
-- ----------------------------
INSERT INTO `ty_user_perm` VALUES (1, 2);

-- ----------------------------
-- Table structure for ty_user_role
-- ----------------------------
DROP TABLE IF EXISTS `ty_user_role`;
CREATE TABLE `ty_user_role`  (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
  INDEX `FK7x2nd7104yj9157yt1wjt392`(`role_id`) USING BTREE,
  CONSTRAINT `FK6uiucjcn2rqia147aredio0e9` FOREIGN KEY (`user_id`) REFERENCES `ty_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK7x2nd7104yj9157yt1wjt392` FOREIGN KEY (`role_id`) REFERENCES `ty_role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ty_user_role
-- ----------------------------
INSERT INTO `ty_user_role` VALUES (1, 1);

SET FOREIGN_KEY_CHECKS = 1;
