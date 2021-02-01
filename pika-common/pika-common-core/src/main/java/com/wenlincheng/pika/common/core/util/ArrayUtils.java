package com.wenlincheng.pika.common.core.util;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 数组工具类
 *
 * @author wulg
 * @date 2017/8/30
 */
public class ArrayUtils {


    /**
     * list转String
     *
     * @param list list
     * @return String[]
     */
    public static String[] listToStringArray(List<String> list) {
        return list.toArray(new String[list.size()]);
    }


    
    /**
     * 数组字符串转字符串数组
     * @MethodName: arrayStringToStringArray
     * @param str
     * @Return: java.lang.String[] 
     * @Author: wenlincheng
     * @Date: 2019/5/7 15:57
     */
    public static String[] arrayStringToStringArray(String str){

        if( str != null && !"".equals(str) && !"[]".equals(str)){
            str = str.replace("[", "");
            str = str.replace("]", "");
            str = str.replace("\"", "");

            return str.split(",");
        }else {

            return null;
        }
    }

    /*******************
     * 获取非null的元素列表
     * @param list list
     * @return T
     */
    public static <T> List<T> filterNullElements(final List<T> list) {
        if (list == null) {
            return new ArrayList<T>(0);
        }
        final List<T> result = new ArrayList<>(list.size());
        for (final T t : list) {
            if (t != null) {
                result.add(t);
            }
        }
        return result;
    }


    /**
     * list转String
     *
     * @param list      字符窜集合
     * @param namespace namespace
     * @return String[]
     */
    public static <T> String[] listToStringArray(String namespace, List<T> list) {
        final String[] array = new String[list.size()];
        int i = -1;
        for (T postFix : list) {
            array[++i] = namespace + postFix;
        }
        return array;
    }




    public static String listToString(List<String> list) {
        if (CheckUtils.isEmpty(list)) {
            return "";
        }
        StringBuilder stringBuffer = new StringBuilder();
        for (String string : list) {
            stringBuffer.append(string);
            stringBuffer.append(",");
        }
        return stringBuffer.toString().substring(0, stringBuffer.length() - 1);
    }

    /**
     * 从list中随机抽取若干不重复元素
     *
     * @param paramList:被抽取list
     * @param count:抽取元素的个数
     * @return List 由抽取元素组成的新list
     */
    public static List getRandomList(List paramList, int count) {
        if (paramList.size() < count) {
            return paramList;
        }
        Random random = new Random();
        List<Integer> tempList = new ArrayList<>();
        List<Object> newList = new ArrayList<>();
        int temp;
        for (int i = 0; i < count; i++) {
            //将产生的随机数作为被抽list的索引
            temp = random.nextInt(paramList.size());
            if (!tempList.contains(temp)) {
                tempList.add(temp);
                newList.add(paramList.get(temp));
            } else {
                i--;
            }
        }
        return newList;
    }


    /**
     * 从list中随机抽取若干不重复元素
     *
     * @param paramList:被抽取list
     * @param count:抽取元素的个数
     * @return List 由抽取元素组成的新list
     */
    public static List getCountList(List  paramList, int count) {
        if (paramList.size() < count) {
            return paramList;
        }
        List<Object> newList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            newList.add(paramList.get(i));
        }
        return newList;
    }
}
