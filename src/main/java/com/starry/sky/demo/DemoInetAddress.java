package com.starry.sky.demo;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 互联网协议ip
 * @author 徐晓阳
 * @创建日期（ 2019-12-26 15:21 ）
 * @description
 */
public class DemoInetAddress {

    public static void main(String[] args) {
        try {
            InetAddress inetAddress = InetAddress.getByName("www.baidu.com");
//            InetAddress inetAddress = InetAddress.getByAddress("112.80.248.75".getBytes());

            System.out.println("address: "+inetAddress.getAddress());
            System.out.println("hostAddress: "+inetAddress.getHostAddress());
            System.out.println("hostName: "+inetAddress.getHostName());
            System.out.println(InetAddress.getLocalHost().getHostName());
            System.out.println(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
