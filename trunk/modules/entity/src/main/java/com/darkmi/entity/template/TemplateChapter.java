package com.darkmi.entity.template;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.darkmi.entity.AuditableEntity;
import com.darkmi.entity.StateEnum;

/**
 * Description: 作业规程模板子级目录实体类.
 * Copyright (c) www.darkmi.com
 * All Rights Reserved.
 * @version 1.0  2012-05-04 上午09:20:11 DarkMi created
 */
@Entity
@Table(name = "t_template_chapter")
public class TemplateChapter extends AuditableEntity {
	private String chapterId;
	private String chapterName;
	private String fileName;
	private String description;
	private Integer templateId;
	private Long parentId;
	private Integer displayOrder;
	private StateEnum state;

	@Column(name = "chapter_id")
	public String getChapterId() {
		return chapterId;
	}

	public void setChapterId(String chapterId) {
		this.chapterId = chapterId;
	}

	@Column(name = "chapter_name")
	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	@Column(name = "file_name")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "template_id")
	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "parent_id")
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Column(name = "state")
	@Enumerated(EnumType.STRING)
	public StateEnum getState() {
		return state;
	}

	public void setState(StateEnum state) {
		this.state = state;
	}

	@Column(name = "display_order")
	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	@Override
	public String toString() {
		return "TaskChapter [chapterId=" + chapterId + ", chapterName=" + chapterName + ", description=" + description
				+ ", state=" + state + ", templateId=" + templateId + "]";
	}
}
