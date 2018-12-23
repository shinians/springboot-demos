package com.limp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Springboot03LoggingApplication {

	//slf4j一定引入的是这个包
	static  Logger logger= LoggerFactory.getLogger(Springboot03LoggingApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(Springboot03LoggingApplication.class, args);
//		System.out.println("启动成功");
		logger.debug("启动成功");
		logger.error("启动成功");

	}

}

