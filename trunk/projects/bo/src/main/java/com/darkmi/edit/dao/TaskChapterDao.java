package com.darkmi.edit.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.darkmi.entity.task.TaskChapter;

/**
 * Description: 任务章节管理DAO.
 * Copyright (c) darkmi
 * All Rights Reserved.
 * @version 1.0  2012-05-23 下午01:36:31 darkmi created
 */
@Component
public class TaskChapterDao extends HibernateDao<TaskChapter, Long> {

	private static final String GET_BY_TID = "from TaskChapter t where t.task.id=? order by displayOrder asc";

	private static final String GET_LEVEL_ONE = "from TaskChapter t where t.task.id=? and parentId=? order by displayOrder asc";

	/**
	 * 通过任务ID得到其下所有章节.
	 */
	public List<TaskChapter> getTcsByTaskId(Long taskId) {
		return find(GET_BY_TID, taskId);
	}

	/**
	 * 通过任务ID得到其下所有章节.
	 */
	public List<TaskChapter> getLevelOne(Long taskId) {
		return find(GET_LEVEL_ONE, taskId, new Long(0));
	}

}
