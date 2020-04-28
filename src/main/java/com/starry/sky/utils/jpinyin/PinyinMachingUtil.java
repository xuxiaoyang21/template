package com.starry.sky.utils.jpinyin;

import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.starry.sky.utils.StringUtils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IDEA.
 * Copyright(c) SUPCON 2003-2015. 浙江浙大中控信息技术有限公司
 *
 * @author:xuxiaoyang
 * @date:2016-03-17.
 * @time:上午10:32.
 * @Description:汉语转拼音
 */
public class PinyinMachingUtil<T> {
    /**
     * Created with IDEA .
     *
     * @author:xuxiaoyang .
     * @date:2016-03-17 13:24:13.
     * @value: list:需要对比的对象泛型类实体列表。
     * @value: name:需要跟实体的那个熟悉对比。
     * @value: key:那个key要与上面的熟悉对比,忽略大小写。
     * @value: pattern:对比的模式，全匹配还是模糊还是左右匹配。
     * @description:多条匹配是否与给定的汉语转换的拼音匹配
     */
    public List<T> pinYinMachingList(List<T> list, String name, String key, MatchingPattern pattern) {
        List<T> finnallyList = new ArrayList<T>();
        if (StringUtils.isEmpty(key)) {
            finnallyList=list;
        } else {
            for (T t : list) {
                if (isMaching(t, name, key, pattern)) {//匹配
                    finnallyList.add(t);
                }
            }
        }

        return finnallyList;
    }

    /**
     * Created with IDEA .
     *
     * @author:xuxiaoyang .
     * @date:2016-03-17 13:24:13.
     * @value: t:需要对比的对象泛型类实体。
     * @value: name:需要跟实体的那个熟悉对比。
     * @value: key:那个key要与上面的熟悉对比,忽略大小写。
     * @value: pattern:对比的模式，全匹配还是模糊还是左右匹配。
     * @description:单条匹配是否与给定的汉语转换的拼音匹配
     */
    public boolean isMaching(T t, String name, String key, MatchingPattern pattern) {
        boolean isMach = false;
        Class<?> clazz = null;
        String nameValue = "";
        try {
            if (t != null) {
                clazz = t.getClass();
                if (StringUtils.isNotEmpty(name)) {
                    Method getNameMethod = clazz.getDeclaredMethod("get" + name.substring(0, 1).toUpperCase() + name.substring(1));
                    //避开java访问控制检测
                    getNameMethod.setAccessible(true);
                    //调用反射方法获取类属性的值
                    nameValue = String.valueOf(getNameMethod.invoke(t, new Object[]{}));
                    //调用汉字转拼音方法将属性转换未拼音
                    String nameValuePinYin = PinyinHelper.convertToPinyinString(nameValue, ",", PinyinFormat.WITHOUT_TONE).toUpperCase();
                    String nameValueShortPinYin = PinyinHelper.getShortPinyin(nameValue).toUpperCase();
                    //判断给定的key是否与属性拼音吻合
                    if (isPinYin(key)) {//如果输入的是纯拼音
                        isMach = matching(key.toUpperCase(), pattern, nameValuePinYin, nameValueShortPinYin);
                    } else {//输入的是非全拼音
                        //key = URLDecoder.decode(key, "UTF-8");
                        key = new String(key.getBytes("ISO-8859-1"), "UTF-8");
                        isMach = matching(key, pattern, nameValue, nameValue);
                    }
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return isMach;
    }

    /**
     * Created with IDEA .
     *
     * @author:xuxiaoyang .
     * @date:2016-03-17 13:30:57.
     * @description:根据匹配模式来确定是否匹配
     */
    private boolean matching(String key, MatchingPattern pattern, String nameValuePinYin, String nameValueShortPinYin) {
        boolean isMach = false;
        if (pattern.equals(MatchingPattern.LIKE)) {//模糊匹配
            //if (nameValuePinYin.contains(key)||nameValueShortPinYin.contains(key)) {
            if (nameValueShortPinYin.contains(key) || everyWordMatching(nameValuePinYin, key)) {
                isMach = true;
            }
        } else if (pattern.equals(MatchingPattern.LIFT)) {//左匹配
            if (nameValuePinYin.startsWith(key)) {
                isMach = true;
            }
        } else if (pattern.equals(MatchingPattern.RIGHT)) {//右匹配
            if (nameValuePinYin.endsWith(key)) {
                isMach = true;
            }
        } else if (pattern.equals(MatchingPattern.FULL)) {//全匹配
            if (nameValuePinYin.equals(key) || nameValueShortPinYin.contains(key)) {
                isMach = true;
            }
        }
        return isMach;
    }

    /**
     * Created with IDEA .
     *
     * @author:xuxiaoyang .
     * @date:2016-03-17 16:44:32.
     * @description：匹配每一个完整汉字的拼音
     */
    private boolean everyWordMatching(String nameValuePinYin, String key) {
        boolean isMatch = false;
        String[] chartsPinYin = {};
        if (StringUtils.isNotEmpty(nameValuePinYin)) {
            chartsPinYin = nameValuePinYin.split(",");
        }
        for (String word : chartsPinYin) {
            if (key.contains(word)) {
                isMatch = true;
            }
        }
        return isMatch;
    }

    /**
     * Created with IDEA .
     *
     * @author:xuxiaoyang .
     * @date:2016-03-17 12:49:52.
     * @description:判断输入的是否为拼音
     */
    public static boolean isPinYin(String inputCode) {
        char[] chars = inputCode.toCharArray();
        boolean ispy = true;
        for (char aChar : chars) {
            boolean ispinyin = (aChar >= 'A' && aChar <= 'Z') || (aChar >= 'a' && aChar <= 'z');
            if (!ispinyin) {
                ispy = false;
                break;
            }
        }
        return ispy;
    }
}
