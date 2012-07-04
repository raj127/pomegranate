<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>作业规程校验</title>
	<%@ include file="/common/meta.jsp" %>
	<script type="text/javascript" src="${ctx}/js/temp/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/temp/jquery.textarea.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/js/temp/global.css" media="all">

	<script language="javascript">
	//show manual links
	function showManuals(link) {
		var obj = $(link);
		window.parent.setManualPosition(".manual", obj.position().left, obj.position().top + obj.height() + 3);
	}
	
	function showServerMenu(link) {
		alert("待实现");
		//var obj = $(link);
		//window.parent.setManualPosition(".server-menu", obj.position().left, obj.position().top + obj.height() + 3);
	}
	</script>
</head>
<body>
	<div class="top">
		<div class="left">
		  当前编辑的作业规程名称：${taskName} &nbsp;|&nbsp;
		当前编辑的章节：${chapterName}
		<!--  <a href="#" onclick="showServerMenu(this);return false;">工具1 </a>| 
		<a href="#" onclick="showServerMenu(this);return false;">工具2</a>--> 
		</div>
		<div class="right">
		<% if(!"anonymousUser".equals(SpringSecurityUtils.getCurrentUserName())){ %>
		当前用户： <%=SpringSecurityUtils.getCurrentUserName()%> | 
		<% } %>
		<a href="#" onclick="javascript:parent.window.opener=null;parent.window.close();">退出</a>
		<!--
		<a href="#" onclick="showServerMenu(this);return false;">工具3 </a>| 
		<a href="#" onclick="showServerMenu(this);return false;">工具4</a> 		
		<a href="#" onclick="showManuals(this);return false;">工具3 <span style="font-size: 11px">▼</span></a> | 
		<a href="#" onclick="showServerMenu(this);return false;">退出</a>-->  
		</div>
		<div class=clear></div>
	</div>
</body>
</html>