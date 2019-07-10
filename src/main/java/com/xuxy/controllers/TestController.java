package com.xuxy.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.jms.Session;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/test")
public class TestController {

    /**
     * 播放wmv视频文件
     * @return
     */
    @RequestMapping("/playWmv")
    public String playWmv() {
        return "/test/playWmvss";
    }


    @RequestMapping("map")
    public String gaodeMap() {
        return "/test/map";
    }

    @RequestMapping("cookies")
    public String analysisCookies(HttpServletRequest request, HttpServletResponse response) {

        //获取session
        HttpSession session = request.getSession();
        String sessionId = session.getId();
        System.out.println("sessionId: "+sessionId);
        System.out.println("1D1658BDB049AFC990A5893DACFBB774");
        System.out.println(session.getAttribute("name"));
        session.setAttribute("name","小阳");
        System.out.println(session.getAttribute("name"));
        //获取cookies
        Cookie[] cookies =  request.getCookies(); //用户浏览器传入的cookies
        if(cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                System.out.println(cookie.getName());
                System.out.println(cookie.getValue());
                System.out.println(cookie.getVersion());
                System.out.println(cookie.getDomain());
                System.out.println(cookie.getComment());
                System.out.println(cookie.getMaxAge());
            }
        } else {
            System.out.println("---------第一次登陆 没有cookies!!------------");
        }
        Cookie coo = new Cookie("lastTime",System.currentTimeMillis()+"");
        coo.setMaxAge(0); //删除cookies
        coo.setMaxAge(10000); //设置有效期为多秒
        response.addCookie(coo);
        return "";
    }

    @RequestMapping("upload")
    public String ajaxFileUpload() {
        return "test/upload";
    }

    @RequestMapping(value = "upload",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> ajaxFileUploads(HttpServletResponse response,@RequestParam("file") CommonsMultipartFile file) {

        System.out.println("---数据传输进来-----");
        //设置写入的路径
        File writeFile = new File("D:\\workspace\\xuxy\\template\\src\\main\\webapp\\static\\photoAttr\\");
        if(!writeFile.exists()) {
            writeFile.mkdir();
        }
        //获取传入文件的名称
        String originName = file.getOriginalFilename();
        String resultName = "D:\\workspace\\xuxy\\template\\src\\main\\webapp\\static\\photoAttr\\"+originName;
        File f = new File(resultName);
        try {
            file.transferTo(f);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("--------图片写入失败-----------");
        }

        System.out.println("-----数据吸入完毕-----");
        Map<String,String> map = new HashMap<>();
        map.put("status","success");
        map.put("picUrl",resultName);
        return map;
    }

    public static void main(String[] args) {
        try {
            InputStream inputStream = new FileInputStream("");
            int size = inputStream.available();
            byte[] bytes = new byte[size];
            ByteArrayInputStream stream = new ByteArrayInputStream(bytes);

        } catch (Exception e) {

        }

        ByteBuffer byteBuffer = ByteBuffer.allocate(88);
        System.out.println(byteBuffer);

        String value = "nio测试数据";
        byteBuffer.put(value.getBytes());
        System.out.println(byteBuffer);

        byteBuffer.flip();
        System.out.println(byteBuffer);

        byte[] bytes = new byte[byteBuffer.remaining()];
        byteBuffer.get(bytes);

        System.out.println(byteBuffer);
        System.out.println(new String(bytes));
    }


}
