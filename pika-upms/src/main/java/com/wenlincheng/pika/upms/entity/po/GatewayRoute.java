package com.wenlincheng.pika.upms.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wenlincheng.pika.common.core.base.model.IdModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 网关路由表
 * </p>
 *
 * @author Pikaman
 * @date 2021/1/1 10:10 上午
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gateway_route")
public class GatewayRoute extends IdModel<GatewayRoute> {

    private static final long serialVersionUID = 1L;

    /**
     * 路由id
     */
    @TableField("route_id")
    private String routeId;

    /**
     * uri路径
     */
    @TableField("uri")
    private String uri;

    /**
     * 判定器
     */
    @TableField("predicates")
    private String predicates;

    /**
     * 过滤器
     */
    @TableField("filters")
    private String filters;

    /**
     * 排序
     */
    @TableField("seq")
    private Integer seq;

    /**
     * 描述
     */
    @TableField("description")
    private String description;

    /**
     * 状态 0 无效 1 有效
     */
    @TableField("status")
    private Integer status;

}
