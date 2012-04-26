package com.darkmi.template.web;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.darkmi.entity.template.Template;
import com.darkmi.template.service.TemplateManager;
import com.darkmi.util.CrudActionSupport;

/**
 * 模板管理Action.
 * @author darkmi
 */
@Namespace("/template")
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "template.action?page.pageNo=${page.pageNo}&page.orderBy=${page.orderBy}&page.order=${page.order}&page.pageSize=${page.pageSize}", type = "redirect") })
public class TemplateAction extends CrudActionSupport<Template> {
	private static final long serialVersionUID = -2907389496513631586L;
	private Long id;
	private Template template;
	private TemplateManager templateManager;
	private Page<Template> page = new Page<Template>(20);

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			template = templateManager.getTemplate(id);
		} else {
			template = new Template();
		}

	}

	@Override
	public Template getModel() {
		return template;
	}

	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.DESC);
		}
		page = templateManager.searchTemplate(page, filters);
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		return INPUT;
	}

	@Override
	public String save() throws Exception {
		templateManager.saveTemplate(template);
		addActionMessage("保存作业规程模板信息成功！");
		return RELOAD;
	}

	/**
	 * 删除模板信息.
	 */
	@Override
	public String delete() throws Exception {
		template = templateManager.getTemplate(id);
		templateManager.deleteTemplate(id);
		dbLogger.info(SpringSecurityUtils.getCurrentUserName() + ":删除" + template.getTemplateName() + "作业规程模板！");
		addActionMessage("删除作业规程模板信息成功！");
		return RELOAD;
	}

	public String getChapters() {

		return "getChaptersucess";
	}

	/**
	 * 支持使用Jquery.validate Ajax检验用户名是否重复.
	 */
	//	public String checkTaskName() {
	//		HttpServletRequest request = ServletActionContext.getRequest();
	//		String providerId = request.getParameter("employeeName");
	//		String oldProviderId = request.getParameter("oldemployeeName");
	//
	//		if (templateManager.isTaskNameUnique(providerId, oldProviderId)) {
	//			Struts2Utils.renderText("true");
	//		} else {
	//			Struts2Utils.renderText("false");
	//		}
	//		//因为直接输出内容而不经过jsp,因此返回null.
	//		return null;
	//	}

	public Page<Template> getPage() {
		return page;
	}

	@Autowired
	public void setTemplateManager(TemplateManager templateManager) {
		this.templateManager = templateManager;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
