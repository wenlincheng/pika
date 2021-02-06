package com.wenlincheng.pika.item.entity.form.item;

import lombok.Data;

/**
 * 商品属性值
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Data
public class Value {

    /**
     * 属性值
     */
    private String val;

    /**
     * 属性值图片url
     */
    private String picUrl;

    /**
     * 是否被选中
     */
    private Boolean isPick;

    /**
     * 是否自定义
     */
    private Boolean isCustom;
}
