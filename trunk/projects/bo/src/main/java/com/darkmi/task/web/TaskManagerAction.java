package com.darkmi.task.web;

import java.util.Iterator;
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

import com.darkmi.SystemConfig;
import com.darkmi.common.tools.Cn2Spell;
import com.darkmi.edit.service.TaskChapterManager;
import com.darkmi.entity.StateEnum;
import com.darkmi.entity.system.Company;
import com.darkmi.entity.task.CreateTypeEnum;
import com.darkmi.entity.task.Task;
import com.darkmi.entity.task.TaskChapter;
import com.darkmi.entity.template.Template;
import com.darkmi.entity.template.TemplateChapter;
import com.darkmi.system.service.AccountManager;
import com.darkmi.task.service.TaskManager;
import com.darkmi.template.service.TemplateChapterManager;
import com.darkmi.template.service.TemplateManager;
import com.darkmi.util.CrudActionSupport;
import com.google.common.collect.Lists;

/**
 * Description: 任务管理Action.
 * Copyright (c) www.darkmi.com
 * All Rights Reserved.
 * @version 1.0  2012-05-10 上午09:20:11 DarkMi created
 */
@Namespace("/task")
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "task-manager.action?page.pageNo=${page.pageNo}&page.orderBy=${page.orderBy}&page.order=${page.order}&page.pageSize=${page.pageSize}", type = "redirect") })
public class TaskManagerAction extends CrudActionSupport<Task> {
	private static final long serialVersionUID = -2907389496513631586L;
	private Long id;
	private Task task;
	private Long templateId;
	private boolean viewOnly = false;
	private boolean update = false;
	private Page<Task> page = new Page<Task>(20);

	private TaskManager taskManager;
	private TaskChapterManager taskChapterManager;
	private TemplateManager templateManager;
	private TemplateChapterManager tcManager;
	private AccountManager accountManager;
	private SystemConfig systemConfig;

	/**
	 * 作业规程列表页面.
	 * 默认入口.
	 */
	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.DESC);
		}
		List<Task> list = taskManager.getAllTask();
		for (Iterator<Task> iterator = list.iterator(); iterator.hasNext();) {
			Task employee = (Task) iterator.next();
			System.out.println(employee.getTaskName());
		}
		page = taskManager.searchTask(page, filters);
		return SUCCESS;
	}

	/**
	 * 进入模板信息录入页面.
	 */
	@Override
	public String input() throws Exception {
		return INPUT;
	}

	/**
	 * 创建作业规程.
	 * 有三种方式:
	 * (1)新建;
	 * (2)使用模板;
	 * (3)复制已有规程.
	 */
	@Override
	public String save() throws Exception {
		logger.debug("保存任务信息{...");
		if (CreateTypeEnum.NEW.equals(task.getCreateType())) { //新建
			//设置作业规程的保存路径
			task.setPath(getPath(task.getTaskName()));
			task.setCompany(getCompany());
			task.setState(StateEnum.NORMAL);
			taskManager.saveTask(task);
		} else if (CreateTypeEnum.TEMPLATE.equals(task.getCreateType())) { //使用模板
			if (templateId == null) {
				addActionMessage("请选择作业规程模板！");
			} else {
				//Template template = templateManager.getTemplate(templateId);
				List<TemplateChapter> list = tcManager.getTcsByTemplateId(templateId);

				//设置作业规程的保存路径
				task.setPath(getPath(task.getTaskName()));
				task.setCompany(getCompany());
				task.setState(StateEnum.NORMAL);
				taskManager.saveTask(task);

				for (Iterator<TemplateChapter> iterator = list.iterator(); iterator.hasNext();) {
					TemplateChapter tc = (TemplateChapter) iterator.next();
					TaskChapter taskChapter = new TaskChapter();
					taskChapter.setChapterName(tc.getChapterName());
					taskChapter.setFileName(tc.getFileName());
					taskChapter.setDisplayOrder(tc.getDisplayOrder());
					taskChapter.setParentId(tc.getParentId());
					taskChapter.setState(StateEnum.NORMAL);
					taskChapter.setDescription(tc.getDescription());
					taskChapter.setTask(task);
					taskChapterManager.saveTaskChapter(taskChapter);
				}
			}
		} else if (CreateTypeEnum.COPY.equals(task.getCreateType())) { //复制已有规程

		}

		addActionMessage("保存任务成功！");
		logger.debug("保存任务信息 ... }");
		return RELOAD;
	}

	/**
	 * 删除作业规程.
	 */
	@Override
	public String delete() throws Exception {
		task = taskManager.getTask(id);
		taskManager.deleteTask(id);
		dbLogger.info(SpringSecurityUtils.getCurrentUserName() + ":删除" + task.getTaskName() + "任务！");
		addActionMessage("删除作业规任务成功！");
		return RELOAD;
	}

	/**
	 * 获得当前工号的工作目录
	 * @return
	 */
	private Company getCompany() {
		//获取当前工号所属单位
		String loginName = SpringSecurityUtils.getCurrentUserName();
		logger.debug("current user loginName is --> {}", loginName);
		Company company = accountManager.getCompanyByLoginName(loginName);
		return company;
	}

	/**
	 * 获取用户公司的根目录.
	 * @return
	 */
	private String getPath(String taskName) {
		String companyNameEn = Cn2Spell.converterToSpell(taskName);
		//获取当前工号所属单位
		String loginName = SpringSecurityUtils.getCurrentUserName();
		logger.debug("current user loginName is --> {}", loginName);
		Company company = accountManager.getCompanyByLoginName(loginName);

		StringBuffer sb = new StringBuffer();
		sb.append(ServletActionContext.getRequest().getContextPath());
		sb.append("/");
		sb.append(systemConfig.getCompanyFolder());
		sb.append(company.getFolder());
		sb.append(systemConfig.getTaskFolder());
		sb.append(companyNameEn);
		sb.append("/");
		String path = sb.toString();
		logger.debug("task path is --> {}", path);

		return path;
	}

	/**
	 * 校验作业规程名称是否重复.
	 */
	public String checkTaskName() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String newTaskName = request.getParameter("taskName");
		String oldTaskName = request.getParameter("oldTaskName");

		if (taskManager.isTaskNameUnique(newTaskName, oldTaskName)) {
			Struts2Utils.renderText("true");
		} else {
			Struts2Utils.renderText("false");
		}
		//因为直接输出内容而不经过jsp,因此返回null.
		return null;
	}

	/**
	 * 获取该用户可使用的作业规程模板.
	 * @return
	 */
	public List<Template> getAllTemplate() {
		logger.debug("获得可用作业规程模板 begin { ...");
		List<Template> list = Lists.newArrayList();
		List<Template> defaultList = templateManager.getAllDefaultTemplate();
		list.addAll(defaultList);
		if (!getCompany().getId().equals(new Long(0))) {
			List<Template> companyList = templateManager.getAllCompanyTemplate(getCompany().getId());
			list.addAll(companyList);
		}
		logger.debug("获取到一级目录数量为 --》 {}", list.size());
		logger.debug("获得可用作业规程模板 end ...}");
		return list;
	}

	/*~~~~~~~~~~~ 重载方法 ~~~~~~~~~~~~~~~~~*/

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			task = taskManager.getTask(id);
		} else {
			task = new Task();
		}
	}

	@Override
	public Task getModel() {
		return task;
	}

	/*~~~~~~~~~~~Setters And Getters ~~~~~~~~~~~~~~~~~*/
	public Page<Task> getPage() {
		return page;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isViewOnly() {
		return viewOnly;
	}

	public void setViewOnly(boolean viewOnly) {
		this.viewOnly = viewOnly;
	}

	public boolean isUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	/*~~~~~~~~~~~业务逻辑类注入~~~~~~~~~~~~~~~~~*/
	@Autowired
	public void setTaskManager(TaskManager taskManager) {
		this.taskManager = taskManager;
	}

	@Autowired
	public void setTemplateManager(TemplateManager templateManager) {
		this.templateManager = templateManager;
	}

	@Autowired
	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}

	@Autowired
	public void setSystemConfig(SystemConfig systemConfig) {
		this.systemConfig = systemConfig;
	}

	@Autowired
	public void setTaskChapterManager(TaskChapterManager taskChapterManager) {
		this.taskChapterManager = taskChapterManager;
	}

	@Autowired
	public void setTcManager(TemplateChapterManager tcManager) {
		this.tcManager = tcManager;
	}
}