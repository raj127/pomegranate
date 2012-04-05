package com.darkmi.system.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import com.darkmi.entity.system.Config;
import com.darkmi.system.dao.ConfigDao;

/**
 * Description: 配置相关的管理类
 * Copyright (c) darkmi.com
 * All Rights Reserved.
 * @version 1.0  2012-03-17 上午08:31:05 darkmi created.
 */
@Component
@Transactional
public class ConfigManager {
	private static Logger logger = LoggerFactory.getLogger(ConfigManager.class);
	private ConfigDao configDao;

	/**
	 * 根据主键得到Config的信息
	 */
	@Transactional(readOnly = true)
	public Config getConfig(Long id) {

		return configDao.get(id);
	}

	/**
	 * 判断配置名字是否唯一
	 * @return 唯一返回true 不唯一返回false
	 */
	@Transactional(readOnly = true)
	public boolean isNameUnique(String newName, String oldName) {
		return configDao.isPropertyUnique("name", newName, oldName);
	}

	/**
	 * 根据名字得到Config的信息
	 */
	public Config getConfig(String name) {

		return configDao.findUniqueBy("name", name);
	}

	/**
	 * 保存Config的信息,包括更新和添加
	 */
	public void saveConfig(Config config) {
		configDao.save(config);
		logger.debug("保存Config {}", config.getId());
	}

	/**
	 * 根据主键删除Config的信息
	 */
	public void deleteConfig(Long id) {
		configDao.delete(id);
		logger.debug("删除Config {}", id);
	}

	/**
	 * 根据条件查询Config的信息
	 */
	@Transactional(readOnly = true)
	public Page<Config> searchConfig(Page<Config> page, List<PropertyFilter> filters) {
		return configDao.findPage(page, filters);
	}

	@Autowired
	public void setConfigDao(ConfigDao configDao) {
		this.configDao = configDao;
	}
}
