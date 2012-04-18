<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html  xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>作业规程编辑</title>
	<meta content="IE=7" http-equiv="X-UA-Compatible" /> 
	<meta http-equiv="cache-control" content="no-cache,must-revalidate"/><!--设置缓存-->
	<meta http-equiv="pragram" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
	<link href="${ctx}/css/edit.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctx}/js/OfficeContorlFunctions.js"></script>
</head>
<body onload='intializePage("${ctx}/company/default/task/default/01-01.docx")' onunload="onPageClose()">
	<form id="form1" method="post" action="upload.action" enctype="multipart/form-data">
		<div id="editmain_top" class="editmain_top">
			<div id="edit_button_div" class="edit_button_div">
				<img alt="保存office文档" src="${ctx}/images/edit_save_office.gif" onclick="saveFileToUrl();" />
				<img alt="保存html文档" src="${ctx}/images/edit_save_html.gif" onclick="saveFileAsHtmlToUrl();"/>
				<img alt="保存PDF" src="${ctx}/images/edit_save_pdf.gif" onclick="saveFileAsPdfToUrl();"/>
			</div>
		</div>
		<div id="formtop">
			<table>
				<tr><td colspan="5"  class="edit_tabletitle">文件表单数据:</td></tr>
				<tr><td colspan="5">&nbsp;</td></tr>
				<tr>
				<td width="7%"> 文&nbsp;件&nbsp;ID:</td>
				<td width="20%"><input id="fileId" name="fileId" type="text" value="" /></td>
				<td width="8%">文件名称:</td>
				<td width="40%"><input id="fileName" name="fileName" type="text" value="测试" /></td>
				<td>&nbsp;</td>
				</tr>
			</table>
		</div>
		              
		<div id="officecontrol">
			<!--<div id="statusBar" style="height:20px;width:100%;background-color:#c0c0c0;font-size:12px;"></div>-->
			<script type="text/javascript" src="${ctx}/officecontrol/ntkoofficecontrol.js"></script>
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
			<script language="JScript" for=TANGER_OCX event="BeforeOriginalMenuCommand(TANGER_OCX_str,TANGER_OCX_obj)">
				alert("BeforeOriginalMenuCommand事件被触发");
			</script>
			<script language="JScript" for=TANGER_OCX event="OnFileCommand(TANGER_OCX_str,TANGER_OCX_obj)">
				if (TANGER_OCX_str == 3){
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
