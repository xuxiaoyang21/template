package com.supcon.controller.jodd;

import jodd.mail.*;


/**
 * Created by Intellij IDEA
 *
 * @author:xuxiaoyang
 * @Date: 2017/9/27  10:16
 * @Description: 使用 jodd 发送email
 */
public class EmailTest {

    public static void main(String[] args){
        System.out.println("邮件开始发送...");
        SendMailSession session = createSession();//获取打开后的邮件发送session
        Email email = email();
        Email email1 = eamilHtml();
        //可以同时发很多封
        session.sendMail(email);//发送普通邮件
        session.sendMail(email1);//发送html邮件
        session.close();//邮件服务关闭
        System.out.println("邮件发送成功");
    }
    private static SendMailSession createSession(){
        SmtpServer smtpServer = SmtpServer.create("smtp.126.com")//设置邮件的服务器
                //#####################################
                .authenticateWith("15738862640@126.com","zmx5211314");//设置发送端的账号和密码 此处密码是客户端授权码
                //.timeout(10);//设置超时时间
                //.properties()//设置额外的邮件属性
        SendMailSession session = smtpServer.createSession();
        session.open();//打开邮件发送服务
        return session;//返回邮件发送的session
    }

    /**
     * 设置简易的email文件对象
     */
    private static Email email(){

        Email email = Email.create() //创建eamil对象
                .from("15738862640@126.com")//设置发送邮件地址
                .to("1334909533@qq.com")//设置接收邮件地址
                .subject("测试email邮件")//设置邮件主题
                //.attach()
                //.bcc()
                //.cc()
                //.embed()
                //.header()
                //.message()
                .addText("email测试内容");//设置邮件内容

        return  email;

    }

    private static Email eamilHtml(){
        Email email = Email.create()
                .from("15738862640@126.com")
                .to("1334909533@qq.com")
                .subject("邮件主题")
                .addHtml("<html><META http-equiv=Content-Type content=\"text/html; charset=utf-8\">"+
                        "<body><h1>Hey!</h1><img src='cid:c.png'><h2>Hay!</h2></body></html>");
        return email;
    }

}
