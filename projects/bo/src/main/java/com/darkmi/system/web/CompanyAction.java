package com.darkmi.system.web;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.StaleStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.darkmi.SystemConfig;
import com.darkmi.common.tools.Cn2Spell;
import com.darkmi.entity.system.Company;
import com.darkmi.system.service.CompanyManager;
import com.darkmi.util.CrudActionSupport;
import com.darkmi.util.FileHelper;
import com.darkmi.util.ServiceException;

/**
 * Description: 公司信息管理Action.
 * Copyright (c) darkmi
 * All Rights Reserved.
 * @version 1.0  2012-05-23 下午01:36:31 darkmi created
 */
@Namespace("/system")
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "company.action?page.pageNo=${page.pageNo}&page.orderBy=${page.orderBy}&page.order=${page.order}&page.pageSize=${page.pageSize}", type = "redirect") })
public class CompanyAction extends CrudActionSupport<Company> {

	private static final long serialVersionUID = 6515585588066931187L;

	//-- 业务操作类 --//
	private CompanyManager companyManager;
	private SystemConfig systemConfig;

	//-- 页面属性 --//
	private Long id;
	private Company entity;
	private Integer workingVersion;//对象版本号, 配合Hibernate的@Version防止并发修改
	private boolean viewOnly = false;
	private Page<Company> page = new Page<Company>(20);//每页20条记录

	//-- ModelDriven 与 Preparable函数 --//

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Company getModel() {
		return entity;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			entity = companyManager.getCompany(id);
		} else {
			entity = new Company();
		}
	}

	//-- CRUD Action 函数 --//

	/**
	 * 公司列表页面.
	 */
	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.DESC);
		}
		page = companyManager.searchCompany(page, filters);
		return SUCCESS;
	}

	/**
	 * 直接进入新公司录入页面.
	 */
	@Override
	public String input() throws Exception {
		return INPUT;
	}

	/**
	 * 保存公司信息并创建公司根目录.
	 */
	@Override
	public String save() throws Exception {
		if (workingVersion != null && workingVersion < entity.getVersion()) {
			throw new StaleStateException("已经被其他人更新");
		}
		//获取公司名并将其转换为英文
		String companyNameCn = entity.getCompanyName();
		String companyNameEn = Cn2Spell.converterToSpell(companyNameCn);
		//创建公司根目录
		createCompanyFolder(companyNameEn);
		//保存公司信息
		entity.setFolder(companyNameEn);
		companyManager.saveCompany(entity);
		addActionMessage("保存公司信息成功");
		return RELOAD;
	}

	/**
	 * 创建公司根目录
	 * @param companyNameEn
	 * @throws Exception
	 */
	private void createCompanyFolder(String companyNameEn) throws Exception {
		logger.debug("createCompanyFolder() { ...");
		ServletContext sc = ServletActionContext.getServletContext();
		String companyPath = FileHelper.getAbsolutePath(sc, systemConfig.getCompanyFolder() + companyNameEn);
		logger.debug("公司根目录为 --> " + companyPath);

		//创建公司根目录
		File companyFolder = new File(companyPath);
		FileUtils.forceMkdir(companyFolder);

		//创建作业规程保存目录
		File taskFolder = new File(companyPath + systemConfig.getTaskFolder());
		FileUtils.forceMkdir(taskFolder);

		//创建作业规程模板保存目录
		File templateFolder = new File(companyPath + systemConfig.getTemplateFolder());
		FileUtils.forceMkdir(templateFolder);

		//创建附件保存目录
		File attachmentFolder = new File(companyPath + systemConfig.getAttachmentFolder());
		FileUtils.forceMkdir(attachmentFolder);
		logger.debug("createCompanyFolder() ...}");
	}

	/**
	 * 删除公司信息及公司根目录.
	 * 该公司所有文件将被物理删除.
	 */
	@Override
	public String delete() throws Exception {
		try {

			//---------------------
			//此处欠考虑的东西很多,比如公司下有员工不允许删除等.
			//开发阶段允许直接删除.
			//---------------------

			//删除DB中的公司信息
			Company company = companyManager.getCompany(id);
			companyManager.deleteCompany(id);
			dbLogger.info(SpringSecurityUtils.getCurrentUserName() + ":删除(" + company.getCompanyName() + " )公司信息！");

			//删除公司根目录
			ServletContext sc = ServletActionContext.getServletContext();
			String folderName = company.getFolder();
			String companyPath = FileHelper.getAbsolutePath(sc, systemConfig.getCompanyFolder() + folderName);
			FileUtils.forceDelete(new File(companyPath));

			addActionMessage("删除公司成功");
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage("删除公司失败");
		}
		return RELOAD;
	}

	//-- 其他Action函数 --//
	/**
	 * 支持使用Jquery.validate Ajax检验用户名是否重复.
	 */
	public String checkCompanyName() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String newCompanyName = request.getParameter("companyName");
		String oldCompanyName = request.getParameter("oldCompanyName");

		if (companyManager.isNameUnique(newCompanyName, oldCompanyName)) {
			Struts2Utils.renderText("true");
		} else {
			Struts2Utils.renderText("false");
		}
		//因为直接输出内容而不经过jsp,因此返回null.
		return null;
	}

	//-- 页面属性访问函数 --//
	/**
	 * list页面显示用户分页列表.
	 */
	public Page<Company> getPage() {
		return page;
	}

	public boolean isViewOnly() {
		return viewOnly;
	}

	public void setViewOnly(boolean viewOnly) {
		this.viewOnly = viewOnly;
	}

	public void setWorkingVersion(Integer workingVersion) {
		this.workingVersion = workingVersion;
	}

	//-- 需要注入的服务类 --//
	@Autowired
	public void setCompanyManager(CompanyManager companyManager) {
		this.companyManager = companyManager;
	}

	@Autowired
	public void setSystemConfig(SystemConfig systemConfig) {
		this.systemConfig = systemConfig;
	}
}