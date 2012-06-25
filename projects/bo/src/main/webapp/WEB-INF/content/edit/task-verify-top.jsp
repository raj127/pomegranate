<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
<HEAD>
<TITLE>top</TITLE>
<META content=text/html;charset=utf-8 http-equiv=Content-Type>
<SCRIPT language=javascript src="${ctx}/js/temp/jquery-1.4.2.min.js"></SCRIPT>
<SCRIPT language=javascript src="${ctx}/js/temp/jquery.textarea.js"></SCRIPT>
<LINK rel=stylesheet type=text/css href="${ctx}/js/temp/global.css" media=all>

<SCRIPT language=javascript>
$(function () {
	$(document).click(window.parent.hideMenus);
	if ($("textarea").length > 0) {
		$("textarea").tabby();
	}
});
</SCRIPT>

<SCRIPT language=javascript>
//show manual links
function showManuals(link) {
	var obj = $(link);
	window.parent.setManualPosition(".manual", obj.position().left, obj.position().top + obj.height() + 3);
}

function showServerMenu(link) {
	var obj = $(link);
	window.parent.setManualPosition(".server-menu", obj.position().left, obj.position().top + obj.height() + 3);
}
</SCRIPT>
</head>
<BODY>

<DIV class=top>
<DIV class=left>
<SELECT title="Switch Hosts" 
                onchange="window.parent.location='/rockmongo/index.php?action=admin.changeHost&amp;index='+this.value" 
name=host> 
<OPTION selected value=0>Localhost</OPTION>
</SELECT> | 
<A onclick="showServerMenu(this);return false;" href="#">工具 
<SPAN style="FONT-SIZE: 11px">▼</SPAN></A> | 
<A title=Master href="http://127.0.0.1:7788/rockmongo/index.php?action=server.replication" target=right>Master</A> 
</DIV>
<DIV class=right>admin | 
<A onclick="showManuals(this);return false;" href="http://127.0.0.1:7788/rockmongo/index.php?action=admin.top#">手册 
<SPAN style="FONT-SIZE: 11px">▼</SPAN></A> | 
<A href="http://127.0.0.1:7788/rockmongo/index.php?action=logout.index" target=_top>退出</A> | 
<SELECT style="WIDTH: 100px" 
                onchange="window.top.location='index.php?action=admin.changeLang&amp;lang='+this.value" 
	     name=language>
	<OPTION value=en_us>English</OPTION>
	<OPTION selected value=zh_cn>简体中文 - Chinese simplified</OPTION>
</SELECT> | 
  <A href="#" target=right>RockMongo v1.1.2</A></DIV>
<DIV class=clear>
</DIV>
</DIV>
</BODY>
</HTML>
