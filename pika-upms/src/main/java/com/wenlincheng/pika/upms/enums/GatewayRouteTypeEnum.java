package com.wenlincheng.pika.upms.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 网关路由类型
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum GatewayRouteTypeEnum implements IEnum<String> {

    ADMIN("ADMIN", "管理端", "管理端"),
    APP("APP", "APP端", "APP端"),
    WEB("WEB", "Web端", "Web端");

    private String value;
    private String name;
    private String help;

    GatewayRouteTypeEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
