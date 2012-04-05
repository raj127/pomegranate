<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<div id="hd">
	<div id="title">
	<table class="noborder" width="100%" background="${ctx}/images/top_bg.jpg" height="69px">
	  <tr><td align="left" valign="top" id="headtableBO"><!--<img class="logo" src="${ctx}/images/logo.png"></img>-->作业规程编制与管理系统  </td>
	<td align="right" class="headtable" id="headtable"><% if(!"anonymousUser".equals(SpringSecurityUtils.getCurrentUserName())){%>您好, <%=SpringSecurityUtils.getCurrentUserName()%><%} %><% if(!"anonymousUser".equals(SpringSecurityUtils.getCurrentUserName())){%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="${ctx}/images/editing.gif" border="0px" align="top" /><security:authorize ifAnyGranted="ROLE_修改密码"><a href="${ctx}/system/modify-password.action" id="n10"> 修改密码</a></security:authorize> &nbsp;<img src="${ctx}/images/exit.png" border="0px" align="top" /><a href="${ctx}/j_spring_security_logout" id="n11" onclick="return confirm('确定退出吗？')">退出登录</a>&nbsp;&nbsp;<%} %></td></tr></table>
	</div>
	
</div>