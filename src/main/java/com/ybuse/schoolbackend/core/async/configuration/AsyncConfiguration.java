package com.ybuse.schoolbackend.core.async.configuration;


import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootConfiguration
@EnableAsync
public class AsyncConfiguration {
    /**
     * <a href="https://juejin.cn/post/7198039436037701687#heading-7">合理配置线程数</a>
     */
    @Bean("asyncIoExecutor")
    public ThreadPoolTaskExecutor threadPoolIoTaskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10); // 设置核心线程数
        executor.setMaxPoolSize(20); // 设置最大线程数
        executor.setQueueCapacity(200); // 设置队列大小
        executor.setThreadNamePrefix("AsyncIoExecutor-"); // 设置线程名前缀
        executor.initialize();
        return executor;
    }
    @Bean("asyncCalculateExecutor")
    public ThreadPoolTaskExecutor threadPoolCalculateTaskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4); // 设置核心线程数
        executor.setMaxPoolSize(5); // 设置最大线程数
        executor.setQueueCapacity(200); // 设置队列大小
        executor.setThreadNamePrefix("AsyncCalculateExecutor-"); // 设置线程名前缀
        executor.initialize();
        return executor;
    }
}
