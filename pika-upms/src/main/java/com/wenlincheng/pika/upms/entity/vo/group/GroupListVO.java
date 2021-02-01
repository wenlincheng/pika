package com.wenlincheng.pika.upms.entity.vo.group;

import com.wenlincheng.pika.common.core.base.vo.BaseVo;
import com.wenlincheng.pika.upms.entity.po.SysGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 组织列表VO
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "GroupListVO", description = "组织列表VO")
public class GroupListVO extends BaseVo<SysGroup> {

    private static final long serialVersionUID = 5531780696582407364L;

    public GroupListVO(SysGroup group) {
        super(group);
    }
    @ApiModelProperty(value = "父级id")
    private Long parentId;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "描述")
    private String description;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
