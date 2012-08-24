<%--
Description:模板管理 --》 模板章节表格展示
author:darkmi
date:2012/08/23
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>模板章节目录</title>
	<%@ include file="/common/meta.jsp" %>
	<link href="${ctx}/css/yui.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx}/css/style.css" type="text/css" rel="stylesheet"/>
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/table.js" type="text/javascript"></script>
	
	<script>
		$(document).ready(function() {
			$(".mainNav a").attr("class","");
			$("#n3").attr("class","actived");
			$("#subNav302").attr("class","actived");
			$(".secondNav div").each(function(){
				$(this).hide();
				$("#subNav3").show();
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
	<form id="mainForm" action="tast-chapter.action" method="get">		
		<div id="message"><s:actionmessage theme="custom" cssClass="success"/></div>
		<div id="filter">
			目录名称: <input type="text" 
			                 name="filter_LIKES_chapterName" 
			                 value="${param['filter_LIKES_chapterName']}" 
			                 size="9" tabindex="1" 
			                 onkeypress="if (event.keyCode == 13) {javascript:document.forms.mainForm.submit()}"/>
			<input type="button" value="搜索" onclick="search();" tabindex="5"/>
			&nbsp;&nbsp;
			
			<security:authorize ifAnyGranted="ROLE_用户修改">
				<input type="button" 
				       value="增加一级目录" 
				       onclick="linkTo('template-chapter!input.action?templateId=${templateId}&parentId=0')" tabindex="6"/>
			</security:authorize>
				<input type="button" 
				       value="树状结构" 
				       onclick="linkTo('template-chapter-tree.action?templateId=${templateId}&parentId=0')" tabindex="6"/>
			
		</div>		
		<div id="content">
		
			<table id="contentTable">
				<tr>
					<th>章节名称</th>
					<th>文件名称</th>
					<th>显示顺序</th>
					<th>章节描述</th>
					<th>章节状态</th>
					<th>操作</th>
				</tr>

				<s:iterator value="tcs">
					<tr>
						<td><s:if test="parentId!=0">&nbsp;&nbsp;&nbsp;&nbsp;</s:if> ${chapterName}&nbsp;</td>
						<td><a href="template-chapter!edit.action?id=${id}" target="_blank">${fileName}</a>&nbsp;</td>
						<td>${displayOrder}&nbsp;</td>
						<td>${description}&nbsp;</td>
						<td>${state}&nbsp;</td>
						<td>&nbsp;
							<security:authorize ifAnyGranted="ROLE_模板修改">
								<a href="template-chapter!input.action?id=${id}&viewOnly=true">查看</a>&nbsp;
								<a href="template-chapter!input.action?id=${id}&templateId=${templateId}">编辑</a>&nbsp;
								<a href="template-chapter!input.action?parentId=${id}&templateId=${templateId}" <s:if test="parentId!=0">disabled</s:if>>添加子目录</a>&nbsp;
								<a href="template-chapter!delete.action?id=${id}&templateId=${templateId}" onclick="return confirm('确定删除吗？')">删除</a>&nbsp;
							</security:authorize>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>

	</form>
	</div>
	</div>
</div>
<%@ include file="/common/footer.jsp" %>
</div>
</body>
</html>
