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
		var height = screen.height;
		var width  = screen.width;
		//var top    = (screen.availHeight - height)/2;
		//var left   = (screen.availWidth - width)/2;
		var param  = 'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=auto,resizable=yes,fullscreen=yes' + 
		             'width=' + width + ',height=' + height + ',left=0,top=0';
		window.open('task-chapter!edit.action?id=${id}', 'win_name', param);
		return false;
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
		<div id="message"><s:actionmessage theme="custom" cssClass="success"/></div>

		<div id="content">
			<table id="contentTable">
				<tr>
					<th>章节名称</th>
					<th>文件名称</th>
					<th>章节描述</th>
					<th>章节状态</th>
					<th>审批状态</th>
					<th>章节类型</th>
					<th>操作</th>
				</tr>

				<s:iterator value="tcs">
					<tr>
						<td><s:if test="parentId!=0">&nbsp;&nbsp;&nbsp;&nbsp;</s:if>${chapterName}&nbsp;</td>
						<td>${fileName}&nbsp;</td>
						<td>${description}&nbsp;</td>
						<td>${state}&nbsp;</td>
						<td>未审批&nbsp;</td>
						<td>模板创建&nbsp;</td>
						<td>&nbsp;
							<security:authorize ifAnyGranted="ROLE_作业规程编制">
								<s:if test='fileName!=""'>
								<a href="task-verify!main.action?id=${id}" target="_blank">编辑</a>&nbsp;
								<a href="task-verify!main.action?id=${id}" target="_blank" <s:if test='fileName==""'>disabled</s:if>>提交审核</a>&nbsp;
								</s:if>
							</security:authorize>
							<%--<a href="task-chapter!testUpload.action">上传测试</a>&nbsp;--%>
							<%--<a href="task-verify!main.action?id=${id}" target="_blank">规程校验</a>&nbsp;--%>
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
