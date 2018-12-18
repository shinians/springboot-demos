package com.limp.controller;

import com.limp.bean.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @intro ：
 * @auth ： shinians
 * @time ： 2018/12/19 0:29
 * @website： www.shinians.com
 */
@RestController
public class HelloController {

    @Value("${student.last-name}")
    private String studentName;

    @Autowired
    Student student;

    @RequestMapping(value = "hello")
    public String hello(String name) {
        System.out.println(student);
        return "您好，"+studentName+"  and "+name;
    }
}
