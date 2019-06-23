package com.limp.bean;

import org.apache.solr.client.solrj.beans.Field;

/**
 * @intro ：
 * @auth ： shinians
 * @time ： 2019/5/10 11:58
 * @website： www.shinians.com
 */
public class MetaDataBean {
    /**
     * Field
     */
    @Field("id")
    private String id;
    @Field("md_name")
    private String name;
    @Field("md_code")
    private String code;
    @Field("relation")
    private String relation;

    //最大评分
    private String maxScore;
    //每天记录评分
    private String score;


    //类型描述
    private String typeName;

    //介绍
    private String intro;

    //规则描述 例如 yyyy-MM-mm 长度大小Max  60
    private  String regIntro;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getRegIntro() {
        return regIntro;
    }

    public void setRegIntro(String regIntro) {
        this.regIntro = regIntro;
    }

    public String getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(String maxScore) {
        this.maxScore = maxScore;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}
