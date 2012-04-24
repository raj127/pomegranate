package com.darkmi.system.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.StaleStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.darkmi.entity.system.Company;
import com.darkmi.system.service.CompanyManager;
import com.darkmi.util.CrudActionSupport;
import com.darkmi.util.ServiceException;

/**
 * 公司信息管理Action.
 * 
 * 使用Struts2 convention-plugin annotation定义Action参数.
 * 
 * @author darkmi
 */
//定义URL映射对应/system/Company.action
@Namespace("/system")
//定义名为reload的result重定向到Company.action, 其他result则按照convention默认.
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "company.action?page.pageNo=${page.pageNo}&page.orderBy=${page.orderBy}&page.order=${page.order}&page.pageSize=${page.pageSize}", type = "redirect") })
public class CompanyAction extends CrudActionSupport<Company> {

	private static final long serialVersionUID = 6515585588066931187L;
	private CompanyManager companyManager;

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

	@Override
	public String input() throws Exception {
		return INPUT;
	}

	@Override
	public String save() throws Exception {
		if (workingVersion != null && workingVersion < entity.getVersion()) {
			throw new StaleStateException("已经被其他人更新");
		}
		companyManager.saveCompany(entity);
		addActionMessage("保存公司信息成功");
		return RELOAD;
	}

	@Override
	public String delete() throws Exception {
		try {
			//Company Company = companyManager.getCompany(id);
			companyManager.deleteCompany(id);
			//dbLogger.info(SpringSecurityUtils.getCurrentCompanyName() + ":删除" + Company.getName() + "用户！");
			addActionMessage("删除用户成功");
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage("删除用户失败");
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

	@Autowired
	public void setCompanyManager(CompanyManager companyManager) {
		this.companyManager = companyManager;
	}

	public void setWorkingVersion(Integer workingVersion) {
		this.workingVersion = workingVersion;
	}

}
