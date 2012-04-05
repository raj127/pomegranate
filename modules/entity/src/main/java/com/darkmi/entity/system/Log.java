package com.darkmi.entity.system;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.darkmi.entity.IdEntity;
import com.google.common.base.Objects;

@Entity
@Table(name = "T_SYSTEM_LOG")
public class Log extends IdEntity {

	private String threadName;
	private String loggerName;
	private Date logTime;
	private String level;
	private String message;

	@Column(name = "thread_name")
	public String getThreadName() {
		return this.threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	@Column(name = "logger_name")
	public String getLoggerName() {
		return this.loggerName;
	}

	public void setLoggerName(String loggerName) {
		this.loggerName = loggerName;
	}

	@Column(name = "log_time", nullable = false, length = 19)
	public Date getLogTime() {
		return this.logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	@Column(name = "level", length = 20)
	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Column(name = "message")
	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("id", id).add("loggerName", loggerName).add("message", message)
				.add("threadName", threadName).add("level", level).add("logTime", logTime).toString();
	}
}
