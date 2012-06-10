package com.darkmi.solr;

import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SolrClient {
	private HttpSolrServer server = null;
	private String url ;
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 启动Solr客户端 
	 */
	public void init(){
		logger.debug("开始启动Solr客服端  {");
		try {
			if (server == null) {
				server = new HttpSolrServer(url);
				server.setSoTimeout(1000); // socket read timeout
				server.setConnectionTimeout(1000);
				server.setDefaultMaxConnectionsPerHost(100);
				server.setMaxTotalConnections(100);
				server.setFollowRedirects(false); // defaults to false
				//allowCompression defaults to false.
				//Server side must support gzip or deflate for this to have any effect.
				server.setAllowCompression(true);
				server.setMaxRetries(1); // defaults to 0.  > 1 not recommended.
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug("结束启动Solr客服端  }");
	}
	
	/**
	 * 创建索引
	 */
	public void createIndex(){
		
	}
	
	/**
	 * 删除索引
	 */
	public void deleteIndex(){
		
	}

}
