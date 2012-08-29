<%--
Description:系统管理--》规范管理--》索引管理
author:darkmi
date:2012/08/23
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>索引维护</title>
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
			$("#subNav208").attr("class","actived");
			$(".secondNav div").each(function(){
				$(this).hide();
				$("#subNav2").show();
			});
		});
		
		function deleteUser(loginName,id){
			if (loginName=='<%=SpringSecurityUtils.getCurrentUserName()%>') {
				alert('不能删除当前登录用户!');
			}else{
				if(confirm('确定删除吗？')){
					window.location.href="user!delete.action?id="+id;
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
	<form id="mainForm" action="specification-chapter-index.action" method="get">
		<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
		<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
		<input type="hidden" name="page.order" id="order" value="${page.order}"/>
		<input type="hidden" name="page.pageSize" id="pageSize" value="${page.pageSize}"/>

		<div id="message"><s:actionmessage theme="custom" cssClass="success"/></div>
		<div id="filter">
			关键词: <input type="text" name="keyWords" value="${keyWords}" size="9" tabindex="1" onkeypress="if (event.keyCode == 13) {javascript:document.forms.mainForm.submit()}"/>
			<input type="button" value="搜索" onclick="search();" tabindex="5"/>
			&nbsp;&nbsp;
			<security:authorize ifAnyGranted="ROLE_用户修改">
			<input type="button" value="创建索引" onclick="linkTo('specification-chapter-index!createIndex.action')" tabindex="6"/>
			</security:authorize>			
			&nbsp;&nbsp;
			<security:authorize ifAnyGranted="ROLE_用户修改">
			<input type="button" value="更新索引" onclick="linkTo('specification-chapter-index!updateIndex.action')" tabindex="6"/>
			</security:authorize>
			&nbsp;&nbsp;
			<security:authorize ifAnyGranted="ROLE_用户修改">
			<input type="button" value="删除所有索引" onclick="linkTo('specification-chapter-index!deleteAllIndex.action')" tabindex="6"/>
			</security:authorize>

		</div>
		<div id="content">
			<table id="contentTable">
				<tr>
					<th width="5%">id</th>
					<th width="10%">specificationName</th>
					<th width="30%">chapterName</th>
					<th>conent</th>
					<th width="10%">操作</th>
				</tr>

				<s:iterator value="page.result">
					<tr>
						<td>${id}&nbsp;</td>
						<td>${specificationName}&nbsp;</td>
						<td>${chapterName}&nbsp;</td>
						<td>
							${content}
							<%-- 
							<s:if test="%{content != null && content.length()>70}">
	                      			<s:property value='%{content.substring(0, 59)+"......"}' />
				           	</s:if>
				           	<s:else>
				            	<s:property value="%{content}" default="-" />
				          	</s:else>
				          	--%>
			          	</td>
						<td>&nbsp;
							<security:authorize ifAnyGranted="ROLE_用户浏览">
									<a href="user!input.action?id=${id}&viewOnly=true">查看</a>&nbsp;
							</security:authorize>

							<security:authorize ifAnyGranted="ROLE_用户修改">
								<a href="user!input.action?id=${id}&page.pageNo=${page.pageNo}&page.orderBy=${page.orderBy}&page.order=${page.order}&page.pageSize=${page.pageSize}">修改</a>&nbsp;
								<a href="#" onclick="deleteUser('${loginName}',${id})">删除</a>
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
