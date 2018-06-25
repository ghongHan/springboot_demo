package com.hskj;

import com.hskj.common.util.Page;
import com.hskj.domain.Train;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;

import java.util.List;

/**
 * Created by hongHan_gao
 * Date: 2018/6/20
 * 参考文档：
 *         https://blog.csdn.net/qq279862451/article/details/80685706
 */


public class TestSolr {

    private static final String URL = "http://localhost:8983/solr/myCollections";

    private static HttpSolrClient server = null;

    public static void init(){
        //与server创建连接
        server = new HttpSolrClient(URL);
    }

    /**
     * 简单查询
     */
    @Test
    public void queryIndex() throws Exception {
        TestSolr.init();
        //创建SolrQuery对象
        SolrQuery query = new SolrQuery();
        //设置查询条件,名称“q”是固定的且必须 的
        query.set("q", "id:2");
        //调用server的查询方法，查询索引库
        QueryResponse response = server.query(query);
        //查询结果
        SolrDocumentList result = response.getResults();
        //查询结果总数
        long count = result.getNumFound();
        System.out.println("查询的结果总数:" + count);

        for(SolrDocument data : result){
            System.out.println(data.get("id"));
            System.out.println(data.get("diesel"));
            System.out.println(data.get("name"));
            System.out.println(data.get("speed"));
            System.out.println(data.get("station_id"));
            System.out.println(data.get("create_time"));
        }
    }

    /**
     * 复杂查询并高亮显示
     */
    public static void queryIndex2(Page page) throws Exception {
        TestSolr.init();
        //创建SolrQuery对象
        SolrQuery query = new SolrQuery();
        //设置查询条件
        query.set("q","到九江的高铁");
        //设置过滤条件
//        query.setFilterQueries("*高铁");
        //设置排序条件
        query.setSort("create_time", SolrQuery.ORDER.desc);
        //设置分页信息
        query.setStart((page.getPageNo() - 1)*page.getPageSize());
        query.setRows(page.getPageSize());
        //设置显得的域的列表
        query.setFields("id","name","diesel","speed", "create_time", "station_id");
        //设置默认搜索域
        query.set("df", "name");
        //设置高亮字段
        query.setHighlight(true);
        query.addHighlightField("name");
        query.setHighlightSimplePre("<font color='red'>");
        query.setHighlightSimplePost("</font>");
        //调用server的查询方法，查询索引库
        QueryResponse response = server.query(query);
        //查询结果
        SolrDocumentList result = response.getResults();
        //查询结果总数
        long count = result.getNumFound();
        System.out.println("查询结果：" + count);

        for(SolrDocument data : result){
            System.out.println("id:" + data.get("id") + "\t"
                                + "diesel:" + data.get("diesel") + "\t"
                                + "name:" + data.get("name") + "\t"
                                + "speed:" + data.get("speed") + "\t"
                                + "station_id:" + data.get("station_id") + "\t"
                                + "create_time:" + data.get("create_time"));
        }

    }

    public static void main(String[] args) throws Exception {
        TestSolr.queryIndex2(new Page(1,2));
    }

}
