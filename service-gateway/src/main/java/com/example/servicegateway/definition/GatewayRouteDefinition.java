package com.example.servicegateway.definition;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吕时海
 * @date 2021/12/22 14:15
 * @explain test-spring-cloud
 */
@Data
public class GatewayRouteDefinition {
    //路由的Id
    private String id;
    //路由断言集合配置
    private List<GatewayPredicateDefinition> predicates = new ArrayList<>();
    //路由过滤器集合配置
    private List<GatewayFilterDefinition> filters = new ArrayList<>();
    //路由规则转发的目标uri
    private String uri;
    //路由执行的顺序
    private int order = 0;

}
