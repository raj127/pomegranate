package com.darkmi.jacob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

/**
 * Jacob测试程序.
 *
 */
public class JacobOne {
	private Logger logger = LoggerFactory.getLogger(JacobOne.class);
	private ActiveXComponent objWord;
	private Dispatch document;
	private Dispatch wordObject;
	private static final String FILE_PATH = "c:\\test\\1.docx";//最好是一个空白word文件

	/**
	 * 打开参数指定的word文件.
	 * @param filename
	 */
	public void open(String filename) {
		logger.debug("open begin { ... ");
		// 实例化 objWord
		objWord = new ActiveXComponent("Word.Application");

		// 将本地word对象赋到 wordObject上
		wordObject = (Dispatch) objWord.getObject();

		// 使 word 为"可见"，主要是方便调试。正式应用时，把true改为false
		Dispatch.put(wordObject, "Visible", new Variant(true));

		// 获得Documents对象
		Dispatch documents = objWord.getProperty("Documents").toDispatch();

		// 调用 Open 打开 Document
		document = Dispatch.call(documents, "Open", filename).toDispatch();

		// 演示如何增加文字内容
		Dispatch disSelect;
		disSelect = Dispatch.call(objWord, "Selection").toDispatch();
		Dispatch.call(disSelect, "TypeText", "高宏伟");
		Dispatch.call(disSelect, "TypeParagraph");
		Dispatch.call(disSelect, "TypeText", "QQ:21807822");
		Dispatch.call(disSelect, "TypeParagraph");
		Dispatch.call(disSelect, "TypeText", "e-mail:dukejoe@163.com");

		// 演示如何增加表格
		Dispatch disTables;
		disTables = Dispatch.call(document, "Tables").toDispatch();
		Dispatch disRange;
		disRange = Dispatch.call(disSelect, "Range").toDispatch();
		Dispatch.call(disTables, "Add", disRange, 3, 4, 1);

		Dispatch disTable;
		disTable = Dispatch.call(disTables, "Item", 1).toDispatch();
		Dispatch.put(disTable, "ApplyStyleColumnBands", 1);

		// 演示如何操纵表格，比如设置对齐为"居中"
		// Unit : 5–wdLine 1–wdCharacter
		// Extend : 2–wdExtend
		Dispatch.call(disSelect, "MoveDown", 5, 2, 2);
		Dispatch.call(disSelect, "MoveRight", 1, 3, 2);
		Dispatch disParagraphFormat;
		disParagraphFormat = Dispatch.get(disSelect, "ParagraphFormat").toDispatch();
		Dispatch.put(disParagraphFormat, "Alignment", 1);

		Dispatch.call(disSelect, "MoveDown", 5, 1);
		Dispatch.call(disSelect, "TypeParagraph");
		Dispatch.call(disSelect, "TypeText", "黑龙江省哈尔滨市");
		Dispatch.call(disSelect, "TypeParagraph");
		Dispatch.call(disSelect, "TypeText", "哈尔滨银行");
		Dispatch.call(disSelect, "TypeParagraph");

		logger.debug("open end ...}");
	}

	/**
	 * 关闭word文档.
	 */
	public void close() {
		// 关闭文档
		// 由于是演示程序，这里只简单的把word退出即可
		Dispatch.call(document, "Close");
		Dispatch.call(wordObject, "quit");
	}

	/**
	 * 主方法.
	 * @param args
	 */
	public static void main(String[] args) {
		JacobOne jacob = new JacobOne();
		try {
			// 为了演示方便，请在下列目录新建一个空白文档
			jacob.open(FILE_PATH);
			jacob.close();
		} catch (Exception e) {
			jacob.close();
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
