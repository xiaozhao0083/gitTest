package com.yc.springTest1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @program: gitTest
 * description:
 * @author:yyq
 * @create: 2023-07-25 20:56
 */
@Configuration
@EnableAsync
public class ThreadPoolConfig {
    /**
     * 核心线程池大小50
     */
    private static final int CORE_POOL_SIZE = 10;

    /**
     * 最大可创建的线程数200
     */
    private static final int MAX_POOL_SIZE = 10;

    /**
     * 队列最大长度1000
     */
    private static final int QUEUE_CAPACITY = 10;

    /**
     * 线程池维护线程所允许的空闲时间300
     */
    private static final int KEEP_ALIVE_SECONDS = 300;

    /**
     * 异步执行方法线程池
     *
     * @return
     */
    @Bean(name = "asyncExecutor")
    public ThreadPoolTaskExecutor eventExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setQueueCapacity(QUEUE_CAPACITY);
        executor.setKeepAliveSeconds(KEEP_ALIVE_SECONDS);
        // 线程池对拒绝任务(无线程可用)的处理策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }
}
