package com.darkmi.edit.dao;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.darkmi.entity.task.TaskChapter;

/**
 * 章节管理DAO
 */
@Component
public class TaskChapterDao extends HibernateDao<TaskChapter, Long> {

	private static final String GET_BY_WHERE = "from TaskChapter t where 1=1 ";

	public Page<TaskChapter> findPageByWhere(Page<TaskChapter> page, String where, Map<String, Object> values) {
		return this.findPage(page, GET_BY_WHERE + where, values);
	}
}

