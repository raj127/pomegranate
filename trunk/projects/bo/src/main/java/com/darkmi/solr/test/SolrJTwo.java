package com.darkmi.solr.test;

import java.util.Iterator;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

/**
 * 测试一下使用SolrJ进行查询.
 * @author DarkMi
 *
 */
public class SolrJTwo {
	public static void main(String[] args) {	
		String url = "http://localhost:8983/solr";
		HttpSolrServer server;
		try{
			server = new HttpSolrServer(url);
			SolrQuery query = new SolrQuery();
			query.setQuery("description:锄");
			query.addField("title");
			query.addField("author");
			query.addField("description");
			
			query.setStart(0);
			query.setRows(10);
			
			QueryResponse response = server.query(query);
			SolrDocumentList list = response.getResults();
			for (Iterator<SolrDocument> iterator = list.iterator(); iterator.hasNext();) {
				SolrDocument solrDocument = (SolrDocument) iterator.next();
				System.out.println(solrDocument.getFieldValue("title"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
