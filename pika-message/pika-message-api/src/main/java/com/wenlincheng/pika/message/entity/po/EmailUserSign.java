package com.wenlincheng.pika.message.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * 邮件签名
 * </p>
 *
 * @author Pikaman
 * @since 2021-01-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("email_user_sign")
public class EmailUserSign extends Model<EmailUserSign> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId("id")
    private Long id;

    /**
     * 签名
     */
    @TableField("signature")
    private String signature;

    @TableField("user_id")
    private Long userId;

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

    /**
     * 创建人
     */
    @TableField("create_user_id")
    private Long createUserId;

    /**
     * 更新人
     */
    @TableField("update_user_id")
    private Long updateUserId;

    /**
     * 逻辑删除
     */
    @TableField("is_deleted")
    private Long isDeleted;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
