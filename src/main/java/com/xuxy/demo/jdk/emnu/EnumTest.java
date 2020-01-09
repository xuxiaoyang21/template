package com.xuxy.demo.jdk.emnu;

public enum EnumTest {

    SUCCESS(1,"ab"),
    ERROR(1,"ab"),
    FAIL(1,"ab");


    private int index;

    private String name;

    EnumTest(int in , String name) {
        this.index = in;
        this.name = name;
    }
}
