package com.wenlincheng.pika.upms.entity.vo.region;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 地址区域详情
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Data
@ApiModel(value = "RegionDetailVO", description = "地址区域详情返回对象")
public class RegionDetailVO implements Serializable {

    private static final long serialVersionUID = 802760342084354761L;

    @ApiModelProperty(value = "行政编码")
    private Long id;
    @ApiModelProperty(value = "名称四种情况 \n省 \n省 市 \n省 市 区/县 \n省 市 区/县 乡镇/街道")
    private String fullName;

}
