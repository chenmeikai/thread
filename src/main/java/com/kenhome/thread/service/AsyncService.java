package com.kenhome.thread.service;

import java.util.Map;
import java.util.concurrent.Future;

/**
 * @Author: cmk
 * @Description: 异步接口
 * @Date: 2018\7\19 0019 23:28
 */
public interface AsyncService {


    /**
     * 执行任务
     * @param: []
     * @return: void
     */
    void executeTask();

    /**
     * 执行任务
     * @param: []
     * @return: void
     */
    Future<Map<String, Object>> getResult(String name);
}
