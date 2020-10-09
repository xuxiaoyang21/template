package com.starry.sky.controllers;

import com.starry.sky.entities.User;
import com.starry.sky.services.UserService;
import com.starry.sky.utils.Pageable;
import com.starry.sky.utils.impl.NewPagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.crypto.Data;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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


    /**
     * Created by Intellij IDEA
     * @author:xuxiaoyang
     * @Date: 2017/9/29 13:27
     * @Description:新增人员
     * @param user
     * @return
     */
    @RequestMapping(value = "/create",method = RequestMethod.POST)
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

    /**
     * Created by Intellij IDEA
     * @author:xuxiaoyang
     * @Date: 2017/9/29 13:28
     * @Description: 查看所有人员列表
     * @param pager
     * @param user
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public Pageable<User> list(NewPagination<User> pager,User user){
         System.out.println("分页的尺寸是："+pager.getPageSize());
        //查询数据列表
        return this.userService.find(pager,user);
    }

    /**
     * Created by Intellij IDEA
     * @author:xuxiaoyang
     * @Date: 2017/9/29 13:28
     * @Description: 更新用户信息
     * @param
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public String update(User user){

       System.out.println(""+user.toString());
       //保存数据
        userService.update(user);

        return "success";
    }

    @RequestMapping(value = "destroy",method = RequestMethod.POST)
    @ResponseBody
    public String destroy(User user){
        System.out.println("删除的id:"+user.getId());

        userService.deleteById(user);

        return "success";

    }

    @RequestMapping(value = "add",method = RequestMethod.POST)
    @ResponseBody
    public String addd(User user) {
        userService.insert(user);
        return "success";
    }

    public static void main(String[] args) {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy.MM'_m'");
        try {
            String str =  format.format(date);
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
