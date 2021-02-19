package com.wenlincheng.pika.common.core.base.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Past;
import java.util.Date;
import java.util.Objects;

/**
 * 分页查询参数基类
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@ApiModel(value = "分页查询参数基类")
@Data
public abstract class BasePageQuery<T> {

    @ApiModelProperty(value = "当前页数")
    private long current = 1;

    @ApiModelProperty(value = "每页显示数量")
    private long size = 10;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past(message = "查询开始时间必须小于当前日期")
    @ApiModelProperty(value = "查询开始时间")
    private Date createTimeStart;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past(message = "查询结束时间必须小于当前日期")
    @ApiModelProperty(value = "查询结束时间")
    private Date createTimeEnd;

    /**
     * 构建分页QueryWrapper
     *
     * @return QueryWrapper
     */
    public QueryWrapper<T> buildWrapper() {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge(Objects.nonNull(this.createTimeStart), "create_time", this.createTimeStart)
                .le(Objects.nonNull(this.createTimeEnd), "create_time", this.createTimeEnd);
        return queryWrapper;
    }

    /**
     * 获取分页参数
     *
     * @return PageParam 分页参数
     * @date 2021/1/1 10:10 上午
     */
    public PageParam<T> getPage() {
        return new PageParam<>(this.getCurrent(), this.getSize());
    }

}
