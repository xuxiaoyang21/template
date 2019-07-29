package com.xuxy.demo.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * 接收服务器响应
 */
public class NioHandler implements Runnable {


    private Selector selector;


    public NioHandler(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void run() {

        try {

            for (; ; ) {
                /**
                 * 轮询选择器是否接收到请求
                 */
                int readyKeys = selector.select();
                if (readyKeys == 0) {
                    /**
                     * 未监听到消息
                     */
                    continue;
                }
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator iterator = selectionKeys.iterator();
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
                     * 如果是 可读事件
                     */
                    if (selectionKey.isReadable()) {
                        readHandler(selectionKey, selector);
                    }
                    System.out.println(":::::::::");
                }
            }
        } catch (Exception e) {
            System.out.println("报错了》》》》》》");
        }

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
        String response = "";
        while (socketChannel.read(byteBuffer) > 0) {
            /**
             * 改为读模式
             */
            byteBuffer.flip();
            response += Charset.forName("utf-8").decode(byteBuffer);
        }
        /**
         * 再次将channel注册到selector上 监听其他可读事件
         */
        socketChannel.register(selector,SelectionKey.OP_READ);
        System.out.println(response);

    }
}
