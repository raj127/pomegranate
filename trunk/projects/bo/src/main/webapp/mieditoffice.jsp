<%@ page contentType="text/html;charset=utf-8" %>

<%
	String filetype=""; //文件类型
	String fileId=""; //文件ID
	String fileName=""; //文件名称
	String fileUrl=""; //文件链接
	String attachFileName="";
	String attachFileDescribe="";
	String attachFileUrl="";
	String templateFileUrl="templateFile/";//新建文档模板url
	String otherData="";
	
	filetype="word";
	fileName="新建word文档.doc";
	templateFileUrl=templateFileUrl+"newWordTemplate.doc";
	fileUrl=templateFileUrl;//如果是新文档，控件打开新建模板文档
	
	System.out.println("filetype -->" + filetype);
	System.out.println("fileId -->" + fileId);
	System.out.println("fileName -->" + fileName);
	System.out.println("fileUrl -->" + fileUrl);
	System.out.println("attachFileName -->" + attachFileName);
	System.out.println("attachFileDescribe -->" + attachFileDescribe);
	System.out.println("attachFileUrl -->" + attachFileUrl);
	System.out.println("templateFileUrl -->" + templateFileUrl);
	System.out.println("otherData -->" + otherData);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html  xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>ntko office文档控件示例-ms office文档编辑</title>
	<meta content="IE=7" http-equiv="X-UA-Compatible" /> 
	<!--设置缓存-->
	<meta http-equiv="cache-control" content="no-cache,must-revalidate"/>
	<meta http-equiv="pragram" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
	<link href="StyleSheet.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="OfficeContorlFunctions.js"></script>
</head>
<body onload='intializePage("<%=fileUrl%>")' onbeforeunload ="onPageClose()">
<form id="form1" action="upLoadOfficeFile.jsp" enctype="multipart/form-data" style="padding:0px;margin:0px;">
	<div id="editmain_top" class="editmain_top">
	<div id="edit_button_div" class="edit_button_div">
	<img alt="保存office文档" src="images/edit_save_office.gif" onclick="saveFileToUrl();" />
	<img alt="保存html文档" src="images/edit_save_html.gif" onclick="saveFileAsHtmlToUrl();"/>
	<img alt="保存PDF" src="images/edit_save_pdf.gif" onclick="saveFileAsPdfToUrl();"/>
	</div>
	</div>
	<div id="formtop">
	<table>
		<tr><td colspan="5"  class="edit_tabletitle">文件表单数据:</td></tr>
		<tr><td colspan="5">&nbsp;</td></tr>
		<tr>
		<td width="7%"> 文&nbsp;件&nbsp;ID:</td>
		<td width="20%"><input name="fileId" id="fileId" readOnly  type="text" value="<%=fileId%>" /></td>
		<td width="8%">文件名称:</td>
		<td width="40%"><input name="filename" id="filename" type="text" value="<%=fileName%>" /></td>
		<td>&nbsp;</td>
		</tr>
	</table>
	</div>                
	<div id="officecontrol">
	<script type="text/javascript" src="officecontrol/ntkoofficecontrol.js"></script>
	<div id="statusBar" style="height:20px;width:100%;background-color:#c0c0c0;font-size:12px;"></div>
	<script language="JScript" for=TANGER_OCX event="OnDocumentClosed()">
	setFileOpenedOrClosed(false);
	</script>
	<script language="JScript" for="TANGER_OCX" event="OnDocumentOpened(TANGER_OCX_str,TANGER_OCX_obj)">
	//saved属性用来判断文档是否被修改过,文档打开的时候设置成ture,当文档被修改,自动被设置为false,该属性由office提供.
	OFFICE_CONTROL_OBJ.activeDocument.saved=true;
	
	//获取文档控件中打开的文档的文档类型
	switch (OFFICE_CONTROL_OBJ.doctype){
	case 1:
	fileType = "Word.Document";
	fileTypeSimple = "wrod";
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
	alert("fileType --> " + fileType);
	setFileOpenedOrClosed(true);
	</script>
	<script language="JScript" for=TANGER_OCX event="BeforeOriginalMenuCommand(TANGER_OCX_str,TANGER_OCX_obj)">
	alert("BeforeOriginalMenuCommand事件被触发");
	</script>
	<script language="JScript" for=TANGER_OCX event="OnFileCommand(TANGER_OCX_str,TANGER_OCX_obj)">
	if (TANGER_OCX_str == 3) 
	{
	alert("不能保存！");
	CancelLastCommand = true;
	}
	</script>
	<script language="JScript" for=TANGER_OCX event="AfterPublishAsPDFToURL(result,code)">
	result=trim(result);
	alert(result);
	document.all("statusBar").innerHTML="服务器返回信息:"+result;
	if(result=="文档保存成功。")
	{window.close();}
	</script>
	<script language="JScript" for=TANGER_OCX event="OnCustomMenuCmd2(menuPos,submenuPos,subsubmenuPos,menuCaption,menuID)">
	alert("第" + menuPos +","+ submenuPos +","+ subsubmenuPos +"个菜单项,menuID="+menuID+",菜单标题为\""+menuCaption+"\"的命令被执行.");
	</script>
	</div><!--officecontrol-->
</form>
</body>
</html>
