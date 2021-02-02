package com.wenlincheng.pika.message.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.wenlincheng.pika.common.core.base.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 短信模板
 * </p>
 *
 * @author Pikaman
 * @since 2021-01-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sms_template")
public class SmsTemplate extends BaseModel<SmsTemplate> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 短信通道
     */
    @TableField("channel")
    private String channel;

    /**
     * 短信模板类型
     */
    @TableField("type")
    private String type;

    /**
     * 短信模板编号
     */
    @TableField("code")
    private String code;

    /**
     * 短信模板内容
     */
    @TableField("content")
    private String content;

    /**
     * 验证码有效时间（秒/s）
     */
    @TableField("expire_time")
    private Integer expireTime;

    /**
     * 是否留存
     */
    @TableField("has_store")
    private Integer hasStore;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
