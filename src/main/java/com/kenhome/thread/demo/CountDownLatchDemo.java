package com.kenhome.thread.demo;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: cmk
 * @Description: CountDownLatch闭锁 允许一个或多个线程，等待其他一组线程完成操作，再继续执行
 * @Date: 2018\7\10 0010 22:17
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(1);

        Thread thread = new Thread(new Task2(countDownLatch));

        thread.start();

        try {
            countDownLatch.await();

            System.out.println("等待完毕");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}


class Task2 implements Runnable {

    private CountDownLatch countDownLatch;

    public Task2(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {

        System.out.println("我是子线程");
        System.out.println("休眠3秒");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("休眠结束，计数完毕");
        countDownLatch.countDown();

    }
}