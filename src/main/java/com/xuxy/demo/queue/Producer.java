package com.xuxy.demo.queue;

import com.xuxy.entities.User;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IDEA.
 * Copyright(c) SUPCON 2003-2020. 浙江浙大中控信息技术有限公司
 * author:xuxiaoyang.
 * date:2019/1/15.
 * time:14:38.
 * Description: 生产者模型
 */
public class Producer implements Runnable {


    private volatile boolean isRunning = true; //判断生产者是否关闭


    //创建缓冲区
    private BlockingQueue<User> queue ; //内存缓冲区

    private static AtomicInteger count = new AtomicInteger(); //总数 原子操作

    public Producer (BlockingQueue<User> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        User user = null;

        System.out.println("start producer is " + Thread.currentThread().getId());

       try {
           while (isRunning) {
               Thread.sleep(1000);
               user = new User();
               user.setName("我在生产数据 序号为："+ count.incrementAndGet());
               System.out.println("正在加入对列");
               if(!queue.offer(user,2,TimeUnit.SECONDS)) {
                   System.out.println("加入失败");
               } else {
                   System.out.println("加入成功");
               }
           }

       } catch (Exception e) {
           e.printStackTrace();
           System.out.println(e.getMessage());
       }

    }

    public void stop() {
        isRunning = false;
    }

}
