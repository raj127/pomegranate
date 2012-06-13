package com.darkmi.system.web;

import org.apache.struts2.convention.annotation.Namespace;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Description: 规范树状展示Action.
 * Copyright (c) darkmi
 * All Rights Reserved.
 * @version 1.0  2012-05-23 下午01:36:31 darkmi created
 */
@Namespace("/system")
public class ZtreeAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private String message;
	public static final String MESSAGE = "Struts is up and running ";

	public String execute() throws Exception {
		setMessage(MESSAGE);
		return SUCCESS;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
