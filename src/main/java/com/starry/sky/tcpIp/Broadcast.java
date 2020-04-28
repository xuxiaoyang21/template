package com.starry.sky.tcpIp;

import clojure.lang.IFn;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 广播
 * @author 徐晓阳
 * @创建日期（ 2020-04-20 11:13 ）
 * @description
 */
public class Broadcast {
    public static void main(String[] args) {
        initCastClient();
    }

    private static void initCastClient() {
        //广播地址
        String host = "255.255.255.255";
        int port = 9999;
        String message = "wo lai le guan bo";
        try {
            InetAddress address = InetAddress.getByName(host);
            DatagramSocket socket = new DatagramSocket();
            DatagramPacket packet = new DatagramPacket(message.getBytes(),message.length(),address,port);
            socket.send(packet);
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
