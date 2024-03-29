<%--
Description:系统管理--》公司管理主页面
author:darkmi
date:2012/08/23
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>公司信息管理</title>
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
			$("#n2").attr("class","actived");
			$("#subNav201").attr("class","actived");
			$(".secondNav div").each(function(){
				$(this).hide();
				$("#subNav2").show();
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
	<form id="mainForm" action="company.action" method="get">
		<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
		<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
		<input type="hidden" name="page.order" id="order" value="${page.order}"/>
		<input type="hidden" name="page.pageSize" id="pageSize" value="${page.pageSize}"/>

		<div id="message"><s:actionmessage theme="custom" cssClass="success"/></div>
		<div id="filter">
			公司名称: <input type="text" name="filter_LIKES_companyName" value="${param['filter_LIKES_companyName']}" size="9" tabindex="1" onkeypress="if (event.keyCode == 13) {javascript:document.forms.mainForm.submit()}"/>
			Email: <input type="text" name="filter_LIKES_email"
							 value="${param['filter_LIKES_email']}" size="9" tabindex="3" onkeypress="if (event.keyCode == 13) {javascript:document.forms.mainForm.submit()}"/>
			电话: <input type="text" name="filter_LIKES_phoneNumber"
							 value="${param['filter_LIKES_phoneNumber']}" size="9" tabindex="4" onkeypress="if (event.keyCode == 13) {javascript:document.forms.mainForm.submit()}"/>
			<input type="button" value="搜索" onclick="search();" tabindex="5"/>
			&nbsp;&nbsp;
			
			<security:authorize ifAnyGranted="ROLE_用户修改">
						<input type="button" value="增加新公司" onclick="linkTo('company!input.action')" tabindex="6"/>
			</security:authorize>
		</div>
		<div id="content">
			<table id="contentTable">
				<tr>
					<th width="5%"><a href="javascript:sort('loginName','asc')">公司名称</a></th>
					<th width="10%"><a href="javascript:sort('phoneNumber','asc')">电话</a></th>
					<th width="10%"><a href="javascript:sort('email','asc')">电邮</a></th>
					<th width="15%">操作</th>
				</tr>

				<s:iterator value="page.result">
					<tr>
						<td>${companyName}&nbsp;</td>
						<td>${phoneNumber}&nbsp;</td>
						<td>${email}&nbsp;</td>
						<td>&nbsp;
							<security:authorize ifAnyGranted="ROLE_用户浏览">
									<a href="company!input.action?id=${id}&viewOnly=true">查看</a>&nbsp;
							</security:authorize>

							<security:authorize ifAnyGranted="ROLE_用户修改">
								<a href="company!input.action?id=${id}&page.pageNo=${page.pageNo}&page.orderBy=${page.orderBy}&page.order=${page.order}&page.pageSize=${page.pageSize}">修改</a>&nbsp;
								<a href="company!delete.action?id=${id}" onclick="return confirm('确定删除吗？')">永久删除</a>
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
