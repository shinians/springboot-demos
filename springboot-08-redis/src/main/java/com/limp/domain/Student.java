package com.limp.domain;

import java.io.Serializable;

/**
 * @intro ：
 * @auth ： shinians
 * @time ： 2018/12/27 23:52
 * @website： www.shinians.com
 */
//** implements Serializable   !!!!! implements Serializable
public class Student  implements Serializable{
    public String  id;
    public String  name;
    public int  age;

    /**
     * 重要重要！！！！！无参构造方法
     */
    public Student() {
    }

    public Student(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
