package com.xuxy.demo;


import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.xuxy.entities.EsResult;
import com.xuxy.utils.ExcelExportRepair;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 大江东 es数据解析并导出
 */
public class DaJiangDongExcel {


    public static void main(String[] args) throws FileNotFoundException {

        JsonParser json = new JsonParser();

        JsonObject jsonObject = (JsonObject) json.parse(new FileReader("D:\\json.json"));


        System.out.printf("");
        //解析聚合层
        JsonObject aggs = jsonObject.get("aggregations").getAsJsonObject();

        JsonArray buckets = aggs.get("count-value").getAsJsonObject().get("buckets").getAsJsonArray();

        //循环数据 开始组装数据
        List<EsResult> list = new ArrayList<>();

        for(int i = 0; i < buckets.size() ; i++) {

            JsonObject object = (JsonObject) buckets.get(i);
            //获取时间 并解析
            long date =  object.get("key").getAsLong(); //获取时间
            SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(date);
            String dateStr = sm.format(calendar.getTime());

            //时间 封装
           // esResult.setJgsj(dateStr);

            //获取地点编号 集合数据
            JsonArray kkids =  object.get("cada").getAsJsonObject().get("buckets").getAsJsonArray();

            if(kkids != null && kkids.size()>0) {
                for(JsonElement object1 : kkids)  {
                    JsonObject obj2 = (JsonObject) object1;
                    //获取地点编号
                    String kkid = obj2.get("key").getAsString();//获取 卡口编号

                   // esResult.setKkid(kkid);//设置卡口编号

                    //获取方向编号
                    JsonArray fxbhs = obj2.get("cada").getAsJsonObject().get("buckets").getAsJsonArray();

                    if(fxbhs !=null && fxbhs.size()>0) {
                        for(JsonElement object3: fxbhs) {
                            JsonObject obj3 = (JsonObject) object3;

                            String fxbh = obj3.get("key").getAsString();//得到方向编号
                          //  esResult.setFxbh(fxbh);

                            JsonArray hpzls = obj3.get("cada").getAsJsonObject().get("buckets").getAsJsonArray();

                            if(fxbhs !=null && fxbhs.size()>0) {
                                for (JsonElement object4 : hpzls) {
                                    JsonObject obj4 = (JsonObject) object4;

                                    String hpzl = obj4.get("key").getAsString();//得到号牌种类


                                    long count = obj4.get("doc_count").getAsLong();
                                    EsResult esResult = new EsResult();
                                    if("01".equals(fxbh)) {
                                        esResult.setFxbh("东向西"+fxbh);
                                    } else if("02".equals(fxbh)) {
                                        esResult.setFxbh("西向东"+fxbh);
                                    } else if("03".equals(fxbh)) {
                                        esResult.setFxbh("南向北"+fxbh);
                                    } else if("04".equals(fxbh)) {
                                        esResult.setFxbh("北向南"+fxbh);
                                    }  else  {
                                        esResult.setFxbh("未定义方向"+fxbh);
                                    }
//                                    esResult.setFxbh(fxbh);
                                    esResult.setJgsj(dateStr);
                                    esResult.setKkid(kkid);
                                    if("01".equals(hpzl)){
                                        esResult.setHpzl("大型车辆"+hpzl);
                                    } else if("02".equals(hpzl)) {
                                        esResult.setHpzl("小型车辆"+hpzl);
                                    } else {
                                        esResult.setHpzl("未定义车辆类型"+hpzl);
                                    }


                                    esResult.setCount(count);

                                    list.add(esResult);


                                }
                            } else {
                                EsResult esResult = new EsResult();
                                esResult.setFxbh(fxbh);
                                esResult.setJgsj(dateStr);
                                esResult.setKkid(kkid);
                                list.add(esResult);
                            }

                        }
                    } else {

                        EsResult esResult = new EsResult();
                        esResult.setJgsj(dateStr);
                        esResult.setKkid(kkid);
                        list.add(esResult);
                    }

                }

            } else {

                EsResult esResult = new EsResult();
                esResult.setJgsj(dateStr);

                list.add(esResult);
            }
        }

        System.out.printf("");
        OutputStream out = new FileOutputStream("D:\\测试.xls");



        ExcelExportRepair es = new ExcelExportRepair();

        es.exportExcel("导出",list,out);








    }

}
