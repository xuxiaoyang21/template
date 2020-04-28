package com.starry.sky.demo.queue;

import com.starry.sky.entities.User;

import java.util.concurrent.BlockingQueue;

/**
 * Created with IDEA.
 * Copyright(c) SUPCON 2003-2020. 浙江浙大中控信息技术有限公司
 * author:xuxiaoyang.
 * date:2019/1/15.
 * time:14:44.
 * Description: 消费者模型
 */
public class Comsumer implements Runnable {

    private BlockingQueue<User> queue; //阻塞对列

    public Comsumer (BlockingQueue<User> queue) {
        this.queue = queue;
    }


    @Override
    public void run() {
        //开始执行消费逻辑

        //开始的线程id打印：
        System.out.println("start comsumer is : " + Thread.currentThread().getId());
        try {
            while (true) { //开始消费

                User user = queue.take();//弹出数据
                if(user!=null) {
                    System.out.println("name:"+user.getName());

                    Thread.sleep(1000); //线程休眠一秒
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
