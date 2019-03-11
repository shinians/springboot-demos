package com.limp.pojo;

import java.io.Serializable;

/**
 * @intro ：
 * @auth ： shinians
 * @time ： 2019/3/8 11:03
 * @website： www.shinians.com
 */
public class TestPo implements Serializable {
    private String id;
    private String name;


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

    @Override
    public String toString() {
        return "TestPo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
