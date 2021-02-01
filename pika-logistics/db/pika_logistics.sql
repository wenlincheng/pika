DROP TABLE IF EXISTS `free_shipping_setting`;
CREATE TABLE `free_shipping_setting` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `free_shipping_rule` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '包邮条件',
  `quantity` int(11) DEFAULT NULL COMMENT '数量',
  `amount` decimal(15,6) DEFAULT NULL COMMENT '金额',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `write_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_uid` bigint(20) DEFAULT NULL COMMENT '创建人',
  `write_uid` bigint(20) DEFAULT NULL COMMENT '更新人',
  `is_deleted` bigint(20) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='运费模板包邮设置';

DROP TABLE IF EXISTS `logistics_provider`;
CREATE TABLE `logistics_provider` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `name` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '名称',
  `contact` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系人',
  `contact_phone` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系人电话',
  `remark` text COLLATE utf8mb4_bin COMMENT '备注',
  `order` int(11) DEFAULT NULL COMMENT '优先级',
  `data_status` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '数据状态',
  `logo_url` varchar(512) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'logo图片路径',
  `code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '编码',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `write_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_uid` bigint(20) DEFAULT NULL COMMENT '创建人',
  `write_uid` bigint(20) DEFAULT NULL COMMENT '更新人',
  `is_deleted` bigint(20) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_logistics_provider_code` (`code`,`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='物流服务商';

BEGIN;
INSERT INTO `logistics_provider` VALUES (145318530538013186, '申通快递', NULL, NULL, NULL, 0, 'ENABLED', NULL, 'STO', '2020-12-15 14:31:56', '2020-12-15 14:31:56', NULL, NULL, 0);
INSERT INTO `logistics_provider` VALUES (145318530538013187, '圆通快递', NULL, NULL, NULL, 1, 'ENABLED', NULL, 'YTO', '2020-12-15 14:31:56', '2020-12-15 14:31:56', NULL, NULL, 0);
INSERT INTO `logistics_provider` VALUES (145318530538013188, '中通快递', NULL, NULL, NULL, 2, 'ENABLED', NULL, 'ZTO', '2020-12-15 14:31:56', '2020-12-15 14:31:56', NULL, NULL, 0);
INSERT INTO `logistics_provider` VALUES (145318530538013189, '韵达快递', NULL, NULL, NULL, 3, 'ENABLED', NULL, 'YUNDA', '2020-12-15 14:31:56', '2020-12-15 14:31:56', NULL, NULL, 0);
INSERT INTO `logistics_provider` VALUES (145318530538013190, '顺丰速运', NULL, NULL, NULL, 4, 'ENABLED', NULL, 'SF', '2020-12-15 14:31:56', '2020-12-15 14:31:56', NULL, NULL, 0);
INSERT INTO `logistics_provider` VALUES (145318530538013191, '天天快递', NULL, NULL, NULL, 5, 'ENABLED', NULL, 'TTKDEX', '2020-12-15 14:31:56', '2020-12-15 14:31:56', NULL, NULL, 0);
INSERT INTO `logistics_provider` VALUES (145318530538013192, '百世快递', NULL, NULL, NULL, 6, 'ENABLED', NULL, 'BEST', '2020-12-15 14:31:56', '2020-12-15 14:31:56', NULL, NULL, 0);
INSERT INTO `logistics_provider` VALUES (145318530538013193, 'EMS', NULL, NULL, NULL, 7, 'ENABLED', NULL, 'EMS', '2020-12-15 14:31:56', '2020-12-15 14:31:56', NULL, NULL, 0);
COMMIT;

DROP TABLE IF EXISTS `logistics_track`;
CREATE TABLE `logistics_track` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `delivery_order_id` bigint(20) DEFAULT NULL COMMENT '履约单ID',
  `delivery_order_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '履约单CODE',
  `tms_order_id` bigint(20) DEFAULT NULL COMMENT '配运单ID',
  `tms_order_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '配运单号',
  `way_bill_no` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '物流单号',
  `logistics_name` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '物流公司',
  `log_code` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '物流日志编码',
  `short_status` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '物流状态',
  `content` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '物流描述',
  `track_date` datetime DEFAULT NULL COMMENT '追踪时间',
  `seller_remark` varchar(128) COLLATE utf8mb4_bin COMMENT '商家备注',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `write_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_uid` bigint(20) DEFAULT NULL COMMENT '创建人',
  `write_uid` bigint(20) DEFAULT NULL COMMENT '更新人',
  `is_deleted` bigint(20) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='物流轨迹追踪';

DROP TABLE IF EXISTS `shipping_fee_template`;
CREATE TABLE `shipping_fee_template` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `name` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '模板名称',
  `delivery_date_range` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '下单后发货时间范围',
  `is_free_shipping` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否包邮',
  `price_by_dimension` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '计价维度',
  `data_status` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '运费模板状态',
  `delivery_address_id` bigint(20) DEFAULT NULL COMMENT '发货地址ID',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `write_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_uid` bigint(20) DEFAULT NULL COMMENT '创建人',
  `write_uid` bigint(20) DEFAULT NULL COMMENT '更新人',
  `is_deleted` bigint(20) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='运费模板';

DROP TABLE IF EXISTS `shipping_fee`;
CREATE TABLE `shipping_fee` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `shipping_fee_template_id` bigint(20) DEFAULT NULL COMMENT '模版Id',
  `region_type` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '国内/外',
  `logistics_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '物流商编码',
  `init_num` int(11) DEFAULT NULL COMMENT '初始值',
  `init_amount` decimal(15,6) DEFAULT NULL COMMENT '初始费用',
  `increment` int(11) DEFAULT NULL COMMENT '增量',
  `increment_amount` decimal(15,6) DEFAULT NULL COMMENT '增量费用',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `write_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_uid` bigint(20) DEFAULT NULL COMMENT '创建人',
  `write_uid` bigint(20) DEFAULT NULL COMMENT '更新人',
  `is_deleted` bigint(20) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='运费';

DROP TABLE IF EXISTS `tms_order_detail`;
CREATE TABLE `tms_order_detail` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `tms_order_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '配运单CODE',
  `sign_status` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '签收状态',
  `signed_time` datetime DEFAULT NULL COMMENT '签收时间',
  `inv_sku_id` bigint(20) DEFAULT NULL COMMENT '产品 SKUID',
  `sku_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '产品编码',
  `sku_name` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '产品编码',
  `sku_img` varchar(2048) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '产品图片',
  `need_sign_num` int(11) DEFAULT NULL COMMENT '签收数量',
  `signed_num` int(11) DEFAULT NULL COMMENT '实际签收数量',
  `remark` varchar(128) COLLATE utf8mb4_bin COMMENT '备注',
  `code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '编码',
  `tms_order_id` bigint(20) DEFAULT NULL COMMENT '配运单ID',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `write_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_uid` bigint(20) DEFAULT NULL COMMENT '创建人',
  `write_uid` bigint(20) DEFAULT NULL COMMENT '更新人',
  `is_deleted` bigint(20) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tms_order_detail_code` (`code`,`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='配运单明细(TMS Order Detail)';

DROP TABLE IF EXISTS `tms_order_rel_delivery_order`;
CREATE TABLE `tms_order_rel_delivery_order` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `tms_order_id` bigint(20) DEFAULT NULL COMMENT '配运单id',
  `tms_order_code` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '配运单号',
  `delivery_order_id` bigint(20) DEFAULT NULL COMMENT '履约单id',
  `delivery_order_code` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '履约单号',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `write_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_uid` bigint(20) DEFAULT NULL COMMENT '创建人',
  `write_uid` bigint(20) DEFAULT NULL COMMENT '更新人',
  `is_deleted` bigint(20) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `idx_tms_order_rel_delivery_order_delivery_order_code` (`delivery_order_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='配运单关联履约单';

DROP TABLE IF EXISTS `tms_order_rel_trade_order`;
CREATE TABLE `tms_order_rel_trade_order` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `tms_order_id` bigint(20) DEFAULT NULL COMMENT '配运单id',
  `tms_order_code` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '配运单号',
  `trade_order_id` bigint(20) DEFAULT NULL COMMENT '交易订单id',
  `trade_order_code` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '交易订单号',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `write_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_uid` bigint(20) DEFAULT NULL COMMENT '创建人',
  `write_uid` bigint(20) DEFAULT NULL COMMENT '更新人',
  `is_deleted` bigint(20) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `idx_tms_order_rel_trade_order_trade_order_code` (`trade_order_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='配运单关联交易订单';


DROP TABLE IF EXISTS `tms_order`;
CREATE TABLE `tms_order` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `delivery_order_codes` varchar(512) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '履约单号列表',
  `trade_order_codes` varchar(512) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '交易订单号列表',
  `notify_order_codes` varchar(512) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '发货通知单编码',
  `logistics_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '物流商编码',
  `logistics_name` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '物流商',
  `waybill_no` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '物流单号',
  `pre_shipping_fee` decimal(15,6) DEFAULT NULL COMMENT '预计物流费用',
  `shipping_fee` decimal(15,6) DEFAULT NULL COMMENT '订单运费',
  `consignee_name` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '收货人名称',
  `consignee_phone` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '收货人手机',
  `consignee_full_address` varchar(256) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '收货人地址',
  `currency_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '币种编码',
  `out_id` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '外部id',
  `status` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '状态',
  `code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '编码',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `write_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_uid` bigint(20) DEFAULT NULL COMMENT '创建人',
  `write_uid` bigint(20) DEFAULT NULL COMMENT '更新人',
  `is_deleted` bigint(20) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tms_order_code` (`code`,`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='配运单(TMS Order)';

DROP TABLE IF EXISTS `wms_notify_order_detail`;
CREATE TABLE `wms_notify_order_detail` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `wms_notify_order_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '发货通知单CODE',
  `delivery_order_detail_id` bigint(20) DEFAULT NULL COMMENT '履约单明细',
  `delivery_order_detail_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '履约单明细CODE',
  `trade_order_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '订单编码',
  `trade_order_detail_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '履约单明细编码',
  `alloc_status` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '配货状态',
  `alloc_time` datetime DEFAULT NULL COMMENT '配货完成时间',
  `inv_sku_id` bigint(20) DEFAULT NULL COMMENT '产品Sku ID',
  `sku_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '产品编码',
  `sku_name` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '产品名称',
  `sku_bar_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '产品条码',
  `stand_unit_price` decimal(15,6) DEFAULT NULL COMMENT '标准单价',
  `need_alloc_num` int(11) DEFAULT NULL COMMENT '配货数量',
  `alloced_num` int(11) DEFAULT NULL COMMENT '实际配货数量',
  `currency_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '币种编码',
  `remark` varchar(128) COLLATE utf8mb4_bin COMMENT '备注',
  `code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '编码',
  `wms_notify_order_id` bigint(20) DEFAULT NULL COMMENT 'ID',
  `inv_unit_id` bigint(20) DEFAULT NULL COMMENT '单位ID',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `write_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_uid` bigint(20) DEFAULT NULL COMMENT '创建人',
  `write_uid` bigint(20) DEFAULT NULL COMMENT '更新人',
  `is_deleted` bigint(20) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_wms_notify_order_detail_code` (`code`,`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='发货通知单/配货单明细';

DROP TABLE IF EXISTS `wms_notify_order`;
CREATE TABLE `wms_notify_order` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `delivery_order_codes` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '履约单编码,逗号分隔',
  `trade_order_codes` varchar(512) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '交易订单编码,逗号分隔',
  `warehouse_id` bigint(20) DEFAULT NULL COMMENT '仓库信息',
  `logical_warehouse_id` bigint(20) DEFAULT NULL COMMENT '库区',
  `logistics_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '物流商编码',
  `pre_shipping_fee` decimal(15,6) DEFAULT NULL COMMENT '预计物流费用',
  `shipping_fee` decimal(15,6) DEFAULT NULL COMMENT '订单运费',
  `delivery_time` datetime DEFAULT NULL COMMENT '发货时间',
  `waybill_no` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '运单号',
  `buyer_partner_id` bigint(20) DEFAULT NULL COMMENT '买家ID',
  `buyer_partner_name` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '买家名称',
  `buyer_remark` text COLLATE utf8mb4_bin COMMENT '买家备注',
  `seller_remark` text COLLATE utf8mb4_bin COMMENT '卖家备注',
  `currency_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '币种编码',
  `audit_status` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '审核状态',
  `out_id` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '外部ID',
  `code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '编码',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `write_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_uid` bigint(20) DEFAULT NULL COMMENT '创建人',
  `write_uid` bigint(20) DEFAULT NULL COMMENT '更新人',
  `is_deleted` bigint(20) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_wms_notify_order_code` (`code`,`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='发货通知单/配货单';