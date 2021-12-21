package com.example.servicegateway.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;


/**
 * @author 吕时海
 * @date 2021/12/20 14:28
 * @explain token过滤器
 * 过滤器类型：Gateway实现方式上，有两种过滤器；
 * 1. 局部过滤器：通过 spring.cloud.gateway.routes.filters 配置在具体路由下，只作用在当前路由
     * 上；自带的过滤器都可以配置或者自定义按照自带过滤器的方式。如果配置
     * spring.cloud.gateway.default-filters 上会对所有路由生效也算是全局的过滤器；但是这些过滤器
     * 的实现上都是要实现GatewayFilterFactory接口。
 * 2. 全局过滤器：不需要在配置文件中配置，作用在所有的路由上；实现 GlobalFilter 接口即可。
 */
@Component
public class TokenGatewayFilterFactory implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        //获取响应对象
        ServerHttpResponse response = exchange.getResponse();
        if(token == null) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            //令牌不存在
            //返回错误信息
            Map<String,Object> responseData = new HashMap();
            responseData.put("code",401);
            responseData.put("message","token不存在");
            responseData.put("cause","token is null");

            return responseError(response,responseData);
        }
        return chain.filter(exchange);
    }

    //返回响应数据
    private Mono<Void> responseError(ServerHttpResponse response, Map<String, Object> responseData) {

        //将信息转换为json
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] data = new byte[0];
        try{
            data = objectMapper.writeValueAsBytes(responseData);
        }catch (Exception e){
            e.printStackTrace();
        }

        //输出结果数据
        DataBuffer buffer = response.bufferFactory().wrap(data);
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type","application/json;charset=utf-8");
        return response.writeWith(Mono.just(buffer));
    }
    @Override
    public int getOrder() {
        return 1;
    }
}
