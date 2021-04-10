## E-commerce Platform Micro Service System

电商平台微服务系统

### 系统通用服务

| 微服务 | 端口 | 名称 | 描述 |
|-------|---------|----------|----------|
|pika-common| |公共模块|基础工具类、日志、数据管理、公共资源、分布式ID、在线文档等|
|pika-gateway-admin|8100|管理端网关服务||
|pika-gateway-app|8101|APP端网关服务||
|pika-auth|8110|认证鉴权服务|提供认证及鉴权功能|
|pika-upms|8120|通用权限管理服务|用户角色权限管理|
|pika-schedule|8130|任务调度|实现分布式定时任务调度|
|pika-message|8140|消息管理|短信、邮件、PUSH、站内信、第三方推送等|
|pika-open|8150|开放平台|对接第三方服务及对外开放接口|

### 电商业务服务
| 微服务 | 端口 | 名称 | 描述 |
|-------|---------|----------|---------|
|pika-item|8200|商品中心||
|pika-inventory|8210|库存中心||
|pika-search|8220|搜索服务||
|pika-trade|8230|交易中心||
|pika-promotion|8240|营销中心||
|pika-pay|8250|支付中心||
|pika-settlement|8260|结算中心||
|pika-logistics|8270|物流中心||
|pika-member|8280|客户中心||
|pika-evaluation|8290|评价问答||
|pika-merchant|8300|商家中心||
|pika-platform|8310|平台运营||

#### 电商业务微服务拆分
 - 用户中心（pika-upms）
     * 员工
     * 部门组织
     * 用户资料
     * 权限控制
 - 客户中心（pika-member）
     * 收货地址
     * 会员管理
     * 我的消息
     * 第三方账户绑定
     * 黑名单管理
     * 店铺收藏
 - 运营中心（pika-platform）
     * 商城装修
 - 商户中心（pika-merchant）
     * 商家资料(入驻、管理)
     * 店铺装修
     * 运费模板
 - 商品中心（pika-item）
     * 产品
     * 商品
     * 前台类目
     * 后台类目
     * 品牌
     * 商品标签
     * 收藏的商品
 - 搜索服务（pika-search）
     * 商品搜索
        - 推荐规则
     * 订单搜索
     * 搜索历史记录
     * 热门关键字
 - 库存中心（pika-inventory）
     * 库存查询
     * 库存变动（增减、占用、冻结）
 - 营销中心（pika-promotion）
     * 活动管理（报名、审核）
     * 优惠设置         
         - 限时折扣
         - 满减、满赠、满包邮
         - 优惠券（商品/店铺/平台级）
         - 打包一口价
         - 周期购
         - 团购
 - 交易中心（pika-trade）
     * 购物车
     * 订单支付
     * 订单履约
     * 订单日志
     * 订单管理
     * 下单业务规则配置（各种限制，防刷单等）
     * 下单流程
     * 售后列表
     * 售后纠纷平台介入流程
     * 售后原因按类目配置
     * 售后退款
     * 售后业务规则配置
 - 评价&问答（pika-evaluation）
     * 评价管理
     * 按商品取评价（展示显示sku）
     * 买家问答
 - 物流中心（pika-logistics）
     * 运费模板
     * 运费计算
     * 物流轨迹
 - 支付中心（pika-pay）
     * 账户体系
     * 充值
     * 提现
     * 提现需验证账户
     * 接入第三方支付(对接微信、支付宝等)
 - 结算中心（pika-settlement）
     * 应收应付流水
     * 对账管理
     * 分佣
     * 结算管理
     * 发票管理

### 基础服务

|  名称    |   服务名  |   版本    |  端口     | 描述                                            |
|---------|--------- |-----------|-----------|--------|
|  数据库 | MySQL | 5.7.24 | 3306 | 单机,可多实例、主从 |
|  缓存 | Redis | 5.0.4 | 6379 | 单机,可集群 |
|  消息中间件 |  RocketMQ | 4.5.0 | 5672 | 单机,可集群 |
|  注册与配置中心 | nacos-server | 1.3.2 | 8848 | 单机,可集群 |
|  熔断降级 | sentinel-dashboard | latest | 8858 | 单机 |
|  轻量级数据收集引擎  | Filebeat | | | 每台机器部署 |
|  数据收集处理工具  | Logstash | 7.7.0 | | 单机，通过 Logback 向 Logstash 日志收集端口发送日志 |
|  搜索引擎  | ElasticSearch |  7.7.0 | 9200 | 三实例集群 |
|  数据可视化分析工具 | Kibana | 7.7.0 | 5601 | 单机 |
|  可视化监控指标展示工具 | Grafana | | 3000 |  单机 |
|  分布式链路追踪工具 | SkyWalking | 6.6.0-es7 |  11800 12800 | 单机 |
|  分布式事务中间件 | Seata |  1.3.0 |  8091 | 共用 |
|  分布式任务调度 | xxl-job |  2.3.0 |  8131 | 共用 |

### 技术栈
| 名称 | 版本号 | 描述 |
|-------|---------|----------|
|Java|1.8|  |
|SpringBoot|2.3.2.RELEASE|  |
|SpringCloud|Hoxton.SR8|  |
|SpringCloud Alibaba|2.2.3.RELEASE|  |
|MyBatis-Plus| 3.3.0 |  |
|Nacos| 2.2.0.RELEASE |  |
|Maven|3.6|  |
|Openfeign| 2.2.2.RELEASE |  |
|Druid| 1.1.9 |  |
|RocketMQ| 2.0.0 |  |
|Seata| 1.3.0 |  |
|SkyWalking| 8.1.0 |  |

### Docker 部署
 #### 环境搭建
 
 启动 MySQL、Redis、Nacos、MQ等基础服务
 ```
cd ../docker-compose
docker-compose -f docker-compose-base.yml up -d
 ```
 
 初始化数据库
 ```
cd ../docker-compose
docker-compose -f docker-compose-init.yml up -d
 ```
 
 #### 构建镜像
 例如，构建网关服务
 ```
 cd ./pika-gateway/pika-gateway-admin
 mvn package && mvn docker:build
 ```
 ![images](https://img.wenlincheng.com/blog/20210126174013.png)
 #### 启动服务
 启动网关服务
 ```
 cd ../docker-compose
 docker-compose -f docker-compose-base.yml -f docker-compose-elk.yml -f docker-compose-app.yml up -d pika-gateway-admin
 ```

可在启动的时候添加以下环境变量，获取提供的测试环境配置进行运行，查看项目效果
```
REGISTER_HOST=122.51.153.231;REGISTER_NAMESPACE=test
```
 
### 日志收集分析（Elastic Stack）

#### 方案一
Logstash安装在应用服务器上收集并解析日志是非常消耗cpu和内存资源的。方案一是在应用的机器上部署 Filebeat，使用 Docker 单独部署 Logstash，然后由 Filebeat 收集日志输出给 Logstash 解析，解析完由 Logstash 再传给 Elasticsearch 中存储，再由Kibana展示。

##### 日志流向  
Filebeat --> Logstash --> Elasticsearch --> Kibana 
如图：
![Elastic Stack](https://img.wenlincheng.com/20200828110236.png)

##### filebeat下载安装
```
https://artifacts.elastic.co/downloads/beats/filebeat/filebeat-7.2.0-linux-x86_64.tar.gz
```

###### 配置中心
```
logging:
  config: classpath:logback-file.xml
```

#### 方案二

如果想降低运维成本，可以通过 Logback 向 Logstash 日志收集端口发送日志，不过会牺牲网络带宽。

##### 日志流向  
Logback --> Logstash --> Elasticsearch --> Kibana

##### 添加依赖

```xml
<!--logstash-->
<dependency>
    <groupId>net.logstash.logback</groupId>
    <artifactId>logstash-logback-encoder</artifactId>
</dependency>
```

##### logback-logstash.xml 配置
```xml
<!--输出到logstash的appender-->
<appender name="LOGSTASH" class="net.logstash.logback.appender.LogstashTcpSocketAppender">
    <!--可以访问的logstash日志收集端口-->
    <destination>127.0.0.1:5044</destination>
    <encoder charset="UTF-8" class="net.logstash.logback.encoder.LogstashEncoder"/>
</appender>
```

###### 配置中心
```
logging:
  config: classpath:logback-logstash.xml
```

#### Leaf 分布式ID生成服务
采用美团开源的分布式ID框架Leaf，为了将配置文件由Nacos统一管理，修改了配置的加载方式，可以使用以下仓库
##### 构建依赖
```
git clone https://github.com/wenlincheng/Leaf.git
cd Leaf
git checkout feature/spring-boot-starter
mvn clean install -Dmaven.test.skip=true
```

##### 号段模式
用于以某种格式生成数据编号。例如，商品编号（I0000001），品牌编号(B000001)等
###### 创建数据库表
```
USE DATABASE `pika-upms`;
CREATE TABLE `leaf_alloc` (
  `biz_tag` varchar(128)  NOT NULL DEFAULT '',
  `max_id` bigint(20) NOT NULL DEFAULT '1',
  `step` int(11) NOT NULL,
  `description` varchar(256)  DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`biz_tag`)
) ENGINE=InnoDB;
```

###### 应用实例

通过在实体类中增加 `@PikaModel.Code` 注解，实现根据规则自动生成`code`，需手动在数据库`leaf_alloc`中添加号段记录

```sql
INSERT INTO `pika_upms`.`leaf_alloc`(`biz_tag`, `max_id`, `step`, `description`, `update_time`) VALUES ('com.wenlincheng.pika.item.entity.po.Item', 13000, 1000, '商品表', '2021-02-07 23:57:25');
```

```java
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("item")
@PikaModel.Code(type = "DATE_ORDERLY_SEQ", prefix = "I", size = 8, format = "yyyyMMdd")
public class Item extends CodeModel<Item> {

    private static final long serialVersionUID = 1L;

    /**
     * 卖家ID
     */
    @TableField("seller_id")
    private Long sellerId;
    
    ......
}

```

#### Snowflake
号段模式可以生成趋势递增的ID，是可计算的，不适用于订单ID生成场景，比如竞争对手在两天中午12点分别下单，通过订单id号相减就能大致计算出公司一天的订单量，这个不允许出现的，因此ID的生成不使用号段模式，而是使用Snowflake。

```java
@Component
public class CustomIdGenerator implements IdentifierGenerator {

    @Autowired
    private LeafSnowflakeService leafSnowflakeService;

    @Override
    public Long nextId(Object entity) {
        // 可以将当前传入的class全类名来作为bizKey,或者提取参数来生成bizKey进行分布式Id调用生成.
        Class<?> entityClass = entity.getClass();
        String bizKey = entityClass.getName();
        // 根据bizKey调用分布式ID生成
        return leafSnowflakeService.genId(bizKey);
    }
}

```

### 预览
#### 后台管理
[线上预览](https://www.ternarytree.cn/#/login?redirect=%2Fdashboard)

登录账号 admin/123456
![登录页](https://img.ternarytree.cn/upload/20210410213606.png)

![首页](https://img.ternarytree.cn/upload/20210410213713.png)

![用户管理](https://img.ternarytree.cn/upload/20210410214200.png)

![角色管理](https://img.ternarytree.cn/upload/20210410214200.png)

![菜单管理](https://img.ternarytree.cn/upload/20210410214402.png)

### TODO
- [x] 分布式ID、Code生成
- [x] 集成Seata分布式事务，以及解决服务降级的分布式事务不生效问题
- [x] 角色管理更新无法自动填充
- [x] xxl-job
- [ ] mybatis-plus通用枚举
- [ ] 消息管理服务（Email、SMS（低）、微信消息模板（低）等）
- [ ] 网关管理（动态刷新、页面优化）
- [ ] 组织架构管理（公司管理、部门管理、员工管理）

