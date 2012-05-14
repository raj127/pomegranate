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
import com.darkmi.entity.system.Company;

/**
 * Description: 任务实体类.
 *  
 * Copyright (c) darkmi.com
 * @version 1.0  2012-4-13 下午01:25:14 DarkMi created.
 */
@Entity
@Table(name = "t_task")
public class Task extends AuditableEntity {
	private String taskName;
	private String description;
	
	private String path;
	private Company company;
	private StateEnum state;
	private CreateTypeEnum createType;

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

	@Column(name = "path")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	@Column(name = "state")
	@Enumerated(EnumType.STRING)
	public StateEnum getState() {
		return state;
	}

	public void setState(StateEnum state) {
		this.state = state;
	}

	@Column(name = "create_type")
	@Enumerated(EnumType.STRING)
	public CreateTypeEnum getCreateType() {
		return createType;
	}

	public void setCreateType(CreateTypeEnum createType) {
		this.createType = createType;
	}


	@Override
	public String toString() {
		return "Task [taskName=" + taskName + ", description=" + description + ", state=" + state + ", path=" + path
				+ ", company=" + company + "]";
	}

}
