package com.xuxy.demo.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackDemo {


    public static void main(String[] args) {
        Logger logger =  LoggerFactory.getLogger(LogbackDemo.class);

        logger.debug("system.out.println()");
    }
}
