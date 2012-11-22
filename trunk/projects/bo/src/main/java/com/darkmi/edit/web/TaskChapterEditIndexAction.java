package com.darkmi.edit.web;

import java.util.Iterator;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.darkmi.edit.service.TaskChapterManager;
import com.darkmi.entity.task.Task;
import com.darkmi.entity.task.TaskChapter;
import com.darkmi.task.service.TaskManager;
import com.darkmi.util.CrudActionSupport;
import com.google.common.collect.Lists;

/**
 * Description: 规程编制--》规程章节查看Action.
 * Copyright (c) www.darkmi.com
 * All Rights Reserved.
 * @version 1.0  2012-05-10 上午09:20:11 DarkMi created
 */
@Namespace("/edit")
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "task-chapter-edit-index.action?page.pageNo=${page.pageNo}&page.orderBy=${page.orderBy}&page.order=${page.order}&page.pageSize=${page.pageSize}", type = "redirect") })
public class TaskChapterEditIndexAction extends CrudActionSupport<Task> {
	private static final long serialVersionUID = -2907389496513631586L;
	private Logger logger = LoggerFactory.getLogger(TaskChapterEditIndexAction.class);

	//-- 任务列表页面属性 --//
	private Long id; //该ID为Task的ID.
	private Task entity; //任务实体
	private Integer workingVersion;//对象版本号, 配合Hibernate的@Version防止并发修改
	private boolean viewOnly = false;
	private Page<Task> page = new Page<Task>(20);//每页20条记录

	//-- 任务详细页面属性 --//
	private String taskTree; //单独一个任务的章节树
	private List<TaskChapter> tcs = Lists.newArrayList();

	//-- 业务类 --//
	private TaskChapterManager taskChapterManager;
	private TaskManager taskManager;

	@Override
	public Task getModel() {
		return entity;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			entity = taskManager.getTask(id);
		} else {
			entity = new Task();
		}
	}

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


	@Override
	public String input() throws Exception {
		return null;
	}

	@Override
	public String save() throws Exception {
		return null;
	}

	@Override
	public String delete() throws Exception {
		return null;
	}

	//	@Override
	//	public String execute() throws Exception {
	//		logger.debug("begin execute() { ...");
	//		getTaskChapter();
	//		logger.debug("end execute() ...} ");
	//		return super.execute();
	//	}

	@SuppressWarnings("unused")
	private void getTaskChapter() throws Exception {
		List<Task> tasks = taskManager.getAllTask();
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
		for (Task task : tasks) {
			sb.append("<tr id=\"ex3-node-" + taskCounter + "\">");
			sb.append("<td>" + task.getTaskName() + "</td>");
			sb.append("<td>&nbsp;</td>");
			sb.append("<td>&nbsp;</td>");
			sb.append("<td>&nbsp;</td>");
			sb.append("<td>&nbsp;</td>");
			sb.append("<td>&nbsp;</td>");
			sb.append("</tr>");

			//获取一级目录
			List<TaskChapter> levelOnes = taskChapterManager.getLevelOne(task.getId());
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
					sb.append("<td>" + "<a href=\"task-chapter-edit-main!main.action?id=" + levelTwo.getId()
							+ "\" target=\"_blank\">编辑</a>" + "</td>");
					sb.append("</tr>");
					levelTwoCounter++;
				}
				levelOneCounter++;
			}
			taskCounter++;
		}
		sb.append("</tbody>");
		sb.append("</table>");
		logger.debug(sb.toString());
		taskTree = sb.toString();
	}

	/*~~~~~~~~~~~Setters And Getters ~~~~~~~~~~~~~~~~~*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Task getEntity() {
		return entity;
	}

	public void setEntity(Task entity) {
		this.entity = entity;
	}

	public Integer getWorkingVersion() {
		return workingVersion;
	}

	public void setWorkingVersion(Integer workingVersion) {
		this.workingVersion = workingVersion;
	}

	public boolean isViewOnly() {
		return viewOnly;
	}

	public void setViewOnly(boolean viewOnly) {
		this.viewOnly = viewOnly;
	}

	public Page<Task> getPage() {
		return page;
	}

	public void setPage(Page<Task> page) {
		this.page = page;
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
