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

	private final static String FIND_ROOT_CATEGORY = " from SpecificationChapter c where c.parentCategory is null";
	private final static String FIND_BY_NAME = "from SpecificationChapter c where c.name like ?";

	public SpecificationChapter findRootChapter() {
		return findUnique(FIND_ROOT_CATEGORY);
	}

	public List<SpecificationChapter> findByName(String name) {
		return find(FIND_BY_NAME, "%" + name + "%");
	}
}
