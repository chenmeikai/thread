package com.kenhome.thread.config.start;

import com.kenhome.thread.model.DelayCancelOrder;
import com.kenhome.thread.model.Order;
import com.kenhome.thread.service.ThreadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.DelayQueue;

/**
 * @Author: cmk
 * @Description:
 * @Date: 2018\7\22 0022 13:48
 */
@Component
public class CancelOrderStart implements CommandLineRunner {

    Logger log =LoggerFactory.getLogger(CancelOrderStart.class);

    @Autowired
    ThreadService threadService;

    @Override
    public void run(String... args) throws Exception {

        DelayQueue<DelayCancelOrder>  delayQueue=new DelayQueue<>();
       
        List<Order> orders =new ArrayList<>();

        /**
         * TODO 项目启动时查询出等待付款的订单集合orders ，加入延迟取消队列中
         */
        for(Order order :orders){
            DelayCancelOrder delayCancelOrder =new DelayCancelOrder(order.getOrderId(),7L,order.getCreateDate());
            delayQueue.add(delayCancelOrder);
        }
        log.info("启动延迟取消队列处理订单数为{}",delayQueue.size());

        threadService.excuteCancelOrder(delayQueue);

    }
}
