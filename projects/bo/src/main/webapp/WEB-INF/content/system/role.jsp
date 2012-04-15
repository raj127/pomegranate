<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>角色管理</title>
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
			$("#subNav202").attr("class","actived");
			$(".secondNav div").each(function(){
				$(this).hide();
				$("#subNav2").show();
			});
		});
		
		function deleteRole(size,id){
			if(size>0){
				alert("不能删除，存在用户正在使用此角色!")
			}else{
				if(confirm('确定删除吗？')){
					window.location.href="role!delete.action?id="+id;
				}			
			}
	
		}
	</script>
</head>

<body>
<div id="doc3">
<%@ include file="/common/header.jsp" %>
<div id="bd">
	<div id="yui-main">
	<div class="yui-b">
	
	<form id="mainForm" action="role.action" method="get">
		<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
		<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
		<input type="hidden" name="page.pageSize" id="pageSize" value="${page.pageSize}"/>
		<input type="hidden" name="page.order" id="order" value="${page.order}"/>
	
		<div id="message"><s:actionmessage theme="custom" cssClass="success"/></div>
		<div id="content">
			<div>
				<security:authorize ifAnyGranted="ROLE_角色修改">
					<input type="button" value="增加新角色" onclick="linkTo('role!input.action')" tabindex="1"/>
				</security:authorize>
			</div>
			<br/>
			<table id="contentTable">
				<tr>
					<th>名称</th>
					<th>授权</th>
					<th>操作</th>
				</tr>
	
				<s:iterator status="status" value="page.result">
					<tr>
						<td>${name}</td>
						<td>
							<s:if test="%{authNames != null && authNames.length()>100}">
	                      			<s:property value='%{authNames.substring(0, 99)+"......"}' />
				           	</s:if>
				           	<s:else>
				            	<s:property value="%{authNames}" default="-" />
				          	</s:else>
			          	</td>
						<td>&nbsp;
							<security:authorize ifAnyGranted="ROLE_角色浏览">
									<a href="role!input.action?id=${id}&viewOnly=true">查看</a>&nbsp;
							</security:authorize>
	
							<security:authorize ifAnyGranted="ROLE_角色修改">
								<a href="role!input.action?id=${id}" id="editLink-${name}">修改</a>&nbsp;
								<a href="#" id="deleteLink-${name}" onclick="deleteRole(${fn:length(userList)},${id})">删除</a>
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