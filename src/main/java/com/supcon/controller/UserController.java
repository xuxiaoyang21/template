package com.supcon.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 进入主页面跳转页面
 * Created by Mxia on 2017/7/13.
 */
@Controller
public class UserController {


    @RequestMapping("/")
    public String login(){
        return "redirect:user";
    }


    @RequestMapping("/user")
    public String input(ModelMap modelMap, Model model){
        model.addAttribute("address","长安");
        modelMap.put("name" ,"hello velocity");


        //return "wdatepicker/wdatepicker";//日期选择器的使用
        //return "date/date"; //日期字符串和日期的转换和使用
        //return "ajax/serialize";//表单的序列还
        //return "kendo/window";//kendowindow测试页面
        //return "javascript/array";//javascript测试页面
        return "jqueryFlexSlider/flexslider";
    }
    @RequestMapping("/test")
    public String test(){

        return "wdatepicker/wdatepicker";
    }
}
