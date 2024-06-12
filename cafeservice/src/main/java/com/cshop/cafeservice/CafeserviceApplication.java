package com.cshop.cafeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CafeserviceApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(CafeserviceApplication.class);
		ApplicationContext applicationContext = springApplication.run(CafeserviceApplication.class, args);

	}

}
