package com.wenlincheng.pika.common.core.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 工具类
 * Created by wulg on 2016/3/29 0029.
 */
public class CheckUtils {

    /**
     * 防止外部实例化该类
     */
    private CheckUtils()
    {
    }

    /**
     * 检验字符串的内容是否是在整型范围内的非负整数 <功能详细描述>
     *
     * @param str
     * @return [参数说明]
     *
     * @return boolean [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static boolean isNotNegativeInt(String str)
    {
        int checkInt;
        try
        {
            checkInt = Integer.parseInt(str);
            if (0 > checkInt)
            {
                return false;
            }
            return true;
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 检验字符串的内容是否是在整形范围内的数字 <功能详细描述>
     *
     * @param str
     * @return [参数说明]
     *
     * @return boolean [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static boolean isInt(String str)
    {
        try
        {
            Integer.parseInt(str);
            return true;
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 检验字符串的内容是否是在长整形范围内的数字 <功能详细描述>
     *
     * @param str
     * @return [参数说明]
     *
     * @return boolean [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static boolean isLong(String str)
    {
        try
        {
            Long.parseLong(str);
            return true;
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 检验字符串的内容是否是浮点格式 <功能详细描述>
     *
     * @param str
     * @return [参数说明]
     *
     * @return boolean [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static boolean isFloat(String str)
    {
        try
        {
            Float.parseFloat(str);
            return true;
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 字符串转int <功能详细描述>
     *
     * @param str
     * @param defaultValue
     * @return int [返回类型说明]
     */
    public static int toIntValue(String str, int defaultValue)
    {
        try
        {
            if (isEmpty(str))
            {
                return defaultValue;
            }
            return Integer.parseInt(str.trim());
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
            return defaultValue;
        }
    }

    /**
     * 字符串转long <功能详细描述>
     *
     * @param str
     * @param defaultValue
     * @return long [返回类型说明]
     */
    public static long toLongValue(String str, Long defaultValue)
    {
        try
        {
            if (isEmpty(str))
            {
                return defaultValue;
            }
            return Long.parseLong(str.trim());
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
            return defaultValue;
        }
    }

    /**
     * 判断Long 是否是空
     *
     * @param value
     * @return [参数说明]
     *
     * @return boolean [返回类型说明]
     * @see [类、类#方法、类#成员]
     */
    public static boolean isEmpty(Long value){
        if(value == null || value == 0){
            return true;
        }
        return false;
    }

    /**
     * 判断Long 是否不为空
     *
     * @param value
     * @return [参数说明]
     *
     * @return boolean [返回类型说明]
     * @see [类、类#方法、类#成员]
     */
    public static boolean isNotEmpty(Long value){
        if(value == null || value == 0){
            return false;
        }
        return true;
    }

    /**
     * 给字符串去掉空格 <功能详细描述>
     *
     * @param arg
     * @return [参数说明]
     *
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String trim(String arg)
    {
        if (null == arg)
        {
            return "";
        }
        else
        {
            return arg.trim();
        }
    }

    /**
     * 检查字符串是否为空，字符串为null，或者长度为0都认为为空 <功能详细描述>
     *
     * @param str
     * @return [参数说明]
     *
     * @return boolean [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static boolean isEmpty(String str)
    {
        if (null == str)
        {
            return true;
        }

        if (0 == str.trim().length())
        {
            return true;
        }
        if ("null".equals(str))
        {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(String str)
    {
        return !isEmpty(str);
    }

    /**
     * 判断指定的对象是否为空
     *
     * @param obj
     * @return [参数说明]
     *
     * @return boolean [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static boolean isEmpty(Object obj)
    {
        if (null == obj)
        {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(Object obj)
    {
        return !isEmpty(obj);
    }

    /**
     * 判断指定的对象是否为空
     *
     * @param map
     * @return [参数说明]
     *
     * @return boolean [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Map map)
    {
        if ((null == map) || (map.isEmpty()))
        {
            return true;
        }

        return false;
    }

    @SuppressWarnings("rawtypes")
    public static boolean isNotEmpty(Map map)
    {
        return !isEmpty(map);
    }

    /**
     * 判断指定的字符串数组是否为空或长度为0 <功能详细描述>
     *
     * @param strArr 字符串数组
     * @return [参数说明]
     *
     * @return boolean [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static boolean isEmpty(String[] strArr)
    {
        if ((null == strArr) || (strArr.length < 1))
        {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(String[] strArr)
    {
        return !isEmpty(strArr);
    }

    /**
     * 判断指定的对象数组是否为空
     *
     * @param objArr 对象数组
     * @return [参数说明]
     *
     * @return boolean [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static boolean isEmpty(Object[] objArr)
    {
        if ((null == objArr) || (objArr.length < 1))
        {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(Object[] objArr)
    {
        return !isEmpty(objArr);
    }

    /**
     * 判断指定的对象列表是否为空
     *
     * @param lst 指定的对象列表
     * @return [参数说明]
     *
     * @return boolean [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static boolean isEmpty(List<? extends Object> lst)
    {
        if ((null == lst) || (lst.isEmpty()))
        {
            return true;
        }
        return false;
    }

    /**
     * 判断指定的对象列表是否为空
     *
     * @param lst 指定的对象列表
     * @return [参数说明]
     *
     * @return boolean [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static boolean isNotEmpty(List<? extends Object> lst)
    {
        return !isEmpty(lst);
    }

    /**
     * 判断指定的对象集合是否为空
     *
     * @param set 指定的对象列表
     * @return [参数说明]
     *
     * @return boolean [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static boolean isEmpty(Set<? extends Object> set)
    {
        if ((null == set) || (set.isEmpty()))
        {
            return true;
        }
        return false;
    }

    /**
     * 判断指定的对象集合是否为空
     *
     * @param set 指定的对象列表
     * @return [参数说明]
     *
     * @return boolean [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static boolean isNotEmpty(Set<? extends Object> set)
    {
        return !isEmpty(set);
    }

    /**
     * 计算指定字符串的MD5摘要
     *
     * @param strSrc 指定的原字符串
     * @return 计算后的字节数组
     */
    public static byte[] getMD5(String strSrc)
    {
        if (null == strSrc)
        {
            return null;
        }

        byte[] bArrRst = null;// 返回的字节数组
        try
        {
            byte[] passwdtmp = strSrc.getBytes("UTF-8");
            MessageDigest md5 = MessageDigest.getInstance("MD5");// MD5
            md5.update(passwdtmp, 0, passwdtmp.length);
            bArrRst = md5.digest();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return bArrRst;
    }

    /**
     * 返回对象的字符形式 如果对象为null，则返回""
     *
     * @param o 输入对象
     * @return string
     *
     */
    public static String nvl(Object o)
    {
        return (null == o) ? "" : o.toString().trim();
    }

    /**
     * 截取Map的部分内容 LinkedHashMap是有顺序的map，所以可以按添加顺序截取
     *
     * @param linkedHashMap
     * @param start 开始下标，从0开始
     * @param size 要截取的个数
     * @param exceptKeys 截取时，不会获取的对象
     * @return [参数说明]
     *
     * @return LinkedHashMap<K,V> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    @SafeVarargs
    public static <K, V> LinkedHashMap<K, V> copyMap(LinkedHashMap<K, V> linkedHashMap, int start, int size,
                                                     K... exceptKeys)
    {
        Set<K> exceptKey = new HashSet<K>();
        Collections.addAll(exceptKey, exceptKeys);

        LinkedHashMap<K, V> subMap = new LinkedHashMap<K, V>();
        if (null != linkedHashMap)
        {
            int length = linkedHashMap.size();
            if (start < length)
            {
                subMap = new LinkedHashMap<K, V>();
                Set<Map.Entry<K, V>> entrys = linkedHashMap.entrySet();
                int index = 0; // map下标
                int curSize = 0;
                for (Map.Entry<K, V> en : entrys)
                {
                    if (index >= start)
                    {
                        K key = en.getKey();
                        // 排除不要的对象
                        if (!exceptKey.contains(key))
                        {
                            if (curSize < size)
                            {
                                curSize++;
                                subMap.put(en.getKey(), en.getValue());
                            }
                            else
                            { // 数量已全，退出循环
                                break;
                            }
                        }
                    }
                    index++;
                }
            }
        }
        return subMap;
    }

    /**
     *
     * 依据传入时间和规则返回时间 默认格式：yyyy-MM-dd HH:mm:ss
     *
     * @author xun.gong
     * @param date
     * @param reg
     * @return
     */
    public static Date parseDateTime(String date, String reg)
    {
        if (isEmpty(date))
        {
            return null;
        }
        if (isEmpty(reg))
        {
            reg = "yyyy-MM-dd HH:mm:ss";
        }
        try
        {
            return new SimpleDateFormat(reg).parse(date);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * 返回当前时间字符串，默认格式：yyyy-MM-dd HH:mm:ss
     *
     * @author xun.gong
     * @param reg
     * @return
     */
    public static String getCurrDateTime(String reg)
    {
        return formatDate(new Date(), reg);
    }

    /**
     *
     * 格式化时间 输出
     *
     * @author xun.gong
     * @param date
     * @param reg
     * @return
     */
    public static String formatDate(Date date, String reg)
    {
        if (isEmpty(reg))
        {
            reg = "yyyy-MM-dd HH:mm:ss";
        }
        return new SimpleDateFormat(reg).format(date);
    }

    /**
     *
     * 计算年份差,结果总是以正整数返回 desDate为空返回0; srcDate为空则默认指定为当前时间
     *
     * @author xun.gong
     * @param desDate
     * @param srcDate
     * @return
     */
    public static int calculateForYear(Date desDate, Date srcDate)
    {
        if (isEmpty(desDate))
        {
            return 0;
        }
        if (isEmpty(srcDate))
        {
            srcDate = new Date();
        }
        Calendar des = new GregorianCalendar();
        des.setTime(desDate);
        Calendar src = new GregorianCalendar();
        src.setTime(srcDate);
        return Math.abs(src.get(Calendar.YEAR) - des.get(Calendar.YEAR)) + 1;
    }

    /**
     * 验证手机号码，11位数字，1开通，第二位数必须是3456789这些数字之一 *
     *
     * @param mobileNumber
     * @return
     */
    public static boolean checkMobileNumber(String mobileNumber) {
        boolean flag = false;
        try {
            Pattern regex = Pattern.compile("^1[345789]\\d{9}$");
            Matcher matcher = regex.matcher(mobileNumber);
            flag = matcher.matches();
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;

        }
        return flag;
    }


    /**
     *
     * TODO solr替换通用符
     *
     * @author lu.xiao
     * @param str
     * @return
     */
    public static String Replace(String str)
    {
        if(null != str && !"".equals(str))
        {
            str=str.replaceAll("\\(","\\?");
            str=str.replaceAll("\\)","\\?");
            str=str.replaceAll("\\-","\\?");
            str=str.replaceAll(" ","\\?");
            str=str.replaceAll("\\,","\\?");
            str=str.replaceAll("\\.","\\?");
            str=str.replaceAll("\\|","\\?");
            str=str.replaceAll("\\_","\\?");
            str=str.replaceAll("\\@","\\?");
            str=str.replaceAll("\\#","\\?");
            str=str.replaceAll("\\/","\\?");
            str=str.replaceAll("\\+","\\?");
            str=str.replaceAll("\\'","\\?");
            str=str.replaceAll("\\:","\\?");
            str=str.replaceAll("\\;","\\?");
            str=str.replaceAll("\\[","\\?");
            str=str.replaceAll("\\]","\\?");
            str=str.replaceAll("\\{","\\?");
            str=str.replaceAll("\\}","\\?");
            str=str.replaceAll("\\<","\\?");
            str=str.replaceAll("\\>","\\?");
            str=str.replaceAll("\\`","\\?");
            str=str.replaceAll("\\!","\\?");
            str=str.replaceAll("\\。","\\?");
            str=str.replaceAll("\\【","\\?");
            str=str.replaceAll("\\】","\\?");
            str=str.replaceAll("\\{","\\?");
            str=str.replaceAll("\\}","\\?");
            str=str.replaceAll("\\；","\\?");
            str=str.replaceAll("\\：","\\?");
            str=str.replaceAll("\\‘","\\?");
            str=str.replaceAll("\\“","\\?");
            str=str.replaceAll("\\”","\\?");
            str=str.replaceAll("\\》","\\?");
            str=str.replaceAll("\\《","\\?");
            str=str.replaceAll("\\，","\\?");
            str=str.replaceAll("\\！","\\?");
            str=str.replaceAll("\\·","\\?");
            str=str.replaceAll("\\’","\\?");
            str=str.replaceAll("\\‘","\\?");
            str=str.replaceAll("\\（","\\?");
            str=str.replaceAll("\\）","\\?");
        }

        return str;
    }
    /**
     *
     * 转换folat格式为00.00
     *
     * @author yuxin.zhou
     * @param f
     * @return
     */
    public static String matchFloat(Float f)
    {
        Float ft=new Float(f*100);
        Integer i=ft.intValue();
        f=(float)i/(float)100;
        if(f==0.0)
        {
            return "0";
        }else if((f+"").endsWith("0"))
        {
            return f.intValue()+"";
        }else
        {
            return f+"";
        }
    }

}
