package com.darkmi.common;

public enum MemcachedObjectType {
	USER("user:", 60 * 60 * 24 * 30), 
	CMSMOVIE("cms-movie:", 60 * 60 * 24 * 30), 
	CMSTV("cms-tv:", 60 * 60 * 24 * 30);

	private String prefix;
	private int expiredTime;

	MemcachedObjectType(String prefix, int expiredTime) {
		this.prefix = prefix;
		this.expiredTime = expiredTime;
	}

	public String getPrefix() {
		return prefix;
	}

	public int getExpiredTime() {
		return expiredTime;
	}
}
