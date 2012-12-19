package com.darkmi.jacob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

/**
 * 本程序演示如何通过jacob读取及操作word标签.
 * @author Darkmi
 *
 */
public class JacobTwo {
	private Logger logger = LoggerFactory.getLogger(JacobTwo.class);
	private ActiveXComponent objWord;
	private Dispatch document;
	private Dispatch wordObject;
	private static final String FILE_PATH = "c:\\test\\RCServer.doc";//带标签的word文档

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
		
		Dispatch bookMarks = Dispatch.call(document, "Bookmarks").toDispatch();
		logger.debug(bookMarks.toString());
		//--------------
//		Dispatch bookMarks = Dispatch.call(document, "Bookmarks").toDispatch();

//			for (int i = 0; i < bookMarks.; i++) {
//				Variant isExist = Dispatch.call(bookMarks, "Exists", bookmarks[i]);
//				if (isExist.getBoolean()) {
//					Dispatch item = Dispatch.call(bookMarks, "Item", bookmarks[i]).toDispatch();
//					Dispatch range = Dispatch.call(item, "Range").toDispatch();
//					Dispatch.put(range, "Text", fillValues[i]);
//				}
//			
//		}


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
		JacobTwo jacob = new JacobTwo();
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
