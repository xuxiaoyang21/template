package com.xuxy.controllers.jodd;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

/**
 * Created by Intellij IDEA
 *
 * @author:xuxiaoyang
 * @Date: 2017/9/25  14:34
 * @Description: jodd-http测试
 */

public class JoddHttpTest {


    //必须依赖jar jodd-http
    public static void main(String[] args){

        //创建httpget请求
        HttpRequest httpRequest = HttpRequest.get("https://jodd.org");
        HttpResponse httpResponse = httpRequest.send();//发送get请求
        System.out.println("响应的状态码："+httpResponse.statusCode());//返回响应的状态码 输出 200
        System.out.println("响应的状态短语："+httpResponse.statusPhrase());//输出 ok



        //另一种连接的方式  支持链式调用
        HttpRequest request = new HttpRequest();
        request
                .method("GET")//请求的方式
                .protocol("http")//协议
                .host("srv")//主机 或者说ip
                .port(8080)//端口号
                .path("/api/jsonws/user/get-user-by-id");//资源路径
        HttpResponse response = request.send();//发送请求

        System.out.println(response.statusPhrase());


    }
}
