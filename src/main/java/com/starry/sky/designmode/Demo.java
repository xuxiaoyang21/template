package com.starry.sky.designmode;


import java.io.*;

public class Demo {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream(new File(""));
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        bufferedInputStream.read();
    }
}
