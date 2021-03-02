package com.wenlincheng.pika.common.core.base.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 请求参数基类
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Data
public abstract class BaseQuery<T> {

    @ApiModelProperty(value = "开始时间")
    private Date createTimeStart;

    @ApiModelProperty(value = "结束时间")
    private Date createTimeEnd;

    public QueryWrapper<T> build() {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge(this.createTimeStart != null, "create_time", this.createTimeStart)
                .le(this.createTimeEnd != null, "create_time", this.createTimeEnd);
        return queryWrapper;
    }
}
