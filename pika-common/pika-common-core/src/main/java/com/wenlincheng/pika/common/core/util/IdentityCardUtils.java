package com.wenlincheng.pika.common.core.util;


import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: IdentityCardUtils
 * @Description: 身份证工具类
 * @Author: wenlincheng
 * @Date: 2019/5/14 17:16
 * @Version: 1.0.0
 */
public class IdentityCardUtils {

    /**
     * 通过身份证号码获取出生日期、性别、年龄
     *
     * @param certificateNo 身份证号
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
    public static Map<String, String> getBirAgeSex(String certificateNo) {
        String birthday = "";
        String age = "";
        String sexCode = "";

        int year = Calendar.getInstance().get(Calendar.YEAR);
        char[] number = certificateNo.toCharArray();
        boolean flag = true;
        // 除了最后一位是否都为数字
        if (number.length == 15) {
            for (int x = 0; x < number.length; x++) {
                if (!flag) {
                    return new HashMap<>();
                }
                flag = Character.isDigit(number[x]);
            }
        } else if (number.length == 18) {
            for (int x = 0; x < number.length - 1; x++) {
                if (!flag) {
                    return new HashMap<>();
                }
                flag = Character.isDigit(number[x]);
            }
        }
        if (flag && certificateNo.length() == 15) {
            birthday = "19" + certificateNo.substring(6, 8) + "-"
                    + certificateNo.substring(8, 10) + "-"
                    + certificateNo.substring(10, 12);
            sexCode = Integer.parseInt(certificateNo.substring(certificateNo.length() - 3, certificateNo.length())) % 2 == 0 ? "女" : "男";
            age = (year - Integer.parseInt("19" + certificateNo.substring(6, 8))) + "";
        } else if (flag && certificateNo.length() == 18) {
            birthday = certificateNo.substring(6, 10) + "-"
                    + certificateNo.substring(10, 12) + "-"
                    + certificateNo.substring(12, 14);
            sexCode = Integer.parseInt(certificateNo.substring(certificateNo.length() - 4, certificateNo.length() - 1)) % 2 == 0 ? "女" : "男";
            age = (year - Integer.parseInt(certificateNo.substring(6, 10))) + "";
        }
        Map<String, String> map = new HashMap<>(3);
        map.put("birthday", birthday);
        map.put("age", age);
        map.put("sexCode", sexCode);

        return map;
    }

    /**
     *
     * 获取性别
     *
     * @param certificateNo 身份证号
     * @return int 性别 0 未知 1 男 2 女
     */
    public static int getGender(String certificateNo) {

        int gender = 0;

        char[] number = certificateNo.toCharArray();
        boolean flag = true;
        // 除了最后一位是否都为数字
        if (number.length == 15) {
            for (int x = 0; x < number.length; x++) {
                if (!flag) {
                    return gender;
                }
                flag = Character.isDigit(number[x]);
            }
        } else if (number.length == 18) {
            for (int x = 0; x < number.length - 1; x++) {
                if (!flag) {
                    return gender;
                }
                flag = Character.isDigit(number[x]);
            }
        }

        if (flag && certificateNo.length() == 18) {
            gender = Integer.parseInt(certificateNo.substring(certificateNo.length() - 4, certificateNo.length() - 1)) % 2 == 0 ? 2 : 1;

        } else if (flag && certificateNo.length() == 15) {
            gender = Integer.parseInt(certificateNo.substring(certificateNo.length() - 3)) % 2 == 0 ? 2 : 1;
        }

        return gender;
    }

//    public static void main(String[] args) {
//
//        System.out.println(GenderEnum.MEN.getDesc().equals(IdentityCardUtils.getGender("362201199503305415")) ? GenderEnum.MEN.getValue() : GenderEnum.WOMEN.getValue());
//
//
//    }

}
