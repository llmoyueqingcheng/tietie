package com.example.demo.juc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author ls
 * @date 2020年12月23日 10:08 下午
 */
public class ListAndSetAndMap {
    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
//        Map<String, Object> synchronizedMap = Collections.synchronizedMap(new HashMap<String, Object>());
        //并发使用的安全线程类
        CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>();
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        ConcurrentHashMap<Object, Object> Map = new ConcurrentHashMap<>();
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
