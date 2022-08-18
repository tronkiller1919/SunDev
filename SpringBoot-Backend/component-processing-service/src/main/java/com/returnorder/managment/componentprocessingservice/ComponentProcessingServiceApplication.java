package com.returnorder.managment.componentprocessingservice;

import com.returnorder.managment.componentprocessingservice.exception.ExceptionMessageDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class ComponentProcessingServiceApplication{

	@Bean
	public ErrorDecoder errorDecoder() {
		return new ExceptionMessageDecoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(ComponentProcessingServiceApplication.class, args);
	}

}
