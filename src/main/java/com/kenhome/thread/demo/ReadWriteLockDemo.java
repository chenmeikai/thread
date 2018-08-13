package com.kenhome.thread.demo;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: cmk
 * @Description: 读写锁: 读和读不互斥，读和写互斥，写和写互斥，提高读写文件的效率
 * @Date: 2018\8\12 0012 23:28
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {
        ReentrantReadWriteLock readAndWriteLock =new ReentrantReadWriteLock();
        String name1="read";
        ReadTask readTask1 =new ReadTask(name1,readAndWriteLock);
        Thread thread1 = new Thread(readTask1);
        thread1.start();

        String name2="write";
        WritTask readTask2 =new WritTask(name2,readAndWriteLock);
        Thread thread2 = new Thread(readTask2);
        thread2.start();
    }


}


class ReadTask implements Runnable {

    private ReentrantReadWriteLock readAndWriteLock;
    private String name;

    public ReadTask(String name, ReentrantReadWriteLock readAndWriteLock) {
        this.name = name;
        this.readAndWriteLock = readAndWriteLock;
    }

    @Override
    public void run() {
        try {
            readAndWriteLock.readLock().lock();
            System.out.println(name+"开始时间:"+System.currentTimeMillis());
            for (int i = 0; i <20; i++) {
                System.out.println(name + ":" + i);
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(name+"结束时间:"+System.currentTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readAndWriteLock.readLock().unlock();
        }
    }
}

class WritTask implements Runnable {

    private ReentrantReadWriteLock readAndWriteLock;
    private String name;

    public WritTask(String name, ReentrantReadWriteLock readAndWriteLock) {
        this.name = name;
        this.readAndWriteLock = readAndWriteLock;
    }

    @Override
    public void run() {
        try {
            readAndWriteLock.writeLock().lock();
            System.out.println(name+"开始时间:"+System.currentTimeMillis());
            for (int i = 0; i <20; i++) {
                System.out.println(name + ":" + i);
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(name+"结束时间:"+System.currentTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readAndWriteLock.writeLock().unlock();
        }
    }
}