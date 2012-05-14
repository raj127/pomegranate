package com.darkmi.task.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.darkmi.edit.service.TaskChapterManager;
import com.darkmi.entity.task.TaskChapter;
import com.darkmi.util.CrudActionSupport;
import com.google.common.collect.Lists;

/**
 * Description: 作业规程章节管理Action.
 * Copyright (c) www.darkmi.com
 * All Rights Reserved.
 * @version 1.0  2012-05-10 上午09:20:11 DarkMi created
 */
@Namespace("/task")
@Results({
		@Result(name = CrudActionSupport.RELOAD, location = "task-chapter.action?taskId=${taskId}", type = "redirect"),
		@Result(name = "edit", location = "edit.jsp") })
public class TaskChapterAction extends CrudActionSupport<TaskChapter> {
	private static final long serialVersionUID = 8559745143820907960L;
	private Long id;
	private Long taskId;
	private Long parentId;

	private TaskChapter taskChapter;
	private List<TaskChapter> tcs = Lists.newArrayList();

	private TaskChapterManager taskChapterManager;

	/**
	 * 模板列表页面.
	 * 默认入口.
	 */
	@Override
	public String list() throws Exception {
		logger.debug("task.taskChapter.list begin{ ... ");
		//获取taskId
		HttpServletRequest request = ServletActionContext.getRequest();
		String taskIdStr = request.getParameter("taskId");
		if (null == taskIdStr || "".equals(taskIdStr)) {
			if (SpringSecurityUtils.getCurrentUserName().equals("admin")) {
				tcs = taskChapterManager.getAllTaskChapter();
			}
		} else {
			taskId = Long.parseLong(request.getParameter("taskId"));
			tcs = taskChapterManager.getTcsByTaskId(taskId);
		}
		logger.debug("task.taskChapter.list end ...} ");
		return SUCCESS;
	}

	//@Action(value = "edit", results = { @Result(name = "edit", location = "edit.jsp", type = "redirect") })
	public String edit() {
		logger.debug("编辑作业规程... begin{ ");
		logger.debug("编辑作业规程... end} ");
		return "edit";
	}

	@Override
	public String input() throws Exception {
		return INPUT;
	}

	@Override
	public String save() throws Exception {
		//designManager.saveTask(taskChapter);
		addActionMessage("保存作业规程任务成功！");
		return RELOAD;
	}

	@Override
	public String delete() throws Exception {
		//taskChapter = designManager.getTask(id);
		//designManager.deleteTask(id);
		//dbLogger.info(SpringSecurityUtils.getCurrentUserName() + ":删除" + taskChapter.getTaskName() + "任务！");
		//addActionMessage("删除作业规任务成功！");
		return RELOAD;
	}

	/*~~~~~~~~~~~ 重载方法 ~~~~~~~~~~~~~~~~~*/
	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			taskChapter = taskChapterManager.getTaskChapter(id);
		} else {
			taskChapter = new TaskChapter();
		}
	}

	@Override
	public TaskChapter getModel() {
		return taskChapter;
	}

	/*~~~~~~~~~~~Setters And Getters ~~~~~~~~~~~~~~~~~*/

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public List<TaskChapter> getTcs() {
		return tcs;
	}

	public void setTcs(List<TaskChapter> tcs) {
		this.tcs = tcs;
	}

	/*~~~~~~~~~~~业务逻辑类注入~~~~~~~~~~~~~~~~~*/

	@Autowired
	public void setTaskChapterManager(TaskChapterManager taskChapterManager) {
		this.taskChapterManager = taskChapterManager;
	}

}
