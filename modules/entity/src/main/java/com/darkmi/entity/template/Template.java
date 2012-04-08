package com.darkmi.entity.template;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.darkmi.entity.AuditableEntity;

@Entity
@Table(name = "t_template")
public class Template extends AuditableEntity {

	private String templateName;
	private String path;
	private String description;

	private int state;

	@Column(name = "template_name")
	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
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

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Template [templateName=" + templateName + ", path=" + path + ", desdescription=" + description
				+ ", state=" + state + "]";
	}

}
