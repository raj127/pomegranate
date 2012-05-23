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
	<link rel="stylesheet" href="${ctx}/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${ctx}/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.ztree.core-3.2.min.js"></script>
	<style>
	/*body {
		background-color: white;
		margin:0; padding:0;
		text-align: center;
	}
	div, p, table, th, td {
		list-style:none;
		margin:0; padding:0;
		color:#333; font-size:12px;
		font-family:dotum, Verdana, Arial, Helvetica, AppleGothic, sans-serif;
	}
	#testIframe {margin-left: 10px;}*/
	</style>
	<script type="text/javascript" >
	<!--
	var zTree;
	var demoIframe;
	
	var setting = {
	view: {
		dblClickExpand: false,
		showLine: true,
		selectedMulti: false,
		expandSpeed: ($.browser.msie && parseInt($.browser.version)<=6)?"":"fast"
	},
	data: {
		simpleData: {
			enable:true,
			idKey: "id",
			pIdKey: "pId",
			rootPId: ""
		}
	},
	callback: {
		beforeClick: function(treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("tree");
			if (treeNode.isParent) {
				zTree.expandNode(treeNode);
				return false;
			} else {
				demoIframe.attr("src",treeNode.file);
				return true;
			}
		}
	}
	};
	
	var zNodes =[
	{id:1, pId:0, name:"[core] 基本功能 演示", open:true},
	{id:101, pId:1, name:"最简单的树 --  标准 JSON 数据", file:"specification-chapter!input.action"},
	{id:102, pId:1, name:"最简单的树 --  简单 JSON 数据", file:"core/simpleData"},
	{id:103, pId:1, name:"不显示 连接线", file:"core/noline"},
	{id:104, pId:1, name:"不显示 节点 图标", file:"core/noicon"},
	{id:105, pId:1, name:"自定义图标 --  icon 属性", file:"core/custom_icon"},
	{id:106, pId:1, name:"自定义图标 --  iconSkin 属性", file:"core/custom_iconSkin"},
	{id:107, pId:1, name:"自定义字体", file:"core/custom_font"},
	{id:115, pId:1, name:"超链接演示", file:"core/url"},
	{id:108, pId:1, name:"异步加载 节点数据", file:"core/async"},
	{id:109, pId:1, name:"用 zTree 方法 异步加载 节点数据", file:"core/async_fun"},
	{id:110, pId:1, name:"用 zTree 方法 更新 节点数据", file:"core/update_fun"},
	{id:111, pId:1, name:"单击 节点 控制", file:"core/click"},
	{id:112, pId:1, name:"展开 / 折叠 父节点 控制", file:"core/expand"},
	{id:113, pId:1, name:"根据 参数 查找 节点", file:"core/searchNodes"},
	{id:114, pId:1, name:"其他 鼠标 事件监听", file:"core/otherMouse"},
	
	{id:2, pId:0, name:"[excheck] 复/单选框功能 演示", open:false},
	{id:201, pId:2, name:"Checkbox 勾选操作", file:"excheck/checkbox"},
	{id:206, pId:2, name:"Checkbox nocheck 演示", file:"excheck/checkbox_nocheck"},
	{id:207, pId:2, name:"Checkbox chkDisabled 演示", file:"excheck/checkbox_chkDisabled"},
	{id:208, pId:2, name:"Checkbox halfCheck 演示", file:"excheck/checkbox_halfCheck"},
	{id:202, pId:2, name:"Checkbox 勾选统计", file:"excheck/checkbox_count"},
	{id:203, pId:2, name:"用 zTree 方法 勾选 Checkbox", file:"excheck/checkbox_fun"},
	{id:204, pId:2, name:"Radio 勾选操作", file:"excheck/radio"},
	{id:209, pId:2, name:"Radio nocheck 演示", file:"excheck/radio_nocheck"},
	{id:210, pId:2, name:"Radio chkDisabled 演示", file:"excheck/radio_chkDisabled"},
	{id:211, pId:2, name:"Radio halfCheck 演示", file:"excheck/radio_halfCheck"},
	{id:205, pId:2, name:"用 zTree 方法 勾选 Radio", file:"excheck/radio_fun"},
	
	{id:3, pId:0, name:"[exedit] 编辑功能 演示", open:false},
	{id:301, pId:3, name:"拖拽 节点 基本控制", file:"exedit/drag"},
	{id:302, pId:3, name:"拖拽 节点 高级控制", file:"exedit/drag_super"},
	{id:303, pId:3, name:"用 zTree 方法 移动 / 复制 节点", file:"exedit/drag_fun"},
	{id:304, pId:3, name:"基本 增 / 删 / 改 节点", file:"exedit/edit"},
	{id:305, pId:3, name:"高级 增 / 删 / 改 节点", file:"exedit/edit_super"},
	{id:306, pId:3, name:"用 zTree 方法 增 / 删 / 改 节点", file:"exedit/edit_fun"},
	{id:307, pId:3, name:"异步加载 & 编辑功能 共存", file:"exedit/async_edit"},
	{id:308, pId:3, name:"多棵树之间 的 数据交互", file:"exedit/multiTree"},
	
	{id:4, pId:0, name:"大数据量 演示", open:false},
	{id:401, pId:4, name:"一次性加载大数据量", file:"bigdata/common"},
	{id:402, pId:4, name:"分批异步加载大数据量", file:"bigdata/diy_async"},
	{id:403, pId:4, name:"分批异步加载大数据量", file:"bigdata/page"},
	
	{id:5, pId:0, name:"组合功能 演示", open:false},
	{id:501, pId:5, name:"冻结根节点", file:"super/oneroot"},
	{id:502, pId:5, name:"单击展开/折叠节点", file:"super/oneclick"},
	{id:503, pId:5, name:"保持展开单一路径", file:"super/singlepath"},
	{id:504, pId:5, name:"添加 自定义控件", file:"super/diydom"},
	{id:505, pId:5, name:"checkbox / radio 共存", file:"super/checkbox_radio"},
	{id:506, pId:5, name:"左侧菜单", file:"super/left_menu"},
	{id:507, pId:5, name:"下拉菜单", file:"super/select_menu"},
	{id:509, pId:5, name:"带 checkbox 的多选下拉菜单", file:"super/select_menu_checkbox"},
	{id:510, pId:5, name:"带 radio 的单选下拉菜单", file:"super/select_menu_radio"},
	{id:508, pId:5, name:"右键菜单 的 实现", file:"super/rightClickMenu"},
	{id:511, pId:5, name:"与其他 DOM 拖拽互动", file:"super/dragWithOther"}
	];
	
	/*$(document).ready(function(){
	var t = $("#tree");
	t = $.fn.zTree.init(t, setting, zNodes);
	demoIframe = $("#testIframe");
	demoIframe.bind("load", loadReady);
	var zTree = $.fn.zTree.getZTreeObj("tree");
	zTree.selectNode(zTree.getNodeByParam("id", 101));
	
	});
	
	function loadReady() {
		var bodyH = demoIframe.contents().find("body").get(0).scrollHeight,
		htmlH = demoIframe.contents().find("html").get(0).scrollHeight,
		maxH = Math.max(bodyH, htmlH), minH = Math.min(bodyH, htmlH),
		h = demoIframe.height() >= maxH ? minH:maxH ;
		if (h < 530) h = 530;
		demoIframe.height(h);
	}*/
	
	//-->
	</script>
	
	<script>
		$(document).ready(function() {
			$(".mainNav a").attr("class","");
			$("#n2").attr("class","actived");
			$("#subNav206").attr("class","actived");
			$(".secondNav div").each(function(){
				$(this).hide();
				$("#subNav2").show();
			});
			
			
			var t = $("#tree");
			t = $.fn.zTree.init(t, setting, zNodes);
			demoIframe = $("#testIframe");
			//demoIframe.bind("load", loadReady);
			var zTree = $.fn.zTree.getZTreeObj("tree");
			zTree.selectNode(zTree.getNodeByParam("id", 101));

		});
	</script>

</head>

<body>
<div id="doc3">
<%@ include file="/common/header.jsp" %>
<div id="bd1">
	<div id="yui-main">
	<div class="yui-b">
	<div id="content">
	<table id="contentTable">
		<tr>
			<td width="260px" valign="top">
				<ul id="tree" class="ztree" style="width:260px; overflow:auto;"></ul>
			</td>
			<td width="100%" align="left" valign="top">
			<iframe id="testIframe" 
			        name="testIframe" 
			        frameborder="0" 
			        scrolling="auto"
			        width="100%"  
			        height="600px" 
			        src="specification-chapter!input.action">
			</iframe>
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
