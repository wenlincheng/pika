package com.wenlincheng.pika.common.core.util;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 处理日期时间的工具类
 *
 * @author  wenlincheng
 * @date    2020/05/13 12:39 下午
 * @version 1.0
 */
public class DateUtils {

    private static final int[] DAY_OF_MONTH = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    /**
     * 取得指定年数后的时间
     *
     * @param date       基准时间
     * @param yearAmount 指定年数，允许为负数
     * @return 指定年数后的时间
     */
    public static Date addYear(Date date, int yearAmount) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, yearAmount);
        return calendar.getTime();
    }

    /**
     * 取得指定年数后的时间
     *
     * @param date        基准时间
     * @param monthAmount 指定月数，允许为负数
     * @return 指定年数后的时间
     */
    public static Date addMonth(Date date, int monthAmount) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, monthAmount);
        return calendar.getTime();
    }

    /**
     * 取得指定天数后的时间
     *
     * @param date      基准时间
     * @param dayAmount 指定天数，允许为负数
     * @return 指定天数后的时间
     */
    public static Date addDay(Date date, int dayAmount) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, dayAmount);
        return calendar.getTime();
    }

    /**
     * 取得指定小时数后的时间
     *
     * @param date       基准时间
     * @param hourAmount 指定小时数，允许为负数
     * @return 指定小时数后的时间
     */
    public static Date addHour(Date date, int hourAmount) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, hourAmount);
        return calendar.getTime();
    }

    /**
     * 取得指定小时数后的时间
     *
     * @param date       基准时间
     * @param hourAmount 指定小时数，允许为负数
     * @return 指定小时数后的时间
     */
    public static Date addHour(Date date, double hourAmount) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        DecimalFormat df1 = new DecimalFormat("###.0");
        String time = df1.format(hourAmount);
        String[] timelist = time.split("\\.");
        int hour = Integer.parseInt(timelist[0]);
        calendar.add(Calendar.HOUR, hour);
        if (timelist.length > 1) {
            //10分制数字，转为时间的分钟
            int minute = Integer.parseInt(timelist[1]) * 60 / 10;
            calendar.add(Calendar.MINUTE, minute);
        }
        return calendar.getTime();
    }

    /**
     * 取得指定分钟数后的时间
     *
     * @param date         基准时间
     * @param minuteAmount 指定分钟数，允许为负数
     * @return 指定分钟数后的时间
     */
    public static Date addMinute(Date date, int minuteAmount) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minuteAmount);
        return calendar.getTime();
    }


    public static double compareHour(Date date, Date anotherDate) {
        if (date == null) {
            date = new Date();
        }
        if (anotherDate == null) {
            anotherDate = new Date();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hourOfDay1 = cal.get(Calendar.HOUR_OF_DAY);
        int day1 = cal.get(Calendar.DAY_OF_YEAR);
        int minute1 = cal.get(Calendar.MINUTE);
        cal.setTime(anotherDate);
        int hourOfDay2 = cal.get(Calendar.HOUR_OF_DAY);
        int day2 = cal.get(Calendar.DAY_OF_YEAR);
        int minute2 = cal.get(Calendar.MINUTE);
        double reuslt = 0;
        int days = day1 - day2;
        //当天
        if (days == 0) {
            reuslt = hourOfDay1 - hourOfDay2;
        }
        if (days > 0) {
            reuslt = (days - 1) * 12 + hourOfDay1 + (24 - hourOfDay2);
        }
        if (days < 0) {
            reuslt = -(((days * -1) - 1) * 12 + hourOfDay2 + (24 - hourOfDay1));
        }
        //加上分钟
        reuslt = reuslt + (0.5 * minute1 / 30) - (0.5 * minute2 / 30);
        return reuslt;
    }

    public static int compareDay(Date date, Date anotherDate) {
        if (date == null) {
            date = new Date();
        }
        if (anotherDate == null) {
            anotherDate = new Date();
        }
        Calendar aCalendar = Calendar.getInstance();
        Calendar bCalendar = Calendar.getInstance();
        aCalendar.setTime(date);
        bCalendar.setTime(anotherDate);
        int days = 0;
        while (aCalendar.before(bCalendar)) {
            days++;
            aCalendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        return days;
    }

    /**
     * 比较两日期对象的大小, 忽略秒, 只精确到分钟.
     *
     * @param date        日期对象1, 如果为 <code>null</code> 会以当前时间的日期对象代替
     * @param anotherDate 日期对象2, 如果为 <code>null</code> 会以当前时间的日期对象代替
     * @return 如果日期对象1大于日期对象2, 则返回大于0的数; 反之返回小于0的数; 如果两日期对象相等, 则返回0.
     */
    public static int compareIgnoreSecond(Date date, Date anotherDate) {
        if (date == null) {
            date = new Date();
        }
        if (anotherDate == null) {
            anotherDate = new Date();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        date = cal.getTime();
        cal.setTime(anotherDate);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        anotherDate = cal.getTime();
        return date.compareTo(anotherDate);
    }

    /**
     * 比较两日期对象的大小, 只比较年月日
     *
     * @param date        日期对象1, 如果为 <code>null</code> 会以当前时间的日期对象代替
     * @param anotherDate 日期对象2, 如果为 <code>null</code> 会以当前时间的日期对象代替
     * @return 如果日期对象1大于日期对象2, 则返回大于0的数; 反之返回小于0的数; 如果两日期对象相等, 则返回0.
     */
    public static int compareTimeByDay(Date date, Date anotherDate) {
        if (date == null) {
            date = new Date();
        }
        if (anotherDate == null) {
            anotherDate = new Date();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        date = cal.getTime();
        cal.setTime(anotherDate);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR, 0);
        anotherDate = cal.getTime();
        return date.compareTo(anotherDate);
    }

    /**
     * 取得当前时间的字符串表示，格式为2006-01-10 205630.756
     *
     * @return 当前时间的字符串表示
     */
    public static String currentDate2String() {
        return date2String(new Date());
    }

    /**
     * 取得当前时间的字符串表示，格式为2006-01-10 205630.756
     *
     * @return 当前时间的字符串表示
     */
    public static String currentDate2NumString() {
        return date2String(new Date(),"yyyyMMddHHmmssSSS");
    }

    /**
     * 取得当前时间的字符串表示，格式为2006-01-10 205630.756
     *
     * @return 当前时间的字符串表示
     */
    public static String currentDate2NumString2() {
        return date2String(new Date(),"yyyyMMddHHmmss");
    }

    /**
     * 取得当前时间的字符串表示，格式为01-10
     *
     * @return 当前时间的字符串表示
     */
    public static String currentDate2NumString3() {
        return date2String(new Date(),"MMdd");
    }

    /**
     * 取得时间的月和日数组
     *
     * @return 当前时间的字符串表示
     */
    public static String[] getDateMonthAndDay(Date date) {
        String date2String = date2String(date, "MM-dd");
        String[] split = date2String.split("-");
        if(split == null ||split.length < 2){
            return null;
        }
        return split;
    }
    /**
     * 取得当前时间的时间戳
     *
     * @return 当前时间的时间戳  long型
     */
    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 取得当前时间的字符串表示，格式为2006-01-10
     *
     * @return 当前时间的字符串表示
     */
    public static String currentDate2StringByDay() {
        return date2StringByDay(new Date());
    }

    /**
     * 取得今天的最后一个时刻
     *
     * @return 今天的最后一个时刻
     */
    public static Date currentEndDate() {
        return getEndDate(new Date());
    }

    /**
     * 取得今天的第一个时刻
     *
     * @return 今天的第一个时刻
     */
    public static Date currentStartDate() {
        return getStartDate(new Date());
    }

    /**
     * 把时间转换成字符串，格式为2006-01-10 205630.756
     *
     * @param date 时间
     * @return 时间字符串
     */
    public static String date2String(Date date) {
        return date2String(date, "yyyy-MM-dd HHmmss");
    }

    /**
     * 把时间转换成字符串，格式为2006-01-10 205630.756
     *
     * @param date 时间
     * @return 时间字符串
     */
    public static String date2StringByChina(Date date) {
        return date2String(date, "yyyy年MM月dd日");
    }

    /**
     * 把时间转换成字符串，格式为2006-01-10 205630.756
     *
     * @param date 时间
     * @return 时间字符串
     */
    public static String date2String2(Date date) {
        return date2String(date, "yyyy-MM-dd HHmmss.SSS");
    }

    public static final String date2String(long time, String pattern) {
        SimpleDateFormat f = new SimpleDateFormat(pattern);
        return f.format(time);
    }

    /**
     * 按照指定格式把时间转换成字符串，格式的写法类似yyyy-MM-dd HHmmss.SSS
     *
     * @param date    时间
     * @param pattern 格式
     * @return 时间字符串
     */
    public static String date2String(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        return (new SimpleDateFormat(pattern)).format(date);
    }

    /**
     * 把时间转换成字符串，格式为2006-01-10
     *
     * @param date 时间
     * @return 时间字符串
     */
    public static String date2StringByHour(Date date) {
        return date2String(date, "yyyy-MM-dd HH");
    }

    /**
     * 把时间转换成字符串，格式为2006-01-10
     *
     * @param date 时间
     * @return 时间字符串
     */
    public static String date2StringByDay(Date date) {
        return date2String(date, "yyyy-MM-dd");
    }

    /**
     * 把时间转换成字符串，格式为01/10
     *
     * @param date 时间
     * @return 时间字符串
     */
    public static String date2StringByDay2(Date date) {
        return date2String(date, "yyyy/MM/dd").substring(5);
    }
    /**
     * 把时间转换成字符串，格式为2017.01.23
     *
     * @param date 时间
     * @return 时间字符串 split
     */
    public static String date2StringByDaySplitPoint(Date date) {
        return date2String(date, "yyyy.MM.dd");
    }

    /**
     * 把时间转换成字符串，格式为01/10
     *
     * @param date 时间
     * @return 时间字符串
     */
    public static String date2StringByMonth(Date date) {
        return date2String(date, "yyyy/MM/dd").replace("/", "").substring(2, 6);
    }

    /**
     * 把时间转换成字符串，格式为2006-01-10 2056
     *
     * @param date 时间
     * @return 时间字符串
     */
    public static String date2StringByMinute(Date date) {
        return date2String(date, "yyyy-MM-dd HHmm");
    }

    /**
     * 把时间转换成字符串，格式为01-10 2056
     *
     * @param date 时间
     * @return 时间字符串
     */
    public static String date2ShortStringByMinute(Date date) {
        return date2String(date, "MM月dd日 HHmm");
    }

    /**
     * 把时间转换成字符串，格式为2006-01-10 205630
     *
     * @param date 时间
     * @return 时间字符串
     */
    public static String date2StringBySecond(Date date) {
        return date2String(date, "yyyy-MM-dd HHmmss");
    }

    /**
     * 根据某星期几的英文名称来获取该星期几的中文数. <br>
     * e.g. <li>monday -> 一</li> <li>sunday -> 日</li>
     *
     * @param englishWeekName 星期的英文名称
     * @return 星期的中文数
     */
    public static String getChineseWeekNumber(String englishWeekName) {
        if ("monday".equalsIgnoreCase(englishWeekName)) {
            return "一";
        }
        if ("tuesday".equalsIgnoreCase(englishWeekName)) {
            return "二";
        }
        if ("wednesday".equalsIgnoreCase(englishWeekName)) {
            return "三";
        }
        if ("thursday".equalsIgnoreCase(englishWeekName)) {
            return "四";
        }
        if ("friday".equalsIgnoreCase(englishWeekName)) {
            return "五";
        }
        if ("saturday".equalsIgnoreCase(englishWeekName)) {
            return "六";
        }
        if ("sunday".equalsIgnoreCase(englishWeekName)) {
            return "日";
        }
        return null;
    }

    /**
     * 根据指定的年, 月, 日等参数获取日期对象.
     *
     * @param year  年
     * @param month 月
     * @param date  日
     * @return 对应的日期对象
     */
    public static Date getDate(int year, int month, int date) {
        return getDate(year, month, date, 0, 0);
    }

    /**
     * 根据指定的年, 月, 日, 时, 分等参数获取日期对象.
     *
     * @param year      年
     * @param month     月
     * @param date      日
     * @param hourOfDay 时(24小时制)
     * @param minute    分
     * @return 对应的日期对象
     */
    public static Date getDate(int year, int month, int date, int hourOfDay, int minute) {
        return getDate(year, month, date, hourOfDay, minute, 0);
    }

    /**
     * 根据指定的年, 月, 日, 时, 分, 秒等参数获取日期对象.
     *
     * @param year      年
     * @param month     月
     * @param date      日
     * @param hourOfDay 时(24小时制)
     * @param minute    分
     * @param second    秒
     * @return 对应的日期对象
     */
    public static Date getDate(int year, int month, int date, int hourOfDay, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, date, hourOfDay, minute, second);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 取得某个日期是星期几，星期日是1，依此类推
     *
     * @param date 日期
     * @return 星期几
     */
    public static int getDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取某天的结束时间, e.g. 2005-10-01 235959.999
     *
     * @param date 日期对象
     * @return 该天的结束时间
     */
    public static Date getEndDate(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND,0);
        return cal.getTime();
    }

    /**
     * 取得一个月最多的天数
     *
     * @param year  年份
     * @param month 月份，0表示1月，依此类推
     * @return 最多的天数
     */
    public static int getMaxDayOfMonth(int year, int month) {
        if (month == 1 && isLeapYear(year)) {
            return 29;
        }
        return DAY_OF_MONTH[month];
    }

    /**
     * 得到指定日期的下一天
     *
     * @param date 日期对象
     * @return 同一时间的下一天的日期对象
     */
    public static Date getNextDay(Date date) {
        return addDay(date, 1);
    }

    /**
     * 获取某天的起始时间, e.g. 2005-10-01 000000.000
     *
     * @param date 日期对象
     * @return 该天的起始时间
     */
    public static Date getStartDate(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 根据日期对象来获取日期中的时间(HHmmss).
     *
     * @param date 日期对象
     * @return 时间字符串, 格式为 HHmmss
     */
    public static String getTime(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat("HHmmss");
        return format.format(date);
    }

    /**
     * 根据日期对象来获取日期中的时间(HHmm).
     *
     * @param date 日期对象
     * @return 时间字符串, 格式为 HHmm
     */
    public static String getTimeIgnoreSecond(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat("HHmm");
        return format.format(date);
    }

    /**
     * 判断是否是闰年
     *
     * @param year 年份
     * @return 是true，否则false
     */
    public static boolean isLeapYear(int year) {
        Calendar calendar = Calendar.getInstance();
        return ((GregorianCalendar) calendar).isLeapYear(year);
    }

    /**
     * 把字符串转换成日期，格式为2006-01-10
     *
     * @param str 字符串
     * @return 日期
     */
    public static Date string2Date(String str) {
        return string2Date(str, "yyyy-MM-dd");
    }

    /**
     * 按照指定的格式把字符串转换成时间，格式的写法类似yyyy-MM-dd HHmmss.SSS
     *
     * @param str     字符串
     * @param pattern 格式
     * @return 时间
     */
    public static Date string2Date(String str, String pattern) {
        if (str == null || "".equals(str)) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = dateFormat.parse(str);
        } catch (ParseException e) {
            // ignore
        }
        return date;
    }

    /**
     * 把字符串转换成日期，格式为2006-01-10 205630
     *
     * @param str 字符串
     * @return 日期
     */
    public static Date string2DateTime(String str) {
        return string2Date(str, "yyyy-MM-dd HHmmss");
    }

    /**
     * 把字符串转换成日期，格式为2006-01-10 2056
     *
     * @param str 字符串
     * @return 日期
     */
    public static Date string2DateTimeByMinutes(String str) {
        return string2Date(str, "yyyy-MM-dd HHmm");
    }

    /**
     * 取得一年中的第几周
     *
     * @param date 时间
     * @return Date
     */
    public static int getWeekOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取上周的指定星期的日期
     *
     * @param dayOfWeek 星期几，取值范围是 {@link Calendar#MONDAY} - {@link Calendar#SUNDAY}
     */
    public static Date getDateOfPreviousWeek(int dayOfWeek) {
        if (dayOfWeek > 7 || dayOfWeek < 1) {
            throw new IllegalArgumentException("参数必须是1-7之间的数字");
        }
        return getDateOfRange(dayOfWeek, -7);
    }

    /**
     * 获取本周的指定星期的日期
     *
     * @param dayOfWeek 星期几，取值范围是 {@link Calendar#MONDAY} - {@link Calendar#SUNDAY}
     */
    public static Date getDateOfCurrentWeek(int dayOfWeek) {
        if (dayOfWeek > 7 || dayOfWeek < 1) {
            throw new IllegalArgumentException("参数必须是1-7之间的数字");
        }
        return getDateOfRange(dayOfWeek, 0);
    }

    /**
     * 获取下周的指定星期的日期
     *
     * @param dayOfWeek 星期几，取值范围是 {@link Calendar#MONDAY} - {@link Calendar#SUNDAY}
     */
    public static Date getDateOfNextWeek(int dayOfWeek) {
        if (dayOfWeek > 7 || dayOfWeek < 1) {
            throw new IllegalArgumentException("参数必须是1-7之间的数字");
        }
        return getDateOfRange(dayOfWeek, 7);
    }

    private static Date getDateOfRange(int dayOfWeek, int dayOfRange) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) + dayOfRange);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取星期
     *
     * @param date 时间
     * @return String
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    public static String getClassShareTime(long diff) {
        long current = getCurrentTimeMillis();
        // 天
        long day = (current - diff) / (24 * 60 * 60 * 1000);
        // 时
        long hours = (current - diff) / (60 * 60 * 1000);
        if (day > 0) {
            return day + "天前";
        } else if (hours > 12) {
            return hours + "小时前";
        } else {
            return new SimpleDateFormat("HHmm").format(diff);
        }
    }

    /**
     * 获取当年年份
     */
    public static int getYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取当年年份
     */
    public static int getYearOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取当年月份
     */
    public static int getMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static String getChatDateString(Date date) {
        StringBuilder str = new StringBuilder();
        // 当前时间
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dateNum = calendar.get(Calendar.DAY_OF_YEAR);
        calendar.setTime(now);
        int nowNum = calendar.get(Calendar.DAY_OF_YEAR);
        int cha = nowNum - dateNum;
        // 今天
        if (cha == 0) {
            str.append(getTimeIgnoreSecond(date));
        } else if (cha == 1) {
            // 昨天
            str.append("昨天   ").append(getTimeIgnoreSecond(date));
        } else {
            // 昨天之前
            if (date.getYear() == now.getYear()) {
                str.append(date2ShortStringByMinute(date));
            } else {
                str.append(date2StringByMinute(date));
            }
        }
        return str.toString();
    }

    /**
     * 获取指定日期是几号
     *
     * @param date 时间
     * @return String 返回的字符窜的日期
     */
    public static String getDay(Date date) {
        String dayStr = date2StringByDay(date).substring(8);
        return dayStr;
    }

    /**
     * 获取指定日期是几月
     *
     * @param date 时间
     * @return String 返回的字符窜的月份
     */
    public static String getMonth2(Date date) {
        return date2StringByDay(date).substring(5, 7);
    }

    /**
     * 获取两个时间的时间差 (只比较时间的小时和分钟部分)
     *
     * @param date        时间
     * @param anotherDate 对比的另一个时间
     * @return String 相差时间
     */
    public static String compareHourAndMinute2(Date date, Date anotherDate) {
        if (date == null) {
            date = new Date();
        }
        if (anotherDate == null) {
            anotherDate = new Date();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hourOfDay1 = cal.get(Calendar.HOUR_OF_DAY);
        int minute1 = cal.get(Calendar.MINUTE);
        cal.setTime(anotherDate);
        int hourOfDay2 = cal.get(Calendar.HOUR_OF_DAY);
        int minute2 = cal.get(Calendar.MINUTE);
        return (hourOfDay2 - hourOfDay1) + "时" + (minute2 - minute1) + "分钟";
    }

    /**
     * 获取两个时间的时间差(只比较时间的天和小时部分)
     *
     * @param date        时间
     * @param anotherDate 对比的另一个时间
     * @return String xx天xx时
     */
    public static String compareHourAndMinuteAndSecond(Date date, Date anotherDate) {
        if (date == null) {
            date = new Date();
        }
        if (anotherDate == null) {
            anotherDate = new Date();
        }
        long time = anotherDate.getTime() - date.getTime();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        long day = time / 1000 / 60 / 60 / 24;
        long hourInMillis = time - (day * 24 * 60 * 60 * 1000);
        long hourOfDay = hourInMillis / 1000 / 60 / 60;
        if (hourInMillis % (1000 * 60 * 60) > 0) {
            hourOfDay = hourOfDay + 1;
        }
        return day + "天" + hourOfDay + "时";
    }

    /**
     * 获取当前月份的第一天
     *
     * @return Date
     */
    public static Date getStartOfMonth() {
        //获取当前月第一天：
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        //设置为1号,当前日期既为本月第一天
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    /**
     * 获取当前月份的最后一天
     *
     * @return Date
     */
    public static Date getEndOfMonth() {
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        return ca.getTime();
    }

    /**
     * 获取时间差精确到半个小时
     */
    public static double getDataDiffer(Date start, Date end) {
        //这样得到的差值是微秒级别
        long differTime = end.getTime() - start.getTime();
        long hours = differTime / (1000 * 60 * 60);
        long minutes = differTime % (1000 * 60 * 60);
        long differ = minutes / (1000 * 60);
        if (differ >= 30) {
            return hours + 0.5;
        } else {
            return hours;
        }
    }

    /**
     * 获得到天
     */
    public static int getDataDay(Date start, Date end) {
        long differTime = end.getTime() - start.getTime();
        if(differTime<=0)
        {
            return 0;
        }
        return (int) (differTime / 1000 / 3600 / 24);
    }
    
    /**
     * 获得到天
     */
    public static int getDataDayRounding(Date start, Date end) {
        long differTime = end.getTime() - start.getTime();
        if(differTime<=0)
        {
            return 0;
        }
        int day=(int)differTime /( 1000 *3600 * 24);
        int i=differTime %( 1000 *3600 * 24)>0? 1 : 0;
        return day+i;
    }

    /**
     * 比较2个时间短的时间差和规定的时间差比较
     *
     * @param beginDate 开始时间
     * @param endDate   结束时间
     * @param second    秒
     * @return boolean
     * @throws Exception 异常
     */
    public static boolean compareDate(Date beginDate, Date endDate, long second){
        //除以1000是为了转换成秒
        long between = (endDate.getTime() - beginDate.getTime()) / 1000;
        return between < second;
    }

    /**
     * 比较2个时间 相差多少小时
     *
     * 返回相差的天数
     *
     * @param beginDate 开始时间
     * @param endDate   结束时间
     * @return long
     */
    public static long compareDateForDay(Date beginDate, Date endDate) {
        //除以1000是为了转换成秒
        return (endDate.getTime() - beginDate.getTime()) / 1000 / 60 / 60 / 24;
    }

    /**
     * 比较2个时间 相差多少小时
     *
     * 返回相差的秒数
     *
     * @param beginDate 开始时间
     * @param endDate   结束时间
     * @return long
     */
    public static long compareDate(Date beginDate, Date endDate) {
        //除以1000是为了转换成秒
        return (endDate.getTime() - beginDate.getTime()) / 1000;
    }

    /**
     * 比较2个时间 相差多少小时
     *
     * 返回相差的分钟数
     *
     * @param beginDate 开始时间
     * @param endDate   结束时间
     * @return long
     */
    public static long compareDateForMinute(Date beginDate, Date endDate) {
        //除以1000是为了转换成秒
        return (endDate.getTime() - beginDate.getTime()) / 1000 / 60;
    }

    /**
     * 比较2个时间 相差多少小时
     *
     * 返回相差的小时数
     *
     * @param beginDate 开始时间
     * @param endDate   结束时间
     * @return long
     */
    public static long compareDateForHour(Date beginDate, Date endDate) {
        return (endDate.getTime() - beginDate.getTime()) / 1000 / 60 / 60;
    }

    /**
     * 时间相加（在原有基础上加几个小时）
     *
     * @param startDate 时间
     * @param timeLong  加的时长
     * @return Date
     */
    public static Date addTimeToHours(Date startDate, int timeLong) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(startDate);
        ca.add(Calendar.MINUTE, timeLong);
        return ca.getTime();
    }

    public static String getHour() {
        Timestamp time = new Timestamp(getCurrentTimeMillis());
        int minutes = time.getMinutes();
        String minutes1;
        if (minutes < 10) {
            minutes1 = "0" + minutes;
        } else {
            minutes1 = "" + minutes;
        }
        return time.getHours() + "" + minutes1;
    }
    
    /**
     * 计算两个日期的自然日期差
     *
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return int 
     * @author wenlincheng
     * @date 2019-07-05 2142
     */
    public static int compareCalendarDay(Date startDate, Date endDate){
        Calendar startCal=Calendar.getInstance();
        startCal.setTime(startDate);
        Calendar endCal=Calendar.getInstance();
        endCal.setTime(endDate);
        return endCal.get(Calendar.DAY_OF_MONTH) - startCal.get(Calendar.DAY_OF_MONTH);
    }
    public static Date getEndDate2(Date date)
    {
        String dateStr=date2StringByDay(date)+" 235959";
        return string2DateTime(dateStr);
    }
//    public static void main(String[] args) throws ParseException {
//
//        System.out.println(DateUtils.date2String(new Date(),"yyMMdd"));
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HHmmss");
//        String date = "2019-06-4 233000";
//        System.out.println(DateUtils.compareTimeByDay(dateFormat.parse(date), new Date()));
//        System.out.println(DateUtils.getDataDay(dateFormat.parse(date), new Date()));
//        System.out.println(DateUtils.date2String(DateUtils.addDay(new Date(), 15)));
//        String date2 = "2019-08-12 120000";
//        int i = DateUtils.compareCalendarDay(dateFormat.parse(date), dateFormat.parse(date2));
//        System.out.println(i);
//        int day = DateUtils.getDataDay(DateUtils.getEndDate(dateFormat.parse(date2)), new Date());
//        System.out.println(day);
//    }
}
