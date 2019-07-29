package com.xuxy.demo.demoTest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class Demo3 {

    public static void main(String[] args) throws IOException {

//        Socket socket = new Socket("127.0.0.1",8000);
//
//        InputStream inputStream = socket.getInputStream();
//        OutputStream outputStream = socket.getOutputStream();
        //客户端
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",8000));

        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        byteBuffer.put(new Byte("123"));
        //写模式切换到读模式
        byteBuffer.flip();
        //
        byteBuffer.get();
        byteBuffer.reset(); //充值postion位置
        byteBuffer.mark(); //标记位置
        byteBuffer.clear();//清空所有位置

    }
}
