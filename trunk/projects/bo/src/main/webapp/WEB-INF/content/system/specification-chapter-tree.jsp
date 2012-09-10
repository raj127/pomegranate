<%--
Description:系统管理--》规范管理--》树状结构
author:darkmi
date:2012/08/23
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>规范管理</title>
	<%@ include file="/common/meta.jsp" %>
	<link href="${ctx}/css/yui.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx}/css/style.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx}/css/zTreeStyle/zTreeStyle.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx}/css/textarearesizer/textarearesizer.css" type="text/css" rel="stylesheet"/>
	<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/ui-lightness/jquery-ui.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/css/jnotify/jquery.jnotify.css" type="text/css" rel="stylesheet"/>
	
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/jquery-ui.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="http://jqueryui.com/themeroller/themeswitchertool/"></script>	
	<script src="${ctx}/js/ztree/jquery.ztree.core-3.3.js" type="text/javascript"></script>
	<script src="${ctx}/js/ztree/jquery.ztree.excheck-3.3.js" type="text/javascript"></script>
	<script src="${ctx}/js/ztree/jquery.ztree.exedit-3.3.js" type="text/javascript"></script>
	<script src="${ctx}/js/jnotify/jquery.jnotify.js" type="text/javascript"></script>
	<script src="${ctx}/js/textarearesizer/jquery.textarearesizer.compressed.js" type="text/javascript"></script>
	<!--<script src="${ctx}/js/artDialog/artDialog.js?skin=default" type="text/javascript"></script>-->
	<script src="${ctx}/js/table.js" type="text/javascript"></script>
	<script>
		<!--		
		/*---------- 全局变量定义 -----------*/
		var zTree;
		var zNodes =[{id:1, pId:0, name: "所有规范", isParent:true, click:false}];
		var selectedId;
		var op;

		/*---------- 参数定义  -----------*/
		var setting = {
			view : {
				//addHoverDom: addHoverDom,
				//removeHoverDom: removeHoverDom,
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
					rootPId : "0"
				}
			},
			edit: {
				enable: false,
				showRemoveBtn: true,
				removeTitle: setRemoveTitle
			},
			async : {
				enable : true,
				url : "specification-chapter-tree!getTree.action",
				autoParam : [ "id", "name", "level" ],
				otherParam : {
					"otherParam" : "zTreeAsyncTest"
				},
				dataFilter : filter
			},
			callback : {
				onAsyncSuccess : zTreeOnAsyncSuccess,
				onClick: zTreeOnClick
				//beforeAsync : zTreeBeforeAsync,
				//beforeRemove: zTreeBeforeRemove,
				//onRemove: zTreeOnRemove,
				//beforeRename: zTreeBeforeRename,
				//onRename: zTreeOnRename
			}
		};

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
	        $("#treeName").attr('disabled','disabled');
	        $("#treeData").attr('disabled','disabled');
	        $("#btnSubmit").attr('disabled','disabled');

			selectedId = treeNode.id;
			if(treeNode.isParent == true){
				$("#treeName").val(treeNode.name);
			}else{
				$("#treeName").val(treeNode.name);
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
	        var url = 'specification-chapter-tree!getTreeData.action';
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
		 * 点击添加节点按钮激活该方法.
		 */
		function addTreeNode(){
			if(selectedId == null){
				alert("请选择一个节点");
				//$.dialog({content:'hello world!'});
				return;
			}
			//将表单置为空值
	        $("#treeName").val("");
	        $("#treeData").val("");
	        //移除只读属性
	        $("#treeName").removeAttr("disabled");
	        $("#treeData").removeAttr("disabled");
	        $("#btnSubmit").removeAttr("disabled");
	        //设置操作代码
	        op = "add";
		}
				
		/**
		 * 修改树节点.
		 */
		function modTreeNode(){
			if(selectedId == null){
				alert("请选择一个节点");
				return;
			}
	        //移除只读属性
	        $("#treeName").removeAttr("disabled");
	        $("#treeData").removeAttr("disabled");
	        $("#btnSubmit").removeAttr("disabled");
	        //设置操作代码
	        op = "mod";
		}

		/**
		 * 点击删除树节点按钮.
		 */
		function delTreeNode(){
			if(selectedId == null){
				alert("请选择一个节点");
				return;
			}
	        //设置操作代码
	        op = "del";
	        var url = 'specification-chapter-tree!delTreeNode.action';
	        var params = {id:selectedId, name:$("#treeName").attr("value"), content:$("#treeData").attr("value")};
	        $.getJSON(url, params, delTreeNodeCallbackFun);
		}

		/**
		 * 删除树节点的回调函数.
		 */
		function delTreeNodeCallbackFun(data){
			//alert("data.id --> " + data.id);
			//alert("data.retCode --> " + data.retCode);
			//alert("data.retMessage --> " + data.retMessage);
			if(data.retCode == 1){
				//alert("删除成功.");
				var selectedNode = zTree.getNodeByParam("id", selectedId, null);
				zTree.removeNode(selectedNode, null);
				//将表单置为空值
		        $("#treeName").val("");
		        $("#treeData").val("");				
			}else{
				//alert("删除失败.");
			}			
		}
		
		/**
		 * 点击提交按钮激活该方法.
		 */
		function doSubmit(){
			if(op == "add"){
				var selectedNode = zTree.getNodeByParam("id", selectedId, null);
		        var url = 'specification-chapter-tree!addTreeNode.action';
		        var params = {id:selectedId, name:$("#treeName").attr("value"), content:$("#treeData").attr("value")};
		        $.getJSON(url, params, addTreeNodeCallbackFun);
			}else if(op == "mod"){
				var selectedNode = zTree.getNodeByParam("id", selectedId, null);
		        var url = 'specification-chapter-tree!modTreeNode.action';
		        var params = {id:selectedId, name:$("#treeName").attr("value"), content:$("#treeData").attr("value")};
		        $.getJSON(url, params, modTreeNodeCallbackFun);
			}
		}
		
		/**
		 *添加树节点的回调函数.
		 */
		function addTreeNodeCallbackFun(data){
			var treeName = $("#treeName").attr("value");
			var selectedNode = zTree.getNodeByParam("id", selectedId, null);
			zTree.addNodes(selectedNode, {id:data.id, pId:selectedNode.id, name:treeName});
		}
		
		/**
		 * 修改树节点的回调函数.
		 */
		function modTreeNodeCallbackFun(data){
			//alert("data.id --> " + data.id);
			//alert("data.retCode --> " + data.retCode);
			//alert("data.retMessage --> " + data.retMessage);	
			if(data.retCode == 1){
				//alert("修改成功.");
				var selectedNode = zTree.getNodeByParam("id", selectedId, null);
				selectedNode.name = $("#treeName").attr("value");
				zTree.updateNode(selectedNode);
			}else{
				//alert("修改失败.");
			}
		}
		
		$(document).ready(function() {
			$(".mainNav a").attr("class","");
			$("#n2").attr("class","actived");
			$("#subNav206").attr("class","actived");
			$(".secondNav div").each(function(){
				$(this).hide();
				$("#subNav2").show();
			});
			
			//将编辑框置为不可用
	        $("#treeName").attr('disabled','disabled');
	        $("#treeData").attr('disabled','disabled');
	        $("#btnSubmit").attr('disabled','disabled');
	        
	        //初始化树
	        refreshTree();
	        
	     // --------------------------------------------------------------------------
	        
            // For jNotify Inizialization
            // Parameter:
            // oneAtTime : true if you want show only one message for time
            // appendType: 'prepend' if you want to add message on the top of stack, 'append' otherwise
            $('#StatusBar').jnotifyInizialize({
                oneAtTime: true
            })
            $('#Notification')
                .jnotifyInizialize({
                    oneAtTime: false,
                    appendType: 'append'
                })
                .css({ 'position': 'absolute',
                    'marginTop': '20px',
                    'right': '20px',
                    'width': '250px',
                    'z-index': '9999'
                });
            // --------------------------------------------------------------------------
            // For add a notification on button click
            // Parameter:
            // text: Html do you want to show
            // type: 'message' or 'error'
            // permanent: True if you want to make a message permanent
            // disappearTime: Time spent before closing message
            $('#addStatusBarMessage').click(function() {
                $('#StatusBar').jnotifyAddMessage({
                    text: 'This is a non permanent message.',
                    permanent: false,
                    showIcon: false
                });
            });

            $('#addStatusBarError').click(function() {
                $('#StatusBar').jnotifyAddMessage({
                    text: 'This is a permanent error.',
                    permanent: true,
                    type: 'error'
                });
            });

            $('#addNotificationMessage').click(function() {
                $('#Notification').jnotifyAddMessage({
                    text: 'This is a non permanent message.',
                    permanent: false
                });
            });

            $('#addNotificationError').click(function() {
                $('#Notification').jnotifyAddMessage({
                    text: 'This is a permanent error.',
                    permanent: true,
                    type: 'error'
                });
            });
            // -----------------------------------------------------
	        
			$('textarea.resizable:not(.processed)').TextAreaResizer();

		});
		
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
	    <div id="Notification"></div>
	    <div id="StatusBar" style="height: 20px;"></div>
    		
		<div id="filter">
			<input type="button" value="表格结构" onclick="linkTo('specification-chapter-table.action')" tabindex="5"/>
			<input type="button" value="添加节点" onclick="addTreeNode();" tabindex="1"/>
			<input type="button" value="修改节点" onclick="modTreeNode();" tabindex="2"/>
			<input type="button" value="删除节点" onclick="delTreeNode();" tabindex="3"/>
			<%-- 
		    <button id="addStatusBarMessage">Add1</button>
		    <button id="addStatusBarError">Add2</button>
		    <button id="addNotificationMessage">Add3</button>
		    <button id="addNotificationError">Add4</button>
		    --%>
			
		</div>
		<div id="content">
		<table width="100%">
			<tr>
			<td valign="top" width="40%">
				<div class="zTreeDemoBackground left">
					<ul id="treeDemo" class="ztree"></ul>
				</div>			
			</td>
			<td valign="top">
			<form id="inputForm" action="specification-chapter-tree!saveTreeData.action" method="post">
				<table class="noborder">
					<tr valign="top">
						<td width="10%">名称:</td>
						<td><input type="text" id="treeName" name="treeName" size="40" value="" maxlength="50"/></td>
					</tr>
					<tr valign="top">
						<td>内容:</td>
						<td>
							<textarea id="treeData" name="treeData" class="resizable"></textarea>
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
							<input type="button" class="button" id="btnSubmit" onclick="doSubmit();" value="提交"/>&nbsp;
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