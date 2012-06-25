<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
<HEAD>
<TITLE>right</TITLE>
<META content=text/html;charset=utf-8 http-equiv=Content-Type>
<SCRIPT language=javascript src="${ctx}/js/temp/jquery-1.4.2.min.js"></SCRIPT>
<SCRIPT language=javascript src="${ctx}/js/temp/collection.js"></SCRIPT>
<SCRIPT language=javascript src="${ctx}/js/temp/jquery-ui-1.8.4.custom.min.js"></SCRIPT>
<LINK rel=stylesheet href="${ctx}/js/temp/collection.css" media=all>
<LINK rel=stylesheet href="${ctx}/js/temp/jquery-ui-1.8.4.smoothness.css" media=all>
<LINK rel=stylesheet type=text/css href="${ctx}/js/temp/global.css" media=all>

<style>
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
</HEAD>
<BODY>


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

  
</BODY>
</HTML>
