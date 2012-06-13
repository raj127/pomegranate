package com.darkmi.system.web;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.StaleStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;


import com.darkmi.entity.system.User;
import com.darkmi.system.service.AccountManager;
import com.darkmi.util.CrudActionSupport;

/**
 * Description: 修改用户的密码的Action.
 * Copyright (c) darkmi
 * All Rights Reserved.
 * @version 1.0  2012-05-23 下午01:36:31 darkmi created
 */
@Namespace("/system")
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "user.action", type = "redirect") })
public class UserPassAction extends CrudActionSupport<User> {

	private static final long serialVersionUID = -2681353177490452103L;

	private AccountManager accountManager;
	private User user;
	private Long id;
	private Integer workingVersion;//对象版本号, 配合Hibernate的@Version防止并发修改

	@Override
	public User getModel() {

		return user;
	}

	@Override
	public String list() throws Exception {

		throw new UnsupportedOperationException("list操作暂时未支持");
	}

	@Override
	public String input() throws Exception {

		return INPUT;
	}

	@Override
	public String save() throws Exception {

		if (workingVersion != null && workingVersion < user.getVersion()) {
			throw new StaleStateException("对象已有新的版本");
		}
		accountManager.saveUserPass(user);
		dbLogger.info(SpringSecurityUtils.getCurrentUserName() + ":修改" + user.getName() + "密码！");
		addActionMessage("修改用户密码成功");

		return RELOAD;
	}

	@Override
	public String delete() throws Exception {

		throw new UnsupportedOperationException("delete操作暂时未支持");
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			user = accountManager.getUser(id);
		} else {
			user = new User();
		}

	}

	@Autowired
	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setWorkingVersion(Integer workingVersion) {
		this.workingVersion = workingVersion;
	}

}
