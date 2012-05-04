package com.darkmi.system.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.utils.web.struts2.Struts2Utils;


import com.darkmi.entity.system.Authority;
import com.darkmi.entity.system.Role;
import com.darkmi.system.service.AccountManager;
import com.darkmi.util.CrudActionSupport;
import com.darkmi.util.HibernateUtils;

/**
 * 角色管理Action.
 * 
 * 
 * @author calvin
 */
@Namespace("/system")
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "role.action", type = "redirect") })
public class RoleAction extends CrudActionSupport<Role> {

	private static final long serialVersionUID = -4052047494894591406L;

	private AccountManager accountManager;

	//-- 页面属性 --//
	private Long id;
	private Role entity;
	private List<Role> allRoleList;//角色列表
	private List<Long> checkedAuthIds;//页面中钩选的权限id列表
	private Page<Role> page = new Page<Role>(20);
	private boolean viewOnly = false;

	//-- ModelDriven 与 Preparable函数 --//
	@Override
	public Role getModel() {
		return entity;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			entity = accountManager.getRole(id);
		} else {
			entity = new Role();
		}
	}

	//-- CRUD Action 函数 --//
	@Override
	public String list() throws Exception {
		allRoleList = accountManager.getAllRole();

		//构造分页对象
		List<Role> resultlist = new ArrayList<Role>();
		int startIndex = (page.getPageNo() - 1) * page.getPageSize();
		int endIndex = allRoleList.size() >= page.getPageNo() * page.getPageSize() ? page.getPageNo()
				* page.getPageSize() : allRoleList.size();
		for (int i = startIndex; i < endIndex; i++) {
			resultlist.add(allRoleList.get(i));
		}
		page.setTotalCount(allRoleList.size());
		page.setResult(resultlist);

		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		checkedAuthIds = entity.getAuthIds();
		return INPUT;
	}

	@Override
	public String save() throws Exception {
		//根据页面上的checkbox 整合Role的Authorities Set.
		HibernateUtils.mergeByCheckedIds(entity.getAuthorityList(), checkedAuthIds, Authority.class);
		//保存用户并放入成功信息.
		accountManager.saveRole(entity);
		addActionMessage("保存角色成功");
		return RELOAD;
	}

	@Override
	public String delete() throws Exception {
		Role role = accountManager.getRole(id);
		accountManager.deleteRole(id);
		dbLogger.info(SpringSecurityUtils.getCurrentUserName() + ":删除" + role.getName() + "角色！");
		addActionMessage("删除角色成功");
		return RELOAD;
	}

	//-- 其他Action函数 --//
	/**
	 * 支持使用Jquery.validate Ajax检验用户名是否重复.
	 */
	public String checkRoleName() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String newRoleName = request.getParameter("name");
		String oldRoleName = request.getParameter("oldName");
		logger.debug("newRoleName --> {}", newRoleName);
		logger.debug("oldRoleName --> {}", oldRoleName);
		if (accountManager.isRoleNameUnique(newRoleName, oldRoleName)) {
			Struts2Utils.renderText("true");
		} else {
			Struts2Utils.renderText("false");
		}
		//因为直接输出内容而不经过jsp,因此返回null.
		return null;
	}

	//-- 页面属性访问函数 --//
	/**
	 * list页面显示所有角色列表.
	 */
	public List<Role> getAllRoleList() {
		return allRoleList;
	}

	/**
	 * input页面显示所有授权列表.
	 */
	public List<Authority> getAllAuthorityList() {
		return accountManager.getAllAuthority();
	}

	/**
	 * input页面显示角色拥有的授权.
	 */
	public List<Long> getCheckedAuthIds() {
		return checkedAuthIds;
	}

	/**
	 * input页面提交角色拥有的授权.
	 */
	public void setCheckedAuthIds(List<Long> checkedAuthIds) {
		this.checkedAuthIds = checkedAuthIds;
	}

	public Page<Role> getPage() {
		return page;
	}

	public void setPage(Page<Role> page) {
		this.page = page;
	}

	public boolean isViewOnly() {
		return viewOnly;
	}

	public void setViewOnly(boolean viewOnly) {
		this.viewOnly = viewOnly;
	}

	@Autowired
	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}
}