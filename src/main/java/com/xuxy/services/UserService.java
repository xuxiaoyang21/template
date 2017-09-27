package com.xuxy.services;

import com.xuxy.entities.User;

import java.util.List;

/**
 * Created by Intellij IDEA
 *
 * @author:xuxiaoyang
 * @Date: 2017/9/27  15:20
 * @Description:
 */
public interface UserService {

    void insert(User user);

    List<User> findByPager();
}
