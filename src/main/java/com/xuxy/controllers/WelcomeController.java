package com.xuxy.controllers;

import com.xuxy.dto.Message;
import jodd.http.HttpRequest;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Intellij IDEA
 *
 * @author:xuxiaoyang
 * @Date: 2017/9/26  9:59
 * @Description: 首页 欢迎页
 */

@Controller
public class WelcomeController {

    /**
     * 登录页面
     * @return
     */
    @RequestMapping("/")
    public String login(ModelMap modelMap){
        return "login";//跳转到登录页面
    }


    /**
     * 登录认证
     * @param name
     * @param password
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(HttpServletRequest httpRequest, ModelMap modelMap , String name, String password, RedirectAttributes redirectAttributes){
        System.out.println(httpRequest.getRequestURL());
        //  RedirectAttributes用来页面提示用的
        System.out.println("name"+name);
        //判断用户是否登录
        Subject subject = SecurityUtils.getSubject();//主题类
        if(subject.isAuthenticated()){
            subject.logout();//如果已经登陆就登出
        }
        try{
            //未登录则验证登录
            UsernamePasswordToken token = new UsernamePasswordToken(name,password);//DigestUtils.md5Hex(password+"34324")
            subject.login(token);//登录验证
            return "home";//跳转到首页
            //不成功一律跳转到原登录页面
        }catch (LockedAccountException ex) {
            redirectAttributes.addFlashAttribute("message",new Message(Message.ERROR,ex.getMessage()));
            return "redirect:/";
        } catch (UnknownAccountException ex) {
            redirectAttributes.addFlashAttribute("message",new Message(Message.ERROR,ex.getMessage()));
            return "redirect:/";
        } catch (AuthenticationException ex) {
            modelMap.put("name","sdfsdfsdf");
            redirectAttributes.addFlashAttribute("message",new Message(Message.ERROR,"账号或密码错误"));
            return "redirect:/";
        }



    }

    @RequestMapping("/home")
    public String home(){
        return "/home";//跳转到主页
    }

    @RequestMapping("/logout")
    @ResponseBody
    public Message logout(){
        try {
            SecurityUtils.getSubject().logout();//安全登出
        }catch (AuthenticationException e){
            return new Message(Message.ERROR,"服务器错误！");
        }
        //return "/login";//跳转到登录页面
        return new Message(Message.SUCCESS,"success");
    }
}
