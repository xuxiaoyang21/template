package com.starry.sky.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

public class NioClient {


    public static void main(String[] args) throws IOException {
        new NioClient().start("");
    }
    public void start(String name) throws IOException {

        /**
         * 连接服务端
         */
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",8001));
        /**
         * 接收服务器相应
         */
        Selector selector = Selector.open();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        new Thread(new NioHandler(selector)).start();


        /**
         * 向服务端发送数据
         */
        System.out.println("请输入数据：");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String request = scanner.nextLine();
            if(request != null) {
                socketChannel.write(Charset.forName("utf-8").encode(name+request));
            }
        }


    }
}
