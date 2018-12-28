package com.limp.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.limp.domain.Student;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.net.UnknownHostException;

/**
 * //com.limp.domain.Student cannot be cast to com.limp.domain.Student

 * @intro ：
 * @auth ： shinians
 * @time ： 2018/12/28 0:17
 * @website： www.shinians.com
 */
@Configuration
@EnableCaching
public class RedisConfiguration   extends CachingConfigurerSupport {

    @Bean
    public RedisTemplate<Object, Student> redisTemplate(RedisConnectionFactory connectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object,Student> template=new RedisTemplate<Object, Student>();
        template.setConnectionFactory(connectionFactory);
        Jackson2JsonRedisSerializer<Student> jackson2JsonRedisSerializer=new Jackson2JsonRedisSerializer<Student>(Student.class);
        template.setDefaultSerializer(jackson2JsonRedisSerializer);
        return  template;
    }


    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory){

      RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        return RedisCacheManager
                .builder(RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory))
                .cacheDefaults(redisCacheConfiguration).build();

    }



    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Bean
    public Jackson2JsonRedisSerializer jackson2JsonRedisSerializer() {
        final Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(
                Object.class);
        final ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();
        objectMapper.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        return jackson2JsonRedisSerializer;
    }

}
