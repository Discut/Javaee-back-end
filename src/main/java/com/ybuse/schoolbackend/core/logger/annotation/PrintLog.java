package com.ybuse.schoolbackend.core.logger.annotation;

import com.ybuse.schoolbackend.core.logger.MethodType;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 日志打印注解<br>
 * 该注解可在Class与Method上使用，Method上优先级更高。<br>
 *
 * @see com.ybuse.schoolbackend.core.logger.aop.LogAspect
 * @see com.ybuse.schoolbackend.core.logger.annotation.PrintLog
 */
@Target({java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.TYPE})
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Documented
public @interface PrintLog {

    @AliasFor(value = "tags")
    String[] value() default {"default"};

    /**
     * 方法的标签
     *
     * @return 日志标签
     */
    @AliasFor(value = "value")
    String[] tags() default {"default"};

    /**
     * 是否打印结果
     *
     * @return true打印结果
     */
    boolean resultLog() default true;


    /**
     * 是否打印入参
     *
     * @return true打印入参
     */
    boolean paramLog() default true;

    /**
     * 方法类型
     *
     * @return 方法类型
     */
    MethodType methodType() default MethodType.UNKNOWN;

}
