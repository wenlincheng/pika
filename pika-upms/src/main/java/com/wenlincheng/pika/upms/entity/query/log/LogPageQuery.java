package com.wenlincheng.pika.upms.entity.query.log;

import com.wenlincheng.pika.common.core.base.query.BasePageQuery;
import com.wenlincheng.pika.upms.entity.po.SysLog;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 操作日志查询参数
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "LogPageQuery", description = "操作日志分页查询参数")
public class LogPageQuery extends BasePageQuery<SysLog> {

    @ApiModelProperty(value = "日志标题")
    private String title;
    @ApiModelProperty(value = "服务ID")
    private String serviceId;
    @ApiModelProperty(value = "操作用户")
    private String username;
    @ApiModelProperty(value = "操作方法")
    private String method;
    @ApiModelProperty(value = "操作耗时")
    private Long time;
    @ApiModelProperty(value = "日志类型")
    private Integer type;
}
