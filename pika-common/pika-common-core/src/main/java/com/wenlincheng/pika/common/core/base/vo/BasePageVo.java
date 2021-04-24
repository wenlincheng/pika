package com.wenlincheng.pika.common.core.base.vo;

import com.wenlincheng.pika.common.core.session.PikaUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * 列表VO基类
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public abstract class BasePageVo<T> extends BaseVo<T> {
    private static final long serialVersionUID = -1102164501562674959L;

    public BasePageVo(T t) {
        BeanUtils.copyProperties(t, this);
    }

    @ApiModelProperty(value = "创建人")
    private PikaUser createUser;

    @ApiModelProperty(value = "修改人")
    private PikaUser updateUser;
}
