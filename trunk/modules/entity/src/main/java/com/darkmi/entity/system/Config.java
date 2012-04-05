package com.darkmi.entity.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.darkmi.entity.AuditableEntity;
import com.google.common.base.Objects;

@Entity
@Table(name = "T_SYSTEM_CONFIG")
public class Config extends AuditableEntity {

	private String name;
	private String value;
	private SystemEnum system;
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

	@Column(name = "system", length = 20)
	@Enumerated(EnumType.STRING)
	public SystemEnum getSystem() {
		return system;
	}

	public void setSystem(SystemEnum system) {
		this.system = system;
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
		return Objects.toStringHelper(this).add("name", name).add("value", value).add("description", description)
				.add("system", system).add("id", id).toString();
	}
}
