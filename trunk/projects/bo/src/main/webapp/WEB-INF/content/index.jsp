﻿<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter" %>
<%@ page import="org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter" %>
<%@ page import="org.springframework.security.web.WebAttributes" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>首页</title>
	<%@ include file="/common/meta.jsp" %>
	<link href="${ctx}/css/yui.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx}/css/style.css" type="text/css" rel="stylesheet"/>
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
	<script>
		$(document).ready(function() {
			$(".mainNav a").attr("class","");
			$("#n1").attr("class","actived");
			$(".secondNav div").each(function(){
				$(this).hide();
				$("#subNav1").show();
			});
		});
	</script>
</head>
<body>
<div id="doc3">
<%@ include file="/common/header.jsp" %>
<div id="bd">
	<div id="yui-main">
	<div class="yui-b">
	   <table align="center" class="noborder">
	   	   <tr><td height="60"></td></tr>
	       <tr>
	       		<td width="240" height="120">	
	       		<security:authorize ifAnyGranted="ROLE_浏览用户"> 
	       		<table align="center"><tr><td><a href="${ctx}/system/user.action" ><img src="${ctx}/images/index-00.png" border="0px" align="top" /></a></td></tr><tr><td align="center" ><a style="font-size:15px" href="${ctx}/system/user.action" >系统管理</a></td></tr></table>
	       		</security:authorize>
	       		</td>
	            <td width="240" height="120">	
	            <security:authorize ifAnyGranted="ROLE_浏览电影"> 
	            <table align="center"><tr><td><a href="${ctx}/cms/movie.action" ><img src="${ctx}/images/index-01.png" border="0px" align="top" /></a></td></tr><tr><td align="center"><a style="font-size:15px" href="${ctx}/cms/movie.action" >任务管理</a></td></tr></table>
	            </security:authorize>
	            </td>
	            <td width="240" height="120"> 
	            <security:authorize ifAnyGranted="ROLE_浏览录制列表">
	            <table align="center"><tr><td><a href="${ctx}/rtm/recording.action?state=normal"><img src="${ctx}/images/index-02.png" border="0px" align="top" /></a></td></tr><tr><td align="center"><a style="font-size:15px" href="${ctx}/rtm/recording.action?state=normal">规程编制</a></td></tr></table>
	            </security:authorize>
	            </td>
	        </tr>
	         <tr>
	         <td width="240" height="120">
	         <security:authorize ifAnyGranted="ROLE_浏览元数据"> <table align="center"><tr><td><a href="${ctx}/apm/package-asset.action"><img src="${ctx}/images/index-03.png" border="0px" align="top" /></a></td></tr><tr><td align="center"><a style="font-size:15px" href="${ctx}/apm/package-asset.action">规程审批</a></td></tr></table>
	         </security:authorize>
	         </td>
	         <td width="240" height="120"><security:authorize ifAnyGranted="ROLE_浏览OFFERING"><table align="center"><tr><td><a href="${ctx}/odm/offering.action"><img src="${ctx}/images/index-04.png" border="0px" align="top" /></a></td></tr><tr><td align="center"><a style="font-size:15px" href="${ctx}/odm/offering.action">规程查询</a></td></tr></table></security:authorize></td>
	         <td width="240" height="120"><security:authorize ifAnyGranted="ROLE_浏览授权管理"><table align="center"><tr><td><a href="${ctx}/ens/customer.action"><img src="${ctx}/images/index-05.png" border="0px" align="top" /></a></td></tr><tr><td align="center"><a style="font-size:15px" href="${ctx}/ens/customer.action">规程快讯</a></td></tr></table></security:authorize></td>
	        </tr>
	         <%-- <tr>
	         <td width="240" height="120"> 	<security:authorize ifAnyGranted="ROLE_浏览视频服务器"><table align="center"><tr><td><a href="${ctx}/rm/video-server.action"><img src="${ctx}/images/index-06.png" border="0px" align="top" /></a></td></tr><tr><td align="center"><a style="font-size:15px" href="${ctx}/rm/video-server.action">资源管理</a></td></tr></table></security:authorize></td>
	         <td width="240" height="120"> <security:authorize ifAnyGranted="ROLE_浏览SRM会话"><table align="center"><tr><td><a href="${ctx}/sm/session.action"><img src="${ctx}/images/index-08.png" border="0px" align="top" /></a></td></tr><tr><td align="center"><a style="font-size:15px" href="${ctx}/sm/session.action" id="n12">会话管理</a></td></tr></table></security:authorize></td>
	         <td width="240" height="120"> <security:authorize ifAnyGranted="ROLE_浏览页面发布"><table align="center"><tr><td><a href="${ctx}/pps/offering-info-movie.action"><img src="${ctx}/images/index-07.png" border="0px" align="top" /></a></td></tr><tr><td align="center"><a style="font-size:15px" href="${ctx}/pps/offering-info-movie.action">页面发布</a></td></tr></table></security:authorize></td>
	        </tr>--%>
	        <%--<tr>
	           <td width="240" height="120"> <security:authorize ifAnyGranted="ROLE_回传服务"><table align="center"><tr><td><a href="${ctx}/ps/phone-customer.action"><img src="${ctx}/images/index-10.png" border="0px" align="top" /></a></td></tr><tr><td align="center"><a style="font-size:15px" href="${ctx}/ps/phone-customer.action">回传服务</a></td></tr></table></security:authorize></td>
	           <td width="240" height="120"><security:authorize ifAnyGranted="ROLE_浏览报表">  <table align="center"><tr><td><a href="${ctx}/report/report-index.action"><img src="${ctx}/images/index-09.png" border="0px" align="top" /></a></td></tr><tr><td align="center"><a style="font-size:15px" href="${ctx}/report/report-index.action">报表</a></td></tr></table></security:authorize></td>
	        </tr>--%>
	   
	   </table>
	</div>
	</div>
</div>
<%@ include file="/common/footer.jsp" %>
</div>
</body>
</html>

