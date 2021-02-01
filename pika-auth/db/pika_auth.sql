
SET NAMES utf8mb4;

DROP DATABASE IF EXISTS pika_oauth;
CREATE DATABASE pika_oauth DEFAULT CHARSET utf8mb4;
USE pika_oauth;

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oauth_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token` (
  `token_id` varchar(256) DEFAULT NULL COMMENT 'MD5加密的access_token的值',
  `token` blob COMMENT 'OAuth2AccessToken.java对象序列化后的二进制数据',
  `authentication_id` varchar(256) DEFAULT NULL COMMENT 'MD5加密过的username,client_id,scope',
  `user_name` varchar(256) DEFAULT NULL COMMENT '登录的用户名',
  `client_id` varchar(256) DEFAULT NULL COMMENT '客户端ID',
  `authentication` blob COMMENT 'OAuth2Authentication.java对象序列化后的二进制数据',
  `refresh_token` varchar(256) DEFAULT NULL COMMENT 'MD5加密果的refresh_token的值'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='访问令牌表';

-- ----------------------------
-- Table structure for oauth_approvals
-- ----------------------------
DROP TABLE IF EXISTS `oauth_approvals`;
CREATE TABLE `oauth_approvals` (
  `userid` varchar(256) DEFAULT NULL COMMENT '登录的用户名',
  `clientid` varchar(256) DEFAULT NULL COMMENT '客户端ID',
  `scope` varchar(256) DEFAULT NULL COMMENT '申请的权限',
  `status` varchar(10) DEFAULT NULL COMMENT '状态（Approve或Deny）',
  `expiresat` datetime DEFAULT NULL COMMENT '过期时间',
  `lastmodifiedat` datetime DEFAULT NULL COMMENT '最终修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='授权记录表';

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `code` varchar(256) DEFAULT NULL COMMENT '授权码(未加密)',
  `authentication` blob COMMENT 'AuthorizationRequestHolder.java对象序列化后的二进制数据'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='授权码表';

-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(256) DEFAULT NULL COMMENT 'MD5加密过的refresh_token的值',
  `token` blob COMMENT 'OAuth2RefreshToken.java对象序列化后的二进制数据',
  `authentication` blob COMMENT 'OAuth2Authentication.java对象序列化后的二进制数据'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='更新令牌表';

SET FOREIGN_KEY_CHECKS = 1;
