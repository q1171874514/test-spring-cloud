package com.example.servicegateway.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;


/**
 * @author 吕时海
 * @date 2021/12/20 14:28
 * @explain token过滤器
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
            Map<String,Object> responseData = Maps.newHashMap();
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
