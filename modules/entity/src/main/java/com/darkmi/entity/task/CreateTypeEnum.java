package com.darkmi.entity.task;

/**
 * Description: 作业规程创建类型枚举.
 * Copyright (c) www.darkmi.com
 * All Rights Reserved.
 * @version 1.0  2012-05-04 上午09:20:11 DarkMi created
 */
public enum CreateTypeEnum {
	NEW, TEMPLATE, COPY;
	public String getLabel() {
		switch (this) {
		case NEW:
			return "新建";
		case TEMPLATE:
			return "模板";
		case COPY:
			return "复制";

		}
		return super.toString();
	}
}
