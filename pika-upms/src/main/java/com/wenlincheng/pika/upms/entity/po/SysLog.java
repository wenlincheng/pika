package com.wenlincheng.pika.upms.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统操作日志表
 * </p>
 *
 * @author Pikaman
 * @date 2021/1/1 10:10 上午
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_log")
public class SysLog extends Model<SysLog> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 日志标题
     */
    @TableField("title")
    private String title;

    /**
     * 微服务ID
     */
    @TableField("service_id")
    private String serviceId;

    /**
     * 操作用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 操作用户名
     */
    @TableField("username")
    private String username;

    /**
     * 客户端IP
     */
    @TableField("client_ip")
    private String clientIp;

    /**
     * 用户代理
     */
    @TableField("user_agent")
    private String userAgent;

    /**
     * 请求URL
     */
    @TableField("url")
    private String url;

    /**
     * 请求URI
     */
    @TableField("uri")
    private String uri;

    /**
     * 请求方式 GET POST DELETE PUT等
     */
    @TableField("method")
    private String method;

    /**
     * 操作提交请求参数
     */
    @TableField("params")
    private String params;

    /**
     * 请求执行时长 单位 ms
     */
    @TableField("time")
    private Long time;

    /**
     * 异常信息
     */
    @TableField("exception_message")
    private String exceptionMessage;

    /**
     * 日志类型 1 正常日志 2 错误日志
     */
    @TableField("type")
    private Integer type;

    /**
     * 是否已删除 0 否 1 是
     */
    @TableField("is_deleted")
    private Integer isDeleted;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
