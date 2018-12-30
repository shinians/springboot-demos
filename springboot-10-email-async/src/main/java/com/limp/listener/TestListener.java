package com.limp.listener;

import com.limp.async.SleepService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * @intro ：
 * @auth ： shinians
 * @time ： 2018/12/30 13:37
 * @website： www.shinians.com
 */
@Component
public class TestListener implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(CommandLineRunner.class);

    @Autowired
    private final SleepService sleepService;

    public TestListener(SleepService sleepService) {
        this.sleepService = sleepService;
    }

    @Override
    public void run(String... args) throws Exception {
        long start = System.currentTimeMillis();
        Future<String> name1 = sleepService.sayHallo("springBoot-1");
        Future<String> name2 = sleepService.sayHallo("springBoot-2");
        Future<String> name3 = sleepService.sayHallo("springBoot-3");
        Future<String> name4 = sleepService.sayHallo("springBoot-4");
        logger.info("异步运行用时: " + (System.currentTimeMillis() - start));


        logger.info("线程池初始化调用，得到返回结果");
        logger.info("开始测试1{}",name1.get());
        logger.info("开始测试2{}",name2.get());
        logger.info("开始测试3{}",name3.get());
        logger.info("开始测试4{}",name4.get());

//        String name5= sleepService.sayHallo1("springBoot-5");
//        logger.info("开始测试4{}",name5);

    }
}
