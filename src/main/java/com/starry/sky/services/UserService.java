package com.starry.sky.services;

import com.starry.sky.entities.User;
import com.starry.sky.utils.Pageable;
import com.starry.sky.utils.impl.NewPagination;

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

    Pageable<User> find(NewPagination<User> pager, User user);

    void update(User user);

    void   deleteById(User user);

    List<User> findAllUser();
}
