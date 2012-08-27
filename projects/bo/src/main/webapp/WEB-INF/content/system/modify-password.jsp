<%--
Description:系统管理--》修改密码
author:darkmi
date:2012/08/23
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>VODLite2.0 修改个人密码</title>
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
			$(".secondNav div").each(function(){
				$(this).hide();
				$("#subNav2").show();
			});
		});
	</script>
	<script>
		$(document).ready(function(){
			//聚焦第一个输入框
			$("#oldPassword").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate({
				rules: { 
				    oldPassword: { 
        				required: true, 
        				remote: "modify-password!checkPass.action"
    				},
          			password: {
    					required: true,
    					minlength:3
    				}, 
    				passwordConfirm: {
    					equalTo:"#password"
    				}
				},
				messages: {
					oldPassword: {
					    required:"不能为空",
						remote: "原密码不正确"
					},
					password: {
    					required: "不能为空",
    					minlength:"最小长度为3"
    				}, 
					passwordConfirm: {
						equalTo: "输入与上面相同的密码"
					}
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
	<h2>修改个人密码</h2>
	<div id="message"><s:actionmessage theme="custom" cssClass="success"/></div>
	<form id="inputForm" action="modify-password!save.action" method="post">
		<input type="hidden" name="id" value="${id}" />
		<table class="noborder">
			<tr>
				<td>原密码:</td>
				<td><input type="password" id="oldPassword" name="oldPassword" size="40" maxlength="200"/></td>
			</tr>
			<tr>
				<td>密码:</td>
				<td><input type="password" id="password" name="password" size="40"  onbeforepaste= "clipboardData.setData( 'text ',clipboardData.getData( 'text ').replace(/[^\d]/g, ' ')) "   autocomplete=off   ondragenter= "javascript:return   false; "   onpaste= "return   false " maxlength="200"/></td>
			</tr>
			<tr>
				<td>确认密码:</td>
				<td><input type="password" id="passwordConfirm" name="passwordConfirm" size="40" onbeforepaste= "clipboardData.setData( 'text ',clipboardData.getData( 'text ').replace(/[^\d]/g, ' ')) "   autocomplete=off   ondragenter= "javascript:return   false; "   onpaste= "return   false " maxlength="200"/></td>
			</tr>
			<tr>
				<td colspan="2">
				<security:authorize ifAnyGranted="ROLE_修改密码">
						<input type="submit" value="提交" />&nbsp; 
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
