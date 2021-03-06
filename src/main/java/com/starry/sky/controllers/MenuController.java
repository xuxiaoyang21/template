package com.starry.sky.controllers;

import com.starry.sky.services.UserService;
import com.starry.sky.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 进入主页面跳转页面
 * Created by Mxia on 2017/7/13.
 */
@Controller
@RequestMapping("/content")
public class MenuController {


    @Autowired
    private UserService userService;
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

    String text;
    @RequestMapping(value = "/serialize",method = RequestMethod.POST)
    @ResponseBody
    public String sereee(HttpServletResponse response,String id, String name , String address, String flag){
        if(flag==null || flag.equals("")){
           text = id+name+address;
            return "success";
        }else if(flag.equals("0")){
            response.setCharacterEncoding("utf-8");
            String result = text;
            text="";
            return result;
        }

        return "为空";
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
        return "/home";
    }
    @RequestMapping("/test")
    public String test(){

        return "jquery/text";
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

    /**
     * 图片放大器的使用
     * @return
     */
    @RequestMapping("jqzoom")
    public String jqzoom(){
        return "jquery/jqzoom";
    }


    @RequestMapping("uploadify")
    public String uploadify(){
        return "jquery/uploadify";
    }
    @RequestMapping(value = "upload",method = RequestMethod.POST)
    public String upload(){
        System.out.println("上传.......");
        return "";
    }
    @RequestMapping(value = "upload",method = RequestMethod.GET)
    public String uploadww(){
        System.out.println("上传get.......");
        return "";
    }


    @RequestMapping(value = "ztree")
    public String ztree(){
        return "ztree/ztree";
    }

    @RequestMapping("echarts")
    public String echarts() {
        List<User> users =  userService.findAllUser();
        return "echarts";
    }



}
