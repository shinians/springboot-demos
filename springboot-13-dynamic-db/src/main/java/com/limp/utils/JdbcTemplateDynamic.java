package com.limp.utils;

import com.limp.pojo.DynamicDataSourceEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * @intro ：
 * @auth ： shinians
 * @time ： 2019/3/8 12:39
 * @website： www.shinians.com
 */
public class JdbcTemplateDynamic {
    DriverManagerDataSource dataSource;
    JdbcTemplate jdbcTemplate;

    public JdbcTemplateDynamic(DynamicDataSourceEntity dynamicDataSourceEntity) {
        //        设置数据库信息
        this.dataSource = new DriverManagerDataSource();
        this.dataSource.setDriverClassName(dynamicDataSourceEntity.getDriverClass());
        this.dataSource.setUrl(dynamicDataSourceEntity.getUrl());
        this.dataSource.setUsername(dynamicDataSourceEntity.getDbUser());
        this.dataSource.setPassword(dynamicDataSourceEntity.getDbPassword());
        //    设置数据源
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public DriverManagerDataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DriverManagerDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
