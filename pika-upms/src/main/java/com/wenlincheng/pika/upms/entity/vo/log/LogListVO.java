package com.wenlincheng.pika.upms.entity.vo.log;

import com.wenlincheng.pika.common.core.base.vo.BaseListVo;
import com.wenlincheng.pika.upms.entity.po.SysLog;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 日志列表VO
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "LogListVO", description = "日志列表VO")
public class LogListVO extends BaseListVo<SysLog> {
    private static final long serialVersionUID = 6464896911081884691L;

    public LogListVO(SysLog log) {
        super(log);
    }

    @ApiModelProperty(value = "日志标题")
    private String title;

    @ApiModelProperty(value = "微服务ID")
    private String serviceId;

    @ApiModelProperty(value = "操作用户id")
    private Long userId;

    @ApiModelProperty(value = "操作用户")
    private String username;

    @ApiModelProperty(value = "客户端IP")
    private String clientIp;

    @ApiModelProperty(value = "请求URL")
    private String url;

    @ApiModelProperty(value = "请求URI")
    private String uri;

    @ApiModelProperty(value = "请求方式 GET POST DELETE PUT等")
    private String method;

    @ApiModelProperty(value = "请求执行时长 单位 ms")
    private Long time;

    @ApiModelProperty(value = "日志类型 1 正常日志 2 错误日志")
    private Integer type;
}
