package com.xuxy.services.impl;

import com.xuxy.entities.User;
import com.xuxy.mappers.UserMapper;
import com.xuxy.services.UserService;
import com.xuxy.utils.Pageable;
import com.xuxy.utils.impl.NewPagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Intellij IDEA
 *
 * @author:xuxiaoyang
 * @Date: 2017/9/27  15:20
 * @Description: 用户 业务逻辑类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }

    @Override
    public List<User> findByPager() {
        return userMapper.findByPager();
    }

    @Override
    public Pageable<User> find(NewPagination<User> pager, User condition) {

        HashMap params = new HashMap();
        params.put("offset", Integer.valueOf(pager.getOffset()));
        params.put("pageSize", Integer.valueOf(pager.getPageSize()));
        params.put("offsetEnd", Integer.valueOf(pager.getOffsetEnd()));
        params.put("condition", condition);

        List result = userMapper.selectByPager(params);
        pager.addAll(result);
        long total = this.calculateTotal(pager, result);
        if(total < 0L) {
            total = (userMapper.countByPager(params)).longValue();
        }

        pager.setTotal(total);
        return pager;
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void deleteById(User user) {
        userMapper.deleteById(user);
    }

    protected <X> long calculateTotal(Pageable<X> pager, List<X> result) {
        return pager.hasPrevPage()?(CollectionUtils.isEmpty(result)?-1L:(result.size() == pager.getPageSize()?-1L:(long)((pager.getPageNo() - 1) * pager.getPageSize() + result.size()))):(result.size() < pager.getPageSize()?(long)result.size():-1L);
    }
}
