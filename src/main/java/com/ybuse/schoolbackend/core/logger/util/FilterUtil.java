package com.ybuse.schoolbackend.core.logger.util;

import com.ybuse.schoolbackend.core.logger.annotation.ArgsFilter;
import com.ybuse.schoolbackend.core.logger.annotation.PrintLog;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;

public class FilterUtil {
    private FilterUtil() {

    }

    /**
     * 解析{@link PrintLog}注解
     *
     * @param signature 方法签名
     * @return {@link PrintLog}注解
     * @see PrintLog
     */
    private static PrintLog getAnnotationPrintLog(Signature signature) {
        PrintLog printLog = null;
        if (signature instanceof MethodSignature methodSignature) {
            printLog = AnnotationUtils.getAnnotation(methodSignature.getMethod(), PrintLog.class);
        }
        if (printLog == null) {
            printLog = AnnotationUtils.getAnnotation(signature.getDeclaringType(), PrintLog.class);
        }
        return printLog;
    }

    /**
     * 解析{@link ArgsFilter}注解
     *
     * @param signature 方法签名
     * @return {@link ArgsFilter}注解
     * @see ArgsFilter
     */
    private static ArgsFilter getAnnotationArgsFilter(Signature signature) {
        ArgsFilter argsFilter = null;
        if (signature instanceof MethodSignature methodSignature) {
            // ArgsFilter注解在方法上
            argsFilter = AnnotationUtils.getAnnotation(methodSignature.getMethod(), ArgsFilter.class);
        }
        if (argsFilter == null) {
            // ArgsFilter注解在类上
            argsFilter = AnnotationUtils.getAnnotation(signature.getDeclaringType(), ArgsFilter.class);
        }
        return argsFilter;
    }

    /**
     * 获取方法参数名
     *
     * @param signature 方法签名
     * @return 参数名
     */
    private static String[] getParameterNames(Signature signature) {
        if (signature instanceof MethodSignature methodSignature) {
            return methodSignature.getParameterNames();

        }
        return new String[0];
    }

    /**
     * 解析方法签名，获得filter对象
     *
     * @param signature 方法签名
     * @return filter对象
     */
    public static Filter of(Signature signature) {
        Filter filter = new Filter();
        filter.parameterNames = getParameterNames(signature);
        filter.printLog = getAnnotationPrintLog(signature);
        ArgsFilter annotationArgsFilter = getAnnotationArgsFilter(signature);
        if (annotationArgsFilter != null) {
            filter.typeFilters = annotationArgsFilter.typeFilter();
            filter.indexFilters = annotationArgsFilter.indexFilter();
            filter.nameFilters = annotationArgsFilter.nameFilter();
            filter.enableFilter = true;
        }
        return filter;
    }

}
