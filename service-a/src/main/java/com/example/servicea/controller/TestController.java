package com.example.servicea.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
/**
 * 热部署配置
 * 刷新注解的控制器中的值才会被刷新
 * git配置修改后，命令行访问curl -Uri 'http://localhost:8082/actuator/refresh' -ContentType 'application/json'  -Method 'POST'
 * （http://localhost:8082为访问端口）
 */
@RefreshScope
public class TestController {
    @Value("${server.port}")
    private String port;

    @Value("${name}")
    private String name;

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
