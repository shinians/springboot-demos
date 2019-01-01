package com.limp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ScheduledFuture;

/**
 * @intro ：如何动态添加修改删除定时任务
 * @auth ： shinians
 * @time ： 2018/12/29 15:32
 * @website： www.shinians.com
 */
@RestController
@Component  //不要忘记！不要忘记！
public class TaskController {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

    private String cronStr = "*/5 * * * * *";

    /**
     * 首先这里我们需要重新认识一个类ThreadPoolTaskScheduler：线程池任务调度类，能够开启线程池进行任务调度。
     *
     * ThreadPoolTaskScheduler.schedule()方法会创建一个定时计划ScheduledFuture，在这个方法需要添加两个参数，
     * Runnable（线程接口类） 和CronTrigger（定时任务触发器）
     */
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    /**
     * ScheduledFuture中有一个cancel可以停止定时任务
     */
    private ScheduledFuture<?> future;

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    /**
     * 开始一个定时任务
     * @return
     */
    @RequestMapping("/start")
    public String startCron() {

        future = threadPoolTaskScheduler.schedule(new MyRunnable(), new CronTrigger("0/5 * * * * *"));
        System.out.println("DynamicTask.startCron()");
        return "startCron";
    }

    /**
     * 关闭定时任务
     * @return
     */
    @RequestMapping("/stop")
    public String stopCron() {

        if (future != null) {
            future.cancel(true);
        }
        System.out.println("动态关闭定时任务");
        return "success";
    }

    /**
     * 重启定时任务
     * @return
     */
    @RequestMapping("/restart")
    public String restart() {
        stopCron();// 先停止，在开启.
        future = threadPoolTaskScheduler.schedule(new MyRunnable(), new CronTrigger("*/10 * * * * *"));
        System.out.println("动态重启");
        return "动态改定时任务";
    }

    @GetMapping(value = "start1")
    public String startTask(){
            System.out.println("startCron1 >>>>");
            threadPoolTaskScheduler.schedule(new MyRunnable(), new Trigger(){
                @Override
                public Date nextExecutionTime(TriggerContext triggerContext){
                    System.out.println("开始运行"+new Date());
                    return new CronTrigger(cronStr).nextExecutionTime(triggerContext);
                }
            });

            System.out.println("startCron1 <<<<");
            return "success";
    }

    /**
     * run方法
     */
    private class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("run方法运行，" + dateFormat.format(new Date()));
        }
    }
}
