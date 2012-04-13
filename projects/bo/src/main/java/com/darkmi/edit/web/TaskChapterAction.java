package com.darkmi.edit.web;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.darkmi.edit.service.TaskChapterManager;
import com.darkmi.entity.task.TaskChapter;
import com.darkmi.util.CrudActionSupport;

@Namespace("/edit")
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "design.action?page.pageNo=${page.pageNo}&page.orderBy=${page.orderBy}&page.order=${page.order}&page.pageSize=${page.pageSize}", type = "redirect"),
	 	   @Result(name="edit", location="edit.jsp")})
public class TaskChapterAction extends CrudActionSupport<TaskChapter> {
	private static final long serialVersionUID = -2907389496513631586L;
	private Long id;
	private TaskChapter taskChapter;
	private TaskChapterManager taskChapterManager;
	private Page<TaskChapter> page = new Page<TaskChapter>(20);

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

	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.DESC);
		}
		page = taskChapterManager.searchTaskChapter(page, filters);
		return SUCCESS;
	}

	@Action(value = "edit", results = { @Result(name = "success", location = "edit.jsp", type = "redirect") })
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

	/**
	 * 支持使用Jquery.validate Ajax检验用户名是否重复.
	 */
	public String checkTaskName() {
		//		HttpServletRequest request = ServletActionContext.getRequest();
		//		String providerId = request.getParameter("employeeName");
		//		String oldProviderId = request.getParameter("oldemployeeName");
		//
		//		if (designManager.isTaskNameUnique(providerId, oldProviderId)) {
		//			Struts2Utils.renderText("true");
		//		} else {
		//			Struts2Utils.renderText("false");
		//		}
		//		//因为直接输出内容而不经过jsp,因此返回null.
		return null;
	}

	public Page<TaskChapter> getPage() {
		return page;
	}

	@Autowired
	public void setTaskChapterManager(TaskChapterManager taskChapterManager) {
		this.taskChapterManager = taskChapterManager;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
