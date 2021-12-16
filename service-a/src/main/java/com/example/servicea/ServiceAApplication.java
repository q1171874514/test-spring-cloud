package com.example.servicea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * 服务器A（被调用服务器）
 */
@SpringBootApplication
@EnableEurekaClient
/**
 * 开启Hystrix服务保护组件
 * 可以实现服务降级, 服务熔断, 服务隔离等保护措施
 * 达到高并发
 * https://www.jianshu.com/p/cce702d44b7d
 */
@EnableHystrix
public class ServiceAApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceAApplication.class, args);
	}

}
