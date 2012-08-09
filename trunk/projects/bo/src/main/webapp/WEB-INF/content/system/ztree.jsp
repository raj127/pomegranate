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
	<script src="${ctx}/js/ztree/jquery.ztree.core-3.3.js" type="text/javascript"></script>
	<script src="${ctx}/js/ztree/jquery.ztree.excheck-3.3.js" type="text/javascript"></script>
	<script src="${ctx}/js/ztree/jquery.ztree.exedit-3.3.js" type="text/javascript"></script>
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
		/*---------- 全局变量定义 -----------*/
		var zTree;
		var zNodes =[];
		var selectedId;

		/*---------- 参数定义 -----------*/
		var setting = {
			view : {
				addHoverDom: addHoverDom,
				removeHoverDom: removeHoverDom,
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
			edit: {
				enable: true,
				showRemoveBtn: true,
				removeTitle: setRemoveTitle
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
				onClick: zTreeOnClick,
				beforeRemove: zTreeBeforeRemove,
				onRemove: zTreeOnRemove,
				beforeRename: zTreeBeforeRename,
				onRename: zTreeOnRename
			}
		};

		/*---------- 初始化树 -----------*/
		$(document).ready(function() {
			refreshTree();
		});

		/**
		 * 初始化树.
		 */
		function refreshTree() {
			zTree = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
			zTree.setting.edit.showRemoveBtn = true;
			zTree.setting.edit.showRenameBtn = true;
		}
		
		var newCount = 1;
		function addHoverDom(treeId, treeNode) {
			var sObj = $("#" + treeNode.tId + "_span");
			if (treeNode.editNameFlag || $("#addBtn_"+treeNode.id).length>0) return;
			var addStr = "<span class='button add' id='addBtn_" + treeNode.id
				+ "' title='add node' onfocus='this.blur();'></span>";
			sObj.append(addStr);
			var btn = $("#addBtn_"+treeNode.id);
			if (btn) btn.bind("click", function(){
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:"new node" + (newCount++)});
				return false;
			});
		};
		
		function removeHoverDom(treeId, treeNode) {
			$("#addBtn_"+treeNode.id).unbind().remove();
		};

		/**
		 * 设置删除按钮的标题.
		 */
		function setRemoveTitle(treeId, treeNode) {
			return treeNode.isParent ? "删除父节点":"删除叶子节点";
		}
		
		/*---------- 回调函数定义 -----------*/
		function zTreeBeforeAsync(treeId, treeNode) {
			//alert("zTreeBeforeAsync");
		}

		function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
			//alert("event -->" + event);
			//alert("treeId -->" + treeId);
			//alert("treeNode -->" + treeNode);
			//alert("msg -->" + msg);
		};
		
		/**
		 * 单击的回调事件.
		 */
		function zTreeOnClick(event, treeId, treeNode, clickFlag) {
			//alert("click");
			selectedId = treeNode.id;
			if(treeNode.isParent == true){	
			}else{
				//alert(treeNode.id);
				getTreeData(treeNode.id);
			}			
		}
		
		/**
		 * 单击的回调事件.
		 */
		function zTreeBeforeRemove(event, treeId, treeNode, clickFlag) {	
		}
		
		/**
		 * 单击的回调事件.
		 */
		function zTreeOnRemove(event, treeId, treeNode, clickFlag) {
			//alert("zTreeOnRemove");
			//alert("treeNode --> " + treeNode);
			if(treeNode.isParent == true){
			}else{
			}			
		}
		
		/**
		 * 单击的回调事件.
		 */
		function zTreeBeforeRename(event, treeId, treeNode, clickFlag) {
			//alert("zTreeBeforeRename");
		}
		
		/**
		 * 单击的回调事件.
		 */
		function zTreeOnRename(event, treeId, treeNode, clickFlag) {
			//alert("zTreeOnRename");
			if(treeNode.isParent == true){
				
			}else{
			}
		}
		
		//提交请求
	    function getTreeData(treeId){
	        var url = 'ztree!getTreeData.action';
	        var params = {id:treeId};
	        jQuery.post(url, params, callbackFun);
	    }
	    
	    //回调函数
	    function callbackFun(data){
	    	//alert(data);
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
		
		/**
		 * 添加树节点.
		 */
		function addTreeNode(){
			//alert("begin addTreeNode{...");
	        var url = 'ztree!addTreeNode.action';
	        //alert($("#treeName").attr("value"));
	        //alert($("#treeData").attr("value"));
	        var params = {id:selectedId, name:$("#treeName").attr("value"), content:$("#treeData").attr("value")};
	        //alert(params);
	        jQuery.post(url, params, addTreeNodeCallbackFun);
	        //alert("end addTreeNode ...}");
		}
		
		/**
		 *添加树节点的回调函数.
		 */
		function addTreeNodeCallbackFun(data){
			alert(data);
			var selectedNode = zTree.getNodeByParam("id", selectedId, null);
			alert("selectedNode --> " + selectedNode);
			zTree.addNodes(selectedNode, {id:1001, pId:selectedNode.id, name:"new node"});
		}
		
		/**
		 * 修改树节点.
		 */
		function modTreeNode(){
	        var url = 'ztree!modTreeNode.action';
	        var params = {id:selectedId};
	        jQuery.post(url, params, modTreeNodeCallbackFun);
		}

		/**
		 * 修改树节点的回调函数.
		 */
		function modTreeNodeCallbackFun(data){
			alert(data);
		}

		/**
		 * 删除树节点.
		 */
		function delTreeNode(){
	        var url = 'ztree!delTreeNode.action';
	        var params = {id:selectedId};
	        jQuery.post(url, params, delTreeNodeCallbackFun);
		}

		/**
		 * 删除树节点的回调函数.
		 */
		function delTreeNodeCallbackFun(data){
			alert(data);
		}
	//-->
	</script>
	<style type="text/css">
	.ztree li span.button.add {
		margin-left: 2px;
		margin-right: -1px;
		background-position: -144px 0;
		vertical-align: top;
		*vertical-align: middle
	}
	</style>

</head>

<body>
<div id="doc3">
<%@ include file="/common/header.jsp" %>
<div id="bd">
	<div id="yui-main">
	<div class="yui-b">
		<div id="message"><s:actionmessage theme="custom" cssClass="success"/></div>
		<div id="filter">
			<%--
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
			--%>
			<input type="button" value="添加节点" onclick="addTreeNode();" tabindex="6"/>
			<input type="button" value="修改节点" onclick="modTreeNode();" tabindex="6"/>
			<input type="button" value="删除节点" onclick="delTreeNode();" tabindex="6"/>
		</div>
		<div id="content">
		<table width="100%">
			<tr>
			<td valign="top">
				<div class="zTreeDemoBackground left">
					<ul id="treeDemo" class="ztree"></ul>
				</div>			
			</td>
			<td valign="top">
			<form id="inputForm" action="ztree!saveTreeData.action" method="post">
				<table class="noborder">
					<tr valign="top">
						<td>名称:</td>
						<td><input type="text" id="treeName" name="treeName" size="40" value="" maxlength="50"/></td>
					</tr>
					<tr valign="top">
						<td>内容:</td>
						<td>
							<textarea id="treeData" name="treeData" rows="5" cols="40"></textarea>
						</td>
					</tr>
					<tr valign="top">
						<td>创建:</td>
						<td>${createBy} <fmt:formatDate value="${createTime}" type="both"/></td>
					</tr>
					<tr valign="top">
						<td>最后修改:</td>
						<td>${lastModifyBy} <fmt:formatDate value="${lastModifyTime}" type="both"/></td>
					</tr>
					<tr>
						<td colspan="2">
							<input class="button" type="submit" value="提交"/>&nbsp;
						</td>
					</tr>
				</table>
			</form>
			</td>			
			</tr>
		</table>
		</div>
	</div>
	</div>
</div>
<%@ include file="/common/footer.jsp" %>
</div>
</body>
</html>