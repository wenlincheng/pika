package com.wenlincheng.pika.common.core.base.form;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 表单基类
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@ApiModel(value = "表单基类")
@Data
public abstract class BaseForm<T> implements Serializable {

    private static final long serialVersionUID = 6870300955287532306L;

    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 表单转化为Po
     *
     * @param clazz 类
     * @return T
     */
    public T toPo(Class<T> clazz) {
        T t = BeanUtils.instantiateClass(clazz);
        BeanUtils.copyProperties(this, t);
        return t;
    }
}
