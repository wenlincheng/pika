package com.wenlincheng.pika.common.core.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 正则匹配
 */
public class RegularUtil {

    private static ObjectMapper mapper = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger(RegularUtil.class);
    public static int PHONE_NUM_LENGTH = 11;
    public static String STAR = "****";
    public static final String mobile_pattern = "((110)|(170)|(177)|(145)|(147)|(13[0-9])|(15[0-9])|(18[0-9]))\\d{8}";
    public static final String exact_mobile_pattern = "^(?:(?:(?:(?:(?:(?:13[0-9])|(?:14[57])|(?:15[0-35-9])|(?:17[36-8])|(?:18[0-9]))\\d{8})|(?:170[057-9]\\d{7})|(?:\\(\\d{3,4}\\)|\\d{3,4}-)?\\d{7,8}(?:-\\d{1,4})?)[,\\s、])+)?(?:(?:(?:(?:13[0-9])|(?:14[57])|(?:15[0-35-9])|(?:17[36-8])|(?:18[0-9]))\\d{8})|(?:170[057-9]\\d{7})|(?:\\(\\d{3,4}\\)|\\d{3,4}-)?\\d{7,8}(?:-\\d{1,4})?)$";
    //public static final String exact_mobile_pattern="^[1][3-9][0-9]{9}$";
    public static final String exact_mobile_pattern_with_110 = "^((110)|(170)|(177)|(145)|(147)|(13[0-9])|(15[0-9])|(18[0-9]))\\d{8}$";
    public static final String ID_CARD_HIDDEN = "^(.{1})(?:\\d+)(.{1})$";





    /**
     * 密码（6位以上字母加数字）
     */
    public static final String password_pattern = "^[a-zA-Z0-9]{6,}$";

    /**
     * 中文正则
     */
    public static final String chinese = "^[\u4E00-\u9FA5]{0,}$";

    /**
     * 数字正则
     */
    public static final String digit = "^[0-9]+.*[0-9]*$";
    /**
     * 银行卡号正则
     */
    public static final String bankCard = "^(\\d{16}|\\d{19})$";


    /**
     * 身份证号卡号正则
     */
    public static final String identity_Card = "^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$";

    /**
     * 链接
     */
    public static final String url = "((http[s]{0,1}|ftp)://[a-zA-Z0-9\\.\\-]+\\.([a-zA-Z]{2,4})(:\\d+)?(/[a-zA-Z0-9\\.\\-~!@#$%^&*+?:_/=<>]*)?)|(www.[a-zA-Z0-9\\.\\-]+\\.([a-zA-Z]{2,4})(:\\d+)?(/[a-zA-Z0-9\\.\\-~!@#$%^&*+?:_/=<>]*)?)";

    /**
     * 时间
     */
    public static final String date="[0-9]{4}-[0-9]{2}-[0-9]{2}";

}
