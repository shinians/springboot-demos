package com.limp.starter;

import com.limp.config.HelloConfig;
import com.limp.starter.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnWebApplication //web应用才生效
@EnableConfigurationProperties(HelloConfig.class)

public class HelloServiceAutoConfiguration {

    @Autowired
    HelloConfig helloConfig;

    /**
     * bean注入
     * @return
     */
    @Bean
    public HelloService helloService(){
        HelloService service = new HelloService();
        service.setHelloConfig(helloConfig);
        return service;
    }
}
