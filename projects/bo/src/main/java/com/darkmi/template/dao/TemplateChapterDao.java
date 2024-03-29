package com.darkmi.template.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.darkmi.entity.template.TemplateChapter;

/**
 * Description: 模板章节管理DAO.
 * Copyright (c) www.darkmi.com
 * All Rights Reserved.
 * @version 1.0  2012-05-10 上午09:20:11 DarkMi created
 */
@Component
public class TemplateChapterDao extends HibernateDao<TemplateChapter, Long> {

	private static final String GET_BY_WHERE = "from TemplateChapter t where 1=1 ";
	private static final String GET_BY_TID = "from TemplateChapter t where t.template.id=? order by displayOrder asc";
	private static final String GET_TC_LEVEL_ONE = "from TemplateChapter t where t.template.id=? and isLeaf = false order by displayOrder asc";

	
	/**
	 * 通过模板ID得到其所有章节.
	 */
	public List<TemplateChapter> getTcsByTemplateId(Long templateId) {
		return find(GET_BY_TID, templateId);
	}
	
	/**
	 * 获取指定模板的一级目录.
	 */
	public List<TemplateChapter> getTcLevelOne(Long templateId) {
		return find(GET_TC_LEVEL_ONE, templateId);
	}

	public Page<TemplateChapter> findPageByWhere(Page<TemplateChapter> page, String where, Map<String, Object> values) {
		return this.findPage(page, GET_BY_WHERE + where, values);
	}
}
