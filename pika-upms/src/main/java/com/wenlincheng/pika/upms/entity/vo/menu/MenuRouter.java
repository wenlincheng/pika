package com.wenlincheng.pika.upms.entity.vo.menu;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单路由
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Data
public class MenuRouter implements Serializable {
    private static final long serialVersionUID = -8259902619013371296L;

    @ApiModelProperty(value = "路径")
    private String path;
    @ApiModelProperty(value = "组件")
    private String component;
    @ApiModelProperty(value = "跳转路径")
    private String redirect;
    @ApiModelProperty(value = "显示")
    private Boolean alwaysShow;
    @ApiModelProperty(value = "隐藏")
    private Boolean hidden;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "meta数据")
    private RouterMeta meta;
    @ApiModelProperty(value = "子菜单")
    private List<MenuRouter> children;
}
