package com.cupdata.pms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@ComponentScan("com.cupdata")
@MapperScan("com.cupdata.pms.mapper")
@SpringBootApplication
@EnableFeignClients
@RefreshScope
//@EnableAspectJAutoProxy(exposeProxy = true) // 开启AspectJ的自动代理，同时暴露代理对象
public class CupdataPmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CupdataPmsApplication.class, args);
	}

}
