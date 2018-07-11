package com.kenhome.thread.demo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author: cmk
 * @Description: 栅栏锁
 * @Date: Created in 12:56 2018/7/11 0011
 * @Modified By:
 */

public class CyclicBarrierDemo {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

        for (int i = 0; i < 5; i++) {

            Thread thread = new Thread(new Task3(cyclicBarrier));
            if (i == 4) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            thread.start();
        }
    }
}


class Task3 implements Runnable {

    private CyclicBarrier cyclicBarrier;

    public Task3(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        System.out.println("准备起跑");
        try {
            cyclicBarrier.await();
            System.out.println("开始");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

