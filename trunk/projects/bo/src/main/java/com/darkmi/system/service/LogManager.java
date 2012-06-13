package com.darkmi.system.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import com.darkmi.entity.system.Log;
import com.darkmi.system.dao.LogDao;

/**
 * Description: 日志相关的管理类.
 * Copyright (c) darkmi
 * All Rights Reserved.
 * @version 1.0  2012-05-23 下午01:36:31 darkmi created
 */
@Component
@Transactional
public class LogManager {

	private static Logger logger = LoggerFactory.getLogger(LogManager.class);

	private LogDao logDao;

	/**
	 * Description: 根据条件查询日志
	 * @Version1.0 2010-12-7 上午08:50:27 laojiang created
	 * @param page
	 * @param filters
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<Log> searchLog(final Page<Log> page, final List<PropertyFilter> filters) {

		return logDao.findPage(page, filters);
	}
	
	public int deleteAllLog() {
		
		return logDao.deleteAll();
	}

	/**
	 * Description: 根据日志的编号删除日志
	 * @Version1.0 2010-12-7 上午08:50:57 laojiang created
	 * @param id
	 */
	public void deleteLog(Long id) {

		logDao.delete(id);
		logger.debug("删除log{}", id);
	}

	@Autowired
	public void setLogDao(LogDao logDao) {
		this.logDao = logDao;
	}

}
