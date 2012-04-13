package com.darkmi.edit.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import com.darkmi.edit.dao.TaskChapterDao;
import com.darkmi.entity.task.TaskChapter;

/**
 * 作业规程任务管理Manager
 */
@Component
@Transactional
public class TaskChapterManager {
	private static Logger logger = LoggerFactory.getLogger(TaskChapterManager.class);
	private TaskChapterDao taskChapterDao;

	/**
	 * 保存作业规程任务信息
	 */
	public void saveTaskChapter(TaskChapter taskChapter) {
		//taskDao.save(task);
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
	public Page<TaskChapter> searchTaskChapter(Page<TaskChapter> page, List<PropertyFilter> filters) {
		return taskChapterDao.findPage(page, filters);
	}

	@Transactional(readOnly = true)
	public Page<TaskChapter> searchTaskChapter(Page<TaskChapter> page, String where, Map<String, Object> values) {
		return taskChapterDao.findPageByWhere(page, where, values);
	}

	@Autowired
	public void setTaskDao(TaskChapterDao taskDao) {
		this.taskChapterDao = taskDao;
	}

}
