package com.wenlincheng.pika.upms.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.wenlincheng.pika.common.core.base.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.wenlincheng.pika.common.core.base.model.IdModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 数据字典值
 * </p>
 *
 * @author Pikaman
 * @since 2021-04-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("dict_value")
public class DictValue extends IdModel<DictValue> {

    private static final long serialVersionUID = 1L;

    /**
     * 数据字典类型id
     */
    @TableField("dict_type_id")
    private Long dictTypeId;

    /**
     * 字典值名称
     */
    @TableField("name")
    private String name;

    /**
     * 字典值
     */
    @TableField("value")
    private String value;

    /**
     * 状态
     */
    @TableField("status")
    private String status;

    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 排序
     */
    @TableField("description")
    private String description;


}
