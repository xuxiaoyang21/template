package com.starry.sky.es;

import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.shield.ShieldPlugin;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.starry.sky.es.ElasticSearchFactory.CLUSTER_NAME;

public class EsController {


    public static void main(String[] args) {


        //设置
        Settings settings = ImmutableSettings.settingsBuilder()
                .put(CLUSTER_NAME, "es_cluster")
                .put("shield.user","es_admin:111111")
                .build();
        //获取客户端
        Client client =  new TransportClient(settings)

                .addTransportAddress(new InetSocketTransportAddress("localhost",9300));


        IndicesAdminClient indicesAdminClient = client.admin()
                .indices();
        String[] str = indicesAdminClient.prepareGetIndex().execute().actionGet().getIndices();
        Map<String, Object> map = new HashMap<String,Object>();
                map.put("userName", "张三");
                 map.put("sendDate", new Date());
                 map.put("msg", "你好李四");
       // client.prepareIndex("momo", "tweet").setSource(map).get();
        SearchResponse response = client.prepareSearch("logstash-2019.11.11").setTypes("doc").execute().actionGet();




    }
}
