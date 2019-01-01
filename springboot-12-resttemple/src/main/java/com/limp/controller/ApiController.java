package com.limp.controller;

import com.limp.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


/**
 * @intro ：
 * @auth ： shinians
 * @time ： 2019/1/1 11:09
 * @website： www.shinians.com
 */
@RestController
public class ApiController {
    Logger logger= LoggerFactory.getLogger(ApiController.class);

    @GetMapping("/api/users")
    public String getUsersInfo(User user){
        logger.info("get查询方法");
        return "返回结果:通过get方式查询users传参"+user.toString();
    }
    @GetMapping("/api/user/{id}")
    public String getUserInfo(@PathVariable String id){
        User user=new User("springboot",23,id);
        logger.info("get 方法");
        return  user.toString();
    }

    /**
     * 新增用户并返回用户信息
     * @param user
     * @return
     */
    @PostMapping("/api/user")
    public String addUserInfo(@RequestBody User user){
        logger.info("post 新增用户方法");
        logger.info(user.toString());
        return  user.toString();
    }
    /**
     * 新增用户并返回用户信息
     * @param id
     * @return
     */
    @DeleteMapping("/api/user/{id}")
    public String delUserInfo(@PathVariable String id){
        logger.info("delete 删除用户方法"+id);
        return  "用户删除成功id:"+id;
    }
    /**
     * 注意：@RequestBody不添加，rest请求过来的user是为空的
     * 修改用户并返回用户信息
     * @param user
     * @return
     */
    @PutMapping("/api/user")
    public String updateUserInfo(@RequestBody  User user){
        logger.info("put 修改用户方法");
        logger.info(user.toString());
        return  user.toString();
    }
}
