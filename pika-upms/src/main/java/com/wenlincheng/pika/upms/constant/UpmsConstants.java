package com.wenlincheng.pika.upms.constant;

/**
 * 常量
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
public interface UpmsConstants {

    /**
     * 初始密码
     */
    String INITIAL_PASSWORD = "123456";

    /**
     * 管理端网关消息总线id
     */
    String GATEWAY_ROUTE_ADMIN_BUS_ID = "pika-gateway-admin:8100";

    /**
     * APP端网关消息总线id
     */
    String GATEWAY_ROUTE_APP_BUS_ID = "pika-gateway-app:8101";
}
