﻿/*
 * --------------------------------------------------
 * 全局变量定义
 * --------------------------------------------------
 */
var OFFICE_CONTROL_OBJ;// 控件对象
var IsFileOpened;      // 控件是否打开文档
var fileType ;
var fileTypeSimple;

/*
 * --------------------------------------------------
 * 界面定制方法
 * --------------------------------------------------
 */

// 设置工具栏
function setToolBar(){
	OFFICE_CONTROL_OBJ.ToolBars=!OFFICE_CONTROL_OBJ.ToolBars;
}

// 控制是否显示所有菜单
function setMenubar(){
	OFFICE_CONTROL_OBJ.Menubar=!OFFICE_CONTROL_OBJ.Menubar;
}

// 控制”插入“菜单栏
function setInsertMemu(){
		OFFICE_CONTROL_OBJ.IsShowInsertMenu=!OFFICE_CONTROL_OBJ.IsShowInsertMenu;
}

// 控制”编辑“菜单栏
function setEditMenu(){
		OFFICE_CONTROL_OBJ.IsShowEditMenu=!OFFICE_CONTROL_OBJ.IsShowEditMenu;
}

// 控制”工具“菜单栏
function setToolMenu(){
	OFFICE_CONTROL_OBJ.IsShowToolMenu=!OFFICE_CONTROL_OBJ.IsShowToolMenu;
}
	
// 自定义菜单功能函数
function initCustomMenus(){
	var myobj = OFFICE_CONTROL_OBJ;
	myobj.AddCustomMenu2(0," 内容工具 ");
	myobj.AddCustomMenuItem2(0,0,-1,false,"校验本行",false,0);

	myobj.AddCustomMenu2(1," 图片工具 ");
	myobj.AddCustomMenuItem2(1,0,-1,false,"插入远程图片",false,10);
	
	myobj.AddCustomMenu2(2," 表格工具 ");
	myobj.AddCustomMenuItem2(2,0,-1,false,"插入表格",false,20);
	
	myobj.AddCustomMenu2(3," 公式 ");
	myobj.AddCustomMenuItem2(3,0,-1,false,"插入公式",false,30);
	
	myobj.AddCustomMenu2(4," 信息统计 ");
	myobj.AddCustomMenuItem2(4,0,-1,false,"文字总数",false,40);
	myobj.AddCustomMenuItem2(4,1,-1,false,"句子总数",false,41);
	myobj.AddCustomMenuItem2(4,2,-1,false,"段落总数",false,42);
	myobj.AddCustomMenuItem2(4,3,-1,false,"图片总数",false,43);
	myobj.AddCustomMenuItem2(4,4,-1,false,"表格总数",false,44);
	
	
//	for(var menuPos=0; menuPos<3; menuPos++){
//		myobj.AddCustomMenu2(menuPos,"菜单"+menuPos+"(&"+menuPos+")");
//		for(var submenuPos=0;submenuPos<10;submenuPos++){
//			if(1 ==(submenuPos % 3)){ // 主菜单增加分隔符。第3个参数是-1是在主菜单增加
//				myobj.AddCustomMenuItem2(menuPos,submenuPos,-1,false,"-",true);
//			}else if(0 == (submenuPos % 2)){ // 主菜单增加子菜单，第3个参数是-1是在主菜单增加
//				myobj.AddCustomMenuItem2(menuPos,submenuPos,-1,true,"子菜单"+menuPos+"-"+submenuPos,false);
//				// 增加子菜单项目
//				for(var subsubmenuPos=0;subsubmenuPos<9;subsubmenuPos++)
//				{
//					if(0 == (subsubmenuPos % 2))// 增加子菜单项目
//					{
//						myobj.AddCustomMenuItem2(menuPos,
//								                 submenuPos,
//								                 subsubmenuPos,
//								                 false,
//							                     "子菜单项目"+menuPos+"-"+submenuPos+"-"+subsubmenuPos,
//							                     false,
//							                     menuPos*100+submenuPos*20+subsubmenuPos);
//					}
//					else // 增加子菜单分隔
//					{
//						myobj.AddCustomMenuItem2(menuPos,submenuPos,subsubmenuPos,false,
//							"-"+subsubmenuPos,true);
//					}
//					// 测试禁用和启用
//					if(2 == (subsubmenuPos % 4))
//					{
//						myobj.EnableCustomMenuItem2(menuPos,submenuPos,subsubmenuPos,false);
//					}
//				}				
//			}else{ // 主菜单增加项目，第3个参数是-1是在主菜单增加
//			
//				myobj.AddCustomMenuItem2(menuPos,
//						                 submenuPos,
//						                 -1,
//						                 false,
//						                 "菜单项目"+menuPos+"-"+submenuPos,
//						                 false,
//						                 menuPos*100+submenuPos);
//			}
//			
//			// 测试禁用和启用
//			if(1 == (submenuPos % 4))
//			{
//				myobj.EnableCustomMenuItem2(menuPos,submenuPos,-1,false);
//			}
//		}
//	}
}

/*
 * --------------------------------------------------
 * 文档属性设置
 * --------------------------------------------------
 */
function SetReviewMode(boolvalue){
	if(OFFICE_CONTROL_OBJ.doctype==1)
	{
		OFFICE_CONTROL_OBJ.ActiveDocument.TrackRevisions = boolvalue;// 设置是否保留痕迹
	}
} 

function setShowRevisions(boolevalue){
	if(OFFICE_CONTROL_OBJ.doctype==1)
	{
		OFFICE_CONTROL_OBJ.ActiveDocument.ShowRevisions =boolevalue;// 设置是否显示痕迹
	}
}

function setFilePrint(boolvalue){
	OFFICE_CONTROL_OBJ.fileprint=boolvalue;// 是否允许打印
}

function setFileNew(boolvalue){
	OFFICE_CONTROL_OBJ.FileNew=boolvalue;// 是否允许新建
}

function setFileSaveAs(boolvalue){
	OFFICE_CONTROL_OBJ.FileSaveAs=boolvalue;// 是否允许另存为
}

function setIsNoCopy(boolvalue){
	OFFICE_CONTROL_OBJ.IsNoCopy=boolvalue;// 是否禁止粘贴
}

/*
 * --------------------------------------------------
 * 文档的打开及保存
 * --------------------------------------------------
 */

/**
 * 初始化word文档.
 */
function intializePage(fileUrl){
	 alert("begin intializePage { ...");
	// alert(document.all("TANGER_OCX"));
	OFFICE_CONTROL_OBJ = document.all("TANGER_OCX");
	//OFFICE_CONTROL_OBJ.ActiveDocument.Protect(3,false,"123456",false,true);
	initCustomMenus();
	NTKO_OCX_OpenDoc(fileUrl);
	//OFFICE_CONTROL_OBJ.ActiveDocument.Protect(3,false,"123456",false,true);
	 alert("end intializePage ...}");
}

/**
 * 页面关闭的时候调用该方法
 */
function onPageClose(){
	if(!OFFICE_CONTROL_OBJ.activeDocument.saved){
		if(confirm( "文档修改过,还没有保存,是否需要保存?")){
			saveFileToUrl();
		}
	}
}

/**
 * 从指定路径打开office文件.
 * @param fileUrl
 */
function NTKO_OCX_OpenDoc(fileUrl){
	OFFICE_CONTROL_OBJ.BeginOpenFromURL(fileUrl);
}

/**
 * 从指定路径打开word模板
 */
function openTemplateFileFromUrl(templateUrl){
	OFFICE_CONTROL_OBJ.openFromUrl(templateUrl);
}

/**
 * 将文件保存到指定路径,以原始格式.
 */
function saveFileToUrl(){
	//alert("begin save file { ...");
	var myUrl =document.forms[0].action ;
	//alert(myUrl);
	var fileName = document.all("fileName").value;
	var result  ;
	if(IsFileOpened){
		switch (OFFICE_CONTROL_OBJ.doctype){
			case 1:
				fileType = "Word.Document";
				break;
			case 2:
				fileType = "Excel.Sheet";
				break;
			case 3:
				fileType = "PowerPoint.Show";
				break;
			case 4:
				fileType = "Visio.Drawing";
				break;
			case 5:
				fileType = "MSProject.Project";
				break;
			case 6:
				fileType = "WPS Doc";
				break;
			case 7:
				fileType = "Kingsoft Sheet";
				break;
			default :
				fileType = "unkownfiletype";
		}
		//alert("fileType --> " + fileType);
		//alert("fileName --> " + fileName);
		result = OFFICE_CONTROL_OBJ.saveToURL(myUrl, // 提交到的url地址
											  "file", // 文件域的id，类似<input type=file id=upLoadFile 中的id
											  "fileType="+fileType, // 与控件一起提交的参数如："p1=a&p2=b&p3=c"
											  fileName, //上传文件的名称，类似<input type=file 的value
											  0 //与控件一起提交的表单id，也可以是form的序列号，这里应该是0.
											  );
		result=trim(result);
		// document.all("statusBar").innerHTML="服务器返回信息:"+result;
		alert(result);
		// window.close();
		alert("end save file ...}");
	}
}

/**
 * 将文档以HTML格式保存到服务器.
 * 该方法未测试.
 */
function saveFileAsHtmlToUrl(){
	var myUrl = "upLoadHtmlFile.jsp"	;
	var htmlFileName = document.all("fileName").value+".html";
	var result;
	if(IsFileOpened){
		result=OFFICE_CONTROL_OBJ.PublishAsHTMLToURL("upLoadHtmlFile.jsp","uploadHtml","htmlFileName="+htmlFileName,htmlFileName);
		result=trim(result);
		document.all("statusBar").innerHTML="服务器返回信息:"+result;
		alert(result);
		window.close();
	}
}

/**
 * 将文档以PDF格式保存到服务器.
 * 该方法未测试.
 */
function saveFileAsPdfToUrl(){
	var myUrl = "upLoadPdfFile.jsp"	;
	var pdfFileName = document.all("fileName").value+".pdf";
	if(IsFileOpened){
		OFFICE_CONTROL_OBJ.PublishAsPdfToURL(myUrl,"uploadPdf","PdfFileName="+pdfFileName,pdfFileName,"","",true,false);
	}
}

/**
 * 没看明白这个方法做什么用.
 * @param bool
 */
function setFileOpenedOrClosed(bool){
	IsFileOpened = bool;
	fileType = OFFICE_CONTROL_OBJ.DocType ;
}

/**
 * 一个测试方法.
 */
function testFunction(){
	alert(IsFileOpened);
}

/*
 * --------------------------------------------------
 * 书签函数定义
 * --------------------------------------------------
 */

/**
 * 获得所有的书签.
 */
function getAllBookmarks(){
	var doc = OFFICE_CONTROL_OBJ.ActiveDocument;
	var bks = doc.Bookmarks;
	var bksCount = bks.Count;
	for(i=1; i<=bksCount; i++){
		alert(bks(i).Name);
	}
}

/**
 * 保护文档部分修改.
 */
function protectDoc(){
	var marks=OFFICE_CONTROL_OBJ.ActiveDocument.Bookmarks;//获取所有的书签
	for(var i=1; i<=marks.Count; i++){
		OFFICE_CONTROL_OBJ.ActiveDocument.Bookmarks(marks(i).Name).Range.Select();//选取书签区域保护
		OFFICE_CONTROL_OBJ.ActiveDocument.Application.Selection.Editors.Add(-1);//增加可编辑区域
		//NTKO START
		OFFICE_CONTROL_OBJ.ActiveDocument.ActiveWindow.View.ShadeEditableRanges = true;
		OFFICE_CONTROL_OBJ.ActiveDocument.ActiveWindow.View.ShowBookmarks = true;
		//NTKO END
	} 
	if(OFFICE_CONTROL_OBJ.ActiveDocument.ProtectionType==-1){
		OFFICE_CONTROL_OBJ.ActiveDocument.Protect(3);//实现文档保护
	}
}

/**
 * 取消可编辑区域，保护文档，每个地方都不能修改.
 */
function unEditDoc(){
	OFFICE_CONTROL_OBJ.ActiveDocument.DeleteAllEditableRanges(-1)
	//增加可编辑区域
	//NTKO START
	OFFICE_CONTROL_OBJ.ActiveDocument.ActiveWindow.View.ShadeEditableRanges = false;
	OFFICE_CONTROL_OBJ.ActiveDocument.ActiveWindow.View.ShowBookmarks = false;
	//NTKO END 
	if(OFFICE_CONTROL_OBJ.ActiveDocument.ProtectionType==-1){
		OFFICE_CONTROL_OBJ.ActiveDocument.Protect(3);//实现文档保护
	}
}


/*
 * --------------------------------------------------
 * 工具类方法
 * --------------------------------------------------
 */
function trim(str){ // 删除左右两端的空格
	　　return str.replace(/(^\s*)|(\s*$)/g, "");
}

/*
 * --------------------------------------------------
 * 印章函数定义定义
 * 暂时未使用.
 * --------------------------------------------------
 */
function addServerSecSign(){
	var signUrl=document.all("secSignFileUrl").options[document.all("secSignFileUrl").selectedIndex].value;
	if(IsFileOpened){
		if(OFFICE_CONTROL_OBJ.doctype==1||OFFICE_CONTROL_OBJ.doctype==2)
		{
			try
			{
					OFFICE_CONTROL_OBJ.AddSecSignFromURL("ntko",signUrl);
			}
			catch(error){}
		}
		else
		{alert("不能在该类型文档中使用安全签名印章.");}
	}	
}

function addLocalSecSign(){
	if(IsFileOpened){
		if(OFFICE_CONTROL_OBJ.doctype==1||OFFICE_CONTROL_OBJ.doctype==2){
			try{
				OFFICE_CONTROL_OBJ.AddSecSignFromLocal("ntko","");
			}catch(error){}
		}
		else{
			alert("不能在该类型文档中使用安全签名印章.");
		}
	}	
}

function addEkeySecSign(){
	if(IsFileOpened)
	{
		if(OFFICE_CONTROL_OBJ.doctype==1||OFFICE_CONTROL_OBJ.doctype==2)
		{
			try
			{OFFICE_CONTROL_OBJ.AddSecSignFromEkey("ntko");}
			catch(error){}
		}
		else
		{alert("不能在该类型文档中使用安全签名印章.");}
	}
}

function addHandSecSign(){
	if(IsFileOpened)
	{
		if(OFFICE_CONTROL_OBJ.doctype==1||OFFICE_CONTROL_OBJ.doctype==2)
		{
			try
			{OFFICE_CONTROL_OBJ.AddSecHandSign("ntko");}
			catch(error){}
		}
		else
		{alert("不能在该类型文档中使用安全签名印章.");}
	}
}

function addServerSign(signUrl){
	if(IsFileOpened)
	{
			try
			{
				OFFICE_CONTROL_OBJ.AddSignFromURL("ntko",// 印章的用户名
				signUrl,// 印章所在服务器相对url
				100,// 左边距
				100,// 上边距 根据Relative的设定选择不同参照对象
				"ntko",// 调用DoCheckSign函数签名印章信息,用来验证印章的字符串
				3,  // Relative,取值1-4。设置左边距和上边距相对以下对象所在的位置 1：光标位置；2：页边距；3：页面距离
					// 4：默认设置栏，段落
				100,// 缩放印章,默认100%
				1);   // 0印章位于文字下方,1位于上方
				
			}
			catch(error){}
	}		
}

function addLocalSign(){
	if(IsFileOpened)
	{
			try
			{
				OFFICE_CONTROL_OBJ.AddSignFromLocal("ntko",// 印章的用户名
					"",// 缺省文件名
					true,// 是否提示选择
					100,// 左边距
					100,// 上边距 根据Relative的设定选择不同参照对象
					"ntko",// 调用DoCheckSign函数签名印章信息,用来验证印章的字符串
					3,  // Relative,取值1-4。设置左边距和上边距相对以下对象所在的位置
						// 1：光标位置；2：页边距；3：页面距离 4：默认设置栏，段落
					100,// 缩放印章,默认100%
					1);   // 0印章位于文字下方,1位于上方
			}
			catch(error){}
	}
}

function addPicFromUrl(picURL){
	if(IsFileOpened)
	{
		if(OFFICE_CONTROL_OBJ.doctype==1||OFFICE_CONTROL_OBJ.doctype==2)
		{
			try
			{
				OFFICE_CONTROL_OBJ.AddPicFromURL(picURL,// 图片的url地址可以时相对或者绝对地址
				false,// 是否浮动,此参数设置为false时,top和left无效
				100,// left 左边距
				100,// top 上边距 根据Relative的设定选择不同参照对象
				1,  // Relative,取值1-4。设置左边距和上边距相对以下对象所在的位置 1：光标位置；2：页边距；3：页面距离
					// 4：默认设置栏，段落
				100,// 缩放印章,默认100%
				1);   // 0印章位于文字下方,1位于上方
				
			}
			catch(error){}
		}
		else
		{alert("不能在该类型文档中使用安全签名印章.");}
	}		
}

function addPicFromLocal()
{
	if(IsFileOpened)
	{
			try
			{
				OFFICE_CONTROL_OBJ.AddPicFromLocal("",// 印章的用户名
					true,// 缺省文件名
					false,// 是否提示选择
					100,// 左边距
					100,// 上边距 根据Relative的设定选择不同参照对象
					1,  // Relative,取值1-4。设置左边距和上边距相对以下对象所在的位置
						// 1：光标位置；2：页边距；3：页面距离 4：默认设置栏，段落
					100,// 缩放印章,默认100%
					1);   // 0印章位于文字下方,1位于上方
			}
			catch(error){}
	}
}

function TANGER_OCX_AddDocHeader(strHeader){
	if(!IsFileOpened)
	{return;}
	var i,cNum = 30;
	var lineStr = "";
	try
	{
		for(i=0;i<cNum;i++) lineStr += "_";  // 生成下划线
		with(OFFICE_CONTROL_OBJ.ActiveDocument.Application)
		{
			Selection.HomeKey(6,0); // go home
			Selection.TypeText(strHeader);
			Selection.TypeParagraph(); 	// 换行
			Selection.TypeText(lineStr);  // 插入下划线
			// Selection.InsertSymbol(95,"",true); //插入下划线
			Selection.TypeText("★");
			Selection.TypeText(lineStr);  // 插入下划线
			Selection.TypeParagraph();
			// Selection.MoveUp(5, 2, 1); //上移两行，且按住Shift键，相当于选择两行
			Selection.HomeKey(6,1);  // 选择到文件头部所有文本
			Selection.ParagraphFormat.Alignment = 1; // 居中对齐
			with(Selection.Font)
			{
				NameFarEast = "宋体";
				Name = "宋体";
				Size = 12;
				Bold = false;
				Italic = false;
				Underline = 0;
				UnderlineColor = 0;
				StrikeThrough = false;
				DoubleStrikeThrough = false;
				Outline = false;
				Emboss = false;
				Shadow = false;
				Hidden = false;
				SmallCaps = false;
				AllCaps = false;
				Color = 255;
				Engrave = false;
				Superscript = false;
				Subscript = false;
				Spacing = 0;
				Scaling = 100;
				Position = 0;
				Kerning = 0;
				Animation = 0;
				DisableCharacterSpaceGrid = false;
				EmphasisMark = 0;
			}
			Selection.MoveDown(5, 3, 0); // 下移3行
		}
	}
	catch(err){
		alert("错误：" + err.number + ":" + err.description);
	}
	finally{
	}
}

function insertRedHeadFromUrl(headFileURL){
	if(OFFICE_CONTROL_OBJ.doctype!=1)// OFFICE_CONTROL_OBJ.doctype=1为word文档
	{return;}
	OFFICE_CONTROL_OBJ.ActiveDocument.Application.Selection.HomeKey(6,0);// 光标移动到文档开头
	OFFICE_CONTROL_OBJ.addtemplatefromurl(headFileURL);// 在光标位置插入红头文档
}


function doHandSign(){
	/*
	 * if(OFFICE_CONTROL_OBJ.doctype==1||OFFICE_CONTROL_OBJ.doctype==2)//此处设置只允许在word和excel中盖章.doctype=1是"word"文档,doctype=2是"excel"文档 {
	 */
		OFFICE_CONTROL_OBJ.DoHandSign2(
									"ntko",// 手写签名用户名称
									"ntko",// signkey,DoCheckSign(检查印章函数)需要的验证密钥。
									0,// left
									0,// top
									1,// relative,设定签名位置的参照对象.0：表示按照屏幕位置插入，此时，Left,Top属性不起作用。1：光标位置；2：页边距；3：页面距离
										// 4：默认设置栏，段落（为兼容以前版本默认方式）
									100);
	// }
}


// 验证文档控件自带印章功能盖的章
function DoCheckSign(){
   if(IsFileOpened)
   {	
			var ret = OFFICE_CONTROL_OBJ.DoCheckSign
			(
			false,/* 可选参数 IsSilent 缺省为FAlSE，表示弹出验证对话框,否则，只是返回验证结果到返回值 */
			"ntko"// 使用盖章时的signkey,这里为"ntko"
			);// 返回值，验证结果字符串
			// alert(ret);
   }	
}


