package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class testStream {


    public static void main(String[] args) {
       //map 将状态替换
        List<User> userList = new ArrayList<User>();
        userList.add(new User("张三", 22, "success"));
        userList.add(new User("礼拜", 32, "success"));
        userList.add(new User("盖伦", 42, "fail"));
        //遍历
//        userList.stream().forEach(s->System.out.println(s.toString()));
        //过滤
//        List<User> collect = userList.stream().filter(s ->s.getAge()>22).collect(Collectors.toList());

        //map  单独拿出一列
//        List<String> collect1 = userList.stream().map(x -> x.getName()).collect(Collectors.toList());
        //map  转成另一个对象user ->userExt
//          List<User> collect1 = userList.stream().map(temp -> {
//              if(temp.getStatus().equals("success")){
//                  temp.setStatus("成功");
//              }else{
//                  temp.setStatus("失败");
//              }
//              return temp;
//          }
//        ).collect(Collectors.toList());
//
//        List<String> list = Arrays.asList("a", "b", "c");
//        List<User> collect = userList.stream().peek(user -> user.setStatus("111")).collect(Collectors.toList());
        List<User> collect = userList.stream().map(user -> {
            user.setStatus("222");
            return user;
        }).collect(Collectors.toList());

        System.out.println(collect.toString());
    }




}
