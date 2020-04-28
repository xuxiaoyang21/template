package com.starry.sky.tcpIp.cast;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 广播服务端
 * @author 徐晓阳
 * @创建日期（ 2020-04-20 11:14 ）
 * @description
 */
public class BroadcastServer {

    public static void main(String[] args) {
        startCastServer();
    }

    private static void startCastServer() {
        int port = 9999;
        DatagramSocket socket = null;
        DatagramPacket packet = null;
        byte[] buf = new byte[1024];

        try {
            socket = new DatagramSocket(port);
            packet = new DatagramPacket(buf,buf.length);
            System.out.println("监听广播端口打开：");
            socket.receive(packet);
            socket.close();

            StringBuffer buffer = new StringBuffer();
            for(int i = 0; i<1024;i++) {
                if(buf[i] == 0) {
                    break;
                }
                buffer.append((char)buf[i]);
            }
            System.out.println("收到广播消息："+buffer.toString());



        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("失败错误");
        }

    }


}
