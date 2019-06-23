package com.limp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.limp"})
//@MapperScan({"com.limp.framework.boss.mapper.oracle","com.gla.datacenter.mapper.mysql"})
public class SolrDemo01HellowordApplication {

	public static void main(String[] args) {
		SpringApplication.run(SolrDemo01HellowordApplication.class, args);
	}

}
