package com.limp.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @intro ：
 * http://cron.qqe2.com/
 *
 * 定时任务&并非编程
 * @auth ： shinians
 * @time ： 2018/12/29 10:22
 * @website： www.shinians.com
 */
//@Component
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

    //可以用原子方式更新的 boolean 值
    private AtomicBoolean firstTime = new AtomicBoolean(true);

    //AtomicInteger这个类的存在是为了满足在高并发的情况下,原生的整形数值自增线程不安全的问题。
    private AtomicInteger number = new AtomicInteger();

    /**
     *  @Scheduled(fixedRate = 2000) ：上一次开始执行时间点之后5秒再执行
      * @Scheduled(fixedDelay = 5000) ：上一次执行完毕时间点之后5秒再执行
     *  @Scheduled(initialDelay=1000, fixedRate=5000) ：第一次延迟1秒后执行，之后按fixedRate的规则每5秒执行一次
     *  @Scheduled(cron=" /5 ") ：通过cron表达式定义规则，什么是cro表达式，自行搜索引擎。
     *
     *  fixedRate 还有一个误区就是，以为任务时长超过 fixedRate 时会启动多个任务实例，
     *             其实不会; 只不过会在上次任务执行完后立即启动下一轮。除非这个 Job 方法用
     *             @Async 注解了，使得任务不在 TaskScheduler 线程池中执行，而是每次创建新线程来执行
     *             通过下面的这个列子我们可以看到在第一休眠7秒之后 ，以后的几个定时任务同时执行
     *  fixedDelay 的逻辑就相当简单:总是上次任务结束 5 秒后，fixedDelay 不存在任务的预先编排操作，是相机而为。
     */
    @Scheduled(fixedRate = 2000)
    public void reportCurrentTime() {
        LocalTime start = LocalTime.now();
        System.out.println(Thread.currentThread() + " 开始时间： " + number.incrementAndGet() + " -->"  + start);
//        以原子方式设置为给定值，并返回以前的值
        if (firstTime.getAndSet(false)) {
            try {
                //休眠7秒
                Thread.sleep(7000);
            } catch (InterruptedException e) {
            }
        }
        LocalTime end = LocalTime.now();
        System.out.println(Thread.currentThread() + " 运行结束： " + number.get() + " @ " + end
                + ", 任务用时 " + (ChronoUnit.SECONDS.between(start, end)));
    }

}
