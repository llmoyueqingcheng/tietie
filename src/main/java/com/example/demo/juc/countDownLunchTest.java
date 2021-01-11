package com.example.demo.juc;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * @author ls
 * @date 2020年12月23日 10:49 下午
 */
public class countDownLunchTest {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(10);

        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {

                try {
                    //业务代码
                    list.add(UUID.randomUUID().toString().substring(0, 5));
                    System.out.println(list);

                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    countDownLatch.countDown();
                }

            }, String.valueOf(i)).start();
        }
        // 等待计数器归零，然后再向下执行
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
        System.out.println("循环结束");
    }
}
