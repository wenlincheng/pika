package com.wenlincheng.pika.common.core.util.compare;

import com.google.common.collect.Maps;
import com.wenlincheng.pika.common.core.util.CheckUtils;
import com.wenlincheng.pika.common.core.util.DateUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 类属性值比较
 *
 * @author wenlincheng
 * @version 1.0
 * @date 2020/05/13 12:39 下午
 */
@Slf4j
public class ClassCompareUtils {

    /**
     * 比较两个类的属性值变化
     *
     * @param before 比较前
     * @param after  比较后
     * @return List<String> 比较结果
     */
    public static List<String> compareClass(Object before, Object after) {
        if (before == null || after == null) {
            return new ArrayList<>();
        }
        Map<String, String> map = Maps.newHashMap();
        //获得对象所有属性
        Field[] fields = before.getClass().getDeclaredFields();
        //获得对象所有属性
        Field[] fields2 = after.getClass().getDeclaredFields();
        try {
            // 遍历所有属性
            for (int j = 0; j < fields.length; j++) {
                // 获取属性的名字
                String name = fields[j].getName();
                // 将属性的首字符大写，方便构造get，set方法
                name = name.substring(0, 1).toUpperCase() + name.substring(1);
                // 获取属性的类型
                String type = fields[j].getGenericType().toString();
                // 获取属性的名字
                String name2 = fields2[j].getName();
                // 将属性的首字符大写，方便构造get，set方法
                name2 = name2.substring(0, 1).toUpperCase() + name2.substring(1);
                // 获取属性的类型
                String type2 = fields2[j].getGenericType().toString();
                // 获取字段中包含fieldMeta的注解
                FieldMeta meta = fields[j].getAnnotation(FieldMeta.class);
                if (null == meta) {
                    continue;
                }
                if (meta.isShow() == -1) {
                    continue;
                }
                if (name2.equals(name)) {
                    // 如果type是类类型，则前面包含"class "，后面跟类名
                    if ("class java.lang.String".equals(type) && "class java.lang.String".equals(type2)) {
                        Method m = before.getClass().getMethod("get" + name);
                        // 调用getter方法获取属性值
                        String value = (String) m.invoke(before);
                        Method m2 = after.getClass().getMethod("get" + name);
                        // 调用getter方法获取属性值
                        String value2 = (String) m2.invoke(after);
                        if (m.getName().equals(m2.getName())) {
                            //如果都为空 就是没有修改 不作记录
                            if (CheckUtils.isEmpty(value2)) {
                                continue;
                            } else if (CheckUtils.isEmpty(value)) {
                                //如果修改前或者修改后为空
                                String str = "之前的值为" + (CheckUtils.isEmpty(value) ? "空" : value) + ",修改之后为" + value2;
                                String key = meta.name();
                                map.put(key, str);
                            } else if (!value.equals(value2)) {
                                //如果都不为空 则判断是否是一样的属性值
                                String str = "之前的值为" + (CheckUtils.isEmpty(value) ? "空" : value) + ",修改之后为" + value2;
                                String key = meta.name();
                                map.put(key, str);
                            }
                        }
                    }
                    boolean isInt = ("int".equals(type) && "int".equals(type2)) || ("class java.lang.Integer".equals(type) && "class java.lang.Integer".equals(type2));
                    if (isInt) {
                        Method m = before.getClass().getMethod("get" + name);
                        int value = (int) m.invoke(before);
                        Method m2 = after.getClass().getMethod("get" + name);
                        // 调用getter方法获取属性值
                        int value2 = (int) m2.invoke(after);
                        if (value2 == 0) {
                            continue;
                        } else if (value != value2) {
                            String beforeValue = "";
                            String afterValue = "";
                            if (meta.isEnum() == 1) {
                                try {
                                    Class classObject = Class.forName(meta.enumStr());
                                    if (CheckUtils.isNotEmpty(meta.innerEnumName())) {
                                        Class[] cs = classObject.getDeclaredClasses();
                                        for (Class c : cs) {
                                            if (c.getSimpleName().contains(meta.innerEnumName())) {
                                                classObject = c;
                                            }
                                        }
                                    }
                                    Method getValue = classObject.getMethod("getValue");
                                    Method getDesc = classObject.getMethod("getDesc");
                                    Enum[] objs = (Enum[]) classObject.getEnumConstants();
                                    for (Enum obj : objs) {
                                        int beforeValueEnum = (Integer) getValue.invoke(obj);
                                        int afterValueEnum = (Integer) getValue.invoke(obj);
                                        if (value == beforeValueEnum) {
                                            beforeValue = (String) getDesc.invoke(obj);
                                        }
                                        if (value2 == afterValueEnum) {
                                            afterValue = (String) getDesc.invoke(obj);
                                        }
                                    }
                                } catch (ClassNotFoundException e) {
                                    log.error("ClassCompareUtils 反射获取枚举失败,msg:{}", e.getMessage(), e);
                                    // 反射失败 直接赋值
                                    beforeValue = value + "";
                                    afterValue = value2 + "";
                                }
                            } else {
                                beforeValue = value + "";
                                afterValue = value2 + "";
                            }
                            String str = "之前的值为" + (CheckUtils.isEmpty(beforeValue) ? "空" : beforeValue) + ",修改之后为" + afterValue;
                            String key = meta.name();
                            map.put(key, str);
                        }
                    }
                    if (type.equals("long") && type2.equals("long")) {
                        Method m = before.getClass().getMethod("get" + name);
                        long value = (long) m.invoke(before);
                        Method m2 = after.getClass().getMethod("get" + name);
                        // 调用getter方法获取属性值
                        long value2 = (long) m2.invoke(after);
                        if (value2 == 0) {
                            continue;
                        } else if (value != value2) {
                            String str = "之前的值为" + (CheckUtils.isEmpty(value) ? "空" : value) + ",修改之后为" + value2;
                            String key = meta == null ? m2.getName() : meta.name();
                            map.put(key, str);
                        }
                    }
                    if (type.equals("class java.math.BigDecimal") && type2.equals("class java.math.BigDecimal")) {
                        Method m = before.getClass().getMethod("get" + name);
                        BigDecimal value = ((BigDecimal) m.invoke(before)).setScale(6, BigDecimal.ROUND_HALF_UP);
                        Method m2 = after.getClass().getMethod("get" + name);
                        // 调用getter方法获取属性值
                        BigDecimal value2 = ((BigDecimal) m2.invoke(after));
                        if (null == value2) {
                            continue;
                        }
                        value2 = value2.setScale(6, BigDecimal.ROUND_HALF_UP);
                        if (value2.doubleValue() == 0) {
                            continue;
                        } else if (value.doubleValue() != value2.doubleValue()) {
                            String str = "之前的值为" + (CheckUtils.isEmpty(value) ? "空" : value.toPlainString()) + ",修改之后为" + value2.toPlainString();
                            String key = meta == null ? m2.getName() : meta.name();
                            map.put(key, str);
                        }
                    }
                    if (type.equals("class java.util.Date") && type2.equals("class java.util.Date")) {
                        Method m = before.getClass().getMethod("get" + name);
                        Date value = (Date) m.invoke(before);
                        Method m2 = after.getClass().getMethod("get" + name);
                        // 调用getter方法获取属性值
                        Date value2 = (Date) m2.invoke(after);
                        if (value2 == null || value == null) {
                            continue;
                        } else if (!DateUtils.date2StringBySecond(value).equals(DateUtils.date2StringBySecond(value2))) {
                            String str = "之前的值为" + (value == null ? null : DateUtils.date2StringBySecond(value)) + ",修改之后为" + (value2 == null ? null : DateUtils.date2StringBySecond(value2));
                            String key = meta == null ? m2.getName() : meta.name();
                            map.put(key, str);
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("ClassCompareUtils 对象对比失败,msg:{}", e.getMessage(), e);
            e.printStackTrace();
        }
        List<String> list = new ArrayList<>();
        //遍历map 找出修改的属性
        for (Map.Entry<String, String> entry : map.entrySet()) {
            StringBuilder sb = new StringBuilder();
            sb.append(entry.getKey());
            sb.append(entry.getValue());
            list.add(sb.toString());
        }
        return list;
    }
}
