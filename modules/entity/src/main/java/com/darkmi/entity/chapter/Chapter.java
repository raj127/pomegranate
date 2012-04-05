package com.darkmi.entity.chapter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.darkmi.entity.AuditableEntity;

@Entity
@Table(name = "t_chapter")
public class Chapter extends AuditableEntity {

	private String chapterId;
	private String chapterName;
	private String description;
	private int state;
	private int parentId;

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

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "state")
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@Column(name = "parent_id")
	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return "Chapter [chapterId=" + chapterId + ", chapterName="
				+ chapterName + ", description=" + description + ", state="
				+ state + ", parentId=" + parentId + "]";
	}

}
