package com.limp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @intro ：
 * @auth ： shinians
 * @time ： 2018/12/16 12:51
 * @website： www.shinians.com
 */
@RestController
public class HelloController {
    @RequestMapping("hello")
    public  String  helloWord(String name){
        return  "您好 ,"+name;
    }

}
