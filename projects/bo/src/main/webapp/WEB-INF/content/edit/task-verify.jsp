<%--

该文件已经不再使用.
darkmi 2012/07/04

--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Simple Layout Demo</title>
	<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.ui.all.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.layout.js"></script>
	<script type="text/javascript" src="${ctx}/js/temp/collection.js"></script>
	<script type="text/javascript" src="${ctx}/js/temp/jquery-ui-1.8.4.custom.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/js/temp/collection.css" media=all/>
	<link rel="stylesheet" type="text/css" href="${ctx}/js/temp/jquery-ui-1.8.4.smoothness.css" media=all/>
	<link rel="stylesheet" type="text/css" href="${ctx}/js/temp/global.css" media=all/>
	
	
	<script type="text/javascript">
	var myLayout; // a var is required because this page utilizes: myLayout.allowOverflow() method
	$(document).ready(function () {
		myLayout = $('body').layout({
			// enable showOverflow on west-pane so popups will overlap north pane
			//west__showOverflowOnHover: true,
			applyDefaultStyles: true,//应用默认样式
			west__size:710
		//,	west__fxSettings_open: { easing: "easeOutBounce", duration: 750 }
		});
		
		//给所有的超链接添加click方法
		$('a').click(function(){
			doSearch($(this).html());
		});
		
 	});
	
	
	</script>
	
	<script type="text/javascript">
	
	//提交请求
    function doSearch(selectedSentence){
		//alert(selectedSentence);
        var url = 'task-verify!search.action';
        var params = {sentence:selectedSentence};
        
        jQuery.post(url, params, callbackFun);
    }
    
    //回调函数
    function callbackFun(data){
    	$('.ui-layout-center').html(data); 
    }
	
	</script>

	<style type="text/css">
	/**
	 *	Basic Layout Theme
	 * 
	 *	This theme uses the default layout class-names for all classes
	 *	Add any 'custom class-names', from options: paneClass, resizerClass, togglerClass
	 */
	.ui-layout-pane { /* all 'panes' */ 
		background: #FFF; 
		border: 1px solid #BBB; 
		padding: 10px; 
		overflow: auto;
	} 

	.ui-layout-resizer { /* all 'resizer-bars' */ 
		background: #DDD; 
	} 

	.ui-layout-toggler { /* all 'toggler-buttons' */ 
		background: #AAA; 
	} 

	</style>


	<style type="text/css">

	body {
		font-family: Arial, sans-serif;
		font-size: 12px;
	}
	p {
		margin: 1em 0;
	}
	
	ul {
		z-index:	100000;
		margin:		1ex 0;
		padding:	0;
		list-style:	none;
		cursor:		pointer;
		border:		1px solid Black;
		width:		15ex;
		position:	relative;
	}
	ul li {
		background-color: #EEE;
		padding: 0.15em 1em 0.3em 5px;
	}
	ul ul {
		display:	none;
		position:	absolute;
		width:		100%;
		left:		-1px;
		bottom:		0;
		margin:		0;
		margin-bottom: 1.55em;
	}
	.ui-layout-north ul ul {
		bottom:		auto;
		margin:		0;
		margin-top:	1.45em;
	}
	ul ul li		{ padding: 3px 1em 3px 5px; }
	ul ul li:hover	{ background-color: #FF9; }
	ul li:hover ul	{ display:	block; background-color: #EEE; }
	
	a:link {
	color: #336699;
	text-decoration: underline;
	}
	a:visited {
	color: #666699;
	text-decoration: underline;
	}
	a:hover {
	color: #FFFFFF;
	text-decoration: none;
	background: #003399;
	}
	a:active {
	color: #FFFFFF;
	text-decoration: none;
	background: #FF9933;
	}
	
	.searchResult{
		border:1px solid red;
		margin-bottom:10px;
		padding:5px;
	}
	
div.record{
	BORDER-BOTTOM: #ccc 2px solid; 
	BORDER-LEFT: #ccc 2px solid; 
	MARGIN-BOTTOM: 5px; 
	BORDER-TOP: #ccc 2px solid; 
	BORDER-RIGHT: #ccc 2px solid
}

div.record_row{
OVERFLOW-Y: hidden; 
WIDTH: 99%; 
MAX-HEIGHT: 150px
}
	
	
	</style>

</head>
<body>

<!-- manually attach allowOverflow method to pane -->
<div class="ui-layout-north" onmouseover="myLayout.allowOverflow('north')" onmouseout="myLayout.resetOverflow(this)">

</div>

<!-- allowOverflow auto-attached by option: west__showOverflowOnHover = true -->
<div class="ui-layout-west">
	<p>
	<a>第一条 为保障煤矿安全生产和职工人身安全，防止煤矿事故，根据《煤炭法》、《矿山安全法》和《煤矿安全监察条例》，制定本规程。</a></p>
	
	<p><a>一、地面位置及标高 </a></p>
	<p><a>3302轨道顺槽开门口相对地面位置位于许厂煤矿自备电厂北院墙以北364m的农田里，杨家河南北穿过巷道，巷道布置方向与杨家河呈70°夹角，停头位置位于杨家河河堤以西837m处的农田里，施工区域相对地面位置均为农田及田间小路。</a><a>巷道掘进范围内地面标高在+39.8～+42.9m之间。 </a></p>
	<p><a>二、井下位置及标高</a></p>
	<p><a>3302轨道顺槽开门口位置位于330西翼采区轨道大巷内353导线点以北31m处巷道的西帮，按方位角311°布置，巷道停头位置西距F5断层为5m。</a><a>巷道底板标高在-317.32～-241.0m之间。</a><p>
	<p><a>三、四邻采掘情况</a></p>
	<p>
	<a>巷道施工区域除已施工的330西翼采区轨道大巷外，其余区域均未采掘。</a>
	</p>
</div>

<div class="ui-layout-south">
</div>

<div class="ui-layout-center">
<!-- list all records -->
<div class="record" onmouseover="showOperationButtons('0')" onmouseout="hideOperationButtons('0')">
<table id=object_0 border=0 width="100%">
<tbody>
<tr>
<td vAlign=top width=50>#1</TD>
<td vAlign=top><!-- operations on record -->
	  <div id=operate_0 class=operation>
			<a href="#">更新</a> | <a id="expand_0" onclick="expandText('1');return false;" href="#">Expand</a> 
	  </div>
	  <div id="text_0" class="record_row" ondblclick="expandText('0');" record_index="0" record_id="">
	  <font color="green">{</font><br>&nbsp;&nbsp; 
	  <font color="#dd0000">just a test</font><br>&nbsp;&nbsp; 
	  <font color="#dd0000">just a test</font><br>&nbsp;&nbsp; 
	  <font color="#dd0000">just a test</font><br>&nbsp;&nbsp; 
	  <font color="#dd0000">just a test</font><br> 
	  <font color="green">}</font><br>&nbsp;&nbsp; </div>
	  <div style="MARGIN-TOP: -14px" align=right>
	  <a href="#">TOP</a>
	  </div>
  
  </td>
  </tr>
  </tbody>
  </table>
  </div>

<div class="record" onmouseover="showOperationButtons('1')" onmouseout="hideOperationButtons('1')">
<table id="object_1" border="0" width="100%">
<tbody>
<tr>
<td vAlign=top width=50>#2</TD>
<td vAlign=top>
	  <div id="operate_1" class="operation">
			<a href="#">更新</a> | <a id="expand_1" onclick="expandText('1');return false;" href="#">Expand</a> 
	  </div>
	  <div id="text_1" class="record_row" ondblclick="expandText('1');" record_index="1" record_id="">
	  <font color="green">{</font><br>&nbsp;&nbsp; 
	  <font color="#dd0000">just a test</font><br>&nbsp;&nbsp; 
	  <font color="#dd0000">just a test</font><br>&nbsp;&nbsp; 
	  <font color="#dd0000">just a test</font><br>&nbsp;&nbsp; 
	  <font color="#dd0000">just a test</font><br> 
	  <font color="green">}</font><br>&nbsp;&nbsp; </div>
	  <div style="MARGIN-TOP: -14px" align=right>
	  <a href="#">TOP</a>
	  </div>
  
  </td>
  </tr>
  </tbody>
  </table>
  </div>

</div>

</body>
</html>