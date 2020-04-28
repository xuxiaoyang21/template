package com.starry.sky.demo.nio;

import java.io.IOException;

public class CClient {

    public static void main(String[] args) throws IOException {
        new NioClient().start("Cclient:");
    }
}
