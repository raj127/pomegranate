package com.darkmi.task.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import com.darkmi.entity.task.Task;
import com.darkmi.task.dao.TaskDao;

/**
 * Description: 任务管理Manager.
 * Copyright (c) www.darkmi.com
 * All Rights Reserved.
 * @version 1.0  2012-05-04 上午09:20:11 DarkMi created
 */
@Component
@Transactional
public class TaskManager {
	private static Logger logger = LoggerFactory.getLogger(TaskManager.class);
	private TaskDao taskDao;

	/**
	 * 保存作业规程任务信息
	 */
	public void saveTask(Task task) {
		taskDao.save(task);
		logger.debug("保存task id={},name={}", new Object[] { task.getId(), task.getTaskName() });
	}

	/**
	 * 根据主键删除作业规程任务信息
	 * @param id
	 */
	public void deleteTask(Long id) {
		taskDao.delete(id);
		logger.debug("删除task id={}", id);
	}

	/**
	 * 根据主键得到作业规程任务信息
	 */
	@Transactional(readOnly = true)
	public Task getTask(Long id) {
		return taskDao.get(id);
	}

	/**
	 * 得到所有的作业规程任务列表
	 */
	public List<Task> getAllTask() {
		return taskDao.getAll();
	}

	/**
	 * 根据Id得到作业规程任务信息
	 * @return
	 */
	@Transactional(readOnly = true)
	public Task getTask(long id) {
		return taskDao.findUniqueBy("id", id);
	}

	/**
	 * 判断taskName是否唯一
	 */
	@Transactional(readOnly = true)
	public boolean isTaskNameUnique(String newTaskName, String oldTaskName) {
		return taskDao.isPropertyUnique("taskName", newTaskName, oldTaskName);
	}

	/**
	 * 根据属性查询任务信息
	 */
	@Transactional(readOnly = true)
	public Page<Task> searchTask(Page<Task> page, List<PropertyFilter> filters) {
		return taskDao.findPage(page, filters);
	}

	@Autowired
	public void setTaskDao(TaskDao taskDao) {
		this.taskDao = taskDao;
	}

}
