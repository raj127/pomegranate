package com.darkmi.template.dao;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.darkmi.entity.template.TemplateChapter;

/**
 * 章节管理DAO.
 */
@Component
public class TemplateChapterDao extends HibernateDao<TemplateChapter, Long> {

	private static final String GET_BY_WHERE = "from TemplateChapter t where 1=1 ";

	public Page<TemplateChapter> findPageByWhere(Page<TemplateChapter> page, String where, Map<String, Object> values) {
		return this.findPage(page, GET_BY_WHERE + where, values);
	}
}
