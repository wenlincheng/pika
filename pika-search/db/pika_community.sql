
SET NAMES utf8mb4;

DROP DATABASE IF EXISTS pika_community;
CREATE DATABASE pika_community DEFAULT CHARSET utf8mb4;
USE pika_community;

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for banner
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '名称',
  `banner_img_url` varchar(255) NOT NULL DEFAULT '' COMMENT '图片url',
  `jump_type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '跳转类型 1 不跳转 2 外部链接 3 活动页',
  `jump_url` mediumtext COMMENT '跳转内容 跳转类型2 url 跳转类型3 html数据',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态 1 显示 2 隐藏',
  `sequence` smallint(255) DEFAULT '0' COMMENT '排序',
  `view` int(10) DEFAULT '0' COMMENT '浏览量',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Banner表';

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) unsigned DEFAULT '0' COMMENT '父节点id',
  `user_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '用户id',
  `reply_user_id` bigint(20) unsigned DEFAULT '0' COMMENT '被@的用户id',
  `source_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '评论的资源id',
  `source_type` varchar(20) NOT NULL DEFAULT '1' COMMENT '评论的资源类型 1 动态 2 回复评论',
  `content` varchar(255) DEFAULT NULL COMMENT '评论内容',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态 1 显示 2 隐藏',
  `like_count` int(10) DEFAULT '0' COMMENT '点赞数',
  `ideleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除 0 否 1 是',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_parent_id` (`parent_id`) USING BTREE,
  KEY `idx_user_id` (`user_id`) USING BTREE,
  KEY `idx_source_id_source_type` (`source_id`,`source_type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评论表';

-- ----------------------------
-- Table structure for hot_word
-- ----------------------------
DROP TABLE IF EXISTS `hot_word`;
CREATE TABLE `hot_word` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  `search_count` int(11) DEFAULT NULL COMMENT '搜索次数',
  `type` tinyint(1) DEFAULT NULL COMMENT '创建类型 1 用户创建 2 运营创建',
  `status` tinyint(1) DEFAULT '1' COMMENT '状态 1 显示 2 隐藏',
  `sequence` smallint(6) DEFAULT NULL COMMENT '排序',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='搜索热词表';

-- ----------------------------
-- Table structure for interaction_relation
-- ----------------------------
DROP TABLE IF EXISTS `interaction_relation`;
CREATE TABLE `interaction_relation` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned DEFAULT NULL COMMENT '用户id',
  `source_id` bigint(20) unsigned DEFAULT NULL COMMENT '来源id',
  `source_type` tinyint(2) NOT NULL COMMENT '来源类型 1 动态 2 评论 3 话题',
  `type` tinyint(2) NOT NULL COMMENT '互动类型 1 点赞 2 收藏 3 关注',
  `count` int(11) DEFAULT '0' COMMENT '计数 点赞计数等',
  `ideleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除 0 否 1 是',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_source_id_source_type_type` (`source_id`,`source_type`,`type`) USING BTREE,
  KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='互动关系表';

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned DEFAULT '0' COMMENT '用户id',
  `topiid` bigint(20) unsigned DEFAULT NULL COMMENT '话题id',
  `content` varchar(1000) DEFAULT NULL COMMENT '内容',
  `img_urls` varchar(1000) DEFAULT NULL COMMENT '逗号分隔，图片URL',
  `status` tinyint(1) DEFAULT '1' COMMENT '状态 1 显示 2 隐藏 ',
  `ijing` tinyint(1) DEFAULT NULL COMMENT '是否加精',
  `type` tinyint(6) DEFAULT NULL COMMENT '创建方式 1 用户创建 2 运营马甲创建 3 官方账号创建',
  `like_count` int(11) DEFAULT '0' COMMENT '点赞数',
  `view_count` int(11) DEFAULT '0' COMMENT '浏览数',
  `ideleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除 0 否 1 是',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`) USING BTREE,
  KEY `idx_create_time` (`create_time`) USING BTREE,
  KEY `idx_topiid` (`topiid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='动态表';

-- ----------------------------
-- Table structure for tool
-- ----------------------------
DROP TABLE IF EXISTS `tool`;
CREATE TABLE `tool` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `tool_type` varchar(20) DEFAULT NULL COMMENT '工具类型',
  `name` varchar(255) DEFAULT NULL COMMENT '工具名称',
  `icon_url` varchar(255) DEFAULT NULL COMMENT 'icon链接',
  `jump_info` varchar(255) DEFAULT NULL COMMENT '跳转链接',
  `status` tinyint(1) DEFAULT '1' COMMENT '状态 1 显示 2 隐藏',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='运营工具表';

-- ----------------------------
-- Table structure for topic
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '标题',
  `introduction` varchar(150) NOT NULL COMMENT '描述',
  `home_img_url` varchar(255) DEFAULT '' COMMENT '首页',
  `main_img_url` varchar(255) DEFAULT NULL COMMENT '主页',
  `list_img_url` varchar(255) DEFAULT NULL COMMENT '列表页',
  `status` tinyint(1) DEFAULT '1' COMMENT '使用状态 1 显示 2 隐藏',
  `viewed` int(11) DEFAULT '0' COMMENT '浏览量',
  `sequence` int(11) DEFAULT '0' COMMENT '排序',
  `ideleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除 0 否 1 是',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='话题表';

SET FOREIGN_KEY_CHECKS = 1;
