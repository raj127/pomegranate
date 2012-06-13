package com.darkmi.edit.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.PropertyFilter;

import com.darkmi.edit.dao.TaskChapterDao;
import com.darkmi.entity.task.TaskChapter;

/**
 * Description: 任务章节管理Manager.
 * Copyright (c) www.darkmi.com
 * All Rights Reserved.
 * @version 1.0  2012-05-04 上午09:20:11 DarkMi created
 */
@Component
@Transactional
public class TaskChapterManager {
	private static Logger logger = LoggerFactory.getLogger(TaskChapterManager.class);
	private TaskChapterDao taskChapterDao;

	/**
	 * 保存作业规程目录信息.
	 */
	public void saveTaskChapter(TaskChapter taskChapter) {
		taskChapterDao.save(taskChapter);
		logger.debug("保存TaskChapter信息成功.");
	}

	/**
	 * 根据主键删除作业规程任务信息
	 * @param id
	 */
	public void deleteTaskChapter(Long id) {
		taskChapterDao.delete(id);
		logger.debug("删除taskChapter id={}", id);
	}

	/**
	 * 根据主键得到作业规程任务信息
	 */
	@Transactional(readOnly = true)
	public TaskChapter getTaskChapter(Long id) {
		//return taskDao.get(id);
		return null;
	}

	/**
	 * 根据作业规程任务ID获取其下所有章节.
	 */
	@Transactional(readOnly = true)
	public List<TaskChapter> getTcsByTaskId(Long taskId) {
		return taskChapterDao.getTcsByTaskId(taskId);
	}

	/**
	 * 得到所有的作业规程任务列表
	 */
	public List<TaskChapter> getAllTaskChapter() {
		return taskChapterDao.getAll();
	}

	/**
	 * 根据Id得到作业规程任务信息
	 * @return
	 */
	@Transactional(readOnly = true)
	public TaskChapter getTaskChapter(long id) {
		return taskChapterDao.findUniqueBy("id", id);
	}

	/**
	 * 根据属性查询任务信息
	 */
	@Transactional(readOnly = true)
	public List<TaskChapter> searchTaskChapter(List<PropertyFilter> filters) {
		return taskChapterDao.find(filters);
	}

	@Autowired
	public void setTaskDao(TaskChapterDao taskDao) {
		this.taskChapterDao = taskDao;
	}

}
