package com.ybuse.schoolbackend.core.logger.util;

import com.ybuse.schoolbackend.core.logger.annotation.IndexFilter;
import com.ybuse.schoolbackend.core.logger.annotation.NameFilter;
import com.ybuse.schoolbackend.core.logger.annotation.PrintLog;
import com.ybuse.schoolbackend.core.logger.annotation.TypeFilter;

import java.util.LinkedList;
import java.util.List;

/**
 * 过滤器功能实现类
 *
 * @see FilterUtil
 */
public class Filter {
    /**
     * 日志注解
     */
    protected PrintLog printLog;

    protected boolean enableFilter = false;

    /**
     * 当前方法参数名称
     */
    protected String[] parameterNames;
    /**
     * 参数类型过滤器
     */
    protected TypeFilter typeFilters;
    /**
     * 参数索引过滤器
     */
    protected IndexFilter indexFilters;
    /**
     * 参数名称过滤器
     */
    protected NameFilter nameFilters;

    public PrintLog getPrintLog() {
        return this.printLog;
    }

    /**
     * 过滤参数
     *
     * @param args 待过滤的参数
     * @return 过滤后的参数
     */
    public Object[] filterArgs(final Object[] args) {
        if (!this.enableFilter) {
            return args;
        }
        final Class<Object>[] classes = this.typeFilters.ignoreArgType();
        final String[] argNames = this.nameFilters.ignoreArgNames();
        final byte[] argIndex = this.indexFilters.ignoreArgIndex();
        byte[] argIsExist = new byte[args.length];
        for (int i = 0; i < args.length; i++) {
            // type filter优先级更高
            for (Class<Object> aClass : classes) {
                if (aClass.isAssignableFrom(args[i].getClass())) {
                    argIsExist[i] = -1;
                    break;
                }
            }
            for (int j = 0; j < argIndex.length && argIndex.length <= args.length; j++) {
                if (argIndex[j] >= 0) {
                    argIsExist[j] = -1;
                    break;
                }
            }
            for (String argName : argNames) {
                if (argName.equals(parameterNames[i])) {
                    argIsExist[i] = -1;
                    break;
                }
            }
        }
        List<Object> results = new LinkedList<>();
        for (int i = 0; i < argIsExist.length; i++) {
            if (argIsExist[i] != -1) {
                results.add(args[i]);
            }
        }
        return results.toArray();
    }

}
