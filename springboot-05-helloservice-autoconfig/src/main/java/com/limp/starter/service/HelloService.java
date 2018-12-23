package com.limp.starter.service;

import com.limp.config.HelloConfig;

/**
 * @intro ：
 * @auth ： shinians
 * @time ： 2018/12/23 21:16
 * @website： www.shinians.com
 */
public class HelloService {

    //注意生成get set方法
    HelloConfig helloConfig;

    /**
     * 你好，方法
     * @param name  名称
     * @return
     */
    public String sayHello(String name){
        return helloConfig.getPrefix()+"-" +name + "-"+helloConfig.getSuffix();
    }

    public HelloConfig getHelloConfig() {
        return helloConfig;
    }

    public void setHelloConfig(HelloConfig helloConfig) {
        this.helloConfig = helloConfig;
    }
}
