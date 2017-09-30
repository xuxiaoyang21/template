package com.xuxy.entities;

import java.io.Serializable;

/**
 * Created by Intellij IDEA
 *
 * @author:xuxiaoyang
 * @Date: 2017/9/27  15:01
 * @Description:  添加用户类 测试
 */

public class User implements Serializable {

    public static final String STATE_UNKNOW = "0";
    public static final String STATE_NORMAL = "1";
    public static final String STATE_DISABLE = "2";

    private Integer id;
    private String name;
    private String address;
    private String state;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {

        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
