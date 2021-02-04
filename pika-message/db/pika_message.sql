SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for email_template
-- ----------------------------
DROP TABLE IF EXISTS `email_template`;
CREATE TABLE `email_template` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `name` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '名称',
  `code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '模板编号',
  `title` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮件标题',
  `content` text COLLATE utf8mb4_bin COMMENT '邮件内容',
  `user_sign_id` bigint(20) DEFAULT NULL COMMENT '用户签名id',
  `sender_source_id` bigint(20) DEFAULT NULL COMMENT '邮件服务器id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `is_deleted` bigint(20) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_email_template_name_code` (`name`,`code`,`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='邮件模板';

-- ----------------------------
-- Table structure for email_user_sign
-- ----------------------------
DROP TABLE IF EXISTS `email_user_sign`;
CREATE TABLE `email_user_sign` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `signature` text COLLATE utf8mb4_bin COMMENT '签名',
  `user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `is_deleted` bigint(20) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='邮件签名';

-- ----------------------------
-- Table structure for sms_template
-- ----------------------------
DROP TABLE IF EXISTS `sms_template`;
CREATE TABLE `sms_template` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `channel` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '短信通道',
  `type` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '短信模板类型',
  `code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '短信模板编号',
  `content` varchar(1024) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '短信模板内容',
  `expire_time` int(11) DEFAULT NULL COMMENT '验证码有效时间（秒/s）',
  `has_store` tinyint(1) DEFAULT NULL COMMENT '是否留存',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `is_deleted` bigint(20) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_sms_template_type_code` (`type`,`code`,`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='短信模板';

-- ----------------------------
-- Table structure for verification_code
-- ----------------------------
DROP TABLE IF EXISTS `verification_code`;
CREATE TABLE `verification_code` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `code` text COLLATE utf8mb4_bin COMMENT '手机号/邮箱验证码',
  `source` varchar(256) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手机号/邮箱',
  `source_type` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '类型: 短信/邮箱',
  `verify_type` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '验证码类型',
  `is_used` tinyint(1) DEFAULT NULL COMMENT '验证码是否使用',
  `out_id` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '外部订单号',
  `biz_id` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '外部业务id',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `is_deleted` bigint(20) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `idx_verification_code_source` (`source`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='验证码';


-- ----------------------------
-- Table structure for email_message_source
-- ----------------------------
DROP TABLE IF EXISTS `email_message_source`;
CREATE TABLE `email_message_source` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `name` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '名称',
  `smtp_user` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户',
  `smtp_password` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '密码',
  `smtp_security` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '连接加密方案',
  `smtp_host` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'smtp host',
  `smtp_port` int(11) DEFAULT NULL COMMENT 'smtp 端口号',
  `active` tinyint(1) DEFAULT NULL COMMENT '是否激活',
  `type` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '消息通知类型',
  `sequence` int(11) DEFAULT NULL COMMENT '若邮件没有指定服务器，使用最高优先级（数字越小，优先级越高）的服务器。',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `write_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_uid` bigint(20) DEFAULT NULL COMMENT '创建人',
  `write_uid` bigint(20) DEFAULT NULL COMMENT '更新人',
  `is_deleted` bigint(20) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_message_source_smtp_user` (`smtp_user`,`is_deleted`),
  UNIQUE KEY `uk_message_source_smtp_host` (`smtp_host`,`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='邮件服务器';

-- ----------------------------
-- Table structure for sms_message_source
-- ----------------------------
DROP TABLE IF EXISTS `sms_message_source`;
CREATE TABLE `sms_message_source` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `name` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '名称',
  `channel` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '短信通道',
  `sign_name` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '短信签名名称',
  `access_key_id` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '主账号AccessKey的ID',
  `access_key_secret` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '主账号AccessKey的密钥',
  `action` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'SMSAction',
  `endpoint` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '发送渠道Endpoint',
  `region_id` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'API支持的RegionID',
  `time_zone` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '时区',
  `signature_method` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '签名方式',
  `signature_version` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '签名算法版本',
  `version` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'API的版本号',
  `type` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '消息通知类型',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `write_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_uid` bigint(20) DEFAULT NULL COMMENT '创建人',
  `write_uid` bigint(20) DEFAULT NULL COMMENT '更新人',
  `is_deleted` bigint(20) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_message_source_channel` (`channel`,`is_deleted`),
  UNIQUE KEY `uk_message_source_sign_name` (`sign_name`,`is_deleted`),
  UNIQUE KEY `uk_message_source_access_key_id` (`access_key_id`,`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='短信供应商';

SET FOREIGN_KEY_CHECKS = 1;