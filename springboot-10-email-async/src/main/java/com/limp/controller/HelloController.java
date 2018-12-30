package com.limp.controller;

import com.limp.async.SleepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @intro ：
 * @auth ： shinians
 * @time ： 2018/12/30 13:24
 * @website： www.shinians.com
 */
@RestController
public class HelloController {
    @Autowired
    SleepService sleepService;

    @GetMapping("hello/{name}")
    public String sayHello(@PathVariable  String name){
       String finalName= sleepService.sayHallo1(name);
        return  "controller-hello--->"+finalName;

    }

}
