<%--
Description:规程编制 --》 规程章节编辑的主页面
author:darkmi
CreateDate:2012/08/21
UpdateDate:2012/08/27
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>规程编制</title>
	<%@ include file="/common/meta.jsp" %>
	<link rel="stylesheet" type="text/css" href="${ctx}/js/temp/global.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/js/temp/layout-default-1.3.0.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/js/temp/collection.css" media="all" />
	<script type="text/javascript" src="${ctx}/js/temp/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/temp/jquery-ui-1.8.4.custom.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/temp/jquery.layout.min-1.3.0.js"></script>
	<script type="text/javascript" src="${ctx}/js/temp/collection.js"></script>
	
	<%--############ --%>
	<script type="text/javascript" src="${ctx}/officecontrol/OfficeContorlFunctions.js"></script>
	<%--############ --%>
	<script language="javascript">
	$(document).ready(function(){
		//-----------------------
		//alert('$(window).height() ==> ' + $(window).height()); //浏览器当前窗口可视区域高度 
		//alert('$(document).height() ==> ' + $(document).height()); //浏览器当前窗口文档的高度 
		//alert('$(document.body).height() ==> ' + $(document.body).height());//浏览器当前窗口文档body的高度 
		//alert('$(document.body).outerHeight(true) ==> ' + $(document.body).outerHeight(true));//浏览器当前窗口文档body的总高度 包括border padding margin 
		//alert($(window).width()); //浏览器当前窗口可视区域宽度 
		//alert($(document).width());//浏览器当前窗口文档对象宽度 
		//alert($(document.body).width());//浏览器当前窗口文档body的高度 
		//alert($(document.body).outerWidth(true));//浏览器当前窗口文档body的总宽度 包括border padding margin 
		//-----------------------
	    //---Init Layout
	    var wsize=250;
	    $('body').layout({
	        defaults: {
	            applyDefaultStyles: false
	
	        },
	        north :{
	            closable:true,
	            slidable:false
	        },
	        east: {
	            minSize: wsize,
	            maxSize: 3*wsize,
	            size: 350,
	            resizable:true,
	            closable: true,
	            slidable: true
	        }
	    });
	});
	</script>
	
	<script type="text/javascript">
	
	//提交请求
    function doSearch(selectedSentence){
        var url = 'task-chapter-edit-main!search.action';
        var params = {sentence:selectedSentence};
        jQuery.post(url, params, callbackFun);
    }
    
    //回调函数
    function callbackFun(data){
    	//alert(data);
    	//alert($("#right",parent.document.body).contents().find("#result").text());
    	$("#result").html(data);
    }
	
	</script>
	<style type="text/css">
	body {
		background-color:#FFFFFF;
		padding: 5px 
	}
	
	div.record{
		border: #ccc 1px solid;  
		margin-bottom: 5px; 
	}
	
	div.record_row{
		WIDTH: 99%;
	}
	</style>
	
</head>

<body onload='intializePage("${ctx}${tc.task.path}${tc.fileName}")' onunload="onPageClose()">
<!-- top bar -->
<div id="top-pane" class="ui-layout-north" style="overflow:hidden" >
<%@ include file="task-chapter-edit-top.jsp"%>
</div>
<div id="left-pane" class="ui-layout-center"><!-- left bar -->
<%@ include file="task-chapter-edit-left.jsp"%>
</div>
<div id="right-pane" class="ui-layout-east"><!-- right bar -->
<div id="result">在编辑器中选中一行文件，点击菜单栏的内容工具-》校验本行，可以插件校验结果。</div>
</div>
</body>
</html>
<%@ include file="task-chapter-edit-ntko.jsp"%>
