package com.wenlincheng.pika.upms.entity.vo.menu;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * 菜单meta
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Data
public class RouterMeta implements Serializable {
    private static final long serialVersionUID = 859717968042185307L;

    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "图标")
    private String icon;
    @ApiModelProperty(value = "有权限的角色id")
    private Set<Long> roles;
    @ApiModelProperty(value = "不缓存")
    private Boolean noCache;
}
