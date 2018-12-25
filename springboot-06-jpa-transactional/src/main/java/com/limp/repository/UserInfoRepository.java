package com.limp.repository;

import com.limp.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


/**
 * @intro ：
 * @auth ： shinians
 * @time ： 2018/12/24 0:10
 * @website： www.shinians.com
 */
//继承JpaRepository来完成对数据库的操作

@Transactional//执行修改方法时一定要添加这个注解和@Modifying注解
public interface UserInfoRepository  extends JpaRepository<UserInfo,String> {

    @Modifying
    @Query(value = "update lp_users set password = :password where id = :id",nativeQuery = true)
    void resetPasswordById(@Param("id") String id, @Param("password") String password);

    /**报错：
     * java.sql.SQLException: Can not issue data manipulation statements with executeQuery().
     * 解决方式：
     * 在修改方法上面添加@Modifying 注解
     * 报错：
     * javax.persistence.TransactionRequiredException: Executing an update/delete query
     * 解决方式：
     * 在UserRepository.java类上添加@Transactional注解
     */

}
