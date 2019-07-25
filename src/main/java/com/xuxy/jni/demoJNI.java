
//package com.xuxy.jni;


public class demoJNI {
    static {
        System.loadLibrary("demoJNI");
    }

    public static void main(String[] args) {
        System.out.println("hello world");
        new demoJNI().sayHello();
    }

    public static native void sayHello();
}
