<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>规范章节查看</title>
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
			$("#subNav203").attr("class","actived");
			$(".secondNav div").each(function(){
				$(this).hide();
				$("#subNav2").show();
			});
		});
	</script>

	<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#name").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate({
				
				rules: {
					name: {
						required:true,
						remote:"role!checkRoleName.action?oldName=" + encodeURIComponent('${name}')
					},
					checkedAuthIds:"required"
				},
				messages: {
					name:{
						required:"名字不能为空",
						remote:"名字不能重复"
					},
					checkedAuthIds: "授权不能为空"
				}
			});
		});
	</script>
	<script language="javascript">
		//全选或清空授权
		function setAuth(title){
			if(title == '全选授权') {
				$("input[type='checkbox']").each(function(){
					this.checked = true;
				});
				$("input[id='selectAll']").attr("value","清空授权");
			}else{
				$("input[type='checkbox']").each(function(){
					this.checked = false;
				});
				$("input[id='selectAll']").attr("value","全选授权")
			}
		}
	</script>
</head>

<body>
<form action="specifaction-chapter!save.action" method="post" id="inputForm">
	<input type="hidden" name="id" value="${id}"/>
	
	<table class="noborder">
		<tr>
			<td>章节名称:</td>
			<td><input type="text" id="name" name="name" size="40" value="${name}" class="required" maxlength="200"/></td>
		</tr>
		<tr>
			<td>章节内容:</td>
			<td>
				
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<security:authorize ifAnyGranted="ROLE_角色修改">
					<c:if test='${viewOnly!=true}'><input class="button" type="submit" value="提交"/>&nbsp;</c:if>
				</security:authorize>
				<input class="button" type="button" value="返回" onclick="history.back()"/>
			</td>
		</tr>
	</table>
</form>
</body>
</html>
