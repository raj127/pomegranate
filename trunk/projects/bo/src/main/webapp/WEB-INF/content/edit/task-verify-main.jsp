<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>作业规程校验</title>
	<%@ include file="/common/meta.jsp" %>
	<link rel="stylesheet" type="text/css" href="${ctx}/js/temp/layout-default-1.3.0.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/js/temp/global.css" />
	<script type="text/javascript" src="${ctx}/js/temp/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/temp/jquery.textarea.js"></script>
	<script type="text/javascript" src="${ctx}/js/temp/jquery-ui-1.8.4.custom.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/temp/jquery.layout.min-1.3.0.js"></script>
	<%--############ --%>
	<script type="text/javascript" src="${ctx}/js/OfficeContorlFunctions.js"></script>
	<%--############ --%>
	<script language="javascript">
	$(document).ready(function(){
		//-----------------------
		//alert('$(window).height() ==> ' + $(window).height()); //浏览器当前窗口可视区域高度 
		//alert('$(document).height() ==> ' + $(document).height()); //浏览器当前窗口文档的高度 
		//alert('$(document.body).height() ==> ' + $(document.body).height());//浏览器当前窗口文档body的高度 
		//alert('$(document.body).outerHeight(true) ==> ' + $(document.body).outerHeight(true));//浏览器当前窗口文档body的总高度 包括border padding margin 
		//alert($(window).width()); //浏览器当前窗口可视区域宽度 
		//alert($(document).width());//浏览器当前窗口文档对象宽度 
		//alert($(document.body).width());//浏览器当前窗口文档body的高度 
		//alert($(document.body).outerWidth(true));//浏览器当前窗口文档body的总宽度 包括border padding margin 
		//-----------------------
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
	
	<script type="text/javascript">
	
	//提交请求
    function doSearch(selectedSentence){
		alert("doSearch...");
		alert(selectedSentence);
        var url = 'task-verify!search.action';
        var params = {sentence:selectedSentence};
        
        jQuery.post(url, params, callbackFun);
    }
    
    //回调函数
    function callbackFun(data){
    	alert(data);
    	alert($("#right",parent.document.body).contents().find("#result").text());
    	$("#right",parent.document.body).contents().find("#result").html(data);
    }
	
	</script>	
</head>

<body onload='intializePage("${ctx}/company/default/task/default/01-01.docx")' onunload="onPageClose()">
<!-- top bar -->
<div id="top-pane" class="ui-layout-north" style="OVERFLOW: hidden" >
<!--  
<iframe name="top"
        id="top"
        src="task-verify!top.action"
        height="20"
        width="100%" 
        marginHeight="0"
        frameBorder="0"
	    scrolling="no">
</iframe>
-->
<div class="top">
	<div class="left">
	  当前编辑的作业规程名称：${taskName} &nbsp;|&nbsp;
	当前编辑的章节：${chapterName}
	<!--  <a href="#" onclick="showServerMenu(this);return false;">工具1 </a>| 
	<a href="#" onclick="showServerMenu(this);return false;">工具2</a>--> 
	</div>
	<div class="right">
	<% if(!"anonymousUser".equals(SpringSecurityUtils.getCurrentUserName())){ %>
	当前用户： <%=SpringSecurityUtils.getCurrentUserName()%> | 
	<% } %>
	<a href="#" onclick="javascript:parent.window.opener=null;parent.window.close();">退出</a>
	<!--
	<a href="#" onclick="showServerMenu(this);return false;">工具3 </a>| 
	<a href="#" onclick="showServerMenu(this);return false;">工具4</a> 		
	<a href="#" onclick="showManuals(this);return false;">工具3 <span style="font-size: 11px">▼</span></a> | 
	<a href="#" onclick="showServerMenu(this);return false;">退出</a>-->  
	</div>
	<div class=clear></div>
</div>
	
</div>
<div id="left-pane" class="ui-layout-center"><!-- left bar -->
<%-- 
<iframe name="left"
        id="left"
        src="task-verify!left.action"
        height="100%"
        width="100%" 
        marginHeight="0"
        frameBorder="0"
	    scrolling="no">
</iframe>
--%>
	<form id="form1" method="post" action="upload.action" enctype="multipart/form-data">
	<input id="fileName" name="fileName" type="hidden" value="测试" />
	<input id="fileId" name="fileId" type="hidden" value="" />
		<script type="text/javascript" src="${ctx}/officecontrol/ntkoofficecontrol.js"></script>
	</form>
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

<script type="text/javascript" for="TANGER_OCX" event="OnDocumentClosed()">
	setFileOpenedOrClosed(false);
</script>

<script type="text/javascript" for="TANGER_OCX" event="OnDocumentOpened(TANGER_OCX_str,TANGER_OCX_obj)">
	//saved属性用来判断文档是否被修改过,文档打开的时候设置成ture,当文档被修改,自动被设置为false,该属性由office提供.
	OFFICE_CONTROL_OBJ.activeDocument.saved=true;
	
	//设置标题
	OFFICE_CONTROL_OBJ.Caption= "haha";
	
	//水平滚动条
	OFFICE_CONTROL_OBJ.ActiveDocument.ActiveWindow.DisplayHorizontalScrollBar =0; 
	//OFFICE_CONTROL_OBJ.ActiveDocument.ActiveWindow.DisplayHorizontalScrollBar;
	//垂直滚动条
	OFFICE_CONTROL_OBJ.ActiveDocument.ActiveWindow.DisplayVerticalScrollBar;
	//OFFICE_CONTROL_OBJ.ActiveDocument.ActiveWindow.DisplayVerticalScrollBar;

	
	//获取文档控件中打开的文档的文档类型
	switch (OFFICE_CONTROL_OBJ.doctype){
		case 1:
			fileType = "Word.Document";
			fileTypeSimple = "word";
			break;
		case 2:
			fileType = "Excel.Sheet";
			fileTypeSimple="excel";
			break;
		case 3:
			fileType = "PowerPoint.Show";
			fileTypeSimple = "ppt";
			break;
		case 4:
			fileType = "Visio.Drawing";
			break;
		case 5:
			fileType = "MSProject.Project";
			break;
		case 6:
			fileType = "WPS Doc";
			fileTypeSimple="wps";
			break;
		case 7:
			fileType = "Kingsoft Sheet";
			fileTypeSimple="et";
			break;
		default :
			fileType = "unkownfiletype";
			fileTypeSimple="unkownfiletype";
	}
	setFileOpenedOrClosed(true);
</script>

<script type="text/javascript" for="TANGER_OCX" event="BeforeOriginalMenuCommand(TANGER_OCX_str,TANGER_OCX_obj)">
	alert("BeforeOriginalMenuCommand事件被触发");
</script>

<script type="text/javascript" for="TANGER_OCX" event="OnFileCommand(TANGER_OCX_str,TANGER_OCX_obj)">
	if (TANGER_OCX_str == 3){
		alert("不能保存！");
		CancelLastCommand = true;
	}
</script>

<script type="text/javascript" for="TANGER_OCX" event="AfterPublishAsPDFToURL(result,code)">
	result=trim(result);
	alert(result);
	document.all("statusBar").innerHTML="服务器返回信息:"+result;
	if(result=="文档保存成功。")
	{window.close();}
</script>

<script type="text/javascript" for="TANGER_OCX" event="OnCustomMenuCmd2(menuPos,submenuPos,subsubmenuPos,menuCaption,menuID)">
	//alert("第" + menuPos +","+ submenuPos +","+ subsubmenuPos +"个菜单项,menuID="+menuID+",菜单标题为\""+menuCaption+"\"的命令被执行.");
	
	switch(menuID){
    	case 0:
    		var selection = OFFICE_CONTROL_OBJ.ActiveDocument.Application.Selection.Text;
    		alert(selection);
    		doSearch(selection);     		
     		break;
   		case 10:
   			alert("");
     	break;
   		case 20:
   			alert("");
     	break;
   		case 30:
   			alert("");
     	break;
   		case 31:
   			alert("");
     	break;
   		case 40:
   			//OFFICE_CONTROL_OBJ.ActiveDocument.Shapes.SelectAll();
   			alert("文字总数");
     		break;
   		case 41:
   			alert("句子总数");
     	break;
   		case 42:
   			alert("段落总数");
     	break;
   		case 43:
   			var doc = OFFICE_CONTROL_OBJ.ActiveDocument;
   			doc.Shapes.SelectAll();
   			var tt = doc.application.selection.InlineShapes.count;
   			alert("本文档图片总数 " + tt);
     	break;
   		case 44:
   			alert("表格总数");
     	break;
   		default:
   			alert("default");
   }

	if(menuID == 555){
		//alert("校验 begin {...");
		//OFFICE_CONTROL_OBJ.ActiveDocument.Application.Selection.GoTo(3,1,1,"");//第三个参数指定要查找的行号
		//OFFICE_CONTROL_OBJ.ActiveDocument.Application.Selection.MoveDown(5,1,1);
		//alert(OFFICE_CONTROL_OBJ.ActiveDocument.Application.Selection.Range.Text);
		//alert("校验 end ...}");
	}
	
</script>


