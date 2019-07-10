package com.xuxy.entities;

/**
 * Created by xuxy2 on 2018/4/28.
 */
public enum Color {


    BLUE("蓝色",1);
    private String name ;
    private int index;
    private Color(String name,int index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
