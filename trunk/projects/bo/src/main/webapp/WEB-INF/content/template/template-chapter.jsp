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
	<!--<link href="${ctx}/css/pagination.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx}/css/jquery.treeTable.css" type="text/css" rel="stylesheet"/>-->
	
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/table.js" type="text/javascript"></script>
	<!--  <script src="${ctx}/js/jquery.pagination.js" type="text/javascript"></script>
	<script src="${ctx}/js/jquery.treeTable.js" type="text/javascript"></script>-->
	
	<script>
		$(document).ready(function() {
			$(".mainNav a").attr("class","");
			$("#n3").attr("class","actived");
			$("#subNav301").attr("class","actived");
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
		<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
		<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
		<input type="hidden" name="page.order" id="order" value="${page.order}"/>
		<input type="hidden" name="page.pageSize" id="pageSize" value="${page.pageSize}"/>

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
				<input type="button" value="增加新目录" onclick="linkTo('template-chapter!input.action')" tabindex="6"/>
			</security:authorize>
		</div>		

		<div id="content">
		
			<table id="contentTable">
				<tr>
					<th><a href="javascript:sort('chapterName','asc')">章节名称</a></th>
					<th><a href="javascript:sort('fileName','asc')">文件名称</a></th>
					<th><a href="javascript:sort('displayOrder','asc')">显示顺序</a></th>
					<th><a href="javascript:sort('description','asc')">章节描述</a></th>
					<th><a href="javascript:sort('state','asc')">章节状态</a></th>
					<th>操作</th>
				</tr>

				<s:iterator value="page.result">
					<tr>
						<td><s:if test="parentId!=0">&nbsp;&nbsp;&nbsp;&nbsp;</s:if> ${chapterName}&nbsp;</td>
						<td><a href="template-chapter!edit.action?fileName=${fileName}" target="_blank">${fileName}</a>&nbsp;</td>
						<td>${displayOrder}&nbsp;</td>
						<td>${description}&nbsp;</td>
						<td>${state}&nbsp;</td>
						<td>&nbsp;
							<security:authorize ifAnyGranted="ROLE_模板修改">
								<a href="template-chapter!input.action?id=${id}">编辑目录</a>&nbsp;
								<a href="template-chapter!input.action?parentId=${id}">添加子目录</a>&nbsp;
								<a href="template-chapter!edit.action?id=${id}" target="_blank">编辑模板</a>&nbsp;
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
