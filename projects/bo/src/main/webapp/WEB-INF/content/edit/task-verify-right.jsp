<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>校验结果显示</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
<meta http-equiv="Cache-Control" content="no-store"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<script type="text/javascript" src="${ctx}/js/temp/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/temp/collection.js"></script>
<script type="text/javascript" src="${ctx}/js/temp/jquery-ui-1.8.4.custom.min.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/js/temp/collection.css" media="all" />
<link rel="stylesheet" type="text/css" href="${ctx}/js/temp/jquery-ui-1.8.4.smoothness.css" media="all" />
<link rel="stylesheet" type="text/css" href="${ctx}/js/temp/global.css" media="all" />
<style type="text/css">
* {
	font-family: 'Courier New', Arial; 
	font-size: 12px;
}
BODY {
	background-color:#FFFFFF;
	PADDING-BOTTOM: 10px; 
	MARGIN: 0px; 
	PADDING-LEFT: 10px; 
	PADDING-RIGHT: 10px; 
	PADDING-TOP: 10px
}
</style>
<style type="text/css">
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
<!-- list all records -->
<div class="record" onmouseover="showOperationButtons('0')" onmouseout="hideOperationButtons('0')">
<table id=object_0 border=0 width="100%">
<tbody>
<tr>
	<td vAlign=top width=50>#1</TD>
	<td vAlign=top><!-- operations on record -->
		<div id=operate_0 class=operation>
			<a href="#">复制</a> | <a id="expand_0" onclick="expandText('0');return false;" href="#">隐藏</a> 
		</div>
		<div id="text_0" class="record_row" ondblclick="expandText('0');" record_index="0" record_id="">
			<font color="green">{</font><br>&nbsp;&nbsp; 
			<font color="#dd0000">just a test</font><br>&nbsp;&nbsp; 
			<font color="#dd0000">just a test</font><br>&nbsp;&nbsp; 
			<font color="#dd0000">just a test</font><br>&nbsp;&nbsp; 
			<font color="#dd0000">just a test</font><br> 
			<font color="green">}</font><br>&nbsp;&nbsp; 
		</div>
		<div style="MARGIN-TOP: -14px" align="right">
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
	<td vAlign="top" width=50>#2</TD>
	<td vAlign="top">
		<div id="operate_1" class="operation">
			<a href="#">复制</a> | <a id="expand_1" onclick="expandText('1');return false;" href="#">隐藏</a> 
		</div>
		<div id="text_1" class="record_row" ondblclick="expandText('1');" record_index="1" record_id="">
			<font color="green">{</font><br>&nbsp;&nbsp; 
			<font color="#dd0000">just a test</font><br>&nbsp;&nbsp;
			<font color="#dd0000">just a test</font><br>&nbsp;&nbsp;
			<font color="#dd0000">just a test</font><br>&nbsp;&nbsp;
			<font color="#dd0000">just a test</font><br>
			<font color="green">}</font><br>&nbsp;&nbsp;
		</div>
		<div style="MARGIN-TOP: -14px" align="right">
			<a href="#">TOP</a>
		</div>
	</td>
</tr>
</tbody>
</table>
</div>

</body>
</html>
