<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>VODLite2.0 FTP管理</title>
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
			$("#subNav203").attr("class","actived");
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
	<form id="mainForm" action="ftp.action" method="get">
		<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
		<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
		<input type="hidden" name="page.order" id="order" value="${page.order}"/>
		<input type="hidden" name="page.pageSize" id="pageSize" value="${page.pageSize}"/>

		<div id="message"><s:actionmessage theme="custom" cssClass="success"/></div>
		<div id="filter">
			FTP编号: <input type="text" name="filter_LIKES_ftpId" value="${param['filter_LIKES_ftpId']}" size="9" tabindex="1" onkeypress="if (event.keyCode == 13) {javascript:document.forms.mainForm.submit()}"/>
			名字: <input type="text" name="filter_LIKES_name"
							 value="${param['filter_LIKES_name']}" size="9" tabindex="2" onkeypress="if (event.keyCode == 13) {javascript:document.forms.mainForm.submit()}"/>
			<input type="button" value="搜索" onclick="search();" tabindex="3"/>
			
			&nbsp;&nbsp;
			<security:authorize ifAnyGranted="ROLE_修改FTP">
				<input type="button" value="增加新FTP" onclick="linkTo('ftp!input.action')" tabindex="4"/>
			</security:authorize>
		</div>
		<div id="content">
			<table id="contentTable">
				<tr>
					<th><a href="javascript:sort('ftpId','asc')">FTP编号</a></th>
					<th><a href="javascript:sort('name','asc')">名字</a></th>
					<th><a href="javascript:sort('address','asc')">地址</a></th>
					<th><a href="javascript:sort('userName','asc')">用户名</a></th>
					<th><a href="javascript:sort('userPass','asc')">密码</a></th>
					<th><a href="javascript:sort('path','asc')">路径</a></th>
					<th><a href="javascript:sort('state','asc')">是否可用</a></th>
					<th><a href="javascript:sort('description','asc')">描述</a></th>
					<th><a href="javascript:sort('system','asc')">系统</a></th>
					<th><a href="javascript:sort('createTime','asc')">创建时间</a></th>
					<th>操作</th>
				</tr>

				<s:iterator value="page.result">
					<tr>
						<td>${ftpId}&nbsp;</td>
						<td>${name}&nbsp;</td>
						<td>${address}&nbsp;</td>
						<td>${userName}&nbsp;</td>
						<td>${userPass}&nbsp;</td>
						<td>${path}&nbsp;</td>
						<td><s:if test="%{state == true}">可用</s:if><s:else>不可用</s:else>&nbsp;</td>
						<td>${description}&nbsp;</td>
						<td>${system.label}&nbsp;</td>
						<td><fmt:formatDate value="${createTime}" type="both"/>&nbsp;</td>
						<td>&nbsp;
							<security:authorize ifAnyGranted="ROLE_浏览FTP">
								<security:authorize ifNotGranted="ROLE_修改FTP">
									<a href="ftp!input.action?id=${id}">查看</a>&nbsp;
								</security:authorize>
							</security:authorize>

							<security:authorize ifAnyGranted="ROLE_修改FTP">
								<a href="ftp!input.action?id=${id}&page.pageNo=${page.pageNo}&page.orderBy=${page.orderBy}&page.order=${page.order}&page.pageSize=${page.pageSize}">修改</a>&nbsp;
								<a href="ftp!delete.action?id=${id}" onclick="return confirm('确定删除吗？')">永久删除</a>
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
