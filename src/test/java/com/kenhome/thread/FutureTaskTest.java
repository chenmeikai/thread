package com.kenhome.thread;

import java.util.concurrent.*;

/**
 * @Author: cmk
 * @Description:
 * @Date: Created in 17:28 2018/7/9 0009
 * @Modified By:
 */
public class FutureTaskTest {

    public static void main(String[] args) {

        ExecutorService es = Executors.newSingleThreadExecutor();

        FutureDemo task = new FutureDemo();

        Future<Integer> future =es.submit(task);

        es.shutdown();

        try {
            int result =future.get();
            System.out.println("等待线程获得："+result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}


class FutureDemo implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {

        System.out.println("开启执行");
        System.out.println("小睡一会");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("报告，已醒");
        return 1;
    }
}