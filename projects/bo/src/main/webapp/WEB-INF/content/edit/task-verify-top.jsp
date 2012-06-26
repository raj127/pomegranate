<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>校验结果显示</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
<meta http-equiv="Cache-Control" content="no-store"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<script type="text/javascript" src="${ctx}/js/temp/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/temp/jquery.textarea.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/js/temp/global.css" media="all">

<script language=javascript>
$(function () {
	$(document).click(window.parent.hideMenus);
	if ($("textarea").length > 0) {
		$("textarea").tabby();
	}
});
</script>

<script language=javascript>
//show manual links
function showManuals(link) {
	var obj = $(link);
	window.parent.setManualPosition(".manual", obj.position().left, obj.position().top + obj.height() + 3);
}

function showServerMenu(link) {
	var obj = $(link);
	window.parent.setManualPosition(".server-menu", obj.position().left, obj.position().top + obj.height() + 3);
}
</script>
</head>
<body>

<div class=top>
<div class=left>
<a onclick="showServerMenu(this);return false;" href="#">工具 </a>| 
<a title=Master href="#" target=right>工具</a> 
</div>

<div class=right>admin | 
<a onclick="showManuals(this);return false;" href="#">工具 <span style="FONT-SIZE: 11px">▼</span></A> | 
<a href="#" target=_top>退出</a>  
</div>
<div class=clear>
</div>
</div>
</body>
</html>
