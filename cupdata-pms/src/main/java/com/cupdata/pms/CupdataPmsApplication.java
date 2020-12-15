package com.cupdata.pms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@MapperScan("com.cupdata.pms.mapper")
@SpringBootApplication
public class CupdataPmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CupdataPmsApplication.class, args);
	}

}
