package com.darkmi.util;

/**
 * Description: 统计中时间段的枚举类型，如：日、周、月、年(目的是定义统一的常量)
 * Copyright (c) 永新视博
 * All Rights Reserved.
 * @version 1.0  2011-2-21 上午10:23:25 MagicY created
 */
public enum TimeIntervalTypeEnum {
	
	DAY, WEEK, MONTH, YEAR;
	
	public String getLabel() {
		
		switch(this) {
			case DAY:
				return "按日";
			case WEEK:
				return "按周";
			case MONTH:
				return "按月";
			case YEAR:
				return "按年";
		}
		return super.toString();
	}
}
