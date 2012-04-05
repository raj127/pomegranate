package com.darkmi.util;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public abstract class DateUtils extends org.apache.commons.lang.time.DateUtils {

	/** 
	 * 默认日期格式 
	 */
	private static final String[] parsePatterns = new String[] {
			"yy/MM/dd HH:mm:ss", "yy/MM/dd HH:mm", "yy-MM-dd HH:mm:ss",
			"yy-MM-dd HH:mm", "yy/MM/dd", "yy-MM-dd"
	// 这里可以增加更多的日期格式，用得多的放在前面  
	};

	/** 
	 * 使用默认的日期格式将字符串转换为日期 
	 *  
	 * @param str 要转换的字符串 
	 * @return 转换后的日期 
	 * @throws ParseException 没有匹配的日期格式 
	 */
	public static Date parseDate(String str) throws ParseException {
		return DateUtils.parseDate(str, parsePatterns);
	}

	/** 
	 * 使用给定的日期格式将字符串转换为日期 
	 *  
	 * @param str 要转换的字符串 
	 * @param parsePattern 日期格式字符串 
	 * @return 转换后的日期 
	 * @throws ParseException 日期格式不匹配 
	 */
	public static Date parseDate(String str, String parsePattern)
			throws ParseException {
		return DateUtils.parseDate(str, new String[] { parsePattern });
	}

	
	/**
	 * Description: 
	 * @Version1.0 2011-10-10 下午05:01:12 MagicY created
	 * @param interval 时间间隔,目前有年,月,日,时,分,秒,毫秒
	 * @param number 时间间隔的数量, 可以为负数
	 * @param date 基准点日期(以此日期进行偏移)
	 * @return
	 * 例子,比如要求某个时间date 5天前的时间对象:getDate(Calendar.DAY_OF_YEAR, -5, date);
	 */
	public static Date getDate(int interval, int number, Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		switch(interval) {
			//年
			case Calendar.YEAR : 
				c.set(Calendar.YEAR, c.get(Calendar.YEAR)+number);break;
			
			//月
			case Calendar.MONTH : 
				c.set(Calendar.MONTH, c.get(Calendar.MONTH)+number);break;
			
			//日
			case Calendar.DAY_OF_YEAR : 
				c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR)+number);break;
			
			//时	
			case Calendar.HOUR_OF_DAY : 
				c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY)+number);break;
			
			//分
			case Calendar.MINUTE : 
				c.set(Calendar.MINUTE, c.get(Calendar.MINUTE)+number);break;
			
			//秒
			case Calendar.SECOND :
				c.set(Calendar.SECOND, c.get(Calendar.SECOND)+number);break;
			
			//毫秒
			case Calendar.MILLISECOND :
				c.set(Calendar.MILLISECOND, c.get(Calendar.MILLISECOND)+number);break;
			
			default :
				c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR)+number);break;
		}
		return c.getTime();
	}
	
	/**
	 * Description: 获得参数日期的下一天的日期对象
	 * @Version1.0 2011-2-28 上午09:27:57 MagicY created
	 * @param date
	 * @return
	 */
	public static Date getNextDay(Date date){
		long thisDay = date.getTime();
		//向后偏移一天 (每天是24(小时)*60(分钟)*60(秒)*1000(毫秒))
		long nextDay = thisDay+24*60*60*1000;
		return new Date(nextDay);
	}
	
	/**
	 * Description: 获得参数日期下一小时的日期对象
	 * @Version1.0 2011-3-1 下午01:39:02 MagicY created
	 * @param date
	 * @return
	 */
	public static Date getNextHour(Date date){
		long thisHour = date.getTime();
		//向后偏移一小时 (每小时是60(分钟)*60(秒)*1000(毫秒))
		long nextHour = thisHour+60*60*1000;
		return new Date(nextHour);
	}
	
	//获得参数日期前一天的日期对象
	public static Date getPreviousDay(Date date){
		long thisDay = date.getTime();
		//向前偏移一天 (每天是24(小时)*60(分钟)*60(秒)*1000(毫秒))
		long previousDay = thisDay-24*60*60*1000;
		return new Date(previousDay);
	}
}
