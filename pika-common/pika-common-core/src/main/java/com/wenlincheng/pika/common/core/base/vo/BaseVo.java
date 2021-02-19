package com.wenlincheng.pika.common.core.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * VO基类
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Data
@NoArgsConstructor
public abstract class BaseVo<T> implements Serializable {
    private static final long serialVersionUID = -1102164501562674959L;

    public BaseVo(T t) {
        BeanUtils.copyProperties(t, this);
    }

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

}
