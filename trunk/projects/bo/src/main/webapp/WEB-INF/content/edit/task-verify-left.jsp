<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
<HEAD>
<TITLE>left</TITLE>
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

</HEAD>
<BODY>
<SCRIPT language=javascript>
/**
 * highlight current collection being viewed
 *
 * @param string name collection name
 */
function highlightCollection(name, count) {
	var collections = $(".collections");
	collections.find("li").each(function () {
		var a = $(this).find("a");
		if (a.attr("cname") == name) {
			if (count != undefined) {
				$(this).find(".count").html(count);
			}
			a.css("font-weight", "bold");
			a.css("color", "blue");
		}
		else {
			a.css("font-weight", "normal");
			a.css("color", "");
		}
	});
}

$(function () {
	var collections = $(".collections");
	collections.find("li").each(function () {
		var li = $(this);
		var a = $(this).find("a");
		var name = a.attr("cname");
		
		li.click(function () {	
			window.location.hash = "#" + name;
		});

		//highlight current selected
		if (window.location.hash == "#" + name) {
			a.css("font-weight", "bold");
			a.css("color", "blue");
		}
	});

	// will be used to redirect to db by name
	var template = '/rockmongo/index.php?action=db.index&db=__DB__';
	$('#selector').submit(function(e) {
	    e.preventDefault();
	    if($('#db').val()) {
		window.parent.frames['right'].location=template.replace('__DB__', $('#db').val());
	    }
	});
});



</SCRIPT>

<DIV style="BACKGROUND-COLOR: #eeefff; HEIGHT: 100%; ">
<DIV style="BORDER-BOTTOM: #ccc 1px solid; MARGIN-BOTTOM: 10px"></DIV>
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

<DIV style="HEIGHT: 40px"></DIV>
</DIV>
</BODY>
</HTML>
