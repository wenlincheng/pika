package com.wenlincheng.pika.common.core.annotation;

import java.lang.annotation.*;

/**
 * 类属性值比较注解
 *
 * @author wenlincheng
 * @version 1.0
 * @date 2020/05/13 12:39 下午
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Documented
public @interface Field {

    /**
     * 是否为序列号
     * @return
     */
    boolean id() default false;
    /**
     * 字段名称
     * @return
     */
    String name() default "";
    /**
     * 是否可编辑
     * @return
     */
    boolean editable() default true;
    /**
     * 是否在列表中显示
     * @return
     */
    boolean summary() default true;
    /**
     * 字段描述
     * @return
     */
    String description() default "";
    /**
     * 排序字段
     * @return
     */
    int order() default 0;
    /**
     * 是否是枚举 默认不是
     * @return
     */
    int isEnum() default 0;
    /**
     * 使用的枚举类型
     * @return
     */
    String enumStr() default "";

    /**
     * 内部枚举类名称
     *
     * @return
     */
    String innerEnumName() default "";
    /**
     * 默认是0  显示
     * @return
     */
    int isShow() default 0;

}
