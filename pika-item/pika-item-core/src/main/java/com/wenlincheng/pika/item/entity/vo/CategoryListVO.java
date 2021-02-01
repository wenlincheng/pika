package com.wenlincheng.pika.item.entity.vo;

import com.wenlincheng.pika.common.core.base.vo.BaseVo;
import com.wenlincheng.pika.item.entity.po.Category;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 类目列表VO
 *
 * @author Pikaman
 * @version 1.0.0
 * @date : 2021/1/1 10:10 上午下午
 */
@ApiModel(value = "类目列表VO")
@EqualsAndHashCode(callSuper = true)
@Data
public class CategoryListVO extends BaseVo<Category> {

    private static final long serialVersionUID = 2072983779491882123L;

    public CategoryListVO(Category category) {
        super(category);
    }

    @ApiModelProperty(value = "类目名称")
    private String name;
    @ApiModelProperty(value = "类别路径名称")
    private String path;
    @ApiModelProperty(value = "类别路径id")
    private String pathId;
    @ApiModelProperty(value = "级别")
    private Integer level;
    @ApiModelProperty(value = "父级id")
    private Integer parentId;
    @ApiModelProperty(value = "是否有子级")
    private Integer hasChildren;

}
