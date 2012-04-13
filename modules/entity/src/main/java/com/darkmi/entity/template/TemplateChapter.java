package com.darkmi.entity.template;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.darkmi.entity.AuditableEntity;

/**
 * Description: 目录实体类.
 * Copyright (c) darkmi.com
 * @version 1.0  2012-4-13 下午01:25:14 DarkMi created.
 */
@Entity
@Table(name = "t_template_chapter")
public class TemplateChapter extends AuditableEntity {

	private String chapterId;
	private String chapterName;
	private String description;
	private Integer state;
	private Integer templateId;

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

	@Column(name = "state")
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "TaskChapter [chapterId=" + chapterId + ", chapterName=" + chapterName + ", description=" + description
				+ ", state=" + state + ", templateId=" + templateId + "]";
	}
}
