package com.darkmi.common.tools;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;

public class DateUtil {

	public static int getYear(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	public static int getMonth(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	public static int getDay(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.get(Calendar.DATE);
	}

	public static int getHour(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	public static int getMinute(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.get(Calendar.MINUTE);
	}

	public static int getSecond(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.get(Calendar.SECOND);
	}

	public static int getWeek(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	public static int getWeekOfYear(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	public static Date stringToDate(String dataString) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

		return df.parse(dataString);
	}

	public static Date stringToChinaDate(String dataString) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return df.parse(dataString);
	}

	/**
	 * 根据一个DATE类型时间得到例如"2009-01-01T09-02-22"格式的时间字符串
	 * 
	 * @author tangliang
	 * @param date
	 * @return
	 */
	public static String dateToGMTDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		String dateString = formatter.format(date);
		return dateString;
	}

	/**
	 * 根据一个DATE类型时间得到例如"2009-01-01 09-02-22"格式的时间字符串
	 * 
	 * @author tangliang
	 * @param date
	 * @return
	 */
	public static String dateToChinaDateTime(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(date);
		return dateString;
	}

	/**
	 * 根据一个DATE类型时间得到例如"2009-01-01 09-02-22"格式的时间字符串
	 * 
	 * @author tangliang
	 * @param date
	 * @return
	 */
	public static String dateToChinaDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(date);
		return dateString;
	}

	/**
	 * 根据一个DATE类型时间得到小时和分钟组合字符串，如"1029"
	 * 
	 * @author tangliang
	 * @param date
	 * @return
	 */
	public static String getHHMMByDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("HHmm");
		return formatter.format(date);
	}

	/**
	 * 根据一个DATE类型时间得到小时、分钟、秒组合字符串，如"102902"
	 * 
	 * @author tangliang
	 * @param date
	 * @return
	 */
	public static String getHHMMSSByDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");
		return formatter.format(date);
	}

	/**
	 * 根据一个以秒为单位的duration，转化成HHMM格式的字符串，如：3670秒，转换后为"0101"
	 * 
	 * @author tangliang
	 * @param duration
	 * @return
	 */
	public static String getHHMMDuration(int duration) {
		String hour = new Integer(duration / 3600).toString();
		if (hour.length() < 2) {
			hour = "0" + hour;
		}
		String minute = new Integer((duration % 3600) / 60).toString();
		if (minute.length() < 2) {
			minute = "0" + minute;
		}
		return hour + minute;
	}

	/**
	 * 将一个Date格式的时间转换成"yyyyMMddHHmmss"格式的字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String getYYYYMMDDHHMMSSTime(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		return formatter.format(date);
	}

	/**
	 * 将一个Date格式的时间转换成"yyyyMMddHHmmss"格式的字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String getYYYYMMTime(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
		return formatter.format(date);
	}

	/**
	 * 将一个Date格式的时间转换成"yyyyMMddHHmmss"格式的字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String getYYYYMMDDTime(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		return formatter.format(date);
	}

	/**
	 * 将一个Date格式的时间转换成"yyyyMMddHHmmss"格式的字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String getMMDDTime(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("MM月dd");
		return formatter.format(date);
	}

	/**
	 * 根据一个以秒为单位的duration，转化成HHMMSS格式的字符串，如：3670秒，转换后为"010110"
	 * 
	 * @author tangliang
	 * @param duration
	 * @return
	 */
	public static String getHHMMSSDuration(int duration) {
		String hour = new Integer(duration / 3600).toString();
		if (hour.length() < 2) {
			hour = "0" + hour;
		}
		String minute = new Integer((duration % 3600) / 60).toString();
		if (minute.length() < 2) {
			minute = "0" + minute;
		}
		String second = new Integer((duration % 3600) % 60).toString();
		if (second.length() < 2) {
			second = "0" + second;
		}
		return hour + minute + second;
	}

	public static String getHHMMSSDuration(int duration, String spliter) {
		String hour = new Integer(duration / 3600).toString();
		if (hour.length() < 2) {
			hour = "0" + hour;
		}
		String minute = new Integer((duration % 3600) / 60).toString();
		if (minute.length() < 2) {
			minute = "0" + minute;
		}
		String second = new Integer((duration % 3600) % 60).toString();
		if (second.length() < 2) {
			second = "0" + second;
		}
		return hour + spliter + minute + spliter + second;
	}

	public static int getDuration(String value) {
		int duration = 0;

		int hh = Integer.parseInt(value.substring(0, 2));
		int mm = Integer.parseInt(value.substring(2, 4));
		int ss = Integer.parseInt(value.substring(4));

		duration = hh * 60 * 60 + mm * 60 + ss;

		return duration;

	}

	/**
	   * 获取给定时间所在月的第一天Ｆ的日期和下个月的第一天的日期
	   * 
	   * @param calendar
	   * @return Date数组，[0]为第一天的日期，[1]下个月的第一天的日期
	   */
	public static Date[] getMonthStartAndEndDate(Calendar calendar) {
		Date[] dates = new Date[2];
		Date firstDateOfMonth, lastDateOfMonth;
		// 得到当天是这月的第几天
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		// 减去dayOfMonth,得到第一天的日期，因为Calendar用０代表每月的第一天，所以要减一
		calendar.add(Calendar.DAY_OF_MONTH, -(dayOfMonth - 1));
		firstDateOfMonth = calendar.getTime();
		// calendar.getActualMaximum(Calendar.DAY_OF_MONTH)得到这个月有几天
		calendar.add(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		lastDateOfMonth = calendar.getTime();

		dates[0] = firstDateOfMonth;
		dates[1] = lastDateOfMonth;
		return dates;
	}

	/**
	   * 获取给定时间所在周的第一天(Sunday)的日期和下一周的第一天日期
	   * 
	   * @param calendar
	   * @return Date数组，[0]为第一天的日期，[1]下一周的第一天的日期
	   */
	public static Date[] getWeekStartAndEndDate(Calendar calendar) {
		Date[] dates = new Date[2];
		Date firstDateOfWeek, lastDateOfWeek;
		// 得到当天是这周的第几天
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		// 减去dayOfWeek,得到第一天的日期，因为Calendar用０－６代表一周七天，所以要减一
		calendar.add(Calendar.DAY_OF_WEEK, -(dayOfWeek - 1));
		firstDateOfWeek = calendar.getTime();
		// 每周7天，加６，得到最后一天的日子
		calendar.add(Calendar.DAY_OF_WEEK, 7);
		lastDateOfWeek = calendar.getTime();

		dates[0] = firstDateOfWeek;
		dates[1] = lastDateOfWeek;
		return dates;
	}

	public static String formatMs(long ms) {//将毫秒数换算成x天x时x分x秒x毫秒
		int ss = 1000;
		int mi = ss * 60;
		int hh = mi * 60;
		int dd = hh * 24;

		long day = ms / dd;
		long hour = (ms - day * dd) / hh;
		long minute = (ms - day * dd - hour * hh) / mi;
		long second = (ms - day * dd - hour * hh - minute * mi) / ss;
		long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

		String strDay = day < 10 ? "0" + day : "" + day;
		String strHour = hour < 10 ? "0" + hour : "" + hour;
		String strMinute = minute < 10 ? "0" + minute : "" + minute;
		String strSecond = second < 10 ? "0" + second : "" + second;
		String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;
		strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : "" + strMilliSecond;
		if (day == 0 && milliSecond == 0)
			return strHour + ":" + strMinute + ":" + strSecond;

		return strDay + " " + strHour + ":" + strMinute + ":" + strSecond + " " + strMilliSecond;
	}
	
	public static String formatSeconds(long s) {//将秒数换算成x.xx时
		double hours = s / (60 * 60.0);
		
		DecimalFormat nf = new DecimalFormat("0.00");
		String res = nf.format(hours) + "小时";
		return res;
	}

	public static String toGMT(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance(new SimpleTimeZone(0, "GMT"));
		format.setCalendar(cal);
		return format.format(date);
	}

	public static void main(String[] args) {

		System.out.println(toGMT(new Date()));
	}

}
