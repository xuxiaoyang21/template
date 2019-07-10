package com.xuxy.demo;

import java.io.*;

/**
 * Created by xuxy2 on 2018/4/19.
 */
public class Demo1Thread implements  Runnable {
    @Override
    public void run() {
        FileInputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            System.out.println("线程2");
            inputStream = new FileInputStream(new File("C:\\Users\\xuxy2\\Pictures\\Saved Pictures\\97412_large.png"));
            outputStream = new FileOutputStream(new File("D:\\2.png"));
            byte[] buffer = new byte[1024];
            int len = 0 ;
            try {
                while ((len = inputStream.read(buffer,0,buffer.length) ) != -1) {
                    outputStream.write(buffer,0,len);
                }
                inputStream.close();
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(inputStream !=null) {
                try {
                    inputStream.close();
                    if(outputStream !=null) {
                        outputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {

                }
            }

        }
    }
}
