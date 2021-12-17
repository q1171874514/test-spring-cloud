package com.example.servicea.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
/**
 * 热部署配置
 * 刷新注解的控制器中的值才会被刷新
 */
@RefreshScope
public class TestController {
    @Value("${server.port}")
    private String port;

    @Value("${name}")
    private String name;

    //.在接口上配置服务保护（fallback是方法）
    @HystrixCommand(fallbackMethod = "fallback")
    @RequestMapping("testA")
    public String textA() {
        return "String Cloud textA 端口号：" + port;
    }

    String fallback(){
        return "服务器繁忙";
    }

    @RequestMapping("hello")
    public String hello() {
        return name;
    }
}
