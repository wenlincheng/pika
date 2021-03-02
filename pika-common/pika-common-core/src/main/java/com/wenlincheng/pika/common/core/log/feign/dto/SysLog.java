package com.wenlincheng.pika.common.core.log.feign.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 日志
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Data
public class SysLog implements Serializable {
    private static final long serialVersionUID = 6906500234281030363L;
    /**
     * 日志标题
     */
    private String title;

    /**
     * 微服务ID
     */
    private String serviceId;

    /**
     * 操作用户id
     */
    private Long userId;

    /**
     * 操作用户名
     */
    private String username;

    /**
     * 客户端IP
     */
    private String clientIp;

    /**
     * 用户代理
     */
    private String userAgent;

    /**
     * 请求URL
     */
    private String url;

    /**
     * 请求URI
     */
    private String uri;

    /**
     * 请求方式 GET POST DELETE PUT等
     */
    private String method;

    /**
     * 操作提交请求参数
     */
    private String params;

    /**
     * 请求执行时长 单位 ms
     */
    private Long time;

    /**
     * 异常信息
     */
    private String exceptionMessage;

    /**
     * 日志类型 1 正常日志 2 错误日志
     */
    private Integer type;

}
