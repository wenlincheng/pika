package com.wenlincheng.pika.upms.entity.vo.log;

import com.wenlincheng.pika.common.core.base.vo.BaseVo;
import com.wenlincheng.pika.upms.entity.po.SysLog;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 日志详情VO
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "LogDetailVO", description = "日志详情VO")
public class LogDetailVO extends BaseVo<SysLog> {
    private static final long serialVersionUID = -7645120848466846520L;

    public LogDetailVO(SysLog log) {
        super(log);
    }

    @ApiModelProperty(value = "日志标题")
    private String title;

    @ApiModelProperty(value = "微服务ID")
    private String serviceId;

    @ApiModelProperty(value = "操作用户id")
    private Long userId;

    @ApiModelProperty(value = "操作用户名")
    private String username;

    @ApiModelProperty(value = "客户端IP")
    private String clientIp;

    @ApiModelProperty(value = "用户代理")
    private String userAgent;

    @ApiModelProperty(value = "请求URL")
    private String url;

    @ApiModelProperty(value = "请求URI")
    private String uri;

    @ApiModelProperty(value = "请求方式 GET POST DELETE PUT等")
    private String method;

    @ApiModelProperty(value = "操作提交请求参数")
    private String params;

    @ApiModelProperty(value = "请求执行时长 单位 ms")
    private Long time;

    @ApiModelProperty(value = "异常信息")
    private String exceptionMessage;

    @ApiModelProperty(value = "日志类型 1 正常日志 2 错误日志")
    private Integer type;
}
