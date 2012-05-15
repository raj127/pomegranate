package cn.gd.lucas.spider;

import java.util.HashSet;
import java.util.Set;

/**
 * ���������Ѿ����ʹ� Url �ʹ����ʵ� Url ����
 */
public class LinkDB {
	//Ӧ�������ֹ����Ĳ���

	//�ѷ��ʵ� url ����
	private  Set<String> visitedUrl = new HashSet<String>();
	//�����ʵ� url ����
	private  Queue<String> unVisitedUrl = new Queue<String>();

	
	public  Queue<String> getUnVisitedUrl() {
		return unVisitedUrl;
	}

	public  void addVisitedUrl(String url) {
		visitedUrl.add(url);
	}

	public  void removeVisitedUrl(String url) {
		visitedUrl.remove(url);
	}

	public  String unVisitedUrlDeQueue() {
		return unVisitedUrl.deQueue();
	}

	// ��֤ÿ�� url ֻ������һ��
	public  void addUnvisitedUrl(String url) {
		if (url != null && !url.trim().equals("")
 && !visitedUrl.contains(url)
				&& !unVisitedUrl.contians(url)&&unVisitedUrl.size()<500)
			unVisitedUrl.enQueue(url);
	}

	public  int getVisitedUrlNum() {
		return visitedUrl.size();
	}

	public  boolean unVisitedUrlsEmpty() {
		return unVisitedUrl.empty();
	}
}
