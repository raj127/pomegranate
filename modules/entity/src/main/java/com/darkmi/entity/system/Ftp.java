package com.darkmi.entity.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.darkmi.entity.AuditableEntity;
import com.google.common.base.Objects;

@Entity
@Table(name = "T_SYSTEM_FTP", uniqueConstraints = @UniqueConstraint(columnNames = "ftp_id"))
public class Ftp extends AuditableEntity {

	private String ftpId;
	private String name;
	private String address;
	private String userName;
	private String userPass;
	private Integer port;
	private Boolean state;
	private String description;
	private SystemEnum system;
	private String path;

	@Column(name = "ftp_id", unique = true, length = 50)
	public String getFtpId() {
		return this.ftpId;
	}

	public void setFtpId(String ftpId) {
		this.ftpId = ftpId;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "address", length = 100)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "user_name", length = 50)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "user_pass", length = 50)
	public String getUserPass() {
		return this.userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	@Column(name = "port")
	public Integer getPort() {
		return this.port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	@Column(name = "state")
	public Boolean getState() {
		return this.state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "system", length = 20)
	@Enumerated(EnumType.STRING)
	public SystemEnum getSystem() {
		return system;
	}

	public void setSystem(SystemEnum system) {
		this.system = system;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("ftpId", ftpId).add("description", description).add("name", name)
				.add("path", path).add("userName", userName).add("userPass", userPass).add("address", address)
				.add("port", port).add("state", state).add("system", system).add("id", id).toString();
	}
}
