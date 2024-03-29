<%--
Description:系统管理--》公司管理--》官司查看及编辑页面
author:darkmi
date:2012/08/23
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>公司信息管理</title>
	<%@ include file="/common/meta.jsp" %>
	<link href="${ctx}/css/yui.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx}/css/style.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet"/>
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
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
	<script>
		//当处于查看页时,除了button之外全部为disabled='true'
		$(document).ready(function() {
			if(${viewOnly}){
				//设置所有除了type='button'的input标签的disabled属性
				$("input[type!='button']").each(function(){
					this.disabled = 'true';
				});
				//设置所有select标签的disabled属性
				$("select").each(function(){
					this.disabled = 'true';
				});
			}
		});
	</script>
	<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#companyName").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate({
				rules: {
					companyName: {
						required: true,
						remote: "company!checkCompanyName.action?oldCompanyName=" + encodeURIComponent('${companyName}')
					},
					email:"email"
				},
				messages: {
					companyName: {
						required:"公司名称不能为空",
						remote: "公司名称已存在"
					},
					email:"请输入正确的EMAIL"
				}
			});
		});
	</script>
</head>

<body>
<div id="doc3">
<%@ include file="/common/header.jsp" %>
<div id="bd1">
	<div id="yui-main">
	<div class="yui-b">
	<h2><s:if test="id == null">创建</s:if><s:else><s:if test="viewOnly">查看</s:if><s:else>修改</s:else></s:else>公司</h2>
	<form id="inputForm" action="company!save.action" method="post">
		<input type="hidden" name="id" value="${id}"/>
		<input type="hidden" name="workingVersion" value="${version}"/>
		<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
		<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
		<input type="hidden" name="page.order" id="order" value="${page.order}"/>
		<input type="hidden" name="page.pageSize" id="pageSize" value="${page.pageSize}"/>
		<table class="noborder">
			<tr>
				<td width="10%">公司名称:</td>
				<td><input type="text" name="companyName" size="40" id="companyName" value="${companyName}" maxlength="50"/></td>
			</tr>
			<tr>
				<td>电话:</td>
				<td><input type="text" id="phoneNumber" name="phoneNumber" size="40" value="${phoneNumber}" maxlength="50" /></td>
			</tr>
			<tr>
				<td>邮箱:</td>
				<td><input type="text" id="email" name="email" size="40" value="${email}" maxlength="100"/></td>
			</tr>
			<tr>
					<td>创建:</td>
					<td>${createBy} <fmt:formatDate value="${createTime}" type="both"/></td>
				</tr>
				<tr>
					<td>最后修改:</td>
					<td>${lastModifyBy} <fmt:formatDate value="${lastModifyTime}" type="both"/></td>
				</tr>
			<tr>
				<td colspan="2">
					<security:authorize ifAnyGranted="ROLE_用户修改">
						<c:if test='${viewOnly!=true}'><input class="button" type="submit" value="提交"/>&nbsp;</c:if>
					</security:authorize>
					<input class="button" type="button" value="返回" onclick="history.back()"/>
				</td>
			</tr>
		</table>
	</form>
	</div>
	</div>
</div>
<%@ include file="/common/footer.jsp" %>
</div>
</body>
</html>
