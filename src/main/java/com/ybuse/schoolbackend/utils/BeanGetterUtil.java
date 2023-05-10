package com.ybuse.schoolbackend.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


@Deprecated
public class BeanGetterUtil implements ApplicationContextAware {
    /**
     * spring上下文
     */
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BeanGetterUtil.applicationContext=applicationContext;
    }

    /**
     * 获取spring上下文
     * @return
     */
    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    /**
     * 获取bean
     * @param name  bean名称
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name){
        if(applicationContext==null){
            return null;
        }
        return (T) applicationContext.getBean(name);
    }

    /**
     * 获取bean
     * @param requiredType bean类型
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> requiredType){
        if(applicationContext==null){
            return null;
        }
        return applicationContext.getBean(requiredType);
    }

    /**
     * 获取bean
     * @param name  bean名称
     * @param requiredType  bean类型
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name, Class<T> requiredType){
        if(applicationContext==null){
            return null;
        }
        return applicationContext.getBean(name,requiredType);
    }
}
