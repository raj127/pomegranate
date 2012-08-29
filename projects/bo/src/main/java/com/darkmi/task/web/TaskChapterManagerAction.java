package com.darkmi.task.web;

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
import com.opensymphony.xwork2.ActionSupport;

/**
 * Description: 任务章节管理Action.
 * Copyright (c) www.darkmi.com
 * All Rights Reserved.
 * @version 1.0  2012-05-10 上午09:20:11 DarkMi created
 */
@Namespace("/task")
@Results({
		@Result(name = CrudActionSupport.RELOAD, location = "task-chapter-manager.action?taskId=${taskId}", type = "redirect"),
		@Result(name = "edit", location = "edit.jsp") })
public class TaskChapterManagerAction extends ActionSupport {
	private static final long serialVersionUID = 8559745143820907960L;
	private Logger logger = LoggerFactory.getLogger(TaskChapterManagerAction.class);
	private Long taskId;
	private Long parentId;
	private String taskTree;
	private List<TaskChapter> tcs = Lists.newArrayList();
	private TaskManager taskManager;
	private TaskChapterManager taskChapterManager;

	/**
	 * 模板列表页面.
	 * 默认入口.
	 */
	@Override
	public String execute() throws Exception {
		logger.debug("begin task execute() { ...");
		getTaskChapter();
		logger.debug("end task execute() ...} ");
		return super.execute();
	}

	private void getTaskChapter() throws Exception {
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
		Task task = taskManager.getTask(taskId);
		sb.append("<tr id=\"ex3-node-" + taskCounter + "\">");
		sb.append("<td>" + task.getTaskName() + "</td>");
		sb.append("<td>&nbsp;</td>");
		sb.append("<td>&nbsp;</td>");
		sb.append("<td>&nbsp;</td>");
		sb.append("<td>&nbsp;</td>");
		sb.append("<td>&nbsp;</td>");
		sb.append("</tr>");

		//获取一级目录
		List<TaskChapter> levelOnes = taskChapterManager.getLevelOne(taskId);
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
			List<TaskChapter> levelTwos = taskChapterManager.getLevelTwo(levelOne.getId());
			int levelTwoCounter = 1;
			for (TaskChapter levelTwo : levelTwos) {
				sb.append("<tr id=\"ex3-node-" + taskCounter + "-" + levelOneCounter + "-" + levelTwoCounter
						+ "\" class=\"child-of-ex3-node-" + taskCounter + "-" + levelOneCounter + "\">");
				sb.append("<td>" + levelTwo.getChapterName() + "</td>");
				sb.append("<td>" + levelTwo.getFileName() + "</td>");
				sb.append("<td>" + levelTwo.getDescription() + "</td>");
				sb.append("<td>&nbsp;</td>");
				sb.append("<td>&nbsp;</td>");
				sb.append("<td>" + "<a href=\"task-chapter-manager!edit.action?id=" + levelTwo.getId()
						+ "\" target=\"_blank\">编辑</a>" + "</td>");
				sb.append("</tr>");
				levelTwoCounter++;
			}
			levelOneCounter++;
		}
		taskCounter++;
		//}
		sb.append("</tbody>");
		sb.append("</table>");
		logger.debug(sb.toString());
		taskTree = sb.toString();
	}

	//@Action(value = "edit", results = { @Result(name = "edit", location = "edit.jsp", type = "redirect") })
	public String edit() {
		logger.debug("编辑作业规程... begin{ ");
		logger.debug("编辑作业规程... end} ");
		return "edit";
	}

	/*~~~~~~~~~~~Setters And Getters ~~~~~~~~~~~~~~~~~*/
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

	public String getTaskTree() {
		return taskTree;
	}

	public void setTaskTree(String taskTree) {
		this.taskTree = taskTree;
	}

	/*~~~~~~~~~~~业务逻辑类注入~~~~~~~~~~~~~~~~~*/

	@Autowired
	public void setTaskChapterManager(TaskChapterManager taskChapterManager) {
		this.taskChapterManager = taskChapterManager;
	}

	@Autowired
	public void setTaskManager(TaskManager taskManager) {
		this.taskManager = taskManager;
	}
}
