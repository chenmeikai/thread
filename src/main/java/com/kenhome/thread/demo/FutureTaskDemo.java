package com.kenhome.thread.demo;

import java.util.concurrent.*;

/**
 * @Author: cmk
 * @Description: FutureTask 获取任务结果，阻塞
 * @Date: 2018\7\9 0009 20:49
 */
public class FutureTaskDemo {

    public static void main(String[] args) {
        //通过线程池管理多线程
        ThreadPoolExecutor executor = new ThreadPoolExecutor(0, 1, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

        FutureTask futureTask =new FutureTask(new Task());

        executor.submit(futureTask);

        try {
            System.out.println("获取的结果："+futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class Task implements  Callable<Integer>{
    @Override
    public Integer call() throws Exception {

        System.out.println("开启线程");
        System.out.println("休眠5秒");
        Thread.sleep(5000);
        System.out.println("休眠结束");
        return 5;
    }
}
