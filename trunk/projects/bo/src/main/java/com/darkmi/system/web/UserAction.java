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
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.darkmi.entity.system.Company;
import com.darkmi.entity.system.Role;
import com.darkmi.entity.system.User;
import com.darkmi.system.service.AccountManager;
import com.darkmi.system.service.CompanyManager;
import com.darkmi.util.CrudActionSupport;
import com.darkmi.util.HibernateUtils;
import com.darkmi.util.ServiceException;

/**
 * 用户管理Action.
 * 
 * 使用Struts2 convention-plugin annotation定义Action参数.
 * 
 * @author calvin
 */
//定义URL映射对应/system/user.action
@Namespace("/system")
//定义名为reload的result重定向到user.action, 其他result则按照convention默认.
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "user.action?page.pageNo=${page.pageNo}&page.orderBy=${page.orderBy}&page.order=${page.order}&page.pageSize=${page.pageSize}", type = "redirect") })
public class UserAction extends CrudActionSupport<User> {

	private static final long serialVersionUID = 8683878162525847072L;

	private AccountManager accountManager;
	private CompanyManager companyManager;

	//-- 页面属性 --//
	private Long id;
	private User entity;
	private Page<User> page = new Page<User>(20);//每页20条记录
	private List<Long> checkedRoleIds; //页面中钩选的角色id列表
	private Integer workingVersion;//对象版本号, 配合Hibernate的@Version防止并发修改
	private Long companyId;
	private boolean viewOnly = false;

	//-- ModelDriven 与 Preparable函数 --//
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public User getModel() {
		return entity;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			entity = accountManager.getUser(id);
			companyId = entity.getCompany().getId();
			logger.debug("companyId --> {}", companyId);
		} else {
			entity = new User();
		}
	}

	//-- CRUD Action 函数 --//
	@Override
	public String list() throws Exception {
		logger.debug("list() begin { ...");
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.DESC);
		}
		page = accountManager.searchUser(page, filters);
		logger.debug("list() end ...}");
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		checkedRoleIds = entity.getRoleIds();
		return INPUT;
	}

	@Override
	public String save() throws Exception {
		logger.debug("save() begin { ...");
		if (workingVersion != null && workingVersion < entity.getVersion()) {
			throw new StaleStateException("已经被其他人更新");
		}
		//根据页面上的checkbox选择 整合User的Roles Set
		HibernateUtils.mergeByCheckedIds(entity.getRoleList(), checkedRoleIds, Role.class);

		accountManager.saveUser(entity, companyId);
		addActionMessage("保存用户成功");
		logger.debug("save() end ...}");
		return RELOAD;
	}

	@Override
	public String delete() throws Exception {
		try {
			User user = accountManager.getUser(id);
			
			if(user.getId() == 1){
				addActionMessage("创始人账号不能删除");
				return RELOAD;
			}
			
			accountManager.deleteUser(id);
			dbLogger.info(SpringSecurityUtils.getCurrentUserName() + ":删除" + user.getName() + "用户！");
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
	public String checkLoginName() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String newLoginName = request.getParameter("loginName");
		String oldLoginName = request.getParameter("oldLoginName");

		if (accountManager.isLoginNameUnique(newLoginName, oldLoginName)) {
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
	public Page<User> getPage() {
		return page;
	}

	/**
	 * input页面显示用户拥有的角色.
	 */
	public List<Long> getCheckedRoleIds() {
		return checkedRoleIds;
	}

	/**
	 * input页面提交用户拥有的角色.
	 */
	public void setCheckedRoleIds(List<Long> checkedRoleIds) {
		this.checkedRoleIds = checkedRoleIds;
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

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	//-- 获取页面列表选项 --//

	/**
	 * input页面显示所有角色列表.
	 */
	public List<Role> getAllRoleList() {
		return accountManager.getAllRole();
	}

	/**
	 * input页面显示所有公司列表.
	 */
	public List<Company> getAllCompany() {
		return companyManager.getAllCompany();
	}

	//-- 注入业务逻辑类 --//

	@Autowired
	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}

	@Autowired
	public void setCompanyManager(CompanyManager companyManager) {
		this.companyManager = companyManager;
	}
}
