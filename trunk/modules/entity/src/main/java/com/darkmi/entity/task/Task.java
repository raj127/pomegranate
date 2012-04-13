package com.darkmi.entity.task;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.darkmi.entity.AuditableEntity;

@Entity
@Table(name = "t_task")
public class Task extends AuditableEntity {

	private String taskId;
	private String taskName;
	private String description;
	private int state;
	private String path;

	@Column(name = "task_id")
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	@Column(name = "task_name")
	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
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

	@Column(name = "path")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", taskName=" + taskName + ", desdescription=" + description + ", state="
				+ state + ", path=" + path + "]";
	}

}
