package com.darkmi.jacob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class MSWordManager {
	private Logger logger = LoggerFactory.getLogger(MSWordManager.class);
	private Dispatch doc; // word文档
	private ActiveXComponent word; // word运行程序对象 
	private Dispatch documents;// 所有word文档集合
	private Dispatch selection; // 选定的范围或插入点
	private boolean saveOnExit = false;

	/**
	 * 构造函数.
	 * @param visible 为true表示word应用程序可见 
	 */
	public MSWordManager(boolean visible) {
		logger.debug("MSWordManager begin { ... ");
		if (word == null) {
			word = new ActiveXComponent("Word.Application");
			word.setProperty("Visible", new Variant(visible));
		}
		if (documents == null) {
			documents = word.getProperty("Documents").toDispatch();
		}
		logger.debug("MSWordManager begin ... }");
	}

	/**
	 * 新建一个空白文档.
	 */
	public void createNewDocument() {
		doc = Dispatch.call(documents, "Add").toDispatch();
		selection = Dispatch.get(word, "Selection").toDispatch();
	}

	/** 
	* 打开一个已存在的文档 
	*　　　　 
	* @param docPath 
	*/
	public void openDocument(String docPath) {
		//首先需要检测文件是否存在

		//检测是否为word文件

		closeDocument();
		doc = Dispatch.call(documents, "Open", docPath).toDispatch();
		selection = Dispatch.get(word, "Selection").toDispatch();
	}

	/*~~~~~~~~~~~~ 光标操作方法 ~~~~~~~~~~~~~~~~~*/

	/** 
	* 把选定的内容或插入点向上移动 
	*　　　　 
	* @param pos 移动的距离 
	*/
	public void moveUp(int pos) {
		if (selection == null) {
			selection = Dispatch.get(word, "Selection").toDispatch();
		}
		for (int i = 0; i < pos; i++) {
			Dispatch.call(selection, "MoveUp");
		}
	}

	/** 
	* 把选定的内容或者插入点向下移动 
	*　　　　 
	* @param pos 移动的距离 
	*/
	public void moveDown(int pos) {
		if (selection == null)
			selection = Dispatch.get(word, "Selection").toDispatch();
		for (int i = 0; i < pos; i++)
			Dispatch.call(selection, "MoveDown");
	}

	/** 
	* 把选定的内容或者插入点向左移动 
	*　　　　 
	* @param pos 移动的距离 
	*/
	public void moveLeft(int pos) {
		if (selection == null)
			selection = Dispatch.get(word, "Selection").toDispatch();
		for (int i = 0; i < pos; i++) {
			Dispatch.call(selection, "MoveLeft");
		}
	}

	/** 
	* 把选定的内容或者插入点向右移动 
	*　　　　 
	* @param pos 移动的距离 
	*/
	public void moveRight(int pos) {
		if (selection == null)
			selection = Dispatch.get(word, "Selection").toDispatch();
		for (int i = 0; i < pos; i++)
			Dispatch.call(selection, "MoveRight");
	}

	/** 
	* 把插入点移动到文件首位置 
	*　　　　 
	*/
	public void moveStart() {
		if (selection == null)
			selection = Dispatch.get(word, "Selection").toDispatch();
		Dispatch.call(selection, "HomeKey", new Variant(6));
	}

	public void moveEnd() {
		if (selection == null)
			selection = Dispatch.get(word, "Selection").toDispatch();
		Dispatch.call(selection, "EndKey", new Variant(6));
	}

	/** 
	* 从选定内容或插入点开始查找文本 
	*　　　　 
	* @param toFindText 要查找的文本 
	* @return boolean true-查找到并选中该文本，false-未查找到文本 
	*/
	public boolean find(String toFindText) {
		if (toFindText == null || toFindText.equals(""))
			return false;
		// 从selection所在位置开始查询 
		@SuppressWarnings("static-access")
		Dispatch find = word.call(selection, "Find").toDispatch();
		// 设置要查找的内容 
		Dispatch.put(find, "Text", toFindText);
		// 向前查找 
		Dispatch.put(find, "Forward", "True");
		// 设置格式 
		Dispatch.put(find, "Format", "True");
		// 大小写匹配 
		Dispatch.put(find, "MatchCase", "True");
		// 全字匹配 
		Dispatch.put(find, "MatchWholeWord", "True");
		// 查找并选中 
		return Dispatch.call(find, "Execute").getBoolean();
	}

	/** 
	* 把选定选定内容设定为替换文本 
	*　　　　 
	* @param toFindText 查找字符串 
	* @param newText 要替换的内容 
	* @return 
	*/
	public boolean replaceText(String toFindText, String newText) {
		if (!find(toFindText))
			return false;
		Dispatch.put(selection, "Text", newText);
		return true;
	}

	/** 
	* 全局替换文本 
	*　　　　 
	* @param toFindText 查找字符串 
	* @param newText 要替换的内容 
	*/
	public void replaceAllText(String toFindText, String newText) {
		while (find(toFindText)) {
			Dispatch.put(selection, "Text", newText);
			Dispatch.call(selection, "MoveRight");
		}
	}

	/** 
	* 在当前插入点插入字符串 
	*　　　　 
	* @param newText 要插入的新字符串 
	*/
	public void insertText(String newText) {
		Dispatch.put(selection, "Text", newText);
	}

	/*~~~~~~~~~~~~ 图片操作方法 ~~~~~~~~~~~~~~~~~*/

	/** 
	*　　　　 
	* @param toFindText 要查找的字符串 
	* @param imagePath 图片路径 
	* @return 
	*/
	public boolean replaceImage(String toFindText, String imagePath) {
		if (!find(toFindText))
			return false;
		Dispatch.call(Dispatch.get(selection, "InLineShapes").toDispatch(), "AddPicture", imagePath);
		return true;
	}

	/** 
	* 全局替换图片 
	*　　　　 
	* @param toFindText 查找字符串 
	* @param imagePath 图片路径 
	*/
	public void replaceAllImage(String toFindText, String imagePath) {
		while (find(toFindText)) {
			Dispatch.call(Dispatch.get(selection, "InLineShapes").toDispatch(), "AddPicture", imagePath);
			Dispatch.call(selection, "MoveRight");
		}
	}

	/** 
	* 在当前插入点插入图片 
	*　　　　 
	* @param imagePath 图片路径 
	*/
	public void insertImage(String imagePath) {
		Dispatch.call(Dispatch.get(selection, "InLineShapes").toDispatch(), "AddPicture", imagePath);
	}

	/*~~~~~~~~~~~~ 表格操作方法 ~~~~~~~~~~~~~~~~~*/

	/** 
	* 合并单元格 
	*　　　　 
	* @param tableIndex 
	* @param fstCellRowIdx 
	* @param fstCellColIdx 
	* @param secCellRowIdx 
	* @param secCellColIdx 
	*/
	public void mergeCell(int tableIndex, int fstCellRowIdx, int fstCellColIdx, int secCellRowIdx, int secCellColIdx) {
		// 所有表格 
		Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
		// 要填充的表格 
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
		Dispatch fstCell = Dispatch.call(table, "Cell", new Variant(fstCellRowIdx), new Variant(fstCellColIdx))
				.toDispatch();
		Dispatch secCell = Dispatch.call(table, "Cell", new Variant(secCellRowIdx), new Variant(secCellColIdx))
				.toDispatch();
		Dispatch.call(fstCell, "Merge", secCell);
	}

	/** 
	* 在指定的单元格里填写数据 
	*　　　　 
	* @param tableIndex 
	* @param cellRowIdx 
	* @param cellColIdx 
	* @param txt 
	*/
	public void putTxtToCell(int tableIndex, int cellRowIdx, int cellColIdx, String txt) {
		// 所有表格 
		Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
		// 要填充的表格 
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
		Dispatch cell = Dispatch.call(table, "Cell", new Variant(cellRowIdx), new Variant(cellColIdx)).toDispatch();
		Dispatch.call(cell, "Select");
		Dispatch.put(selection, "Text", txt);
	}

	/** 
	* 在当前文档拷贝数据 
	*　　　　 
	* @param pos 
	*/
	public void copy(String toCopyText) {
		moveStart();
		if (this.find(toCopyText)) {
			Dispatch textRange = Dispatch.get(selection, "Range").toDispatch();
			Dispatch.call(textRange, "Copy");
		}
	}
	
	/**
	 * 在当前文档复制图像.
	 */
	public void copyImage(){
		
	}
	
	/** 
	* 在当前文档指定的位置拷贝表格
	* @param pos 当前文档指定的位置
	* @param tableIndex 被拷贝的表格在word文档中所处的位置 
	*/
	public void copyTable(String pos, int tableIndex) {
		Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
		Dispatch range = Dispatch.get(table, "Range").toDispatch();
		Dispatch.call(range, "Copy");
		if (this.find(pos)) {
			Dispatch textRange = Dispatch.get(selection, "Range").toDispatch();
			Dispatch.call(textRange, "Paste");
		}
	}

	/** 
	* 在当前文档粘帖剪贴板数据 
	*　　　　 
	* @param pos 
	*/
	public void paste(String pos) {
		moveStart();
		if (this.find(pos)) {
			Dispatch textRange = Dispatch.get(selection, "Range").toDispatch();
			Dispatch.call(textRange, "Paste");
		}
	}



	/** 
	* 在当前文档末尾拷贝来自另一个文档中的段落 
	*　　　　 
	* @param anotherDocPath 另一个文档的磁盘路径 
	* @param tableIndex 被拷贝的段落在另一格文档中的序号(从1开始) 
	*/
	public void copyParagraphFromAnotherDoc(String anotherDocPath, int paragraphIndex) {
		Dispatch wordContent = Dispatch.get(doc, "Content").toDispatch(); // 取得当前文档的内容 
		Dispatch.call(wordContent, "InsertAfter", "$selection$");// 插入特殊符定位插入点 
		copyParagraphFromAnotherDoc(anotherDocPath, paragraphIndex, "$selection$");
	}

	/** 
	* 在当前文档指定的位置拷贝来自另一个文档中的段落 
	*　　　　 
	* @param anotherDocPath 另一个文档的磁盘路径 
	* @param tableIndex 被拷贝的段落在另一格文档中的序号(从1开始) 
	* @param pos 当前文档指定的位置 
	*/
	public void copyParagraphFromAnotherDoc(String anotherDocPath, int paragraphIndex, String pos) {
		Dispatch doc2 = null;
		try {
			doc2 = Dispatch.call(documents, "Open", anotherDocPath).toDispatch();
			Dispatch paragraphs = Dispatch.get(doc2, "Paragraphs").toDispatch();
			Dispatch paragraph = Dispatch.call(paragraphs, "Item", new Variant(paragraphIndex)).toDispatch();
			Dispatch range = Dispatch.get(paragraph, "Range").toDispatch();
			Dispatch.call(range, "Copy");
			if (this.find(pos)) {
				Dispatch textRange = Dispatch.get(selection, "Range").toDispatch();
				Dispatch.call(textRange, "Paste");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (doc2 != null) {
				Dispatch.call(doc2, "Close", new Variant(saveOnExit));
				doc2 = null;
			}
		}
	}

	/** 
	* 在当前文档指定的位置拷贝来自另一个文档中的表格 
	*　　　　 
	* @param anotherDocPath 另一个文档的磁盘路径 
	* @param tableIndex 被拷贝的表格在另一格文档中的序号(从1开始) 
	* @param pos 当前文档指定的位置 
	*/
	public void copyTableFromAnotherDoc(String anotherDocPath, int tableIndex, String pos) {
		Dispatch doc2 = null;
		try {
			doc2 = Dispatch.call(documents, "Open", anotherDocPath).toDispatch();
			Dispatch tables = Dispatch.get(doc2, "Tables").toDispatch();
			Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
			Dispatch range = Dispatch.get(table, "Range").toDispatch();
			Dispatch.call(range, "Copy");
			if (this.find(pos)) {
				Dispatch textRange = Dispatch.get(selection, "Range").toDispatch();
				Dispatch.call(textRange, "Paste");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (doc2 != null) {
				Dispatch.call(doc2, "Close", new Variant(saveOnExit));
				doc2 = null;
			}
		}
	}

	/** 
	* 在当前文档指定的位置拷贝来自另一个文档中的图片 
	*　　　　 
	* @param anotherDocPath 另一个文档的磁盘路径 
	* @param shapeIndex 被拷贝的图片在另一格文档中的位置 
	* @param pos 当前文档指定的位置 
	*/
	public void copyImageFromAnotherDoc(String anotherDocPath, int shapeIndex, String pos) {
		Dispatch doc2 = null;
		try {
			doc2 = Dispatch.call(documents, "Open", anotherDocPath).toDispatch();
			Dispatch shapes = Dispatch.get(doc2, "InLineShapes").toDispatch();
			Dispatch shape = Dispatch.call(shapes, "Item", new Variant(shapeIndex)).toDispatch();
			Dispatch imageRange = Dispatch.get(shape, "Range").toDispatch();
			Dispatch.call(imageRange, "Copy");
			if (this.find(pos)) {
				Dispatch textRange = Dispatch.get(selection, "Range").toDispatch();
				Dispatch.call(textRange, "Paste");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (doc2 != null) {
				Dispatch.call(doc2, "Close", new Variant(saveOnExit));
				doc2 = null;
			}
		}
	}

	/** 
	* 创建表格 
	*　　　　　
	* @param pos　　　 位置 
	* @param cols 列数 
	* @param rows 行数 
	*/
	public void createTable(int numCols, int numRows) {
		//(String pos, int numCols, int numRows) { 
		//if (!find(pos)) { 
		Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
		Dispatch range = Dispatch.get(selection, "Range").toDispatch();
		@SuppressWarnings("unused")
		Dispatch newTable = Dispatch.call(tables, "Add", range, new Variant(numRows), new Variant(numCols))
				.toDispatch();
		Dispatch.call(selection, "MoveRight");
		moveEnd();
		//} 
	}

	/** 
	* 在指定行前面增加行 
	*　　　　 
	* @param tableIndex word文件中的第N张表(从1开始) 
	* @param rowIndex 指定行的序号(从1开始) 
	*/
	public void addTableRow(int tableIndex, int rowIndex) {
		// 所有表格 
		Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
		// 要填充的表格 
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
		// 表格的所有行 
		Dispatch rows = Dispatch.get(table, "Rows").toDispatch();
		Dispatch row = Dispatch.call(rows, "Item", new Variant(rowIndex)).toDispatch();
		Dispatch.call(rows, "Add", new Variant(row));
	}

	/** 
	* 在第1行前增加一行 
	*　　　　 
	* @param tableIndex word文档中的第N张表(从1开始) 
	*/
	public void addFirstTableRow(int tableIndex) {
		// 所有表格 
		Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
		// 要填充的表格 
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
		// 表格的所有行 
		Dispatch rows = Dispatch.get(table, "Rows").toDispatch();
		Dispatch row = Dispatch.get(rows, "First").toDispatch();
		Dispatch.call(rows, "Add", new Variant(row));
	}

	/** 
	* 在最后1行前增加一行 
	*　　　　 
	* @param tableIndex 
	*　　　　　　　　　　　　　　　　　　　　　　　 word文档中的第N张表(从1开始) 
	*/
	public void addLastTableRow(int tableIndex) {
		// 所有表格 
		Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
		// 要填充的表格 
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
		// 表格的所有行 
		Dispatch rows = Dispatch.get(table, "Rows").toDispatch();
		Dispatch row = Dispatch.get(rows, "Last").toDispatch();
		Dispatch.call(rows, "Add", new Variant(row));
	}

	/** 
	* 增加一行 
	*　　　　 
	* @param tableIndex word文档中的第N张表(从1开始) 
	*/
	public void addRow(int tableIndex) {
		Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
		// 要填充的表格 
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
		// 表格的所有行 
		Dispatch rows = Dispatch.get(table, "Rows").toDispatch();
		Dispatch.call(rows, "Add");
	}

	/** 
	 * 增加一列 
	 *　　　　 
	 * @param tableIndex word文档中的第N张表(从1开始) 
	 */
	public void addCol(int tableIndex) {
		// 所有表格 
		Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
		// 要填充的表格 
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
		// 表格的所有行 
		Dispatch cols = Dispatch.get(table, "Columns").toDispatch();
		Dispatch.call(cols, "Add").toDispatch();
		Dispatch.call(cols, "AutoFit");
	}

	/** 
	 * 在指定列前面增加表格的列 
	 *　　　　 
	 * @param tableIndex word文档中的第N张表(从1开始) 
	 * @param colIndex　　　 指定列的序号 (从1开始) 
	 */
	public void addTableCol(int tableIndex, int colIndex) {
		// 所有表格 
		Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
		// 要填充的表格 
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
		// 表格的所有行 
		Dispatch cols = Dispatch.get(table, "Columns").toDispatch();
		System.out.println(Dispatch.get(cols, "Count"));
		Dispatch col = Dispatch.call(cols, "Item", new Variant(colIndex)).toDispatch();
		// Dispatch col = Dispatch.get(cols, "First").toDispatch(); 
		Dispatch.call(cols, "Add", col).toDispatch();
		Dispatch.call(cols, "AutoFit");
	}

	/** 
	* 在第1列前增加一列 
	*　　　　 
	* @param tableIndex word文档中的第N张表(从1开始) 
	*/
	public void addFirstTableCol(int tableIndex) {
		Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
		// 要填充的表格 
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
		// 表格的所有行 
		Dispatch cols = Dispatch.get(table, "Columns").toDispatch();
		Dispatch col = Dispatch.get(cols, "First").toDispatch();
		Dispatch.call(cols, "Add", col).toDispatch();
		Dispatch.call(cols, "AutoFit");
	}

	/** 
	* 在最后一列前增加一列 
	*　　　　 
	* @param tableIndex word文档中的第N张表(从1开始) 
	*/
	public void addLastTableCol(int tableIndex) {
		Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
		// 要填充的表格 
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
		// 表格的所有行 
		Dispatch cols = Dispatch.get(table, "Columns").toDispatch();
		Dispatch col = Dispatch.get(cols, "Last").toDispatch();
		Dispatch.call(cols, "Add", col).toDispatch();
		Dispatch.call(cols, "AutoFit");
	}

	/** 
	* 自动调整表格 
	*　　　　 
	*/
	public void autoFitTable() {
		Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
		//int count = Dispatch.get(tables, "Count").toInt(); 
		int count = Integer.parseInt(Dispatch.get(tables, "Count").toString());
		for (int i = 0; i < count; i++) {
			Dispatch table = Dispatch.call(tables, "Item", new Variant(i + 1)).toDispatch();
			Dispatch cols = Dispatch.get(table, "Columns").toDispatch();
			Dispatch.call(cols, "AutoFit");
		}
	}

	/** 
	* 调用word里的宏以调整表格的宽度,其中宏保存在document下 
	*　　　　 
	*/
	public void callWordMacro() {
		Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
		//int count = Dispatch.get(tables, "Count").toInt();
		@SuppressWarnings("unused")
		int count = Integer.parseInt(Dispatch.get(tables, "Count").toString());
		Variant vMacroName = new Variant("Normal.NewMacros.tableFit");
		@SuppressWarnings("unused")
		Variant vParam = new Variant("param1");
		Variant para[] = new Variant[] { vMacroName };
		for (int i = 0; i < para.length; i++) {
			Dispatch table = Dispatch.call(tables, "Item", new Variant(i + 1)).toDispatch();
			Dispatch.call(table, "Select");
			Dispatch.call(word, "Run", "tableFitContent");
		}
	}

	/** 
	 * 设置当前选定内容的字体 
	 *　　　　 
	 * @param boldSize 
	 * @param italicSize 
	 * @param underLineSize 下划线 
	 * @param colorSize 字体颜色 
	 * @param size 字体大小 
	 * @param name 字体名称 
	 */
	public void setFont(boolean bold, boolean italic, boolean underLine, String colorSize, String size, String name) {
		Dispatch font = Dispatch.get(selection, "Font").toDispatch();
		Dispatch.put(font, "Name", new Variant(name));
		Dispatch.put(font, "Bold", new Variant(bold));
		Dispatch.put(font, "Italic", new Variant(italic));
		Dispatch.put(font, "Underline", new Variant(underLine));
		Dispatch.put(font, "Color", colorSize);
		Dispatch.put(font, "Size", size);
	}

	/** 
	* 文件保存或另存为 
	*　　　　 
	* @param savePath 保存或另存为路径 
	*/
	public void save(String savePath) {
		Dispatch.call((Dispatch) Dispatch.call(word, "WordBasic").getDispatch(), "FileSaveAs", savePath);
	}

	/** 
	 * 关闭当前word文档 
	 *　　　　 
	 */
	public void closeDocument() {
		logger.debug("closeDocument begin { ... ");
		if (doc != null) {
			Dispatch.call(doc, "Save");
			Dispatch.call(doc, "Close", new Variant(saveOnExit));
			doc = null;
		} else {
			logger.error("doc is null~~~~~~~~~~~~~~~~~~~~~~~~~");
		}
		logger.debug("closeDocument end ... } ");
	}

	/** 
	* 关闭全部应用 
	*　　　　 
	*/
	public void close() {
		logger.debug("close begin { ... ");
		closeDocument();
		if (word != null) {
			Dispatch.call(word, "Quit");
			word = null;
		}
		selection = null;
		documents = null;
		logger.debug("close end ... } ");
	}

	/** 
	* 打印当前word文档 
	*　　　　 
	*/
	public void printFile() {
		if (doc != null) {
			Dispatch.call(doc, "PrintOut");
		}
	}

	/*~~~~~~~~~~~~ 标签操作方法 ~~~~~~~~~~~~~~~~~*/

	/**
	 * 获取指定word文件的所有书签.
	 */
	public List<Map<String, String>> getAllBookmark(String inFile) {
		//打开指定文件
		openDocument(inFile);
		//书签集合
		Dispatch bookmarks = Dispatch.call(doc, "Bookmarks").toDispatch();
		List<Map<String, String>> allBookmarks = new ArrayList<Map<String, String>>();
		int bkCount = Dispatch.get(bookmarks, "Count").getInt();
		//将书签列表存放到list + map 结构中
		for (int i = 1; i <= bkCount; i++) {
			Map<String, String> bookMark = new HashMap<String, String>();
			Dispatch item = Dispatch.call(bookmarks, "Item", i).toDispatch();
			String itemName = Dispatch.get(item, "Name").getString(); //读取书签命名
			Dispatch range = Dispatch.get(item, "Range").toDispatch();
			String itemValue = Dispatch.get(range, "Text").getString(); //读取书签文本
			if (!itemName.equals("")) {
				logger.debug("BookmarkName--》" + itemName);
				logger.debug("BookmarkValue--》" + itemValue);
				bookMark.put("NAME", itemName);
				bookMark.put("TEXT", itemValue);
				allBookmarks.add(bookMark);
			}
		}
		return allBookmarks;
	}

	/**
	 * 往标签里面插入值,例:生成合同文本
	 * @param infile
	 * @param tofile
	 * @param tagVals
	 */
	public void insertTagValue(String infile, String tofile, @SuppressWarnings("rawtypes") HashMap tagVals) {

		openDocument(infile);

		Dispatch activeDocument = word.getProperty("ActiveDocument").toDispatch();
		@SuppressWarnings("static-access")
		Dispatch bookMarks = word.call(activeDocument, "Bookmarks").toDispatch(); //Bookmarks是一个关键字

		for (Object key : tagVals.keySet()) {
			Dispatch.put(Dispatch.call(Dispatch.call(bookMarks, "Item", key).toDispatch(), "Range").toDispatch(),
					"Text", new Variant(tagVals.get(key)));//给文本框赋值
		}

		save(tofile);
	}

	/*~~~~~~~~~~~~ Setter And Getter ~~~~~~~~~~~~~~~~~*/
	public boolean isSaveOnExit() {
		return saveOnExit;
	}

	/** 
	* 设置退出时参数 
	*　　　　 
	* @param saveOnExit boolean true-退出时保存文件，false-退出时不保存文件 
	*/
	public void setSaveOnExit(boolean saveOnExit) {
		this.saveOnExit = saveOnExit;
	}

	/**
	 * 测试主方法
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		//String savePath = "C:\\doc\\test.doc";
		String srcDoc = "C:\\doc\\RCServer.doc";
		MSWordManager wordManager = new MSWordManager(true);
		wordManager.setSaveOnExit(true);
		
		//wordManager.createNewDocument();
		//wordManager.insertText("你好.");
		//wordManager.save(savePath);
		//wordManager.close();
		//wordManager.openDocument(srcDoc);
		wordManager.getAllBookmark(srcDoc);
		wordManager.close();

	}
}
