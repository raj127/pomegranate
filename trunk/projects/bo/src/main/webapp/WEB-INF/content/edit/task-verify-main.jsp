<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>作业规程校验</title>
	<%@ include file="/common/meta.jsp" %>
	<script type="text/javascript" src="${ctx}/js/temp/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/temp/jquery.textarea.js"></script>
	<script type="text/javascript" src="${ctx}/js/temp/jquery-ui-1.8.4.custom.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/temp/jquery.layout.min-1.3.0.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/js/temp/layout-default-1.3.0.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/js/temp/global.css" />

	<script language="javascript">
	$(document).ready(function(){
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
	
	/** show manual links **/
	function setManualPosition(className, x, y) {
	    if ($(className).is(":visible")) {
	        $(className).hide();
	    }
	    else {
	        window.setTimeout(function () {
	            $(className).show();
	            $(className).css("left", x);
	            $(className).css("top", y)
	        }, 100);
	        $(className).find("a").click(function () {
	            hideMenus();
	        });
	    }
	}
	
	/** hide menus **/
	function hideMenus() {
	    $(".manual").hide();
	    //$(".server-menu").hide();
	}
	
	function test(){
		alert("main");
	}
	</script>
</head>

<body>
<!-- top bar -->
<div id="top-pane" class="ui-layout-north" style="OVERFLOW: hidden" >
<iframe name="top"
        id="top"
        src="task-verify!top.action"
        height="20"
        width="100%" 
        marginHeight="0"
        frameBorder="0"
	    scrolling="no">
</iframe>
</div>
<div id="left-pane" class="ui-layout-center"><!-- left bar -->
<iframe name="left"
        id="left"
        src="task-verify!left.action"
        height="100%"
        width="100%" 
        marginHeight="0"
        frameBorder="0"
	    scrolling="no">
</iframe>
</div>
<div id="right-pane" class="ui-layout-east"><!-- right bar -->
<iframe name="right"
        id="right"
        src="task-verify!right.action"
        height="100%"
        width="100%" 
        marginHeight="0"
        frameBorder="0"
	    scrolling="no">
</iframe>
</div>
<!-- quick links -->
<div class="manual">
<a href="#" target=_blank>Querying</a><br/>
<a href="#" target=_blank>Updating</a><br/>
</div>
</body>
</html>