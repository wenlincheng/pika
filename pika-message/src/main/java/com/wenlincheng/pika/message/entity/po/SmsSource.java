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
 * 短信供应商
 * </p>
 *
 * @author Pikaman
 * @since 2021-02-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sms_message_source")
public class SmsSource extends BaseModel<SmsSource> {

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
     * 短信通道
     */
    @TableField("channel")
    private String channel;

    /**
     * 短信签名名称
     */
    @TableField("sign_name")
    private String signName;

    /**
     * 主账号AccessKey的ID
     */
    @TableField("access_key_id")
    private String accessKeyId;

    /**
     * 主账号AccessKey的密钥
     */
    @TableField("access_key_secret")
    private String accessKeySecret;

    /**
     * SMSAction
     */
    @TableField("action")
    private String action;

    /**
     * 发送渠道Endpoint
     */
    @TableField("endpoint")
    private String endpoint;

    /**
     * API支持的RegionID
     */
    @TableField("region_id")
    private String regionId;

    /**
     * 时区
     */
    @TableField("time_zone")
    private String timeZone;

    /**
     * 签名方式
     */
    @TableField("signature_method")
    private String signatureMethod;

    /**
     * 签名算法版本
     */
    @TableField("signature_version")
    private String signatureVersion;

    /**
     * API的版本号
     */
    @TableField("version")
    private String version;

    /**
     * 消息通知类型
     */
    @TableField("type")
    private String type;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
