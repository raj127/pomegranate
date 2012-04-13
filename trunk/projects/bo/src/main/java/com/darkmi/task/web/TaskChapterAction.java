package com.darkmi.task.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;

import com.darkmi.edit.service.TaskChapterManager;
import com.darkmi.entity.task.TaskChapter;
import com.darkmi.util.CrudActionSupport;

@Namespace("/task")
@Results({
		@Result(name = CrudActionSupport.RELOAD, location = "chapter.action?page.pageNo=${page.pageNo}&page.orderBy=${page.orderBy}&page.order=${page.order}&page.pageSize=${page.pageSize}", type = "redirect"),
		@Result(name = "success", location = "chapter.jsp") })
public class TaskChapterAction extends CrudActionSupport<TaskChapter> {
	private static final long serialVersionUID = 7117372628753539453L;
	private Long id;
	private Integer taskId;
	private String chapterName;
	private TaskChapter chapter;
	private TaskChapterManager taskChapterManager;

	private Page<TaskChapter> page = new Page<TaskChapter>(20);

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			chapter = taskChapterManager.getTaskChapter(id);
		} else {
			chapter = new TaskChapter();
		}

	}

	@Override
	public TaskChapter getModel() {
		return chapter;
	}

	@Override
	public String list() throws Exception {
		StringBuilder hqlBuilder = new StringBuilder();
		Map<String, Object> map = new HashMap<String, Object>();
		builerWhere(hqlBuilder, map);
		builderOrder(hqlBuilder);
		page = taskChapterManager.searchTaskChapter(page, hqlBuilder.toString(), map);
		return SUCCESS;
	}

	private void builerWhere(StringBuilder hqlBuilder, Map<String, Object> map) {
		if (taskId != null) {
			logger.debug("taskId is --> " + taskId);
			hqlBuilder.append(" and t.taskId=:taskId");
			map.put("taskId", taskId);
		}
	}

	private void builderOrder(StringBuilder hqlBuilder) {
		if (StringUtils.isNotBlank(page.getOrder()) && StringUtils.isNotBlank(page.getOrderBy())) {
			hqlBuilder.append(" order by ").append(page.getOrderBy()).append(" ").append(page.getOrder());
		} else {
			hqlBuilder.append(" order by t.id desc");
		}
	}

	@Override
	public String input() throws Exception {
		return INPUT;
	}

	@Override
	public String save() throws Exception {
		taskChapterManager.saveTaskChapter(chapter);
		addActionMessage("保存章节信息成功！");
		return RELOAD;
	}

	@Override
	public String delete() throws Exception {
		//		task = taskManager.getTask(id);
		//		taskManager.deleteTask(id);
		//		dbLogger.info(SpringSecurityUtils.getCurrentUserName() + ":删除" + task.getTaskName() + "员工！");
		//		addActionMessage("删除员工信息成功！");
		return RELOAD;
	}

	//	/**
	//	 * 支持使用Jquery.validate Ajax检验用户名是否重复.
	//	 */
	//	public String checkTaskName() {
	//		HttpServletRequest request = ServletActionContext.getRequest();
	//		String providerId = request.getParameter("employeeName");
	//		String oldProviderId = request.getParameter("oldemployeeName");
	//
	//		if (taskManager.isTaskNameUnique(providerId, oldProviderId)) {
	//			Struts2Utils.renderText("true");
	//		} else {
	//			Struts2Utils.renderText("false");
	//		}
	//		//因为直接输出内容而不经过jsp,因此返回null.
	//		return null;
	//	}

	public Page<TaskChapter> getPage() {
		return page;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	@Autowired
	public void setTaskChapterManager(TaskChapterManager taskChapterManager) {
		this.taskChapterManager = taskChapterManager;
	}

}
