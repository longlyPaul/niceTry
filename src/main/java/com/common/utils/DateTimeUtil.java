package com.common.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * @文件描述 时间的工具类
 * @version CVN #V1# #2014年3月20日# 
 */
public final class DateTimeUtil{

	/**
	 * 获得当前的系统时间
	 * @return 当前的系统日期
	 */
	public static Date getCurrentTime(){
		return new Date();
	}

	/**
	 * 获得当前的系统日期，不带有时分秒
	 * @return 当前的系统日期
	 */
	public static Date getCurrentDate(){
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.clear(Calendar.HOUR);
		c.clear(Calendar.MINUTE);
		c.clear(Calendar.SECOND);
		c.clear(Calendar.MILLISECOND);
		date = c.getTime();
		return date;
	}

	/**
	 * 根据字符串格式日期，返回Date
	 * @param date
	 * @return 当前的系统日期
	 */
	public static Date getDate(String date){
		Timestamp ts = Timestamp.valueOf(date);
		return ts;
	}

	/**
	 * 得到当前系统日期,格式："yyyy-MM-dd"
	 * @return String
	 */
	public static String getCurDate(){
		return getFormatDate(getCurrentDate());
	}

	/**
	 * 输出字符串类型的格式化日期 "yyyy-MM-dd"
	 * @param date
	 * @return sDate
	 */
	public static String getFormatDate(Date date){
		return getFormatDate(date,BaseConstants.DATE_PATTERN);
	}

	/**
	 * 输出字符串类型的格式化时间 yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return sDate
	 */
	public static String getFormatTime(Date date){
		return getFormatDate(date,BaseConstants.DATETIME_PATTERN);
	}

	/**
	 * 输出字符串类型的格式化日期
	 * @param date
	 * @param pattern 时间格式
	 * @return sDate String
	 */
	public static String getFormatDate(Date date, String pattern){
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(date);
	}

	/**
	 * 该方法将日期格式化为标准日期格式字符串
	 * @param date 待转化的日期
	 * @return 标准日期格式字符串
	 */
	public static String getCriterionFormatDate(Date date){
		SimpleDateFormat format = new SimpleDateFormat(BaseConstants.DATE_PATTERN);
		format.setLenient(false);
		return format.format(date);
	}

	/**
	 * 得到指定日期的月份,格式：yyyy-mm-dd
	 * @return String
	 */
	public static String getDateMonth(Date date){
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy'-'MM'-'dd");
		format1.setLenient(false);
		String dateStr = format1.format(date);
		int begin = dateStr.indexOf('-') + 1;
		int end = dateStr.lastIndexOf('-');
		return dateStr.substring(begin, end);
	}

	/**
	 * 得到某一天的开始时间，精确到毫秒
	 * @param date 日期
	 * @return 某一天的0时0分0秒0毫秒的那个Date
	 */
	public static Date beginOfDay(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		date = c.getTime();
		return date;
	}

	/**
	 * 得到某一天的最后时间，精确到毫秒
	 * @param date 日期
	 * @return 某一天的下一天的0时0分0秒0毫秒的那个Date减去1毫秒所得到的Date
	 */
	public static Date endOfDay(Date date){
		date = beginOfDay(date);
		return endOfDayByBeginOfDate(date);
	}

	/**
	 * 根据某一天的开始时间，得到某一天的最后时间，精确到毫秒
	 * @param date 日期
	 * @return 某一天的下一天的0时0分0秒0毫秒的那个Date减去1毫秒所得到的Date
	 */
	public static Date endOfDayByBeginOfDate(Date date){
		date = nextDay(date);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.SECOND, -1);
		date = c.getTime();
		return date;
	}

	/**
	 * 得到指定日期后若干天的日期 by Yin Jian
	 * @param date 指定日期
	 * @param days 天数
	 * @return 自指定日期后的若干天的日期
	 */
	public static Date afterDaysSinceDate(Date date, int days){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, days);
		date = c.getTime();
		return date;
	}

	/**
	 * 判断两个Date是否在同一天 
	 * @param date1 date1
	 * @param date2 date2
	 * @return 如果两个Date在同一天，则返回true，否则false
	 */
	public static boolean isTwoDatesInSameDay(Date date1, Date date2){
		boolean flag=false;
		Date preDate1 = preDay(date1);
		Date nextDate1 = nextDay(date1);
		if (date2.after(preDate1) && date2.before(nextDate1)){
			flag=true;
		}
		return flag;
	}

	/**
	 * 得到指定日期的下一天的开始时间
	 * @param date 指定Date
	 * @return 下一天的开始时间
	 */
	public static Date beginOfNextDay(Date date){
		date = nextDay(date);
		return beginOfDay(date);
	}

	/**
	 * 得到指定日期的下一天
	 * @param date 日期
	 * @return 传入日期的下一天
	 */
	public static Date nextDay(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 1);
		date = c.getTime();
		return date;
	}

	/**
	 * 得到指定日期的前一天
	 * @param date  日期
	 * @return 传入日期的前一天
	 */
	public static Date preDay(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, -1);
		date = c.getTime();
		return date;
	}

	/**
	 * 得到当前月份的下一个月份
	 * @return String
	 */
	public static Date addMonth(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, 1);
		date = c.getTime();
		return date;
	}

	/**
	 * 得到年份与月份,格式：yyyy-mm
	 * @return String
	 */
	public static String getYearMonth(Date date){
		String yearMonthStr = getCriterionFormatDate(date);
		int index = yearMonthStr.lastIndexOf('-');
		yearMonthStr = yearMonthStr.substring(0, index);
		return yearMonthStr;
	}

	/**
	 * 得到当前月的最后一天
	 * @return String
	 */
	public static Date getLastDayOfMonth(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.DAY_OF_MONTH, -1);
		date = c.getTime();
		return date;
	}

	/**
	 * 得到当前月的第一天
	 * @return String
	 */
	public static Date getFirstDayOfMonth(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, 1);
		date = c.getTime();
		return date;
	}

	/**
	 * 判断一个日期是否在指定的时间段内
	 * @return String
	 */
	public static boolean inTimeSegment(Date start, Date end, Date date){
		boolean flag=false;
		start = preDay(start);
		end = nextDay(end);
		if (date.after(start) && date.before(end)){
			flag=true;
		}
		return flag;
	}

	/**
	 * 判断当前日期是否在指定的时间段内
	 * @param start 时间段开始时间
	 * @param end 时间段结束时间
	 * @return 如果当前日期在指定时间段内，则为true，否则为false
	 */
	public static boolean isCurrentDateInTimeSegment(Date start, Date end){
		boolean flag=false;
		Date date = getCurrentDate();
		if (inTimeSegment(start, end, date)){
			flag=true;
		}
		return flag;
	}

	/**
	 * 得到同一个月内两个日期的间隔天数 
	 * 备注：可能需要提交到框架作统一处理
	 * @param start
	 * @param end
	 * @return
	 */
	public static int betweenDaysInOneMonth(Date start, Date end){
		String startStr = getFormatDate(start, "yyyyMMdd");
		String endStr = getFormatDate(end, "yyyyMMdd");
		int days = Integer.parseInt(endStr) - Integer.parseInt(startStr) + 1;
		return days;
	}

	/**
	 * @author 得到两个日期的间隔天数
	 * @param start
	 * @param end
	 * @return -1说明开始日期大于结束日期
	 */
	public static int getBetweenDays(Date start, Date end){
		if (start.after(end)){
			return -1;
		}
		Calendar startC = Calendar.getInstance();
		startC.setTime(start);
		Calendar endC = Calendar.getInstance();
		endC.setTime(end);
		endC.add(Calendar.DAY_OF_YEAR, 1);
		int days = 0;
		do{
			days++;
			startC.add(Calendar.DAY_OF_YEAR, 1);
		}while(startC.before(endC));
		return days;
	}
	
	public static long getBetweenDays2(Date start, Date end) throws Exception{
		long day=0;
		try {
		    Date date1 = start;
		    Date date2 = end;
		    day=date1.getTime()-date2.getTime();
		    day =day/1000/60/60/24;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return day;
	}

	/**
	 * 得到指定月份的天数
	 * @param date
	 * @return
	 */
	public static int daysInMonth(Date date){
		Date start = getFirstDayOfMonth(date);
		Date end = getLastDayOfMonth(date);
		int days = betweenDaysInOneMonth(start, end);
		return days;
	}

	/**
	 * 判断两个时间段是否存在重叠
	 * @param start1 第一个时间段的开始时间
	 * @param end1 第一个时间段的结束时间
	 * @param start2 第二个时间段的开始时间
	 * @param end2 第二个时间段的结束时间
	 * @return 如果存在重叠返回true，否则false
	 */
	public static boolean isTimeOverlap(Date start1,Date end1,Date start2,Date end2){
		boolean flag=false;
		if (inTimeSegment(start1, start2, end2)||inTimeSegment(end1, start2, end2)){
			flag=true;
		}
		return flag;
	}

	/**
	 * 把传入的时间和当前的系统时间进行比较，精度控制在天。
	 * @param formmartTimeString
	 * 	格式化的时间字符串 年月日 ********8位的字符串
	 * @return comparedResult 1 传入的时间大于系统当前时间，0相等，-1是小于。
	 */
	public static int compareCurrentTime(String formmartTimeString){
		Calendar currentTime = Calendar.getInstance();
		Calendar comparedTime = (Calendar) currentTime.clone();
		comparedTime.set(Calendar.YEAR, Integer.parseInt(formmartTimeString
				.substring(0, 4)));
		comparedTime.set(Calendar.MONTH, Integer.parseInt(formmartTimeString
				.substring(4, 6)) - 1);
		comparedTime.set(Calendar.DAY_OF_MONTH, Integer
				.parseInt(formmartTimeString.substring(6, 8)));
		int comparedResult = comparedTime.compareTo(currentTime);
		return comparedResult;
	}
	
	/**
	* 日期差(time1 - time2，返回负数，若time1在time2之前)
	* @param time1
	* @param time2
	* @return 日期差(精度控制在小时)
	*/
	public static long getQuot(Date time1, Date time2) {
	   long quot = 0;
	   try {
		   Date date1 = time1;
		   Date date2 = time2;
		   quot = date1.getTime() - date2.getTime();
		   quot = quot/1000/60/60;
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
	   return quot;
	}

	public static Date getDateFromFormattingString(String dateString){
		Calendar currentTime = Calendar.getInstance();
		currentTime.set(Calendar.YEAR, Integer.parseInt(dateString.substring(0,
				4)));
		currentTime.set(Calendar.MONTH, Integer.parseInt(dateString.substring(
				5, 7)) - 1);
		currentTime.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateString
				.substring(8, 10)));
		currentTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(dateString
				.substring(11, 13)));
		currentTime.set(Calendar.MINUTE, Integer.parseInt(dateString.substring(
				14, 16)));
		currentTime.set(Calendar.SECOND, Integer.parseInt(dateString.substring(
				17, 19)));
		return currentTime.getTime();
	}

	public static Date getFormatDate(String str) throws ParseException{
		if (str==null||"".equals(str)){
			return null;
		}
		if (str.length()<=10){
			return getDateByString(str,"yyyy-MM-dd");
		} else{
			return getDateByString(str,"yyyy-MM-dd HH:mm:ss");
		}
	}

	public static Date getDateByString(String str, String pattern)
			throws ParseException{
		SimpleDateFormat df3 = new SimpleDateFormat();
		df3.applyPattern(pattern);
		return df3.parse(str);
	}

	public static Date addDate(Date date, int num){
		return new Date(date.getTime()+num*(long)24*3600*1000);
	}

	public static Long getBetweenDays2(String str1,String str2,String pattern) throws Exception {
		Date date1=getDateByString(str1,pattern);
		Date date2=getDateByString(str2,pattern);
		return getBetweenDays2(date1,date2);
	}
	
}

