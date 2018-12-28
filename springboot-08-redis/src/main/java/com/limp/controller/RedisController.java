package com.limp.controller;

import com.limp.domain.Student;
import com.limp.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @intro ：
 * @auth ： shinians
 * @time ： 2018/12/27 23:57
 * @website： www.shinians.com
 */
@RestController
public class RedisController {
    Logger logger= LoggerFactory.getLogger(RedisController.class);
    @Autowired
    RedisService redisService;

    @RequestMapping("/student/{id}")
    public Student getStudent(@PathVariable String id){
        logger.info("进入Controller方法getStudent");
        Student student=  redisService.getStudentById(id);
        return  student;
    }
}
