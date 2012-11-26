package com.darkmi.edit.web;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.darkmi.edit.service.TaskChapterManager;
import com.darkmi.entity.task.Task;
import com.darkmi.entity.task.TaskChapter;
import com.darkmi.task.service.TaskManager;
import com.darkmi.util.CrudActionSupport;
import com.google.common.collect.Lists;

/**
 * Description: 规程编制--》规程章节树状表格展示.
 * Copyright (c) www.darkmi.com
 * All Rights Reserved.
 * @version 1.0  2012-05-10 上午09:20:11 DarkMi created
 */
@Namespace("/edit")
@Results({ @Result(name = "reload", location = "task-chapter-tabletree.action?taskId=${taskId}", type = "redirect") })
public class TaskChapterTabletreeAction extends CrudActionSupport<TaskChapter> {
	private static final long serialVersionUID = 8559745143820907960L;
	private Logger logger = LoggerFactory.getLogger(TaskChapterTabletreeAction.class);
	private Long id;// 当前章节的ID
	private Long parentId;// 当前章节的父ID
	private Long taskId;// 当前章节的所属任务ID
	private boolean viewOnly = false;
	private String taskTree;// 任务树
	private String filePath;

	private TaskChapter tc;
	private List<TaskChapter> tcs = Lists.newArrayList();

	//-- 业务类 --//
	private TaskManager taskManager;
	private TaskChapterManager tcManager;

	@Override
	public String list() throws Exception {
		logger.debug("list() begin {... ");
		getTaskChapter();
		logger.debug("list() ...end } ");
		return SUCCESS;
	}

	/**
	 * 获得目录树状展示字符串.
	 * @throws Exception
	 */
	private void getTaskChapter() throws Exception {
		Task task = taskManager.getTask(taskId);
		logger.debug("task --》" + task);
		StringBuffer sb = new StringBuffer();
		sb.append("<table id=\"taskTree\">");
		sb.append("<thead>");
		sb.append("<th>名称</th>");
		sb.append("<th>文件名称</th>");
		sb.append("<th>章节描述</th>");
		sb.append("<th>章节状态</th>");
		sb.append("<th>章节类型</th>");
		sb.append("<th>操作</th>");
		sb.append("</thead>");
		sb.append("<tbody>");
		int taskCounter = 1;
		//for (Task task : tasks) {
		sb.append("<tr id=\"ex3-node-" + taskCounter + "\">");
		sb.append("<td>" + task.getTaskName() + "</td>");
		sb.append("<td>&nbsp;</td>");
		sb.append("<td>&nbsp;</td>");
		sb.append("<td>&nbsp;</td>");
		sb.append("<td>&nbsp;</td>");
		sb.append("<td>&nbsp;</td>");
		sb.append("</tr>");

		//获取一级目录
		List<TaskChapter> levelOnes = tcManager.getLevelOne(task.getId());
		int levelOneCounter = 1;
		for (TaskChapter levelOne : levelOnes) {
			sb.append("<tr id=\"ex3-node-" + taskCounter + "-" + levelOneCounter + "\" class=\"child-of-ex3-node-"
					+ taskCounter + "\">");
			sb.append("<td>" + levelOne.getChapterName() + "</td>");
			sb.append("<td>" + levelOne.getFileName() + "</td>");
			sb.append("<td>" + levelOne.getDescription() + "</td>");
			sb.append("<td>&nbsp;</td>");
			sb.append("<td>&nbsp;</td>");
			sb.append("<td>&nbsp;</td>");
			sb.append("</tr>");

			//获取二级目录
			List<TaskChapter> levelTwos = tcManager.getLevelTwo(levelOne.getId());
			int levelTwoCounter = 1;
			for (TaskChapter levelTwo : levelTwos) {
				sb.append("<tr id=\"ex3-node-" + taskCounter + "-" + levelOneCounter + "-" + levelTwoCounter
						+ "\" class=\"child-of-ex3-node-" + taskCounter + "-" + levelOneCounter + "\">");
				sb.append("<td>" + levelTwo.getChapterName() + "</td>");
				sb.append("<td>" + levelTwo.getFileName() + "</td>");
				sb.append("<td>" + levelTwo.getDescription() + "</td>");
				sb.append("<td>&nbsp;</td>");
				sb.append("<td>&nbsp;</td>");
				sb.append("<td>" + "<a href=\"task-chapter-edit-main!main.action?id=" + levelTwo.getId()
						+ "\" target=\"_blank\">编辑</a>" + "</td>");
				sb.append("</tr>");
				levelTwoCounter++;
			}
			levelOneCounter++;
		}
		//taskCounter++;
		//}
		sb.append("</tbody>");
		sb.append("</table>");
		logger.debug(sb.toString());
		taskTree = sb.toString();
	}

	@Override
	public String save() throws Exception {
		return null;
	}

	@Override
	public String input() throws Exception {
		return null;
	}

	@Override
	public String delete() throws Exception {
		return null;
	}

	public String edit() {
		return null;
	}

	/* ~~~~~~~~~~~ 数据准备 ~~~~~~~~~~~~~~~~~ */
	@Override
	public TaskChapter getModel() {
		logger.debug("getModel() {begin... ");
		logger.debug("getModel() ...end }");
		return tc;
	}

	@Override
	protected void prepareModel() throws Exception {
		logger.debug("prepareModel() {begin... ");
		if (id != null) {
			tc = tcManager.getTaskChapter(id);
		} else {
			tc = new TaskChapter();
		}
		logger.debug("prepareModel() ...end }");
	}

	/* ~~~~~~~~~~~Setters And Getters ~~~~~~~~~~~~~~~~~ */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getTaskTree() {
		return taskTree;
	}

	public void setTaskTree(String taskTree) {
		this.taskTree = taskTree;
	}

	public TaskChapter getTc() {
		return tc;
	}

	public void setTc(TaskChapter tc) {
		this.tc = tc;
	}

	public List<TaskChapter> getTcs() {
		return tcs;
	}

	public void setTcs(List<TaskChapter> tcs) {
		this.tcs = tcs;
	}

	public boolean isViewOnly() {
		return viewOnly;
	}

	public void setViewOnly(boolean viewOnly) {
		this.viewOnly = viewOnly;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/* ~~~~~~~~~~~业务逻辑类注入~~~~~~~~~~~~~~~~~ */

	@Autowired
	public void setTaskChapterManager(TaskChapterManager taskChapterManager) {
		this.tcManager = taskChapterManager;
	}

	@Autowired
	public void setTaskManager(TaskManager taskManager) {
		this.taskManager = taskManager;
	}

}
