/*
 Navicat Premium Data Transfer

 Source Server         : aliyun
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : 39.106.163.62:3306
 Source Schema         : cms

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 08/01/2021 19:53:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_article
-- ----------------------------
DROP TABLE IF EXISTS `tb_article`;
CREATE TABLE `tb_article`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `channel_id` int(11) NOT NULL COMMENT '栏目id',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `title_img` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题图',
  `summary` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '摘要',
  `url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '外链URL',
  `content` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '正文',
  `status` int(1) NULL DEFAULT NULL COMMENT '0待发布1发布',
  `comment_status` int(1) NULL DEFAULT NULL COMMENT '0/Null允许评论，不允许评论',
  `rotation` int(1) NULL DEFAULT NULL COMMENT '0/NULL非轮播 1 轮播',
  `top` int(1) NULL DEFAULT NULL COMMENT '0/null不置顶，1置顶',
  `orderby` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_date` date NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `update_date` date NULL DEFAULT NULL,
  `article_view` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id_articleId_id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 80 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文章' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_article
-- ----------------------------

-- ----------------------------
-- Table structure for tb_article_attachment
-- ----------------------------
DROP TABLE IF EXISTS `tb_article_attachment`;
CREATE TABLE `tb_article_attachment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `article_id` int(11) NULL DEFAULT NULL COMMENT '文章ID',
  `url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文章附件' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_article_attachment
-- ----------------------------
-- ----------------------------
-- Table structure for tb_article_tag
-- ----------------------------
DROP TABLE IF EXISTS `tb_article_tag`;
CREATE TABLE `tb_article_tag`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` int(11) NULL DEFAULT NULL,
  `tag_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 97 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文章标签' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_article_tag
-- ----------------------------

-- ----------------------------
-- Table structure for tb_channel
-- ----------------------------
DROP TABLE IF EXISTS `tb_channel`;
CREATE TABLE `tb_channel`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '上级栏目',
  `channel_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '栏目图片',
  `summary` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '摘要',
  `single` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否单页 .Y为单页，Null为不单页',
  `url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '外链URL',
  `seo_title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'SEO标题',
  `seo_keyword` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'SEO关键字',
  `seo_description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'SEO描述',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '正文',
  `create_user` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `create_date` date NULL DEFAULT NULL COMMENT '创建时间',
  `pos` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '位置',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '栏目' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_channel
-- ----------------------------

-- ----------------------------
-- Table structure for tb_comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_comment`;
CREATE TABLE `tb_comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `author` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评论者',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `create_date` date NULL DEFAULT NULL COMMENT '创建时间',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '正文',
  `status` int(1) NULL DEFAULT NULL COMMENT '0 待批准，1已通过2未通过',
  `article_id` int(11) NULL DEFAULT NULL COMMENT '文章ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '评论' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_comment
-- ----------------------------

-- ----------------------------
-- Table structure for tb_friend_link
-- ----------------------------
DROP TABLE IF EXISTS `tb_friend_link`;
CREATE TABLE `tb_friend_link`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '链接URL',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `target` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '友情链接' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_friend_link

-- Table structure for tb_tag
-- ----------------------------
DROP TABLE IF EXISTS `tb_tag`;
CREATE TABLE `tb_tag`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_tag
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `nick_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `create_date` date NULL DEFAULT NULL COMMENT '创建时间',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 't代表正常，f代表禁用，d代表删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 358 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user

SET FOREIGN_KEY_CHECKS = 1;
