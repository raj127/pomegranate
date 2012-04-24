package com.darkmi.system.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import com.darkmi.entity.system.Company;
import com.darkmi.system.dao.CompanyDao;

/**
 * Description: 公司信息的管理类.
 * Copyright (c) darkmi.com
 * All Rights Reserved.
 * @version 1.0  2012-03-17 上午08:31:05 darkmi created.
 */
@Component
@Transactional
public class CompanyManager {
	private static Logger logger = LoggerFactory.getLogger(CompanyManager.class);
	private CompanyDao companyDao;

	/**
	 * 根据主键得到Company的信息
	 */
	@Transactional(readOnly = true)
	public Company getCompany(Long id) {
		return companyDao.get(id);
	}

	/**
	 * 根据名字得到Company的信息
	 */
	public Company getCompany(String name) {
		return companyDao.findUniqueBy("name", name);
	}

	/**
	 * 判断公司名字是否唯一
	 * @return 唯一返回true 不唯一返回false
	 */
	@Transactional(readOnly = true)
	public boolean isNameUnique(String newName, String oldName) {
		return companyDao.isPropertyUnique("companyName", newName, oldName);
	}


	/**
	 * 保存Company的信息,包括更新和添加
	 */
	public void saveCompany(Company Company) {
		companyDao.save(Company);
		logger.debug("保存Company {}", Company.getId());
	}

	/**
	 * 根据主键删除Company的信息
	 */
	public void deleteCompany(Long id) {
		companyDao.delete(id);
		logger.debug("删除Company {}", id);
	}

	/**
	 * 根据条件查询Company的信息
	 */
	@Transactional(readOnly = true)
	public Page<Company> searchCompany(Page<Company> page, List<PropertyFilter> filters) {
		return companyDao.findPage(page, filters);
	}

	@Autowired
	public void setCompanyDao(CompanyDao CompanyDao) {
		this.companyDao = CompanyDao;
	}
}

