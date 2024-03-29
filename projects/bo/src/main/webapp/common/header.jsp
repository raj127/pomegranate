<%--
Description:菜单页面
author:darkmi
date:2012/08/21
--%>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<div id="hd">
	<div id="title">
	<table class="noborder" width="100%" background="${ctx}/images/top_bg.jpg" >
	  <tr>
	  <td align="left" valign="top" height="69px" id="headtableBO">作业规程编制与管理系统 </td>
	  <td align="right" class="headtable" id="headtable">
	  <% if(!"anonymousUser".equals(SpringSecurityUtils.getCurrentUserName())){ %>
	  		您好, <%=SpringSecurityUtils.getCurrentUserName()%>
	  <% } %>
	  	 <% if(!"anonymousUser".equals(SpringSecurityUtils.getCurrentUserName())){%>
	  	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="${ctx}/images/editing.gif" border="0px" align="top" />
	  	 <security:authorize ifAnyGranted="ROLE_修改密码"><a href="${ctx}/system/modify-password.action" id="n10"> 修改密码</a></security:authorize> &nbsp;<img src="${ctx}/images/exit.png" border="0px" align="top" /><a href="${ctx}/j_spring_security_logout" id="n11" onclick="return confirm('确定退出吗？')">退出登录</a>&nbsp;&nbsp;<%} %>
	  </td>
	  </tr>
	</table>
	</div>
	<div class="header">
	<div class="padder">
		<div class="nav">
			<div class="navLaftBg">
				<div class="navRightBg">
					<div class="mainNav">
						<security:authorize ifAnyGranted="ROLE_首页浏览">
							<a href="${ctx}/index.action" id="n1" class="actived">首页</a>
						</security:authorize>
						<security:authorize ifAnyGranted="ROLE_系统管理">
							<a href="${ctx}/system/user.action" id="n2">系统管理</a>
						</security:authorize>
						
						<security:authorize ifAnyGranted="ROLE_模板管理">
							<a href="${ctx}/template/template.action" id="n3">模板管理</a>
						</security:authorize>	
											
						<security:authorize ifAnyGranted="ROLE_任务管理">
							<a href="${ctx}/task/task-manager.action" id="n4">任务管理</a>
						</security:authorize>
							
						<security:authorize ifAnyGranted="ROLE_作业规程编制">
							<a href="${ctx}/edit/task-chapter-edit-index.action" id="n5">规程编制</a>
						</security:authorize>
							
						<security:authorize ifAnyGranted="ROLE_作业规程审批">
							<a href="${ctx}/approve/approve.action" id="n6">规程审批</a>
						</security:authorize>
						
						<%-- 
						<security:authorize ifAnyGranted="ROLE_作业规程查询">
							<a href="${ctx}/query/query.action" id="n7">规程查询</a>
						</security:authorize>
						
						<security:authorize ifAnyGranted="ROLE_作业规程快讯">
							<a href="${ctx}/news/news.action" id="n8">规程快讯</a>
						</security:authorize>
						
						<security:authorize ifAnyGranted="ROLE_在线帮助">
							<a href="${ctx}/help/help.action" id="n9">在线帮助</a>
						</security:authorize>
						--%>
					</div>
				</div>
			</div>
		</div>
		
		<div class="secondNav">
			<security:authorize ifAnyGranted="ROLE_首页浏览">
				<div id="subNav1" class="subNav1">
					<a href="${ctx}/index.action" id="n1" class="actived">首页</a>
				</div>
			</security:authorize>
					
			<security:authorize ifAnyGranted="ROLE_系统管理">
				<div id="subNav2" class="subNav2">
					<%--<a id="subNav201" href="${ctx}/system/company.action" >公司列表</a>--%>
					<a id="subNav202" href="${ctx}/system/user.action" >用户列表</a>
					<a id="subNav203" href="${ctx}/system/role.action" >角色列表</a>
					<%-- <a id="subNav204" href="${ctx}/system/config.action" >配置列表</a>--%>
					<a id="subNav205" href="${ctx}/system/log.action" >日志列表</a>
					<a id="subNav206" href="${ctx}/system/specification-chapter-table.action" >规范管理</a>
					<a id="subNav208" href="${ctx}/system/specification-chapter-index.action" >索引维护</a>
				</div>
			</security:authorize>
			
			<security:authorize ifAnyGranted="ROLE_模板管理">
				<div id="subNav3" class="subNav3">
					<a id="subNav301" href="${ctx}/template/template.action" >模板列表</a>
				</div>
			</security:authorize>

			<security:authorize ifAnyGranted="ROLE_任务管理">
					<div id="subNav4" class="subNav4">
						<a id="subNav401" href="${ctx}/task/task-manager.action" >任务管理</a>
					</div>
			</security:authorize>
			<security:authorize ifAnyGranted="ROLE_作业规程编制">
				<div id="subNav5" class="subNav5">
					<a id="subNav501" href="${ctx}/edit/task-chapter-edit-index.action" >规程编制</a>
				</div>
			</security:authorize>
			
			<security:authorize ifAnyGranted="ROLE_作业规程审批">
				<div id="subNav6" class="subNav6">
					<a  id="subNav601" href="${ctx}/approve/approve.action" >作业规程审批</a>
				</div>
			</security:authorize>
			
			<%-- 
			<security:authorize ifAnyGranted="ROLE_作业规程查询">
				<div id="subNav7" class="subNav7">
					<a  id="subNav701" href="${ctx}/query/query.action" >作业规程查询</a>
				</div>
			</security:authorize>

			<security:authorize ifAnyGranted="ROLE_作业规程快讯">
				<div id="subNav8" class="subNav8">
					<a  id="subNav801" href="${ctx}/news/news.action" >作业规程快讯</a>
				</div>
			</security:authorize>

			<security:authorize ifAnyGranted="ROLE_在线帮助">
				<div id="subNav9" class="subNav9">
					<a  id="subNav901" href="${ctx}/help/help.action" >在线帮助</a>
				</div>
			</security:authorize>
			--%>
		</div>
	</div>
</div>
</div>