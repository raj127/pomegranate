<%--
Description:模板管理 --》 模板编辑页面
author:darkmi
date:2012/08/21
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html  xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>作业规程编辑</title>
	<meta http-equiv="cache-control" content="no-cache,must-revalidate"/><!--设置缓存-->
	<meta http-equiv="pragram" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
	<link href="${ctx}/css/edit.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/css/edit.office.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctx}/js/OfficeContorlFunctions.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.layout.js"></script>
	<script type="text/javascript">
	$(document).ready(function () {
		$('body').layout({ applyDefaultStyles: true });
	});

	</script>	
</head>
<body onload='intializePage("${ctx}${filePath}")' onunload="onPageClose()">
<div class="ui-layout-center">
	<form id="form1" method="post" action="template-chapter-upload!upload.action" enctype="multipart/form-data">
	<input id="fileName" name="fileName" type="hidden" value="${fileName}" />
	<input id="fileId" name="fileId" type="hidden" value="" />

		<div id="officecontrol">
			<script type="text/javascript" src="${ctx}/officecontrol/ntkoofficecontrol.js"></script>
			<script type="text/javascript" for="TANGER_OCX" event="OnDocumentClosed()">
				setFileOpenedOrClosed(false);
			</script>
			<script type="text/javascript" for="TANGER_OCX" event="OnDocumentOpened(TANGER_OCX_str,TANGER_OCX_obj)">
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
				alert("第" + menuPos +","+ submenuPos +","+ subsubmenuPos +"个菜单项,menuID="+menuID+",菜单标题为\""+menuCaption+"\"的命令被执行.");
			</script>
		</div><!--officecontrol-->
	</form>
</div>
<div class="ui-layout-east">
	<table class="TableBlock" width="100%" align="center">
		<tr class="TableHeader">
			<td align="center">文件操作</td>
		</tr>
		<tr class="TableData" onclick="saveFileToUrl();" style="cursor: hand; line-height: 20px;">
			<td align="center">保存文件</td>
		</tr>
		<tr class="TableData" onclick="saveFileAsHtmlToUrl();" style="cursor: hand; line-height: 20px;">
			<td align="center">保存HTML文件</td>
		</tr>
		<tr class="TableData" onclick="saveFileAsPdfToUrl();" style="cursor: hand; line-height: 20px;">
			<td align="center">保存PDF文件</td>
		</tr>			
		<tr class="TableData" onclick="ShowHistory()" style="cursor: hand; line-height: 20px;">
			<td align="center">历史版本</td>
		</tr>
		<tr class="TableData" onclick="TANGER_OCX_ChgLayout()" style="cursor: hand; line-height: 20px;">
			<td align="center">页面设置</td>
		</tr>
		<tr class="TableData" onclick="TANGER_OCX_PrintDoc()" style="cursor: hand; line-height: 20px;">
			<td align="center">打印</td>
		</tr>
		<tr class="TableData" onclick="ShowLog()"
			style="cursor: hand; line-height: 20px;">
			<td align="center">操作日志</td>
		</tr>
		<tr class="TableHeader">
			<td align="center">文件编辑</td>
		</tr>

		<tr onclick="MY_SetMarkModify(true)"
			style="cursor: hand; line-height: 20px;">
			<td class="TableControl" align="center" id="mflag1">保留痕迹
			</td>
		</tr>
		<tr onclick="MY_SetMarkModify(false)"
			style="cursor: hand; line-height: 20px;">
			<td class="TableData" align="center" id="mflag2">不留痕迹</td>
		</tr>
		<tr onclick="MY_ShowRevisions(true)"
			style="cursor: hand; line-height: 20px;">
			<td class="TableControl" align="center" id="sflag1">显示痕迹</td>
		</tr>
		<tr onclick="MY_ShowRevisions(false)"
			style="cursor: hand; line-height: 20px;">
			<td class="TableData" align="center" id="sflag2">隐藏痕迹</td>
		</tr>
		<tr onclick="return selectword('allow_save');"
			style="cursor: hand; line-height: 20px;">
			<td class="TableData" align="center">文件套红</td>
		</tr>
		<tr onclick="AddPictureFromLocal()"
			style="cursor: hand; line-height: 20px;">
			<td class="TableData" align="center">插入图片</td>
		</tr>
		<tr class="TableHeader">
			<td align="center">电子认证</td>
		</tr>
		<tr class="TableData" onclick="DoCheckSign('3601701659')"
			style="cursor: hand; line-height: 20px;">
			<td align="center">验证签名及印章</td>
		</tr>
		<tr class="TableData" onclick="DoHandSign2('3601701659')"
			style="cursor: hand; line-height: 20px;">
			<td align="center">全屏手写签名</td>
		</tr>
		<tr class="TableData" onclick="DoHandDraw2('3601701659')"
			style="cursor: hand; line-height: 20px;">
			<td align="center">全屏手工绘图</td>
		</tr>
		<tr class="TableData" onclick="DoHandSign('3601701659')"
			style="cursor: hand; line-height: 20px;">
			<td align="center">插入手写签名</td>
		</tr>
		<tr class="TableData" onclick="DoHandDraw()"
			style="cursor: hand; line-height: 20px;">
			<td align="center">插入手工绘图</td>
		</tr>
	</table>
</div>
</body>
</html>
