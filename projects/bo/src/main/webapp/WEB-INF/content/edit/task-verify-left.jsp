<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>作业规程校验</title>
	<%@ include file="/common/meta.jsp" %>
	<script type="text/javascript" src="${ctx}/js/temp/jquery-1.4.2.min.js"></SCRIPT>
	<script type="text/javascript" src="${ctx}/js/temp/jquery.textarea.js"></SCRIPT>
	<link rel="stylesheet" type="text/css" href="${ctx}/js/temp/global.css" media="all">
	<script type="text/javascript">
	var myLayout; // a var is required because this page utilizes: myLayout.allowOverflow() method
	$(document).ready(function () {
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
    	//alert(data);
    	//alert($(window.parent.frames["right"].document));
    	//$(window.parent.frames["right"].document).html(data);
    	//alert(top.right.location);
    	//alert(top.left.location);
    	//alert(top.top.location);
    	
    	//self.parent.frames["right"].test();
    	
    	alert($("#right",parent.document.body).contents().find("#result").text());
    	alert($("#right",parent.document.body).contents().find("#result").html(data));

    	//window.parent.frames["right"].
    	//$('.ui-layout-center').html(data); 
    }
	
	</script>
	
	<style type="text/css">
	body {
		background-color:#EEEFFF; 
		padding: 5px;
	}
	</style>
</head>

<body>
	<div style="BORDER-BOTTOM: #ccc 1px solid; MARGIN-BOTTOM: 10px"></div>
	<p>
	<a href="#">第一条 为保障煤矿安全生产和职工人身安全，防止煤矿事故，根据《煤炭法》、《矿山安全法》和《煤矿安全监察条例》，制定本规程。</a></p>
	
	<p><a>一、地面位置及标高 </a></p>
	<p><a>3302轨道顺槽开门口相对地面位置位于许厂煤矿自备电厂北院墙以北364m的农田里，杨家河南北穿过巷道，巷道布置方向与杨家河呈70°夹角，停头位置位于杨家河河堤以西837m处的农田里，施工区域相对地面位置均为农田及田间小路。</a><a>巷道掘进范围内地面标高在+39.8～+42.9m之间。 </a></p>
	<p><a>二、井下位置及标高</a></p>
	<p><a>3302轨道顺槽开门口位置位于330西翼采区轨道大巷内353导线点以北31m处巷道的西帮，按方位角311°布置，巷道停头位置西距F5断层为5m。</a><a>巷道底板标高在-317.32～-241.0m之间。</a><p>
	<p><a>三、四邻采掘情况</a></p>
	<p>
	<a>巷道施工区域除已施工的330西翼采区轨道大巷外，其余区域均未采掘。</a>
	</p>
	<div style="BORDER-BOTTOM: #ccc 1px solid; MARGIN-BOTTOM: 10px"></div>	
</body>
</html>
