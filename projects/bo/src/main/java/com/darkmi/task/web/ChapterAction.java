package com.darkmi.task.web;

import java.util.Iterator;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.darkmi.entity.chapter.Chapter;
import com.darkmi.task.service.ChapterManager;
import com.darkmi.util.CrudActionSupport;

@Namespace("/task")
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "chapter.action?page.pageNo=${page.pageNo}&page.orderBy=${page.orderBy}&page.order=${page.order}&page.pageSize=${page.pageSize}", type = "redirect"),
	       @Result(name = "edit", location = "/WEB-INF/content/task/chapter-edit.jsp")
	     })
public class ChapterAction extends CrudActionSupport<Chapter> {
	private static final long serialVersionUID = 7117372628753539453L;
	private Long id;
	private Chapter chapter;
	private ChapterManager chapterManager;

	private Page<Chapter> page = new Page<Chapter>(20);

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			chapter = chapterManager.getChapter(id);
		} else {
			chapter = new Chapter();
		}

	}

	@Override
	public Chapter getModel() {
		return chapter;
	}

	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.DESC);
		}
		List<Chapter> list = chapterManager.getAllChapter();
		for (Iterator<Chapter> iterator = list.iterator(); iterator.hasNext();) {
			Chapter employee = (Chapter) iterator.next();
			System.out.println(employee.getChapterName());
		}
		page = chapterManager.searchChapter(page, filters);
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		return INPUT;
	}

	@Override
	public String save() throws Exception {
		chapterManager.saveChapter(chapter);
		addActionMessage("保存章节信息成功！");
		return RELOAD;
	}
	
	public String edit(){
		return "edit";
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

	public Page<Chapter> getPage() {
		return page;
	}

	@Autowired
	public void setChapterManager(ChapterManager chapterManager) {
		this.chapterManager = chapterManager;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
