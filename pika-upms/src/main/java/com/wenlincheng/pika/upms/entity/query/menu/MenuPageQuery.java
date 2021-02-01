package com.wenlincheng.pika.upms.entity.query.menu;

import com.wenlincheng.pika.common.core.base.query.BasePageQuery;
import com.wenlincheng.pika.upms.entity.po.SysMenu;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 菜单分页查询参数
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Data
@ApiModel(value = "MenuQueryForm", description = "菜单分页查询参数")
public class MenuPageQuery extends BasePageQuery<SysMenu> {

    @ApiModelProperty(value = "菜单名")
    private String name;
    @ApiModelProperty(value = "菜单类型")
    private Integer type;
    @ApiModelProperty(value = "菜单状态")
    private Integer status;
}
