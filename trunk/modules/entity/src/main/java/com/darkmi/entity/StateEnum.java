package com.darkmi.entity;

/**
 * Description: 状态枚举类型.
 * Copyright (c) www.darkmi.com
 * All Rights Reserved.
 * @version 1.0  2012-05-04 上午09:20:11 DarkMi created
 */
public enum StateEnum {
	NORMAL, DELETE;
	public String getLabel() {
		switch (this) {
		case NORMAL:
			return "正常";
		case DELETE:
			return "删除";
		}
		return super.toString();
	}
}
