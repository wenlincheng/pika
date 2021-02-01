package com.wenlincheng.pika.upms.entity.vo.gateway;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wenlincheng.pika.upms.entity.form.gateway.FilterDefinition;
import com.wenlincheng.pika.upms.entity.po.GatewayRoute;
import com.wenlincheng.pika.upms.entity.form.gateway.PredicateDefinition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 网关路由返回对象
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Data
@Slf4j
@ApiModel(value = "GatewayRouteVo", description = "网关路由返回对象")
public class GatewayRouteVO implements Serializable {
    private static final long serialVersionUID = 7892341151822583034L;

    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "路由id")
    private String routeId;
    @ApiModelProperty(value = "描述")
    private String description;
    @ApiModelProperty(value = "状态")
    private Integer status;
    @ApiModelProperty(value = "uri")
    private String uri;
    @ApiModelProperty(value = "排序")
    private Integer seq;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    @ApiModelProperty(value = "过滤器列表")
    private List<FilterDefinition> filters = new ArrayList<>();
    @ApiModelProperty(value = "判定器列表")
    private List<PredicateDefinition> predicates = new ArrayList<>();

    public GatewayRouteVO(GatewayRoute gatewayRoute) {
        this.id = gatewayRoute.getId();
        this.routeId = gatewayRoute.getRouteId();
        this.uri = gatewayRoute.getUri();
        this.description = gatewayRoute.getDescription();
        this.status = gatewayRoute.getStatus();
        this.seq = gatewayRoute.getSeq();
        this.createTime = gatewayRoute.getCreateTime();
        this.updateTime = gatewayRoute.getUpdateTime();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            this.filters = objectMapper.readValue(gatewayRoute.getFilters(), new TypeReference<List<FilterDefinition>>() {
            });
            this.predicates = objectMapper.readValue(gatewayRoute.getPredicates(), new TypeReference<List<PredicateDefinition>>() {
            });
        } catch (IOException e) {
            log.error("网关路由对象转换失败", e);
        }
    }
}
