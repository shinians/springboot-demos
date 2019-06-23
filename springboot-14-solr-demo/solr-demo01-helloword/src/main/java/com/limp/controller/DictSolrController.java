package com.limp.controller;

import com.limp.bean.MetaBean;
import com.limp.framework.core.bean.Result;
import com.limp.framework.core.constant.Constant;
import com.limp.framework.utils.JsonUtils;
import com.limp.framework.utils.StrUtils;
import com.limp.service.DictService;
import com.limp.utils.AlgorithmUtill;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.TermsResponse;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @intro ：
 * @auth ： shinians
 * @time ： 2019/5/10 11:15
 * @website： www.shinians.com
 */
@RestController
@CrossOrigin
public class DictSolrController {
    //    @Autowired
//    private SolrTemplate solrTemplate;
    //单机版solr连接时，创建HttpSolrServer对象；集群版创建CloudSolrServer对象
    private static final String solrUrl = "http://192.168.26.160:8983/solr/metadata_db";
//    private  static  final String solrUrl="http://127.0.0.1:8983/solr/metadata_db";

    /**
     * dict Service服务
     */
    @Autowired
    private DictService dictService;

    @RequestMapping("hello")
    public String hello() {
        return "hello";
    }

    /**
     * 查询词库
     *
     * @param metaBean
     * @return
     */
    @RequestMapping("search")
    public String searchDic(MetaBean metaBean) {
        //定义查询语句
        String fqSql = "";
        if (!StrUtils.isBlank(metaBean.getTypeName())) {
            fqSql = "md_type_name:" + metaBean.getTypeName();
        }

        //md_name加权展示
        List<MetaBean> metaBeanList = dictService.metaBeansList("md_name:" + metaBean.getName() + "^2", fqSql);
//      List<MetaBean> metaBeanList= dictService.metaBeansList("md_name:"+metaBean.getName(),fqSql);
        //如果为空，则在copy中查询
        if (metaBeanList.size() == 0) {
            metaBeanList = dictService.metaBeansList("md_copy:" + metaBean.getName(), fqSql);
//          metaBeanList= metaBeansList("md_relations:*"+keyWord+"*");
        }
        //排序
        List<MetaBean>newmetaBeanList= AlgorithmUtill.decTreeScoreSort(metaBeanList,metaBean);
        //searchType=1 返回一条推荐结果
        if(Constant.STRING_1.equalsIgnoreCase(metaBean.getSearchType())){
            return newmetaBeanList.size()>0? JsonUtils.toJson(newmetaBeanList.get(0)):null;
        }
        //searchType=2 返回20条推荐结果
        if(Constant.STRING_2.equalsIgnoreCase(metaBean.getSearchType())){
            return JsonUtils.toJson(newmetaBeanList);
        }
        return Result.getInstance("200", "success", metaBeanList, null).getJson();
    }

     /*   //在SlorJ中调用ping的例子
        SolrPing ping = new SolrPing();
        ping.getParams().add("distrib", "true"); //To make it a distributed request against a collection
        HttpSolrServer solrServer=new HttpSolrServer(solrUrl);
        SolrPingResponse rsp = ping.process(solrServer);
        int status = rsp.getStatus();*/

    /**
     * 建立关系
     *
     * @param metaBean
     * @return
     */
    @RequestMapping("conn")
    public String conn(MetaBean metaBean) {
        //创建Solr的客户端链接对象
        SolrClient solrServer =  new HttpSolrClient.Builder(solrUrl).build();
        //创建一个文档对象
        SolrInputDocument sd = new SolrInputDocument();
        //添加域
        sd.addField("id", metaBean.getId());
        sd.addField("md_name", metaBean.getName());
        sd.addField("md_code", metaBean.getCode());
        sd.addField("md_relations", metaBean.getRelation());
        try {
            solrServer.add(sd);
            solrServer.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.getInstance("200", "success", "", null).getJson();
    }

    public static void main(String[] args) {
        SolrClient client =  new HttpSolrClient.Builder(solrUrl).build();
        //[2]创建SolrQuery
        SolrQuery query = new SolrQuery();
        //[3]设置查询参数
        query.set("q", "*:*");
        query.set("qt", "/terms");//设置requestHandler

        // parameters settings for terms requesthandler
        // 参考 http://wiki.apache.org/solr/termscomponent
        query.set("terms", "true");//开启terms
        query.set("terms.fl", "md_name");//必须的. 统计的字段

        //指定下限
        // query.set("terms.lower", ""); // term lower bounder开始的字符  ，// 可选的. 这个term开始。如果不指定,使用空字符串,这意味着从头开始的。
        // query.set("terms.lower.incl", "true");
        // query.set("terms.mincount", "1");//可选的. 设置最小统计个数
        // query.set("terms.maxcount", "100"); //可选的. 设置最大统计个数

        //http://localhost:8983/solr/terms?terms.fl=text&terms.prefix=家//
        //using for auto-completing   //自动完成
        //query.set("terms.prefix", "家");//可选的. 限制匹配，设置terms前缀是以什么开始的。
        query.set("terms.regex", "元数据+.*");
        query.set("terms.regex.flag", "case_insensitive");

        //query.set("terms.limit", "20"); //设置每页返回的条目数量
        //query.set("terms.upper", ""); //结束的字符
        //query.set("terms.upper.incl", "false");
        //query.set("terms.raw", "true");

        query.set("terms.sort", "count");//terms.sort={count|index} -如果count，各种各样的条款术语的频率（最高计数第一）。 如果index，索引顺序返回条款。默认是count

        // 查询并获取结果
        try {
            QueryResponse response = client.query(query);
            System.out.println("1");
            if (response != null) {
                TermsResponse termsResponse = response.getTermsResponse();
                if (termsResponse != null) {
                    Map<String, List<TermsResponse.Term>> termsMap = termsResponse.getTermMap();
                    for (Map.Entry<String, List<TermsResponse.Term>> termsEntry : termsMap.entrySet()) {
                        //System.out.println("Field Name: " + termsEntry.getKey());
                        List<TermsResponse.Term> termList = termsEntry.getValue();
                        for (TermsResponse.Term term : termList) {
                            System.out.println(term.getTerm() + " : " + term.getFrequency());
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
