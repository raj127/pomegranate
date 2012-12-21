package com.darkmi.jacob;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

/**
 * 使用jacob操作word书签.
 * @author Darkmi
 * Copyright (c) www.darkmi.com
 * All Rights Reserved.
 *
 */
public class BookmarkManager {
	private Logger logger = LoggerFactory.getLogger(BookmarkManager.class);
	private ActiveXComponent app; //代表word程序
	private Dispatch document; //合并后的文档
	private Dispatch selection;//代表选中文本
	private Dispatch paragraph;//代表段落
	private Dispatch font;//代表字体

	Dispatch documents;

	/**
	 * 构造函数,初始化并新建Word文档.
	 */
	public BookmarkManager() {
		logger.debug("BookmarkManager { ... ");
		ComThread.InitSTA();// 初始化Com线程
		app = new ActiveXComponent("Word.Application");// 初始化word应用程序
		app.setProperty("Visible", new Variant(false));// 设置word文档不在前台打开
		app.setProperty("AutomationSecurity", new Variant(3)); // 禁用宏
		documents = app.getProperty("Documents").toDispatch();// 获取文档属性
		document = Dispatch.call(documents, "Add").toDispatch(); // 创建新文档
		selection = app.getProperty("Selection").toDispatch();// 获得选定内容
		paragraph = Dispatch.get(selection, "ParagraphFormat").getDispatch();// 段落
		font = Dispatch.get(selection, "Font").toDispatch();// 字体
		logger.debug("BookmarkManager ... }");
	}

	/**
	 * 合并Word文档
	 * @param file
	 */
	public void mergeWord(File[] file) {
		logger.debug("合并word文档 begin { ...");
		try {
			setWordTitle();
			// 设置文本样式，不包括接下来要插入的WORD文档内容
			Dispatch.put(paragraph, "Alignment", "3");// 1:中 2:右 3:左
			Dispatch.put(font, "Color", "1,0,0,0"); // 红色
			Dispatch.put(font, "Size", 12);
			for (int i = 0; i < file.length; i++) {
				String tempFile = file[i].getAbsolutePath();
				logger.debug("合并第一个文件--> {}", tempFile);
				if (tempFile.endsWith(".doc") || tempFile.endsWith(".docx")) {
					Dispatch.put(selection, "Text", file[i].getName() + "文档内容如下：");
					Dispatch.call(selection, "MoveDown");
					Dispatch.call(selection, "TypeParagraph"); // 空一行段落
					Dispatch.call(selection, "insertFile", tempFile);// 插入文件内容
					Dispatch.call(selection, "TypeParagraph"); // 空一行段落
				}
			}

			Dispatch.call(document, "SaveAs", new Variant("C:\\test\\test.docx")); //保存
			Dispatch.call(document, "Close", new Variant(false));// 关闭文档
			logger.debug("");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			//app.invoke("Quit", new Variant(0));// 退出word应用
			ComThread.Release();// 释放Com线程
		}
		logger.debug("合并word文档 end ...}");
	}

	/**
	 * 合并Word文档
	 * @param file
	 * @param savePath
	 */
	public void mergeWord(File[] file, String savePath) {
		logger.debug("合并word文档 begin { ...");
		try {
			setWordTitle();
			// 设置文本样式，不包括接下来要插入的WORD文档内容
			//Dispatch.put(paragraph, "Alignment", "3");// 1:中 2:右 3:左
			Dispatch.put(font, "Color", "1,0,0,0"); // 红色
			Dispatch.put(font, "Size", 12);
			for (int i = 0; i < file.length; i++) {
				String tempFile = file[i].getAbsolutePath();
				logger.debug("合并第一个文件--> {}", tempFile);
				if (tempFile.endsWith(".doc") || tempFile.endsWith(".docx")) {
					Dispatch.put(selection, "Text", file[i].getName() + "文档内容如下：");
					Dispatch.call(selection, "MoveDown");
					Dispatch.call(selection, "TypeParagraph"); // 空一行段落
					Dispatch.call(selection, "insertFile", tempFile);// 插入文件内容
					Dispatch.call(selection, "TypeParagraph"); // 空一行段落
				}
			}

			Dispatch.call(document, "SaveAs", new Variant(savePath + "\\tempate.docx")); //保存
			Dispatch.call(document, "Close", new Variant(false));// 关闭文档
			logger.debug("");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			//app.invoke("Quit", new Variant(0));// 退出word应用
			ComThread.Release();// 释放Com线程
		}
		logger.debug("合并word文档 end ...}");
	}

	/**
	 * 设置合并后文档的标题及样式
	 */
	private void setWordTitle() {
		Dispatch.put(font, "Bold", new Variant(true));
		Dispatch.put(font, "Color", "0,0,0,0"); // 黑色
		Dispatch.put(font, "Size", 16);
		Dispatch.call(selection, "TypeText", "合并后的Word文档"); // 写入标题内容
		Dispatch.call(selection, "TypeParagraph"); // 空一行段落
	}

	/**
	 * 获取所有的书签列表.
	 */
	public List<Map<String, String>> getAllBookmark() {
		Dispatch documents = app.getProperty("Documents").toDispatch();
		Dispatch doc = Dispatch.call(documents, "Open", "c:/test/RCServer.doc").toDispatch();
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

				bookMark.put("NAME", itemName);
				bookMark.put("TEXT", itemValue);
				allBookmarks.add(bookMark);

			}
		}
		return allBookmarks;
	}

	/**
	 * 按照标签来切割word文档.
	 */
	public void splitBookmarks(String infile, String tofile, HashMap tagVals) {
		Dispatch documents = app.getProperty("Documents").toDispatch();
		Dispatch doc = Dispatch.call(documents, "Open", "c:/test/RCServer.doc").toDispatch();
		Dispatch bookMarks = Dispatch.call(doc, "Bookmarks").toDispatch(); //Bookmarks是一个关键字

		//----------------------
		//书签集合
		Dispatch bookmarks = Dispatch.call(doc, "Bookmarks").toDispatch();

		int bkCount = Dispatch.get(bookmarks, "Count").getInt();//文档中的书签数量
		//将书签列表存放到list + map 结构中
		for (int i = 1; i <= bkCount; i++) {
			Dispatch item = Dispatch.call(bookmarks, "Item", i).toDispatch(); //获取对应的书签对象
			String itemName = Dispatch.get(item, "Name").getString(); //读取书签名称
			Dispatch range = Dispatch.get(item, "Range").toDispatch();//读取书签内容
			@SuppressWarnings("unused")
			String itemValue = Dispatch.get(range, "Text").getString(); //读取书签文本
			if (!itemName.equals("")) {

				//bookMark.put("NAME", itemName);
				//bookMark.put("TEXT", itemValue);
				//allBookmarks.add(bookMark);

			}
		}
		//----------------------
		for (Object key : tagVals.keySet()) {
			Dispatch item = Dispatch.call(bookMarks, "Item", key).toDispatch();
			@SuppressWarnings("unused")
			Dispatch range = Dispatch.call(item, "Range").toDispatch();
			@SuppressWarnings("unused")
			Dispatch newDoc = Dispatch.call(documents, "Add").toDispatch(); // 创建新文档
			selection = app.getProperty("Selection").toDispatch();// 获得选定内容
			paragraph = Dispatch.get(selection, "ParagraphFormat").getDispatch();// 段落
			font = Dispatch.get(selection, "Font").toDispatch();// 字体
			//Dispatch.put(selection, "Text", file[i].getName() + "文档内容如下：");
			Dispatch.call(selection, "MoveDown");
			Dispatch.call(selection, "TypeParagraph"); // 空一行段落
			//Dispatch.call(selection, "insertFile", tempFile);// 插入文件内容
			Dispatch.call(selection, "TypeParagraph"); // 空一行段落
			Dispatch.put(Dispatch.call(item, "Range").toDispatch(), "Text", new Variant(tagVals.get(key)));//给文本框赋值
		}
		//save(tofile);
	}

	/**
	 * 删除书签
	 * @param markKey 书签名
	 * @param info 可替换
	 * @return
	 * @throws Exception
	 */
	public boolean deleteBookMark(String markKey, String info) throws Exception {
		Dispatch activeDocument = app.getProperty("ActiveDocument").toDispatch();
		Dispatch bookMarks = Dispatch.call(activeDocument, "Bookmarks").toDispatch();
		boolean isExists = Dispatch.call(bookMarks, "Exists", markKey).getBoolean();
		if (isExists) {
			Dispatch n = Dispatch.call(bookMarks, "Item", markKey).toDispatch();
			Dispatch.call(n, "Delete");
			return true;
		}
		return false;
	}

	/**
	 * 根据书签插入数据
	 * @param bookMarkKey 书签名
	 * @param info 插入的数据
	 * @return
	 * @throws Exception
	 */
	public boolean intoValueBookMark(String bookMarkKey, String info) throws Exception {
		Dispatch activeDocument = app.getProperty("ActiveDocument").toDispatch();
		Dispatch bookMarks = Dispatch.call(activeDocument, "Bookmarks").toDispatch();
		boolean bookMarkExist = Dispatch.call(bookMarks, "Exists", bookMarkKey).getBoolean();
		if (bookMarkExist) {
			Dispatch rangeItem = Dispatch.call(bookMarks, "Item", bookMarkKey).toDispatch();
			Dispatch range = Dispatch.call(rangeItem, "Range").toDispatch();
			Dispatch.put(range, "Text", new Variant(info));
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		BookmarkManager bmm = new BookmarkManager();
		bmm.getAllBookmark();
	}

}
