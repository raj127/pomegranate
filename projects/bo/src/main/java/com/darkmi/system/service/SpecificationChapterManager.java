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
	private SpecificationChapterDao ChapterDao;
	
	public List<SpecificationChapter> getAllChapter(){
		return ChapterDao.getAll();
	}

	/**
	 * Description: 保存章节
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
	 * Description: 
	 * @param Chapter
	 * @param parentId
	 * @param isLeaf
	 */
	public void saveChapter(SpecificationChapter Chapter, Long parentId, boolean isLeaf) {
		Chapter.setParentChapter(getChapter(parentId));
		Chapter.getParentChapter().getChapterList().add(Chapter);
		Chapter.setIsLeaf(isLeaf);
		saveChapter(Chapter);
	}

	/**
	 * Description: 
	 * @param Chapter
	 */
	public void saveChapter(SpecificationChapter Chapter) {
		ChapterDao.save(Chapter);
	}

	/**
	 * Description: 根据主键删除分类
	 * @param id
	 */
	public void deleteChapter(Long id) {
		SpecificationChapter Chapter = getChapter(id);
		if (Chapter.getTreeIndex().length() <= 9) {
			throw new ServiceException("该分类不能删除");
		}
		SpecificationChapter parentChapter = Chapter.getParentChapter();
		parentChapter.getChapterList().remove(Chapter);
		Chapter.setParentChapter(null);
		if (parentChapter.getChapterList().size() == 0) {
			Chapter.setIsLeaf(true);
		}
		ChapterDao.delete(id);
		logger.debug("删除分类 {}", id);
	}
	
	/**
	 * 根据条件查询Company的信息
	 */
	@Transactional(readOnly = true)
	public Page<SpecificationChapter> searchChapter(Page<SpecificationChapter> page, List<PropertyFilter> filters) {
		return ChapterDao.findPage(page, filters);
	}


	@Transactional(readOnly = true)
	public List<SpecificationChapter> getChapterByName(String name) {
		return ChapterDao.findByName(name);
	}

	/**
	 * Description: 根据主键查询分类
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public SpecificationChapter getChapter(Long id) {
		return ChapterDao.get(id);
	}

	@Transactional(readOnly = true)
	public SpecificationChapter getChapter(String treeIndex) {
		return ChapterDao.findUniqueBy("treeIndex", treeIndex);
	}

	/**
	 * Description: 查询根分类
	 * @return
	 */
	@Transactional(readOnly = true)
	public SpecificationChapter getRootChapter() {
		return ChapterDao.findRootChapter();
	}

	@Autowired
	public void setChapterDao(SpecificationChapterDao ChapterDao) {
		this.ChapterDao = ChapterDao;
	}
}