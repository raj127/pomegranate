package com.darkmi.entity.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.darkmi.entity.AuditableEntity;

@Entity
@Table(name = "T_SYSTEM_CONFIG")
public class Config extends AuditableEntity {

	private String name;
	private String value;
	private String description;

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "value")
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Config [name=" + name + ", value=" + value + ", description=" + description + "]";
	}
}
