package com.darkmi.edit.web;

import java.util.Iterator;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Description: 作业规程校验Action.
 * Copyright (c) www.darkmi.com
 * All Rights Reserved.
 * @version 1.0  2012-05-10 上午09:20:11 DarkMi created
 */
@Namespace("/edit")
public class TaskVerifyAction extends ActionSupport {
	private static final long serialVersionUID = -2907389496513631586L;
	protected Logger logger = LoggerFactory.getLogger(TaskVerifyAction.class);
	private String keyWords;

	private List<SearchResult> srs = Lists.newArrayList();

	@Override
	public String execute() throws Exception {
		return super.execute();
	}

	public String search() {
		logger.debug("search begin{ ... ");		
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
				SearchResult sr = new SearchResult();
				sr.setTitle(solrDocument.getFieldValue("title").toString());
				sr.setDescription(solrDocument.getFieldValue("description").toString());
				sr.setAuthor(solrDocument.getFieldValue("author").toString());
				srs.add(sr);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		

		logger.debug("search end ...} ");
		return SUCCESS;

	}

	/*~~~~~~~~~~~Setters And Getters ~~~~~~~~~~~~~~~~~*/

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}
	
	public List<SearchResult> getSrs() {
		return srs;
	}

	public void setSrs(List<SearchResult> srs) {
		this.srs = srs;
	}

}
