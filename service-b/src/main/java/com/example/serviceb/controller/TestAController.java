package com.example.serviceb.controller;

import com.example.serviceb.controller.feign.ServiceTestAFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestAController {
    @Autowired
    private ServiceTestAFeignClient serviceTestAFeignClient;

    @RequestMapping("testA")
    public String testA(){
        String result = serviceTestAFeignClient.testA();
        return "b to a 访问结果 ---" + result;
    }
}
