package com.xuxy.demo.jdk.emnu;
/**
 * 枚举
 *@author 徐晓阳
 *@创建日期（ 2019-11-04 16:45 ）
 *@description
 */
public enum  TypeEnum {

    SUCCESS(1,2);
    int index;

    int value;

    TypeEnum(int index,int value) {
        this.index = index;
        this.value = value;
    }

    public static void getValues() {
        for(TypeEnum i : TypeEnum.values() ) {
            System.out.println(i.index);
            System.out.println(i.value);
            System.out.println(TypeEnum.SUCCESS);
            System.out.println(TypeEnum.SUCCESS.value);
        }
    }

    public static void main(String[] args) {
        TypeEnum.getValues();
    }
}
