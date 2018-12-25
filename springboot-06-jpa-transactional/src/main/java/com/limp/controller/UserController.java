package com.limp.controller;

import com.limp.domain.UserInfo;
import com.limp.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * @intro ：
 * @auth ： shinians
 * @time ： 2018/12/24 0:11
 * @website： www.shinians.com
 */
@RestController
@Transactional
public class UserController {
    @Autowired
    UserInfoRepository userInfoRepository;

    /**
     * 新增用户信息
     * IP:端口/user?id=201&account=201&isbindip=1&isbindmac=1
     * @param user
     * @return
     * 注：线上环境应该为post提交
     */
    @GetMapping("/user")
    public UserInfo insertUser(UserInfo user){
        UserInfo save = userInfoRepository.save(user);
        Integer.parseInt("error");
        return save;
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @GetMapping("/userdel/{id}")
    public String delUser(@PathVariable("id") String id){
        userInfoRepository.deleteById(id);
        return "success";
    }
    /**
     * 修改密码
     * @param id
     * @return
     */
    @GetMapping("/userupdate/{id}")
    public String updateUser(@PathVariable("id") String id){
        userInfoRepository.resetPasswordById(id,"123456");
        //saveAndFlush
        return "success";
    }

    @GetMapping("/user/{id}")
    public UserInfo getUser(@PathVariable("id") String id){
        //在测试方法上加入@Transactional注解可以解决报错的问题
//        UserInfo userList1 = userInfoRepository.getOne(id); //使用getOne()返回的是代理对象，无法直接操作，会出现hibernate lazyxxx  no session 的错误
        Optional<UserInfo> userList = userInfoRepository.findById(id);
        //需要结果过做判断，查询结果为null时会报NoSuchElementException
        if(userList.isPresent()){
            return userList.get();
        }
        return null;
    }

    /**
     * 多条件查询用户
     * http://localhost:8080/users?password=fcfc92fc7492b5fc7b74cf92b8b537fc
     * @param userInfo
     * @return
     */
    @GetMapping("/users")
    public List<UserInfo>  getUsers(UserInfo userInfo){
        Example<UserInfo> example=Example.of(userInfo);
        List<UserInfo> exampleResult = userInfoRepository.findAll(example);
        return exampleResult;
    }


}
