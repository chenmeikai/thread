package com.kenhome.thread;

import com.kenhome.thread.service.AsyncService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThreadApplicationTests {

    @Autowired
    private AsyncService asyncService;

    @Test
    public void contextLoads() {

        System.out.println("主线程");

        Future<Map<String, Object>> result = asyncService.getResult("chenmeikai");

        try {
            System.out.println("获得结果中.......");
            Map<String,Object> resultMap =result.get();
            System.out.println("获得结果");
            int code = (int) resultMap.get("code");
            System.out.println("code是："+code);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }





    }

}
