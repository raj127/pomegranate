package com.darkmi.task.dao;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.darkmi.entity.chapter.Chapter;

/**
 * 章节管理DAO
 */
@Component
public class ChapterDao extends HibernateDao<Chapter, Long> {

	private static final String GET_BY_WHERE = "from Chapter t where 1=1 ";

	public Page<Chapter> findPageByWhere(Page<Chapter> page, String where, Map<String, Object> values) {
		return this.findPage(page, GET_BY_WHERE + where, values);
	}

}
