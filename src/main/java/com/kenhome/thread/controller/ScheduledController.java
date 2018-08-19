package com.kenhome.thread.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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

    @GetMapping("start")
    public String start(){
        System.out.println("开启定时任务.....");
        scheduledThreadPoolExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("现在是执行定时任务......");
            }
        },1,30,TimeUnit.SECONDS);
        return  "sucess";
    }


    @GetMapping("stop")
    public String stop(){
        System.out.println("结束定时任务.....");
        scheduledThreadPoolExecutor.shutdownNow();
        return  "sucess";
    }



}
