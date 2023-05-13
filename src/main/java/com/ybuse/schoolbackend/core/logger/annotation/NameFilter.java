package com.ybuse.schoolbackend.core.logger.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 参数名过滤器<br>
 * 该过滤方式在java编译后也许会失效，即参数名被java编译混淆。不建议使用。
 *
 * @see ArgsFilter
 */
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface NameFilter {
    @AliasFor("ignoreArgNames")
    String[] value() default {};

    /**
     * 需过滤参数的名称
     *
     * @return 参数名
     */
    @AliasFor("value")
    String[] ignoreArgNames() default {};
}
