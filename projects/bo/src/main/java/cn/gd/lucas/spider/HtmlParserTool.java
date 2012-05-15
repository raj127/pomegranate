package cn.gd.lucas.spider;

import java.util.HashSet;
import java.util.Set;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.beans.StringBean;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.util.SimpleNodeIterator;
import org.htmlparser.visitors.HtmlPage;
import cn.gd.lucas.util.*;
import cn.gd.lucas.util.ReplaseSpecialXmlChar;

public class HtmlParserTool {
	// ��ȡһ����վ�ϵ�����,filter ������������
	public static Set<String> extracLinks(String url,LinkFilter filter) {

		Set<String> links = new HashSet<String>();
		try {
			Parser parser = new Parser(url);
			parser.setEncoding("gb2312");
			// ���� <frame >��ǩ�� filter��������ȡ frame ��ǩ��� src ��������ʾ������
			NodeFilter frameFilter = new NodeFilter() {
				public boolean accept(Node node) {
					if (node.getText().startsWith("frame src=")) {
						return true;
					} else {
						return false;
					}
				}
			};
			// OrFilter �����ù��� <a> ��ǩ���� <frame> ��ǩ
			OrFilter linkFilter = new OrFilter(new NodeClassFilter(
					LinkTag.class), frameFilter);
			// �õ����о������˵ı�ǩ
			NodeList list = parser.extractAllNodesThatMatch(linkFilter);
			for (int i = 0; i < list.size(); i++) {
				Node tag = list.elementAt(i);
				if (tag instanceof LinkTag)// <a> ��ǩ
				{
					LinkTag link = (LinkTag) tag;
					String linkUrl = link.getLink();// url
					if(filter.accept(linkUrl))
						links.add(linkUrl);
				} else// <frame> ��ǩ
				{
		        // ��ȡ frame �� src ���Ե������� <frame src="test.html"/>
					String frame = tag.getText();
					int start = frame.indexOf("src=");
					frame = frame.substring(start);
					int end = frame.indexOf(" ");
					if (end == -1)
						end = frame.indexOf(">");
					String frameUrl = frame.substring(5, end - 1);
					if(filter.accept(frameUrl))
						links.add(frameUrl);
				}
			}
		} catch (ParserException e) {
			
			return null;
		}
		return links;
	}
	//��ȡ����ҳ�е���֮��Ϣ
	public static String extracKeyWords(String url){
		
	        StringBean sb = new StringBean();   
	           
	        //���ò���Ҫ�õ�ҳ����������������Ϣ   
	        sb.setLinks(false);   
	        //���ý�����Ͽո�������ո������   
	        sb.setReplaceNonBreakingSpaces(true);   
	        //���ý�һ���пո���һ����һ�ո�������   
	        sb.setCollapse(true);   
	        //����Ҫ������URL   
	        sb.setURL(url);   
	        //���ؽ��������ҳ���ı���Ϣ   
	        return sb.getStrings();   
	
	}
	public static Page getPage(String url){
	
		Parser myParser;
		try {
			myParser = new Parser(url);
			 myParser.setEncoding("gb2312");
				HtmlPage visitor = new HtmlPage(myParser);
				myParser.visitAllNodesWith(visitor);
				String title = visitor.getTitle();
				String body = extracKeyWords(url);
				title=ReplaseSpecialXmlChar.doReplace(title);
				body=ReplaseSpecialXmlChar.doReplace(body);
				//title=XmlCharFilter.doFilter(title);
				//body=XmlCharFilter.doFilter(body);
				return new Page(url,title,body);
		} catch (ParserException e) {
			e.getStackTrace();
		   return null;
		}
		
       
	}
	public static void main(String[]args)
	{
      System.out.println(HtmlParserTool.extracKeyWords("http://www.sina.com/"));
	}
	
}

