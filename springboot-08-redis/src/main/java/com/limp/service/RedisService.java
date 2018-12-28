package com.limp.service;

import com.limp.domain.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @intro ：
 * @auth ： shinians
 * @time ： 2018/12/27 23:38
 * @website： www.shinians.com
 */
@Service
public class RedisService {

    Logger logger= LoggerFactory.getLogger(RedisService.class);

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 设置缓存 key-value（包含过期时间）
     * @param key
     * @param value
     */
    public  void set(String key,String value){
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set(key,value,10, TimeUnit.MINUTES);//1分钟过期
    }

    public String getValue(String key){
        ValueOperations<String, String> ops = this.redisTemplate.opsForValue();
        return ops.get(key);
    }

    /**
     * 这其中有个报错：No cache could be resolved for 'Builder[public java.util.List com.es.service.evralarm.EvrAlarmCacheService.getEvrAlarmByAccountId(java.lang.String)] caches=[] | key=''EvrAlarm-'+#accountId' | keyGenerator='' | cacheManager='' | cacheResolver='' | condition='' | unless='' | sync='false'' using resolver 'org.springframework.cache.interceptor.SimpleCacheResolver@7fbfc31a'. At least one cache should be provided per cache operation.
     * @Cacheable注解中添加cacheNames即可
     *用实体类返回报错：java.lang.String cannot be cast to com.limp.domain.Student

     * 运行service方法
     * @param id
     * @return
     */
    @Cacheable(cacheNames="student",key = "#id")
    public Student getStudentById(String id){
        logger.info("运行service方法，获取学生信息id{}",id);
        Student student=new Student(id,"name+"+id,18);
        return student;
    }


}
