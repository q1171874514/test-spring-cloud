package com.example.servicea.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 吕时海
 * @date 2021/12/21 12:56
 * @explain test-spring-cloud
 */
@RestController("test2")
public class Test2Controller {
    @Value("${server.port}")
    private String port;

    @RequestMapping("/test2A")
    public String textA() {
        return "String Cloud text2 端口号：" + port;
    }
}
