package com.darkmi.entity;

import org.hibernate.HibernateException;
import org.hibernate.event.SaveOrUpdateEvent;
import org.hibernate.event.SaveOrUpdateEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.darkmi.entity.system.SpecificationChapter;

public class TreeListener implements SaveOrUpdateEventListener {
	private static final long serialVersionUID = 7081325756014617875L;
	private static Logger logger = LoggerFactory.getLogger(AuditListener.class);

	public void onSaveOrUpdate(SaveOrUpdateEvent event) throws HibernateException {
		Object object = event.getObject();
		if (object instanceof SpecificationChapter) {
			SpecificationChapter category = (SpecificationChapter) object;
			if (category.getId() == null) {
				category.calculateTreeIndex();
				logger.debug("计算category={}的treeIndex", category.getName());
			}
		}
	}
}
