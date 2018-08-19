package com.kenhome.thread.service.Impl;

import com.kenhome.thread.model.DelayCancelOrder;
import com.kenhome.thread.service.ThreadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Future;

/**
 * @Author: cmk
 * @Description:
 * @Date: 2018\7\20 0020 00:06
 */

@Service
public class ThreadServiceImpl implements ThreadService {

    Logger log = LoggerFactory.getLogger(ThreadServiceImpl.class);


    @Async("asyncServiceExecutor")
    @Override
    public void executeTask() {

    }


    @Async("asyncServiceExecutor")
    @Override
    public Future<Map<String, Object>> getResult(String name) {

        Map<String, Object> result = new HashMap<>();
        System.out.println(name + "执行任务");
        result.put("code", 200);
        result.put("desc", "success");

        try {
            System.out.println("睡眠开始");
            Thread.sleep(5000);
            System.out.println("睡眠结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AsyncResult<Map<String, Object>>(result);
    }


    /**
     * @Description: 单线程执行:延迟队列取出订单进行取消，注意：客户手动取消订单或者确认付款时，需要调用delayQueue.remove(delayCancelOrder)移出该订单
     * @param: [orderId, delayQueue]
     * @return: void
     */
    @Async("singleServiceExecutor")
    @Override
    public void excuteCancelOrder(DelayQueue<DelayCancelOrder> delayQueue) {

        while (true) {
            try {
                DelayCancelOrder delayCancelOrder = delayQueue.take();
                log.info("从延迟队列中取出待取消的订单id为{}", delayCancelOrder.getOrderId());
                //取消订单
                cancelOrder(delayCancelOrder.getOrderId());
                log.info("延迟队列中的订单数为{}" + delayQueue.size());
            } catch (InterruptedException e) {
                log.error("获取延迟队列中的订单失败");
                e.printStackTrace();
            }
        }
    }


    /**
     * @Description: 取消订单
     * @param: [orderId]
     * @return: void
     */
    @Override
    public void cancelOrder(Long orderId) {
        //TODO 执行取消订单的操作:更新该订单id以及状态为等待付款的订单状态为自动取消,打印取消结果  sql: update t_order  set state='autoCancel' where id=#{id} and state='waite'
    }
}
