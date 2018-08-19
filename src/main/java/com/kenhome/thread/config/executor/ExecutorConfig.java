package com.kenhome.thread.config.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: cmk
 * @Description: 线程池
 * @Date: 2018\7\19 0019 23:11
 */
@Configuration
@EnableAsync
public class ExecutorConfig {

    private static final Logger logger = LoggerFactory.getLogger(ExecutorConfig.class);

    @Bean
    public Executor asyncServiceExecutor() {
        logger.info("start asyncServiceExecutor");
        ExtendThreadPoolTaskExecutor executor = new ExtendThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(5);
        //配置最大线程数
        executor.setMaxPoolSize(5);
        //配置队列大小
        executor.setQueueCapacity(1000);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("async-service-");

        /**
         *rejection-policy：当pool已经达到max size的时候，如何处理新任务
         * 1、直接丢弃（DiscardPolicy） 2、丢弃队列中最老的任务(DiscardOldestPolicy) 3、抛异常(AbortPolicy) 4、将任务分给调用线程来执行(CallerRunsPolicy)
         */
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        //执行初始化
        executor.initialize();

        return executor;
    }


    /**
     * @Description 单例线程
     * @param
     * @return java.util.concurrent.Executor
     */
    @Bean
    public Executor singleServiceExecutor() {
        logger.info("start singleServiceExecutor");
        ExtendThreadPoolTaskExecutor executor = new ExtendThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(1);
        //配置最大线程数
        executor.setMaxPoolSize(1);
        //配置队列大小
        executor.setQueueCapacity(0);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("single-service-");

        /**
         *rejection-policy：当pool已经达到max size的时候，如何处理新任务
         * 1、直接丢弃（DiscardPolicy） 2、丢弃队列中最老的任务(DiscardOldestPolicy) 3、抛异常(AbortPolicy) 4、将任务分给调用线程来执行(CallerRunsPolicy)
         */
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        //执行初始化
        executor.initialize();

        return executor;
    }

    /**
     * @Description 定时任务
     * @param
     * @return java.util.concurrent.ScheduledThreadPoolExecutor
     */
    @Bean
    public ScheduledThreadPoolExecutor scheduledThreadPoolExecutor(){
        ScheduledThreadPoolExecutor  scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        return  scheduledThreadPoolExecutor;
    }


}
