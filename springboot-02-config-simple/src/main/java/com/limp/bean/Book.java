package com.limp.bean;

/**
 * @intro ： Spring Boot读取配置文件自定义信息
 * @auth ： shinians
 * @time ： 2018/12/19 0:17
 * @website： www.shinians.com
 */
public class Book {
    //书名
    private String name;
    //价格
    private  int price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
