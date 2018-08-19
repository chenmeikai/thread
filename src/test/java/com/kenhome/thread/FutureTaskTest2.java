package com.kenhome.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author: cmk
 * @Description:
 * @Date: 2018\7\9 0009 20:30
 */
public class FutureTaskTest2 {

    public static void main(String[] args) {

        FutureTask<String> futureTask = new FutureTask<>(new Task());
        Thread thread = new Thread(futureTask);
        thread.start();
        try {
            String result = futureTask.get();
            System.out.println("结果" + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}

class Task implements Callable {

    @Override
    public Object call() throws Exception {

        System.out.println("启用线程");
        System.out.println("休眠5秒");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("休眠结束");
        return "success";
    }
}