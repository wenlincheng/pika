package com.wenlincheng.pika.common.core.base.model;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Model基类
 *
 * @author : Pikaman
 * @version : 1.0.0
 * @date : 2021/1/31 12:07 上午
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseModel<T extends Model<?>> extends Model<T> {

    private static final long serialVersionUID = -6387524599153408156L;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;

    /**
     * 创建人ID
     */
    @TableField(value = "create_user_id", fill = FieldFill.INSERT)
    private Long createUserId;

    /**
     * 更新人ID
     */
    @TableField(value = "update_user_id", fill = FieldFill.UPDATE)
    private Long updateUserId;

    /**
     * 逻辑删除
     */
    @TableField(value = "is_deleted", fill = FieldFill.INSERT)
    private Integer isDeleted;
}
