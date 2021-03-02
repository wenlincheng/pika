package com.wenlincheng.pika.upms.entity.vo.menu;

import com.wenlincheng.pika.common.core.base.vo.BaseVo;
import com.wenlincheng.pika.upms.entity.po.Menu;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 菜单列表VO
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "MenuListVO", description = "菜单列表VO")
public class MenuListVO extends BaseVo<Menu> {
    private static final long serialVersionUID = 59345947510627473L;

    public MenuListVO(Menu menu) {
        super(menu);
    }

    @ApiModelProperty(value = "上级id")
    private Long parentId;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "类型")
    private Integer type;
    @ApiModelProperty(value = "URI")
    private String uri;
    @ApiModelProperty(value = "请求方法")
    private String method;
    @ApiModelProperty(value = "权限标识")
    private String code;
    @ApiModelProperty(value = "图标")
    private String icon;
    @ApiModelProperty(value = "描述")
    private String description;
    @ApiModelProperty(value = "排序")
    private Integer sequence;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    @ApiModelProperty(value = "子菜单")
    private List<MenuListVO> children;

}
