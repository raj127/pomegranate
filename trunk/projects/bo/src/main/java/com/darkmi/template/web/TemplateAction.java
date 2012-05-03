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
	private Long id; //模板Id
	private Template template;
	private TemplateManager templateManager;
	private Page<Template> page = new Page<Template>(20);

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

	/*~~~~~~~~~~~ 重载方法 ~~~~~~~~~~~~~~~~~*/
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

	/*~~~~~~~~~~~Setters And Getters ~~~~~~~~~~~~~~~~~*/

	public Page<Template> getPage() {
		return page;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/*~~~~~~~~~~~业务逻辑类注入~~~~~~~~~~~~~~~~~*/
	@Autowired
	public void setTemplateManager(TemplateManager templateManager) {
		this.templateManager = templateManager;
	}

}
