package com.nearme.statistics.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.nearme.statistics.common.Constants;

/**
 * 日期工具类
 * 
 * @author 刘超
 * @version 1.0
 * @since 1.0,2012-2-8
 */
public class DateUtil {

	/**
	 * 标准完整日期时间格式
	 */
	public static final String FMT_FULL_DATETIME = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 标准日期格式
	 */
	public static final String FMT_SHORT_DATETIME = "yyyy-MM-dd";

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static String getTime() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String today = sdf.format(new Date());
			return today;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取今天凌晨 00:00:00的时间字符串
	 * 
	 * @return
	 */
	public static String getToday() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String today = sdf.format(new Date());
			return today;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取今天凌晨 00:00:00的时间
	 * 
	 * @return
	 */
	public static Date getTodayDate() {
		return trunc(new Date());
	}

	/**
	 * 格式化时间
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDate(Date date, String format) {
		if (date == null) {
			return "";
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		} catch (Exception e) {
			return null;
		}
	}

	public static String formatDate(Date date) {
		return formatDate(date, FMT_FULL_DATETIME);
	}

	/**
	 * 格式化时间
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate2Short(Date date) {
		return formatDate(date, FMT_SHORT_DATETIME);
	}

	/**
	 * 字符串格式时间转化为Date
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date parseDate(String date, String format) {
		if (StringUtil.isNullOrEmpty(date)) {
			return null;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.parse(date);
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * 将完整格式时间字符串转为Date
	 * 
	 * @param date
	 * @return
	 */
	public static Date parseFullDateTime(String date) {
		return parseDate(date, FMT_FULL_DATETIME);
	}

	/**
	 * 将短日期字符串转为Date
	 * 
	 * @param date
	 * @return
	 */
	public static Date parseShortDateTime(String date) {
		return parseDate(date, FMT_SHORT_DATETIME);
	}

	/**
	 * 获取指定日期的凌晨时间
	 * 
	 * @param input
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static Date trunc(Date input) {
		if (input == null) {
			return null;
		}

		return new Date(input.getTime() - input.getHours() * 60 * 60 * 1000
				- input.getMinutes() * 60 * 1000 - input.getSeconds() * 1000
				- (input.getTime() % 1000));
	}

	/**
	 * 在指定日期的基础上增加(负数为删除)一定天数
	 * 
	 * @param input
	 * @param days
	 * @return
	 */
	public static Date AddDays(Date input, int days) {
		if (input == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(input);
		cal.add(Calendar.DATE, days);
		return new Date(cal.getTimeInMillis());
	}

	public static Date getTimeOfXdaysAgo(int xDaysAgo) {
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);

		cal.add(Calendar.DATE, -1 * xDaysAgo);

		return cal.getTime();
	}

	public static String getDateOfXdaysAgo(int xDaysAgo) {
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);

		cal.add(Calendar.DATE, -1 * xDaysAgo);

		return formatDate2Short(cal.getTime());
	}
	
	public static String getDateOfXdaysAgo(Date date, int xDaysAgo, String format) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		cal.add(Calendar.DATE, -1 * xDaysAgo);
		SimpleDateFormat sdf = new SimpleDateFormat(format);

		return sdf.format(cal.getTime());
	}
	
	public static String getDateOfXmonthsAgo(Date date, int xMonthsAgo, String format) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		cal.add(Calendar.MONTH, -1 * xMonthsAgo);
		SimpleDateFormat sdf = new SimpleDateFormat(format);

		return sdf.format(cal.getTime());
	}

	public static String getDateOfXweeksAgo(int xWeeksAgo) {
		try {

			Date today = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(today);

			cal.add(Calendar.WEEK_OF_MONTH, -1 * xWeeksAgo);

			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
			cal.add(Calendar.DATE, -1 * (dayOfWeek - 1));

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(cal.getTime());

			return date;

		} catch (Exception e) {
			return null;
		}
	}

	public static String getDateOfXmonthAgo(int xDaysAgo) {
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);

		cal.add(Calendar.MONTH, -1 * xDaysAgo);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(cal.getTime());

		return date;
	}

	/**
	 * 获取x个月前的第一天
	 * 
	 * @param xDaysAgo
	 * @return
	 */
	public static String getFirstDayOfXmonthAgo(int xDaysAgo) {
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);

		cal.add(Calendar.MONTH, -1 * xDaysAgo);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String date = sdf.format(cal.getTime());
		date += "-01";

		return date;
	}

	/**
	 * 获取x个月前的第二天
	 * 
	 * @param xDaysAgo
	 * @return
	 */
	public static String getSecondDayOfXmonthAgo(int xDaysAgo) {
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);

		cal.add(Calendar.MONTH, -1 * xDaysAgo);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String date = sdf.format(cal.getTime());
		date += "-02";

		return date;
	}

	/**
	 * 获取相对date的x个月前/后（x为负表示后）的第一天
	 * 
	 * @param date
	 * @param xmonth
	 * @return
	 */
	public static Date getFirstDayOfXmonthAgo(Date date, int x) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		cal.add(Calendar.MONTH, -1 * x);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String newdate = sdf.format(cal.getTime());
		newdate += "-01";
		try {
			return sdf.parse(newdate);
		} catch (ParseException e) {
			return null;
		}
	}

	public static String formatDateOfXmonthAgo(int xDaysAgo) {
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);

		cal.add(Calendar.MONTH, -1 * xDaysAgo);

		return formatDate2Short(cal.getTime());
	}

	public static String getTimeOf7daysAgo() {
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);

		cal.add(Calendar.DATE, -1 * 7);

		return formatDate2Short(cal.getTime());
	}

	public static Date getMaxTimeOfDay(String date) {

		try {

			if (date == null) {
				return null;
			}
			// 一天当中最早的时间点为[00:00:00]
			String newDate = date + " 24:00:00";

			SimpleDateFormat sdf = new SimpleDateFormat(FMT_FULL_DATETIME);
			return sdf.parse(newDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date getMinTimeOfDay(String date) {

		try {

			if (date == null) {
				return null;
			}
			// 一天当中最早的时间点为[00:00:00]
			String newDate = date + " 00:00:00";

			SimpleDateFormat sdf = new SimpleDateFormat(FMT_FULL_DATETIME);
			return sdf.parse(newDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date getMaxTimeOfDay(Date date) {

		try {

			if (date == null) {
				return null;
			}

			SimpleDateFormat sdf = new SimpleDateFormat(FMT_SHORT_DATETIME);

			// 一天当中最早的时间点为[00:00:00]
			String newDate = sdf.format(date) + " 24:00:00";

			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.parse(newDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取两个日期之间天数
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long getDays(Date startDate, Date endDate) {

		try {

			if (startDate == null || endDate == null) {
				return -1;
			}
			long start = startDate.getTime();
			long end = endDate.getTime();
			return (end - start) / (1000 * 60 * 60 * 24);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * 获取日期所在月的天数
	 * 
	 * @param date
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static int getDays(Date date) {
		try {
			if (date == null) {
				return -1;
			}
			int year = date.getYear() + 1900;
			int month = date.getMonth() + 1;
			return getDays(year, month);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	// 判断闰年
	private static boolean isLeap(int year) {
		if (((year % 100 == 0) && year % 400 == 0)
				|| ((year % 100 != 0) && year % 4 == 0))
			return true;
		else
			return false;
	}

	private static int getDays(int year, int month) {
		int days;
		int FebDay = 28;
		if (isLeap(year))
			FebDay = 29;
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			days = 31;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			days = 30;
			break;
		case 2:
			days = FebDay;
			break;
		default:
			days = 0;
			break;
		}
		return days;
	}

	public static int getWeekDay(String date) {
		try {
			Date currDate = DateUtil.parseDate(date, "yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setFirstDayOfWeek(Calendar.MONDAY);
			cal.setTime(currDate);

			return cal.get(Calendar.WEEK_OF_YEAR);
		} catch (Exception e) {
			return 1;
		}
	}

	public static int getWeekDay(Date date) {
		try {
			Calendar cal = Calendar.getInstance();
			cal.setFirstDayOfWeek(Calendar.MONDAY);
			cal.setTime(date);

			return cal.get(Calendar.WEEK_OF_YEAR);
		} catch (Exception e) {
			return 1;
		}
	}
	
	public static String getMonday(Date date, String format) {
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			
			int x = cal.get(Calendar.DAY_OF_WEEK);
			cal.add(Calendar.DATE, -1 * x + 2);
			
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(cal.getTime());
			
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 *  判断时间date1是否在时间date2之前
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isDateBefore(Date date1, Date date2) {
		try {
			return date1.getTime() < date2.getTime();
		} catch (Exception e) {
			return false;
		}
	}
	
	public static Date getMonday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		cal.add(Calendar.DATE, -1 * ((day==1 ? 8 : day)-2));

		return cal.getTime();
	}
	
	public static String format(String input, String formatFrom, String formatTo) {

		if(input==null || "".equals(input)){
			return input;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(formatFrom);
		try {
			Date date = sdf.parse(input);
			sdf = new SimpleDateFormat(formatTo);
			return sdf.format(date);
		} catch (ParseException e) {
			return input;
		}
	}
	
	/**
	 * 根据输入日期   输出规范的日期显示
	 * 
	 * @param date 输入日期
	 * @param format 输入日期的格式
	 * @param lidu DAILY/WEEKLY/MONTHLY
	 * @return
	 */
	public static String getFormatResult(String date, String format, String lidu) {

		if(date==null || "".equals(date)){
			return date;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			Date d = sdf.parse(date);
			if(Constants.DAILY.equals(lidu)){
				sdf = new SimpleDateFormat("MM-dd");
				return sdf.format(d);
			}else if(Constants.WEEKLY.equals(lidu)){
				sdf = new SimpleDateFormat("MM-dd");
				return sdf.format(d)+"~"+ sdf.format(DateUtil.AddDays(d, 6));
			}else if(Constants.MONTHLY.equals(lidu)){
				sdf = new SimpleDateFormat("yyyy-MM");
				return sdf.format(d);
			}else{
				return date;
			}
			
		} catch (ParseException e) {
			return date;
		}
	}
	
	
	
	public static void main(String[] args){
		System.out.println(DateUtil.formatDate(DateUtil.getMonday(DateUtil.parseDate("20131224", "yyyyMMdd"))));
	}
}
