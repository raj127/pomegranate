<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>VODLite2.0 FTP管理</title>
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
			jQuery.validator.addMethod("ip", function(value, element) {    
				return this.optional(element) || (/^(\d+)\.(\d+)\.(\d+)\.(\d+)$/.test(value) && (RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256));
			}, "请输入合法的IP"); 
			jQuery.validator.addMethod("path",function(value,element){
			   	var path=/^\/$|^\/(\w)+[\/(\w)+]*\/?$/ ;
			   	return this.optional(element)||(path.test(value));
		  	},"请输入合法的路径");
			//聚焦第一个输入框
			$("#ftpId").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate({
				rules: {
					ftpId: {
						required: true,
						remote: "ftp!checkFtpId.action?oldFtpId=" + encodeURIComponent('${ftpId}')
					},
					address: {required:true, ip:true},
					name: "required",
					userName:"required",
					userPass:"required",
					path:"path",
					port:{digits:true,required:true,min:0,max:65535},
					system:"required",
					state:"required"
				},
				messages: {
					ftpId: {
						required:"ftpId不能为空",
						remote: "ftpId已存在"
					},
					address:{required:"不能为空"},
					name: "不能为空",
					userName:"不能为空",
					userPass:"不能为空",
					port:{digits:"只能输入整数",required:"不能为空",min:"最小值为0",max:"最大值为65535"},
					system:"不能为空",
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
	<h2><s:if test="id == null">创建</s:if><s:else>修改</s:else>FTP</h2>
	<form id="inputForm" action="ftp!save.action" method="post">
		<input type="hidden" name="id" value="${id}"/>
		<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
		<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
		<input type="hidden" name="page.order" id="order" value="${page.order}"/>
		<input type="hidden" name="page.pageSize" id="pageSize" value="${page.pageSize}"/>
		<table class="noborder">
			<tr>
				<td>FTP编号:</td>
				<td><input type="text" name="ftpId" size="40" id="ftpId" value="${ftpId}" maxlength="50"/></td>
			</tr>
			<tr>
				<td>名字:</td>
				<td><input type="text" id="name" name="name" size="40" value="${name}" maxlength="50"/></td>
			</tr>
		    <tr>
				<td>地址:</td>
				<td><input type="text" id="address" name="address" size="40" value="${address}" maxlength="100"/></td>
			</tr>
			<tr>
				<td>用户名:</td>
				<td><input type="text" id="userName" name="userName" size="40" value="${userName}" maxlength="50"/>
				</td>
			</tr>
			<tr>
				<td>密码:</td>
				<td><input type="text" id="userPass" name="userPass" size="40" value="${userPass}" maxlength="50"/></td>
			</tr>
			<tr>
				<td>路径:</td>
				<td><input type="text" id="path" name="path" size="40" value="${path}" maxlength="50"/></td>
			</tr>
			<tr>
				<td>端口:</td>
				<td><input type="text" id="port" name="port" size="40" value="${port}" maxlength="5"/></td>
			</tr>
			<tr>
				<td>所属系统:</td>
				<td>
					<s:select id="system" name="system"  list="@cn.com.supertv.entity.system.SystemEnum@values()"   value="%{system}" listValue="label" headerKey="" headerValue="请选择" required="true" theme="simple"/>
				</td>
			</tr>
				<tr>
				<td>状态:</td>
				<td>
					<s:select id="state" name="state"  list='#{"false":"不可用","true":"可用"}'   value="%{state}" required="true" headerKey="" headerValue="请选择" theme="simple"/>
				</td>
			</tr>
				<tr>
				<td>描述:</td>
				<td>
					<textarea rows="5" cols="40" id="description" name="description" >${description}</textarea>
				</td>
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
					<security:authorize ifAnyGranted="ROLE_修改FTP">
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
