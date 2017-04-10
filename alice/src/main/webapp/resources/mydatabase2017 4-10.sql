/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50520
Source Host           : 127.0.0.1:3306
Source Database       : mydatabase

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2017-04-10 22:20:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for html_meituan
-- ----------------------------
DROP TABLE IF EXISTS `html_meituan`;
CREATE TABLE `html_meituan` (
  `id` char(36) NOT NULL COMMENT '主键',
  `url` varchar(255) NOT NULL,
  `keyword` varchar(255) DEFAULT NULL COMMENT '关键字',
  `city` char(30) DEFAULT NULL COMMENT '(gaevent=header/cityname)城市',
  `grade_type` varchar(200) DEFAULT NULL COMMENT '分类按照食品层级(class="bread-nav")',
  `component_description` varchar(255) DEFAULT NULL COMMENT '(class=deal-component-description)组件描述',
  `default_price` double(10,0) DEFAULT NULL COMMENT '默认价格',
  `discount_price` double(10,0) DEFAULT NULL COMMENT '折扣价',
  `out_quantities` int(11) DEFAULT NULL COMMENT '已售出数量class=deal-component-rating-sold-count orange',
  `rating` double(10,0) DEFAULT NULL COMMENT 'class=average-score评分',
  `collect_count` int(10) DEFAULT NULL COMMENT '收藏数(class="J-fav-count)',
  `rating_persons` int(11) DEFAULT NULL COMMENT '评分人数class=(total-group total-count)',
  `end_time` timestamp NULL DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`,`url`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for html_meituan_comment
-- ----------------------------
DROP TABLE IF EXISTS `html_meituan_comment`;
CREATE TABLE `html_meituan_comment` (
  `html_url` varchar(255) DEFAULT NULL COMMENT '网页链接',
  `comment` varchar(255) DEFAULT NULL,
  `comment_date` datetime DEFAULT NULL COMMENT '评论时间',
  `comment_score` double(10,0) DEFAULT NULL COMMENT '得分'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for html_message
-- ----------------------------
DROP TABLE IF EXISTS `html_message`;
CREATE TABLE `html_message` (
  `id` char(36) NOT NULL COMMENT '主键',
  `url` varchar(255) DEFAULT NULL,
  `type` tinyint(1) DEFAULT NULL COMMENT '(0:标题,1:文章内容,2:图片)',
  `keywords` varchar(255) DEFAULT NULL COMMENT '关键词',
  `editor` varchar(255) DEFAULT NULL COMMENT '责任编辑',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `article_create_time` timestamp NULL DEFAULT NULL COMMENT '文章创建时间',
  `article_source` varchar(50) DEFAULT NULL COMMENT '文章来源',
  `article_content` varchar(10000) DEFAULT NULL COMMENT '文章正文',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '数据创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for html_scoring_details
-- ----------------------------
DROP TABLE IF EXISTS `html_scoring_details`;
CREATE TABLE `html_scoring_details` (
  `score` int(1) DEFAULT NULL,
  `score_person` int(11) DEFAULT NULL,
  `html_url` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for spider_waitqueue
-- ----------------------------
DROP TABLE IF EXISTS `spider_waitqueue`;
CREATE TABLE `spider_waitqueue` (
  `id` char(36) NOT NULL COMMENT '主键',
  `father_url` varchar(1000) NOT NULL,
  `url` varchar(1000) DEFAULT NULL COMMENT '地址',
  `isdownload` int(1) DEFAULT '0' COMMENT '是否已下载(0:未下载,1:已下载,2:下载失败)',
  `downpath` varchar(1000) DEFAULT NULL,
  `analysisError` int(1) unsigned zerofill DEFAULT '0' COMMENT 'j解析失败,失败三次不再解析',
  `isanalysis` int(1) DEFAULT '0' COMMENT '是否已解析(0:未解析,1:已解析)',
  `type` int(1) DEFAULT '0' COMMENT '页面种类(0:普通,1:新闻类,2美团商品)',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for system_users
-- ----------------------------
DROP TABLE IF EXISTS `system_users`;
CREATE TABLE `system_users` (
  `USER_ID` varchar(20) NOT NULL COMMENT '使用者ID',
  `USER_NAME` varchar(20) DEFAULT '' COMMENT '用户名',
  `USER_PHONE` varchar(16) DEFAULT NULL COMMENT '用户手机号',
  `USER_CREAT_TIME` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `USER_UPDATE_TIME` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for system_users_detail
-- ----------------------------
DROP TABLE IF EXISTS `system_users_detail`;
CREATE TABLE `system_users_detail` (
  `DETAIL_ID` tinyint(10) NOT NULL AUTO_INCREMENT COMMENT '详情ID',
  `USER_ID` varchar(20) DEFAULT NULL COMMENT '人员ID',
  PRIMARY KEY (`DETAIL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
