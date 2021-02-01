package com.wenlincheng.pika.common.core.util;


import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * BigDecimal 工具类
 *
 * @author  wenlincheng
 * @date    2020/05/13 12:39 下午
 * @version 1.0
 */
public class BigDecimalUtils {

    /**
     * 默认除法运算精度
     */
    private static final int DEF_DIV_SCALE = 2;

    /**
     * 零 两位小数点
     */
    private static final String ZERO_TWO_POINT = "0.00";

    /**
     * 提供精确的加法运算
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供精确的减法运算
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供精确的乘法运算
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static double multiply(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
     * <p>
     * 小数点以后10位，以后的数字四舍五入
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static double div(double v1, double v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
     * <p>
     * 小数点以后10位，以后的数字四舍五入
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static String div(BigDecimal v1, BigDecimal v2) {
        return roundAndScale2ToString(v1.divide(v2, DEF_DIV_SCALE));
    }

    /**
     * 提供（相对）精确的除法运算当发生除不尽的情况时，由scale参数指
     * <p>
     * 定精度，以后的数字四舍五入
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示表示需要精确到小数点以后几位
     * @return 两个参数的商
     */
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供精确的小数位四舍五入处理
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 不保留小数点
     *
     * @param bigDecimal 需要四舍五入的数字
     * @return String
     */
    public static String round(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return "0";
        }
        return bigDecimal.setScale(0, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 四舍五入保留2小数点返回字符串值
     *
     * @param bigDecimal 需要四舍五入的数字
     * @return String
     */
    public static String roundAndScale2ToString(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return ZERO_TWO_POINT;
        }
        return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 四舍五入保留几位小数点 (默认scale小于0时 默认为0)
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return String
     */
    public static String roundDouble2String(double v, int scale) {
        if (v == 0) {
            return "0";
        }
        if (scale < 0) {
            scale = 0;
        }
        return new BigDecimal(v).setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 四舍五入保留相对于小数点
     *
     * @param bigDecimal 需要四舍五入的数字
     * @param scale      小数点后保留几位
     * @return String
     */
    public static String round(BigDecimal bigDecimal, int scale) {
        if (bigDecimal == null) {
            return ZERO_TWO_POINT;
        }
        return bigDecimal.setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * bigDecimal1*100
     *
     * @param bigDecimal1 bigDecimal1
     * @return String
     */
    public static String multiplyRoundHalfUp100(BigDecimal bigDecimal1) {
        if (bigDecimal1 == null) {
            return "0";
        }
        return multiplyRoundHalfUp(bigDecimal1, new BigDecimal(100), 0);
    }

    /**
     * bigDecimal1乘法,默认保留2位小数
     *
     * @param bigDecimal1 bigDecimal1
     * @param bigDecimal2 bigDecimal2
     * @return String
     */
    public static String multiplyRoundHalfUp(BigDecimal bigDecimal1, BigDecimal bigDecimal2) {
        if (bigDecimal1 == null || bigDecimal2 == null) {
            return ZERO_TWO_POINT;
        }
        return bigDecimal1.multiply(bigDecimal2).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * bigDecimal1乘法,保留整数
     * @MethodName: multiplyRoundHalfUp2BigDecimal
     * @param bigDecimal1
     * @param bigDecimal2
     * @Return: java.math.BigDecimal
     * @Author: wenlincheng
     * @Date: 2019-06-13 11:34
     */
    public static BigDecimal multiplyRoundHalfUp2BigDecimal(BigDecimal bigDecimal1, BigDecimal bigDecimal2) {
        if (bigDecimal1 == null || bigDecimal2 == null) {
            return new BigDecimal(0.00);
        }
        return bigDecimal1.multiply(bigDecimal2).setScale(0, BigDecimal.ROUND_HALF_UP);
    }
    
    /**
     * 乘法 保留整数
     *
     * @param bigDecimal1
     * @param bigDecimal2
     * @return java.math.BigDecimal
     */
    public static BigDecimal multiplyRoundHalfUp2ScaleBigDecimal(BigDecimal bigDecimal1, BigDecimal bigDecimal2) {
        if (bigDecimal1 == null || bigDecimal2 == null) {
            return new BigDecimal(ZERO_TWO_POINT);
        }
        return bigDecimal1.multiply(bigDecimal2).setScale(2, BigDecimal.ROUND_HALF_UP);
    }


    /**
     * 乘法
     *
     * @param bigDecimal1 bigDecimal1
     * @param bigDecimal2 bigDecimal2
     * @param scale       精度
     * @return String
     */
    public static String multiplyRoundHalfUp(BigDecimal bigDecimal1, BigDecimal bigDecimal2, int scale) {
        if (bigDecimal1 == null || bigDecimal2 == null) {
            return "0";
        }
        if (scale < 0) {
            scale = 0;
        }
        return bigDecimal1.multiply(bigDecimal2).setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 提供精确的减法运算默认保留2位精度
     *
     * @param bigDecimal1 被减数
     * @param bigDecimal2 减数
     * @return 两个参数的差
     */
    public static String subToString(BigDecimal bigDecimal1, BigDecimal bigDecimal2) {
        return bigDecimal1.subtract(bigDecimal2).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 提供精确的减法运算默认保留2位精度
     *
     * @param bigDecimal1 被减数
     * @param bigDecimal2 减数
     * @return 两个参数的差
     */
    public static BigDecimal sub(BigDecimal bigDecimal1, BigDecimal bigDecimal2) {
        if (bigDecimal1 == null) {
            bigDecimal1 = new BigDecimal(ZERO_TWO_POINT);
        }
        if (bigDecimal2 == null) {
            bigDecimal2 = new BigDecimal(ZERO_TWO_POINT);
        }
        return bigDecimal1.subtract(bigDecimal2).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 提供精确的加法运算默认保留2位精度
     *
     * @param bigDecimal1 被加数
     * @param bigDecimal2 加数
     * @return 两个参数的和
     */
    public static BigDecimal add(BigDecimal bigDecimal1, BigDecimal bigDecimal2) {
        if (bigDecimal1 == null) {
            bigDecimal1 = new BigDecimal(ZERO_TWO_POINT);
        }
        if (bigDecimal2 == null) {
            bigDecimal2 = new BigDecimal(ZERO_TWO_POINT);
        }
        return bigDecimal1.add(bigDecimal2).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 保留2小数点
     *
     * @param bigDecimal 需要四舍五入的数字
     * @return String
     */
    public static BigDecimal roundAndScale2(BigDecimal bigDecimal) {
        if (bigDecimal == null || bigDecimal.compareTo(new BigDecimal(0.00)) <= 0) {
            return new BigDecimal(0.00);
        }
        return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 校验参数
     *
     * @param bigDecimal bigDecimal
     * @return BigDecimal
     */
    public static BigDecimal checkBigDecimal(BigDecimal bigDecimal) {
        if (bigDecimal == null || bigDecimal.compareTo(new BigDecimal(ZERO_TWO_POINT)) <= 0) {
            return new BigDecimal(ZERO_TWO_POINT);
        }
        return bigDecimal;
    }

    /**
     * 校验参数
     *
     * @param bigDecimal bigDecimal
     * @return BigDecimal
     */
    public static String checkBigDecimalToString(BigDecimal bigDecimal) {
        if (bigDecimal == null || bigDecimal.compareTo(new BigDecimal(ZERO_TWO_POINT)) <= 0) {
            return ZERO_TWO_POINT;
        }
        return roundAndScale2ToString(bigDecimal);
    }

    /**
     * 比较大小
     *
     * @param bigDecimal1 bigDecimal1
     * @param bigDecimal2 bigDecimal2
     * @return boolean
     */
    public static boolean compare(BigDecimal bigDecimal1, BigDecimal bigDecimal2) {
        return bigDecimal1.compareTo(bigDecimal2) > 0;
    }

    /**
     * 格式化数字   默认保留两位小数
     * 如果最后一位是0 则保留一位小数
     * 如果最后两位都是0 则不保留小数
     *
     * @param bigDecimal bigDecimal
     * @return String
     */
    public static String whetherRetainPoint(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return "0";
        }
        if (bigDecimal.compareTo(new BigDecimal(0)) <= 0) {
            return "0";
        }
        DecimalFormat decimalFormat = new DecimalFormat("###.##");
        return decimalFormat.format(bigDecimal.doubleValue());
    }

    /**
     * 格式化数字 成4位整数
     *
     * @param number number
     * @return String
     */
    public static String decimalFormatTo4(Number number) {
        if (number == null || number.intValue() == 0) {
            return "0000";
        }
        return decimalFormat(number,"0000");
    }

    /**
     * 格式化数字 成4位整数
     *
     * @param number number
     * @return String
     */
    public static String decimalFormatTo8(Number number) {
        if (number ==null || number.intValue() == 0) {
            return "00000000";
        }
        return decimalFormat(number,"00000000");
    }

    /**
     * 格式化数字
     *
     * @param number number
     * @return String
     */
    public static String decimalFormat(Number number,String pattern) {
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(number);
    }

    /**
     * 保留2小数点
     *
     * @param bigDecimal 需要四舍五入的数字
     * @return String
     */
    public static String round2Scale(BigDecimal bigDecimal) {
        if (bigDecimal == null || bigDecimal.compareTo(new BigDecimal(ZERO_TWO_POINT)) <= 0) {
            return new BigDecimal(ZERO_TWO_POINT).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        }
        return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 保留2小数点
     *
     * @param bigDecimal 需要四舍五入的数字
     * @return String
     */
    public static double round2DoubleScale(BigDecimal bigDecimal) {
        if (bigDecimal == null || bigDecimal.compareTo(new BigDecimal(ZERO_TWO_POINT)) <= 0) {
            return 0.00;
        }
        return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 保留1小数点
     *
     * @param bigDecimal 需要四舍五入的数字
     * @return String
     */
    public static String round1Scale(BigDecimal bigDecimal) {
        if (bigDecimal == null || bigDecimal.compareTo(new BigDecimal(ZERO_TWO_POINT)) <= 0) {
            return new BigDecimal(ZERO_TWO_POINT).setScale(1, BigDecimal.ROUND_HALF_UP).toString();
        }
        return bigDecimal.setScale(1, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 保留0小数点 (不保留小数)
     *
     * @param bigDecimal 需要四舍五入的数字
     * @return String
     */
    public static String round0Scale(BigDecimal bigDecimal) {
        if (bigDecimal == null || bigDecimal.compareTo(new BigDecimal(ZERO_TWO_POINT)) <= 0) {
            return new BigDecimal(ZERO_TWO_POINT).setScale(0, BigDecimal.ROUND_HALF_UP).toString();
        }
        return bigDecimal.setScale(0, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 保留0小数点 (不保留小数)
     *
     * @param bigDecimal 需要四舍五入的数字
     * @return BigDecimal
     */
    public static BigDecimal round0Scale2(BigDecimal bigDecimal) {
        if (bigDecimal == null || bigDecimal.compareTo(new BigDecimal(ZERO_TWO_POINT)) <= 0) {
            return new BigDecimal(ZERO_TWO_POINT).setScale(0, BigDecimal.ROUND_HALF_UP);
        }
        return bigDecimal.setScale(0, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 字符窜转BigDecimal
     *
     * @param value bigDecimal
     * @return String
     */
    public static BigDecimal stringToBigDecimal(String value) {
        if (CheckUtils.isEmpty(value)) {
            value = ZERO_TWO_POINT;
        }
        return new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal bigDecimalRoundDown(BigDecimal bigDecimal)
    {
        if (bigDecimal == null || bigDecimal.compareTo(new BigDecimal(ZERO_TWO_POINT)) <= 0) {
            return new BigDecimal(ZERO_TWO_POINT);
        }
        return bigDecimal.setScale(0, BigDecimal.ROUND_DOWN);
    }

//    public static void main(String[] args) {
//        BigDecimal b=new BigDecimal("2.00");
//        BigDecimal a=new BigDecimal("2.00");
//        double b2=b.doubleValue();
//        System.out.println(System.currentTimeMillis() - DateUtils.string2Date("2018-01-01").getTime());
//        System.out.println(a.compareTo(b)<0);
//        System.out.println(!BigDecimalUtils.compare(new BigDecimal(ZERO_TWO_POINT),new BigDecimal(ZERO_TWO_POINT)));
//
//        System.out.println(new BigDecimal("1").doubleValue());
//    }
}
