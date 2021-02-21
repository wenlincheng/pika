package com.wenlincheng.pika.upms.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.wenlincheng.pika.common.core.base.model.IdModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户组表
 * </p>
 *
 * @author Pikaman
 * @date 2021/1/1 10:10 上午
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_group")
public class SysGroup extends IdModel<SysGroup> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户组父id
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 用户组名称
     */
    @TableField("name")
    private String name;

    /**
     * 描述
     */
    @TableField("description")
    private String description;

}
