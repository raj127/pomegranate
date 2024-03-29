package com.darkmi.system.web;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.StaleStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.darkmi.entity.system.SpecificationChapter;
import com.darkmi.system.service.SpecificationChapterManager;
import com.darkmi.util.CrudActionSupport;

/**
 * Description: 系统管理--》规范管理(table)Action.
 * Copyright (c) darkmi
 * All Rights Reserved.
 * @version 1.0  2012-05-23 下午01:36:31 darkmi created
 */
@Namespace("/system")
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "specification-chapter.action?page.pageNo=${page.pageNo}&page.orderBy=${page.orderBy}&page.order=${page.order}&page.pageSize=${page.pageSize}", type = "redirect") })
public class SpecificationChapterTableAction extends CrudActionSupport<SpecificationChapter> {
	private static final long serialVersionUID = 5159486167580353246L;
	private Long id;
	private Integer workingVersion;//对象版本号, 配合Hibernate的@Version防止并发修改
	private boolean viewOnly = false;
	private SpecificationChapter entity;
	private SpecificationChapterManager scManager;
	private Page<SpecificationChapter> page = new Page<SpecificationChapter>(20);//每页20条记录

	/**
	 * 规范章节列表页面.
	 */
	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.DESC);
		}
		page = scManager.searchChapter(page, filters);
		return SUCCESS;
	}

	/**
	 * 直接进入规范录入页面.
	 */
	@Override
	public String input() throws Exception {
		logger.debug("是否指允许查看 --> {}", viewOnly);
		return INPUT;
	}

	/**
	 * 删除规范章节信息.
	 * TODO:删除功能还没有实现.
	 */
	@Override
	public String delete() throws Exception {
		return null;
	}

	/**
	 * 保存规范章节信息.
	 */
	@Override
	public String save() throws Exception {
		if (workingVersion != null && workingVersion < entity.getVersion()) {
			throw new StaleStateException("已经被其他人更新");
		}
		scManager.saveChapter(entity);
		addActionMessage("保存规范信息成功");
		return RELOAD;
	}

	/*~~~~~~~~~~~ 重载方法 ~~~~~~~~~~~~~~~~~*/
	@Override
	public SpecificationChapter getModel() {
		return entity;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			entity = scManager.getChapter(id);
			logger.debug("获取到章节信息  --> {}", entity);
		} else {
			entity = new SpecificationChapter();
		}
	}

	/*~~~~~~~~~~~Setters And Getters ~~~~~~~~~~~~~~~~~*/

	public void setId(Long id) {
		this.id = id;
	}

	public Page<SpecificationChapter> getPage() {
		return page;
	}

	public boolean isViewOnly() {
		return viewOnly;
	}

	public void setViewOnly(boolean viewOnly) {
		this.viewOnly = viewOnly;
	}

	public void setWorkingVersion(Integer workingVersion) {
		this.workingVersion = workingVersion;
	}

	/*~~~~~~~~~~~业务逻辑类注入~~~~~~~~~~~~~~~~~*/

	@Autowired
	public void setScManager(SpecificationChapterManager scManager) {
		this.scManager = scManager;
	}
}