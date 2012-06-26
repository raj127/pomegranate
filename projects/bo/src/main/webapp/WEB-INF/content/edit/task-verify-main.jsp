<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>作业规程校验</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
<meta http-equiv="Cache-Control" content="no-store"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<script type="text/javascript" src="${ctx}/js/temp/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/temp/jquery.textarea.js"></script>
<script type="text/javascript" src="${ctx}/js/temp/jquery-ui-1.8.4.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/js/temp/jquery.layout.min-1.3.0.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/js/temp/layout-default-1.3.0.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/js/temp/global.css" />
<style type="text/css">
* {
	FONT-FAMILY: 'Courier New', Arial; FONT-SIZE: 12px
}
BODY {
	background-color:#FFFFFF;
	PADDING-BOTTOM: 10px; 
	MARGIN: 0px; 
	PADDING-LEFT: 10px; 
	PADDING-RIGHT: 10px; 
	PADDING-TOP: 10px
}
A {
	LINE-HEIGHT: 1.5; 
	COLOR: #004499; 
	TEXT-DECORATION: none
}
.manual {
	Z-INDEX: 10000; 
	BORDER-BOTTOM: #ccc 2px solid; 
	POSITION: absolute; 
	BORDER-LEFT: #ccc 1px solid; 
	BACKGROUND-COLOR: #eee; 
	MARGIN-TOP: 0px; PADDING-LEFT: 3px; 
	WIDTH: 100px; 
	DISPLAY: none; 
	FLOAT: right; 
	BORDER-TOP: #ccc 1px solid; 
	MARGIN-RIGHT: 100px; 
	BORDER-RIGHT: #ccc 2px solid
}
.server-menu {
	Z-INDEX: 10000; 
	BORDER-BOTTOM: #ccc 2px solid; 
	POSITION: absolute; 
	BORDER-LEFT: #ccc 1px solid; 
	BACKGROUND-COLOR: #eee; 
	MARGIN-TOP: 0px; 
	PADDING-LEFT: 3px; 
	WIDTH: 100px; 
	DISPLAY: none; 
	FLOAT: right; 
	BORDER-TOP: #ccc 1px solid; 
	MARGIN-RIGHT: 100px; BORDER-RIGHT: #ccc 2px solid
}
#right-pane {
	PADDING-LEFT: 10px
}
</style>

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
        west: {
            minSize: wsize,
            maxSize: 4*wsize,
            size: 3*wsize,
            resizable:true,
            closable: false,
            slidable: false
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
    $(".server-menu").hide();
}
</script>
</head>

<body>
<!-- top bar -->
<div id="top-pane" class="ui-layout-north" style="OVERFLOW: hidden" >
<iframe name="top"
        src="task-verify!top.action"
        height="20"
        width="100%" 
        marginHeight="0"
        frameBorder="0"
	    scrolling="no">
</iframe>
</div>
<div id="left-pane" class="ui-layout-west"><!-- left bar -->
<iframe name="left"
        src="task-verify!left.action"
        height="100%"
        width="100%" 
        marginHeight="0"
        frameBorder="0"
	    scrolling="no">
</iframe>
</div>
<div id="right-pane" class="ui-layout-center"><!-- right bar -->
<iframe name="right"
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
