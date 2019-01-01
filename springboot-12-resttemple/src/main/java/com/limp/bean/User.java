package com.limp.bean;

import java.io.Serializable;

/**
 * @intro ：
 * @auth ： shinians
 * @time ： 2019/1/1 11:12
 * @website： www.shinians.com
 */
public class User implements Serializable {
    public  String  name;
    public  Integer  age;
    public  String  id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User() {
    }

    public User(String name, Integer age, String id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id='" + id + '\'' +
                '}';
    }
}
