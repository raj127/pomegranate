package com.darkmi.entity.task;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.darkmi.entity.AuditableEntity;
import com.darkmi.entity.StateEnum;

/**
 * Description: 作业规程章节实体类.
 * Copyright (c) darkmi.com
 * @version 1.0  2012-4-13 下午01:25:14 DarkMi created.
 */
@Entity
@Table(name = "t_task_chapter")
public class TaskChapter extends AuditableEntity {
	private String chapterId;
	private String chapterName;
	private String fileName;
	private String description;
	private Task task;

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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "task_id")
	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
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
		return "TemplateChapter [chapterId=" + chapterId + ", chapterName=" + chapterName + ", fileName=" + fileName
				+ ", description=" + description + ", template=" + task + ", parentId=" + parentId + ", displayOrder="
				+ displayOrder + ", state=" + state + "]";
	}

}
