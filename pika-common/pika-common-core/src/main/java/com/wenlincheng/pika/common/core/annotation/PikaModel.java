package com.wenlincheng.pika.common.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Model注解
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.TYPE })
public @interface PikaModel {

    /**
     * 编码注解
     */
    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface Code {

        /**
         * 类型
         */
        String type() default "";

        /**
         * 前缀
         */
        String prefix() default "";

        /**
         * 后缀
         */
        String suffix() default "";

        /**
         * 分隔符
         */
        String separator() default "";

        /**
         * 大小
         */
        int size() default 16;

        /**
         * 步长
         */
        int step() default 1;

        /**
         * 初始值
         */
        int initial() default 1000;

        /**
         * 时间格式
         */
        String format() default "";

    }


}
