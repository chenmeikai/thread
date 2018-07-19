package com.kenhome.thread.service.Impl;

import com.kenhome.thread.service.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * @Author: cmk
 * @Description:
 * @Date: 2018\7\20 0020 00:06
 */

@Service
public class AsyncServiceImpl implements AsyncService {

    Logger log =LoggerFactory.getLogger(AsyncServiceImpl.class);


    @Async("asyncServiceExecutor")
    @Override
    public void executeTask() {

    }


    @Async("asyncServiceExecutor")
    @Override
    public Future<Map<String, Object>> getResult(String name) {

        Map<String,Object> result =new HashMap<>();
        System.out.println(name+"执行任务");
        result.put("code",200);
        result.put("desc","success");

        try {
            System.out.println("睡眠开始");
            Thread.sleep(5000);
            System.out.println("睡眠结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AsyncResult<Map<String, Object>>(result);
    }
}
