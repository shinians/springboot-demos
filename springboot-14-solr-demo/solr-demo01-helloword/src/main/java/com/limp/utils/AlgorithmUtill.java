package com.limp.utils;

import com.limp.bean.MetaBean;
import com.limp.framework.utils.StrUtils;

import java.util.*;

/**
 * @intro ：
 * @auth ： shinians
 * @time ： 2019/6/6 10:49
 * @website： www.shinians.com
 */
public class AlgorithmUtill {
    private static Map<String, Float> ruleMap = new HashMap<String, Float>() {
        {
            put("typeName", 5.0f);
            put("code", 0.2f);
        }

    };

    /**
     * 返回重新打分后的数据
     * @param metaBeanList
     * @param metaBeanField
     * @return
     */
    public static List<MetaBean> decTreeScoreSort(List<MetaBean> metaBeanList, MetaBean metaBeanField) {
        /**
         * 调整评分
         */
        List<MetaBean> metaBeans=new ArrayList<MetaBean>();
        for (MetaBean metaBean : metaBeanList) {
            metaBean.setScore(getMetaScore(metaBeanField,metaBean));
            metaBeans.add(metaBean);
        }
        //评分排序
        Collections.sort(metaBeans, new Comparator<MetaBean>(){
            @Override
            public int compare(MetaBean a, MetaBean b) {//将List依照出生日期倒序排序
                return b.getScore()-a.getScore()>0?1:(b.getScore()-a.getScore()==0?0:-1);
            }
        });
        return metaBeans;
    }

    /**
     * 获取字段的匹配得分
     * @param metaBeanField 字段实体类（需要匹配对象）
     * @param metaBeanDict  与之匹配实体类
     * @return
     */
    public static float getMetaScore(MetaBean metaBeanField, MetaBean metaBeanDict) {
        float score=metaBeanDict.getScore();

        //定义条件熵评分

        //定义初始积分
        float scoreExt=0;
        // 判断字段类型 是否能否匹配
        if(!StrUtils.isBlank(metaBeanField.getTypeName())&&
                !StrUtils.isBlank(metaBeanDict.getTypeName())&& metaBeanField.getTypeName().equalsIgnoreCase(metaBeanDict.getTypeName())){
            scoreExt+=ruleMap.get("typeName");
        }
        /*************根据code值进行评分******************/
        /**
         * 匹配一个字符加1分，上线5分
         */
        if(!StrUtils.isBlank(metaBeanField.getCode())&&
                !StrUtils.isBlank(metaBeanDict.getCode())){
            //将字符串转换为 char数组
            char[] metaCodeChar = metaBeanField.getCode().toCharArray();
            for(int i=0;i<metaCodeChar.length;i++){
                if(metaBeanDict.getCode().indexOf(metaCodeChar[i])>-1){
                    scoreExt+=ruleMap.get("code");
                }
            }
        }
        //scoreExt 占比0.1，缩小其他评分对score的影响
        score+=scoreExt*0.1;
        return  score;

    }
}
