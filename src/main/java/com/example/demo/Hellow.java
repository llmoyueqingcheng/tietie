package com.example.demo;

import com.example.demo.excel.ExcelUtilTwo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author ls
 * @date 2020年12月21日 7:26 下午
 */
@RestController
public class Hellow {

    @RequestMapping("/jzy")
    public  static  String hellow(Integer num){
        try {
            ExcelUtilTwo.expord(num);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "铁铁整完了去看一下吧（づ￣3￣）づ╭❤～";
    }
}
