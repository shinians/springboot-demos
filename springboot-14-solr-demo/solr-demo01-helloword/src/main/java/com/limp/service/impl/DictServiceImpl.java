package com.limp.service.impl;

import com.limp.bean.MetaBean;
import com.limp.bean.MetaDataBean;
import com.limp.framework.utils.StrUtils;
import com.limp.service.DictService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @intro ：
 * @auth ： shinians
 * @time ： 2019/5/29 17:04
 * @website： www.shinians.com
 */
@Service
public class DictServiceImpl implements DictService {
//    private  static  final String solrUrl="http://127.0.0.1:8983/solr/metadata_db";
    private  static  final String solrUrl="http://192.168.26.160:8983/solr/metadata_db";

    /**
     * 删除doc
     * @param ids 逗号分割的字符串
     * @return 删除是否成功
     */
    @Override
    public boolean delteByIds(String ids) {
        //创建Solr的客户端链接对象
//      SolrClient solrServer=new HttpSolrClient(solrUrl); //4.0版本方式
        SolrClient solrServer =  new HttpSolrClient.Builder(solrUrl).build();
        try {
            //根据ID集合批量删除
            solrServer.deleteById(Arrays.asList(ids.split(",")));
            //提交
            solrServer.commit();
        } catch (Exception e) {
            //删除失败
            e.printStackTrace();
            return  false;
        }
        return true;
    }

    /**
     * 测试新增
     * @return
     */
    public boolean addMeataDataTest() {
        SolrClient solrServer =  new HttpSolrClient.Builder(solrUrl).build();
        /******************方式一新增文档********************/
        try {
            //创建一个文档对象
            SolrInputDocument sd = new SolrInputDocument();
            //添加域
            sd.addField("id", "testtest");
            sd.addField("md_name", "testName2");
            sd.addField("md_code", "testcode2");
            try {
                solrServer.add(sd);
                solrServer.commit();
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }

            /******************方式二 新增文档********************/
            //实例化MetaDataBean
            MetaDataBean metaDataBean=new MetaDataBean();
            //初始化id
            metaDataBean.setId("test123456");
            //初始化名称
            metaDataBean.setName("testname");
            //初始化编码
            metaDataBean.setCode("testcode");
            //新增bean
            solrServer.addBean(metaDataBean);
            //提交
            solrServer.commit();
            solrServer.close();
        } catch (Exception e) {
            e.printStackTrace();
            return  false;
        }
        return true;
    }

    /**
     * 测试clod
     * @throws Exception
     */
    public void addCloud() throws Exception {
        List<String> zkHosts = new ArrayList<String>();
        zkHosts.add("192.168.25.120:2181");
        zkHosts.add("192.168.25.120:2182");
        zkHosts.add("192.168.25.120:2183");
        Optional<String> zkChroot = Optional.of("/");
        //builder的构造函数需要一个List和一个Optional
        CloudSolrClient.Builder builder = new CloudSolrClient.Builder(zkHosts, zkChroot);
        CloudSolrClient solrClient = builder.build();
        solrClient.setDefaultCollection("collection");
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", "cloud");
        document.addField("item_title", "这是一个cloud测试");
        solrClient.add(document);
        solrClient.commit();
        solrClient.close();
    }


    /**
     * 根据查询条件获取bean集合
     * @param sql  查询语句
     * @param fqSql  过滤语句
     * @return
     */
    public List<MetaBean> metaBeansList(String sql, String fqSql){
        //创建Solr的客户端链接对象
        SolrClient solrServer =  new HttpSolrClient.Builder(solrUrl).build();
        //创建solr的查询对象
        SolrQuery sq=new SolrQuery();
        //设置查询条件【1】这里*:*表示获取所有数据，第一个*对应field，第二个对应查询的条件。
        //sq.set("q","item_title:3 AND item_desc:东西  OR item_sell_point:好看" );
        sq.set("q",sql );

        //设置分页【注】
        sq.setStart(0);
        sq.setRows(20);

        //排序【注】   SolrQuery.ORDER.desc   SolrQuery.ORDER.asc
       // sq.addSort("sortFieldName ", SolrQuery.ORDER.asc);

        //设置过滤条件【2】
        if(!StrUtils.isBlank(fqSql)){
            sq.set("fq", fqSql);
//          sq.setFilterQueries("author_name:出山","filesize:[* TO 4000000]");
        }
        //【3】“fl” 只查询指定域  ；是solr返回数据时返回哪些字段;*表示返回所有存在字段，后面加上“score”就表示返回数据时同时返回该条数据匹配的score。
        sq.set("fl", "*,score");
        //设置高亮【4】
        //1.打开开关
        sq.setHighlight(true);
        //2.指定高亮域
        sq.addHighlightField("md_name");
        //3.前缀
        sq.setHighlightSimplePre("<font style='color:red'>");
        //后缀
        sq.setHighlightSimplePost("</font>");
        QueryResponse qr= null;
        try {
            //执行查询
            qr = solrServer.query(sq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //获取查询结果
        SolrDocumentList sds=qr.getResults();
        //获取查询的记录数【5】
        long total=sds.getNumFound();
        System.out.println("数量："+total);

        //获取返回结果的最大得分【6】
        float scoreMax=sds.getMaxScore();
        System.out.println("score："+scoreMax);

        //获取返回结果的偏移量
        long start=sds.getStart();

        List<MetaBean> metaBeanList =new ArrayList<>();
        for(SolrDocument sd:sds){//默认取出10条记录
            MetaBean metaBean=new MetaBean();
            //查询出的结果标题设置高亮
            Map<String, Map<String, List<String>>> highlighting =  qr.getHighlighting();
            List<String > list = highlighting.get(sd.get("id")).get("md_name");
            String md_name;
            if(list != null && list.size()>0){
                md_name = list.get(0);
            }else {
                md_name = (String)sd.get("md_name");
            }
            /******************初始化实体对象****************************/

            String id=(String) sd.getFieldValue("id");
//            String name=(String) sd.getFieldValue("md_name");
            String code=(String) sd.getFieldValue("md_code");
            String typeName=(String) sd.getFieldValue("md_type_name");
            String intro=(String) sd.getFieldValue("md_intro");
            String score=sd.getFieldValue("score")+"";
            String md_relations=(String) sd.getFieldValue("md_relations");
            metaBean.setId(id);
            metaBean.setName(md_name);
            metaBean.setScore(Float.parseFloat(score));
            metaBean.setMaxScore(scoreMax+"");
            metaBean.setCode(code);
            metaBean.setTypeName(typeName);
            metaBean.setIntro(intro);
            metaBean.setRelation(md_relations);
            //添加到list集合
            metaBeanList.add(metaBean);
        }
        return  metaBeanList;
    }

    public static void main(String[] args) {
        DictServiceImpl dictService=new DictServiceImpl();
        dictService.delteByIds("testtest");
    }
}
