package com.wenlincheng.pika.common.core.base.form;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

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
public class BaseForm<T> {

    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;
    @ApiModelProperty(value = "创建用户id")
    private Long createUserId;
    @ApiModelProperty(value = "修改用户id")
    private Long updateUserId;

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
