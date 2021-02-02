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
 * 邮件模板
 * </p>
 *
 * @author Pikaman
 * @since 2021-01-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("email_template")
public class EmailTemplate extends BaseModel<EmailTemplate> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 模板编号
     */
    @TableField("code")
    private String code;

    /**
     * 邮件标题
     */
    @TableField("title")
    private String title;

    /**
     * 邮件内容
     */
    @TableField("content")
    private String content;

    /**
     * 用户签名id
     */
    @TableField("user_sign_id")
    private Long userSignId;

    /**
     * 邮件服务器id
     */
    @TableField("sender_source_id")
    private Long senderSourceId;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
