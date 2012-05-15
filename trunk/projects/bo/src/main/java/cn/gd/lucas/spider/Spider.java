package cn.gd.lucas.spider;

import java.util.Set;

import org.htmlparser.util.ParserException;

/**
 * 
 * @author Administrator
 * ����Ľ��棬
 */
public class Spider {
	/**
	 * url Ϊ�������ʼ����
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
	 * ���������ȡ����һ����ҳ
	 * @return
	 * @throws ParserException 
	 */
	public Page nextPage(){
		while(!linkDB.unVisitedUrlsEmpty()&&linkDB.getVisitedUrlNum()<=500){
			//��ͷ URL ����
			String visitUrl=linkDB.unVisitedUrlDeQueue();
			if(visitUrl==null)
				return null;
			
			linkDB.addVisitedUrl(visitUrl);
			//��ȡ��������ҳ�е� URL
			
			Set<String> links=HtmlParserTool.extracLinks(visitUrl,filter);
			//�µ�δ���ʵ� URL ���
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
