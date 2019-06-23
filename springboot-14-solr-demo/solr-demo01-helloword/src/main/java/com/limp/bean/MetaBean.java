package com.limp.bean;

import org.apache.solr.client.solrj.beans.Field;

import java.io.Serializable;

/**
 * @intro ：
 * @auth ： shinians
 * @time ： 2019/5/10 11:58
 * @website： www.shinians.com
 */
public class MetaBean implements Serializable {
    @Field("id")
    private String id;
    @Field("name")
    private String name;
    @Field("code")
    private String code;
    @Field("relation")
    private String relation;

    //最大评分
    private String maxScore;
    //每天记录评分
    private float score;


    //machType 查询类型  1 为表下字段推荐匹配，返回数量为一
    private String searchType;

    //类型描述
    private String typeName;

    //介绍
    private String intro;
    //规则描述 例如 yyyy-MM-mm 长度大小Max  60
    private  String regIntro;


    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }



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

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
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
