package com.example.servicegateway.definition;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 吕时海
 * @date 2021/12/22 14:19
 * @explain test-spring-cloud
 */
@Data
public class GatewayPredicateDefinition {
    //Filter Name
    private String name;
    //对应的路由规则
    private Map<String, String> args = new LinkedHashMap<>();
}
