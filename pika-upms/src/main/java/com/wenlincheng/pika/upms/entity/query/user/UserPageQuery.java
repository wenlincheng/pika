package com.wenlincheng.pika.upms.entity.query.user;

import com.wenlincheng.pika.common.core.base.query.BasePageQuery;
import com.wenlincheng.pika.upms.entity.po.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户分页查询参数
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Data
@ApiModel(value = "UserPageQuery", description = "用户分页查询参数")
public class UserPageQuery extends BasePageQuery<User> {

    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "用户姓名")
    private String name;
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "状态")
    private String status;
}
