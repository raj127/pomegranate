package com.darkmi.entity.task;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.darkmi.entity.AuditableEntity;

/**
 * Description: 目录实体类.
 *  
 * Copyright (c) darkmi.com
 * @version 1.0  2012-4-13 下午01:25:14 DarkMi created.
 */
@Entity
@Table(name = "t_task_chapter")
public class TaskChapter extends AuditableEntity {

	private String chapterId;
	private String chapterName;
	private String description;
	private Integer state;
	private Integer taskId;

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

	@Column(name = "task_id")
	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
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
				+ ", state=" + state + ", taskId=" + taskId + "]";
	}
}
