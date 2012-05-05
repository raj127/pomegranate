package com.darkmi.template.dao;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.darkmi.entity.template.Template;

/**
 * Description: 作业规程模板DAO.
 * Copyright (c) www.darkmi.com
 * All Rights Reserved.
 * @version 1.0  2012-05-04 上午09:20:11 DarkMi created
 */
@Component
public class TemplateDao extends HibernateDao<Template, Long> {

	private static final String GET_BY_WHERE = "from Template t where 1=1 ";

	public Page<Template> findPageByWhere(Page<Template> page, String where, Map<String, Object> values) {
		return this.findPage(page, GET_BY_WHERE + where, values);
	}

}
