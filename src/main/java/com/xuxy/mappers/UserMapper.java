package com.xuxy.mappers;

import com.xuxy.entities.User;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Intellij IDEA
 *
 * @author:xuxiaoyang
 * @Date: 2017/9/27  15:23
 * @Description:
 */
public interface UserMapper {


    void insert(User user);

    List<User> findByPager();

    List selectByPager(HashMap params);

    Long countByPager(HashMap params);

    void update(User user);

    void deleteById(User user);

    User findByName(String name);
}
