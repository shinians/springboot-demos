package com.limp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @intro ：
 * @auth ： shinians
 * @time ： 2018/12/16 12:51
 * @website： www.shinians.com
 */
@Controller
public class HelloController {
    @ResponseBody
    @RequestMapping("hello")
    public  String  helloWord(String name){
        return  "您好 ,"+name;
    }

}
