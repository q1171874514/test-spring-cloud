package com.example.serviceb.controller.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

// 填入注册中心中的应用名, 也就是要调用的微服务的应用名
// 在eureka页面中可以找到
@FeignClient("SERVICE-OBJCAT-A")
public interface ServiceTestAFeignClient {
    @RequestMapping("testA")
    public String testA();
}
