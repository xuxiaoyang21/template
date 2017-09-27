package com.xuxy.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return "hello";//跳转到首页
    }
}
