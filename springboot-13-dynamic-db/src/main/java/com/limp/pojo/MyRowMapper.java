package com.limp.pojo;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @intro ：
 * @auth ： shinians
 * @time ： 2019/3/8 11:19
 * @website： www.shinians.com
 */
public class MyRowMapper implements RowMapper<TestPo> {
    @Nullable
    @Override
    public TestPo mapRow(ResultSet resultSet, int i) throws SQLException {
//        获取结果集中的数据
       String name = resultSet.getString("name");
       String id = resultSet.getString("id");
//               把数据封装成Test对象
        TestPo test = new TestPo();
        test.setName(name);
        test.setId(id);
        return test;
    }
}
