package com.darkmi.edit.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.darkmi.entity.task.TaskChapter;

/**
 * 章节管理DAO
 */
@Component
public class TaskChapterDao extends HibernateDao<TaskChapter, Long> {

	private static final String GET_BY_TID = "from TaskChapter t where t.task.id=? order by displayOrder asc";

	/**
	 * 通过任务ID得到其下所有章节.
	 */
	public List<TaskChapter> getTcsByTaskId(Long taskId) {
		return find(GET_BY_TID, taskId);
	}

}

