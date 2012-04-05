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

import com.darkmi.entity.task.Task;
import com.darkmi.task.service.TaskManager;
import com.darkmi.util.CrudActionSupport;

@Namespace("/task")
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "task.action?page.pageNo=${page.pageNo}&page.orderBy=${page.orderBy}&page.order=${page.order}&page.pageSize=${page.pageSize}", type = "redirect") })
public class TaskAction extends CrudActionSupport<Task> {
	private static final long serialVersionUID = -2907389496513631586L;
	private Long id;
	private Task task;
	private TaskManager taskManager;
	private Page<Task> page = new Page<Task>(20);

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

	public String viewChapter() {
		System.out.println("aaa");
		return "VIEW_CHAPTER";
	}

	@Override
	public String input() throws Exception {
		return INPUT;
	}

	@Override
	public String save() throws Exception {
		taskManager.saveTask(task);
		addActionMessage("保存员工信息成功！");
		return RELOAD;
	}

	@Override
	public String delete() throws Exception {
		task = taskManager.getTask(id);
		taskManager.deleteTask(id);
		dbLogger.info(SpringSecurityUtils.getCurrentUserName() + ":删除" + task.getTaskName() + "员工！");
		addActionMessage("删除员工信息成功！");
		return RELOAD;
	}

	/**
	 * 支持使用Jquery.validate Ajax检验用户名是否重复.
	 */
	public String checkTaskName() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String providerId = request.getParameter("employeeName");
		String oldProviderId = request.getParameter("oldemployeeName");

		if (taskManager.isTaskNameUnique(providerId, oldProviderId)) {
			Struts2Utils.renderText("true");
		} else {
			Struts2Utils.renderText("false");
		}
		//因为直接输出内容而不经过jsp,因此返回null.
		return null;
	}

	public Page<Task> getPage() {
		return page;
	}

	@Autowired
	public void setTaskManager(TaskManager taskManager) {
		this.taskManager = taskManager;
	}

	public void setId(Long id) {
		this.id = id;
	}
}