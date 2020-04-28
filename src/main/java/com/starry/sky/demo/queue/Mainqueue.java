package com.starry.sky.demo.queue;

public class Mainqueue {

    private volatile int inc = 0;

    private synchronized void increase() {
        inc++;
    }



    public static void main(String[] args) throws Exception {


        final Mainqueue test = new Mainqueue();
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<1000;j++)
                        test.increase();
                };
            }.start();

        }

        while(Thread.activeCount()>1)  //保证前面的线程都执行完
            Thread.yield();
        System.out.println(test.inc);





//        BlockingQueue<User> blockingQueue = new LinkedBlockingDeque<>(10);
//        Producer q1 = new Producer(blockingQueue);
//
//        Comsumer c1 = new Comsumer(blockingQueue);
//
//        new Thread(q1).start();
//        new Thread(c1).start();
//
//
//        Thread.sleep(10000);
//        System.out.println("消费完毕");
//        q1.stop();
    }
}
