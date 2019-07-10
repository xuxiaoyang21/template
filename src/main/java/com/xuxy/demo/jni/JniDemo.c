
#include <jni.h>
#include <JniDemo.h>
#include <stdio.h>

JNIEXPORT void JNICALL Java_JniDemo_sayHello
  (JNIEnv *, jclass){

    printf("Hello World!\n");
    return;
  }