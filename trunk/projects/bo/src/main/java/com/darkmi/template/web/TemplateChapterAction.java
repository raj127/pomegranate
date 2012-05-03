package com.darkmi.template.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.darkmi.SystemConfig;
import com.darkmi.entity.system.Company;
import com.darkmi.entity.template.TemplateChapter;
import com.darkmi.system.service.AccountManager;
import com.darkmi.template.service.TemplateChapterManager;
import com.darkmi.util.CrudActionSupport;

/**
 * 模板目录管理Action.
 * @author darkmi
 */
@Namespace("/template")
@Results({
		@Result(name = CrudActionSupport.RELOAD, location = "template-chapter.action?templateId=${templateId}&page.pageNo=${page.pageNo}&page.orderBy=${page.orderBy}&page.order=${page.order}&page.pageSize=${page.pageSize}", type = "redirect"),
		@Result(name = "edit", location = "edit.jsp") })
public class TemplateChapterAction extends CrudActionSupport<TemplateChapter> {
	private static final long serialVersionUID = 4387918912684322626L;
	private Long id; //目录Id
	private Integer templateId; //模板Id
	private Long parentId; //目录的父Id
	private String fileName; //模板文件名
	private String filePath; //模板的保存路径
	private TemplateChapter templateChapter;
	private TemplateChapterManager tcManager;
	private AccountManager accountManager;
	private SystemConfig systemConfig;

	private Page<TemplateChapter> page = new Page<TemplateChapter>(20);

	@Override
	protected void prepareModel() throws Exception {
		logger.debug("prepareModel begin { ...");
		if (id != null) {
			templateChapter = tcManager.getTemplateChapter(id);
		} else {
			templateChapter = new TemplateChapter();
		}
		logger.debug("prepareModel end ...}");
	}

	@Override
	public TemplateChapter getModel() {
		return templateChapter;
	}

	/**
	 * 显示目录列表.
	 */
	@Override
	public String list() throws Exception {
		logger.debug("list chapter begin { ...");
		StringBuilder hqlBuilder = new StringBuilder();
		Map<String, Object> map = new HashMap<String, Object>();
		builerWhere(hqlBuilder, map);
		builderOrder(hqlBuilder);
		page = tcManager.searchTemplateChapter(page, hqlBuilder.toString(), map);
		logger.debug("list chapter end ...} ");
		return SUCCESS;
	}

	/**
	 * 构建Where查询条件.
	 * @param hqlBuilder
	 * @param map
	 */
	private void builerWhere(StringBuilder hqlBuilder, Map<String, Object> map) {
		if (templateId != null) {
			logger.debug("templateId is --> " + templateId);
			hqlBuilder.append(" and t.templateId=:templateId");
			map.put("templateId", templateId);
		}
	}

	/**
	 * 构建查询中的order by子句.
	 * @param hqlBuilder
	 */
	private void builderOrder(StringBuilder hqlBuilder) {
		if (StringUtils.isNotBlank(page.getOrder()) && StringUtils.isNotBlank(page.getOrderBy())) {
			hqlBuilder.append(" order by ").append(page.getOrderBy()).append(" ").append(page.getOrder());
		} else {
			hqlBuilder.append(" order by t.displayOrder asc");
		}
	}

	/**
	 * 获取用户公司的根目录.
	 * @return
	 */
	private String getFolder() {
		String loginName = SpringSecurityUtils.getCurrentUserName();
		logger.debug("current user loginName is --> {}", loginName);
		Company company = accountManager.getCompanyByLoginName(loginName);
		return company.getFolder();
	}

	/**
	 * 保存目录信息.
	 */
	@Override
	public String save() throws Exception {
		logger.debug("begin save { ...");
		tcManager.saveTemplateChapter(templateChapter);
		addActionMessage("保存作业规程任务成功！");
		logger.debug("end save ...}");
		return RELOAD;
	}

	/**
	 * 进入目录录入界面.
	 */
	@Override
	public String input() throws Exception {
		if (templateId == null) {
			templateId = 0;
		}
		return INPUT;
	}

	/**
	 * 删除指定目录.
	 */
	@Override
	public String delete() throws Exception {
		int size = tcManager.getChildTemplateChapter(id).size();
		if (size > 0) {
			addActionMessage("该目录下有二级目录,不能删除！");
			return RELOAD;
		} else {
			tcManager.deleteTemplateChapter(id);
			addActionMessage("删除作业规程模板目录成功！");
			return RELOAD;
		}
	}

	/**
	 * 进入模板编辑页面
	 * @return
	 */
	//@Action(value = "edit", results = { @Result(name = "success", location = "edit.jsp", type = "redirect") })
	public String edit() {
		logger.debug("编辑作业规程... begin{ ");

		filePath = systemConfig.getTemplateDefault() + fileName;
		logger.debug("filePath --> {}", filePath);

		logger.debug("编辑作业规程... end} ");
		return "edit";
	}

	/**
	 * 获取该模板的所有一级目录
	 * @return
	 */
	public List<TemplateChapter> getAllParentChapter() {
		logger.debug("获得一级目录 begin { ...");
		List<TemplateChapter> list = tcManager.getParentTemplateChapter();
		logger.debug("获取到一级目录数量为 --》 {}", list.size());
		logger.debug("获得一级目录 end ...}");
		return list;

	}

	/*~~~~~~~~~~~Setters And Getters ~~~~~~~~~~~~~~~~~*/
	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Page<TemplateChapter> getPage() {
		return page;
	}

	/*~~~~~~~~~~~业务逻辑类注入~~~~~~~~~~~~~~~~~*/
	@Autowired
	public void setTemplateChapterManager(TemplateChapterManager templateChapterManager) {
		this.tcManager = templateChapterManager;
	}

	@Autowired
	public void setSystemConfig(SystemConfig systemConfig) {
		this.systemConfig = systemConfig;
	}

	@Autowired
	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}

}
