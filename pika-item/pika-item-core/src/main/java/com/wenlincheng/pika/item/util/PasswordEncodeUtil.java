package com.wenlincheng.pika.item.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 密码加密
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
public class PasswordEncodeUtil {

    /**
     * 加密
     *
     * @param password 明文
     * @return java.lang.String 密文
     */
    public static String encode(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);
        return encoder.encode(password);
    }

    public static void main(String[] args) {
        String encode = PasswordEncodeUtil.encode("123456");
        System.out.println(encode);
    }

}
