package com.kenhome.thread.model;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Author: cmk
 * @Description: 延迟取消订单
 * @Date: 2018\7\21 0021 22:10
 */
public class DelayCancelOrder implements Delayed {

    //订单id
    private Long orderId;
    /**
     * 过期取消订单时间（ms）
     */
    private Long expireTime;

    /**
     * @Description:
     * @param: [orderId, expireTime(单位天), createDate]
     * @return:
     */
    public DelayCancelOrder(Long orderId, Long expireTime, Date createDate) {
        super();
        this.orderId = orderId;
        //过期时间为天
        this.expireTime = TimeUnit.MILLISECONDS.convert(expireTime, TimeUnit.DAYS) + createDate.getTime();
    }

    /**获得延迟时间：过期时间-当前时间*/
    @Override
    public long getDelay(TimeUnit unit) {
        return expireTime -System.currentTimeMillis();
    }

    /**比较延迟队列的内部排序：返回当前时间的延迟时间-比较对象的延迟时间*/
    @Override
    public int compareTo(Delayed o) {
        return this.expireTime.compareTo(((DelayCancelOrder)o).expireTime);
    }


    public static void main(String[] args) {
        Long currentTime = System.currentTimeMillis();

        long time = TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS);

        System.out.println(time);

    }


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }
}
