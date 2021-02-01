
DROP TABLE IF EXISTS `pay_order`;
CREATE TABLE `pay_order` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `pay_content` varchar(256) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '支付内容',
  `out_trade_no` varchar(40) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商户订单号',
  `pay_type` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '支付类型',
  `gateway_no` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '网关流水号',
  `origin_pay_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '原支付单编码',
  `channel` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '渠道类型',
  `sub_channel` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '详细渠道',
  `currency_code` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '币种编码',
  `origin_amount` decimal(15,6) DEFAULT NULL COMMENT '应付金额',
  `actual_amount` decimal(15,6) DEFAULT NULL COMMENT '实付金额',
  `pay_service_fee` decimal(15,6) DEFAULT NULL COMMENT '支付服务费',
  `remark` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `pay_status` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '支付状态',
  `custom_json` varchar(2000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '自定义扩展字段',
  `code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '编码',
  `buyer_id` bigint(20) DEFAULT NULL COMMENT '付款人ID',
  `seller_id` bigint(20) DEFAULT NULL COMMENT '收款人ID',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `write_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_uid` bigint(20) DEFAULT NULL COMMENT '创建人',
  `write_uid` bigint(20) DEFAULT NULL COMMENT '更新人',
  `is_deleted` bigint(20) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_pay_order_code` (`code`,`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='支付订单';


DROP TABLE IF EXISTS `pay_log`;
CREATE TABLE `pay_log` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `pay_order_no` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '支付单编码',
  `pay_type` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '支付类型',
  `method` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '接口方法名称',
  `request` text COLLATE utf8mb4_bin COMMENT '请求参数',
  `response` text COLLATE utf8mb4_bin COMMENT '返回参数',
  `success` tinyint(1) DEFAULT NULL COMMENT '是否成功',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `write_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_uid` bigint(20) DEFAULT NULL COMMENT '创建人',
  `write_uid` bigint(20) DEFAULT NULL COMMENT '更新人',
  `is_deleted` bigint(20) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='支付日志';

DROP TABLE IF EXISTS `payment_channel`;
CREATE TABLE `payment_channel` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `url` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '支付跳转链接',
  `icon` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'icon',
  `type` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '支付渠道',
  `active` tinyint(1) DEFAULT NULL COMMENT '是否生效',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `write_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_uid` bigint(20) DEFAULT NULL COMMENT '创建人',
  `write_uid` bigint(20) DEFAULT NULL COMMENT '更新人',
  `is_deleted` bigint(20) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='支付渠道';
