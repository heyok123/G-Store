package com.cupdata.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CupdataSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(CupdataSearchApplication.class, args);
	}

}
