package com.wenlincheng.pika.upms.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import com.wenlincheng.pika.common.core.exception.ErrorTypeEnum;
import lombok.Getter;

/**
 * 菜单类型
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum SysMenuTypeEnum implements IEnum<Integer> {

    DIR(0, "目录", "目录"),
    MENU(1, "菜单", "菜单"),
    BUTTON(2, "按钮", "按钮");

    private Integer value;
    private String name;
    private String help;

    SysMenuTypeEnum(Integer  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }

}
