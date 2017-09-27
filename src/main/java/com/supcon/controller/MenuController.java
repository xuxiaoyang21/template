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
@RequestMapping("/content")
public class MenuController {


    /**
     * Created by Intellij IDEA
     * @author:xuxiaoyang
     * @Date: 2017/9/26 14:57
     * @Description:表单在ajax中的序列化
     * @return
     */
    @RequestMapping("/ajax")
    public String serialize(){
        return "ajax/serialize";//表单的序列还
    }

    /**
     * Created by Intellij IDEA
     * @author:xuxiaoyang
     * @Date: 2017/9/26 14:57
     * @Description:日期字符串和日期的使用测试
     * @return
     */
    @RequestMapping("/date")
    public String date(){
        return "date/date"; //日期字符串和日期的转换和使用
    }

    /**
     * Created by Intellij IDEA
     * @author:xuxiaoyang
     * @Date: 2017/9/26 14:56
     * @Description:日期选择器的使用
     * @return
     */
    @RequestMapping("/wdatepicker")
    public String wdatepicker(){
        return "wdatepicker/wdatepicker";
    }

    /**
     * Created by Intellij IDEA
     * @author:xuxiaoyang
     * @Date: 2017/9/26 14:56
     * @Description:kendo中window对象的使用
     * @return
     */
    @RequestMapping("/window")
    public String window(){
        return "kendo/window";
    }

    /**
     * Created by Intellij IDEA
     * @author:xuxiaoyang
     * @Date: 2017/9/26 14:56
     * @Description: array对像的测试页面
     * @return
     */
    @RequestMapping("/array")
    public String array(){
        return "javascript/array";
    }

    /**
     * Created by Intellij IDEA
     * @author:xuxiaoyang
     * @Date: 2017/9/26 14:55
     * @Description:   滚动动画的测试使用
     * @return
     */
    @RequestMapping("/flexslider")
    public String flexslicer(){
        return "jqueryFlexSlider/flexslider";
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
        //return "jqueryFlexSlider/flexslider";
        return "hello";
    }
    @RequestMapping("/test")
    public String test(){

        return "wdatepicker/wdatepicker";
    }

    /**
     * Created by Intellij IDEA
     * @author:xuxiaoyang
     * @Date: 2017/9/27 13:59
     * @Description: kendo中grid的使用
     * @return
     */
    @RequestMapping("grid")
    public String grid(){

        return "kendo/grid";
    }
}
