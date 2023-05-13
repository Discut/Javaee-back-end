package com.ybuse.schoolbackend.core.logger.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 参数索引过滤器
 *
 * @see ArgsFilter
 */
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface IndexFilter {
    @AliasFor("ignoreArgIndex")
    byte[] value() default {};

    /**
     * 需要过滤参数的索引
     *
     * @return 参数索引
     */
    @AliasFor("value")
    byte[] ignoreArgIndex() default {};
}
