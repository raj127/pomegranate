package com.darkmi.template.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;

import com.darkmi.entity.template.TemplateChapter;
import com.darkmi.template.service.TemplateChapterManager;
import com.darkmi.util.CrudActionSupport;

@Namespace("/template")
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "template-chapter.action?page.pageNo=${page.pageNo}&page.orderBy=${page.orderBy}&page.order=${page.order}&page.pageSize=${page.pageSize}", type = "redirect"),
	 	   @Result(name="edit", location="edit.jsp")})
public class TemplateChapterAction extends CrudActionSupport<TemplateChapter> {
	private static final long serialVersionUID = 4387918912684322626L;
	private Long id;
	private Integer templateId;
	private TemplateChapter templateChapter;
	private TemplateChapterManager templateChapterManager;
	private Page<TemplateChapter> page = new Page<TemplateChapter>(20);

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			templateChapter = templateChapterManager.getTemplateChapter(id);
		} else {
			templateChapter = new TemplateChapter();
		}
	}

	@Override
	public TemplateChapter getModel() {
		return templateChapter;
	}

	@Override
	public String list() throws Exception {
		StringBuilder hqlBuilder = new StringBuilder();
		Map<String, Object> map = new HashMap<String, Object>();
		builerWhere(hqlBuilder, map);
		builderOrder(hqlBuilder);
		page = templateChapterManager.searchTemplateChapter(page, hqlBuilder.toString(), map);
		return SUCCESS;
	}

	private void builerWhere(StringBuilder hqlBuilder, Map<String, Object> map) {
		if (templateId != null) {
			logger.debug("templateId is --> " + templateId);
			hqlBuilder.append(" and t.templateId=:templateId");
			map.put("templateId", templateId);
		}
	}

	private void builderOrder(StringBuilder hqlBuilder) {
		if (StringUtils.isNotBlank(page.getOrder()) && StringUtils.isNotBlank(page.getOrderBy())) {
			hqlBuilder.append(" order by ").append(page.getOrderBy()).append(" ").append(page.getOrder());
		} else {
			hqlBuilder.append(" order by t.id desc");
		}
	}

	//@Action(value = "edit", results = { @Result(name = "success", location = "edit.jsp", type = "redirect") })
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

	public Page<TemplateChapter> getPage() {
		return page;
	}

	@Autowired
	public void setTemplateChapterManager(TemplateChapterManager templateChapterManager) {
		this.templateChapterManager = templateChapterManager;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}
}