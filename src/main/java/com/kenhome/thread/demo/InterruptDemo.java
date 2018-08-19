package com.kenhome.thread.demo;

/**
 * @Author: cmk
 * @Description: 中断线程机制 :interrupt():给线程一个中断状态；isInterrupted()：检查线程是否设置了中断状态；interrupted()：检查线程是否设置了中断状态，同时会清除线程的中断状态
 * @Date: 2018\7\22 0022 17:07
 */
public class InterruptDemo {

    public static void main(String[] args) {
        Stop stop = new Stop();
        Thread thread = new Thread(stop);
        thread.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }

}

class Stop implements Runnable {

    @Override
    public void run() {

        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("获得中断信号,中断线程");
                break;
            }

            System.out.println("运行中...");
            System.out.println("此时线程中断状态是" + Thread.currentThread().isInterrupted());

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("线程睡眠中被中断，将重设为中断信号");
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }

        }

    }
}
