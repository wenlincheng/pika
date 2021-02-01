package com.wenlincheng.pika.common.core.util;


import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String 工具类
 * Created by shuzheng on 2016/12/07.
 */
public class StringUtil {

    private static Pattern linePattern = Pattern.compile("_(\\w)");
    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /**
     * 下划线转驼峰
     *
     * @param str
     * @return
     */
    public static String lineToHump(String str) {
        if (null == str || "".equals(str)) {
            return str;
        }
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);

        str = sb.toString();
        str = str.substring(0, 1).toUpperCase() + str.substring(1);

        return str;
    }

    /**
     * 驼峰转下划线(简单写法，效率低于{@link #humpToLine2(String)})
     *
     * @param str
     * @return
     */
    public static String humpToLine(String str) {
        return str.replaceAll("[A-Z]", "_$0").toLowerCase();
    }

    /**
     * 驼峰转下划线,效率比上面高
     *
     * @param str
     * @return
     */
    public static String humpToLine2(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 首字母转小写
     *
     * @param s
     * @return
     */
    public static String toLowerCaseFirstOne(String s) {
        if (StringUtils.isBlank(s)) {
            return s;
        }
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     * 首字母转大写
     *
     * @param s
     * @return
     */
    public static String toUpperCaseFirstOne(String s) {
        if (StringUtils.isBlank(s)) {
            return s;
        }
        if (Character.isUpperCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuffer()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     * object转String
     *
     * @param object
     * @return
     */
    public static String getString(Object object) {
        return getString(object, "");
    }

    public static String getString(Object object, String defaultValue) {
        if (null == object) {
            return defaultValue;
        }
        try {
            return object.toString();
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * object转Integer
     *
     * @param object
     * @return
     */
    public static int getInt(Object object) {
        return getInt(object, -1);
    }

    public static int getInt(Object object, Integer defaultValue) {
        if (null == object) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(object.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * object转Boolean
     *
     * @param object
     * @return
     */
    public static boolean getBoolean(Object object) {
        return getBoolean(object, false);
    }

    public static boolean getBoolean(Object object, Boolean defaultValue) {
        if (null == object) {
            return defaultValue;
        }
        try {
            return Boolean.parseBoolean(object.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }


    public static String getTrailPrice(BigDecimal trialPrice) {
        return "¥ " + trialPrice.setScale(1, BigDecimal.ROUND_UP) + "/天";
    }

    public static String getParamByUrl(String url, String key) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        url=url.replaceAll("%26","&");
        url=url.replaceAll("%3D","=");
        if(url.contains("sourceURL")){
            url=url.replaceAll("sourceURL=","");
        }


        try {
            if (url.contains("&")) {
                for (String param : url.split("&")) {
                    String paramKey = param.split("=")[0];
                    String value = param.split("=")[1];
                    if (paramKey.equals(key)) {
                        return value;
                    }
                }
            } else {
                String paramKey = url.split("=")[0];
                String value = url.split("=")[1];
                if (paramKey.equals(key)) {
                    return value;
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public static  String[] splitStr(String str,String symbol){
        String[] split = new String[]{};
        if(str != null) {
            split = str.split(symbol);
        }
        return split;
    }

//    public static void main(String[] args) {
//        System.out.println(getParamByUrl("http%3A%2F%2F192.168.7.10%3A8081%2FbaigeApp%2FcateWeb.jsp%3Fid%3D84615679178584651a00a093b5e86c98%26sourceURL%3DclickType%3D5%26clickId%3Da7909494f3cc8e2d6fa7d7931290b0bb","clickId"));
//    }
}
