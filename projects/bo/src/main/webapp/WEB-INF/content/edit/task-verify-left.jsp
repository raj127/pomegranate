<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>作业规程校验</title>
	<%@ include file="/common/meta.jsp" %>
	<link href="${ctx}/css/edit.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/css/edit.office.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/js/temp/global.css" media="all">
	<script type="text/javascript" src="${ctx}/js/temp/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/temp/jquery.textarea.js"></script>
	<script type="text/javascript" src="${ctx}/js/OfficeContorlFunctions.js"></script>
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
    	//alert($("#right",parent.document.body).contents().find("#result").text());
    	$("#right",parent.document.body).contents().find("#result").html(data);
    }
	
	</script>
	
	<style type="text/css">
	body {
		background-color:#EEEFFF; 
		padding: 5px;
	}
	</style>
</head>

<body onload='intializePage("${ctx}/company/default/task/default/01-01.docx")' onunload="onPageClose()">
	<form id="form1" method="post" action="upload.action" enctype="multipart/form-data">
	<input id="fileName" name="fileName" type="hidden" value="测试" />
	<input id="fileId" name="fileId" type="hidden" value="" />
	<div id="officecontrol">
		<script type="text/javascript" src="${ctx}/officecontrol/ntkoofficecontrol.js"></script>
	</div><!--officecontrol-->
	</form>

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
	OFFICE_CONTROL_OBJ.ActiveDocument.ActiveWindow.DisplayVerticalScrollBar =0;
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
	if(menuID == 555){
		//alert("校验 begin {...");
		//OFFICE_CONTROL_OBJ.ActiveDocument.Application.Selection.GoTo(3,1,1,"");//第三个参数指定要查找的行号
		//OFFICE_CONTROL_OBJ.ActiveDocument.Application.Selection.MoveDown(5,1,1);
		//alert(OFFICE_CONTROL_OBJ.ActiveDocument.Application.Selection.Range.Text);
		var selection = OFFICE_CONTROL_OBJ.ActiveDocument.Application.Selection.Text 
		alert(selection);
		doSearch(selection);
		//alert("校验 end ...}");
	}
	
</script>


