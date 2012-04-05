package com.darkmi.entity.system;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.darkmi.entity.IdEntity;

@Entity
@Table(name = "T_SYSTEM_MEMCACHED_KEY")
public class MemcachedKey extends IdEntity {

	private String memcachedKey;
	private Date createTime;

	public String getMemcachedKey() {
		return memcachedKey;
	}

	public void setMemcachedKey(String memcachedKey) {
		this.memcachedKey = memcachedKey;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "MemcachedKey [memcachedKey=" + memcachedKey + ", createTime=" + createTime + "]";
	}

}
