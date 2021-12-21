package com.example.servicea.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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

    private Integer t = 0;

    @RequestMapping("testA")
    public String textA() {
        return "String Cloud textA 端口号：" + port;
    }


    @RequestMapping("hello")
    public String hello(Integer mids) {
        if(mids != null) {
            try {
                Thread.sleep(mids);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return name;
    }

    /**
     * 熔断器测试接口
     * @param mids
     * @return
     */
    @RequestMapping("circuitTest")
    /**
     *熔断器
     * 注释到类或方法上则可以启用断路器。
     * 如果在类指定这个注释，则为所有公共方法启用断路器
     */
    @CircuitBreaker(name = "backendA", fallbackMethod = "fallBack")
    public String circuitTest(Integer mids){
        System.out.println("t: " + this.t);
        if(mids != null) {
            try {
                Thread.sleep(mids);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return name;
    }

    private String fallBack(Integer mids, Throwable throwable){
        this.t ++;
        return "服务器繁忙";
    }
}
