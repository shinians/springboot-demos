package com.limp.controller;

import com.limp.starter.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @intro ：
 * @auth ： shinians
 * @time ： 2018/12/23 21:39
 * @website： www.shinians.com
 */
@RestController
public class HelloController {
    @Autowired
    HelloService helloService;


    @GetMapping(value = "hello")
    public  String sayhello(String name){

       return  helloService.sayHello(name);
    }
}
