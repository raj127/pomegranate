package com.darkmi.task.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;

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
	
	private String chapterName;
	private Integer parentId;
	
	

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
		StringBuilder hqlBuilder = new StringBuilder();
		Map<String, Object> map = new HashMap<String, Object>();
		builerWhere(hqlBuilder, map);
		builderOrder(hqlBuilder);
		page = chapterManager.searchChapter(page, hqlBuilder.toString(), map);
		System.out.println("chapter list");
		return SUCCESS;
	}
	
	private void builerWhere(StringBuilder hqlBuilder, Map<String, Object> map) {

		//if (StringUtils.isNotBlank(parentId)) {
		//	hqlBuilder.append(" and v.ip like :ip");
		//	map.put("ip", "%" + queryIp + "%");
		//}

		//if (StringUtils.isNotBlank(queryName)) {
		//	hqlBuilder.append(" and v.name like :name");
		//	map.put("name", "%" + queryName + "%");
		//}

		if (parentId != null) {
			logger.debug("parentId is --> " + parentId);
			hqlBuilder.append(" and t.parentId=:parentId");
			map.put("parentId", parentId);
		}

		//if (StringUtils.isNotBlank(queryState)) {
		//	hqlBuilder.append(" and v.state=:state");
		//	map.put("state", StateEnum.valueOf(queryState));
		//}
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
	
	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

}
