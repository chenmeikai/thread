package com.kenhome.thread.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author cmk
 * @Description 定时任务调度开关
 * @Date 2018\8\19 0019 22:06
 */

@RestController
public class ScheduledController {

    @Autowired
    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;

    private static  final Map<String,ScheduledFuture<?>> scheduledFutureMap =new HashMap<>();

    private  static final Logger log =LoggerFactory.getLogger(ScheduledController.class);

    @GetMapping("start")
    public String start(String threadName,String message){
        System.out.println("开启定时任务....."); 


        //查看该线程是否运行中
        ScheduledFuture<?> exist = scheduledFutureMap.get(threadName);
        if (exist != null) {
            log.warn("自动任务开启失败,该任务已经在运行");
            return "fail";
        }

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setName(threadName);

        TimeUnit timeUnit = TimeUnit.SECONDS;
        ScheduledFuture<?> scheduledFuture = scheduledThreadPoolExecutor.scheduleAtFixedRate(thread, 1, 10, timeUnit);

        scheduledFutureMap.put(threadName,scheduledFuture);

        return  "success";
    }


    @GetMapping("stop")
    public String stop(String threadName){
        System.out.println("结束定时任务.....");

        //查看该线程是否运行中
        ScheduledFuture<?> scheduledFuture = scheduledFutureMap.get(threadName);
        if (scheduledFuture == null) {
            log.warn("自动任务停止失败,该任务没有在运行");
            return "fail";
        }

        scheduledFuture.cancel(true);
        scheduledFutureMap.remove(threadName);

        return  "success";
    }




    //获得线程
    public Thread getThreadByName(String threadName) {
        ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();
        int noThreads = currentGroup.activeCount();
        Thread[] lstThreads = new Thread[noThreads];
        currentGroup.enumerate(lstThreads);
        log.info("遍历线程数" + noThreads);
        for (int i = 0; i < noThreads; i++) {
            String nm = lstThreads[i].getName();
            if (nm.equals(threadName)) {
                log.info("遍历查询到目标线程:{}",threadName);
                return lstThreads[i];
            }
        }
        log.info("没有查询到目标线程:{}",threadName);
        return null;
    }



}
