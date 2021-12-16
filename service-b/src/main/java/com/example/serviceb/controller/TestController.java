package com.example.serviceb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @RequestMapping("testB")
    public String textB() {
        return "String Cloud textB";
    }
}
