package com.wenlincheng.pika.upms.entity.query.gateway;

import com.wenlincheng.pika.common.core.base.query.BasePageQuery;
import com.wenlincheng.pika.upms.entity.po.GatewayRoute;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户分组分页查询参数
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "GatewayRoutePageQuery", description = "用户分组分页查询参数")
@Data
public class GatewayRoutePageQuery extends BasePageQuery<GatewayRoute> {

    @ApiModelProperty(value = "路由ID")
    private String routeId;

    @ApiModelProperty(value = "uri路径")
    private String uri;
}
