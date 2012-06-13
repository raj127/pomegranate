package com.darkmi.system.dao;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.darkmi.entity.system.Log;

/**
 * Description: 日志对象的泛型DAO.
 * Copyright (c) darkmi
 * All Rights Reserved.
 * @version 1.0  2012-05-23 下午01:36:31 darkmi created
 */
@Component
public class LogDao extends HibernateDao<Log, Long> {
	//删除所有日志
	private static final String DELETE_ALL_LOGS = "delete Log log where 1=1 ";
	
	//删除所有日志
	public int deleteAll() {
		return this.batchExecute(DELETE_ALL_LOGS);
	}
}
