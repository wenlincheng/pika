SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXIS `cart`;
CREATE TABLE `cart` (
  `id` bigint NOT NULL COMMENT 'ID',
  `buyer_id` bigint DEFAULT NULL COMMENT '买家id',
  `seller_id` bigint DEFAULT NULL COMMENT '卖家id',
  `shop_code` varchar(128) DEFAULT NULL COMMENT '店铺code',
  `cart_type` varchar(32) DEFAULT NULL COMMENT '购物车类型',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人',
  `is_deleted` bigint DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='购物车';

-- ----------------------------
-- Table structure for cart_item_info
-- ----------------------------
DROP TABLE IF EXIS `cart_item_info`;
CREATE TABLE `cart_item_info` (
  `id` bigint NOT NULL COMMENT 'ID',
  `buyer_id` bigint DEFAULT NULL COMMENT '买家id',
  `seller_id` bigint DEFAULT NULL COMMENT '卖家id',
  `shop_code` varchar(128) DEFAULT NULL COMMENT '店铺code',
  `item_num` int(11) DEFAULT NULL COMMENT '数量',
  `cart_id` bigint DEFAULT NULL,
  `item_id` bigint DEFAULT NULL COMMENT '商品id',
  `sale_sku_id` bigint DEFAULT NULL COMMENT '销售sku id',
  `is_checked` tinyint(1) DEFAULT NULL COMMENT '是否选中',
  `sort_date` datetime DEFAULT NULL COMMENT '排序字段',
  `sort_by` bigint DEFAULT NULL COMMENT '排序字段',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人',
  `is_deleted` bigint DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='购物车商品';

-- ----------------------------
-- Table structure for delivery_order
-- ----------------------------
DROP TABLE IF EXIS `delivery_order`;
CREATE TABLE `delivery_order` (
  `id` bigint NOT NULL COMMENT 'ID',
  `trade_order_codes` varchar(512) DEFAULT NULL COMMENT '交易订单号列表',
  `seller_id` bigint DEFAULT NULL COMMENT '卖家id',
  `seller_name` varchar(128) DEFAULT NULL COMMENT '卖家名称',
  `buyer_id` bigint DEFAULT NULL COMMENT '买家id',
  `buyer_name` varchar(128) DEFAULT NULL COMMENT '卖家名称',
  `shop_code` varchar(30) DEFAULT NULL COMMENT '店铺code',
  `shop_name` varchar(128) DEFAULT NULL COMMENT '店铺名称',
  `buyer_info` varchar(512) DEFAULT NULL COMMENT '买家信息',
  `consignee_name` varchar(30) DEFAULT NULL COMMENT '收货人名称',
  `consignee_phone` varchar(20) DEFAULT NULL COMMENT '收货人手机',
  `consignee_region_code` varchar(20) DEFAULT NULL COMMENT '收货地址末级地区码',
  `consignee_full_address` varchar(256) DEFAULT NULL COMMENT '收货人地址',
  `order_time` datetime DEFAULT NULL COMMENT '下单时间',
  `order_type` varchar(32) DEFAULT NULL COMMENT '订单类型',
  `order_status` varchar(32) DEFAULT NULL COMMENT '订单状态',
  `origin_amount` decimal(15,6) DEFAULT NULL COMMENT '订单总额（不包含优惠），即：实际总额 + 优惠金额',
  `actual_amount` decimal(15,6) DEFAULT NULL COMMENT '成交金额，SUM(明细行成交金额) + 运费',
  `actual_goods_amount` decimal(15,6) DEFAULT NULL COMMENT 'SUM(明细行货款金额)',
  `discount_amount` decimal(15,6) DEFAULT NULL COMMENT 'SUM(明细行优惠金额)，包含商品优惠，订单优惠的分摊和平台优惠的分摊',
  `paid_amount` decimal(15,6) DEFAULT NULL COMMENT '已支付金额，取支付信息的支付金额和',
  `origin_shipping_fee` decimal(15,6) DEFAULT NULL COMMENT '原运费',
  `actual_shipping_fee` decimal(15,6) DEFAULT NULL COMMENT '实际运费',
  `tax_fee` decimal(15,6) DEFAULT NULL COMMENT 'SUM(单品税费)',
  `refund_amount` decimal(15,6) DEFAULT NULL COMMENT '订单退款金额',
  `currency_code` varchar(10) DEFAULT NULL COMMENT '币种编码',
  `promotion_infos` varchar(1024) DEFAULT NULL COMMENT '优惠信息',
  `pay_type` varchar(32) DEFAULT NULL COMMENT '支付方式',
  `pay_status` varchar(32) DEFAULT NULL COMMENT '支付状态',
  `paid_time` datetime DEFAULT NULL COMMENT '支付时间',
  `paid_expire_time` datetime DEFAULT NULL COMMENT '支付过期时间',
  `delivery_order_status` varchar(32) DEFAULT NULL COMMENT '履约单状态',
  `delivery_time` datetime DEFAULT NULL COMMENT '发货通知单发货时回填该时间',
  `finish_time` datetime DEFAULT NULL COMMENT '交易成功或关闭的时间',
  `close_reason` varchar(32) DEFAULT NULL COMMENT '关闭原因',
  `buyer_remark` varchar(128) DEFAULT NULL COMMENT '买家备注',
  `seller_remark` varchar(1024) COMMENT '卖家备注',
  `is_reverse_pending` tinyint(1) DEFAULT NULL COMMENT '是否退款中',
  `is_overdue_reverse` tinyint(1) DEFAULT NULL COMMENT '是否开启超期售后',
  `delivery_type` varchar(32) DEFAULT NULL COMMENT '履约类型',
  `delivery_address` varchar(1024) DEFAULT NULL COMMENT '发货地址',
  `logistics_code` varchar(128) DEFAULT NULL COMMENT '物流商编码',
  `waybill_no` varchar(128) DEFAULT NULL COMMENT '运单号',
  `audit_status` varchar(32) DEFAULT NULL COMMENT '审核状态',
  `delivery_status` varchar(32) DEFAULT NULL COMMENT '发货状态',
  `alloc_time` datetime DEFAULT NULL COMMENT '配货时间',
  `code` varchar(128) DEFAULT NULL COMMENT '编码',
  `seller_remark_list` varchar(1024) DEFAULT NULL COMMENT '卖家备注',
  `invoice_status` varchar(32) DEFAULT NULL COMMENT '发票状态',
  `is_evaluated` tinyint(1) DEFAULT NULL COMMENT '是否已评价',
  `evaluation_status` varchar(32) DEFAULT NULL COMMENT '评价状态',
  `order_promotion_info` varchar(1024) COMMENT '优惠信息',
  `is_buyer_deleted` tinyint(1) DEFAULT NULL COMMENT '是否买家删除',
  `alloc_status` varchar(32) DEFAULT NULL COMMENT '配货状态',
  `option` varchar(128) DEFAULT NULL COMMENT '订单标记',
  `prepare_delivery_time` datetime DEFAULT NULL COMMENT '统计发货时效用',
  `expected_delivery_time` datetime DEFAULT NULL COMMENT '统计发货时效用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人',
  `is_deleted` bigint DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_trade_delivery_order_code` (`code`,`is_deleted`),
  KEY `idx_trade_delivery_order_waybill_no` (`waybill_no`),
  KEY `idx_ade_delivery_order_seller_id_buyer_id_order_time` (`seller_id`,`buyer_id`,`order_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='履约单';

-- ----------------------------
-- Table structure for delivery_order_detail
-- ----------------------------
DROP TABLE IF EXIS `delivery_order_detail`;
CREATE TABLE `delivery_order_detail` (
  `id` bigint NOT NULL COMMENT 'ID',
  `delivery_order_id` bigint DEFAULT NULL COMMENT '履约单id',
  `delivery_order_code` varchar(50) DEFAULT NULL COMMENT '履约单号',
  `trade_order_id` bigint DEFAULT NULL COMMENT '交易订单id',
  `trade_order_code` varchar(50) DEFAULT NULL COMMENT '交易订单号',
  `trade_order_detail_id` bigint DEFAULT NULL COMMENT '交易订单明细id',
  `trade_order_detail_code` varchar(50) DEFAULT NULL COMMENT '交易订单明细单号',
  `order_type` varchar(32) DEFAULT NULL COMMENT '订单类型',
  `trade_item_type` varchar(32) DEFAULT NULL COMMENT '交易商品类型',
  `seller_id` bigint DEFAULT NULL COMMENT '卖家id',
  `buyer_id` bigint DEFAULT NULL COMMENT '买家id',
  `shop_code` varchar(50) DEFAULT NULL COMMENT '店铺code',
  `item_id` bigint DEFAULT NULL COMMENT '商品id',
  `item_name` varchar(128) DEFAULT NULL COMMENT '商品名称',
  `item_code` varchar(50) DEFAULT NULL COMMENT '商品编码',
  `item_bar_code` varchar(50) DEFAULT NULL COMMENT '商品条码',
  `item_spec` varchar(128) DEFAULT NULL COMMENT '商品规格',
  `category_code` varchar(50) DEFAULT NULL COMMENT '品类code',
  `sale_sku_info` varchar(512) DEFAULT NULL COMMENT '记录下单时的sku关键信息',
  `sale_sku_code` varchar(50) DEFAULT NULL COMMENT '销售sku编码',
  `sku_bar_code` varchar(50) DEFAULT NULL COMMENT '销售sku条码',
  `sku_short_code` varchar(50) DEFAULT NULL COMMENT '销售sku短码',
  `tax_rate` int(11) DEFAULT NULL COMMENT '商品税率',
  `tax_code` varchar(50) DEFAULT NULL COMMENT '税收编码',
  `item_num` bigint DEFAULT NULL COMMENT '购买数量',
  `promotion_infos` varchar(1024) DEFAULT NULL COMMENT '优惠信息',
  `item_unit_price` decimal(15,6) DEFAULT NULL COMMENT '未优惠前的单价',
  `discount_unit_price` decimal(15,6) DEFAULT NULL COMMENT '最终成交的单价、折后单价',
  `actual_goods_amount` decimal(15,6) DEFAULT NULL COMMENT '商品单价itemUnitPrice * 数量',
  `origin_amount` decimal(15,6) DEFAULT NULL COMMENT 'actualGoodsAmount + taxFee',
  `discount_amount` decimal(15,6) DEFAULT NULL COMMENT '折扣优惠 + 满减优惠，具体查看优惠信息promotionInfos',
  `actual_amount` decimal(15,6) DEFAULT NULL COMMENT '成交单价actualUnitPrice * 数量 + taxFee',
  `tax_fee` decimal(15,6) DEFAULT NULL COMMENT '商品成交单价itemUnitPrice * 商品税率',
  `currency_code` varchar(20) DEFAULT NULL COMMENT '币种编码',
  `undelivered_num` int(11) DEFAULT NULL COMMENT '未发货数量',
  `delivered_num` int(11) DEFAULT NULL COMMENT '已发货数量',
  `return_num` int(11) DEFAULT NULL COMMENT '退货数量',
  `return_amount` decimal(15,6) DEFAULT NULL COMMENT '退款金额',
  `order_status` varchar(32) DEFAULT NULL COMMENT '订单状态',
  `pay_status` varchar(32) DEFAULT NULL COMMENT '支付状态',
  `paid_time` datetime DEFAULT NULL COMMENT '支付时间',
  `delivery_order_status` varchar(32) DEFAULT NULL COMMENT '履约单状态',
  `delivery_time` datetime DEFAULT NULL COMMENT '发货时间',
  `finish_time` datetime DEFAULT NULL COMMENT '交易成功或关闭的时间',
  `close_reason` varchar(32) DEFAULT NULL COMMENT '关闭原因',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  `is_gifted` tinyint(1) DEFAULT NULL COMMENT '是否赠品',
  `is_entity` tinyint(1) DEFAULT NULL COMMENT '是否是实物商品',
  `out_id` varchar(50) DEFAULT NULL COMMENT '外部id',
  `delivery_type` varchar(32) DEFAULT NULL COMMENT '履约类型',
  `delivery_status` varchar(32) DEFAULT NULL COMMENT '发货状态',
  `sku_code` varchar(50) DEFAULT NULL COMMENT '产品编码',
  `need_deliver_num` int(11) DEFAULT NULL COMMENT '待发货数量',
  `allocated_num` int(11) DEFAULT NULL COMMENT '当前配货数量',
  `delivered_time` datetime DEFAULT NULL COMMENT '配送完成时间',
  `code` varchar(128) DEFAULT NULL COMMENT '编码',
  `sale_sku_id` bigint DEFAULT NULL COMMENT '销售sku id',
  `sku_unit_id` bigint DEFAULT NULL COMMENT '交易单位id',
  `inv_sku_id` bigint DEFAULT NULL COMMENT '库存sku id',
  `sku_unit_code` varchar(10) DEFAULT NULL COMMENT '交易单位',
  `reverse_status` varchar(32) DEFAULT NULL COMMENT '售后状态',
  `is_evaluated` tinyint(1) DEFAULT NULL COMMENT '是否已评价',
  `evaluation_status` varchar(32) DEFAULT NULL COMMENT '评价状态',
  `order_promotion_info` varchar(1024) COMMENT '优惠信息',
  `actual_unit_price` decimal(15,6) DEFAULT NULL COMMENT '最终成交的单价、折后单价',
  `is_buyer_deleted` tinyint(1) DEFAULT NULL COMMENT '是否买家删除',
  `alloc_status` varchar(32) DEFAULT NULL COMMENT '配货状态',
  `sale_unit_id` bigint DEFAULT NULL,
  `item_face_price` decimal(15,6) DEFAULT NULL COMMENT '商品页面价',
  `option` varchar(128) DEFAULT NULL COMMENT '订单标记',
  `sort_order` bigint DEFAULT NULL COMMENT '在打包一口价的情况下，需要安装打包一口价进行分组排序',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人',
  `is_deleted` bigint DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_trade_delivery_order_detail_code` (`code`,`is_deleted`),
  UNIQUE KEY `uk_trade_delivery_order_detail_trade_order_detail_id` (`trade_order_detail_id`,`is_deleted`),
  KEY `idx_a_trade_delivery_order_detail_seller_id_buyer_id` (`seller_id`,`buyer_id`),
  KEY `idx_trade_delivery_order_detail_trade_order_id` (`trade_order_id`),
  KEY `idx_trade_delivery_order_detail_trade_order_code` (`trade_order_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='卖家履约单明细';

-- ----------------------------
-- Table structure for delivery_order_rel_trade_order
-- ----------------------------
DROP TABLE IF EXIS `delivery_order_rel_trade_order`;
CREATE TABLE `delivery_order_rel_trade_order` (
  `id` bigint NOT NULL COMMENT 'ID',
  `delivery_order_id` bigint DEFAULT NULL COMMENT '履约单id',
  `delivery_order_code` varchar(50) DEFAULT NULL COMMENT '履约单号',
  `trade_order_id` bigint DEFAULT NULL COMMENT '交易订单id',
  `trade_order_code` varchar(50) DEFAULT NULL COMMENT '交易订单号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人',
  `is_deleted` bigint DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `idx_trade_delivery_order_rel_trade_order_delivery_order_id` (`delivery_order_id`),
  KEY `idx_trade_delivery_order_rel_trade_order_trade_order_id` (`trade_order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='履约单关联交易订单';

-- ----------------------------
-- Table structure for dispute_comm_record
-- ----------------------------
DROP TABLE IF EXIS `dispute_comm_record`;
CREATE TABLE `dispute_comm_record` (
  `id` bigint NOT NULL COMMENT 'ID',
  `reverse_order_id` bigint DEFAULT NULL COMMENT '售后单id',
  `platform_user_id` bigint DEFAULT NULL COMMENT '用户id',
  `platform_user_name` varchar(128) DEFAULT NULL COMMENT '用户名称',
  `user_group` varchar(32) DEFAULT NULL COMMENT '沟通对象',
  `communicate_result` varchar(32) DEFAULT NULL COMMENT '沟通结果',
  `file_urls` varchar(2000) DEFAULT NULL COMMENT '图片url，逗号分隔',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  `communicate_result_str` varchar(128) DEFAULT NULL COMMENT '沟通结果文本',
  `avatar_url` varchar(128) DEFAULT NULL COMMENT '头像',
  `buyer_phone` varchar(128) DEFAULT NULL COMMENT '买家联系电话',
  `seller_phone` varchar(128) DEFAULT NULL COMMENT '商家联系电话',
  `is_received` tinyint(1) DEFAULT NULL COMMENT '是否收到货',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人',
  `is_deleted` bigint DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='纠纷沟通记录';

-- ----------------------------
-- Table structure for negotiate_config
-- ----------------------------
DROP TABLE IF EXIS `negotiate_config`;
CREATE TABLE `negotiate_config` (
  `id` bigint NOT NULL COMMENT 'ID',
  `index_num` int DEFAULT NULL COMMENT '编号',
  `operate_name` varchar(128) DEFAULT NULL COMMENT '操作名称',
  `brief` varchar(128) DEFAULT NULL COMMENT '内容',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人',
  `is_deleted` bigint DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='协商内容配置';

-- ----------------------------
-- Table structure for negotiate_record
-- ----------------------------
DROP TABLE IF EXIS `negotiate_record`;
CREATE TABLE `negotiate_record` (
  `id` bigint NOT NULL COMMENT 'ID',
  `reverse_order_id` bigint DEFAULT NULL COMMENT '售后单id',
  `user_group` varchar(32) DEFAULT NULL COMMENT '所在群体',
  `user_id` bigint DEFAULT NULL COMMENT '用户id',
  `logistics_name` varchar(128) DEFAULT NULL COMMENT '物流名称',
  `waybill_no` varchar(128) DEFAULT NULL COMMENT '物流编号',
  `return_reason_enum` varchar(32) DEFAULT NULL COMMENT '退货原因',
  `refund_reason_enum` varchar(32) DEFAULT NULL COMMENT '退款原因',
  `reverse_type` varchar(32) DEFAULT NULL COMMENT '售后类型',
  `reverse_item_num` int(11) DEFAULT NULL COMMENT '退货数量',
  `phone` varchar(128) DEFAULT NULL COMMENT '退款联系电话',
  `is_received` tinyint(1) DEFAULT NULL COMMENT '是否收到货',
  `refund_amount` decimal(15,6) DEFAULT NULL COMMENT '退款金额',
  `file_urls` varchar(2000) DEFAULT NULL COMMENT '图片url 逗号分隔',
  `remark` varchar(1000) DEFAULT NULL COMMENT '备注',
  `negotiate_config_id` bigint DEFAULT NULL,
  `buyer_name` varchar(128) DEFAULT NULL COMMENT '买家名称',
  `avatar_url` varchar(128) DEFAULT NULL COMMENT '买家头像',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人',
  `is_deleted` bigint DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='协商记录';

-- ----------------------------
-- Table structure for refund_order
-- ----------------------------
DROP TABLE IF EXIS `refund_order`;
CREATE TABLE `refund_order` (
  `id` bigint NOT NULL COMMENT 'ID',
  `buyer_id` bigint DEFAULT NULL COMMENT '买家id',
  `buyer_name` varchar(128) DEFAULT NULL COMMENT '买家名称',
  `seller_id` bigint DEFAULT NULL COMMENT '卖家id',
  `seller_name` varchar(128) DEFAULT NULL COMMENT '卖家名称',
  `reverse_order_id` bigint DEFAULT NULL COMMENT '售后单',
  `reverse_order_code` varchar(128) DEFAULT NULL COMMENT '售后单编号',
  `trade_order_id` bigint DEFAULT NULL COMMENT '交易订单',
  `trade_order_code` varchar(128) DEFAULT NULL COMMENT '交易订单编号',
  `trade_order_detail_id` bigint DEFAULT NULL COMMENT '交易子订单',
  `trade_order_detail_code` varchar(128) DEFAULT NULL COMMENT '交易子订单编号',
  `refund_amount` decimal(15,6) DEFAULT NULL COMMENT '退款金额',
  `refund_shipping_fee` decimal(15,6) DEFAULT NULL COMMENT '实退运费',
  `currency_code` varchar(128) DEFAULT NULL COMMENT '币种编码',
  `refund_time` datetime DEFAULT NULL COMMENT '退款时间',
  `pay_order_code` varchar(40) DEFAULT NULL COMMENT '支付单号',
  `refund_status` varchar(32) DEFAULT NULL COMMENT '退款状态',
  `process_status` varchar(32) DEFAULT NULL COMMENT '处理状态',
  `shop_code` varchar(128) DEFAULT NULL COMMENT '店铺code',
  `code` varchar(128) DEFAULT NULL COMMENT '编码',
  `contact_phone` varchar(128) DEFAULT NULL COMMENT '买家联系电话',
  `contact_email` varchar(128) DEFAULT NULL COMMENT '买家联系邮箱',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人',
  `is_deleted` bigint DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_trade_refund_order_code` (`code`,`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='退款单';

-- ----------------------------
-- Table structure for return_order
-- ----------------------------
DROP TABLE IF EXIS `return_order`;
CREATE TABLE `return_order` (
  `id` bigint NOT NULL COMMENT 'ID',
  `buyer_id` bigint DEFAULT NULL COMMENT '买家id',
  `seller_id` bigint DEFAULT NULL COMMENT '卖家id',
  `reverse_order_id` bigint DEFAULT NULL COMMENT '售后单id',
  `reverse_order_code` varchar(128) DEFAULT NULL COMMENT '售后单编号',
  `trade_order_id` bigint DEFAULT NULL COMMENT '交易订单id',
  `trade_order_code` varchar(128) DEFAULT NULL COMMENT '交易订单编号',
  `in_ware_status` varchar(32) DEFAULT NULL COMMENT '入库状态',
  `in_ware_mark` varchar(32) DEFAULT NULL COMMENT '入库标识',
  `return_trade_order_code` varchar(128) DEFAULT NULL COMMENT '销售单号',
  `return_way_bill_no` varchar(128) DEFAULT NULL COMMENT '退回物流单号',
  `return_logistics` varchar(128) DEFAULT NULL COMMENT '退回物流企业',
  `return_to_ware` varchar(128) DEFAULT NULL COMMENT '退入仓库',
  `shop_code` varchar(128) DEFAULT NULL COMMENT '店铺code',
  `contact_phone` varchar(128) DEFAULT NULL COMMENT '买家联系电话',
  `biz_date` datetime DEFAULT NULL COMMENT '业务时间',
  `code` varchar(128) DEFAULT NULL COMMENT '编码',
  `return_to_ware_id` bigint DEFAULT NULL,
  `contact_email` varchar(128) DEFAULT NULL COMMENT '买家联系邮箱',
  `last_in_stock_time` datetime DEFAULT NULL COMMENT '最后入库时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人',
  `is_deleted` bigint DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_trade_return_order_code` (`code`,`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='退货单';

-- ----------------------------
-- Table structure for reverse_order
-- ----------------------------
DROP TABLE IF EXIS `reverse_order`;
CREATE TABLE `reverse_order` (
  `id` bigint NOT NULL COMMENT 'ID',
  `buyer_id` bigint DEFAULT NULL COMMENT '买家id',
  `seller_id` bigint DEFAULT NULL COMMENT '卖家id',
  `trade_order_id` bigint DEFAULT NULL COMMENT '交易订单id',
  `trade_order_code` varchar(50) DEFAULT NULL COMMENT '交易订单编号',
  `order_status` varchar(32) DEFAULT NULL COMMENT '订单状态',
  `buyer_info` varchar(128) DEFAULT NULL COMMENT '买家信息',
  `delivery_order_id` bigint DEFAULT NULL COMMENT '履约单id',
  `delivery_order_code` varchar(50) DEFAULT NULL COMMENT '履约单编码',
  `reverse_type` varchar(32) DEFAULT NULL COMMENT '售后类型',
  `contact_phone` varchar(30) DEFAULT NULL COMMENT '买家联系电话',
  `contact_email` varchar(40) DEFAULT NULL COMMENT '买家联系邮箱',
  `platform_return_msg` varchar(128) DEFAULT NULL COMMENT '平台返回信息',
  `audit_status` varchar(32) DEFAULT NULL COMMENT '审核状态',
  `apply_time` datetime DEFAULT NULL COMMENT '申请时间',
  `audit_date` datetime DEFAULT NULL COMMENT '审核时间',
  `auditor_id` bigint DEFAULT NULL COMMENT '审核人',
  `process_status` varchar(32) DEFAULT NULL COMMENT '处理状态',
  `is_dispute` tinyint(1) DEFAULT NULL COMMENT '是否纠纷',
  `dispute_status` varchar(32) DEFAULT NULL COMMENT '纠纷状态',
  `merchant_reverse_status` varchar(32) DEFAULT NULL COMMENT '商家-售后单状态',
  `client_reverse_status` varchar(32) DEFAULT NULL COMMENT '买家-售后单状态',
  `currency_code` varchar(10) DEFAULT NULL COMMENT '币种编码',
  `actual_amount` decimal(15,6) DEFAULT NULL COMMENT '子单总额originAmount - 优惠总额discountAmount',
  `refund_amount` decimal(15,6) DEFAULT NULL COMMENT '应退金额',
  `actual_refund_amount` decimal(15,6) DEFAULT NULL COMMENT '实退总额',
  `refund_shipping_fee` decimal(15,6) DEFAULT NULL COMMENT '实退运费',
  `buyer_remark` varchar(256) DEFAULT NULL COMMENT '买家备注信息',
  `biz_date` datetime DEFAULT NULL COMMENT '业务时间',
  `has_return_trade` tinyint(1) DEFAULT NULL COMMENT '换货生产的换货交易订单',
  `return_trade_order_id` bigint DEFAULT NULL COMMENT '换货生产的换货交易订单id',
  `return_trade_order_code` varchar(128) DEFAULT NULL COMMENT '换货生产的换货交易订单code',
  `shop_name` varchar(128) DEFAULT NULL COMMENT '店铺名称',
  `shop_code` varchar(50) DEFAULT NULL COMMENT '店铺code',
  `return_reason` varchar(32) DEFAULT NULL COMMENT '退货原因',
  `refund_reason` varchar(32) DEFAULT NULL COMMENT '退款原因',
  `is_received` tinyint(1) DEFAULT NULL COMMENT '是否收到货',
  `reverse_item_num` int(11) DEFAULT NULL COMMENT '退款退货数量',
  `apply_count` int DEFAULT NULL COMMENT '申请次数',
  `deleted_by_buyer` tinyint(1) DEFAULT NULL COMMENT '买家是否删除',
  `file_urls` varchar(2000) DEFAULT NULL COMMENT '申请信息图片',
  `timeout` datetime DEFAULT NULL COMMENT '超时时间',
  `code` varchar(128) DEFAULT NULL COMMENT '编码',
  `consignee_name` varchar(30) DEFAULT NULL COMMENT '收货人名称',
  `consignee_phone` varchar(20) DEFAULT NULL COMMENT '收货人手机',
  `close_reason_str` varchar(128) DEFAULT NULL COMMENT '关闭原因文本',
  `close_reason` varchar(32) DEFAULT NULL COMMENT '关闭原因',
  `reject_remark` varchar(128) DEFAULT NULL COMMENT '拒绝原因',
  `return_delivery_order_id` bigint DEFAULT NULL COMMENT '逆向履约单id',
  `return_delivery_order_code` varchar(50) DEFAULT NULL COMMENT '逆向履约单编码',
  `jump2_trade_detail` bigint DEFAULT NULL COMMENT '订单详情跳转id',
  `return_time` datetime DEFAULT NULL COMMENT '寄回时间',
  `sign_time` datetime DEFAULT NULL COMMENT '签收时间',
  `refund_time` datetime DEFAULT NULL COMMENT '退款时间',
  `logistics_name` varchar(128) DEFAULT NULL COMMENT '退回物流企业名称',
  `logistics_code` varchar(128) DEFAULT NULL COMMENT '退回物流企业编码',
  `logistics_remark` varchar(128) DEFAULT NULL COMMENT '退回物流备注',
  `waybill_no` varchar(128) DEFAULT NULL COMMENT '退回运单号',
  `is_dispute_str` varchar(128) DEFAULT NULL COMMENT '是否纠纷',
  `tms_order_id` bigint DEFAULT NULL COMMENT '配运单id',
  `tms_order_code` varchar(128) DEFAULT NULL COMMENT '配运单code',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人',
  `is_deleted` bigint DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_trade_reverse_order_code` (`code`,`is_deleted`),
  KEY `idx_trade_reverse_order_buyer_id` (`buyer_id`),
  KEY `idx_trade_reverse_order_seller_id` (`seller_id`),
  KEY `idx_trade_reverse_order_trade_order_id` (`trade_order_id`),
  KEY `idx_trade_reverse_order_delivery_order_id` (`delivery_order_id`),
  KEY `idx_trade_reverse_order_waybill_no` (`waybill_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='售后单';

-- ----------------------------
-- Table structure for reverse_order_detail
-- ----------------------------
DROP TABLE IF EXIS `reverse_order_detail`;
CREATE TABLE `reverse_order_detail` (
  `id` bigint NOT NULL COMMENT 'ID',
  `reverse_order_id` bigint DEFAULT NULL COMMENT '售后单id',
  `reverse_order_code` varchar(50) DEFAULT NULL COMMENT '售后单号',
  `trade_order_id` bigint DEFAULT NULL COMMENT '交易订单id',
  `trade_order_code` varchar(50) DEFAULT NULL COMMENT '交易订单号',
  `trade_order_detail_id` bigint DEFAULT NULL COMMENT '订单明细id',
  `trade_order_detail_code` varchar(50) DEFAULT NULL COMMENT '订单明细编码',
  `reverse_type` varchar(32) DEFAULT NULL COMMENT '售后类型',
  `item_id` bigint DEFAULT NULL COMMENT '商品id',
  `item_name` varchar(128) DEFAULT NULL COMMENT '商品名称',
  `attr_label` varchar(128) DEFAULT NULL COMMENT '2XL|蓝色, 用于页面上选择属性后定位一个具体的sku',
  `sku_pic_url` varchar(512) DEFAULT NULL COMMENT '从订单快照SaleSkuInfo中获取',
  `sku_code` varchar(50) DEFAULT NULL COMMENT '产品编码',
  `sku_name` varchar(128) DEFAULT NULL COMMENT '商品名称',
  `bar_code` varchar(50) DEFAULT NULL COMMENT '产品条码',
  `item_num` int(11) DEFAULT NULL COMMENT '商品数量',
  `currency_code` varchar(20) DEFAULT NULL COMMENT '币种编码',
  `stand_unit_price` decimal(15,6) DEFAULT NULL COMMENT '标准单价',
  `cost_unit_price` decimal(15,6) DEFAULT NULL COMMENT '成本价',
  `discount_unit_price` decimal(15,6) DEFAULT NULL COMMENT '折后单价',
  `need_pay_amount` decimal(15,6) DEFAULT NULL COMMENT '应付金额',
  `discount_amount` decimal(15,6) DEFAULT NULL COMMENT '单品优惠金额',
  `actual_pay_amount` decimal(15,6) DEFAULT NULL COMMENT '实付金额',
  `item_spec` varchar(128) DEFAULT NULL COMMENT '商品规格',
  `is_gifted` tinyint(1) DEFAULT NULL COMMENT '是否赠品',
  `item_tax` int(11) DEFAULT NULL COMMENT '税率',
  `refund_unit_price` decimal(15,6) DEFAULT NULL COMMENT '退款单价',
  `reverse_item_num` int(11) DEFAULT NULL COMMENT '退款退货数量',
  `refund_amount` decimal(15,6) DEFAULT NULL COMMENT '退款金额',
  `actual_refund_amount` decimal(15,6) DEFAULT NULL COMMENT '实退总额',
  `refund_status` varchar(32) DEFAULT NULL COMMENT '退款状态',
  `return_status` varchar(32) DEFAULT NULL COMMENT '退货状态',
  `refund_order_code` varchar(50) DEFAULT NULL COMMENT '退款单编码',
  `delivery_order_code` varchar(50) DEFAULT NULL COMMENT '退货的的履约单号',
  `return_order_id` bigint DEFAULT NULL COMMENT '退货单id',
  `code` varchar(128) DEFAULT NULL COMMENT '编码',
  `inv_sku_id` bigint DEFAULT NULL,
  `units_id` bigint DEFAULT NULL,
  `refund_order_id` bigint DEFAULT NULL,
  `sale_sku_id` bigint DEFAULT NULL COMMENT '销售sku id',
  `actual_unit_price` decimal(15,6) DEFAULT NULL COMMENT '折后单价',
  `tms_order_id` bigint DEFAULT NULL COMMENT '配运单id',
  `tms_order_code` varchar(128) DEFAULT NULL COMMENT '配运单code',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人',
  `is_deleted` bigint DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_trade_reverse_order_detail_code` (`code`,`is_deleted`),
  KEY `idx_trade_reverse_order_detail_reverse_order_id` (`reverse_order_id`),
  KEY `idx_trade_reverse_order_detail_trade_order_id` (`trade_order_id`),
  KEY `idx_trade_reverse_order_detail_trade_order_detail_id` (`trade_order_detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='售后单明细';

-- ----------------------------
-- Table structure for reverse_out_detail
-- ----------------------------
DROP TABLE IF EXIS `reverse_out_detail`;
CREATE TABLE `reverse_out_detail` (
  `id` bigint NOT NULL COMMENT 'ID',
  `reverse_order_id` bigint DEFAULT NULL COMMENT '售后单id',
  `reverse_order_code` varchar(128) DEFAULT NULL COMMENT '售后单号',
  `trade_order_code` varchar(128) DEFAULT NULL COMMENT '交易订单号',
  `trade_order_detail_code` varchar(128) DEFAULT NULL COMMENT '订单明细编码',
  `reverse_type` varchar(32) DEFAULT NULL COMMENT '售后类型',
  `item_id` varchar(128) DEFAULT NULL COMMENT '商品id',
  `sku_code` varchar(128) DEFAULT NULL COMMENT '产品编码',
  `sku_name` varchar(128) DEFAULT NULL COMMENT '商品名称',
  `bar_code` varchar(128) DEFAULT NULL COMMENT '产品条码',
  `item_num` int(11) DEFAULT NULL COMMENT '商品数量',
  `market_unit_price` decimal(15,6) DEFAULT NULL COMMENT '市场建议价',
  `cost_unit_price` decimal(15,6) DEFAULT NULL COMMENT '成本价',
  `stand_unit_price` decimal(15,6) DEFAULT NULL COMMENT '标准单价',
  `need_pay_amount` decimal(15,6) DEFAULT NULL COMMENT '应付金额',
  `actual_pay_amount` decimal(15,6) DEFAULT NULL COMMENT '实付金额',
  `item_spec` varchar(128) DEFAULT NULL COMMENT '商品规格',
  `discount_amount` decimal(15,6) DEFAULT NULL COMMENT '单品优惠金额',
  `is_gifted` tinyint(1) DEFAULT NULL COMMENT '是否赠品',
  `currency_code` varchar(128) DEFAULT NULL COMMENT '币种编码',
  `item_tax` int(11) DEFAULT NULL COMMENT '税率',
  `return_order_id` bigint DEFAULT NULL COMMENT '退货单id',
  `return_order_code` varchar(128) DEFAULT NULL COMMENT '退货单编码',
  `code` varchar(128) DEFAULT NULL COMMENT '编码',
  `trade_order_id` bigint DEFAULT NULL,
  `trade_order_detail_id` bigint DEFAULT NULL,
  `inv_sku_id` bigint DEFAULT NULL,
  `units_id` bigint DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人',
  `is_deleted` bigint DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_trade_reverse_out_detail_code` (`code`,`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='换出商品';

-- ----------------------------
-- Table structure for reverse_reason
-- ----------------------------
DROP TABLE IF EXIS `reverse_reason`;
CREATE TABLE `reverse_reason` (
  `id` bigint NOT NULL COMMENT 'ID',
  `code` varchar(128) DEFAULT NULL COMMENT '编码',
  `reason` varchar(128) DEFAULT NULL COMMENT '原因',
  `is_first` tinyint(1) DEFAULT NULL COMMENT '是否是第一级',
  `depth` int DEFAULT NULL COMMENT '深度',
  `parent_id` bigint DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人',
  `is_deleted` bigint DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_trade_reverse_reason_reason` (`reason`,`is_deleted`),
  UNIQUE KEY `uk_trade_reverse_reason_code` (`code`,`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='售后单售后原因';

-- ----------------------------
-- Table structure for reverse_remark
-- ----------------------------
DROP TABLE IF EXIS `reverse_remark`;
CREATE TABLE `reverse_remark` (
  `id` bigint NOT NULL COMMENT 'ID',
  `reverse_order_id` bigint DEFAULT NULL COMMENT '售后单id',
  `user_group` varchar(32) DEFAULT NULL COMMENT '用户群体',
  `user_id` bigint DEFAULT NULL COMMENT '备注人id',
  `username` varchar(128) DEFAULT NULL COMMENT '备注人名称',
  `content` varchar(128) DEFAULT NULL COMMENT '备注内容',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人',
  `is_deleted` bigint DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='售后单卖家备注';

-- ----------------------------
-- Table structure for reverse_timeout_conf
-- ----------------------------
DROP TABLE IF EXIS `reverse_timeout_conf`;
CREATE TABLE `reverse_timeout_conf` (
  `id` bigint NOT NULL COMMENT 'ID',
  `process_status` varchar(32) DEFAULT NULL COMMENT '流程状态',
  `timeout` int DEFAULT NULL COMMENT '超时时间',
  `unit` int DEFAULT NULL COMMENT '时间单位',
  `comment` varchar(128) DEFAULT NULL COMMENT '备注说明',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人',
  `is_deleted` bigint DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='售后单明细(退货商品)';

-- ----------------------------
-- Table structure for trade_order
-- ----------------------------
DROP TABLE IF EXIS `trade_order`;
CREATE TABLE `trade_order` (
  `id` bigint NOT NULL COMMENT 'ID',
  `seller_id` bigint DEFAULT NULL COMMENT '卖家id',
  `seller_name` varchar(128) DEFAULT NULL COMMENT '卖家名称',
  `buyer_id` bigint DEFAULT NULL COMMENT '买家id',
  `buyer_name` varchar(128) DEFAULT NULL COMMENT '卖家名称',
  `shop_code` varchar(30) DEFAULT NULL COMMENT '店铺code',
  `shop_name` varchar(128) DEFAULT NULL COMMENT '店铺名称',
  `buyer_info` varchar(512) DEFAULT NULL COMMENT '买家信息',
  `consignee_name` varchar(30) DEFAULT NULL COMMENT '收货人名称',
  `consignee_phone` varchar(20) DEFAULT NULL COMMENT '收货人手机',
  `consignee_region_code` varchar(20) DEFAULT NULL COMMENT '收货地址末级地区码',
  `consignee_full_address` varchar(256) DEFAULT NULL COMMENT '收货人地址',
  `order_time` datetime DEFAULT NULL COMMENT '下单时间',
  `order_type` varchar(32) DEFAULT NULL COMMENT '订单类型',
  `order_status` varchar(32) DEFAULT NULL COMMENT '订单状态',
  `origin_amount` decimal(15,6) DEFAULT NULL COMMENT '订单总额（不包含优惠），即：实际总额 + 优惠金额',
  `actual_amount` decimal(15,6) DEFAULT NULL COMMENT '成交金额，SUM(明细行成交金额) + 运费',
  `actual_goods_amount` decimal(15,6) DEFAULT NULL COMMENT 'SUM(明细行货款金额)',
  `discount_amount` decimal(15,6) DEFAULT NULL COMMENT 'SUM(明细行优惠金额)，包含商品优惠，订单优惠的分摊和平台优惠的分摊',
  `paid_amount` decimal(15,6) DEFAULT NULL COMMENT '已支付金额，取支付信息的支付金额和',
  `origin_shipping_fee` decimal(15,6) DEFAULT NULL COMMENT '原运费',
  `actual_shipping_fee` decimal(15,6) DEFAULT NULL COMMENT '实际运费',
  `tax_fee` decimal(15,6) DEFAULT NULL COMMENT 'SUM(单品税费)',
  `refund_amount` decimal(15,6) DEFAULT NULL COMMENT '订单退款金额',
  `currency_code` varchar(10) DEFAULT NULL COMMENT '币种编码',
  `promotion_infos` varchar(1024) DEFAULT NULL COMMENT '优惠信息',
  `pay_type` varchar(32) DEFAULT NULL COMMENT '支付方式',
  `pay_status` varchar(32) DEFAULT NULL COMMENT '支付状态',
  `paid_time` datetime DEFAULT NULL COMMENT '支付时间',
  `paid_expire_time` datetime DEFAULT NULL COMMENT '也是超时关闭',
  `delivery_order_status` varchar(32) DEFAULT NULL COMMENT '履约单状态',
  `delivery_time` datetime DEFAULT NULL COMMENT '发货通知单发货时回填该时间',
  `finish_time` datetime DEFAULT NULL COMMENT '交易成功或关闭的时间',
  `close_reason` varchar(32) DEFAULT NULL COMMENT '关闭原因',
  `buyer_remark` varchar(128) DEFAULT NULL COMMENT '买家备注',
  `seller_remark` varchar(1024) COMMENT '卖家备注',
  `is_reverse_pending` tinyint(1) DEFAULT NULL COMMENT '是否退款中',
  `is_evaluated` tinyint(1) DEFAULT NULL COMMENT '是否已评价',
  `is_overdue_reverse` tinyint(1) DEFAULT NULL COMMENT '是否开启超期售后',
  `code` varchar(128) DEFAULT NULL COMMENT '编码',
  `address_id` bigint DEFAULT NULL,
  `platform_remark_list` varchar(1024) DEFAULT NULL COMMENT '平台备注',
  `invoice_status` varchar(32) DEFAULT NULL COMMENT '发票状态',
  `evaluation_status` varchar(32) DEFAULT NULL COMMENT '评价状态',
  `trade_order_group_id` bigint DEFAULT NULL COMMENT '订单组id',
  `order_promotion_info` varchar(1024) COMMENT '优惠信息',
  `is_buyer_deleted` tinyint(1) DEFAULT NULL COMMENT '是否买家删除',
  `option` varchar(128) DEFAULT NULL COMMENT '订单标记',
  `actual_face_amount` decimal(15,6) DEFAULT NULL COMMENT '确认订单页购物车合计',
  `prepare_delivery_time` datetime DEFAULT NULL COMMENT '统计发货时效用',
  `expected_delivery_time` datetime DEFAULT NULL COMMENT '统计发货时效用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人',
  `is_deleted` bigint DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_trade_trade_order_code` (`code`,`is_deleted`),
  KEY `idx_trade_trade_order_buyer_id_order_time` (`buyer_id`,`order_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='主订单';

-- ----------------------------
-- Table structure for trade_order_detail
-- ----------------------------
DROP TABLE IF EXIS `trade_order_detail`;
CREATE TABLE `trade_order_detail` (
  `id` bigint NOT NULL COMMENT 'ID',
  `trade_order_id` bigint DEFAULT NULL COMMENT '主订单id',
  `trade_order_code` varchar(50) DEFAULT NULL COMMENT '交易订单号',
  `order_type` varchar(32) DEFAULT NULL COMMENT '订单类型',
  `trade_item_type` varchar(32) DEFAULT NULL COMMENT '交易商品类型',
  `seller_id` bigint DEFAULT NULL COMMENT '卖家id',
  `buyer_id` bigint DEFAULT NULL COMMENT '买家id',
  `shop_code` varchar(30) DEFAULT NULL COMMENT '店铺code',
  `item_id` bigint DEFAULT NULL COMMENT '商品id',
  `item_name` varchar(128) DEFAULT NULL COMMENT '商品名称',
  `item_code` varchar(50) DEFAULT NULL COMMENT '商品编码',
  `item_bar_code` varchar(50) DEFAULT NULL COMMENT '商品条码',
  `item_spec` varchar(128) DEFAULT NULL COMMENT '商品规格',
  `category_code` varchar(30) DEFAULT NULL COMMENT '品类code',
  `sale_sku_info` varchar(1024) DEFAULT NULL COMMENT '记录下单时的sku关键信息',
  `sale_sku_code` varchar(50) DEFAULT NULL COMMENT '销售sku编码',
  `sku_bar_code` varchar(50) DEFAULT NULL COMMENT '销售sku 条码',
  `sku_short_code` varchar(50) DEFAULT NULL COMMENT '销售sku 短码',
  `tax_rate` int(11) DEFAULT NULL COMMENT '商品税率',
  `tax_code` varchar(40) DEFAULT NULL COMMENT '税收编码',
  `item_num` bigint DEFAULT NULL COMMENT '购买数量',
  `promotion_infos` varchar(1024) DEFAULT NULL COMMENT '优惠信息',
  `item_unit_price` decimal(15,6) DEFAULT NULL COMMENT '未优惠前的单价',
  `discount_unit_price` decimal(15,6) DEFAULT NULL COMMENT '最终成交的单价、折后单价',
  `actual_goods_amount` decimal(15,6) DEFAULT NULL COMMENT '商品单价itemUnitPrice * 数量',
  `origin_amount` decimal(15,6) DEFAULT NULL COMMENT 'actualGoodsAmount + taxFee',
  `discount_amount` decimal(15,6) DEFAULT NULL COMMENT '折扣优惠 + 满减优惠，具体查看优惠信息promotionInfos',
  `actual_amount` decimal(15,6) DEFAULT NULL COMMENT '成交单价actualUnitPrice * 数量 + taxFee',
  `tax_fee` decimal(15,6) DEFAULT NULL COMMENT '商品成交单价itemUnitPrice * 商品税率',
  `currency_code` varchar(10) DEFAULT NULL COMMENT '币种编码',
  `undelivered_num` int(11) DEFAULT NULL COMMENT '未发货数量',
  `delivered_num` int(11) DEFAULT NULL COMMENT '已发货数量',
  `return_num` int(11) DEFAULT NULL COMMENT '退货数量',
  `return_amount` decimal(15,6) DEFAULT NULL COMMENT '退款金额',
  `order_status` varchar(32) DEFAULT NULL COMMENT '订单状态',
  `pay_status` varchar(32) DEFAULT NULL COMMENT '支付状态',
  `paid_time` datetime DEFAULT NULL COMMENT '支付时间',
  `delivery_order_status` varchar(32) DEFAULT NULL COMMENT '履约单状态',
  `delivery_time` datetime DEFAULT NULL COMMENT '发货时间',
  `finish_time` datetime DEFAULT NULL COMMENT '交易成功或关闭的时间',
  `close_reason` varchar(32) DEFAULT NULL COMMENT '关闭原因',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  `is_gifted` tinyint(1) DEFAULT NULL COMMENT '是否赠品',
  `is_entity` tinyint(1) DEFAULT NULL COMMENT '是否是实物商品',
  `out_id` varchar(50) DEFAULT NULL COMMENT '外部id',
  `code` varchar(128) DEFAULT NULL COMMENT '编码',
  `sale_sku_id` bigint DEFAULT NULL COMMENT '销售sku id',
  `sku_unit_id` bigint DEFAULT NULL COMMENT '交易单位id',
  `sku_unit_code` varchar(10) DEFAULT NULL COMMENT '交易单位',
  `reverse_status` varchar(32) DEFAULT NULL COMMENT '售后状态',
  `is_evaluated` tinyint(1) DEFAULT NULL COMMENT '是否已评价',
  `evaluation_status` varchar(32) DEFAULT NULL COMMENT '评价状态',
  `order_promotion_info` varchar(1024) COMMENT '优惠信息',
  `actual_unit_price` decimal(15,6) DEFAULT NULL COMMENT '最终成交的单价、折后单价',
  `is_buyer_deleted` tinyint(1) DEFAULT NULL COMMENT '是否买家删除',
  `item_face_price` decimal(15,6) DEFAULT NULL COMMENT '商品页面价',
  `option` varchar(128) DEFAULT NULL COMMENT '订单标记',
  `sort_order` bigint DEFAULT NULL COMMENT '在打包一口价的情况下，需要安装打包一口价进行分组排序',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人',
  `is_deleted` bigint DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_trade_trade_order_detail_code` (`code`,`is_deleted`),
  KEY `idx_trade_trade_order_detail_buyer_id` (`buyer_id`),
  KEY `idx_trade_trade_order_detail_trade_order_id` (`trade_order_id`),
  KEY `idx_trade_trade_order_detail_trade_order_code` (`trade_order_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单明细';

-- ----------------------------
-- Table structure for trade_order_group
-- ----------------------------
DROP TABLE IF EXIS `trade_order_group`;
CREATE TABLE `trade_order_group` (
  `id` bigint NOT NULL COMMENT 'ID',
  `buyer_id` bigint DEFAULT NULL COMMENT '买家id',
  `origin_amount` decimal(15,6) DEFAULT NULL COMMENT '订单总额（不包含优惠），即：实际总额 + 优惠金额',
  `actual_amount` decimal(15,6) DEFAULT NULL COMMENT '成交金额，SUM(明细行成交金额) + 运费',
  `actual_goods_amount` decimal(15,6) DEFAULT NULL COMMENT 'SUM(明细行货款金额),页面价格汇总',
  `discount_amount` decimal(15,6) DEFAULT NULL COMMENT 'SUM(明细行优惠金额)，包含商品优惠，订单优惠的分摊和平台优惠的分摊',
  `refund_amount` decimal(15,6) DEFAULT NULL COMMENT '订单退款金额',
  `promotion_infos` varchar(4096) DEFAULT NULL COMMENT '优惠信息',
  `code` varchar(128) DEFAULT NULL COMMENT '编码',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人',
  `is_deleted` bigint DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_trade_trade_order_group_code` (`code`,`is_deleted`),
  KEY `idx_trade_trade_order_group_buyer_id` (`buyer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单组';

-- ----------------------------
-- Table structure for trade_order_log
-- ----------------------------
DROP TABLE IF EXIS `trade_order_log`;
CREATE TABLE `trade_order_log` (
  `id` bigint NOT NULL COMMENT 'ID',
  `trade_order_id` bigint DEFAULT NULL COMMENT '订单id',
  `trade_order_code` varchar(128) DEFAULT NULL COMMENT '订单code',
  `operate_log` varchar(128) DEFAULT NULL COMMENT '操作日志',
  `operate_time` datetime DEFAULT NULL COMMENT '操作时间',
  `status_before` varchar(32) DEFAULT NULL COMMENT '订单变更前状态',
  `status_after` varchar(32) DEFAULT NULL COMMENT '订单变更后状态',
  `event` varchar(32) DEFAULT NULL COMMENT '订单变更引发的事件',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人',
  `is_deleted` bigint DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单变更日志, 订单状态时间轴';

-- ----------------------------
-- Table structure for trade_pay
-- ----------------------------
DROP TABLE IF EXIS `trade_pay`;
CREATE TABLE `trade_pay` (
  `id` bigint NOT NULL COMMENT 'ID',
  `batch_code` varchar(40) DEFAULT NULL COMMENT '支付批次号',
  `seller_id` bigint DEFAULT NULL COMMENT '商家id',
  `buyer_id` bigint DEFAULT NULL COMMENT '买家id',
  `trade_order_id` bigint DEFAULT NULL COMMENT '订单id',
  `trade_order_code` varchar(128) DEFAULT NULL COMMENT '订单号',
  `pay_type` varchar(32) DEFAULT NULL COMMENT '支付类型',
  `pay_method` varchar(32) DEFAULT NULL COMMENT '支付方式',
  `pay_channel` varchar(32) DEFAULT NULL COMMENT '支付渠道',
  `sub_pay_channel` varchar(32) DEFAULT NULL COMMENT '子支付渠道',
  `pay_amount` decimal(15,6) DEFAULT NULL COMMENT '应付总额',
  `currency_code` varchar(10) DEFAULT NULL COMMENT '币种编码',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `pay_order_code` varchar(40) DEFAULT NULL COMMENT '支付单号',
  `pay_order_id` bigint DEFAULT NULL COMMENT '支付单id',
  `features` varchar(2000) DEFAULT NULL COMMENT '扩展信息',
  `pay_status` varchar(32) DEFAULT NULL COMMENT '支付状态',
  `code` varchar(128) DEFAULT NULL COMMENT '编码',
  `pay_fee` decimal(15,6) DEFAULT NULL COMMENT '手续费',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人',
  `is_deleted` bigint DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_trade_trade_pay_code` (`code`,`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='交易支付信息';

-- ----------------------------
-- Table structure for trade_risk_info
-- ----------------------------
DROP TABLE IF EXIS `trade_risk_info`;
CREATE TABLE `trade_risk_info` (
  `id` bigint NOT NULL COMMENT 'ID',
  `trade_order_code` varchar(128) DEFAULT NULL COMMENT '交易订单号',
  `handle_advise` varchar(128) DEFAULT NULL COMMENT '处理建议',
  `fraud_index` varchar(128) DEFAULT NULL COMMENT '欺诈指数',
  `risk_source` varchar(128) DEFAULT NULL COMMENT '风险来源',
  `risk_source_desc` varchar(128) DEFAULT NULL COMMENT '风险来源描述',
  `trade_order_id` bigint DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新人',
  `is_deleted` bigint DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='主订单风控信息';

SET FOREIGN_KEY_CHECKS = 1;
