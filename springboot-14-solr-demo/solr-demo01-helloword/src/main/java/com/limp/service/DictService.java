package com.limp.service;

import com.limp.bean.MetaBean;

import java.util.List;

/**
 * @intro ：维护元数据字典Service
 * @auth ： shinians
 * @time ： 2019/5/29 17:02
 * @website： www.shinians.com
 */
public interface DictService {
    /**
     * 删除IDS
     * @param ids 逗号分割的字符串
     * @return
     */
    boolean delteByIds(String ids);

//    boolean addMetaBean();

    /**
     * 根据条件查询MetaBean集合
     * @param sql
     * @param fqSql
     * @return
     */
    public List<MetaBean> metaBeansList(String sql, String fqSql);
}
