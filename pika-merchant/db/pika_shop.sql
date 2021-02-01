SET NAMES utf8mb4;

DROP DATABASE IF EXISTS pika_shop;
CREATE DATABASE pika_shop DEFAULT CHARSET utf8mb4;
USE pika_shop;

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for label
-- ----------------------------
DROP TABLE IF EXISTS `label`;
CREATE TABLE `label` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(80) DEFAULT NULL COMMENT '名称',
  `description` varchar(800) DEFAULT NULL COMMENT '描述',
  `type` tinyint(2) DEFAULT NULL COMMENT '类型 1 店铺 2 商品',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除 0 否 1 是',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='标签表';

-- ----------------------------
-- Table structure for shop
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '店铺名称',
  `logo` varchar(200) DEFAULT NULL COMMENT '店铺LOGO',
  `shop_no` varchar(32) DEFAULT NULL COMMENT '店铺编号',
  `scope` varchar(100) DEFAULT NULL COMMENT '经营范围',
  `address` varchar(100) DEFAULT NULL COMMENT '经营地址',
  `man_body` varchar(500) DEFAULT NULL COMMENT '经营主体信息',
  `domain` varchar(200) DEFAULT NULL COMMENT '店铺域名主营类目',
  `telphone` varchar(20) DEFAULT NULL COMMENT '客服电话',
  `description` varchar(500) DEFAULT NULL COMMENT '店铺描述',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除 0 否 1 是',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='店铺表';

-- ----------------------------
-- Table structure for shop_address
-- ----------------------------
DROP TABLE IF EXISTS `shop_address`;
CREATE TABLE `shop_address` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `shop_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '店铺id',
  `key_word` varchar(80) DEFAULT NULL COMMENT '关键字',
  `contact_name` varchar(80) DEFAULT NULL COMMENT '联系人姓名',
  `landline` varchar(20) DEFAULT NULL COMMENT '座机号',
  `mobile` varchar(15) DEFAULT NULL COMMENT '手机号',
  `area_code` int(11) DEFAULT NULL COMMENT '省市区街道编码',
  `postal_code` varchar(20) DEFAULT NULL COMMENT '邮政编码',
  `detail` varchar(800) DEFAULT NULL COMMENT '详细地址：如门牌号、小区、楼栋号、单元室等',
  `area_address` varchar(2000) DEFAULT NULL COMMENT '地址全名',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除 0 否 1 是',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_shop_id` (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商家地址库表';

-- ----------------------------
-- Table structure for shop_address_type
-- ----------------------------
DROP TABLE IF EXISTS `shop_address_type`;
CREATE TABLE `shop_address_type` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `shop_address_id` bigint(20) unsigned DEFAULT NULL COMMENT '商家地址库id',
  `type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '地址类型 1 发货地址 2 退货地址 3 发票地址',
  `is_default` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否默认 0 否 1 是',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_shop_address_id` (`shop_address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商家地址类型表';

-- ----------------------------
-- Table structure for shop_classify
-- ----------------------------
DROP TABLE IF EXISTS `shop_classify`;
CREATE TABLE `shop_classify` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `shop_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '店铺id',
  `page_id` bigint(20) unsigned DEFAULT NULL COMMENT '分组页面id',
  `code` varchar(10) DEFAULT NULL COMMENT '编码',
  `name` varchar(80) DEFAULT NULL COMMENT '名称',
  `pid` int(11) DEFAULT NULL COMMENT '上级分类上级品类',
  `icon` varchar(1000) DEFAULT NULL COMMENT '图片图标',
  `description` varchar(2000) DEFAULT NULL COMMENT '品类介绍',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `level` int(11) DEFAULT NULL COMMENT '层级',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除 0 否 1 是',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_shop_id` (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='店铺级自定义商品分类表';

-- ----------------------------
-- Table structure for shop_compose
-- ----------------------------
DROP TABLE IF EXISTS `shop_compose`;
CREATE TABLE `shop_compose` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `shop_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '店铺id',
  `name` varchar(200) DEFAULT NULL COMMENT '组件名',
  `type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '组件类型 1 图文 2 banner',
  `logic` varchar(2000) DEFAULT NULL COMMENT '组件逻辑',
  `parameter` varchar(4000) DEFAULT NULL COMMENT '组件参数 列表',
  `content` longtext COMMENT '组件内容',
  `classify` varchar(10) DEFAULT NULL COMMENT '组件的类别 是否需要配置参数',
  `action` varchar(10) DEFAULT NULL COMMENT '组件应用行为定义 引用方式/复制方式',
  `is_timing` tinyint(1) DEFAULT NULL COMMENT '是否定时应用 0 否 1 是 否表示立即应用',
  `apply_time` datetime DEFAULT NULL COMMENT '定时应用时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除 0 否 1 是',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_shop_id` (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='组件表';

-- ----------------------------
-- Table structure for shop_data
-- ----------------------------
DROP TABLE IF EXISTS `shop_data`;
CREATE TABLE `shop_data` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `shop_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '店铺id',
  `name` tinyint(2) DEFAULT NULL COMMENT '资料类型 1 关于我们 2 店铺介绍 3 免责声明 4 用户须知 5 商户须知',
  `content` text COMMENT '内容',
  `status` char(10) DEFAULT NULL COMMENT '编辑状态',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除 0 否 1 是',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_shop_id` (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='店铺资料表';

-- ----------------------------
-- Table structure for shop_delivery_area
-- ----------------------------
DROP TABLE IF EXISTS `shop_delivery_area`;
CREATE TABLE `shop_delivery_area` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `delivery_rule_id` bigint(20) unsigned DEFAULT NULL COMMENT '配送规则id',
  `area_code` int(11) DEFAULT NULL COMMENT '地区编码',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除 0 否 1 是',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='店铺配送区域范围表';

-- ----------------------------
-- Table structure for shop_express_template
-- ----------------------------
DROP TABLE IF EXISTS `shop_express_template`;
CREATE TABLE `shop_express_template` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `shop_address_id` bigint(20) unsigned DEFAULT NULL COMMENT '地址库id',
  `shop_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '店铺id',
  `name` varchar(80) DEFAULT NULL COMMENT '模板名称',
  `charging_type` varchar(10) DEFAULT NULL COMMENT '计费方式 1 按件 2 按重量',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除 0 否 1 是',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_shop_id` (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='店铺快递模板表';

-- ----------------------------
-- Table structure for shop_express_template_delivery_rule
-- ----------------------------
DROP TABLE IF EXISTS `shop_express_template_delivery_rule`;
CREATE TABLE `shop_express_template_delivery_rule` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `express_template_id` bigint(20) unsigned DEFAULT NULL COMMENT '模板id',
  `first` decimal(10,3) DEFAULT NULL COMMENT '首件/首重',
  `first_fee` decimal(15,6) DEFAULT NULL COMMENT '运费',
  `second` decimal(10,3) DEFAULT NULL COMMENT '续件/续重',
  `second_fee` decimal(15,6) DEFAULT NULL COMMENT '续费',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除 0 否 1 是',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='店铺快递模板配送规则表';

-- ----------------------------
-- Table structure for shop_image_group
-- ----------------------------
DROP TABLE IF EXISTS `shop_image_group`;
CREATE TABLE `shop_image_group` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(80) NOT NULL COMMENT '分组名',
  `shop_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '店铺id',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除 0 否 1 是',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_shop_id` (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT=' 图片分组表';

-- ----------------------------
-- Table structure for shop_image_lib
-- ----------------------------
DROP TABLE IF EXISTS `shop_image_lib`;
CREATE TABLE `shop_image_lib` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `shop_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '店铺id',
  `group_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '分组id',
  `name` varchar(300) DEFAULT NULL COMMENT '名称',
  `url` varchar(1000) DEFAULT NULL COMMENT '地址',
  `thumbnail_url` varchar(1000) DEFAULT NULL COMMENT '缩略图地址',
  `format` varchar(20) DEFAULT NULL COMMENT '图片格式',
  `filename` varchar(300) DEFAULT NULL COMMENT '文件名称',
  `proportion` decimal(10,1) DEFAULT '0.0' COMMENT '比例',
  `size` int(11) DEFAULT NULL COMMENT '大小',
  `length` int(11) DEFAULT NULL COMMENT '长',
  `width` int(11) DEFAULT NULL COMMENT '宽',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除 0 否 1 是',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_shop_id` (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6095 DEFAULT CHARSET=utf8mb4 COMMENT='商铺图片库';

-- ----------------------------
-- Table structure for shop_label
-- ----------------------------
DROP TABLE IF EXISTS `shop_label`;
CREATE TABLE `shop_label` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `label_id` int(11) NOT NULL DEFAULT '0' COMMENT '标签id',
  `shop_id` int(11) NOT NULL DEFAULT '0' COMMENT '店铺id',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除 0 否 1 是',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='店铺标签表';

-- ----------------------------
-- Table structure for shop_page
-- ----------------------------
DROP TABLE IF EXISTS `shop_page`;
CREATE TABLE `shop_page` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `shop_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '店铺id',
  `title` varchar(400) DEFAULT NULL COMMENT '页面标题',
  `descb` varchar(1000) DEFAULT NULL COMMENT '页面描述',
  `type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '页面类型 1 首页 2 活动页 3 分类页 4 商品分组页',
  `layout` text COMMENT '页面中组件的布局',
  `status` varchar(10) DEFAULT NULL COMMENT '页面状态',
  `page_group_id` bigint(20) DEFAULT NULL COMMENT '页面分组id',
  `ref_count` int(11) DEFAULT NULL COMMENT '引用次数',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除 0 否 1 是',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_shop_id` (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='页面表';

-- ----------------------------
-- Table structure for shop_page_compose
-- ----------------------------
DROP TABLE IF EXISTS `shop_page_compose`;
CREATE TABLE `shop_page_compose` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `page_id` bigint(20) unsigned DEFAULT NULL COMMENT '页面id',
  `cid` bigint(20) unsigned DEFAULT NULL COMMENT '组件id',
  `code` varchar(80) DEFAULT NULL COMMENT '编码',
  `name` varchar(200) DEFAULT NULL COMMENT '组件名',
  `type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '组件类型 1 图文 1 banner',
  `logic` varchar(2000) DEFAULT NULL COMMENT '组件逻辑',
  `parameter` varchar(4000) DEFAULT NULL COMMENT '组件参数 列表',
  `content` longtext COMMENT '组件内容',
  `classify` varchar(10) DEFAULT NULL COMMENT '组件的类别 是否需要配置参数',
  `action` varchar(10) DEFAULT NULL COMMENT '组件应用行为定义 引用方式/复制方式',
  `is_timing` tinyint(1) DEFAULT NULL COMMENT '是否定时应用 0 否 1 是 否表示立即应用',
  `apply_at` bigint(20) DEFAULT NULL COMMENT '定时应用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='页面直接使用的组件表';

-- ----------------------------
-- Table structure for shop_page_default
-- ----------------------------
DROP TABLE IF EXISTS `shop_page_default`;
CREATE TABLE `shop_page_default` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `page_id` bigint(20) unsigned DEFAULT NULL COMMENT '页面id',
  `type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '应用端类型 1 APP端 2 小程序 3 H5端',
  `is_timing` tinyint(4) DEFAULT NULL COMMENT '是否定时应用 0 否 1 是 否表示立即应用',
  `apply_time` datetime DEFAULT NULL COMMENT '定时应用时间',
  `is_default` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否默认 0 否 1 是',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除 0 否 1 是',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='页面应用的端及默认表';

-- ----------------------------
-- Table structure for shop_page_group
-- ----------------------------
DROP TABLE IF EXISTS `shop_page_group`;
CREATE TABLE `shop_page_group` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `shop_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '店铺id',
  `name` varchar(400) DEFAULT NULL COMMENT '分组名称',
  `num` int(11) DEFAULT NULL COMMENT '页面数量',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除 0 否 1 是',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_shop_id` (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='页面分组表';

-- ----------------------------
-- Table structure for shop_page_ref
-- ----------------------------
DROP TABLE IF EXISTS `shop_page_ref`;
CREATE TABLE `shop_page_ref` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `page_id` bigint(20) unsigned DEFAULT NULL COMMENT '引用页面id',
  `compose_id` bigint(20) unsigned DEFAULT NULL COMMENT '使用的组件id',
  `type` varchar(20) DEFAULT NULL COMMENT '使用类型',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='页面引用记录表';

-- ----------------------------
-- Table structure for shop_qualification
-- ----------------------------
DROP TABLE IF EXISTS `shop_qualification`;
CREATE TABLE `shop_qualification` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `shop_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '店铺id',
  `name` varchar(200) DEFAULT NULL COMMENT '资质名称',
  `images` varchar(2000) DEFAULT NULL COMMENT '资质图片',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除 0 否 1 是',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_shop_id` (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='店铺资质表';

-- ----------------------------
-- Table structure for shop_trade_time_setup
-- ----------------------------
DROP TABLE IF EXISTS `shop_trade_time_setup`;
CREATE TABLE `shop_trade_time_setup` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `shop_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '店铺id',
  `order_cancel` int(10) DEFAULT NULL COMMENT '待付款订单取消时间',
  `confirm_receipt` int(10) DEFAULT NULL COMMENT '发货后自动确认收货时间',
  `finish_praise` int(10) DEFAULT NULL COMMENT '交易完成后默认好评时间',
  `append_comment` int(10) DEFAULT NULL COMMENT '评价后可追评时间',
  `return_agree` int(10) DEFAULT NULL COMMENT '商家超时未处理退款申请，系统自动同意时间',
  `return_sendback_close` int(10) DEFAULT NULL COMMENT '买家超时未回寄商品退款，系统自动关闭时间',
  `return_modify_close` int(10) DEFAULT NULL COMMENT '买家超时未修改退款申请，系统自动关闭时间',
  `return_receipt` int(10) DEFAULT NULL COMMENT '商家超时未确认收货，系统自动退款时间',
  `canot_request_return` int(10) DEFAULT NULL COMMENT '订单完成后不能申请维权时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_shop_id` (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='店铺交易时间设置表';

-- ----------------------------
-- Table structure for shop_video_group
-- ----------------------------
DROP TABLE IF EXISTS `shop_video_group`;
CREATE TABLE `shop_video_group` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(80) NOT NULL COMMENT '分组名',
  `shop_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '店铺id',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除 0 否 1 是',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_shop_id` (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频分组表';

-- ----------------------------
-- Table structure for shop_video_lib
-- ----------------------------
DROP TABLE IF EXISTS `shop_video_lib`;
CREATE TABLE `shop_video_lib` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `shop_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '店铺id',
  `group_id` bigint(20) unsigned DEFAULT NULL COMMENT '分组id',
  `name` varchar(300) DEFAULT NULL COMMENT '名称',
  `url` varchar(1000) DEFAULT NULL COMMENT '地址',
  `format` varchar(20) DEFAULT NULL COMMENT '视频格式',
  `filename` varchar(300) DEFAULT NULL COMMENT '文件名称',
  `fist_img` varchar(1000) DEFAULT NULL COMMENT '首图',
  `proportion` decimal(10,1) DEFAULT '0.0' COMMENT '比例',
  `size` int(11) DEFAULT NULL COMMENT '大小',
  `length` int(11) DEFAULT NULL COMMENT '长',
  `width` int(11) DEFAULT NULL COMMENT '宽',
  `duration` int(11) DEFAULT NULL COMMENT '时长 秒',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除 0 否 1 是',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_shop_id` (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商铺视频库表';

SET FOREIGN_KEY_CHECKS = 1;
