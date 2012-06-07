<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>作业规程校验</title>
	<%@ include file="/common/meta.jsp" %>
	<link href="${ctx}/css/yui.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx}/css/style.css" type="text/css" rel="stylesheet"/>

	<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.ui.all.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.layout.js"></script>
	<script type="text/javascript">
	
	var myLayout; // a var is required because this page utilizes: myLayout.allowOverflow() method

	$(document).ready(function () {
		myLayout = $('body').layout({
			// enable showOverflow on west-pane so popups will overlap north pane
			//west__showOverflowOnHover: true,
			west__size:710

		//,	west__fxSettings_open: { easing: "easeOutBounce", duration: 750 }
		});
 	});

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
	/**
	 *	ALL CSS below is only for cosmetic and demo purposes
	 *	Nothing here affects the appearance of the layout
	 */

	body {
		font-family: Arial, sans-serif;
		font-size: 0.85em;
	}
	p {
		margin: 1em 0;
	}

	/*
	 *	Rules below are for simulated drop-down/pop-up lists
	 */
	ul {
		/* rules common to BOTH inner and outer UL */
		z-index:	100000;
		margin:		1ex 0;
		padding:	0;
		list-style:	none;
		cursor:		pointer;
		border:		1px solid Black;
		/* rules for outer UL only */
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
		/* Pop-Up */
		bottom:		0;
		margin:		0;
		margin-bottom: 1.55em;
	}
	.ui-layout-north ul ul {
		/* Drop-Down */
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
</div>


</body>
</html>