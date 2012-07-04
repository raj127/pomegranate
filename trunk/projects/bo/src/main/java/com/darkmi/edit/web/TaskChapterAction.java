package com.darkmi.edit.web;

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
 * Description: 任务章节管理Action.
 * Copyright (c) www.darkmi.com
 * All Rights Reserved.
 * @version 1.0  2012-05-10 上午09:20:11 DarkMi created
 */
@Namespace("/edit")
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "task-chapter.action?taskId=${taskId}", type = "redirect") })
public class TaskChapterAction extends CrudActionSupport<TaskChapter> {
	private static final long serialVersionUID = -2907389496513631586L;
	private Long id;
	private Long taskId;
	private TaskChapter taskChapter;
	private List<TaskChapter> tcs = Lists.newArrayList();

	private TaskChapterManager taskChapterManager;

	/**
	 * 根据当前工号权限获取所有的章节目录.
	 */
	@Override
	public String list() throws Exception {
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
		return SUCCESS;
	}

	//@Action(value = "edit", results = { @Result(name = "edit", location = "edit.jsp", type = "redirect") })
	//	public String edit() {
	//		logger.debug("编辑作业规程... begin{ ");
	//		logger.debug("编辑作业规程... end} ");
	//		return "edit";
	//	}

	@Override
	public String input() throws Exception {
		return INPUT;
	}

	@Override
	public String save() throws Exception {
		addActionMessage("保存作业规程任务成功！");
		return RELOAD;
	}

	@Override
	public String delete() throws Exception {
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
