package com.darkmi.common;

/**
 * Description: Memcached存储KEY对象
 * Copyright (c) 永新视博
 * All Rights Reserved.
 * @version 1.0  2011-3-28 下午06:28:18 laojiang created
 */
public enum MemcachedObjectType {
	USER("user:", 60 * 60 * 24 * 30), CMSMOVIE("cms-movie:", 60 * 60 * 24 * 30), CMSTV("cms-tv:", 60 * 60 * 24 * 30), CMSKTV(
			"cms-ktv:", 60 * 60 * 24 * 30), ODMOFFERING("odm-offering:", 60 * 60 * 24 * 30), ODMPROVIDER(
			"odm-provider:", 60 * 60 * 24 * 30), ODMPRODUCT("odm-product:", 60 * 60 * 24 * 30), ODMSERVICE(
			"odm-service:", 60 * 60 * 24 * 30), SMCUSTOMER("sm-customer:", 60 * 60 * 24 * 30), SMASSET("sm-asset:",
			60 * 60 * 24 * 30), SMSESSION("sm-session:", 60 * 60 * 24 * 30), SRMSESSION("srm-session:",
			60 * 60 * 24 * 30), SMARTCARD("smartcard-session:", 60 * 60 * 24 * 30), PPSMENU("pps-menu:",
			60 * 60 * 24 * 30), PPSMOVIE("pps-movie:", 60 * 60 * 24 * 30), PPSCOMMENDMOVIE("pps-commend-movie:",
			60 * 60 * 24 * 30), PPSTV("pps-tv:", 60 * 60 * 24 * 30), PPSCOMMENDTV("pps-commend-tv:", 60 * 60 * 24 * 30), PPSKTV(
			"pps-ktv:", 60 * 60 * 24 * 30), PPSCOMMENDKTV("pps-commend-ktv:", 60 * 60 * 24 * 30), PPSNEWKTV(
			"pps-new-ktv:", 60 * 60 * 24 * 30), PPSHOTKTV("pps-hot-ktv:", 60 * 60 * 24 * 30), PPSEPISODE(
			"pps-episode:", 60 * 60 * 24 * 30), PPSSERVICE("pps-service:", 60 * 60 * 24 * 30), PPSCATEGORY(
			"pps-category:", 60 * 60 * 24 * 30), PPSCHANNEL("pps-channel:", 60 * 60 * 24 * 30), PPSAMCHANNEL(
			"pps-am-channel:", 60 * 60 * 24 * 30), PPSPMCHANNEL("pps-pm-channel:", 60 * 60 * 24 * 30), RMTS("rm-ts:",
			60 * 60 * 24 * 30), RMPORT("rm-port:", 60 * 60 * 24 * 30), PSPSSESSION("ps-ps-session:", 60 * 24 * 30), PSSRMSESSION(
			"ps-srm-session:", 60 * 24 * 30), PSCUSTOMER("ps-customer:", 60 * 60 * 24 * 30), PSHONE("ps-phone:",
			60 * 60 * 24 * 30), PPSMOVIEBOOKMARK("pps-movie-bookmark:", 60 * 60 * 24 * 30), PPSTSTVBOOKMARK(
			"pps-tstv-bookmark:", 60 * 60 * 24 * 30), PPSRECENTLYBROWSE("pps-recently-browse:", 60 * 60 * 24 * 30), SYSTEMSTATE(
			"system-state:", 60 * 60 * 24 * 30);

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
