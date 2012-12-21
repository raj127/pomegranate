package com.darkmi.jacob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class BookmarkManager {
	ActiveXComponent word = new ActiveXComponent("Word.Application");

	public BookmarkManager() {
		word.setProperty("Visible", new Variant(false));
	}

	/**
	 * 获取所有的书签列表.
	 */
	public void getAllBookMark() {
		Dispatch documents = word.getProperty("Documents").toDispatch();
		Dispatch doc = Dispatch.call(documents, "Open", "c:/test/RCServer.doc").toDispatch();
		//书签集合
		Dispatch bookMarks = Dispatch.call(doc, "Bookmarks").toDispatch();
		List<Map<String, String>> lstBookMarks = new ArrayList<Map<String, String>>();
		int bCount = Dispatch.get(bookMarks, "Count").getInt();
		//将书签列表存放到list + map 结构中
		for (int i = 1; i <= bCount; i++) {
			Map<String, String> bookMark = new HashMap<String, String>();
			Dispatch item = Dispatch.call(bookMarks, "Item", i).toDispatch();
			String item_name = String.valueOf(Dispatch.get(item, "Name").getString()).replaceAll("null", ""); //读取书签命名
			Dispatch range = Dispatch.get(item, "Range").toDispatch();
			String item_value = String.valueOf(Dispatch.get(range, "Text").getString()).replaceAll("null", ""); //读取书签文本
			if (!item_name.equals("")) {
				bookMark.put("NAME", item_name);
				bookMark.put("TEXT", item_value);
				lstBookMarks.add(bookMark);
			}
		}
	}

	/**
	 * 删除书签
	 * @param markKey 书签名
	 * @param info 可替换
	 * @return
	 * @throws Exception
	 */
	public boolean deleteBookMark(String markKey, String info) throws Exception {
		Dispatch activeDocument = word.getProperty("ActiveDocument").toDispatch();
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
		Dispatch activeDocument = word.getProperty("ActiveDocument").toDispatch();
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
		bmm.getAllBookMark();
	}

}
