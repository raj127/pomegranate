package cn.gd.lucas.util;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


import cn.gd.lucas.API.MyQueryResult;

public class XmlTool {
	public static MyQueryResult getMyQueryResult(String xmlStr){
		
		MyQueryResult queryResult = new MyQueryResult();
		String rows = "10";
		String start = "0";
		Document document = null;
		try {
			document = DocumentHelper.parseText(xmlStr);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String searchStr = null;
		ArrayList alist = new ArrayList();
		Element rootElement = document.getRootElement();
		// ��÷��ؽ����ͷ���
		Element headerElement = rootElement.element("lst");
		Element paramsElement = headerElement.element("lst");
		List<Element> params = paramsElement.elements("str");
		for (Element e : params) {
			if (e.attributeValue("name").equals("q"))
				searchStr = e.getText();
			if (e.attributeValue("name").equals("rows"))
				rows = e.getText();
			if (e.attributeValue("name").equals("start"))
				start = e.getText();
		}
		// ��ý���ڵ�
		Element resultElement = rootElement.element("result");
		String total = resultElement.attributeValue("numFound");
		List<Element> list = resultElement.elements("doc");
		// ��ÿ��DOC���д���
		for (Element e : list) {
			// array[0]:id array[1]:int array[2]:field1 array[3]:field2
			String[] array = new String[4];
		
			List<Element> strList = e.elements("str");
			for (Element strElement : strList) {
				String name = strElement.attributeValue("name");
				array[0]="a";
				if (name.equals("url"))
					array[0] = strElement.getText();
				if (name.equals("title"))
					array[2] = strElement.getText();
				if (name.equals("text")) {
					String field2Str = strElement.getText();
					if (field2Str.length() < 200)
						array[3] = field2Str;
					else
						array[3] = field2Str.substring(0, 200);
				}

			}
			// TODO ���Լ�����������������
			alist.add(array);
			//System.out.println("searchStr:" + searchStr);
		}
		// ���ز�ѯ���������,��СΪrowsָ��
		String[][] arrayReturn = new String[Integer.parseInt(rows)][4];
		for (int a = 0; a < alist.size(); a++) {
			arrayReturn[a] = (String[]) alist.get(a);
		}

		/*
		 * for(String[] s:arrayReturn){ System.out.println(s[0]);
		 * System.out.println(s[1]); System.out.println(s[2]);
		 * System.out.println(s[3]); }
		 */
		MyQueryResult qr = new MyQueryResult();
		qr.setAction("search");
		qr.setPageNumber(Integer.toString((Integer.parseInt(start) / 10 + 1)));
		qr.setResultArray(arrayReturn);
		qr.setSa(null);
		qr.setKeyword(searchStr);
		qr.setTotal(total);
		return qr;
	}

}
