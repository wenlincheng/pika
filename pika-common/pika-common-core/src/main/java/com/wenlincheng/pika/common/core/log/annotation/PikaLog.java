package com.wenlincheng.pika.common.core.log.annotation;

import java.lang.annotation.*;

/**
 * 系统操作注解
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
	 * 描述说明
	 *
	 * @return String
	 */
	String value();

	/**
	 * 模块名称
	 *
	 * 例如：系统管理-用户管理
	 * @return String
	 */
	String module() default "";

	/**
	 * 方法
	 *
	 * 例如：新增用户
	 * @return String
	 */
	String method()  default "";

}
