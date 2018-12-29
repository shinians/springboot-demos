package com.limp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Springboot09TasksApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot09TasksApplication.class, args);
	}

}

