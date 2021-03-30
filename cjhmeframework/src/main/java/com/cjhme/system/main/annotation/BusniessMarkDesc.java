package com.cjhme.system.main.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 描述：业务标识描述
 * @author cjh
 * @created 2016-3-30 下午5:28:24
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
@Documented
public @interface BusniessMarkDesc {

	/**
	 * 
	 * 描述：描述
	 * @author cjh
	 * @created 2016-3-30 下午5:29:50
	 * @return
	 */
	String description() default "";
}
