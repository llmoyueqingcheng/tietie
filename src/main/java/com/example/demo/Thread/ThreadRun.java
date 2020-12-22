package com.example.demo.Thread;

/**
 * @author ls
 * @date 2020年12月16日 11:23 下午
 */
public class ThreadRun implements  Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
