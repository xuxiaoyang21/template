package com.xuxy.demo.httpClient;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.*;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * 发屏软件接口调用方法
 */

public class PostClient {


    public static void mainss(String[] args) {

//        HttpPost httpPost = new HttpPost("http://33.173.56.78/artemis-web/debug");
//        HttpClient httpClient = HttpClients.createDefault();

        String bodys ="{ \"appKey\": \"20321868\"," +
                " \"appSecret\": \"jL3U7wgTFZDE3TCgLgQF\", " +
                "\"contentType\": \"application/x-www-form-urlencoded;charset=UTF-8\"," +
                " \"headers\": {}," +
                "\"httpMethod\": \"GET\", " +
                "\"mock\": false, " +
                "\"parameter\": { \"indexCode\": \"330603690006031660\" }," +
                "\"indexCode\": \"330603690006031660\"," +
                " \"path\": \"/api/mss/v1/hls/{indexCode}\", " +
                "\"query\": {} }";

        HttpClientUtil httpClientUtil = new HttpClientUtil();
        String result = httpClientUtil.doPost("https://33.173.56.78/artemis-web/debug", JSON.parseObject(bodys));
        System.out.println(result);
    }



    public static void main(String[] args) {

        //oracle 数据库连接
        Connection conn=null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");//找到oracle驱动器所在的类
            String url = "jdbc:oracle:thin:@33.173.56.253:1521:orcl"; //URL地址
            String username = "itmb_kq";
            String password = "itmb";
            conn = DriverManager.getConnection(url, username, password);
        }catch (Exception e) {
            e.printStackTrace();
        }


        String sql="insert into HO_USER_LOGIN_LOG(id,USERNAME,login_status,LOGIN_TIME) values(?,?,?,?)"; //?为參数占位符

        PreparedStatement pstmt=null; //通常利用PreparedStatement进行操作，性能得到优化
        try{

            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, "10000");
            pstmt.setString(2,"ceshi");
            pstmt.setString(3, "0");
            pstmt.setDate(4,new java.sql.Date(new Date().getTime()));
            pstmt.executeUpdate();//运行增删改操作

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {

            }
        }


        String url = "https://33.173.56.78/artemis-web/debug";
        Integer s= new HttpClientUtil().getCameraListTotal(url,"154202365328952260");
        System.out.println(s);

        if(s != null) {
            new HttpClientUtil().getCameraList(url, "0",s+"");
        }



    }



    public static CloseableHttpClient createSSLClientDefault(){
        try {
            SSLContext sslContexts = SSLContext.getInstance("TLS");
            SSLContext sslContext=new SSLContextBuilder().loadTrustMaterial(
                    null,new TrustStrategy() {
                        //信任所有
                        public boolean isTrusted(X509Certificate[] chain, String authType)
                                throws CertificateException {
                            return true;
                        }
                    }).build();
            SSLConnectionSocketFactory sslsf=new SSLConnectionSocketFactory(sslContext);
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return HttpClients.createDefault();
    }

}
