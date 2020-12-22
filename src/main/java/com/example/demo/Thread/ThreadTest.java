package com.example.demo.Thread;

import java.util.concurrent.Callable;

/**
 * 创建线程的方式
 * @author ls
 * @date 2020年12月16日 11:24 下午
 */
public class ThreadTest {
    public static void main(String[] args) {
        //实现runable接口
        ThreadRun threadRun = new ThreadRun();
        new Thread(threadRun).start();
        //继承thread
        new ThreadEx().start();
        //匿名内部类
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();
        //lamda表达式
        new Thread(() ->System.out.println(Thread.currentThread().getName())).start();



    }
}
