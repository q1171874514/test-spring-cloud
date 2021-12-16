package com.example.servicea.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Value("${server.port}")
    private String port;

    //.在接口上配置服务保护（fallback是方法）
    @HystrixCommand(fallbackMethod = "fallback")
    @RequestMapping("testA")
    public String textA() {
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return "String Cloud textA 端口号：" + port;
    }

    String fallback(){
        return "服务器繁忙";
    }
}
