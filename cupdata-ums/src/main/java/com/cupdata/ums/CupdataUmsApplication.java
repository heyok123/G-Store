package com.cupdata.ums;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@ComponentScan("com.cupdata")
@MapperScan("com.cupdata.ums.mapper")
//@SpringBootApplication(scanBasePackages = {"com.cupdata.ums"},
//		exclude = {ShiroAnnotationProcessorConfiguration.class, ShiroBeanConfiguration.class, ShiroConfiguration.class})
@SpringBootApplication
public class CupdataUmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CupdataUmsApplication.class, args);
	}


}
