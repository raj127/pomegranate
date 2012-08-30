package com.darkmi.task.web;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.darkmi.SystemConfig;
import com.darkmi.edit.service.TaskChapterManager;
import com.darkmi.entity.system.Company;
import com.darkmi.entity.task.Task;
import com.darkmi.entity.task.TaskChapter;
import com.darkmi.system.service.AccountManager;
import com.darkmi.task.service.TaskManager;
import com.darkmi.util.CrudActionSupport;
import com.google.common.collect.Lists;

/**
 * Description: 任务章节管理Action. Copyright (c) www.darkmi.com All Rights Reserved.
 * 
 * @version 1.0 2012-05-10 上午09:20:11 DarkMi created
 */
@Namespace("/task")
@Results({ @Result(name = "reload", location = "task-chapter-manager.action?taskId=${taskId}", type = "redirect")
// @Result(name = "edit", location =
// "task-chapter-manager-edit.jsp?taskId=${taskId}", type = "redirect")
})
public class TaskChapterManagerAction extends CrudActionSupport<TaskChapter> {
	private static final long serialVersionUID = 8559745143820907960L;
	private Logger logger = LoggerFactory.getLogger(TaskChapterManagerAction.class);
	private Long id;// 当前章节的ID
	private Long parentId;// 当前章节的父ID
	private Long taskId;// 当前章节的所属任务ID
	private boolean viewOnly = false;
	private String taskTree;// 任务树
	private String filePath;

	private TaskChapter tc;
	private List<TaskChapter> tcs = Lists.newArrayList();
	private TaskManager taskManager;
	private TaskChapterManager tcManager;
	private AccountManager accountManager;
	private SystemConfig systemConfig;

	@Override
	public String list() throws Exception {
		getTaskChapter();
		return SUCCESS;
	}

	@Override
	public String save() throws Exception {
		logger.debug("保存任务章节 begin {...");
		logger.debug("id --> " + id);
		logger.debug("taskId --> " + taskId);
		logger.debug("parentId --> " + parentId);
		logger.debug("tc --> {}", tc.toString());

		if (null == tc.getTask()) {
			Task task = taskManager.getTask(taskId);
			tc.setTask(task);
		}
		tc.setParentId(parentId);
		tc.setFileName("default.docx");
		tcManager.saveTaskChapter(tc);
		addActionMessage("保存任务章节成功");
		logger.debug("保存任务章节 end ...}");
		return RELOAD;
	}

	@Override
	public String input() throws Exception {
		return INPUT;
	}

	@Override
	public String delete() throws Exception {
		logger.debug("删除任务章节 begin {...");
		logger.debug("id -->{}", id);
		tcManager.deleteTaskChapter(id);
		addActionMessage("删除任务章节成功");
		logger.debug("删除任务章节 ...}");
		return RELOAD;
	}

	public String edit() {
		logger.debug("编辑作业规程... begin{ ");
		filePath = getPath();
		logger.debug("编辑作业规程... end} ");
		return "edit";
	}

	//
	// @Action(value = "add", results = { @Result(name = "add", location =
	// "task-chapter-manager-input.jsp", type = "redirect") })
	// public String add() {
	// logger.debug("添加作业规程... begin{ ");
	// logger.debug("添加作业规程... end} ");
	// return "add";
	// }

	/**
	 * 获取用户公司的根目录.
	 * 
	 * @return
	 */
	private String getPath() {
		// 获取当前工号所属单位
		String loginName = SpringSecurityUtils.getCurrentUserName();
		logger.debug("current user loginName is --> {}", loginName);
		Company company = accountManager.getCompanyByLoginName(loginName);

		StringBuffer sb = new StringBuffer();
		sb.append(ServletActionContext.getRequest().getContextPath());
		sb.append("/");
		sb.append(systemConfig.getCompanyFolder());
		sb.append(company.getFolder());
		sb.append("/");
		sb.append("default.docx");
		String path = sb.toString();
		logger.debug("task path is --> {}", path);

		return path;
	}

	/**
	 * 获得任务的表格树.
	 * 
	 * @throws Exception
	 */
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
		// for (Task task : tasks) {
		Task task = taskManager.getTask(taskId);
		sb.append("<tr id=\"ex3-node-" + taskCounter + "\">");
		sb.append("<td>" + task.getTaskName() + "</td>");
		sb.append("<td>&nbsp;</td>");
		sb.append("<td>&nbsp;</td>");
		sb.append("<td>&nbsp;</td>");
		sb.append("<td>&nbsp;</td>");
		sb.append("<td>");
		sb.append("<a href=\"task-chapter-manager!input.action?taskId=" + taskId + "&parentId=0\">添加</a>");
		sb.append("</td>");
		sb.append("</tr>");

		// 获取一级目录
		List<TaskChapter> levelOnes = tcManager.getLevelOne(taskId);
		int levelOneCounter = 1;
		for (TaskChapter levelOne : levelOnes) {
			sb.append("<tr id=\"ex3-node-" + taskCounter + "-" + levelOneCounter + "\" class=\"child-of-ex3-node-"
					+ taskCounter + "\">");
			sb.append("<td>" + levelOne.getChapterName() + "</td>");
			sb.append("<td>");
			sb.append("<a href=\"task-chapter-manager!edit.action\">");
			sb.append(levelOne.getFileName());
			sb.append("</a>");
			sb.append("</td>");
			sb.append("<td>" + levelOne.getDescription() + "</td>");
			sb.append("<td>&nbsp;</td>");
			sb.append("<td>&nbsp;</td>");
			sb.append("<td>");
			sb.append("<a href=\"task-chapter-manager!input.action?id=" + levelOne.getId() + "&viewOnly=true\">查看</a>");
			sb.append("&nbsp;&nbsp;");
			sb.append("<a href=\"task-chapter-manager!input.action?id=" + levelOne.getId() + "&taskId=" + taskId
					+ "&viewOnly=false\">编辑</a>");
			sb.append("&nbsp;&nbsp;");
			sb.append("<a href=\"task-chapter-manager!input.action?taskId=" + taskId + "&parentId=" + levelOne.getId()
					+ "\">添加</a>");

			// 获取二级目录
			List<TaskChapter> levelTwos = tcManager.getLevelTwo(levelOne.getId());
			if (levelTwos.size() <= 0) {
				sb.append("&nbsp;&nbsp;");
				sb.append("<a href=\"task-chapter-manager!delete.action?id=" + levelOne.getId() + "&taskId=" + taskId
						+ "\">删除</a>");
			}
			sb.append("</td>");
			sb.append("</tr>");

			int levelTwoCounter = 1;
			for (TaskChapter levelTwo : levelTwos) {
				sb.append("<tr id=\"ex3-node-" + taskCounter + "-" + levelOneCounter + "-" + levelTwoCounter
						+ "\" class=\"child-of-ex3-node-" + taskCounter + "-" + levelOneCounter + "\">");
				sb.append("<td>" + levelTwo.getChapterName() + "</td>");
				sb.append("<td>");
				sb.append("<a href=\"task-chapter-manager!edit.action\" target=\"_blank\">");
				sb.append(levelTwo.getFileName());
				sb.append("</a>");
				sb.append("</td>");
				sb.append("<td>" + levelTwo.getDescription() + "</td>");
				sb.append("<td>&nbsp;</td>");
				sb.append("<td>&nbsp;</td>");
				sb.append("<td>");
				sb.append("<a href=\"task-chapter-manager!input.action?id=" + levelTwo.getId()
						+ "&viewOnly=true\">查看</a>");
				sb.append("&nbsp;&nbsp;");
				sb.append("<a href=\"task-chapter-manager!input.action?id=" + levelTwo.getId() + "&taskId=" + taskId
						+ "&viewOnly=false\">编辑</a>");
				sb.append("&nbsp;&nbsp;");
				sb.append("<a href=\"task-chapter-manager!delete.action?id=" + levelTwo.getId() + "&taskId=" + taskId
						+ "\">删除</a>");
				sb.append("</td>");
				sb.append("</tr>");
				levelTwoCounter++;
			}
			levelOneCounter++;
		}
		taskCounter++;
		// }
		sb.append("</tbody>");
		sb.append("</table>");
		logger.debug(sb.toString());
		taskTree = sb.toString();
	}

	/* ~~~~~~~~~~~ 数据准备 ~~~~~~~~~~~~~~~~~ */
	@Override
	public TaskChapter getModel() {
		return tc;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			tc = tcManager.getTaskChapter(id);
		} else {
			tc = new TaskChapter();
		}
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

	@Autowired
	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}

	@Autowired
	public void setSystemConfig(SystemConfig systemConfig) {
		this.systemConfig = systemConfig;
	}

}
