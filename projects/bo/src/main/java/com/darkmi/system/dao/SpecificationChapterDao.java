package com.darkmi.system.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.darkmi.entity.system.SpecificationChapter;

/**
 * Description: 规范章节DAO.
 * Copyright (c) darkmi.com
 * All Rights Reserved.
 * @version 1.0  2012-2-28 上午10:18:26 darkmi created
 */
@Component
public class SpecificationChapterDao extends HibernateDao<SpecificationChapter, Long> {

	private final static String FIND_ROOT_CATEGORY = " from SpecificationChapter c where c.parentChapter is null";
	private final static String FIND_SPECIFICATION = " from SpecificationChapter c where c.parentChapter.id=1";
	private final static String FIND_CHILD_SC = " from SpecificationChapter c where c.parentChapter.id=?";
	private final static String FIND_BY_NAME = "from SpecificationChapter c where c.name like ?";

	/**
	 * 获得根节点.
	 * @return
	 */
	public SpecificationChapter findRootChapter() {
		return findUnique(FIND_ROOT_CATEGORY);
	}

	/**
	 * 获取规范名称.
	 * 目前有两本规范.
	 * @return
	 */
	public List<SpecificationChapter> getSpecifications() {
		return find(FIND_SPECIFICATION);
	}

	/**
	*获得下一级的所有章节.
	 * @return
	 */
	public List<SpecificationChapter> getChildSC(Long parentId) {
		return find(FIND_CHILD_SC, parentId);
	}

	/**
	 * 通过名称获取规范内容.
	 * @param name
	 * @return
	 */
	public List<SpecificationChapter> findByName(String name) {
		return find(FIND_BY_NAME, "%" + name + "%");
	}
}
