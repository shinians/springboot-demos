package com.limp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableCaching  //2 开启缓存
public class Springboot08RedisApplication {

	public static void main(String[] args) {
		System.setProperty("spring.devtools.restart.enabled","false");
		SpringApplication.run(Springboot08RedisApplication.class, args);
	}

}

