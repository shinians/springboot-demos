package com.limp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @intro ：
 * @auth ： shinians
 * @time ： 2019/1/1 0:57
 * @website： www.shinians.com
 */
@Service
public class ApiService {
    private Logger logger= LoggerFactory.getLogger(ApiService.class);
    @Autowired
    RestTemplate restTemplate;
    //定义访问ip
    public  String IP="http://localhost:8080";

    /**
     * 通过get方式发送请求【1】
     */
    public void getObjectByGet(){

//        调用服务提供者提供的接口时，可能需要传递参数，有两种不同的方式，如下：
        //第一种:参数动态扩展

        String result1 = restTemplate.getForObject(IP+
                "/api/users?name={1}&age={2}", String.class,"李白",23);
        logger.info("第一种：输出结果{}",result1);

        //第二种
        Map<String, String> map = new HashMap<>();
        map.put("name", "李白");
        String quote = restTemplate.getForObject(IP+
                "/api/users?name={name}", String.class,map);
        logger.info("第二种：输出结果{}",quote);

    }

    /**
     * 第二种方式
     */
    public void getForEntityByGet(){

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(IP+
                "/api/users?name={1}&age={2}", String.class,"李白",23);
        String body = responseEntity.getBody();

        HttpStatus statusCode = responseEntity.getStatusCode();
        int statusCodeValue = responseEntity.getStatusCodeValue();
        HttpHeaders headers = responseEntity.getHeaders();
        logger.info(body);
        logger.info(statusCode.toString());
        logger.info(statusCodeValue+"");
        logger.info(headers.toString());

    }

    /**
     * post请求测试
     */
    public void getObjectByPost(){
        Map<String, String> map = new HashMap<>();
        map.put("name", "李白");
        String resp = restTemplate.postForObject(IP+"/api/user", map, String.class);
        logger.info("返回结果");
        logger.info(resp);
    }


    /**
     * PUT 请求测试
     */
    public void getObjectByPut(){
        Map<String, String> map = new HashMap<>();
        map.put("name", "李白");
        restTemplate.put(IP+"/api/user", map);
        logger.info("日志请查看controller");
    }
    /**
     * del 请求测试
     */
    public void getObjectByDel(){
//        Map<String, String> map = new HashMap<>();
//        map.put("name", "李白");
        restTemplate.delete(IP+"/api/user/12");
        logger.info("日志请查看controller");
    }


        public void test(){
        /**
         * 说明：
         1）url: 请求地址；
         2）method: 请求类型(如：POST,PUT,DELETE,GET)；
         3）requestEntity: 请求实体，封装请求头，请求内容
         4）responseType: 响应类型，根据服务接口的返回类型决定
         5）uriVariables: url中参数变量值
         */

    }

}
