package com.darkmi.system.service;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;


import com.darkmi.common.Constants;
import com.darkmi.entity.system.Authority;
import com.darkmi.entity.system.Role;
import com.darkmi.entity.system.User;
import com.google.common.collect.Sets;

/**
 * 实现SpringSecurity的UserDetailsService接口,实现获取用户Detail信息的回调函数.
 * 
 * @author calvin
 */
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {

	private AccountManager accountManager;
	private Logger dbLogger = LoggerFactory.getLogger(Constants.DB_LOGGER_NAME);

	/**
	 * 获取用户Details信息的回调函数.
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {

		User user = accountManager.findUserByLoginName(username);
		if (user == null) {
			throw new UsernameNotFoundException("用户" + username + " 不存在");
		}

		Set<GrantedAuthority> grantedAuths = obtainGrantedAuthorities(user);

		//-- bo示例中无以下属性, 暂时全部设为true. --//
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		UserDetails userdetails = new org.springframework.security.core.userdetails.User(user.getLoginName(),
				user.getShaPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
				grantedAuths);

		//更新最后登陆时间
		//user.setLoginTime(new Date());
		//accountManager.saveUser(user);
		dbLogger.info(user.getLoginName() + ":登录系统！");
		return userdetails;
	}

	/**
	 * 获得用户所有角色的权限集合.
	 */
	private Set<GrantedAuthority> obtainGrantedAuthorities(User user) {
		Set<GrantedAuthority> authSet = Sets.newHashSet();
		for (Role role : user.getRoleList()) {
			for (Authority authority : role.getAuthorityList()) {
				authSet.add(new GrantedAuthorityImpl(authority.getPrefixedName()));
			}
		}
		return authSet;
	}

	@Autowired
	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}
}
