<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>模板目录管理</title>
	<%@ include file="/common/meta.jsp" %>
	<link href="${ctx}/css/yui.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx}/css/style.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet"/>
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
		<script>
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
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#fullname").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate({
				rules: {
					providerId: {
						required: true,
						remote: "provider-info!checkProviderId.action?oldProviderId=" + encodeURIComponent('${providerId}')
					},
					name: {
						required: true,
						remote: "provider-info!checkProviderName.action?oldProviderName=" + encodeURIComponent('${name}')
					},
					state:"required",
					checkedProductIds:"required"
				},
				messages: {
					providerId: {
						required:"不能为空",
						remote: "已存在"
					},
					name: {
						required:"不能为空",
						remote: "已存在"
					},
					state:"不能为空",
					checkedProductIds:"不能为空"
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
	<h2><s:if test="id == null">添加</s:if><s:else>修改</s:else>作业规程模板目录</h2>
	<form id="inputForm" action="template-chapter!save.action" method="post">
		<input type="hidden" name="id" value="${id}"/>
		<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
		<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
		<input type="hidden" name="page.order" id="order" value="${page.order}"/>
		<input type="hidden" name="page.pageSize" id="pageSize" value="${page.pageSize}"/>
		<input type="hidden" name="templateId" id="templateId" value="${templateId}"/>
		<table class="noborder">
			<tr>
				<td>章节名称:</td>
				<td>
					<input type="text" 
					       id="chapterName" 
					       name="chapterName" 
					       value="${chapterName}" 
					       size="40" 
					       maxlength="50" />
				</td>
			</tr>
			<tr>
				<td>显示顺序:</td>
				<td><input type="text" id="displayOrder" name="displayOrder" value="${displayOrder}" size="40" maxlength="255"/></td>
			</tr>
			
			<s:if test="%{parentId != 0}">
			<tr>
				<td>父章节:</td>
				<td>
				<s:select id="parentId" 
				          name="parentId"
				          headerKey="0" 
				          headerValue="请选择" 
				          list="%{allParentChapter}"  
				          listKey="id" 
				          listValue="chapterName"   
				          required="true" 
				          theme="simple" />

				</td>
			</tr>
			</s:if>
			<tr>
				<td>状态:</td>
				<td>
					<s:select id="state" 
					          name="state"  
					          list='#{"DELETE":"不可用","NORMAL":"可用"}'   value="%{state}" required="true" headerKey="" headerValue="请选择" theme="simple"/>
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
					
						<input class="button" type="submit" value="提交"/>&nbsp;
					
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
