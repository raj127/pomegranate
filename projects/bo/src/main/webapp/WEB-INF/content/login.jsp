<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter" %>
<%@ page import="org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter" %>
<%@ page import="org.springframework.security.web.WebAttributes" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>作业规程编制与管理系统 - 登录页</title>
	<%@ include file="/common/meta.jsp" %>
	<link href="${ctx}/css/yui.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx}/css/style.css" type="text/css" rel="stylesheet"/>
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
	<script>
		$(document).ready(function() {
			$("#loginForm").validate();
		});
		function refreshCaptcha() {
			$('#captchaImg').hide().attr('src','${ctx}/security/jcaptcha.jpg?' + Math.floor(Math.random()*100)).fadeIn();
		}
	</script>
</head>
<body>
<div id="doc3">
<%@ include file="/common/header-index.jsp" %>
<div id="bd">
	<div id="yui-main">
	<div class="yui-b">
	<%if ("1".equals(request.getParameter("error"))) {%>
			<div class="error"> 用户名密码错误,请重试.</div>
		<%
			}
			if ("2".equals(request.getParameter("error"))) {
		%>
			<div class="error"> 验证码错误,请重试.</div>
		<%
			}
			if ("3".equals(request.getParameter("error"))) {
		%>
			<div class="error"> 此帐号已从别处登录.</div>
		<%}%>
	<form id="loginForm" action="${ctx}/j_spring_security_check" method="post" style="margin-top:1em">
		<table class="noborder" align="center">
				<tr>
					<td style="text-align:right">用户名:</td>
					<td>
						<input type='text' name='j_username' size='10'
						<s:if test="not empty param.error">
							value='<%=session.getAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_LAST_USERNAME_KEY)%>'</s:if> />
					</td>
					<!--  <td rowspan="3"><img id="captchaImg" src="${ctx}/security/jcaptcha.jpg"/></td>-->

				</tr>
				<tr>
					<td style="text-align:right">密码:</td>
					<td><input type='password' size='10' name='j_password'/></td>
				</tr>
				<!--<tr>
					<td>验证码:</td>
					<td><input type='text' name='j_captcha' size='5'/></td>
				</tr>
				-->
				
				<tr>
					<td>&nbsp;</td>
					<td style="text-align:left"><input type="checkbox" name="_spring_security_remember_me"/>
						两周内记住我<!--<span style="margin-left:25px"><a href="javascript:refreshCaptcha()">看不清楚换一张</a></span>-->
					</td>
					
				</tr>
				
				<tr>
					<td>&nbsp;</td>
					<td style="text-align:left"><input value="登录" type="submit"/></td>
					
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

