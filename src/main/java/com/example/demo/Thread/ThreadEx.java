package com.example.demo.Thread;

/**
 * @author ls
 * @date 2020年12月16日 11:25 下午
 */
public class ThreadEx extends Thread{
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }

}
