package com.starry.sky.es;


import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * elasticsearch 客户端初始化
 */
@Configuration
public class ElasticSearchFactory {


    @Value("${es.ip}")
    private String esIp;
    @Value("${es.cluster}")
    private String clusterValue;

    public static final String CLUSTER_NAME = "cluster.name";

    @Autowired
    private RedisTemplate redisTemplate;
    //@Bean(name="client")
    public Client initEsClient() {

        return null;
//        ValueOperations operations = redisTemplate.opsForValue();
//        Object str =  redisTemplate.opsForValue().get("aaa");
//        System.out.println(str);
//
//        Properties properties = new Properties();
//        String path = ElasticSearchUtil.class.getResource("/").getPath()+"config.properties";
//        try {
//            InputStream in = new FileInputStream(new File(path));
//            properties.load(in);
//            ElasticSearchUtil.properties = properties;
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
//        //设置
//        Settings settings = ImmutableSettings.settingsBuilder()
//                .put(CLUSTER_NAME, clusterValue)
//                .put("shield.user","es_user:111111")
//                .build();
//        //获取客户端
//        Client client =  new TransportClient(settings)
//                .addTransportAddress(new InetSocketTransportAddress(esIp,9300));
//
//        return client;

      /*  BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("kkid","330521210034"));


        SearchResponse qqq = client.prepareSearch("traffic_index_201809201812")
                .setTypes("AfterVehicle")
                .setQuery(boolQueryBuilder)
                .execute()
                .actionGet();

        //解析返回数据
        System.out.println(qqq);*/
    }

    /**
     * 获取查询的工厂
     * @return
     */
    //@Bean(name = "builder")
    public SearchRequestBuilder intSearchRequestBuilder() {
        return initEsClient().prepareSearch("traffic_index_201809201812")
                .setTypes("AfterVehicle");
    }


}
