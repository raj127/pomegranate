<%--
Description:模板管理 --》 模板章节表格展示
author:darkmi
date:2012/08/21
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>作业规程编制</title>
	<%@ include file="/common/meta.jsp" %>
	<link href="${ctx}/css/yui.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx}/css/style.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx}/css/jquery.treeTable.css" rel="stylesheet" type="text/css" />
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/treetable/jquery.ui.js" type="text/javascript"></script>
	<script src="${ctx}/js/treetable/jquery.treeTable.js" type="text/javascript"></script>
	<script src="${ctx}/js/table.js" type="text/javascript"></script>
	  	
	<script type="text/javascript">
		$(document).ready(function() {
			$(".mainNav a").attr("class","");
			$("#n3").attr("class","actived");
			$("#subNav301").attr("class","actived");
			$(".secondNav div").each(function(){
				$(this).hide();
				$("#subNav3").show();
			});
			
			//
			$("#taskTree").treeTable({expandable: true});
			$("#taskTree tr").hover(function(){
				jQuery(this).addClass("color3")},
				function(){
				jQuery(this).removeClass("color3")
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
	<form id="mainForm" action="template-chapter-tree.action" method="get">
	<input type="hidden" name="templateId" id="templateId" value="${templateId}"/>
		<div id="message"><s:actionmessage theme="custom" cssClass="success"/></div>
		<div id="filter">
			<%--<input type="button" value="添加模板" onclick="linkTo('template!input.action')" tabindex="3"/>--%>
			<input type="button" value="树状查看" onclick="linkTo('template-chapter-tree.action?templateId=${templateId}&parentId=0')" tabindex="1"/>
		</div>		
		<div id="content">
		${taskTree}
		</div>
	</form>
	</div>
	</div>
</div>
<%@ include file="/common/footer.jsp" %>
</div>
</body>
</html>