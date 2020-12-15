package com.cupdata.oms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@MapperScan("com.cupdata.oms.mapper")
@SpringBootApplication
public class CupdataOmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CupdataOmsApplication.class, args);
	}

}
