package com.xuxy.utils;


import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
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
}
