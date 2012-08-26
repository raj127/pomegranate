package com.darkmi.template.web;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
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

import com.darkmi.SystemConfig;
import com.darkmi.common.tools.Cn2Spell;
import com.darkmi.entity.system.Company;
import com.darkmi.entity.template.Template;
import com.darkmi.jacob.JacobWord;
import com.darkmi.system.service.AccountManager;
import com.darkmi.template.service.TemplateManager;
import com.darkmi.util.CrudActionSupport;
import com.darkmi.util.FileHelper;

/**
 * Description: 模板管理Action.
 * Copyright (c) www.darkmi.com
 * All Rights Reserved.
 * @version 1.0  2012-05-10 上午09:20:11 DarkMi created
 */
@Namespace("/template")
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "template.action?page.pageNo=${page.pageNo}&page.orderBy=${page.orderBy}&page.order=${page.order}&page.pageSize=${page.pageSize}", type = "redirect") })
public class TemplateAction extends CrudActionSupport<Template> {
	private static final long serialVersionUID = -2907389496513631586L;
	private Long id; //模板Id
	private Template template;
	private TemplateManager templateManager;
	private AccountManager accountManager;
	private JacobWord jacobWord;
	private SystemConfig systemConfig;
	private Page<Template> page = new Page<Template>(20);
	private boolean viewOnly = false;

	/**
	 * 模板列表页面.
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
		page = templateManager.searchTemplate(page, filters);
		return SUCCESS;
	}

	/**
	 * 进入模板信息录入页面.
	 */
	@Override
	public String input() throws Exception {
		return INPUT;
	}

	/**
	 * 保存模板信息.
	 */
	@Override
	public String save() throws Exception {
		template.setPath(getPath(template.getTemplateName()));
		//获取当前工号所属单位
		String loginName = SpringSecurityUtils.getCurrentUserName();
		logger.debug("current user loginName is --> {}", loginName);
		Company company = accountManager.getCompanyByLoginName(loginName);
		template.setCompany(company);
		templateManager.saveTemplate(template);
		FileHelper.mkDir(ServletActionContext.getServletContext(), template.getPath());
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

	/**
	 * 导出模板.
	 */
	public String export() throws Exception {
		logger.debug("export template begin { ...");
		logger.debug("id --> {}", id);
		Template template = templateManager.getTemplate(id);
		logger.debug("template --> {}", template);
		ServletContext sc = ServletActionContext.getServletContext();
		String aPath = FileHelper.getAbsolutePath(sc, template.getPath());
		logger.debug("文件保存的绝对路径为 -->{}", aPath);

		//new JacobWord().mergeWord(new File(aPath).listFiles(), aPath);
		//logger.debug("jacobWord -->{}", jacobWord);
		jacobWord.mergeWord(new File(aPath).listFiles(), aPath);
		//createWord();
		//testJacob();

		logger.debug("export template end ...} ");
		return RELOAD;
	}

	/**
	 * 获取用户公司的根目录.
	 * @return
	 */
	private String getPath(String templateName) {
		String companyNameEn = Cn2Spell.converterToSpell(templateName);
		//获取当前工号所属单位
		String loginName = SpringSecurityUtils.getCurrentUserName();
		logger.debug("current user loginName is --> {}", loginName);
		Company company = accountManager.getCompanyByLoginName(loginName);

		StringBuffer sb = new StringBuffer();
		sb.append(ServletActionContext.getRequest().getContextPath());
		sb.append("/");
		sb.append(systemConfig.getCompanyFolder());
		sb.append(company.getFolder());
		sb.append(systemConfig.getTemplateFolder());
		sb.append(companyNameEn);
		sb.append("/");
		String path = sb.toString();
		logger.debug("template path is --> {}", path);

		return path;
	}

	/*~~~~~~~~~~~ 校验函数 ~~~~~~~~~~~~~~~~~*/

	/**
	 * 校验模板路径是否重复.
	 */
	public String checkPath() {
		logger.debug("检查模板路径是否唯一  begin { ...");
		HttpServletRequest request = ServletActionContext.getRequest();
		String newPath = request.getParameter("path");
		String oldPath = request.getParameter("oldPath");
		logger.debug("newPath --> {}", newPath);
		logger.debug("oldPath --> {}", oldPath);
		if (templateManager.isPathUnique(newPath, oldPath)) {
			logger.debug("没有重复项");
			Struts2Utils.renderText("true");
		} else {
			logger.debug("有重复项");
			Struts2Utils.renderText("false");
		}
		logger.debug("检查模板路径是否唯一  end ...}");
		//因为直接输出内容而不经过jsp,因此返回null.
		return null;
	}

	/*~~~~~~~~~~~ 重载方法 ~~~~~~~~~~~~~~~~~*/
	@Override
	protected void prepareModel() throws Exception {
		logger.debug("begin prepareModel {...");
		logger.debug("id --> {}", id);
		if (id != null) {
			template = templateManager.getTemplate(id);
			logger.debug("template --> {}", template.toString());
		} else {
			template = new Template();
		}
		logger.debug("begin prepareModel ...}");
	}

	@Override
	public Template getModel() {
		return template;
	}

	/*~~~~~~~~~~~Setters And Getters ~~~~~~~~~~~~~~~~~*/

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public Page<Template> getPage() {
		return page;
	}

	public boolean isViewOnly() {
		return viewOnly;
	}

	public void setViewOnly(boolean viewOnly) {
		this.viewOnly = viewOnly;
	}

	/*~~~~~~~~~~~业务逻辑类注入~~~~~~~~~~~~~~~~~*/
	@Autowired
	public void setTemplateManager(TemplateManager templateManager) {
		this.templateManager = templateManager;
	}

	@Autowired
	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}

	@Autowired
	public void setSystemConfig(SystemConfig systemConfig) {
		this.systemConfig = systemConfig;
	}

	@Autowired
	public void setJacobWord(JacobWord jacobWord) {
		this.jacobWord = jacobWord;
	}
}
