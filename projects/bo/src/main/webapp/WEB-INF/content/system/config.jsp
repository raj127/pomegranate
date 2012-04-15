<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>配置信息管理</title>
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
			$("#subNav204").attr("class","actived");
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
	<form id="mainForm" action="config.action" method="get">
		<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
		<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
		<input type="hidden" name="page.order" id="order" value="${page.order}"/>
		<input type="hidden" name="page.pageSize" id="pageSize" value="${page.pageSize}"/>

		<div id="message"><s:actionmessage theme="custom" cssClass="success"/></div>
		<div id="filter">
			名字: <input type="text" name="filter_LIKES_name" value="${param['filter_LIKES_name']}" size="9" tabindex="1" onkeypress="if (event.keyCode == 13) {javascript:document.forms.mainForm.submit()}"/>
			值: <input type="text" name="filter_LIKES_value"
							 value="${param['filter_LIKES_value']}" size="9" tabindex="2" onkeypress="if (event.keyCode == 13) {javascript:document.forms.mainForm.submit()}"/>
			<input type="button" value="搜索" onclick="search();" tabindex="3"/>
			
			&nbsp;&nbsp;
			<security:authorize ifAnyGranted="ROLE_配置修改">
				<input type="button" value="增加新配置" onclick="linkTo('config!input.action')" tabindex="4"/>
			</security:authorize>
		</div>
		<div id="content">
			<table id="contentTable">
				<tr>
					<th><a href="javascript:sort('name','asc')">名字</a></th>
					<th><a href="javascript:sort('value','asc')">值</a></th>
					<th><a href="javascript:sort('description','asc')">描述</a></th>
					<th><a href="javascript:sort('system','asc')">系统</a></th>
					<th><a href="javascript:sort('createTime','asc')">创建时间</a></th>
					<th>操作</th>
				</tr>

				<s:iterator value="page.result">
					<tr>
						<td>${name}&nbsp;</td>
						<td>${value}&nbsp;</td>
						<td>${description}&nbsp;</td>
						<td>${system.label}&nbsp;</td>
						<td><fmt:formatDate value="${createTime}" type="both"/>&nbsp;</td>
						<td>&nbsp;
							<security:authorize ifAnyGranted="ROLE_配置浏览">
								<security:authorize ifNotGranted="ROLE_配置修改">
									<a href="config!input.action?id=${id}">查看</a>&nbsp;
								</security:authorize>
							</security:authorize>

							<security:authorize ifAnyGranted="ROLE_配置修改">
								<a href="config!input.action?id=${id}&page.pageNo=${page.pageNo}&page.orderBy=${page.orderBy}&page.order=${page.order}&page.pageSize=${page.pageSize}">修改</a>&nbsp;
								<a href="config!delete.action?id=${id}" onclick="return confirm('确定删除吗？')">永久删除</a>
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
