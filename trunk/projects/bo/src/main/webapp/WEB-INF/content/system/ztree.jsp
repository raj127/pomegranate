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
		var tree1;
		var zNodes =[];

		var setting = {
			view : {
				dblClickExpand : false,
				showLine : true,
				selectedMulti : false,
				expandSpeed : ($.browser.msie && parseInt($.browser.version) <= 6) ? "" : "fast"
			},
			data : {
				simpleData : {
					enable : true,
					idKey : "id",
					pIdKey : "pId",
					rootPId : ""
				}
			},
			async : {
				enable : true,
				url : "ztree!getTree.action",
				autoParam : [ "id", "name", "level" ],
				otherParam : {
					"otherParam" : "zTreeAsyncTest"
				},
				dataFilter : filter
			},
			callback : {
				beforeAsync : zTreeBeforeAsync,
				onAsyncSuccess : zTreeOnAsyncSuccess,
				onClick: onClick
			}
		};

		$(document).ready(function() {
			refreshTree();
		});

		function refreshTree() {
			tree1 = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
		}


		function zTreeBeforeAsync(treeId, treeNode) {
			//alert("zTreeBeforeAsync");
		}

		function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
			//alert("event -->" + event);
			//alert("treeId -->" + treeId);
			//alert("treeNode -->" + treeNode);
			//alert("msg -->" + msg);
		};
		
		function onClick(event, treeId, treeNode, clickFlag) {
			if(treeNode.isParent == true){
				
			}else{
				//alert(treeNode.scdata);
				getTreeData(treeNode.id);
				//alert(treeNode.id + ", " + treeNode.name);	
			}
			
			//alert("event -->" + event);
			//alert("treeId -->" + treeId);
			//alert("treeNode -->" + treeNode);
			//alert("clickFlag -->" + clickFlag);	
		}
		
		//提交请求
	    function getTreeData(treeId){
	        var url = 'ztree!getTreeData.action';
	        var params = {id:treeId};
	        jQuery.post(url, params, callbackFun);
	    }
	    
	    //回调函数
	    function callbackFun(data){
	    	alert(data);
	    	//alert($("#right",parent.document.body).contents().find("#result").text());
	    	$("#treeData").html(data);
	    }




		function filter(treeId, parentNode, childNodes) {
			if (!childNodes)
				return null;
			for ( var i = 0, l = childNodes.length; i < l; i++) {
				childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
			}
			return childNodes;
		}

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
		<table width="100%">
			<tr>
			<td>
				<div class="zTreeDemoBackground left">
					<ul id="treeDemo" class="ztree"></ul>
				</div>			
			</td>
			<td>
			<div id="treeData"></div>
			</td>			
			</tr>
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