package com.wenlincheng.pika.upms.entity.query.organization;

import com.wenlincheng.pika.common.core.base.query.BasePageQuery;
import com.wenlincheng.pika.upms.entity.po.Organization;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 组织分页查询参数
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Data
@ApiModel(value = "OrganizationPageQuery", description = "组织分页查询参数")
public class OrganizationPageQuery extends BasePageQuery<Organization> {

    @ApiModelProperty(value = "组织名称", required = true)
    private String name;
}
