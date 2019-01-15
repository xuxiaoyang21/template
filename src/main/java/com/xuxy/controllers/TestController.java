package com.xuxy.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
