DROP TABLE IF EXISTS `allocate_bill`;
CREATE TABLE `allocate_bill` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `approve_status` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '审批状态',
  `approve_date` datetime DEFAULT NULL COMMENT '审核时间',
  `allocate_status` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '调拨状态',
  `stock_in_status` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '入库状态',
  `stock_out_status` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '出库状态',
  `stock_in_date` datetime DEFAULT NULL COMMENT '入库时间',
  `stock_out_date` datetime DEFAULT NULL COMMENT '出库时间',
  `allot_total_num` int(11) DEFAULT NULL COMMENT '调拨总数',
  `operate_time` datetime DEFAULT NULL COMMENT '操作时间',
  `reject_reason` text COLLATE utf8mb4_bin COMMENT '拒绝原因',
  `remark` varchar(512) COLLATE utf8mb4_bin COMMENT '备注',
  `code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '编码',
  `in_owner_id` bigint(20) DEFAULT NULL COMMENT '入库货主ID',
  `in_warehouse_id` bigint(20) DEFAULT NULL COMMENT '调入仓库ID',
  `in_warehouse_org_id` bigint(20) DEFAULT NULL COMMENT '库存组织ID',
  `out_owner_id` bigint(20) DEFAULT NULL COMMENT '出库货主ID',
  `out_warehouse_id` bigint(20) DEFAULT NULL COMMENT '出库仓库ID',
  `out_warehouse_org_id` bigint(20) DEFAULT NULL COMMENT '库存组织ID',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_allocate_bill_code` (`code`,`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='库存调拨单';

DROP TABLE IF EXISTS `allocate_bill_detail`;
CREATE TABLE `allocate_bill_detail` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `allocate_bill_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '调拨单单号',
  `short_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '内部简码',
  `bar_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '69码',
  `sku_name` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '名称',
  `sku_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '产品编码',
  `brand_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '品牌CODE',
  `sku_pic_url` varchar(512) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'sku图片路径',
  `attr_label` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '属性值',
  `allocate_num` int(11) DEFAULT NULL COMMENT '调拨数量',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `actual_ex_ware_num` int(11) DEFAULT NULL COMMENT '实际出库数量',
  `actual_en_ware_num` int(11) DEFAULT NULL COMMENT '实际入库数量',
  `allocate_bill_id` bigint(20) DEFAULT NULL COMMENT '调拨单ID',
  `inv_sku_id` bigint(20) DEFAULT NULL COMMENT '库存SKUID',
  `inv_unit_id` bigint(20) DEFAULT NULL COMMENT '库存单位ID',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='调拨单明细';

DROP TABLE IF EXISTS `alteration_bill_in`;
CREATE TABLE `alteration_bill_in` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `supplier_partner_id` bigint(20) DEFAULT NULL COMMENT '供应商ID',
  `supplier_partner_name` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '供应商名称',
  `type` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '入库单类型',
  `approve_date` datetime DEFAULT NULL COMMENT '审批时间',
  `items_total_amount` decimal(15,6) DEFAULT NULL COMMENT '商品总金额',
  `total_num` int(11) DEFAULT NULL COMMENT '实际入库数',
  `approve_status` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '审批状态',
  `in_stock_status` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '入库状态',
  `last_in_stock_time` datetime DEFAULT NULL COMMENT '最后入库时间',
  `remark` varchar(512) COLLATE utf8mb4_bin COMMENT '备注',
  `has_cancelled` tinyint(1) DEFAULT NULL COMMENT '作废',
  `reject_reason` text COLLATE utf8mb4_bin COMMENT '拒绝原因',
  `code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '编码',
  `owner_id` bigint(20) DEFAULT NULL COMMENT '货主ID',
  `in_warehouse_id` bigint(20) DEFAULT NULL COMMENT '收货仓ID',
  `warehouse_org_id` bigint(20) DEFAULT NULL COMMENT '库存组织ID',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_alteration_bill_in_code` (`code`,`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='库存调整单-入库单';

DROP TABLE IF EXISTS `alteration_bill_in_detail`;
CREATE TABLE `alteration_bill_in_detail` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `alteration_bill_in_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '入库单号',
  `supplier_partner_id` bigint(20) DEFAULT NULL COMMENT '供应商ID',
  `supplier_partner_name` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '供应商名称',
  `inv_sku_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '产品编码',
  `inv_sku_name` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '产品名称',
  `bar_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '条码',
  `sku_pic_url` varchar(512) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'sku图片路径',
  `convert_rate` int(11) DEFAULT NULL COMMENT '库存单位转换系数',
  `in_warehouse_num` int(11) DEFAULT NULL COMMENT '入库数',
  `actual_in_warehouse_num` int(11) DEFAULT NULL COMMENT '实际入库数量',
  `source_bill_type` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '来源单据类型',
  `source_bill_no` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '来源单据号',
  `actual_amount` decimal(15,6) DEFAULT NULL COMMENT '实际金额',
  `in_stock_status` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '入库状态',
  `in_stock_time` datetime DEFAULT NULL COMMENT '入库时间',
  `remark` varchar(512) COLLATE utf8mb4_bin COMMENT '备注',
  `alteration_bill_in_id` bigint(20) DEFAULT NULL COMMENT '库存调整单入库单ID',
  `inv_sku_id` bigint(20) DEFAULT NULL COMMENT '库存skuID',
  `inv_unit_id` bigint(20) DEFAULT NULL COMMENT '库存单位ID',
  `purchase_unit_id` bigint(20) DEFAULT NULL COMMENT '采购单位ID',
  `warehouse_id` bigint(20) DEFAULT NULL COMMENT '仓库ID',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='库存调整单-入库单明细';

DROP TABLE IF EXISTS `alteration_bill_out`;
CREATE TABLE `alteration_bill_out` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `supplier_partner_id` bigint(20) DEFAULT NULL COMMENT '供应商ID',
  `supplier_partner_name` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '供应商名称',
  `type` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '出库单类型',
  `approve_date` datetime DEFAULT NULL COMMENT '审批时间',
  `items_total_amount` decimal(15,6) DEFAULT NULL COMMENT '商品总金额',
  `total_num` int(11) DEFAULT NULL COMMENT '实际出库数量',
  `approve_status` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '审批状态',
  `out_stock_status` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '出库状态',
  `last_out_stock_time` datetime DEFAULT NULL COMMENT '出库时间',
  `remark` varchar(512) COLLATE utf8mb4_bin COMMENT '备注',
  `has_cancelled` tinyint(1) DEFAULT NULL COMMENT '作废',
  `code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '编码',
  `out_warehouse_id` bigint(20) DEFAULT NULL COMMENT '出货仓ID',
  `warehouse_org_id` bigint(20) DEFAULT NULL COMMENT '库存组织ID',
  `owner_id` bigint(20) DEFAULT NULL COMMENT '货主ID',
  `operator_id` bigint(20) DEFAULT NULL COMMENT '操作人ID',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_alteration_bill_out_code` (`code`,`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='库存调整单-出库单';

DROP TABLE IF EXISTS `alteration_bill_out_detail`;
CREATE TABLE `alteration_bill_out_detail` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `alteration_bill_out_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '出库单号',
  `supplier_partner_id` bigint(20) DEFAULT NULL COMMENT '供应商ID',
  `supplier_partner_name` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '供应商名称',
  `bar_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '69码',
  `sku_pic_url` varchar(512) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'sku图片路径',
  `inv_sku_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '产品编码',
  `inv_sku_name` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '产品名称',
  `convert_rate` bigint(20) DEFAULT NULL COMMENT '库存单位转换系数',
  `out_warehouse_num` int(11) DEFAULT NULL COMMENT '出库数',
  `actual_out_warehouse_num` int(11) DEFAULT NULL COMMENT '实际出库数量',
  `source_bill_type` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '来源单据类型',
  `source_bill_no` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '来源单据号',
  `actual_amount` decimal(15,6) DEFAULT NULL COMMENT '实际金额',
  `out_stock_status` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '出库状态',
  `out_stock_time` datetime DEFAULT NULL COMMENT '出库时间',
  `remark` varchar(512) COLLATE utf8mb4_bin COMMENT '备注',
  `alteration_bill_out_id` bigint(20) DEFAULT NULL COMMENT 'ID',
  `inv_sku_id` bigint(20) DEFAULT NULL COMMENT '库存skuID',
  `inv_unit_id` bigint(20) DEFAULT NULL COMMENT '库存单位ID',
  `purchase_unit_id` bigint(20) DEFAULT NULL COMMENT '采购单位ID',
  `warehouse_id` bigint(20) DEFAULT NULL COMMENT '仓库ID',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='库存调整单-出库单明细';

DROP TABLE IF EXISTS `anonymous_package`;
CREATE TABLE `anonymous_package` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `status` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '包裹状态',
  `reverse_order_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '关联售后单Code',
  `logistics_org` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '物流企业',
  `waybill_no` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '物流单号',
  `sender_name` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '发货人姓名',
  `sender_phone` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '发货人电话',
  `total_num` int(11) DEFAULT NULL COMMENT '总数量',
  `receive_date` datetime DEFAULT NULL COMMENT '收包时间',
  `remark` varchar(512) COLLATE utf8mb4_bin COMMENT '备注',
  `code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '编码',
  `warehouse_id` bigint(20) DEFAULT NULL COMMENT '仓库ID',
  `units_id` bigint(20) DEFAULT NULL COMMENT '单位ID',
  `originator_id` bigint(20) DEFAULT NULL COMMENT '制单人ID',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_anonymous_package_code` (`code`,`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='三无包裹';

DROP TABLE IF EXISTS `anonymous_package_detail`;
CREATE TABLE `anonymous_package_detail` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `sku_name` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '名称',
  `sku_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '产品编码',
  `bar_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '69码',
  `item_num` int(11) DEFAULT NULL COMMENT '数量',
  `remark` varchar(512) COLLATE utf8mb4_bin COMMENT '备注',
  `anonymous_package_id` bigint(20) DEFAULT NULL COMMENT '三无包裹ID',
  `inv_sku_id` bigint(20) DEFAULT NULL COMMENT '库存SKUID',
  `inv_unit_id` bigint(20) DEFAULT NULL COMMENT '库存单位ID',
  `main_pic_id` bigint(20) DEFAULT NULL COMMENT '主图ID',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='三无包裹明细';

DROP TABLE IF EXISTS `inventory`;
CREATE TABLE `inventory` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `category_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '类目CODE',
  `brand_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '品牌CODE',
  `sku_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '库存SKU编码',
  `bar_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '69码',
  `short_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '69码',
  `sku_name` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '库存SKU名称',
  `cost_unit_price` decimal(15,6) DEFAULT NULL COMMENT '成本单价',
  `total_num` int(11) DEFAULT NULL COMMENT '总库存量',
  `occupy_num` int(11) DEFAULT NULL COMMENT '占用库存',
  `blocked_num` int(11) DEFAULT NULL COMMENT '冻结库存',
  `available_num` int(11) DEFAULT NULL COMMENT '可用库存',
  `remark` varchar(512) COLLATE utf8mb4_bin COMMENT '备注',
  `opt_version` bigint(20) DEFAULT NULL COMMENT '乐观锁',
  `warehouse_id` bigint(20) DEFAULT NULL COMMENT 'ID',
  `owner_id` bigint(20) DEFAULT NULL COMMENT '所有者ID',
  `inv_sku_id` bigint(20) DEFAULT NULL COMMENT '库存SKUID',
  `inv_unit_id` bigint(20) DEFAULT NULL COMMENT '库存单位ID',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_inventory_warehouse_id_inv_sku_id` (`warehouse_id`,`inv_sku_id`,`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='库存';

DROP TABLE IF EXISTS `inventory_log`;
CREATE TABLE `inventory_log` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `source_bill_type` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '来源单据类型',
  `source_bill_no` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '来源单据',
  `operate_type` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '库存操作类型',
  `sku_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '库存SKU编码',
  `sku_name` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '产品名称',
  `attr_label` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '属性值',
  `direct` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '库存变动方向',
  `opt_num` int(11) DEFAULT NULL COMMENT '当次发生的数量(扣减或者冻结)',
  `opt_desc` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '本次库存变动的简要说明',
  `total_num` int(11) DEFAULT NULL COMMENT '记录库存操作后的数据',
  `available_num` int(11) DEFAULT NULL COMMENT '记录库存操作后的数据',
  `blocked_num` int(11) DEFAULT NULL COMMENT '记录库存操作后的数据',
  `occupy_num` int(11) DEFAULT NULL COMMENT '记录库存操作后的数据',
  `has_roll_back` tinyint(1) DEFAULT NULL COMMENT '退货后库存回退标识',
  `operate_time` datetime DEFAULT NULL COMMENT '操作时间',
  `operator` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作人',
  `inventory_id` bigint(20) DEFAULT NULL COMMENT '库存ID',
  `warehouse_id` bigint(20) DEFAULT NULL COMMENT '仓库ID',
  `inv_sku_id` bigint(20) DEFAULT NULL COMMENT '库存SKUID',
  `inv_unit_id` bigint(20) DEFAULT NULL COMMENT '库存单位ID',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_inventory_log_source_bill_no_source_bill_type` (`source_bill_no`,`source_bill_type`,`inv_sku_id`,`operate_type`,`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='库存流水';

DROP TABLE IF EXISTS `logical_warehouse`;
CREATE TABLE `logical_warehouse` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `name` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '名称',
  `type` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '类型',
  `un_allot_num` int(11) DEFAULT NULL COMMENT '未分配库存',
  `total_num` int(11) DEFAULT NULL COMMENT '总物理库存',
  `locked_num` int(11) DEFAULT NULL COMMENT '锁定库存',
  `use_no_allot_num` tinyint(1) DEFAULT NULL COMMENT '使用未分配库存',
  `has_use` tinyint(1) DEFAULT NULL COMMENT '是否启用',
  `code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '编码',
  `warehouse_id` bigint(20) DEFAULT NULL COMMENT 'ID',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_logical_warehouse_warehouse_id_name` (`warehouse_id`,`name`,`is_deleted`),
  UNIQUE KEY `uk_logical_warehouse_code` (`code`,`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='逻辑仓库/库区';

DROP TABLE IF EXISTS `logical_wh_inventory_log`;
CREATE TABLE `logical_wh_inventory_log` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `source_bill_type` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '来源单据类型',
  `source_bill_no` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '来源单据',
  `operate_type` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '库存操作类型',
  `sku_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '库存SKU编码',
  `sku_name` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '产品名称',
  `attr_label` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '属性值',
  `direct` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '库存变动方向',
  `opt_num` int(11) DEFAULT NULL COMMENT '当次发生的数量(扣减或者冻结)',
  `opt_desc` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '本次库存变动的简要说明',
  `total_num` int(11) DEFAULT NULL COMMENT '记录库存操作后的数据',
  `available_num` int(11) DEFAULT NULL COMMENT '记录库存操作后的数据',
  `blocked_num` int(11) DEFAULT NULL COMMENT '记录库存操作后的数据',
  `occupy_num` int(11) DEFAULT NULL COMMENT '记录库存操作后的数据',
  `overdraft_num` int(11) DEFAULT NULL COMMENT '记录库存操作后的数据',
  `has_roll_back` tinyint(1) DEFAULT NULL COMMENT '退货后库存回退标识',
  `operate_time` datetime DEFAULT NULL COMMENT '操作时间',
  `operator` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作人',
  `logical_wh_inventory_id` bigint(20) DEFAULT NULL COMMENT '库区库存ID',
  `warehouse_id` bigint(20) DEFAULT NULL COMMENT '仓库信息ID',
  `logical_warehouse_id` bigint(20) DEFAULT NULL COMMENT '逻辑仓库ID',
  `inv_sku_id` bigint(20) DEFAULT NULL COMMENT '库存SKUID',
  `inv_unit_id` bigint(20) DEFAULT NULL COMMENT '库存单位ID',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_logical_wh_inventory_log_source_bill_no_sourc` (`source_bill_no`,`source_bill_type`,`inv_sku_id`,`operate_type`,`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='库区库存流水';

DROP TABLE IF EXISTS `logical_wh_inventory`;
CREATE TABLE `logical_wh_inventory` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `category_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '类目CODE',
  `brand_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '品牌CODE',
  `bar_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '69码',
  `sku_name` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '名称',
  `sku_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '产品编码',
  `cost_unit_price` decimal(15,6) DEFAULT NULL COMMENT '成本单价',
  `total_num` int(11) DEFAULT NULL COMMENT '总库存量',
  `blocked_num` int(11) DEFAULT NULL COMMENT '冻结库存',
  `occupy_num` int(11) DEFAULT NULL COMMENT '预占库存',
  `available_num` int(11) DEFAULT NULL COMMENT '可用库存',
  `overdraft_num` int(11) DEFAULT NULL COMMENT '透支总数',
  `remark` varchar(512) COLLATE utf8mb4_bin COMMENT '备注',
  `opt_version` bigint(20) DEFAULT NULL COMMENT '乐观锁',
  `warehouse_id` bigint(20) DEFAULT NULL COMMENT '仓库信息ID',
  `logical_warehouse_id` bigint(20) DEFAULT NULL COMMENT '逻辑仓库ID',
  `inv_sku_id` bigint(20) DEFAULT NULL COMMENT '库存SKUID',
  `inv_unit_id` bigint(20) DEFAULT NULL COMMENT '库存单位ID',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='库区库存';

DROP TABLE IF EXISTS `logistics_fee_rule`;
CREATE TABLE `logistics_fee_rule` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `region_type` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '国内/外',
  `currency_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '币种编码',
  `first_weight` int(11) DEFAULT NULL COMMENT '首重重量',
  `first_price` decimal(15,6) DEFAULT NULL COMMENT '首重费用',
  `every_kg_price` decimal(15,6) DEFAULT NULL COMMENT '每kg续重费用',
  `logistics_id` bigint(20) DEFAULT NULL COMMENT 'ID',
  `city_id` bigint(20) DEFAULT NULL COMMENT '地区ID',
  `country_id` bigint(20) DEFAULT NULL COMMENT '国家ID',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='物流费用';

DROP TABLE IF EXISTS `sale_sku_stock`;
CREATE TABLE `sale_sku_stock` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `item_id` bigint(20) DEFAULT NULL COMMENT '商品ID',
  `sale_sku_id` bigint(20) DEFAULT NULL COMMENT '销售SKU ID',
  `sale_sku_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '销售SKU编码',
  `total_num` int(11) DEFAULT NULL COMMENT '总库存量',
  `occupy_num` int(11) DEFAULT NULL COMMENT '占用库存',
  `blocked_num` int(11) DEFAULT NULL COMMENT '冻结库存',
  `available_num` int(11) DEFAULT NULL COMMENT '可用库存',
  `remark` varchar(512) COLLATE utf8mb4_bin COMMENT '备注',
  `opt_version` bigint(20) DEFAULT NULL COMMENT '乐观锁',
  `owner_id` bigint(20) DEFAULT NULL COMMENT '所有者partnerId',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_sale_sku_stock_owner_id_sale_sku_id` (`owner_id`,`sale_sku_id`,`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='销售SKU库存';

DROP TABLE IF EXISTS `sale_sku_stock_log`;
CREATE TABLE `sale_sku_stock_log` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `source_bill_type` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '来源单据类型',
  `source_bill_no` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '来源单据',
  `operate_type` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '库存操作类型',
  `sale_sku_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '销售SKU编码',
  `sku_name` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '产品名称',
  `attr_label` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '属性值',
  `direct` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '库存变动方向',
  `opt_num` int(11) DEFAULT NULL COMMENT '当次发生的数量(扣减或者冻结)',
  `opt_desc` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '本次库存变动的简要说明',
  `total_num` int(11) DEFAULT NULL COMMENT '记录库存操作后的数据',
  `available_num` int(11) DEFAULT NULL COMMENT '记录库存操作后的数据',
  `blocked_num` int(11) DEFAULT NULL COMMENT '记录库存操作后的数据',
  `occupy_num` int(11) DEFAULT NULL COMMENT '记录库存操作后的数据',
  `has_roll_back` tinyint(1) DEFAULT NULL COMMENT '退货后库存回退标识',
  `operate_time` datetime DEFAULT NULL COMMENT '操作时间',
  `operator` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作人',
  `stock_id` bigint(20) DEFAULT NULL COMMENT '库存ID',
  `sale_sku_id` bigint(20) DEFAULT NULL COMMENT '销售SKUID',
  `inv_unit_id` bigint(20) DEFAULT NULL COMMENT '库存单位ID',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_sale_sku_stock_log_source_bill_no_source_bill` (`source_bill_no`,`source_bill_type`,`sale_sku_id`,`operate_type`,`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='销售SKU库存流水';

DROP TABLE IF EXISTS `stock_taking`;
CREATE TABLE `stock_taking` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `ware_attr` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '仓库属性',
  `stock_taking_type` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '盘点类型',
  `start_time` datetime DEFAULT NULL COMMENT '盘点开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '盘点结束时间',
  `stock_taking_date` datetime DEFAULT NULL COMMENT '盘点日期',
  `taking_man` bigint(20) DEFAULT NULL COMMENT '盘点人',
  `confirm_man` bigint(20) DEFAULT NULL COMMENT '确认人',
  `confirm_time` datetime DEFAULT NULL COMMENT '确认时间',
  `status` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '状态',
  `approve_status` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '审核状态',
  `has_cancelled` tinyint(1) DEFAULT NULL COMMENT '作废',
  `total_num` int(11) DEFAULT NULL COMMENT '盘点总数',
  `remark` varchar(512) COLLATE utf8mb4_bin COMMENT '备注',
  `code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '编码',
  `owner_id` bigint(20) DEFAULT NULL COMMENT '所有者ID',
  `warehouse_id` bigint(20) DEFAULT NULL COMMENT '仓库ID',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_stock_taking_code` (`code`,`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='库存盘点单';

DROP TABLE IF EXISTS `stock_taking_detail`;
CREATE TABLE `stock_taking_detail` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `stock_taking_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '盘点单编码',
  `sku_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '库存SKU编码',
  `bar_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '库存SKU 条码',
  `sku_pic_url` varchar(512) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'sku图片路径',
  `sku_name` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '库存SKU名称',
  `is_loss` tinyint(1) DEFAULT NULL COMMENT '是否亏损',
  `diff_num` int(11) DEFAULT NULL COMMENT '盈亏数量',
  `history_num` int(11) DEFAULT NULL COMMENT '历史库存',
  `stocking_num` int(11) DEFAULT NULL COMMENT '实际盘点库存',
  `stock_taking_id` bigint(20) DEFAULT NULL COMMENT 'ID',
  `inv_sku_id` bigint(20) DEFAULT NULL COMMENT '库存SKUID',
  `inv_unit_id` bigint(20) DEFAULT NULL COMMENT '库存单位ID',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='库存盘点明细';


DROP TABLE IF EXISTS `warehouse`;
CREATE TABLE `warehouse` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `name` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '仓库名称',
  `warehouse_attr` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '仓库属性',
  `warehouse_type` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '仓库类型',
  `region_type` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '国内/外',
  `contact` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系人',
  `contact_phone` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系人电话',
  `logical_sum` int(11) DEFAULT NULL COMMENT '逻辑仓库数量',
  `support_negative` tinyint(1) DEFAULT NULL COMMENT '是否支持负库存销售',
  `enable_logical` tinyint(1) DEFAULT NULL COMMENT '是否启用库区',
  `data_status` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '状态',
  `remark` varchar(512) COLLATE utf8mb4_bin COMMENT '仓库备注',
  `out_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '外部仓库编码',
  `code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '编码',
  `owner_id` bigint(20) DEFAULT NULL COMMENT '所有者ID',
  `resource_address_id` bigint(20) DEFAULT NULL COMMENT '仓库地址ID',
  `warehouse_org_id` bigint(20) DEFAULT NULL COMMENT '库存组织ID',
  `service_provider_id` bigint(20) DEFAULT NULL COMMENT '仓库服务商ID',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_warehouse_out_code` (`out_code`,`is_deleted`),
  UNIQUE KEY `uk_warehouse_code` (`code`,`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='仓库';

DROP TABLE IF EXISTS `warehouse_coverage_area`;
CREATE TABLE `warehouse_coverage_area` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `region_type` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '国内/外',
  `is_prior_area` tinyint(1) DEFAULT NULL COMMENT '优先配送地区',
  `warehouse_id` bigint(20) DEFAULT NULL COMMENT 'ID',
  `city_id` bigint(20) DEFAULT NULL COMMENT '地区ID',
  `country_id` bigint(20) DEFAULT NULL COMMENT '国家ID',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='仓库覆盖区域';

DROP TABLE IF EXISTS `warehouse_logistics`;
CREATE TABLE `warehouse_logistics` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `logistics_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '物流商编码',
  `name` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '物流服务商名称',
  `code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '物流服务商编码',
  `order` int(11) DEFAULT NULL COMMENT '物流服务商优先级',
  `warehouse_id` bigint(20) DEFAULT NULL COMMENT 'ID',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='配置物流代理';

DROP TABLE IF EXISTS `warehouse_service_provider`;
CREATE TABLE `warehouse_service_provider` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `name` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '名称',
  `describe` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
  `code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '编码',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_warehouse_service_provider_name` (`name`,`is_deleted`),
  UNIQUE KEY `uk_warehouse_service_provider_code` (`code`,`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='仓库服务商';