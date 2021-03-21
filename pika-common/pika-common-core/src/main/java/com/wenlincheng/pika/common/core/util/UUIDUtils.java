package com.wenlincheng.pika.common.core.util;

import java.util.UUID;

/**
 * 随机id
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/3/21 9:11 下午
 */
public class UUIDUtils {

    /**
     *32位默认长度的uuid
     * (获取32位uuid)
     *
     * @return
     */
    public static  String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * (获取指定长度uuid)
     *
     * @return
     */
    public static String getUUID(int len) {
        if (0 >= len) {
            return null;
        }
        String uuid = getUUID();
        StringBuffer str = new StringBuffer();

        for (int i = 0; i < len; i++) {
            str.append(uuid.charAt(i));
        }

        return str.toString();
    }
}
