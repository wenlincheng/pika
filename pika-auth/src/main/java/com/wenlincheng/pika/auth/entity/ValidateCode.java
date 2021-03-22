package com.wenlincheng.pika.auth.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 验证码
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/3/21 9:15 下午
 */
@Data
@ApiModel(value = "ValidateCode", description = "验证码")
public class ValidateCode implements Serializable {

    private static final long serialVersionUID = 3380046429869331353L;

    @ApiModelProperty(value = "随机id")
    private String uuid;

    @ApiModelProperty(value = "验证码图片")
    private String codeImg;

}
