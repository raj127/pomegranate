package cn.gd.lucas.spider;

import java.util.Set;

import org.htmlparser.util.ParserException;

/**
 * 
 * @author Administrator
 * 爬虫的界面，
 */
public class Spider {
	/**
	 * url 为爬虫的起始链接
	 */
	private String url;
	private LinkDB linkDB;
	private SubLinkFilter filter;
	
	public Spider(String url) {
		this.url=url;
		linkDB=new LinkDB();
		filter=new SubLinkFilter(url);
		linkDB.addUnvisitedUrl(url);
	}
	/**
	 * 返回爬虫获取的下一个网页
	 * @return
	 * @throws ParserException 
	 */
	public Page nextPage(){
		while(!linkDB.unVisitedUrlsEmpty()&&linkDB.getVisitedUrlNum()<=500){
			//队头 URL 出对
			String visitUrl=linkDB.unVisitedUrlDeQueue();
			if(visitUrl==null)
				return null;
			
			linkDB.addVisitedUrl(visitUrl);
			//提取出下载网页中的 URL
			
			Set<String> links=HtmlParserTool.extracLinks(visitUrl,filter);
			//新的未访问的 URL 入队
			if(null!=links){
				for(String link:links)
				{
						linkDB.addUnvisitedUrl(link);
				}
			}
			
			Page page=HtmlParserTool.getPage(visitUrl);
			if(null!=page)
				return page;
		}
		return null;
	}

}
