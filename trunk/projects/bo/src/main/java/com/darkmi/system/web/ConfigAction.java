package com.darkmi.system.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.darkmi.entity.system.Config;
import com.darkmi.system.service.ConfigManager;
import com.darkmi.util.CrudActionSupport;

/**
 * Description: 配置管理的Action
 * Copyright (c) darkmi.com
 * All Rights Reserved.
 * @version 1.0  2012-03-17 上午08:31:05 darkmi created.
 */
@Namespace("/system")
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "config.action?page.pageNo=${page.pageNo}&page.orderBy=${page.orderBy}&page.order=${page.order}&page.pageSize=${page.pageSize}", type = "redirect") })
public class ConfigAction extends CrudActionSupport<Config> {
	private static final long serialVersionUID = 980413704936421667L;
	private ConfigManager configManager;
	private Long id;
	private Config config;
	private Page<Config> page = new Page<Config>(20);

	@Override
	public Config getModel() {
		return config;
	}

	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.DESC);
		}
		page = configManager.searchConfig(page, filters);
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		return INPUT;
	}

	@Override
	public String save() throws Exception {
		configManager.saveConfig(config);
		addActionMessage("保存配置信息成功！");
		return RELOAD;
	}

	@Override
	public String delete() throws Exception {
		config = configManager.getConfig(id);
		configManager.deleteConfig(id);
		dbLogger.info(SpringSecurityUtils.getCurrentUserName() + ":删除" + config.getName() + "配置！");
		addActionMessage("删除配置信息成功！");
		return RELOAD;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			config = configManager.getConfig(id);
		} else {
			config = new Config();
		}
	}

	/**
	 * 支持使用Jquery.validate Ajax检验用户名是否重复.
	 */
	public String checkName() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String name = request.getParameter("name");
		String oldName = request.getParameter("oldName");

		if (configManager.isNameUnique(name, oldName)) {
			Struts2Utils.renderText("true");
		} else {
			Struts2Utils.renderText("false");
		}
		//因为直接输出内容而不经过jsp,因此返回null.
		return null;
	}

	public Page<Config> getPage() {
		return page;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Autowired
	public void setConfigManager(ConfigManager configManager) {
		this.configManager = configManager;
	}
}
