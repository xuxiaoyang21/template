package com.xuxy.demo.httpClient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 利用HttpClient进行post请求的工具类
 */
public class HttpClientUtil {

    //获取hls请求路径
    public static final  String SELECT_HLS_PATH = "/api/mss/v1/hls/{indexCode}";

    public static final String SELECT_CARMES_LIST_PATH = "/api/common/v1/remoteCameraInfoRestService/findCameraInfoPage";


    //获取SSL实例
    class  SSLClient extends DefaultHttpClient {
        public SSLClient() throws Exception {
            super();
            SSLContext ctx = SSLContext.getInstance("TLS");
            X509TrustManager tm = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }
                @Override
                public void checkServerTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            ctx.init(null, new TrustManager[]{tm}, null);
            SSLSocketFactory ssf = new SSLSocketFactory(ctx,SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            ClientConnectionManager ccm = this.getConnectionManager();
            SchemeRegistry sr = ccm.getSchemeRegistry();
            sr.register(new Scheme("https", 443, ssf));
        }
    }

    /**
     * 使用post 方法获取数据结果
     * @param url
     * @param param
     * @return
     */
    public String doPost(String url, JSONObject param){
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try{
            httpClient = new SSLClient();
            httpPost = new HttpPost(url);
            httpPost.setHeader("Content-Type", "application/json");
            //设置参数
            if (param != null) {
                StringEntity se = new StringEntity(param.toString(), "utf-8");
                httpPost.setEntity(se); // post方法中，加入json数据
                httpPost.setHeader("Content-Type", "application/json");
            }

            HttpResponse response = httpClient.execute(httpPost);
            if(response != null){
                HttpEntity resEntity = response.getEntity();
                if(resEntity != null){
                    result = EntityUtils.toString(resEntity,"utf-8");
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }



    //获取HLS数据流转换
    public Map<String,String> getHLSByCode (String url, String code) {
        Map<String,String> map = new HashMap<>();
        String result = "";
        map.put("status","error");
        if(url!=null && url.contains("https")) {
            if(!"".equals(code)) {
                String bodys ="{ \"appKey\": \"20321868\"," +
                        " \"appSecret\": \"jL3U7wgTFZDE3TCgLgQF\", " +
                        "\"contentType\": \"application/x-www-form-urlencoded;charset=UTF-8\"," +
                        " \"headers\": {}," +
                        "\"httpMethod\": \"GET\", " +
                        "\"mock\": false, " +
                        "\"parameter\": { \"indexCode\": \""+code+"\" }," +
                        "\"indexCode\": \"330603690006031660\"," +
                        " \"path\": \"/api/mss/v1/hls/{indexCode}\", " +
                        "\"query\": {} }";
                result = doPost(url, JSON.parseObject(bodys));
                //结果转换为对象
                JSONObject jsonObject = JSON.parseObject(result);
                if(jsonObject !=null) {
                    if(jsonObject.get("code") != null && (int)jsonObject.get("code") == 0) {
                        //返回成功
                        map.put("status","success");
                        map.put("result", (String) jsonObject.get("data"));
                    } else {
                        map.put("result", (String) jsonObject.get("msg"));
                    }
                }


            } else {
                System.out.println("监控点编号不能太为空！！！");
                result = "监控点编号不能太为空！！！";
                map.put("result",result);
            }
        } else {
            System.out.println("获取视频HLS url格式错误！");
            result = "获取视频HLS url格式错误！";
            map.put("result",result);
        }

        return map;
    }

    /**
     * 获取监控点总数
     * @return
     */
    public Integer getCameraListTotal(String url,String code) {
        //查询的数据体
        String bodys ="{ \"appKey\": \"20321868\"," +
                " \"appSecret\": \"jL3U7wgTFZDE3TCgLgQF\", " +
                "\"contentType\": \"application/x-www-form-urlencoded;charset=UTF-8\"," +
                " \"headers\": {}," +
                "\"httpMethod\": \"GET\", " +
                "\"mock\": false, " +
                "\"parameter\": {}," +
                " \"path\": \"/api/common/v1/remoteCameraInfoRestService/findCameraInfoPage\", " +
                "\"query\": { start: \"0\", size: \"1\", order: \"desc\", orderby: \"createTime\"} }";

        String result = doPost(url,JSON.parseObject(bodys));
        JSONObject jsonObject = JSON.parseObject(result);
        if(jsonObject.get("code") != null && jsonObject.get("code").equals("200")) {
            JSONObject json = (JSONObject) jsonObject.get("page");
            Integer total = (Integer) json.get("total");

            return  total;
        }
        return null;
    }

    /**
     * 获取监控点列表
     * @return
     */
    public List<String> getCameraList(String url,String start,String size) {


        //查询的数据体
        String bodys ="{ \"appKey\": \"20321868\"," +
                " \"appSecret\": \"jL3U7wgTFZDE3TCgLgQF\", " +
                "\"contentType\": \"application/x-www-form-urlencoded;charset=UTF-8\"," +
                " \"headers\": {}," +
                "\"httpMethod\": \"GET\", " +
                "\"mock\": false, " +
                "\"parameter\": {}," +
                " \"path\": \"/api/common/v1/remoteCameraInfoRestService/findCameraInfoPage\", " +
                "\"query\": { start: \""+start+"\", size: \""+size+"\", order: \"desc\", orderby: \"createTime\"} }";

        String result = doPost(url,JSON.parseObject(bodys));
        JSONObject jsonObject = JSON.parseObject(result);
        if(jsonObject.get("code") != null && jsonObject.get("code").equals("200")) {
            //获取多有的监控点位集合
            List<JSONObject> list = (List<JSONObject>) jsonObject.get("data");
            System.out.println();
            //便利获取所有的监控点位编号
            for (JSONObject object : list) {
                System.out.println(object.get("indexCode"));
            }


        }
        return null;

    }
}
