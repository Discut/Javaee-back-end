package com.ybuse.schoolbackend.core;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * 注释某Controller路径为/api/v1/*
 * @author Discut
 */
@Target(value = {java.lang.annotation.ElementType.TYPE})
@Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
@RestController
@RequestMapping(produces = "application/json;charset=utf-8")
public @interface ApiV1Controller {

    @AliasFor(annotation = RequestMapping.class)
    String name() default "";

    @AliasFor(annotation = RequestMapping.class)
    String[] value() default {};

    @AliasFor(annotation = RequestMapping.class)
    String[] path() default {};
}
