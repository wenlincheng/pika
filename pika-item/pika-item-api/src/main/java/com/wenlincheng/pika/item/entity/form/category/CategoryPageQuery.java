package com.wenlincheng.pika.item.entity.form.category;

import com.wenlincheng.pika.common.core.base.query.BasePageQuery;
import com.wenlincheng.pika.item.entity.po.Category;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 类目分页查询参数
 *
 * @author Pikaman
 * @version 1.0.0
 * @date : 2021/1/1 10:10 上午下午
 */
@ApiModel(value = "CategoryPageQuery", description = "类目分页查询参数")
@EqualsAndHashCode(callSuper = true)
@Data
public class CategoryPageQuery extends BasePageQuery<Category> {

    @ApiModelProperty(value = "类目名称")
    private String name;

    @ApiModelProperty(value = "父级id")
    private Integer parentId;
}
