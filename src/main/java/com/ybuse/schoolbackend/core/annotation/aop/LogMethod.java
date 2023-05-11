package com.ybuse.schoolbackend.core.annotation.aop;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({java.lang.annotation.ElementType.METHOD})
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Documented
public @interface LogMethod {
}
