package com.ybuse.schoolbackend.core.logger.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 方法参数过滤器<br>
 * 用于过滤需要打印日志的方法的参数。使用该注解请与{@link PrintLog}配对<br>
 * 过滤器优先级为 TypeFilter > IndexFilter > NameFilter, 优先级高的会优先过滤
 *
 * @see com.ybuse.schoolbackend.core.logger.annotation.PrintLog
 * @see com.ybuse.schoolbackend.core.logger.annotation.TypeFilter 参数类型过滤器
 * @see com.ybuse.schoolbackend.core.logger.annotation.IndexFilter 参数索引过滤器
 * @see com.ybuse.schoolbackend.core.logger.annotation.NameFilter 参数名过滤器
 */
@Target({java.lang.annotation.ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface ArgsFilter {

    @AliasFor("typeFilter")
    TypeFilter value() default @TypeFilter;

    /**
     * 打印日志时，忽略的参数
     *
     * @return 参数类型过滤器
     */
    @AliasFor("value")
    TypeFilter typeFilter() default @TypeFilter;

    /**
     * 忽略参数的索引
     *
     * @return 参数索引过滤器
     */
    IndexFilter indexFilter() default @IndexFilter;

    /**
     * 忽略参数的参数名
     *
     * @return 参数名过滤器
     */
    NameFilter nameFilter() default @NameFilter;
}
