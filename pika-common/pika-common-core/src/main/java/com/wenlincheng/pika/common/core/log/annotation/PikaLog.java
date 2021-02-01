package com.wenlincheng.pika.common.core.log.annotation;

import java.lang.annotation.*;

/**
 * PikaLog 注解
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PikaLog {

	/**
	 * 描述
	 * @return String
	 */
	String value();

}
