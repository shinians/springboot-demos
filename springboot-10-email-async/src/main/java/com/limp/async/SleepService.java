package com.limp.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * @intro ：
 * @auth ： shinians
 * @time ： 2018/12/30 13:23
 * @website： www.shinians.com
 */
@Service
public class SleepService {

    /**
     *异步问候
     * @param name
     * @return
     */
    @Async
   public Future<String>   sayHallo(String name){
       try {
           //休眠10秒
           Thread.sleep(10000);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
       return  new AsyncResult<String>("异步您好"+name);
   }

    /**
     *问候【非异步】
     * @param name
     * @return
     */
    public  String  sayHallo1(String name){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  "非异步，您好-》"+name;
    }
}
