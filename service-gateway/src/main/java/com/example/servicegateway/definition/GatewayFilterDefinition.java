package com.example.servicegateway.definition;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 吕时海
 * @date 2021/12/22 14:21
 * @explain test-spring-cloud
 */
@Data
public class GatewayFilterDefinition {
    //断言对应的Name
    private String name;
    //配置的断言规则
    private Map<String, String> args = new LinkedHashMap<>();

}
