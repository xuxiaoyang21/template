package com.starry.sky.demo.jni;

class JniDemo {

    static {
        System.loadLibrary("dddd");
    }
    public static native void sayHello();

    public static void main(String[] args) {
        new JniDemo().sayHello();
    }
}
