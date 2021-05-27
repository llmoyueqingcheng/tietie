package com.example.demo;

import org.apache.tomcat.util.buf.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class testStreamTest {


    public static void main(String[] args) {

        //map 将状态替换
        List<User> userList = Arrays.asList(new User("武器", 22, false),
                new User("猴子", 22, true),
                new User("卡特", 12, false),
                new User("盖伦", 42, false)
        );
        //遍历
//        userList.stream().forEach(s->System.out.println(s.toString()));
        //过滤
//       List<User> collect = userList.stream().filter(s ->s.getAge()>22).collect(Collectors.toList());
        //map  单独拿出一列
//        List<String> collect1 = userList.stream().map(x -> x.getName()).collect(Collectors.toList());
        //map  转成另一个对象user ->userExt




        //替换更改属性 peek会改变原来的list
//        List<User> collect = userList.stream().peek(user -> {
//                    if (user.getStatus()) {
//                        user.setName(user.getName() + user.getAge());
//                    }
//                }
//        ).collect(Collectors.toList());

//        List<Integer> list = Arrays.asList(1,2,2,3);
//        list.stream().distinct().forEach(System.out::println);
//        userList.forEach(System.out::println);
        //filter会生成新的list
//        List<User> collect = userList.stream().filter(user -> user.getAge() >30).collect(Collectors.toList());
//        List<User> collect1 = userList.stream().map(temp -> {
//                    if(temp.getStatus()){
//                        temp.setName(temp.getName()+temp.getAge());
//                    }
//                    return temp;
//                }
//        ).collect(Collectors.toList());
//        List<User> collect = userList.stream().peek(user -> {
//                    if (user.getStatus()) {
//                        user.setName(user.getName() + user.getAge());
//                    }
//                }
//        ).collect(Collectors.toList());
//
//        userList.forEach(System.out::println);

//        List<User> collect1 = userList.stream().distinct().collect(Collectors.toList());

//        collect1.forEach(System.out::println);

//        userList.stream().filter(user -> user.getName().equals("武器")
//        ).collect(Collectors.toList()).forEach(System.out::println);

        List<User> collect = userList.stream()
                .filter(distinctByKey( (p) -> (p.getStatus())  ))
                .collect(Collectors.toList());
        collect.forEach(System.out::println);

    }

    /**
     * 函数式接口 LIST Object 属性去重;原理 使用了map.putIfAbsent方法的特性如果key不存在
     * 设置成功并且返回上一次的值(上次一的为null) 如果key已经存在了;设置失败并且返回上一次的值(上一次的为true) ;
     * 返回true!=null  fliter之后会过滤掉
     * @param keyExtractor
     * @param <T>
     * @return
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        ConcurrentHashMap<Object, Boolean> map = new ConcurrentHashMap<>(16);
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }


}
