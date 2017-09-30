package com.xuxy.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Intellij IDEA
 *
 * @author:xuxiaoyang
 * @Date: 2017/9/26  9:59
 * @Description: 首页 欢迎页
 */

@Controller
public class WelcomeController {

    @RequestMapping("/")
    public String login(){
        return "redirect:menu";//重定向到 首页路径
    }
    @RequestMapping("/menu")
    public String menu(){
        return "login";//跳转到首页
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public String login(String name,String password){
        System.out.println(name+"-"+password);
        return "success";
    }

    @RequestMapping("/home")
    public String home(){
        return "/home";//跳转到主页
    }
}
