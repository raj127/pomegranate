<%--
Description:规程编制 --》 主页面
author:darkmi
date:2012/08/21
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>任务管理</title>
	<%@ include file="/common/meta.jsp" %>
	<link href="${ctx}/css/yui.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx}/css/style.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx}/css/pagination.css" type="text/css" rel="stylesheet"/>
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/table.js" type="text/javascript"></script>
	<script src="${ctx}/js/jquery.pagination.js" type="text/javascript"></script>
	<script>
		$(document).ready(function() {
			$(".mainNav a").attr("class","");
			$("#n5").attr("class","actived");
			$("#subNav501").attr("class","actived");
			$(".secondNav div").each(function(){
				$(this).hide();
				$("#subNav5").show();
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
	<form id="mainForm" action="task-chapter-edit-index.action" method="get">
		<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
		<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
		<input type="hidden" name="page.order" id="order" value="${page.order}"/>
		<input type="hidden" name="page.pageSize" id="pageSize" value="${page.pageSize}"/>

		<div id="message"><s:actionmessage theme="custom" cssClass="success"/></div>
		<div id="filter">
			任务名称: <input type="text" name="filter_LIKES_taskName" value="${param['filter_LIKES_taskName']}" size="9" tabindex="1" onkeypress="if (event.keyCode == 13) {javascript:document.forms.mainForm.submit()}"/>
			<input type="button" value="搜索" onclick="search();" tabindex="1"/>
			&nbsp;&nbsp;
			<%--
			<security:authorize ifAnyGranted="ROLE_任务修改">
				<input type="button" value="新建任务" onclick="linkTo('task-manager!input.action')" tabindex="2"/>
			</security:authorize>
			--%>
			
		</div>
		<div id="content">
			<table id="contentTable">
				<tr>
					<th><a href="javascript:sort('fullName','asc')">任务名称</a></th>
					<th><a href="javascript:sort('gender','asc')">任务描述</a></th>
					<th><a href="javascript:sort('birthyear','asc')">任务状态</a></th>
					<th>操作</th>
				</tr>

				<s:iterator value="page.result">
					<tr>
						<td>${taskName}&nbsp;</td>
						<td>${description}&nbsp;</td>
						<td>${state}&nbsp;</td>
						<td>&nbsp;
							<security:authorize ifAnyGranted="ROLE_任务浏览">
							<a href="task-manager!input.action?id=${id}&viewOnly=true">查看</a>&nbsp;
							</security:authorize>
							<security:authorize ifAnyGranted="ROLE_任务修改">
							<a href="task-manager!input.action?id=${id}&update=true&page.pageNo=${page.pageNo}&page.orderBy=${page.orderBy}&page.order=${page.order}&page.pageSize=${page.pageSize}">修改</a>&nbsp;
							</security:authorize>
							<security:authorize ifAnyGranted="ROLE_任务浏览">
							<a href="task-chapter-tabletree.action?taskId=${id}">查看目录</a>&nbsp;
							</security:authorize>
							<security:authorize ifAnyGranted="ROLE_任务修改">
							<a href="#" onclick="if(confirm('确定要永久删除么?')){linkTo('task-manager!delete.action?id=${id}');}">永久删除</a>
							</security:authorize>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>

		<div class="pagination">
		    <%@ include file="/common/page.jsp" %>
		</div>
	</form>
	</div>
	</div>
</div>
<%@ include file="/common/footer.jsp" %>
</div>
</body>
</html>
