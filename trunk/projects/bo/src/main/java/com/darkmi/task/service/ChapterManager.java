package com.darkmi.task.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import com.darkmi.entity.chapter.Chapter;
import com.darkmi.task.dao.ChapterDao;

/**
 * 作业规程任务管理Manager
 */
@Component
@Transactional
public class ChapterManager {
	private static Logger logger = LoggerFactory.getLogger(ChapterManager.class);
	private ChapterDao chapterDao;

	/**
	 * 保存章节信息
	 */
	public void saveChapter(Chapter chapter) {
		chapterDao.save(chapter);
		// logger.debug("保存task id={},name={}", new Object[] { task.getId(),
		// task.getTaskName() });
	}

	/**
	 * 根据主键删除作业规程任务信息
	 * 
	 * @param id
	 */
	public void deleteTask(Long id) {
		// ChapterDao.delete(id);
		logger.debug("删除task id={}", id);
	}

	/**
	 * 根据主键得到作业规程的章节信息
	 */
	@Transactional(readOnly = true)
	public Chapter getChapter(Long id) {
		return chapterDao.get(id);

	}

	/**
	 * 得到所有的作业规程任务列表
	 */
	public List<Chapter> getAllChapter() {
		return chapterDao.getAll();
	}

	/**
	 * 根据Id得到作业规程任务信息
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public Chapter getChpater(long id) {
		return chapterDao.findUniqueBy("id", id);
	}

	/**
	 * 判断taskName是否唯一
	 */
	// @Transactional(readOnly = true)
	// public boolean isTaskNameUnique(String newTaskName, String oldTaskName) {
	// return taskDao.isPropertyUnique("name", newTaskName, oldTaskName);
	// }

	/**
	 * Description: 根据属性查询员工信息
	 */
	@Transactional(readOnly = true)
	public Page<Chapter> searchChapter(Page<Chapter> page, List<PropertyFilter> filters) {
		return chapterDao.findPage(page, filters);
	}
	
	@Transactional(readOnly = true)
	public Page<Chapter> searchChapter(Page<Chapter> page, String where, Map<String, Object> values) {
		return chapterDao.findPageByWhere(page, where, values);
	}

	@Autowired
	public void setChapterDao(ChapterDao chapterDao) {
		this.chapterDao = chapterDao;
	}

}
