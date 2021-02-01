package com.wenlincheng.pika.upms.entity.query.group;

import com.wenlincheng.pika.common.core.base.query.BasePageQuery;
import com.wenlincheng.pika.upms.entity.po.SysGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户分组分页查询参数
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Data
@ApiModel(value = "GroupPageQuery", description = "用户分组分页查询参数")
public class GroupPageQuery extends BasePageQuery<SysGroup> {

    @ApiModelProperty(value = "用户组名称", required = true)
    private String name;
}
