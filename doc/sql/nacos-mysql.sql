/*
 Navicat Premium Data Transfer

 Source Server         : docker
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : nacos_config

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 10/03/2021 17:47:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `c_use` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `effect` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `type` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `c_schema` text COLLATE utf8_bin,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfo_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info';

-- ----------------------------
-- Records of config_info
-- ----------------------------
BEGIN;
INSERT INTO `config_info` VALUES (7, 'pika-auth', 'PIKA', 'server:\n  port: 8110\n\n# JWT 配置\njwt:\n  # 服务端根据 SigningKey 生成 token\n  signingKey: VCsGI5YF+QH7uQOK5dbI0BGpskwRF8pPJr/MLmjeUPWPVgyIuREAQCvfo3nylgpz8DNuGFZ5w8Jjs5CSTT1+VQ==\n  # token 的有效时间\n  tokenExpiration: 36000\n  refreshTokenExpiration: 36000\n\n# Security 需要放行的路径\nignored:\n  uris:\n    - /login/v1\n    - /auth/token/**\n    - /auth/logout\n\nsecurity:\n  # 登录失败次数限制\n  loginTimeLimit: 5\n  # 多少分钟后可重试\n  loginAfterTime: 2', '5828892a0b070735dcc82200db61e795', '2021-03-04 03:09:15', '2021-03-10 02:39:29', 'nacos', '172.27.0.1', 'pika-auth', 'dev', '认证鉴权服务', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (8, 'pika-upms', 'PIKA', 'server:\n  port: 8120\nspring:\n  # 数据库\n  datasource:\n    url: jdbc:mysql://127.0.0.1:3306/pika_upms?characterEncoding=UTF-8&useUnicode=true&useSSL=false&serverTimezone=GMT%2b8\n    username: root\n    password: 123456\n\n# mybatis-plus\nmybatis-plus:\n  # 实体扫描，多个package用逗号或者分号分隔\n  type-aliases-package: com.wenlincheng.pika.upms.entity.po', 'ee642224921fd8f28cf3b0388208ac8b', '2021-03-04 03:09:15', '2021-03-10 02:01:45', 'nacos', '172.27.0.1', 'pika-upms', 'dev', '通用用户管理服务', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (9, 'pika-gateway-admin', 'PIKA', 'server:\n  port: 8100\nspring:\n  cloud:\n    # 网关\n    gateway:\n      enabled: true\n      # 跨域全局CORS配置\n      globalcors:\n        cors-configurations:\n          \'[/**]\': # 匹配所有请求\n            allowedOrigins: \"*\" # 跨域处理允许所有的域\n            allowedHeaders: \"*\"\n            allowedMethods:\n              - GET\n              - POST\n              - PUT\n              - DELETE\n      # 默认过滤器\n      default-filters:\n        # 重试请求\n        - name: Retry\n          args:\n            retries: 3\n            statuses: BAD_GATEWAY\n        # 添加响应头\n        - AddResponseHeader=X-Response-Default-Foo, Default-Bar\n        # 限流器\n        - name: RequestRateLimiter\n          args:\n            redis-rate-limiter.replenishRate: 500 # 允许每秒可以处理的请求数，流入速率\n            redis-rate-limiter.burstCapacity: 2000 # 允许在一秒内执行的最大请求数，令牌桶的容积\n            redis-rate-limiter.requestedTokens: 1 # 每个请求消耗的令牌数，默认为 1\n            rate-limiter: \"#{@defaultRedisRateLimiter}\"  # 使用SpEL引用bean，自定义实现RateLimiter接口的限流器\n            key-resolver: \"#{@apiKeyResolver}\" # 使用SpEL引用bean，自定义解析器\n\n# Ribbon\nribbon:\n  # Ribbon的默认加载策略是懒加载。当第一次访问的时候，不仅会发送访问请求，还有初始化相关的服务。 设置饥饿加载相关服务\n  eager-load:\n    enabled: true\n    clients:\n      - pika-auth\n      - pika-upms\n\n# 网关鉴权放行的路径\ngateway:\n  ignored:\n    uris:\n      - /login/v1\n      - /auth/token/**\n      - /v2/api-docs\n      - /app/**\n', '9e2c740279fc911898d07121b7aad426', '2021-03-04 03:09:15', '2021-03-10 02:48:25', 'nacos', '172.27.0.1', 'pika-gateway-admin', 'dev', '后台网关服务', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (15, 'logback.yml', 'PIKA', '# log\nlogging:\n  config: classpath:logback-logstash.xml\n  file:\n    max-size: 1MB\n    max-history: 60\n    path: ./logs/${spring.application.name}\n  level:\n    root: INFO\n    com:\n      wenlincheng: DEBUG\n\n# logstash\nlogstash:\n  host: 127.0.0.1\n  port: 5044', '905b379976bbc1a5be6027b43a64927e', '2021-03-09 20:25:34', '2021-03-10 02:11:12', 'nacos', '172.27.0.1', '', 'dev', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (21, 'swagger.yml', 'PIKA', '# 文档\nswagger:\n  enabled: true\n  basePackage:\n  basePath:\n  excludePath:\n  title: ${spring.application.name}\n  description:\n  version: 0.0.1\n  license:\n  licenseUrl:\n  termsOfServiceUrl:\n  host:\n  contact:\n    name: Pikaman\n    url: https://www.wenlincheng.com\n    email: wenlcmax@gmail.com', '4c1dc6196e1bffd663ec17a6d2274461', '2021-03-09 20:47:23', '2021-03-09 20:47:23', 'nacos', '172.27.0.1', '', 'dev', '扩展配置-文档', NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (22, 'leaf.yml', 'PIKA', '# Leaf\nleaf:\n  name: ${spring.application.name}\n  # segment\n  segment:\n    enable: true\n    url: jdbc:mysql://${DATASOURCE_HOST:127.0.0.1}:3306/pika_upms?characterEncoding=UTF-8&useUnicode=true&useSSL=false&serverTimezone=GMT%2b8\n    username: ${DATASOURCE_USERNAME:root}\n    password: ${DATASOURCE_PASSWORD:123456}\n  # snowflake\n  snowflake:\n    enable: true\n    address: ${ZOOKEEPER_HOST:127.0.0.1}\n    port: ${ZOOKEEPER_PORT:2181}', 'd4a33b8a23e8b0cdd36d2248f1bb319a', '2021-03-09 20:49:41', '2021-03-09 20:49:41', 'nacos', '172.27.0.1', '', 'dev', '扩展配置-leaf', NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (23, 'redis.yml', 'PIKA', '# 缓存\nspring:\n  redis:\n    host: ${REDIS_HOST:127.0.0.1}\n    port: 6379\n    password: ${REDIS_PASSWORD:123456}\n    timeout: 5000ms\n    lettuce:\n      # 关闭超时时间\n      shutdown-timeout: 100\n      pool:\n        # 连接池最大连接数\n        max-active: 300\n        # 连接池最大阻塞等待时间\n        max-wait: 10000\n        # 连接池中的最大空闲连接\n        max-idle: 8\n        # 连接池中的最小空闲连接\n        min-idle: 0', '6ca930bb138b1af824612f26df429146', '2021-03-09 20:52:19', '2021-03-09 20:52:19', 'nacos', '172.27.0.1', '', 'dev', '扩展配置-redis', NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (24, 'spring.yml', 'PIKA', 'spring:\n  profiles:\n    active: ${ACTIVE_ENV:dev}\n  mvc:\n    servlet:\n      load-on-startup: 0\n  jackson:\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n  cloud:\n    nacos:\n      discovery:\n        server-addr: ${REGISTER_HOST:127.0.0.1}:${REGISTER_PORT:8848}\n        namespace: ${REGISTER_NAMESPACE:dev}\n        group: ${REGISTER_GROUP:PIKA}\n        namingLoadCacheAtStart: true\n        username: ${REGISTER_USERNAME:nacos}\n        password: ${REGISTER_PASSWORD:nacos}\n    # 分布式消息\n    stream:\n      rocketmq:\n        binder:\n          name-server: 127.0.0.1:9876\n          \n    # 消息总线\n    bus:\n      id: ${spring.application.name}:${server.port}\n      trace:\n        enabled: true\n      destination: springCloudBus\n\n# ribbon\nribbon:\n  # Ribbon的默认加载策略是懒加载。当第一次访问的时候，不仅会发送访问请求，还有初始化相关的服务。 设置饥饿加载相关服务\n  eager-load:\n    enabled: true\n    clients:\n      - pika-trade\n\n# Feign\nfeign:\n  # sentinel对Feign的支持\n  sentinel:\n    enabled: true\n\n# 微服务监控\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n      base-path: /monitor\n  endpoint:\n    health:\n      show-details: always\n    shutdown:\n      enabled: true', 'ae5096808ac2ba171678b3ae82b8ea77', '2021-03-09 20:56:05', '2021-03-10 02:23:54', 'nacos', '172.27.0.1', '', 'dev', '公共配置-spring', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (26, 'jetcache.yml', 'PIKA', 'jetcache:\n  statIntervalMinutes: 15\n  areaInCacheName: false\n  hidePackages: com.wenlincheng.pika\n  local:\n    # 默认2小时本地缓存\n    default:\n      type: caffeine\n      keyConvertor: fastjson\n  remote:\n    # 默认2小时的远程缓存\n    default:\n      type: redis\n      keyConvertor: fastjson\n      valueEncoder: kryo\n      valueDecoder: kryo\n      poolConfig:\n        minIdle: 5\n        maxIdle: 20\n        maxTotal: 50\n      host: ${REDIS_HOST:127.0.0.1}\n      port: ${REDIS_PORT:6379}\n      password: ${REDIS_PASSWORD:123456}', 'c976c49e9a8e81d29c627c305e92e643', '2021-03-09 23:05:33', '2021-03-09 23:05:33', 'nacos', '172.27.0.1', '', 'dev', '公共配置-jetcache缓存系统', NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (34, 'database.yml', 'PIKA', 'spring:\n  # 数据库\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    # 数据库连接池\n    druid:\n      filters: stat\n      filter:\n        stat:\n          log-slow-sql: true\n          slow-sql-millis: 2000\n      maxActive: 20\n      initialSize: 5\n      maxWait: 60000\n      minIdle: 1\n      timeBetweenEvictionRunsMillis: 60000\n      minEvictableIdleTimeMillis: 300000\n      validationQuery: select \'x\'\n      testWhileIdle: true\n      testOnBorrow: false\n      testOnReturn: false\n      poolPreparedStatements: true\n      maxOpenPreparedStatements: 20\n      # Druid StatViewServlet配置，说明请参考Druid Wiki，配置_StatViewServlet配置\n      stat-view-servlet:\n        enabled: true\n        url-pattern: /druid/*\n        reset-enable: true\n        login-username: admin\n        login-password: admin\n        allow:\n        deny:\n      # Druid WebStatFilter配置\n      web-stat-filter:\n        enabled: true\n        url-pattern: /*\n        exclusions: .js,*.gif,*.png,*.jpg,*.html,*.js,*.css,*.ico,/druid/*\n\n\n# mybatis-plus\nmybatis-plus:\n  # config-location:\n  mapper-locations: classpath:mapper/*Mapper.xml\n  configuration:\n    # 驼峰下划线转换\n    map-underscore-to-camel-case: true\n    cache-enabled: false\n    call-setters-on-nulls: true\n  global-config:\n    banner: false\n    # 数据库大写下划线转换\n    #capital-mode: true\n    # 序列接口实现类配置\n    #key-generator: com.baomidou.springboot.xxx\n    # 数据库相关配置\n    db-config:\n      # 主键类型  AUTO:\"数据库ID自增\", INPUT:\"用户输入ID\",ID_WORKER:\"全局唯一ID (数字类型唯一ID)\", UUID:\"全局唯一ID UUID\";\n      id-type: ASSIGN_ID\n      capital-mode: true\n      # 逻辑删除配置\n      logic-delete-value: 1\n      logic-not-delete-value: 0', '086d4843ccee08b4350b5b66ad223afd', '2021-03-10 01:59:23', '2021-03-10 01:59:23', 'nacos', '172.27.0.1', '', 'dev', '公共配置-数据库', NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (37, 'pika-upms-sentinel', 'PIKA', '{}', '99914b932bd37a50b983c5e7c90ae93b', '2021-03-10 02:20:38', '2021-03-10 02:28:40', 'nacos', '172.27.0.1', '', '', 'sentinel限流规则', '', '', 'json', '');
INSERT INTO `config_info` VALUES (39, 'sentinel', 'PIKA', 'spring:\n  cloud:\n    sentinel:\n      transport:\n        dashboard: 127.0.0.1:8858\n        port: 8719\n      datasource:\n        ds1:\n          # 将限流规则持久化进nacos配置中心\n          nacos:\n            server-addr: 127.0.0.1:8848\n            dataId: ${spring.application.name}-sentinel\n            groupId: PIKA\n            data-type: json\n            rule-type: flow\n            username: nacos\n            password: nacos', '4a0d97dd1367bc3fb45108c15c0b973f', '2021-03-10 02:27:47', '2021-03-10 02:27:47', NULL, '172.27.0.1', '', 'dev', NULL, NULL, NULL, 'yaml', NULL);
COMMIT;

-- ----------------------------
-- Table structure for config_info_aggr
-- ----------------------------
DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfoaggr_datagrouptenantdatum` (`data_id`,`group_id`,`tenant_id`,`datum_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='增加租户字段';

-- ----------------------------
-- Table structure for config_info_beta
-- ----------------------------
DROP TABLE IF EXISTS `config_info_beta`;
CREATE TABLE `config_info_beta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) COLLATE utf8_bin DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfobeta_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info_beta';

-- ----------------------------
-- Table structure for config_info_tag
-- ----------------------------
DROP TABLE IF EXISTS `config_info_tag`;
CREATE TABLE `config_info_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfotag_datagrouptenanttag` (`data_id`,`group_id`,`tenant_id`,`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info_tag';

-- ----------------------------
-- Table structure for config_tags_relation
-- ----------------------------
DROP TABLE IF EXISTS `config_tags_relation`;
CREATE TABLE `config_tags_relation` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `tag_name` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`),
  UNIQUE KEY `uk_configtagrelation_configidtag` (`id`,`tag_name`,`tag_type`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_tag_relation';

-- ----------------------------
-- Table structure for group_capacity
-- ----------------------------
DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='集群、各Group容量信息表';

-- ----------------------------
-- Table structure for his_config_info
-- ----------------------------
DROP TABLE IF EXISTS `his_config_info`;
CREATE TABLE `his_config_info` (
  `id` bigint(64) unsigned NOT NULL,
  `nid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL,
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL,
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `src_user` text COLLATE utf8_bin,
  `src_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `op_type` char(10) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`nid`),
  KEY `idx_gmt_create` (`gmt_create`),
  KEY `idx_gmt_modified` (`gmt_modified`),
  KEY `idx_did` (`data_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='多租户改造';

-- ----------------------------
-- Records of his_config_info
-- ----------------------------
BEGIN;
INSERT INTO `his_config_info` VALUES (7, 1, 'pika-auth', 'PIKA', '', '# JWT 配置\njwt:\n  # 服务端根据 SigningKey 生成 token\n  signingKey: VCsGI5YF+QH7uQOK5dbI0BGpskwRF8pPJr/MLmjeUPWPVgyIuREAQCvfo3nylgpz8DNuGFZ5w8Jjs5CSTT1+VQ==\n  # token 的有效时间\n  tokenExpiration: 36000\n  refreshTokenExpiration: 36000\n\n# Security 需要放行的路径\nignored:\n  uris:\n    - /login/v1\n    - /auth/token/**\n    - /auth/logout\n\nsecurity:\n  # 登录失败次数限制\n  loginTimeLimit: 5\n  # 多少分钟后可重试\n  loginAfterTime: 2', '7649e624ccc2199de3932d9a7434e103', '2021-03-10 10:58:49', '2021-03-09 20:58:49', 'nacos', '172.27.0.1', 'U', 'dev');
INSERT INTO `his_config_info` VALUES (8, 2, 'pika-upms', 'PIKA', '', 'logging:\n  level:\n    root: info', '7f41c1a2a2b501222690cc5dc551aa70', '2021-03-10 10:59:19', '2021-03-09 20:59:19', 'nacos', '172.27.0.1', 'U', 'dev');
INSERT INTO `his_config_info` VALUES (9, 3, 'pika-gateway-admin', 'PIKA', '', 'logging:\n  level:\n    root: info', '7f41c1a2a2b501222690cc5dc551aa70', '2021-03-10 10:59:40', '2021-03-09 20:59:41', 'nacos', '172.27.0.1', 'U', 'dev');
INSERT INTO `his_config_info` VALUES (24, 4, 'spring.yml', 'PIKA', '', 'spring:\n  profiles:\n    active: ${ACTIVE_ENV:dev}\n  mvc:\n    servlet:\n      load-on-startup: 0\n  jackson:\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8', '8f4473b58ee27a79b748a17a7bf7656a', '2021-03-10 11:04:43', '2021-03-09 21:04:44', 'nacos', '172.27.0.1', 'U', 'dev');
INSERT INTO `his_config_info` VALUES (24, 5, 'spring.yml', 'PIKA', '', 'spring:\n  profiles:\n    active: ${ACTIVE_ENV:dev}\n  mvc:\n    servlet:\n      load-on-startup: 0\n  jackson:\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n\n# ribbon\nribbon:\n  # Ribbon的默认加载策略是懒加载。当第一次访问的时候，不仅会发送访问请求，还有初始化相关的服务。 设置饥饿加载相关服务\n  eager-load:\n    enabled: true\n    clients:\n      - pika-trade\n\n# Feign\nfeign:\n  # sentinel对Feign的支持\n  sentinel:\n    enabled: true\n\n# 微服务监控\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n      base-path: /monitor\n  endpoint:\n    health:\n      show-details: always\n    shutdown:\n      enabled: true', '27f82eb1105dd09bc9dfb2149234f365', '2021-03-10 12:04:06', '2021-03-09 22:04:02', 'nacos', '172.27.0.1', 'U', 'dev');
INSERT INTO `his_config_info` VALUES (0, 6, 'jetcache.yml', 'PIKA', '', 'jetcache:\n  statIntervalMinutes: 15\n  areaInCacheName: false\n  hidePackages: com.wenlincheng.pika\n  local:\n    # 默认2小时本地缓存\n    default:\n      type: caffeine\n      keyConvertor: fastjson\n  remote:\n    # 默认2小时的远程缓存\n    default:\n      type: redis\n      keyConvertor: fastjson\n      valueEncoder: kryo\n      valueDecoder: kryo\n      poolConfig:\n        minIdle: 5\n        maxIdle: 20\n        maxTotal: 50\n      host: ${REDIS_HOST:127.0.0.1}\n      port: ${REDIS_PORT:6379}\n      password: ${REDIS_PASSWORD:123456}', 'c976c49e9a8e81d29c627c305e92e643', '2021-03-10 13:05:33', '2021-03-09 23:05:33', 'nacos', '172.27.0.1', 'I', 'dev');
INSERT INTO `his_config_info` VALUES (15, 7, 'logback.yml', 'PIKA', '', '# log\nlogging:\n  file:\n    max-size: 50MB\n    path: ./logs/${spring.application.name}\n  level:\n    root: INFO\n    com:\n      wenlincheng: INFO\n\n# logstash\nlogstash:\n  host: ${LOGSTASH_HOST:127.0.0.1}\n  port: ${LOGSTASH_PORT:5044}', '0623cdc323733619f9c0fd3d18cb2826', '2021-03-10 14:40:36', '2021-03-10 00:40:36', 'nacos', '172.27.0.1', 'U', 'dev');
INSERT INTO `his_config_info` VALUES (15, 8, 'logback.yml', 'PIKA', '', '# log\nlogging:\n  config: classpath:logback-spring.xml\n  file:\n    max-size: 50MB\n    path: ./logs/${spring.application.name}\n  level:\n    root: INFO\n    com:\n      wenlincheng: INFO\n\n# logstash\nlogstash:\n  host: 127.0.0.1\n  port: 5044', 'f2dd5f9deb0eac9b292ff6605f995077', '2021-03-10 14:49:07', '2021-03-10 00:49:07', 'nacos', '172.27.0.1', 'U', 'dev');
INSERT INTO `his_config_info` VALUES (15, 9, 'logback.yml', 'PIKA', '', '# log\nlogging:\n  config: classpath:logback-logstash.xml\n  file:\n    max-size: 50MB\n    max-history: 60\n    path: ./logs/${spring.application.name}\n  level:\n    root: INFO\n    com:\n      wenlincheng: INFO\n\n# logstash\nlogstash:\n  host: 127.0.0.1\n  port: 5044', 'dbeca54a9cc43358fe3117ee48195750', '2021-03-10 15:41:23', '2021-03-10 01:41:23', 'nacos', '172.27.0.1', 'U', 'dev');
INSERT INTO `his_config_info` VALUES (15, 10, 'logback.yml', 'PIKA', '', '# log\nlogging:\n  config: classpath:logback-logstash.xml\n  file:\n    max-size: 1MB\n    max-history: 60\n    path: ./logs/${spring.application.name}\n  level:\n    root: INFO\n    com:\n      wenlincheng: INFO\n\n# logstash\nlogstash:\n  host: 127.0.0.1\n  port: 5044', 'da87a1ac4409b7041a2eeb2aeed4c513', '2021-03-10 15:48:52', '2021-03-10 01:48:53', 'nacos', '172.27.0.1', 'U', 'dev');
INSERT INTO `his_config_info` VALUES (15, 11, 'logback.yml', 'PIKA', '', '# log\nlogging:\n  config: classpath:logback-logstash.xml\n  file:\n    max-size: 1MB\n    max-history: 60\n    path: ./logs/${spring.application.name}\n  level:\n    root: DEBUG\n    com:\n      wenlincheng: INFO\n\n# logstash\nlogstash:\n  host: 127.0.0.1\n  port: 5044', 'f7c33821ed893bb987c61d472579cd7e', '2021-03-10 15:50:56', '2021-03-10 01:50:56', 'nacos', '172.27.0.1', 'U', 'dev');
INSERT INTO `his_config_info` VALUES (15, 12, 'logback.yml', 'PIKA', '', '# log\nlogging:\n  config: classpath:logback-logstash.xml\n  file:\n    max-size: 1MB\n    max-history: 60\n    path: ./logs/${spring.application.name}\n  level:\n    root: DEBUG\n    com:\n      wenlincheng: INFO\n\n# logstash\nlogstash:\n  host: 127.0.0.1\n  port: 5044', 'f7c33821ed893bb987c61d472579cd7e', '2021-03-10 15:51:44', '2021-03-10 01:51:45', 'nacos', '172.27.0.1', 'U', 'dev');
INSERT INTO `his_config_info` VALUES (15, 13, 'logback.yml', 'PIKA', '', '# log\nlogging:\n  config: classpath:logback-logstash.xml\n  file:\n    max-size: 1MB\n    max-history: 60\n    path: ./logs/${spring.application.name}\n  level:\n    root: INFO\n    com:\n      wenlincheng: INFO\n\n# logstash\nlogstash:\n  host: 127.0.0.1\n  port: 5044', 'da87a1ac4409b7041a2eeb2aeed4c513', '2021-03-10 15:53:03', '2021-03-10 01:53:04', 'nacos', '172.27.0.1', 'U', 'dev');
INSERT INTO `his_config_info` VALUES (0, 14, 'database.yml', 'PIKA', '', 'spring:\n  # 数据库\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    # 数据库连接池\n    druid:\n      filters: stat\n      filter:\n        stat:\n          log-slow-sql: true\n          slow-sql-millis: 2000\n      maxActive: 20\n      initialSize: 5\n      maxWait: 60000\n      minIdle: 1\n      timeBetweenEvictionRunsMillis: 60000\n      minEvictableIdleTimeMillis: 300000\n      validationQuery: select \'x\'\n      testWhileIdle: true\n      testOnBorrow: false\n      testOnReturn: false\n      poolPreparedStatements: true\n      maxOpenPreparedStatements: 20\n      # Druid StatViewServlet配置，说明请参考Druid Wiki，配置_StatViewServlet配置\n      stat-view-servlet:\n        enabled: true\n        url-pattern: /druid/*\n        reset-enable: true\n        login-username: admin\n        login-password: admin\n        allow:\n        deny:\n      # Druid WebStatFilter配置\n      web-stat-filter:\n        enabled: true\n        url-pattern: /*\n        exclusions: .js,*.gif,*.png,*.jpg,*.html,*.js,*.css,*.ico,/druid/*\n\n\n# mybatis-plus\nmybatis-plus:\n  # config-location:\n  mapper-locations: classpath:mapper/*Mapper.xml\n  configuration:\n    # 驼峰下划线转换\n    map-underscore-to-camel-case: true\n    cache-enabled: false\n    call-setters-on-nulls: true\n  global-config:\n    banner: false\n    # 数据库大写下划线转换\n    #capital-mode: true\n    # 序列接口实现类配置\n    #key-generator: com.baomidou.springboot.xxx\n    # 数据库相关配置\n    db-config:\n      # 主键类型  AUTO:\"数据库ID自增\", INPUT:\"用户输入ID\",ID_WORKER:\"全局唯一ID (数字类型唯一ID)\", UUID:\"全局唯一ID UUID\";\n      id-type: ASSIGN_ID\n      capital-mode: true\n      # 逻辑删除配置\n      logic-delete-value: 1\n      logic-not-delete-value: 0', '086d4843ccee08b4350b5b66ad223afd', '2021-03-10 15:59:23', '2021-03-10 01:59:23', 'nacos', '172.27.0.1', 'I', 'dev');
INSERT INTO `his_config_info` VALUES (8, 15, 'pika-upms', 'PIKA', 'pika-upms', 'logging:\n  level:\n    root: info', '7f41c1a2a2b501222690cc5dc551aa70', '2021-03-10 16:01:45', '2021-03-10 02:01:45', 'nacos', '172.27.0.1', 'U', 'dev');
INSERT INTO `his_config_info` VALUES (15, 16, 'logback.yml', 'PIKA', '', '# log\nlogging:\n  config: classpath:logback-logstash.xml\n  file:\n    max-size: 1MB\n    max-history: 60\n    path: ./logs/${spring.application.name}\n  level:\n    root: DEBUG\n    com:\n      wenlincheng: DEBUG\n\n# logstash\nlogstash:\n  host: 127.0.0.1\n  port: 5044', '215f69d71f7247a7a2f4f0415114d358', '2021-03-10 16:11:12', '2021-03-10 02:11:12', 'nacos', '172.27.0.1', 'U', 'dev');
INSERT INTO `his_config_info` VALUES (0, 17, 'pika-upms-sentinel', 'PIKA', '', 'spring:\n  cloud:\n    sentinel:\n      transport:\n        dashboard: 127.0.0.1:8858\n        port: 8719\n      datasource:\n        ds1:\n          # 将限流规则持久化进nacos配置中心\n          nacos:\n            server-addr: 127.0.0.1:8848\n            dataId: ${spring.application.name}-sentinel\n            groupId: PIKA\n            data-type: json\n            rule-type: flow\n            username: nacos\n            password: nacos', '4a0d97dd1367bc3fb45108c15c0b973f', '2021-03-10 16:20:38', '2021-03-10 02:20:38', 'nacos', '172.27.0.1', 'I', '');
INSERT INTO `his_config_info` VALUES (24, 18, 'spring.yml', 'PIKA', '', 'spring:\n  profiles:\n    active: ${ACTIVE_ENV:dev}\n  mvc:\n    servlet:\n      load-on-startup: 0\n  jackson:\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n  cloud:\n    nacos:\n      discovery:\n        server-addr: ${REGISTER_HOST:127.0.0.1}:${REGISTER_PORT:8848}\n        namespace: ${REGISTER_NAMESPACE:dev}\n        group: ${REGISTER_GROUP:PIKA}\n        namingLoadCacheAtStart: true\n        username: ${REGISTER_USERNAME:nacos}\n        password: ${REGISTER_PASSWORD:nacos}\n\n# ribbon\nribbon:\n  # Ribbon的默认加载策略是懒加载。当第一次访问的时候，不仅会发送访问请求，还有初始化相关的服务。 设置饥饿加载相关服务\n  eager-load:\n    enabled: true\n    clients:\n      - pika-trade\n\n# Feign\nfeign:\n  # sentinel对Feign的支持\n  sentinel:\n    enabled: true\n\n# 微服务监控\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n      base-path: /monitor\n  endpoint:\n    health:\n      show-details: always\n    shutdown:\n      enabled: true', '14c418fc42889501251073e614354c55', '2021-03-10 16:23:54', '2021-03-10 02:23:54', 'nacos', '172.27.0.1', 'U', 'dev');
INSERT INTO `his_config_info` VALUES (0, 19, 'sentinel', 'PIKA', '', 'spring:\n  cloud:\n    sentinel:\n      transport:\n        dashboard: 127.0.0.1:8858\n        port: 8719\n      datasource:\n        ds1:\n          # 将限流规则持久化进nacos配置中心\n          nacos:\n            server-addr: 127.0.0.1:8848\n            dataId: ${spring.application.name}-sentinel\n            groupId: PIKA\n            data-type: json\n            rule-type: flow\n            username: nacos\n            password: nacos', '4a0d97dd1367bc3fb45108c15c0b973f', '2021-03-10 16:27:47', '2021-03-10 02:27:47', NULL, '172.27.0.1', 'I', 'dev');
INSERT INTO `his_config_info` VALUES (37, 20, 'pika-upms-sentinel', 'PIKA', '', 'spring:\n  cloud:\n    sentinel:\n      transport:\n        dashboard: 127.0.0.1:8858\n        port: 8719\n      datasource:\n        ds1:\n          # 将限流规则持久化进nacos配置中心\n          nacos:\n            server-addr: 127.0.0.1:8848\n            dataId: ${spring.application.name}-sentinel\n            groupId: PIKA\n            data-type: json\n            rule-type: flow\n            username: nacos\n            password: nacos', '4a0d97dd1367bc3fb45108c15c0b973f', '2021-03-10 16:28:40', '2021-03-10 02:28:40', 'nacos', '172.27.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (7, 21, 'pika-auth', 'PIKA', 'pika-auth', '# JWT 配置\njwt:\n  # 服务端根据 SigningKey 生成 token\n  signingKey: VCsGI5YF+QH7uQOK5dbI0BGpskwRF8pPJr/MLmjeUPWPVgyIuREAQCvfo3nylgpz8DNuGFZ5w8Jjs5CSTT1+VQ==\n  # token 的有效时间\n  tokenExpiration: 36000\n  refreshTokenExpiration: 36000\n\n# Security 需要放行的路径\nignored:\n  uris:\n    - /login/v1\n    - /auth/token/**\n    - /auth/logout\n\nsecurity:\n  # 登录失败次数限制\n  loginTimeLimit: 5\n  # 多少分钟后可重试\n  loginAfterTime: 2', '7649e624ccc2199de3932d9a7434e103', '2021-03-10 16:39:29', '2021-03-10 02:39:29', 'nacos', '172.27.0.1', 'U', 'dev');
INSERT INTO `his_config_info` VALUES (9, 22, 'pika-gateway-admin', 'PIKA', 'pika-gateway-admin', 'logging:\n  level:\n    root: info', '7f41c1a2a2b501222690cc5dc551aa70', '2021-03-10 16:48:25', '2021-03-10 02:48:25', 'nacos', '172.27.0.1', 'U', 'dev');
COMMIT;

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions` (
  `role` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `resource` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `action` varchar(8) COLLATE utf8mb4_bin NOT NULL,
  UNIQUE KEY `uk_role_permission` (`role`,`resource`,`action`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `username` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `role` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  UNIQUE KEY `idx_user_role` (`username`,`role`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of roles
-- ----------------------------
BEGIN;
INSERT INTO `roles` VALUES ('nacos', 'ROLE_ADMIN');
COMMIT;

-- ----------------------------
-- Table structure for tenant_capacity
-- ----------------------------
DROP TABLE IF EXISTS `tenant_capacity`;
CREATE TABLE `tenant_capacity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数',
  `max_aggr_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='租户容量信息表';

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint(20) NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint(20) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_info_kptenantid` (`kp`,`tenant_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='tenant_info';

-- ----------------------------
-- Records of tenant_info
-- ----------------------------
BEGIN;
INSERT INTO `tenant_info` VALUES (2, '1', 'dev', 'dev', '开发环境', 'nacos', 1614848937559, 1614848937559);
COMMIT;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `password` varchar(500) COLLATE utf8mb4_bin NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users` VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
