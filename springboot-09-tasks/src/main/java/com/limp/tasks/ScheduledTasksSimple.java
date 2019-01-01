package com.limp.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @intro ：
 *
 * 定时任务&并非编程
 * @auth ： shinians
 * @time ： 2018/12/29 10:22
 * @website： www.shinians.com
 */
//@Component
public class ScheduledTasksSimple {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasksSimple.class);
    DateFormat dateFormat=new SimpleDateFormat("YYYY-MM-dd hh:mm;ss");
    /**
     * @Scheduled(fixedRate = 2000) 每隔2秒执行一次：上一次开始执行时间点之后5秒再执行
     * @Scheduled(fixedDelay = 2000) ：上一次执行完毕时间点之后2秒再执行
     */
    @Scheduled(fixedRate = 2000)
    public void reportCurrentTime() {
        log.info("我是定时任务我开始运行...{}",dateFormat.format(new Date()));
    }

}
