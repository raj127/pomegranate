<%@ page contentType="text/html;charset=UTF-8" %>
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
	       <tr>
	       		<td width="240" height="120">	
	       		<security:authorize ifAnyGranted="ROLE_系统管理"> 
		       		<table align="center">
			       		<tr>
			       		<td align="center">
			       		<a href="${ctx}/system/user.action" ><img src="${ctx}/images/index-00.png" border="0px" align="top" /></a>
			       		</td>
			       		</tr>
			       		<tr>
			       		<td align="center">
			       		<a style="font-size:15px" href="${ctx}/system/user.action" >系统管理</a>
			       		</td>
			       		</tr>
		       		</table>
	       		</security:authorize>
	       		</td>
	            <td width="240" height="120">	
	            <security:authorize ifAnyGranted="ROLE_任务管理"> 
		            <table align="center">
		            <tr><td align="center">
		            <a href="${ctx}/task/task-manager.action" ><img src="${ctx}/images/index-01.png" border="0px" align="top" /></a>
		            </td></tr>
		            <tr><td align="center"><a style="font-size:15px" href="${ctx}/task/task-manager.action" >任务管理</a></td></tr>
		            </table>
	            </security:authorize>
	            </td>
	        </tr>
	         <tr>
	            <td width="240" height="120"> 
	            <security:authorize ifAnyGranted="ROLE_作业规程编制">
	            <table align="center">
	            	<tr>
	            	<td align="center">
	            	<a href="${ctx}/edit/task-chapter-edit-index.action"><img src="${ctx}/images/index-02.png" border="0px" align="top" /></a>
	            	</td>
	            	</tr>
	            	<tr>
	            	<td align="center"><a style="font-size:15px" href="${ctx}/edit/task-chapter-edit-index.action">规程编制</a>
	            	</td>
	            	</tr>
	            </table>
	            </security:authorize>
	            </td>
	         
	         <td width="240" height="120">
	         <security:authorize ifAnyGranted="ROLE_作业规程审批"> 
	         <table align="center">
	         	<tr>
	         	<td align="center">
	         	<a href="${ctx}/approve/approve.action"><img src="${ctx}/images/index-03.png" border="0px" align="top" /></a>
	         	</td>
	         	</tr>
	         	<tr>
	         	<td align="center">
	         	<a style="font-size:15px" href="${ctx}/approve/approve.action">规程审批</a>
	         	</td>
	         	</tr>
	         </table>
	         </security:authorize>
	         </td>
	         <%-- 
	         <td width="240" height="120">
	         <security:authorize ifAnyGranted="ROLE_作业规程查询">
	         <table align="center">
	         	<tr><td align="center"><a href="${ctx}/query/query.action"><img src="${ctx}/images/index-04.png" border="0px" align="top" /></a></td></tr>
	         	<tr><td align="center"><a style="font-size:15px" href="${ctx}/query/query.action">规程查询</a></td></tr>
	         </table>
	         </security:authorize>
	         </td>
	         <td width="240" height="120">
	         <security:authorize ifAnyGranted="ROLE_作业规程快讯">
	         <table align="center">
	         	<tr><td align="center"><a href="${ctx}/news/news.action"><img src="${ctx}/images/index-05.png" border="0px" align="top" /></a></td></tr>
	         	<tr><td align="center"><a style="font-size:15px" href="${ctx}/news/news.action">规程快讯</a></td></tr></table>
	         </security:authorize>
	         </td>
	         --%>
	        </tr>
	   </table>
	</div>
	</div>
</div>
<%@ include file="/common/footer.jsp" %>
</div>
</body>
</html>

