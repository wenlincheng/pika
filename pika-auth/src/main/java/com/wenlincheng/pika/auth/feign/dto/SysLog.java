package com.wenlincheng.pika.auth.feign.dto;

import lombok.Data;

import java.util.Date;

/**
 * TODO
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Data
public class SysLog {
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
     * 操作IP地址
     */
    private String userIp;

    /**
     * 用户代理
     */
    private String userAgent;

    /**
     * 请求URI
     */
    private String requestUri;

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

    /**
     * 是否已删除 0 否 1 是
     */
    private Integer isDeleted;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
