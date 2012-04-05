<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>VODLite2.0  日志管理</title>
	<%@ include file="/common/meta.jsp" %>
	<link href="${ctx}/css/yui.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx}/css/style.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx}/css/pagination.css" type="text/css" rel="stylesheet"/>
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/table.js" type="text/javascript"></script>
	<script src="${ctx}/js/jquery.pagination.js" type="text/javascript"></script>
	<script language="javascript" type="text/javascript" src="${ctx}/js/my97/WdatePicker.js"></script>
	<script>
		$(document).ready(function() {
			$(".mainNav a").attr("class","");
			$("#n2").attr("class","actived");
			$("#subNav205").attr("class","actived");
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
	<form id="mainForm" action="log.action" method="get">
		<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
		<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
		<input type="hidden" name="page.order" id="order" value="${page.order}"/>
		<input type="hidden" name="page.pageSize" id="pageSize" value="${page.pageSize}"/>

		<div id="message"><s:actionmessage theme="custom" cssClass="success"/></div>
		<div id="filter">
			描述: <input type="text" name="filter_LIKES_message" value="${param['filter_LIKES_message']}" size="9" tabindex="1" onkeypress="if (event.keyCode == 13) {javascript:document.forms.mainForm.submit()}"/>
			 记录时间:<input type="text" name="filter_GED_logTime" id="filter_GED_logTime" value="${param['filter_GED_logTime']}" tabindex="2" size="18" onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" onkeypress="if (event.keyCode == 13) {javascript:document.forms.mainForm.submit()}"/>-<input type="text" name="filter_LED_logTime" id="filter_LED_logTime" value="${param['filter_LED_logTime']}" size="18" onFocus="WdatePicker({startDate:'%y-%M-01 59:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" onkeypress="if (event.keyCode == 13) {javascript:document.forms.mainForm.submit()}"/>
			<input type="button" value="搜索" onclick="search();" tabindex="4"/>
			
			&nbsp;&nbsp;
			<security:authorize ifAnyGranted="ROLE_删除日志">
						<input type="button" value="删除所有日志" onclick="javascript:if(confirm('确定要删除所有日志么？')){linkTo('log!deleteAll.action');}" tabindex="4"/>
			</security:authorize>
		</div>
		<div id="content">
			<table id="contentTable">
				<tr>
					<th><a href="javascript:sort('message','asc')">描述</a></th>
					<th><a href="javascript:sort('logTime','asc')">记录时间</a></th>
					<th>操作</th>
				</tr>

				<s:iterator value="page.result">
					<tr>
						<td>${message}&nbsp;</td>
						<td><fmt:formatDate value="${logTime}" type="both"/>&nbsp;</td>
						<td>&nbsp;
							<security:authorize ifAnyGranted="ROLE_删除日志">
								<a href="log!delete.action?id=${id}" onclick="return confirm('确定删除吗？')">删除</a>
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
