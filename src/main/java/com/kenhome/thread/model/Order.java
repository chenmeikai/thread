package com.kenhome.thread.model;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Author: cmk
 * @Description: 订单
 * @Date: 2018\7\21 0021 22:10
 */
public class Order {

    //订单id
    private Long orderId;

    private Integer state;

    private Date createDate;

    //...其它字段


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
