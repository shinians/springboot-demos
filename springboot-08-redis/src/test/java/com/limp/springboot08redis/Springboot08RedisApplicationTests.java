package com.limp.springboot08redis;

import com.limp.domain.Student;
import com.limp.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot08RedisApplicationTests {
	public static Logger logger= LoggerFactory.getLogger(Springboot08RedisApplicationTests.class);

	@Autowired
	RedisTemplate<Object,Student> redisTemplate;
	@Test
	public void contextLoads() {
	}
	@Autowired
	RedisService redisService;
	@Test
	public void testRedis(){
		redisService.set("name","spring boot redis");
		redisService.set("age","11");
		logger.info("输出结果");
		logger.info(redisService.getValue("name"));
		logger.info(redisService.getValue("age"));
	}

	/**
	 * 测试 存储student学生和取得学生类
	 */
	@Test
	public void testSetAndGetSudent(){
		//初始化学生，然后设置缓存
		Student student=new Student("12","21",3);
		ValueOperations<Object, Student> ops = redisTemplate.opsForValue();
		ops.set("student"+student.getId(),student,10, TimeUnit.MINUTES);//1分钟过期

		//从缓存中获取学生
		ValueOperations<Object, Student> opsStudent = this.redisTemplate.opsForValue();

		logger.info("输出结果");
		Student student1=opsStudent.get("student"+student.getId());
		logger.info(student1.toString());
	}


}

