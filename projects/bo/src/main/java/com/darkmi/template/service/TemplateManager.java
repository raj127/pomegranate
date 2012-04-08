package com.darkmi.template.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import com.darkmi.entity.template.Template;
import com.darkmi.template.dao.TemplateDao;

/**
 * 作业规程任务管理Manager
 */
@Component
@Transactional
public class TemplateManager {
	private static Logger logger = LoggerFactory.getLogger(TemplateManager.class);
	private TemplateDao templateDao;

	/**
	 * 保存模板信息
	 */
	public void saveTemplate(Template template) {
		templateDao.save(template);
		logger.debug("保存模板信息成功.");
	}

	/**
	 * 根据主键删除模板信息
	 * 
	 * @param id
	 */
	public void deleteTemplate(Long id) {
		templateDao.delete(id);
	}

	/**
	 * 根据主键得到模板信息
	 */
	@Transactional(readOnly = true)
	public Template getTemplate(Long id) {
		return templateDao.get(id);
	}

	/**
	 * 得到所有的模板信息
	 */
	public List<Template> getAllTemplate() {
		return templateDao.getAll();
	}

	// /**
	// * 根据Id得到作业规程任务信息
	// * @return
	// */
	// @Transactional(readOnly = true)
	// public Task getTask(long id) {
	// return taskDao.findUniqueBy("id", id);
	// }

	// /**
	// * 判断taskName是否唯一
	// */
	// @Transactional(readOnly = true)
	// public boolean isTaskNameUnique(String newTaskName, String oldTaskName) {
	// return taskDao.isPropertyUnique("name", newTaskName, oldTaskName);
	// }

	/**
	* 根据属性查询模板信息.
	*/
	@Transactional(readOnly = true)
	public Page<Template> searchTemplate(Page<Template> page, List<PropertyFilter> filters) {
		return templateDao.findPage(page, filters);
	}

	@Autowired
	public void setTemplateDao(TemplateDao templateDao) {
		this.templateDao = templateDao;
	}

}
