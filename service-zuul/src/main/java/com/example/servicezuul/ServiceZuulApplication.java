package com.example.servicezuul;

import com.example.filter.TokenFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * 网关（过滤拦截中心）
 */
@SpringBootApplication
@EnableEurekaClient
//开启网关
@EnableZuulProxy
public class ServiceZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceZuulApplication.class, args);
	}

	/**
	 * 实例化过滤器
	 *
	 * @return
	 */
	@Bean
	TokenFilter tokenFilter() {
		return new TokenFilter();
	}
}
