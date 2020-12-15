package com.cupdata.ums;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@MapperScan("com.cupdata.ums.mapper")
@SpringBootApplication
public class CupdataUmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CupdataUmsApplication.class, args);
	}

}
