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

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface Code {

        String prefix() default "";

        String suffix() default "";

        String separator() default "";

        int size() default 16;

        int step() default 1;

        int initial() default 1000;

        String format() default "";

    }


}
