package com.kenhome.thread.demo;

import java.util.concurrent.Semaphore;

/**
 * @Author: cmk
 * @Description: Semaphore信号量 控制线程并发的数量  分配几把锁，允许几个线程获得锁，其它没有获得锁的等待
 * @Date: 2018\7\10 0010 23:23
 */
public class SemaphoreDemo {


    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(3);

        Driver driver = new Driver(semaphore);

        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new Car(driver));
            thread.start();
        }

    }
}

class Driver {

    private Semaphore semaphore;

    public Driver(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    public void drive() {
        try {
            // 从信号量中获取一个允许机会
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " start at " + System.currentTimeMillis());
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " stop at " + System.currentTimeMillis());
            // 释放允许，将占有的信号量归还
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Car implements Runnable {

    private Driver driver;

    public Car(Driver driver) {
        this.driver = driver;
    }

    @Override
    public void run() {
        driver.drive();
    }
}