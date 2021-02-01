package com.wenlincheng.pika.schedule.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Cron工具类
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
public class CronUtils {

    private static final String CRON_DATE_FORMAT = "ss mm HH dd MM ? yyyy";

    /***
     * @param date 时间
     * @return String cron类型的日期
     */
    public static String getCron(final Date date) {
        SimpleDateFormat fmt = new SimpleDateFormat(CRON_DATE_FORMAT);
        String formatTimeStr = "";
        if (date != null) {
            formatTimeStr = fmt.format(date);
        }
        return formatTimeStr;
    }

    public static void main(String[] args) {
        String cron = CronUtils.getCron(new Date());
        System.out.println(cron);
    }
}
