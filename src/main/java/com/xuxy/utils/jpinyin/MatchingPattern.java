package com.xuxy.utils.jpinyin;

/**
 * Created with IDEA.
 * Copyright(c) SUPCON 2003-2015. 浙江浙大中控信息技术有限公司
 *
 * @author:zhangxiaoqiang.
 * @date:2016-03-17.
 * @time:下午12:43.
 * @Description:对比匹配模式
 */
public enum MatchingPattern {
    LIKE,  //模糊查询
    LIFT,  //作匹配
    RIGHT, //右匹配
    FULL,   //全匹配
    HANZIFIRST, //拼音汉字混合汉字优先
    PINYINFIRST ////拼音汉字混合拼音优先
}
