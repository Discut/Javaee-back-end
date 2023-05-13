package com.ybuse.schoolbackend.core.logger.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 参数类型过滤器
 *
 * @see ArgsFilter
 */
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface TypeFilter {
    @AliasFor("ignoreArgType")
    Class[] value() default {};

    /**
     * 打印日志时，忽略的参数
     *
     * @return class数组
     */
    @AliasFor("value")
    Class[] ignoreArgType() default {};
}
