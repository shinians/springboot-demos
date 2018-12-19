package com.limp.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @intro ：
 * @auth ： shinians
 * @time ： 2018/12/19 0:17
 * @website： www.shinians.com
 */


@Component
//@Configuration
@PropertySource(value = {"classpath:teacher.properties"})
public class Teacher {

    //lastName必须是邮箱格式
    // @Email
    @Value("${teacher.teacher-name}")
    private String tearchName;
    @Value("#{11*2}")
    private Integer age;
    @Value("true")
    private Boolean boss;

    private Date birth;
//    @Value("${teacher.maps}")
    private Map<String,Object> maps;
    @Value("${teacher.lists}")
    private List<Object> lists;
    private Book  book;

    /**
     * 特别注意！！特别注意！！：如果通过前缀的方式注入，一定要有get set 方法
     * @return
     */

    @Override
    public String toString() {
        return "Teacher{" +
                "tearchName='" + tearchName + '\'' +
                ", age=" + age +
                ", boss=" + boss +
                ", birth=" + birth +
                ", maps=" + maps +
                ", lists=" + lists +
                ", book=" + book +
                '}';
    }
}
