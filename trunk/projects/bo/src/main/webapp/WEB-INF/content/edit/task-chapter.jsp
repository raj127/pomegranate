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
	<link href="${ctx}/css/pagination.css" type="text/css" rel="stylesheet"/>
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/table.js" type="text/javascript"></script>
	<script src="${ctx}/js/jquery.pagination.js" type="text/javascript"></script>
	<script type="text/javascript">
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
	<script type="text/javascript">
	function showWin(){
		var height = screen.availHeight;
		var width  = screen.availWidth;
		//var top    = (screen.availHeight - height)/2;
		//var left   = (screen.availWidth - width)/2;
		var param  = 'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=auto,resizable=yes,' + 
		             'width=' + width + ',height=' + height + ',left=0,top=0';
		window.open('task-chapter!edit.action?id=${id}', 'win_name', param);
	}
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

		<div id="content">
			<table id="contentTable">
				<tr>
					<th><a href="javascript:sort('chapterName','asc')">章节名称</a></th>
					<th><a href="javascript:sort('description','asc')">章节描述</a></th>
					<th><a href="javascript:sort('state','asc')">章节状态</a></th>
					<th>操作</th>
				</tr>

				<s:iterator value="page.result">
					<tr>
						<td>${chapterName}&nbsp;</td>
						<td>${description}&nbsp;</td>
						<td>${state}&nbsp;</td>
						<td>&nbsp;
							<security:authorize ifAnyGranted="ROLE_作业规程编制">
								<a href="#" onclick="showWin();" target="_blank">编辑</a>&nbsp;
							</security:authorize>
							<a href="task-chapter!testUpload.action">上传测试</a>&nbsp;
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
