package com.xuxy.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class NioServer {


    public static void main(String[] args) throws IOException {
        new NioServer().start();
    }

    public void start() throws IOException {

        /**
         * 打开selector
         */
        Selector selector = Selector.open();

        /**
         * 打开selectChannel
         */
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        /**
         * 绑定端口
         */
        serverSocketChannel.bind(new InetSocketAddress(8001));
        /**
         * 开启非阻塞工作模式
         */
        serverSocketChannel.configureBlocking(false);
        /**
         * 注册选择其
         */
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务器启动成功>>>>>>>>>>>");

        while (true) {
            /**
             * 轮询选择器是否接收到请求
             */
            int readyKeys = selector.select();
            if(readyKeys == 0) {
                /**
                 * 未监听到消息
                 */
                continue;
            }
            /**
             * 监听到消息  判断是可读还是工作
             *
             */
            /**
             * 获取可用的channel集合
             */
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator iterator =  selectionKeys.iterator();
            while (iterator.hasNext()) {
                /**
                 * 获取selectionKey实例
                 */
                SelectionKey selectionKey = (SelectionKey) iterator.next();
                /**
                 * 移除当前的key
                 */
                iterator.remove();
                /**
                 * 根据就绪状态 调用对应的业务逻辑
                 */

                /**
                 * 如果是接入事件
                 */
                if(selectionKey.isAcceptable()) {
                    System.out.println();
                    acceptHandler(serverSocketChannel,selector);
                }
                /**
                 * 如果是 可读事件
                 */
                if(selectionKey.isReadable()) {
                    readHandler(selectionKey,selector);
                }
            }


        }

    }

    private void acceptHandler(ServerSocketChannel serverSocketChannel,Selector selector) throws IOException {

        /**
         * 接收接入事件
         */
        SocketChannel socketChannel = serverSocketChannel.accept();
        /**
         * 设置非阻塞模式
         */
        socketChannel.configureBlocking(false);
        /**
         * 将channel注册到selector上 监听可读事件
         */
        socketChannel.register(selector,SelectionKey.OP_READ);
        /**
         * 回去客户端消息 广播
         */
        socketChannel.write(Charset.forName("UTF-8").encode("你与聊天室以建立连接"));
    }

    private void readHandler(SelectionKey selectionKey,Selector selector) throws IOException {
        /**
         * 从selectionKey中获取channel
         */
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        /**
         * 创建buffer
         */
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        /**
         * 循环向客户端请求消息
         */
        String request = ">> ";
        while (socketChannel.read(byteBuffer) > 0) {
            /**
             * 改为读模式
             */
            byteBuffer.flip();
            request += Charset.forName("utf-8").decode(byteBuffer);
        }


        /**
         * 再次将channel注册到selector上 监听其他可读事件
         */
        socketChannel.register(selector,SelectionKey.OP_READ);

        /**
         * 通知所有客户端
         */
        //广播
        System.out.println("开始广播>>>>>>>>>>>>");
        broadCast(selector,socketChannel,request);

    }


    private void  broadCast(Selector selector,SocketChannel sourceChannel,String request) {

        Set<SelectionKey> selectionKeys = selector.keys();

        selectionKeys.forEach(selectionKey->{
            Channel targetChannel = selectionKey.channel();

            if(targetChannel instanceof SocketChannel && targetChannel != sourceChannel) {
                try {
                    ((SocketChannel) targetChannel).write(Charset.forName("utf-8").encode(request));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }
}
