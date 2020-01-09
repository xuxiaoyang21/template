package com.xuxy.es;

import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.elasticsearch.search.aggregations.metrics.cardinality.CardinalityBuilder;
import org.elasticsearch.search.aggregations.metrics.cardinality.InternalCardinality;
import org.elasticsearch.search.aggregations.metrics.tophits.TopHitsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * elasticSearch 工具类
 */

public class ElasticSearchUtil {

    public static Properties properties  = new Properties();





    /**
     * 多字段聚合查询
     * 通过字段聚合
     * 开始时间
     * 结束时间
     *
     * 支持时间范围 精确查询
     * 单字段精确查询（等于或者不等于）
     * 多字段查询
     *
     *
     *
     * @param client es客户端
     * @param search 搜索参数实体
     * @param isOpenFilter 是否开启单字段反转查询
     * @return
     */
    public static SearchResponse getMangFieldAggsQuery(Client client, Search search, Boolean isOpenFilter) {

        //创建查询 bool
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //QueryBuilder idsQuery = idsQuery().ids("","",""); //通过id查询
        //ConstantScoreQueryBuilder scoreQueryBuilder = QueryBuilders.constantScoreQuery();

        // 添加时间范围字段
        if(search.getDataFlag()) { //是否开始时间字段查询
            boolQueryBuilder.must(QueryBuilders
                    .rangeQuery(search.getDateField()) //查询字段
                    .from(search.getStartDate()) //开始时间
                    .to(search.getEndDate()) //结束时间
            );
        }


        if(search.getManyTermFlag()) { //是否开启多字段联合精确查询
            for(int i = 0 ; i < search.getTermKeyList().size();i++) {
                boolQueryBuilder.must(QueryBuilders
                        .termsQuery(search.getTermKeyList().get(i),search.getTermValueList().get(i))
                );
            }
        } else {
            if(search.getTermFlag()) { //开启字段查询
                if(isOpenFilter) { //是否开启过滤
                    boolQueryBuilder.mustNot(QueryBuilders
                            .termsQuery(search.getTermKey(),search.getTermValues())

                    );
                } else {
                    boolQueryBuilder.must(QueryBuilders
                            .termsQuery(search.getTermKey(),search.getTermValues())

                    );
                }

            }
        }

        SearchRequestBuilder searchRequestBuilder = getSearchRequestBuilder(client)
                .setFrom(search.getFrom())
                .setSize(search.getSize())
                .setQuery(boolQueryBuilder);
        if(search.getSortFlag()) {
            searchRequestBuilder.addSort(search.getSortKey(),search.getSortRule());
        }

        //是否开启字段双层聚合
        if(search.getManyTermsFlag()){
            TermsBuilder termsBuilder = null;
            //遍历聚合字段
            for (int i = 0;i < search.getAggsKeyList().size();i++){
                TermsBuilder termsBuilder_temp =
                        AggregationBuilders.terms(search.getAggTermsAlias())
                                .field(search.getAggsKeyList().get(i))
                                .minDocCount(search.getMinDocCount());
                if(i==0){
                    termsBuilder = termsBuilder_temp;
                }else {
                    termsBuilder = termsBuilder.subAggregation(termsBuilder_temp);
                }
            }
            searchRequestBuilder.addAggregation(termsBuilder);
        }

        //是否开启时间和字段双层聚合
        if(search.getIsOpenTermToHistogram()) {
            //双层聚合  先字段聚合后时间聚合
            if(search.getTermToHistogram()) { //先字段后时间

                //字段聚合
                TermsBuilder termsBuilder =
                        AggregationBuilders.terms(search.getAggTermsAlias())
                                .field(search.getAggsKey())
                                .minDocCount(search.getMinDocCount())
                                .size(search.getAggsSize());
                DateHistogramBuilder dateHistogramBuilder =
                        AggregationBuilders.dateHistogram(search.getAggTermsAlias())
                                .field(search.getDateField())
                                .interval(search.getInterval())
                                .minDocCount(search.getMinDocCount())
                                .extendedBounds(search.getStartDate(),search.getEndDate());

                termsBuilder.subAggregation(dateHistogramBuilder);
                searchRequestBuilder.addAggregation(termsBuilder);

            } else {//双层聚合  先时间聚合后字段聚合
                //字段聚合
                TermsBuilder termsBuilder =
                        AggregationBuilders.terms(search.getAggTermsAlias())
                                .field(search.getAggsKey())
                                .minDocCount(search.getMinDocCount())
                                .size(search.getAggsSize()); //设置聚合展示的条数
                DateHistogramBuilder dateHistogramBuilder =
                        AggregationBuilders.dateHistogram(search.getAggTermsAlias())
                                .field(search.getDateField())
                                .interval(search.getInterval())
                                .minDocCount(search.getMinDocCount())
                                .extendedBounds(search.getStartDate(),search.getEndDate());

                dateHistogramBuilder.subAggregation(termsBuilder);
                searchRequestBuilder.addAggregation(dateHistogramBuilder);

            }

        } else {
            if(search.getAggsFlag()) { //是否开启单字段聚合
                //字段聚合
                TermsBuilder termsBuilder =
                        AggregationBuilders.terms(search.getAggTermsAlias())
                                .field(search.getAggsKey())
                                .minDocCount(search.getMinDocCount())
                                .size(search.getAggsSize());

//            termsBuilder.subAggregation(dateHistogramBuilder);
                //是否开启单字段聚合后 去重
                if(search.getRepetFlag()) {
                    CardinalityBuilder cardinalityBuilder =
                            AggregationBuilders.cardinality(search.getAggTermsAlias()).field(search.getRepetName())
                                    .precisionThreshold(10000);
                    termsBuilder.subAggregation(cardinalityBuilder);
                }
                //是否开启单字段聚合后 展示源数据
                if(search.getTopHitsFlag()) {
                    TopHitsBuilder topHitsBuilder =
                            AggregationBuilders.topHits(search.getAggTermsAlias())
                                    .setFrom(search.getTopHitFrom())
                                    .setSize(search.getTopHitsSize());
                    termsBuilder.subAggregation(topHitsBuilder);

                }

                searchRequestBuilder.addAggregation(termsBuilder);
            } else {
                //时间分片开启聚合
                if(search.getHisFlag()) {
                    DateHistogramBuilder dateHistogramBuilder =
                            AggregationBuilders.dateHistogram(search.getAggTermsAlias())
                                    .field(search.getHistKey())
                                    .interval(search.getInterval())
                                    .minDocCount(search.getMinDocCount())
                                    .timeZone("+08:00")
                                    .extendedBounds(search.getStartDate(),search.getEndDate());
                    searchRequestBuilder.addAggregation(dateHistogramBuilder);
                }
            }
        }

        //创建查询
        return searchRequestBuilder.execute().actionGet();
    }



    /**
     * 得到 过车总数
     * 开始时间
     * 结束时间
     *
     */
    public static SearchResponse getHitsCount(Client client, Search search) {
        //创建查询
        return  getSearchRequestBuilder(client).setSize(0)
                .setQuery(QueryBuilders.boolQuery().must(
                        QueryBuilders.rangeQuery(search.getDateField())
                        .from(search.getStartDate())
                        .to(search.getEndDate())
                ))
                .execute()
                .actionGet();
    }

    /**
     * 根据号牌号码的前缀查询
     * 开始时间
     * 结束时间
     * flag  true 前缀查询总数  false 前缀查询不包含总数
     *
     */
    public static SearchResponse getPrefixQuery(Client client, Search search, Boolean flag) {

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(
                QueryBuilders
                        .rangeQuery(search.getDateField())
                        .from(search.getStartDate())
                        .to(search.getEndDate()));
        //前缀查询  不包含总数
        if(flag) {
           boolQueryBuilder.must(QueryBuilders
                   .prefixQuery(search.getPrefixName()
                           ,search.getPrefixValue()));
        } else {
            boolQueryBuilder.mustNot(QueryBuilders
                    .prefixQuery(search.getPrefixName()
                            ,search.getPrefixValue()));
        }
        SearchRequestBuilder searchRequestBuilder =
                getSearchRequestBuilder(client).setSize(search.getSize())
                        .setQuery(boolQueryBuilder);

        //是否开启去重查询
        if(search.getRepetFlag()) {
            //查询后去重
            CardinalityBuilder cardinalityBuilder =
                    AggregationBuilders.cardinality(search.getAggTermsAlias())
                            .field(search.getRepetName())
                            .precisionThreshold(1000000);
            searchRequestBuilder.addAggregation(cardinalityBuilder);
        }




        //前缀查询 总数
        //创建查询
        return  searchRequestBuilder
                .execute()
                .actionGet();
    }

    /**
     * 得到 去重后的过车车辆
     * 开始时间
     * 结束时间
     *
     */
    public static SearchResponse getVelCarCount(Client client, Search search) {

        //聚合数据
        CardinalityBuilder cardinalityBuilder =
                AggregationBuilders.cardinality(search.getAggTermsAlias())
                .field(search.getRepetName())
                .precisionThreshold(1000000);

        //创建查询
        return  getSearchRequestBuilder(client).setSize(search.getSize())
                .setQuery(QueryBuilders.boolQuery().must(
                        QueryBuilders.rangeQuery(search.getDateField())
                                .from(search.getStartDate())
                                .to(search.getEndDate())
                ))
                .addAggregation(cardinalityBuilder)
                .execute()
                .actionGet();
    }



    /**
     *  获取去重后的结果
     * @param response 返回的json对象
     * @param alias terms设置的对象名称   聚合的别名
     * @return
     */
    public static InternalCardinality getCardinalityBucketIts(SearchResponse response, String alias) {
        //得到聚合的对象
        InternalCardinality bucketList = (InternalCardinality) response.getAggregations().getAsMap().get(alias);;
        return bucketList;
    }

    /**
     *  按字段统计结果 数据封装
     * @param response 返回的json对象
     * @param alias terms设置的对象名称   聚合的别名
     * @return
     */
    public static List<Terms.Bucket> getTermsBucketIts(SearchResponse response, String alias) {
        Terms terms = (Terms) response.getAggregations().getAsMap().get(alias);

        return terms.getBuckets();
    }

    /**
     *  按字段统计结果 数据封装
     * @param response 返回的json对象
     * @param alias terms设置的对象名称   聚合的别名
     * @return
     */
    public static Aggregation getBucketIts(SearchResponse response, String alias) {
        //bucket的父对象
        return response.getAggregations().getAsMap().get(alias);
    }

    /**
     * 通过buckets得到下一层的buckets
     *
     * 通过 terms 得到 terms
     * @param bucketsList
     * @return
     */
    public static Map<String, List<Histogram.Bucket>> getNextTermBuckets(List<Terms.Bucket> bucketsList, String hisKey){

        Map<String, List<Histogram.Bucket>> map = new HashMap<String, List<Histogram.Bucket>>();
        if(bucketsList !=null && bucketsList.size()>0) {
            //遍历buckets 封装map
            for(Terms.Bucket bucket : bucketsList) {
                Map<String, Aggregation> maps = bucket.getAggregations().getAsMap();
                for(String str : maps.keySet()) {
                    if(str.equals(hisKey)) {
                        Object obj = maps.get(str);
                        if(obj instanceof Histogram) {
                            Histogram stringTerms = (Histogram) maps.get(str);
                            map.put(bucket.getKey() + "", (List<Histogram.Bucket>) stringTerms.getBuckets());
                        } else {
                            return map;
                        }
                    }
                }
            }
        }

        return map;
    }

    /**
     * 通过bucket得到下一层的buckets
     *
     * 通过 terms 得到 terms
     * @param bucket
     * @return
     */
    public static List<Terms.Bucket> getNextTermBuckets_term(Terms.Bucket bucket, Search search){

        Terms terms = (Terms)bucket.getAggregations().getAsMap().get(search.getAggTermsAlias());

        return terms.getBuckets();
    }

    /**
     * 设置查询 index 和 type
     * @param client
     * @return
     */
    private static SearchRequestBuilder getSearchRequestBuilder(Client client) {
        String str = (String) properties.get("es.index");
        String type = (String) properties.get("es.type");
        String[] strings = str.split(",");
        return  client.prepareSearch(strings)
                .setTypes(type);
    }

//    @Resource(name = "client")
//    private Client client;

    /**
     * 通过时间字段和字段插叙
     * @return
     */
    public static SearchResponse doDateAndTermsToQuery(Client client, String dataField, String startTime, String endTime, String termKey, List<String> termsValue, int from, int size) {

        Search search = new Search();
        search.setDateField(dataField);
        search.setStartDate(startTime);
        search.setEndDate(endTime);

        search.setTermKey(termKey);
        search.setTermValues(termsValue);
        search.setSize(size);
        search.setFrom(from);

        return ElasticSearchUtil.getMangFieldAggsQuery(client,search,false);



    }


    /**
     * 通过时间范围  车牌和车辆种类 来获取车辆是否经过 或者经过次数
     * @param client
     * @param search
     * @return
     *
     * 逻辑 一定时间范围内  传入一定数据量的车牌和号牌种类 获取这段时间内是否有传入的车牌经过 经过了多少次
     *
     */
    public static SearchResponse findPassNumByHphmAndHpzl(Client client, Search search) {
        //创建bool数据
        BoolQueryBuilder resultBool = QueryBuilders.boolQuery();

        //添加精确条件
        resultBool.must(QueryBuilders
                .rangeQuery(search.getDateField())
                .from(search.getStartDate())
                .to(search.getEndDate()));



        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //创建should  添加匹配条件
        BoolQueryBuilder bool = QueryBuilders.boolQuery();
        bool.must(QueryBuilders
                .termsQuery(search.getTermKey(),"浙EZP382"));
        bool.must(QueryBuilders
                .termsQuery("hpzl","02"));
        boolQueryBuilder.should(bool);


        BoolQueryBuilder bool2 = QueryBuilders.boolQuery();
        bool2.must(QueryBuilders
                .termsQuery(search.getTermKey(),"浙EGG687"));
        bool2.must(QueryBuilders
                .termsQuery("hpzl","02"));
        boolQueryBuilder.should(bool2);

        resultBool.must(boolQueryBuilder);
        //创建循环条件
        System.out.println();



        return null;
    }


    public static  void insert(Client client) {
        IndexRequestBuilder builder = new IndexRequestBuilder(client);
        builder.setSource();
        client.prepareBulk().add(builder);
    }

}
