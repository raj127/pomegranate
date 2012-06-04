<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>作业规程校验</title>
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
		return false;
	}
	
	function query(){
		
	}
	</script>
</head>

<body>
<div id="doc3">
<%@ include file="/common/header.jsp" %>
<div id="bd">
	<div id="yui-main">
	<div class="yui-b">
	<form id="mainForm" action="task-verify!search.action" method="get">
		<div id="message"><s:actionmessage theme="custom" cssClass="success"/></div>
		<div id="filter">
			关键词: <input type="text" name="keyWords" value="" size="9" tabindex="1" onkeypress="if (event.keyCode == 13) {javascript:document.forms.mainForm.submit()}"/>
			<input type="button" value="搜索" onclick="search();" tabindex="2"/>
		</div>

		<div id="content">
			<table id="contentTable">
				<tr>
					<th><a href="javascript:sort('chapterName','asc')">章节名称</a></th>
					<th><a href="javascript:sort('description','asc')">章节描述</a></th>
					<th><a href="javascript:sort('state','asc')">作者</a></th>
				</tr>

				<s:iterator value="srs">
					<tr>
						<td>${title}&nbsp;</td>
						<td>${description}&nbsp;</td>
						<td>${author}&nbsp;</td>
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
