package com.starry.sky.demo.demoTest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Demo4 {

    public static void main(String[] args) throws IOException {

//        ServerSocket serverSocket = new ServerSocket(8000);
//
//        while (true) {
//
//            //接收请求
//            Socket socket = serverSocket.accept();
//            System.out.println(socket.getChannel());
//        }

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.bind(new InetSocketAddress(8000));
//        serverSocketChannel.

        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            if(socketChannel != null) {
                System.out.println(socketChannel);
            } else {
                System.out.println("打印>>>>>>>>>");
            }
        }
    }
}
