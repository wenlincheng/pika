package com.wenlincheng.pika.upms.entity.vo.region;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 地址区域列表
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Data
@ApiModel(value = "RegionListVO", description = "地址区域列表返回对象")
public class RegionListVO implements Serializable {

    private static final long serialVersionUID = -7518286089470450653L;

    @ApiModelProperty(value = "行政编码")
    private Long id;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "级别")
    private Integer level;
}
