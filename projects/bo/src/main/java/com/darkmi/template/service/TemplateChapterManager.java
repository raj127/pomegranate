package com.darkmi.template.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import com.darkmi.entity.template.TemplateChapter;
import com.darkmi.template.dao.TemplateChapterDao;

/**
 * 作业规程模板目录管理Manager
 */
@Component
@Transactional
public class TemplateChapterManager {
	private static Logger logger = LoggerFactory.getLogger(TemplateChapterManager.class);
	private TemplateChapterDao tcDao;

	/**
	 * 保存作业规程任务信息
	 */
	public void saveTemplateChapter(TemplateChapter templateChapter) {
		tcDao.save(templateChapter);
		logger.debug("保存TemplateChapter信息成功.");
	}

	/**
	 * 根据主键删除作业规程任务信息
	 * @param id
	 */
	public void deleteTemplateChapter(Long id) {
		tcDao.delete(id);
		logger.debug("删除TemplateChapter id={}", id);
	}

	/**
	 * 根据主键得到章节信息
	 */
	@Transactional(readOnly = true)
	public TemplateChapter getTemplateChapter(Long id) {
		return tcDao.get(id);
	}

	/**
	 * 得到所有的作业规程任务列表
	 */
	public List<TemplateChapter> getAllTemplateChapter() {
		return tcDao.getAll();
	}

	/**
	 * 得到作业规程模板的一级目录.
	 */
	public List<TemplateChapter> getParentTemplateChapter() {
		return tcDao.findBy("parentId", new Long(0));
	}

	/**
	 * 根据Id得到作业规程任务信息
	 * @return
	 */
	@Transactional(readOnly = true)
	public TemplateChapter getTemplateChapter(long id) {
		return tcDao.findUniqueBy("id", id);
	}

	/**
	 * 根据属性查询任务信息
	 */
	@Transactional(readOnly = true)
	public Page<TemplateChapter> searchTemplateChapter(Page<TemplateChapter> page, List<PropertyFilter> filters) {
		return tcDao.findPage(page, filters);
	}

	@Transactional(readOnly = true)
	public Page<TemplateChapter> searchTemplateChapter(Page<TemplateChapter> page, String where,
			Map<String, Object> values) {
		return tcDao.findPageByWhere(page, where, values);
	}

	@Autowired
	public void setTemplateDao(TemplateChapterDao templateDao) {
		this.tcDao = templateDao;
	}
}
