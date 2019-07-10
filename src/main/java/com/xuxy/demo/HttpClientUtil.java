package com.xuxy.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 利用HttpClient进行post请求的工具类
 */
public class HttpClientUtil {
    //静态常量

    //密钥
    public static final String APPKEY = "27890152";
    public static final String APPSECRET = "CVZzWPb0dm466OxkFbjX";

    //分页获取所有的视频节点信息
    public static final String GET_PAGE_CAMERA_PATH = "/api/common/v1/remoteCameraInfoRestService/findCameraInfoPage";
    //获取HLS 流路径
    public static final String GET_HLS_PATH = "/api/mss/v1/hls/{indexCode}";
    //内容类型
    public static final String CONTENT_TYPE = "application/x-www-form-urlencoded;charset=UTF-8";

    //获取rstp 流路径
    public static final String GET_RSTP_PATH = "/api/vms/v1/rtsp/basic/{indexCode}";




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

   /* private HttpRequestRetryHandler myRetryHandler = new HttpRequestRetryHandler() {
        @Override
        public boolean retryRequest(IOException exception,
                                    int executionCount, HttpContext context) {
            return false;
        }};
*/
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
//            ((SSLClient) httpClient).setHttpRequestRetryHandler(myRetryHandler);
            RequestConfig requestConfig =RequestConfig
                    .custom()
                    .setConnectionRequestTimeout(1000)//从数据库连接池获取连接超时时间设置
                    .setSocketTimeout(2000) //socket连接建立成功, 数据传输响应超时
                    .setConnectTimeout(2000)//建立socket链接超时时间
                    .build();
            httpPost = new HttpPost(url);
            httpPost.setConfig(requestConfig);
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
            System.out.println(ex.getMessage());
        }
        return result;
    }

    public static void main(String[] args) {
        //取流
        //Map<String,String> data =  new HttpClientUtil().getHLSByCode("https://33.173.56.78/artemis-web/debug","154202395611369660");
        new HttpClientUtil().getCameras("https://33.173.56.78/artemis-web/debug",0,10);
        System.out.println(1);
    }

    //获取HLS数据流转换
    public Map<String,String> getHLSByCode (String url, String code) {
        Map<String,String> map = new HashMap<>();
        String result = "";
        map.put("status","error");
        if(url!=null && url.contains("https")) {
            if(!"".equals(code)) {
                String bodys ="{ \"appKey\": \""+APPKEY+"\"," +
                        " \"appSecret\": \""+APPSECRET+"\", " +
                        "\"contentType\": \""+CONTENT_TYPE+"\"," +
                        " \"headers\": {}," +
                        "\"httpMethod\": \"GET\", " +
                        "\"mock\": false, " +
                        "\"parameter\": { \"indexCode\": \""+code+"\" }," +
                        //"\"indexCode\": \"154202395611369660\"," +
                        " \"path\": \""+GET_HLS_PATH+"\", " +
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
                } else {
                    map.put("result","数据获取失败！可能视频服务重启");
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


    //获取rtsp数据流转换
    public Map<String,String> getRTSPByCode (String url, String code) {
        Map<String,String> map = new HashMap<>();
        String result = "";
        map.put("status","error");
        if(url!=null && url.contains("https")) {
            if(!"".equals(code)) {
                String bodys ="{ \"appKey\": \""+APPKEY+"\"," +
                        " \"appSecret\": \""+APPSECRET+"\", " +
                        "\"contentType\": \""+CONTENT_TYPE+"\"," +
                        " \"headers\": {}," +
                        "\"httpMethod\": \"GET\", " +
                        "\"mock\": false, " +
                        "\"parameter\": { \"indexCode\": \""+code+"\" }," +
                        //"\"indexCode\": \"154202395611369660\"," +
                        " \"path\": \""+GET_RSTP_PATH+"\", " +
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
                } else {
                    map.put("result","数据获取失败！可能视频服务重启");
                }
            } else {
                System.out.println("监控点编号不能太为空！！！");
                result = "监控点编号不能太为空！！！";
                map.put("result",result);
            }
        } else {
            System.out.println("获取视频RSTP url格式错误！");
            result = "获取视频RSTP url格式错误！";
            map.put("result",result);
        }

        return map;
    }




    /**
     * 从海康平台获取视频点位 信息列表
     * @param url
     * @param start
     * @param size
     * @return
     * 配合 /coreDeviceCctv/getCameraList 方法配合插入主表和视频表数据
     *
     */
    public Map<String,Object> getCameras (String url, int start, int size) {
        Map<String,Object> map = new HashMap<>();
        String result = "";
        map.put("status","error");
        if(url!=null && url.contains("https")) {
            String bodys ="{ \"appKey\": \""+APPKEY+"\"," +
                    " \"appSecret\": \""+APPSECRET+"\", " +
                    "\"contentType\": \""+CONTENT_TYPE+"\"," +
                    " \"headers\": {}," +
                    "\"httpMethod\": \"GET\", " +
                    "\"mock\": false, " +
                    "\"parameter\": {}," +
                    " \"path\": \""+GET_PAGE_CAMERA_PATH+"\", " +
                    "\"query\": {\"start\": \""+ start +"\", \"size\": \""+ size +"\", \"order\": \"desc\", \"orderby\": \"createTime\"} }";
            result = doPost(url, JSON.parseObject(bodys));
            //结果转换为对象
            JSONObject jsonObject = JSON.parseObject(result);
            if(jsonObject !=null) {
                if(jsonObject.get("code") != null && "200".equals(jsonObject.get("code"))) {
                    try{
                        JSONObject page = (JSONObject) jsonObject.get("page");
                        map.put("total",page.get("total"));
                        JSONArray data = (JSONArray) jsonObject.get("data");
                        List<Map<String,String>> cameras = new ArrayList<Map<String,String>>();
                        for( int i= 0 , len = data.size(); i < len ; i++){
                            JSONObject trans = (JSONObject) data.get(i);
                            Map<String,String> childMap = new HashMap<>();
                            //监控点获取流唯一编号
                            childMap.put("indexCode",(String)(trans.get("indexCode")));
                            //监控点id
                            childMap.put("cameraId",(String)(trans.get("cameraId")));
                            //监控点名称
                            childMap.put("name", (String) trans.get("name"));
                            //纬度
                            childMap.put("latitude", (String) trans.get("latitude"));
                            //经度
                            childMap.put("longitude", (String) trans.get("longitude"));
                            cameras.add(childMap);
                        }
                        map.put("data",cameras);
                    }catch (Exception e){

                    }
                    //返回成功
                    map.put("status","success");
                } else {
                    map.put("data", jsonObject.get("msg"));
                }
            }
        } else {
            System.out.println("获取视频HLS url格式错误！");
            result = "获取视频HLS url格式错误！";
            map.put("result",result);
        }

        return map;
    }



}
