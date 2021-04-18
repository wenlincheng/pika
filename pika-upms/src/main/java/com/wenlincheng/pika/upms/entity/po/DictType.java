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
 * 数据字典类型
 * </p>
 *
 * @author Pikaman
 * @since 2021-04-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("dict_type")
public class DictType extends IdModel<DictType> {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 编码
     */
    @TableField("code")
    private String code;


}
