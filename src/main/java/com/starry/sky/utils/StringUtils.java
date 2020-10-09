package com.starry.sky.utils;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jodd.util.StringUtil;

public class StringUtils extends StringUtil {
    private static final Pattern PATTERN_VARIABLE = Pattern.compile("\\$\\{([\\w_-]+?)\\}");

    public StringUtils() {
    }

    public static String parse(String template, Map<String, Object> context) {
        if(isBlank(template)) {
            return "";
        } else {
            String replace;
            Object value;
            for(Matcher matcher = PATTERN_VARIABLE.matcher(template); matcher.find(); template = template.replace(replace, null != value?String.valueOf(value):"")) {
                replace = matcher.group();
                String var = matcher.group(1);
                value = context.get(var);
            }

            return template;
        }
    }

    public static <T> StringBuilder concat(Object c, Collection<T> coll) {
        StringBuilder sb = new StringBuilder();
        if(null != coll && !coll.isEmpty()) {
            Iterator it = coll.iterator();
            sb.append(it.next());

            while(it.hasNext()) {
                sb.append(c).append(it.next());
            }

            return sb;
        } else {
            return sb;
        }
    }

    public static <T> StringBuilder concat(int offset, int len, Object c, T[] objs) {
        StringBuilder sb = new StringBuilder();
        if(null != objs && len >= 0 && 0 != objs.length) {
            if(offset < objs.length) {
                sb.append(objs[offset]);

                for(int i = 1; i < len && i + offset < objs.length; ++i) {
                    sb.append(c).append(objs[i + offset]);
                }
            }

            return sb;
        } else {
            return sb;
        }
    }

    public static <T> StringBuilder concat(int offset, int len, T[] array) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < len; ++i) {
            sb.append(array[i + offset].toString());
        }

        return sb;
    }

    public static String dup(CharSequence cs, int num) {
        if(null != cs && cs.length() != 0 && num > 0) {
            StringBuilder sb = new StringBuilder(cs.length() * num);

            for(int i = 0; i < num; ++i) {
                sb.append(cs);
            }

            return sb.toString();
        } else {
            return "";
        }
    }

    public static String dup(char c, int num) {
        if(c != 0 && num >= 1) {
            StringBuilder sb = new StringBuilder(num);

            for(int i = 0; i < num; ++i) {
                sb.append(c);
            }

            return sb.toString();
        } else {
            return "";
        }
    }


    /**
     * 字符串根据制定的pattern转成集合
     * @param str 字符串
     * @param pattern 规则
     * @return 返回转换好的集合数据
     */
    public static List<String> stringToList(String str, String pattern) {
        if(!org.apache.commons.lang.StringUtils.isEmpty(str)) {
            return Arrays.asList(str.split(pattern));
        }
        return null;
    }

    /**
     * 将string字符串转换为加单引号可以供oracle查询使用
     * @param list  传入的字符串集合
     * @return  返回可供oracle操纵的带单引号的数据拼接
     */
    public static String stringToOracle(List<String> list) {
        StringBuffer result = new StringBuffer();
        if(list != null) {
            boolean flag = true;
            for (String str : list) {

                if(flag) { //第一次
                    result.append("\'").append(str).append("\'");
                    flag = false;
                }else { //出去第一次 接下去每次都加上逗号
                    result.append(",").append("\'").append(str).append("\'");
                }
            }
            return result.toString();
        }
        return  null;
    }


    /**
     * 字符数据反转
     * @author 徐晓阳
     * @creatTime 2020-09-22 09:26
     * @param arrays 需要反转的字符数组
     * @return
     * @version 1.0
     */
    public static String[] reversalArrary(String[] arrays) {
        String[] result = new String[arrays.length];
        for(int i = arrays.length,j=0; i > 0; i--,j++) {
            result[j] = arrays[i];
        }
        return result;
    }

}
