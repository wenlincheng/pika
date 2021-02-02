package com.wenlincheng.pika.message.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;

import lombok.Getter;

/**
 * 短信模板类型
 *
 * @author : Pikaman
 * @version : 1.0.0
 * @date : 2021/1/24 9:05 下午
 */
@Getter
public enum SMSTemplateTypeEnum implements IEnum<String> {

    SIGN_IN("SIGN_IN","登录","登录"),
    SIGN_UP("SIGN_UP","注册","注册"),
    CHANGE_PASSWORD("CHANGE_PASSWORD","修改密码","修改密码"),
    CHANGE_PHONE("CHANGE_PHONE","修改手机号","修改手机号"),
    BIND_PHONE("BIND_PHONE","绑定手机号","绑定手机号"),
    NOTIFY("NOTIFY","通知短信","通知短信"),
    ;

    private String value;
    private String name;
    private String help;

    SMSTemplateTypeEnum(String value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
