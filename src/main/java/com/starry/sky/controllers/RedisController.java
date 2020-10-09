package com.starry.sky.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import redis.clients.jedis.Jedis;


/**
 * rediscontroller
 *
 * @author 徐晓阳
 * @创建日期（ 2020-05-14 23:07 ）
 * @description
 */
@Controller
public class RedisController {


    @Autowired
    private RedisTemplate redisTemplate;


    //@PostConstruct
    public void init() {
        ValueOperations  operations = redisTemplate.opsForValue();

        String str = (String) redisTemplate.opsForValue().get("aaa");
        System.out.println(str);
    }

    public static void main(String[] args) {

        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.auth("123456");
        String value = jedis.get("aaa");
        System.out.println(value);
    }
}
