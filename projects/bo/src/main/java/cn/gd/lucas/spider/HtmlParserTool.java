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
	// 获取一个网站上的链接,filter 用来过滤链接
	public static Set<String> extracLinks(String url,LinkFilter filter) {

		Set<String> links = new HashSet<String>();
		try {
			Parser parser = new Parser(url);
			parser.setEncoding("gb2312");
			// 过滤 <frame >标签的 filter，用来提取 frame 标签里的 src 属性所表示的链接
			NodeFilter frameFilter = new NodeFilter() {
				public boolean accept(Node node) {
					if (node.getText().startsWith("frame src=")) {
						return true;
					} else {
						return false;
					}
				}
			};
			// OrFilter 来设置过滤 <a> 标签，和 <frame> 标签
			OrFilter linkFilter = new OrFilter(new NodeClassFilter(
					LinkTag.class), frameFilter);
			// 得到所有经过过滤的标签
			NodeList list = parser.extractAllNodesThatMatch(linkFilter);
			for (int i = 0; i < list.size(); i++) {
				Node tag = list.elementAt(i);
				if (tag instanceof LinkTag)// <a> 标签
				{
					LinkTag link = (LinkTag) tag;
					String linkUrl = link.getLink();// url
					if(filter.accept(linkUrl))
						links.add(linkUrl);
				} else// <frame> 标签
				{
		        // 提取 frame 里 src 属性的链接如 <frame src="test.html"/>
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
	//提取出网页中的闻之信息
	public static String extracKeyWords(String url){
		
	        StringBean sb = new StringBean();   
	           
	        //设置不需要得到页面所包含的链接信息   
	        sb.setLinks(false);   
	        //设置将不间断空格由正规空格所替代   
	        sb.setReplaceNonBreakingSpaces(true);   
	        //设置将一序列空格由一个单一空格所代替   
	        sb.setCollapse(true);   
	        //传入要解析的URL   
	        sb.setURL(url);   
	        //返回解析后的网页纯文本信息   
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

