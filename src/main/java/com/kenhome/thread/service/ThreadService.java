package com.kenhome.thread.service;

import com.kenhome.thread.model.DelayCancelOrder;

import java.util.Map;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Future;

/**
 * @Author: cmk
 * @Description: 异步接口
 * @Date: 2018\7\19 0019 23:28
 */
public interface ThreadService {


    /**
     * 异步执行任务
     * @param: []
     * @return: void
     */
    void executeTask();

    /**
     * 异步执行任务
     * @param: []
     * @return: void
     */
    Future<Map<String, Object>> getResult(String name);

    /**
     * 执行任务：处理延迟队列中的待取消订单
     * @param: [orderId]
     * @return: void
     */
    void excuteCancelOrder(DelayQueue<DelayCancelOrder> delayQueue);

    /**
     * 延迟取消订单
     * @param: [orderId]
     * @return: void
     */
    void cancelOrder(Long orderId);


}
