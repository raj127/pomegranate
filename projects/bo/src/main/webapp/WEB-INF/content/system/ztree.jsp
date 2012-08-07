<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>安全规范查看</title>
	<%@ include file="/common/meta.jsp" %>
	<link href="${ctx}/css/yui.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx}/css/style.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx}/css/zTreeStyle/zTreeStyle.css" type="text/css" rel="stylesheet"/>
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/table.js" type="text/javascript"></script>
	<script src="${ctx}/js/ztree/jquery.ztree.core-3.3.js" type="text/javascript"></script>
	<script src="${ctx}/js/jquery.pagination.js" type="text/javascript"></script>
	<script>
		$(document).ready(function() {
			$(".mainNav a").attr("class","");
			$("#n2").attr("class","actived");
			$("#subNav206").attr("class","actived");
			$(".secondNav div").each(function(){
				$(this).hide();
				$("#subNav2").show();
			});
		});
		
	</script>
	<script type="text/javascript">
		<!--
		var setting = {	};

		var zNodes =[
			{ name:"父节点1 - 展开", open:true,
				children: [
					{ name:"父节点11 - 折叠",
						children: [
							{ name:"叶子节点111"},
							{ name:"叶子节点112"},
							{ name:"叶子节点113"},
							{ name:"叶子节点114"}
						]},
					{ name:"父节点12 - 折叠",
						children: [
							{ name:"叶子节点121"},
							{ name:"叶子节点122"},
							{ name:"叶子节点123"},
							{ name:"叶子节点124"}
						]},
					{ name:"父节点13 - 没有子节点", isParent:true}
				]},
			{ name:"父节点2 - 折叠",
				children: [
					{ name:"父节点21 - 展开", open:true,
						children: [
							{ name:"叶子节点211"},
							{ name:"叶子节点212"},
							{ name:"叶子节点213"},
							{ name:"叶子节点214"}
						]},
					{ name:"父节点22 - 折叠",
						children: [
							{ name:"叶子节点221"},
							{ name:"叶子节点222"},
							{ name:"叶子节点223"},
							{ name:"叶子节点224"}
						]},
					{ name:"父节点23 - 折叠",
						children: [
							{ name:"叶子节点231"},
							{ name:"叶子节点232"},
							{ name:"叶子节点233"},
							{ name:"叶子节点234"}
						]}
				]},
			{ name:"父节点3 - 没有子节点", isParent:true}

		];


		$(document).ready(function() {
			$.ajax({
				type : "Get",
				url : 'GetTree',
				dataType : "text",
				global : false,
				async : false,
				success : function(strReult) {
					zNodes = eval(strReult);
				},
				error : function() {
					alert("Ajax请求数据失败!");
				}
			});

			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});
	//-->
	</script>
	
</head>

<body>
<div id="doc3">
<%@ include file="/common/header.jsp" %>
<div id="bd">


	<div id="yui-main">
	<div class="yui-b">
	<form id="mainForm" action="specification-chapter.action" method="post">
		<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
		<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
		<input type="hidden" name="page.order" id="order" value="${page.order}"/>
		<input type="hidden" name="page.pageSize" id="pageSize" value="${page.pageSize}"/>
		<p>该功能准备使用ztree来做.</p>
		<div id="message"><s:actionmessage theme="custom" cssClass="success"/></div>
		<div id="filter">
			章节名称: <input type="text" name="filter_LIKES_chapterName" 
			                            value="${param['filter_LIKES_chapterName']}" 
			                            size="9" tabindex="1" 
			                            onkeypress="if (event.keyCode == 13) {javascript:document.forms.mainForm.submit()}"/>
			章节内容: <input type="text" name="filter_LIKES_content"
							             value="${param['filter_LIKES_content']}" 
							             size="9" tabindex="3" 
							             onkeypress="if (event.keyCode == 13) {javascript:document.forms.mainForm.submit()}"/>
			<input type="button" value="搜索" onclick="search();" tabindex="5"/>
			&nbsp;&nbsp;
			
			<input type="button" value="更新索引" onclick="linkTo('specification-chapter!index.action')" tabindex="6"/>
			<input type="button" value="树状结构" onclick="linkTo('ztree.action')" tabindex="6"/>

		</div>
		<div id="content">
		
		<div class="zTreeDemoBackground left">
			<ul id="treeDemo" class="ztree"></ul>
		</div>
		
			<table id="contentTable">
				<tr>
					<th width="5%"><a href="javascript:sort('name','asc')">章节名称</a></th>
					<th width="10%"><a href="javascript:sort('content','asc')">章节内容</a></th>
					<th width="10%"><a href="javascript:sort('isLeaf','asc')">叶子节点</a></th>
					<th width="15%">操作</th>
				</tr>

				<s:iterator value="page.result">
					<tr>
						<td>${name}&nbsp;</td>
						<td>
						<s:if test="%{content != null && content.length()>70}">
	                    	<s:property value='%{content.substring(0, 59)+"......"}' />
				        </s:if>
			           	<s:else>
			            	<s:property value="%{content}" default="-" />
			          	</s:else>						
						</td>
						<td>${isLeaf}&nbsp;</td>
						<td>&nbsp;
						<a href="specification-chapter!input.action?id=${id}&viewOnly=true">查看</a>&nbsp;
						<a href="specification-chapter!input.action?id=${id}&page.pageNo=${page.pageNo}&page.orderBy=${page.orderBy}&page.order=${page.order}&page.pageSize=${page.pageSize}">修改</a>&nbsp;
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
	</form>
	</div>
	</div>
</div>
<%@ include file="/common/footer.jsp" %>
</div>
</body>
</html>