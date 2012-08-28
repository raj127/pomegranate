package com.darkmi.solr.test;

import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;

/**
 * 测试一下使用SolrJ创建索引.
 * @author Darkmi
 *
 */
public class SolrJOne {
	
	public static void main(String[] args) {
		
		String url = "http://localhost:8088/solr/core1";
		HttpSolrServer server;
		try{
			server = new HttpSolrServer(url);
			SolrInputDocument doc1 = new SolrInputDocument();
			doc1.addField("id", "chuhe");
			doc1.addField("specificationName", "锄禾");
			doc1.addField("chapterName", "李绅");
			doc1.addField("content", "锄禾日当午,汗滴禾下土,谁知盘中餐,粒粒皆辛苦.");
			
			server.add(doc1);
			server.commit();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
