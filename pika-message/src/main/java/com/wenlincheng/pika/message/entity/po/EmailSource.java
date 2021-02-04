package com.wenlincheng.pika.message.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.wenlincheng.pika.common.core.base.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 邮件服务器
 * </p>
 *
 * @author Pikaman
 * @since 2021-02-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("email_message_source")
public class EmailSource extends BaseModel<EmailSource> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId("id")
    private Long id;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 用户
     */
    @TableField("smtp_user")
    private String smtpUser;

    /**
     * 密码
     */
    @TableField("smtp_password")
    private String smtpPassword;

    /**
     * 连接加密方案
     */
    @TableField("smtp_security")
    private String smtpSecurity;

    /**
     * smtp host
     */
    @TableField("smtp_host")
    private String smtpHost;

    /**
     * smtp 端口号
     */
    @TableField("smtp_port")
    private Integer smtpPort;

    /**
     * 是否激活
     */
    @TableField("active")
    private Integer active;

    /**
     * 消息通知类型
     */
    @TableField("type")
    private String type;

    /**
     * 若邮件没有指定服务器，使用最高优先级（数字越小，优先级越高）的服务器。
     */
    @TableField("sequence")
    private Integer sequence;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
