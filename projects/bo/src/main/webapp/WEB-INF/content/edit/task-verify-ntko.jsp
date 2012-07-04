<%@ page contentType="text/html;charset=UTF-8" %>
<script type="text/javascript" for="TANGER_OCX" event="OnDocumentClosed()">
	setFileOpenedOrClosed(false);
</script>

<script type="text/javascript" for="TANGER_OCX" event="OnDocumentOpened(TANGER_OCX_str,TANGER_OCX_obj)">
	//saved属性用来判断文档是否被修改过,文档打开的时候设置成ture,当文档被修改,自动被设置为false,该属性由office提供.
	OFFICE_CONTROL_OBJ.activeDocument.saved=true;
	
	//设置标题
	OFFICE_CONTROL_OBJ.Caption= "haha";
	
	//水平滚动条
	OFFICE_CONTROL_OBJ.ActiveDocument.ActiveWindow.DisplayHorizontalScrollBar; 
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


