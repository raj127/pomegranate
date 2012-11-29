<%--
Description:模板管理 --》 模板查看和修改
author:darkmi
date:2012/08/23
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>模板管理</title>
	<%@ include file="/common/meta.jsp" %>
	<link href="${ctx}/css/yui.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx}/css/style.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet"/>
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
	
	<script>
		//菜单着色功能
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
				
				$("#description").attr("disabled", "disabled");
			}
		});
	</script>
	
	<script>
		//表单校验功能
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#templateName").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate({
				rules: {
					templateName:"required"
					/*path: {
						required: true,
						remote: "template!checkPath.action?oldPath=" + encodeURIComponent('${path}')
					}*/
				},
				messages: {
					templateName:"不能为空",
					/*path: {
						required:"不能为空",
						remote: "目录已存在"
					},*/
					state:"不能为空"
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
	<h2><s:if test="id == null">创建</s:if><s:else><s:if test="viewOnly">查看</s:if><s:else>修改</s:else></s:else>模板</h2>
	<form id="inputForm" action="template!save.action" method="post">
		<input type="hidden" name="id" value="${id}"/>
		<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
		<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
		<input type="hidden" name="page.order" id="order" value="${page.order}"/>
		<input type="hidden" name="page.pageSize" id="pageSize" value="${page.pageSize}"/>
		<table class="noborder">
			<tr>
				<td width="10%">模板名称:</td>
				<td>
					<input type="text" id="templateName" name="templateName" value="${templateName}" size="98" maxlength="98" class="required"/>
				</td>
			</tr>
			<%--
			<tr>
				<td>保存路径:</td>
				<td>
					<input type="text" id="path" name="path" value="${path}" size="40" maxlength="50"/>
				</td>
			</tr>
			--%>			
			<tr>
				<td>模板描述:</td>
				<td>
				<textarea id="description" name="description" rows="5" cols="100">${description}</textarea>
				<%--<input type="text" id="description" name="description" value="${description}" size="40" maxlength="255"/> --%>
				</td>
			</tr>
			</tr>
			<tr>
				<td>状态:</td>
				<td>
					<s:select id="state" 
					          name="state"  
					          list='#{"DELETE":"不可用","NORMAL":"可用"}'   value="%{state}" required="true" headerKey="" headerValue="请选择" theme="simple"/>
				</td>
			</tr>			
			<tr>
					<td>创建时间:</td>
					<td>${createBy} <fmt:formatDate value="${createTime}" type="both"/></td>
				</tr>
				<tr>
					<td>修改时间:</td>
					<td>${lastModifyBy} <fmt:formatDate value="${lastModifyTime}" type="both"/></td>
				</tr>
			<tr>
				<td colspan="2">
					<security:authorize ifAnyGranted="ROLE_模板修改">
						<input class="button" type="submit" value="提交"/>&nbsp;
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
