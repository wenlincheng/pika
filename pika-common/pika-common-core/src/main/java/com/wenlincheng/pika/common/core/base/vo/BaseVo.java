package com.wenlincheng.pika.common.core.base.vo;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.wenlincheng.pika.common.core.context.PikaUser;
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
 * @author wenlincheng
 */
@Data
@NoArgsConstructor
public class BaseVo<T> implements Serializable {
    private static final long serialVersionUID = -1102164501562674959L;

    public BaseVo(T t) {
        BeanUtils.copyProperties(t, this);
    }

    @ApiModelProperty(value = "id", required = true)
    private Long id;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

}
