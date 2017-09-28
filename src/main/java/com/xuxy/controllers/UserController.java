package com.xuxy.controllers;

import com.xuxy.entities.User;
import com.xuxy.services.UserService;
import com.xuxy.utils.Pageable;
import com.xuxy.utils.impl.NewPagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Intellij IDEA
 *
 * @author:xuxiaoyang
 * @Date: 2017/9/27  15:17
 * @Description:  用户类控制器
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public String  add(User user){
        System.out.println("输出："+user.toString());
        userService.insert(user);

        return "success";
    }

    @RequestMapping(value = "/lists",method = RequestMethod.POST)
    @ResponseBody
    public List<User> lists(ModelMap modelMap){

        List<User> list = userService.findByPager();

        return  list;
    }

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public Pageable<User> list(NewPagination<User> pager,User user){
        //查询数据列表
        return this.userService.find(pager,user);
    }



}
