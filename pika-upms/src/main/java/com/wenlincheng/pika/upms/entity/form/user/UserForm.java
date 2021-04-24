package com.wenlincheng.pika.upms.entity.form.user;

import com.wenlincheng.pika.common.core.base.form.BaseForm;
import com.wenlincheng.pika.upms.entity.po.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Set;

/**
 * 用户表单
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Data
@ApiModel
public class UserForm  extends BaseForm<User> {

    private static final long serialVersionUID = -3293566082824871698L;

    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "用户名不能为空")
    @Length(min = 3, max = 20, message = "用户名长度在3到20个字符")
    private String username;

    @ApiModelProperty(value = "手机号")
    @NotBlank(message = "手机号不能为空")
    @Pattern(message = "手机号格式不正确", regexp = "^\\d{11}$")
    private String mobile;

    @ApiModelProperty(value = "姓名")
    @NotBlank(message = "姓名不能为空")
    private String name;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "角色")
    private Set<Long> roleIds;

    @ApiModelProperty(value = "启用状态")
    private String status;
}
