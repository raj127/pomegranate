package com.darkmi.system.web;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;


import com.darkmi.entity.system.Log;
import com.darkmi.system.service.LogManager;
import com.darkmi.util.CrudActionSupport;

/**
 * Description: 日志管理Action
 * Copyright (c) 永新视博
 * All Rights Reserved.
 * @version 1.0  2010-12-7 上午10:29:47 laojiang created
 */
@Namespace("/system")
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "log.action", type = "redirect") })
public class LogAction extends CrudActionSupport<Log> {

	private static final long serialVersionUID = 6943247450642219255L;

	private LogManager logManager;

	private Long id;
	private Page<Log> page = new Page<Log>(20);

	@Override
	public Log getModel() {

		//throw new UnsupportedOperationException();
		return null;
	}

	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.DESC);
		}
		page = logManager.searchLog(page, filters);
		return SUCCESS;
	}
	
	public String deleteAll() throws Exception {
		
		int deleteNum = logManager.deleteAllLog();
		
		addActionMessage("成功删除"+deleteNum+"条日志信息！");
		
		return RELOAD;
	}

	@Override
	public String input() throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public String save() throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public String delete() throws Exception {

		logManager.deleteLog(id);

		addActionMessage("删除日志信息成功！");

		return RELOAD;
	}

	@Override
	protected void prepareModel() throws Exception {

	}

	public Page<Log> getPage() {
		return page;
	}

	@Autowired
	public void setLogManager(LogManager logManager) {
		this.logManager = logManager;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
