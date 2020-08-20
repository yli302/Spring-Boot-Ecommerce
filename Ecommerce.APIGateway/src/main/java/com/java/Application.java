package com.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.java.service.UserSecurityFilter;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@EnableFeignClients
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// do security here.
	@Bean
	public UserSecurityFilter securityFilter() {
		return new UserSecurityFilter();
	}
}
