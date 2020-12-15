package com.cupdata.sms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@MapperScan("com.cupdata.sms.mapper")
@SpringBootApplication
public class CupdataSmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CupdataSmsApplication.class, args);
	}

}
