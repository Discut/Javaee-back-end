package com.ybuse.schoolbackend.core.annotation.swagger;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于标记需要扫描的controller包
 *
 * @author Discut
 */
@Target(ElementType.PACKAGE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Mark {
    @AliasFor("tag")
    String value() default "";

    @AliasFor("value")
    String tag() default "";
}
