package com.kenhome.thread.demo;

import java.util.concurrent.*;

/**
 * @Author: cmk
 * @Description:
 * @Date: 2018\7\9 0009 20:49
 */
public class FutureTaskDemo {

    //通过线程池管理多线程
    ExecutorService executor = new ThreadPoolExecutor(1,1,5,TimeUnit.MINUTES);
}
