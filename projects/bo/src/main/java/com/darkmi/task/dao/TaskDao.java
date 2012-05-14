package com.darkmi.task.dao;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.darkmi.entity.task.Task;

/**
 * Description: 作业规程任务管理DAO.
 * Copyright (c) www.darkmi.com
 * All Rights Reserved.
 * @version 1.0  2012-05-14 上午09:20:11 DarkMi created
 */
@Component
public class TaskDao extends HibernateDao<Task, Long> {

	private static final String GET_BY_WHERE = "from Task t where 1=1 ";

	public Page<Task> findPageByWhere(Page<Task> page, String where, Map<String, Object> values) {
		return this.findPage(page, GET_BY_WHERE + where, values);
	}
}
