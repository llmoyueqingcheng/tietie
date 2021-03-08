package com.example.demo;

import com.example.demo.excel.ExcelUtilTwo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @author ls
 * @date 2020年12月21日 7:26 下午
 */
@RestController
public class Hellow {

    @Value("classpath:号码导入模板.xlsx")
    private org.springframework.core.io.Resource excelResource;

    @RequestMapping("/jzy")
    public  static  String hellow(Integer num){
        try {
            ExcelUtilTwo.expord(num);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "铁铁整完了去看一下吧（づ￣3￣）づ╭❤～";
    }

     @GetMapping("/daochu")
    public  void daochu() throws IOException{
         HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
         HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
//         org.springframework.core.io.Resource resource = new DefaultResourceLoader()
//                 .getResource("classpath:static/add.xlsx");
//         File sourceFile = excelResource.getFile();
         InputStream fis = excelResource.getInputStream();

         ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
         int ch;
         while ((ch = fis.read()) != -1) {
             byteArrayOutputStream.write(ch);
         }
         response.setContentType("application/octet-stream;charset=utf-8");
         response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("addTemplate.xlsx", "utf-8"));
         ServletOutputStream outputStream = response.getOutputStream();
         outputStream.write(byteArrayOutputStream.toByteArray());
         outputStream.flush();
//测试回退
    }

}
