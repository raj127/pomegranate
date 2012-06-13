package com.darkmi.system.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import com.darkmi.entity.system.SpecificationChapter;
import com.darkmi.system.dao.SpecificationChapterDao;
import com.darkmi.util.ServiceException;

/**
 * Description: 规范章节的管理类.
 * Copyright (c) darkmi.com
 * All Rights Reserved.
 * @version 1.0  2012-03-17 上午08:31:05 darkmi created.
 */
@Component
@Transactional
public class SpecificationChapterManager {
	private static Logger logger = LoggerFactory.getLogger(SpecificationChapterManager.class);
	private SpecificationChapterDao scDao;

	/**
	 * 根据条件查询规范章节.
	 */
	@Transactional(readOnly = true)
	public Page<SpecificationChapter> searchChapter(Page<SpecificationChapter> page, List<PropertyFilter> filters) {
		return scDao.findPage(page, filters);
	}

	/**
	 * 通过章节名称查询章节信息.
	 */
	@Transactional(readOnly = true)
	public List<SpecificationChapter> getChapterByName(String chapterName) {
		return scDao.findByName(chapterName);
	}

	/**
	 * 根据主键查询规范章节.
	 */
	@Transactional(readOnly = true)
	public SpecificationChapter getChapter(Long id) {
		return scDao.get(id);
	}

	/**
	 * 根据treeIndex获取章节信息.
	 */
	@Transactional(readOnly = true)
	public SpecificationChapter getChapter(String treeIndex) {
		return scDao.findUniqueBy("treeIndex", treeIndex);
	}

	/**
	 * 查询根章节.
	 */
	@Transactional(readOnly = true)
	public SpecificationChapter getRootChapter() {
		return scDao.findRootChapter();
	}

	/**
	 * 获取所有章节信息.
	 * @return
	 */
	public List<SpecificationChapter> getAllChapter() {
		return scDao.getAll();
	}

	/*~~~~~~~~~~~ 更新及删除类方法 ~~~~~~~~~~~~~~~~~*/

	/**
	 * 保存章节.
	 */
	public void saveChapter(SpecificationChapter Chapter, Long parentId) {
		Chapter.setParentChapter(getChapter(parentId));
		Chapter.getParentChapter().setIsLeaf(false);
		Chapter.getParentChapter().getChapterList().add(Chapter);
		if (Chapter.getChapterList().size() == 0) {
			Chapter.setIsLeaf(true);
		} else {
			Chapter.setIsLeaf(false);
		}
		saveChapter(Chapter);
	}

	/**
	 * 保存章节. 
	 */
	public void saveChapter(SpecificationChapter Chapter, Long parentId, boolean isLeaf) {
		Chapter.setParentChapter(getChapter(parentId));
		Chapter.getParentChapter().getChapterList().add(Chapter);
		Chapter.setIsLeaf(isLeaf);
		saveChapter(Chapter);
	}

	/**
	 * 保存章节. 
	 */
	public void saveChapter(SpecificationChapter Chapter) {
		scDao.save(Chapter);
	}

	/**
	 * 根据主键删除章节.
	 */
	public void deleteChapter(Long id) {
		SpecificationChapter Chapter = getChapter(id);
		if (Chapter.getTreeIndex().length() <= 9) {
			throw new ServiceException("该章节不能删除");
		}

		SpecificationChapter parentChapter = Chapter.getParentChapter();
		parentChapter.getChapterList().remove(Chapter);
		Chapter.setParentChapter(null);
		if (parentChapter.getChapterList().size() == 0) {
			Chapter.setIsLeaf(true);
		}
		scDao.delete(id);
		logger.debug("删除章节{}", id);
	}

	/*~~~~~~~~~~~业务逻辑类注入~~~~~~~~~~~~~~~~~*/
	@Autowired
	public void setScDao(SpecificationChapterDao scDao) {
		this.scDao = scDao;
	}
}
