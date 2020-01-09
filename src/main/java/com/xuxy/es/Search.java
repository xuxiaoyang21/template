package com.xuxy.es;

//import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogram;
import org.elasticsearch.search.sort.SortOrder;

import java.util.List;

/**
 * Created with IDEA.
 * Copyright(c) SUPCON 2003-2020. 浙江浙大中控信息技术有限公司
 * author:xuxiaoyang.
 * date:2018/6/7.
 * time:15:27.
 * Description:c查询搜索条件实体类
 */
public class Search {

    public final static String DEFAULT = "yyyy-MM-dd HH:mm:ss";
    public final static String YEAR= "yyyy";
    public final static String MONTH = "yyyy-MM";
    public final static String DAY = "yyyy-MM-dd";
    public final static String HOUR = "yyyy-MM-dd HH";
    public final static String MINUTE = "yyyy-MM-dd HH:mm";
    public final static String SECOND = "yyyy-MM-dd HH:mm:ss";


    public static final Long MINUTE_COUNT = 60000L;//一分钟的毫秒数
    public static final Long HOUR_COUNT = 3600000L;//一小时的毫秒数
    public static final Long DAY_COUNT = 86400000L;//一分钟的毫秒数
    public static final Long MONTH_COUNT = 2592000000L;//一分钟的毫秒数




    //时间范围
    private String dateField;//执行时间范围的字段

    private String startDate;//开始时间  传入的时间戳

    private String endDate;//结束时间

    private Boolean dataFlag = true;//时间范围查询是否开启


    //单字段对应多值
    private String termKey;//需要精确匹配多值的字段名

    private List<String> termValues;//需要精确匹配的多值

    private Boolean termFlag = false; //是否开启某个字段多值匹配


    //多字段
    private Boolean manyTermFlag = false;//是否开启多个字段精确匹配

    private List<String> termKeyList;//多字段精确匹配

    private List<List<String>> termValueList;//多字段精度匹配



    //单字段聚合
    private Boolean aggsFlag = false;//聚合开启

    private String aggsKey;//需要聚合的key值

    private int aggsSize = 10; //聚合size展示

    //
    private String aggTermsAlias;//聚合分组的别名  随便起 可以不起 会默认

    private int size = 0 ; //设置hist显示 数量

    private int from = 0 ;

    private int minDocCount = 0;//最小显示数值


    //时间分段聚合
    private Boolean hisFlag = false;//时间分片开启

    private String histKey; //时间分片的字段  一般和dateField一致

    private DateHistogram.Interval interval; //时间分片  柱状体间隔时间

    private String format = DEFAULT;//时间分片 格式转换

    private String extendMin;//数据为零是也可以显示  开始时间

    private String extendMax;//数据为零是也可以显示  结束时间

    private Boolean sizeFlag = false; //是否开启总数设置



    private String countKey;//数值范围分割

    private Long countFrom;//数值开始

    private Long countTo;//数据结束

    private Boolean countFlag = false;//数值范围开启

    private Long intervalCount;//数值间隔
    //前缀模糊查询
    private String prefixValue; //前缀

    private String prefixName; //查询字段

    private Boolean prefixFlag = false; //模糊查询是否开启

    private Boolean reverFuzzy = false;//模糊查询反转


    private Boolean topHitsFlag = false;//是否开启聚合后展示源数据

    private int topHitsSize = 1;// 展示源数据条数
    private int topHitFrom = 0; // 起始位置

    private int aggDefaultSize = 500;//聚合显示长度默认为500;


    private Boolean manyTermsFlag = false;

    private List<String> aggsKeyList;

    public int getAggsSize() {
        return aggsSize;
    }

    public void setAggsSize(int aggsSize) {
        this.aggsSize = aggsSize;
    }

    public Boolean getManyTermsFlag() {
        return manyTermsFlag;
    }

    public void setManyTermsFlag(Boolean manyTermsFlag) {
        this.manyTermsFlag = manyTermsFlag;
    }

    public List<String> getAggsKeyList() {
        return aggsKeyList;
    }

    public void setAggsKeyList(List<String> aggsKeyList) {
        this.aggsKeyList = aggsKeyList;
    }

    public Boolean getTopHitsFlag() {
        return topHitsFlag;
    }

    public void setTopHitsFlag(Boolean topHitsFlag) {
        this.topHitsFlag = topHitsFlag;
    }

    public int getTopHitsSize() {
        return topHitsSize;
    }

    public void setTopHitsSize(int topHitsSize) {
        this.topHitsSize = topHitsSize;
    }

    public int getTopHitFrom() {
        return topHitFrom;
    }

    public void setTopHitFrom(int topHitFrom) {
        this.topHitFrom = topHitFrom;
    }

    //去重查询
    private String repetName;//字段

    private Boolean repetFlag = false;//是否开启去重查询

    //是否有筛选条件
    private Boolean conditionFlag = false;

    //筛选条件对应的字段
    private String condition;

    //筛选条件的内容
    private String conditionValue;


    private Boolean isOpenTermToHistogram = false; //是否开启 时间和字段双聚合   与termToHistogram字段联合使用

    private Boolean termToHistogram = true; // true代表 先字段聚合后时间聚合 false 为先时间聚合后字段聚合


    public Boolean getIsOpenTermToHistogram() {
        return isOpenTermToHistogram;
    }

    public void setIsOpenTermToHistogram(Boolean openTermToHistogram) {
        isOpenTermToHistogram = openTermToHistogram;
    }

    public Boolean getTermToHistogram() {
        return termToHistogram;
    }

    public void setTermToHistogram(Boolean termToHistogram) {
        this.termToHistogram = termToHistogram;
    }

    public DateHistogram.Interval getInterval() {
        return interval;
    }

    public void setInterval(DateHistogram.Interval interval) {
        this.interval = interval;
    }

    public void setCountFrom(Long countFrom) {
        this.countFrom = countFrom;
    }

    public int getFrom() {
        return from;
    }

    public Boolean getOpenTermToHistogram() {
        return isOpenTermToHistogram;
    }

    public void setOpenTermToHistogram(Boolean openTermToHistogram) {
        isOpenTermToHistogram = openTermToHistogram;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public Long getCountTo() {
        return countTo;
    }

    public void setCountTo(Long countTo) {
        this.countTo = countTo;
    }

    public Boolean getConditionFlag() {
        return conditionFlag;
    }

    public void setConditionFlag(Boolean conditionFlag) {
        this.conditionFlag = conditionFlag;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.conditionFlag = true;
        this.condition = condition;
    }

    public String getConditionValue() {
        return conditionValue;
    }

    public void setConditionValue(String conditionValue) {
        this.conditionValue = conditionValue;
    }

    private String sortKey;//排序字段

    private SortOrder sortRule;//排序规则

    private Boolean sortFlag = false;//排序开关

    public Boolean getHisFlag() {
        return hisFlag;
    }

    public void setHisFlag(Boolean hisFlag) {
        this.hisFlag = hisFlag;
    }

    public Search() {
        Double random = Math.random();
        int in = (int) (random*10/1);
        this.aggTermsAlias = "count_value"+in; //别名默认


    }


    public void setStartDate(String startDate) {
        this.startDate = startDate;
        this.dataFlag = true;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getAggDefaultSize() {
        return aggDefaultSize;
    }

    public void setAggDefaultSize(int aggDefaultSize) {
        this.aggDefaultSize = aggDefaultSize;
    }

    public Long getIntervalCount() {
        return intervalCount;
    }

    public void setIntervalCount(Long intervalCount) {
        this.intervalCount = intervalCount;
    }

    public String getCountKey() {
        return countKey;
    }

    public void setCountKey(String countKey) {
        this.countKey = countKey;
        this.countFlag = true;
    }

    public Long getCountFrom() {
        return countFrom;
    }

   /* public void setCountFrom(String startDate, String pattern) {
        this.countFrom = SearchUtils.parseTimeMillion(startDate,pattern);
        //将数据min和max数据添加
        this.extendMin = this.countFrom.toString();
    }

    public Long getCountTo() {
        return countTo;
    }

    public void setCountTo(String endDate, String pattern) {
        this.countTo = SearchUtils.parseTimeMillion(endDate,pattern);
        //将数据min和max数据添加
        this.extendMax = this.countTo.toString();
    }*/

    public Boolean getCountFlag() {
        return countFlag;
    }

    public void setCountFlag(Boolean countFlag) {
        this.countFlag = countFlag;
    }

    public String getRepetName() {
        return repetName;
    }

    public void setRepetName(String repetName) {
        this.repetName = repetName;
        this.repetFlag = true;//开启去重查询
    }

    public String getSortKey() {
        return sortKey;
    }

    public void setSortKey(String sortKey) {
        this.sortKey = sortKey;
        this.sortFlag = true;
    }

    public SortOrder getSortRule() {
        return sortRule;
    }

    public void setSortRule(SortOrder sortRule) {
        this.sortRule = sortRule;
    }

    public Boolean getSortFlag() {
        return sortFlag;
    }

    public void setSortFlag(Boolean sortFlag) {
        this.sortFlag = sortFlag;
    }

    public Boolean getRepetFlag() {
        return repetFlag;
    }

    public void setRepetFlag(Boolean repetFlag) {
        this.repetFlag = repetFlag;
    }

    public Boolean getAggsFlag() {
        return aggsFlag;
    }

    public void setAggsFlag(Boolean aggsFlag) {
        this.aggsFlag = aggsFlag;
    }

    public Boolean getReverFuzzy() {
        return reverFuzzy;
    }

    public void setReverFuzzy(Boolean reverFuzzy) {
        this.reverFuzzy = reverFuzzy;
    }

    public String getPrefixValue() {
        return prefixValue;
    }

    public void setPrefixValue(String prefixValue) {
        this.prefixValue = prefixValue;
        this.prefixFlag = true;
    }

    public String getPrefixName() {
        return prefixName;
    }

    public void setPrefixName(String prefixName) {
        this.prefixName = prefixName;
        this.prefixFlag =true;
    }

    public Boolean getPrefixFlag() {
        return prefixFlag;
    }

    public void setPrefixFlag(Boolean prefixFlag) {
        this.prefixFlag = prefixFlag;
    }

    public Boolean getDataFlag() {
        return dataFlag;
    }

    public void setDataFlag(Boolean dataFlag) {
        this.dataFlag = dataFlag;
    }

    public Boolean getTermFlag() {
        return termFlag;
    }

    public void setTermFlag(Boolean termFlag) {
        this.termFlag = termFlag;

    }

    public List<String> getTermKeyList() {
        return termKeyList;
    }

    public void setTermKeyList(List<String> termKeyList) {
        this.termKeyList = termKeyList;
        this.manyTermFlag = true;//开多字段精确值查询
    }

    public List<List<String>> getTermValueList() {
        return termValueList;
    }

    public void setTermValueList(List<List<String>> termValueList) {
        this.termValueList = termValueList;
    }

    public Boolean getManyTermFlag() {
        return manyTermFlag;
    }

    public void setManyTermFlag(Boolean manyTermFlag) {
        this.manyTermFlag = manyTermFlag;
    }

    public Boolean getSizeFlag() {
        return sizeFlag;
    }

    public void setSizeFlag(Boolean sizeFlag) {
        this.sizeFlag = sizeFlag;
    }

//    public DateHistogramInterval getInterval() {
//        return interval;
//    }
//
//    public void setInterval(DateHistogramInterval interval) {
//        this.interval = interval;
//    }

    public String getHistKey() {
        return histKey;
    }

    public void setHistKey(String histKey) {
        this.histKey = histKey;
        this.hisFlag = true;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getExtendMin() {
        return extendMin;
    }

    public void setExtendMin(String extendMin) {
        this.extendMin = extendMin;
    }

    public String getExtendMax() {
        return extendMax;
    }

    public void setExtendMax(String extendMax) {
        this.extendMax = extendMax;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
        this.sizeFlag = true;
    }

    public int getMinDocCount() {
        return minDocCount;
    }

    public void setMinDocCount(int minDocCount) {
        this.minDocCount = minDocCount;
    }

    public String getDateField() {
        return dateField;
    }

    public void setDateField(String dateField) {
        this.dateField = dateField;
    }

    public String getStartDate() {
        return startDate;
    }

  /*  public void setStartDate(String startDate,String pattern)  {

        this.startDate =  SearchUtils.parseUtc(startDate,pattern);
        //将数据min和max数据添加
        this.extendMin = SearchUtils.parseCN(startDate,pattern);
   }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate,String pattern) {

        this.endDate =  SearchUtils.parseUtc(endDate,pattern);
        //将数据min和max数据添加
        this.extendMax = SearchUtils.parseCN(endDate,pattern);
    }*/

    public String getTermKey() {
        return termKey;
    }

    public void setTermKey(String termKey) {
        this.termKey = termKey;
        this.termFlag = true;
    }

    public List<String> getTermValues() {
        return termValues;
    }

    public void setTermValues(List<String> termValues) {
        this.termValues = termValues;
    }

    public String getAggsKey() {
        return aggsKey;
    }

    public void setAggsKey(String aggsKey) {
        this.aggsKey = aggsKey;
        this.aggsFlag = true;//开启聚合
    }

    public String getAggTermsAlias() {
        return aggTermsAlias;
    }

    public void setAggTermsAlias(String aggTermsAlias) {
        this.aggTermsAlias = aggTermsAlias;
    }

    public static void main(String[] args) {
        Double random = Math.random();
        int in = (int) (random*10/1);
    }
}
