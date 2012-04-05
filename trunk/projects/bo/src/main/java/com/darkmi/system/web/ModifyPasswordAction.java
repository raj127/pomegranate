package com.darkmi.system.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.utils.web.struts2.Struts2Utils;


import com.darkmi.common.Constants;
import com.darkmi.entity.system.User;
import com.darkmi.system.service.AccountManager;
import com.darkmi.util.CrudActionSupport;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Description: 修改个人密码的Action
 * Copyright (c) 永新视博
 * All Rights Reserved.
 * @version 1.0  2010-12-6 下午02:29:45 laojiang created
 */
@Namespace("/system")
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "modify-password.action", type = "redirect") })
public class ModifyPasswordAction extends ActionSupport {

	private static final long serialVersionUID = -258972430971030904L;
	private Logger dbLogger = LoggerFactory.getLogger(Constants.DB_LOGGER_NAME);
	private AccountManager accountManager;

	private String password;

	/**
	 * Description: 修改密码
	 * @Version1.0 2010-12-6 下午02:33:16 laojiang created
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {

		String userLoginName = SpringSecurityUtils.getCurrentUserName();

		User user = accountManager.findUserByLoginName(userLoginName);

		user.setPassword(password);

		accountManager.saveUserPass(user);
		dbLogger.info(SpringSecurityUtils.getCurrentUserName() + ":修改本用户密码！");
		addActionMessage("修改密码成功！");

		return CrudActionSupport.RELOAD;
	}

	/**
	 * Description: 查询原来的密码是否正确
	 * @Version1.0 2010-12-6 下午02:33:24 laojiang created
	 * @return
	 */
	public String checkPass() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String oldPass = request.getParameter("oldPassword");
		PasswordEncoder encoder = new ShaPasswordEncoder();
		String shaPassword = encoder.encodePassword(oldPass, null);
		String userLoginName = SpringSecurityUtils.getCurrentUserName();

		User u = accountManager.findUserByLoginName(userLoginName);

		if (shaPassword.equals(u.getShaPassword())) {
			Struts2Utils.renderText("true");
		} else {
			Struts2Utils.renderText("false");
		}
		return null;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Autowired
	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}

}
