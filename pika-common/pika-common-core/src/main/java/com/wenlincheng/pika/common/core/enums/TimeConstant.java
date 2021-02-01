package com.wenlincheng.pika.common.core.enums;


public interface TimeConstant {

	/*****************
	 * 毫秒常量
	 * @author zhuliming
	 */
	public static enum Miliseconds{
		// 1/2秒的毫秒数
		HalfSecond(500),
		// 1秒的毫秒数
		OneSecond(1000),
		// 2秒的毫秒数
		TwoSeconds(2000),
		// 2秒的毫秒数
		FiveSeconds(5000),
		// 10秒的毫秒数
		TenSeconds(10*1000),
		// 15秒的毫秒数
		FifteenSeconds(15*1000),
		// 20秒的毫秒数
		TwentySeconds(20*1000),
		// 30秒的毫秒数
		ThirtySeconds(30*1000),
		// 一分钟的毫秒数
		OneMinute(60*1000),
		// 2分钟的毫秒数
		TwoMinutes(2*60*1000),
		// 5分钟的毫秒数
		FiveMinutes(5*60*1000),
		// 10分钟的毫秒数
		TenMinutes(10*60*1000),
		// 15分钟的毫秒数
		FifteenMinutes(15*60*1000),
		// 20分钟的毫秒数
		TwentyMinutes(20*60*1000),
		// 30分钟的毫秒数
		HalfAnHour(30*60*1000),
		// 一小时的毫秒数
		OneHour(60*60*1000),
		// 2小时的毫秒数
		TwoHours(2*60*60*1000),
		// 3小时的毫秒数
		ThreeHours(3*60*60*1000),
		// 4小时的毫秒数
		FourHours(4*60*60*1000),
		// 6小时的毫秒数
		SixHours(6*60*60*1000),
		// 半天的毫秒数
		HalfDay(12*60*60*1000),
		// 一天的毫秒数
		OneDay(24L*60*60*1000),
		// 2天的毫秒数
		TwoDays(2L*24*60*60*1000),
		// 3天的毫秒数
		ThreeDays(3L*24*60*60*1000),
		// 一周的毫秒数
		OneWeek(7L*24*60*60*1000),
		// 8天的毫秒数
		EightDays(8L*24*60*60*1000),
		// 10天的毫秒数
		TenDays(10L*24*60*60*1000),
		// 11天的毫秒数
		ElevenDays(11L*24*60*60*1000),
		// 15天的毫秒数
		FifteenDays(15L*24*60*60*1000),
		// 30天的毫秒数
		OneMonth(30L*24*60*60*1000),
		// 90天的毫秒数
		ThreeMonths(3*30L*24*60*60*1000),
		// 半年
		HalfYear(365L*12*60*60*1000),
		// 一年
		OneYear(365L*24*60*60*1000),
		// 10年
		TenYear(10*365L*24*60*60*1000),
		// 15年
		FifteenYear(15*365L*24*60*60*1000),
		// 20年
		TwentyYear(20*365L*24*60*60*1000),
		// 100年
		HundredYear(100*365L*24*60*60*1000);
		public final long miliseconds;
		private Miliseconds(final long miliseconds){
			this.miliseconds = miliseconds;
		}
	}
	
	/************
	 * 秒数常量
	 */
	public static enum Seconds{
		// 一年的秒
		OneYear(60 * 60 * 24 * 365),
		// 一周的秒
		OneWeek(60 * 60 * 24 * 7),
		// 3天的秒数
		ThreeDays(3 * 24 * 60 * 60),
		// 两天的秒数
		TwoDays(2 * 24 * 60 * 60),
		// 一天的秒数
		OneDay(24 * 60 * 60),
		// 半天的秒数
		HalfDay(12 * 60 * 60),
		//8小时的秒数
		EightHourSecond(60 * 60 * 8),
		//44小时的秒数
		FortyFourHourSecond(60 * 60 * 44),
		// 一小时的秒数
		OneHour(60 *60),
		// 1/2小时的秒数
		HalfAnHour(30 *60),
		// 一分钟的秒数
		OneMinute(60);
		public final int seconds;
		Seconds(final int seconds){
			this.seconds = seconds;
		}
	}
	
	/************
	 * 分钟常量
	 */
	public static enum Minutes{
		// "一天的分钟数"
		OneDay(24 * 60),
		// "一小时的分钟数"
		OneHour(60),
		// "1/2小时的分钟数"
		HalfAnHour(30),
		// "2天的分钟数"
		TwoDays(2*24 * 60),
		// "一周的分钟数"
		OneWeek(7*24 * 60);
		public final int minutes;
		Minutes(final int minutes){
			this.minutes = minutes;
		}
	}	
	
	/************
	 * 小时常量
	 */
	public static enum Hours{
		//"一天的小时数"
		OneDay(24),
		//"1/2天的小时数"
		HalfADay(12);
		public final int hours;
		private Hours(final int hours){
			this.hours = hours;
		}
	}		


	/**
	 * 所有需要用到的时间的格式化器
	 */
	public static enum TimeFormat{
		// //"年yyyy"
		YYYY("yyyy"),
		//"年月日时分秒(无下划线) yyyyMMddHHmmss"
		YYYYMMDDHHMMSS("yyyyMMddHHmmss"),
		//"年月日(无下划线) yyyyMMdd"
		YYYYMMDD("yyyyMMdd"),
		//"年月日时(无下划线) yyyyMMddHH"
		YYYYMMDDHH("yyyyMMddHH"),
		yyyy_MM_dd_HH_mm_ss("yyyy-MM-dd HH:mm:ss"),
		//"完整时间 yyyy-MM-dd HH:mm:ss"
		//完整时间 MM-dd HH:mm
		MM_dd_HH_mm("MM-dd HH:mm"),
		//完整时间 MM-dd HH:mm:ss
		MM_dd_HH_mm_ss("MM-dd HH:mm:ss"),
		//"完整时间 yyyy-MM-dd HH:mm"
		yyyy_MM_dd_HH_mm("yyyy-MM-dd HH:mm"),
		//"完整时间 yyyy-MM-dd"
		yyyy_MM_dd("yyyy-MM-dd"),
		//"月日(无下划线) MMdd"
		SimpleMMDD("MMdd"),
		//"月日 MM-dd"
		SimpleMM_DD("MM-dd"),
		//"月日 MM/dd"
		SimpleMM_Slash_DD("MM/dd"),
		//"月日时分秒(无下划线) MMddHHmmss"
		MMDDHHMMSS("MMddHHmmss"),
		//小时
		HH("HH"),
		//"年月日时分秒(无下划线) yyyyMMddHHmmssSSS"
		YYYYMMDDHHMMssSS("yyyyMMddHHmmssSSS"),
		//"月日时分秒(无下划线) MMddHHmmssSS"
		MMDDHHMMssSS("MMddHHmmssSS"),
		HHMMSS("HH:mm:ss"),
		HHMMSS_Miliseconds("HH:mm:ss.SSS"),
		HHMM("HH:mm"),
		//"年月日时(无下划线) mm/dd";
		MMDD("MM/dd"),
		//"年月日时(无下划线) yyyy/mm/dd";
		yyyyMMDD("yyyy/MM/dd"),
		//"年月日时(无下划线) mm/dd hh:mm";
		MMDD_HH_mm("MM/dd HH:mm"),
		//按照点区分的mm.dd
		MDByPoint("M.d");
		public final String format;
		private TimeFormat(final String f){
			this.format = f;
		}
	}

	/**
	 * 规定时期类型及天数
	 *
	 */
	public static enum DateType{
		DAY(1),WEEK(7),MONTH(30),YEAR(365);
		public final int day;
		private DateType(final int day){
			this.day = day;
		}
		public final static DateType parse(int d){
			if(DAY.day == d){
				return DAY;
			}else if (WEEK.day == d) {
				return WEEK;
			}else if (MONTH.day == d) {
				return MONTH;
			}else if (YEAR.day == d) {
				return YEAR;
			}
			return null;
		}
	}
}