package com.wenlincheng.pika.item.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import java.io.Serializable;

import com.wenlincheng.pika.common.core.base.model.IdModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 分类
 * </p>
 *
 * @author Pikaman
 * @date 2021/1/1 10:10 上午
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("category")
public class Category extends IdModel<Category> {

    private static final long serialVersionUID = 1L;

    /**
     * 类别名称
     */
    @TableField(value = "name", condition = SqlCondition.LIKE)
    private String name;

    /**
     * 是否有子类 0 否 1 是
     */
    @TableField("has_children")
    private Integer hasChildren;

    /**
     * 父级id
     */
    @TableField("parent_id")
    private Integer parentId;

    /**
     * 级别
     */
    @TableField("level")
    private Integer level;

    /**
     * 类别缩略图
     */
    @TableField("path_id")
    private String pathId;

    /**
     * 种类背景图
     */
    @TableField("path")
    private String path;

}
