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

import com.darkmi.entity.system.SpecificationChapter;
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

	private List<SpecificationChapter> scList = Lists.newArrayList();

	@Override
	public String execute() throws Exception {
		return super.execute();
	}

	public String search() {
		logger.debug("search begin{ ... ");		
		logger.debug("检索关键字为 --> " + keyWords);
		String url = "http://localhost:8080/solr/core0";
		HttpSolrServer server;
		try{
			server = new HttpSolrServer(url);
			SolrQuery query = new SolrQuery();
			query.setQuery("content:" + keyWords);
			query.addField("id");
			query.addField("chapterName");
			query.addField("content");
			
			query.setStart(0);
			query.setRows(10);
			
			QueryResponse response = server.query(query);
			SolrDocumentList list = response.getResults();
			for (Iterator<SolrDocument> iterator = list.iterator(); iterator.hasNext();) {
				SolrDocument solrDocument = (SolrDocument) iterator.next();
				SpecificationChapter chpater = new SpecificationChapter();
				chpater.setId(Long.parseLong(solrDocument.getFieldValue("id").toString()));
				chpater.setName(solrDocument.getFieldValue("chapterName").toString());
				chpater.setContent(solrDocument.getFieldValue("content").toString());
				scList.add(chpater);
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
	
	public List<SpecificationChapter> getScList() {
		return scList;
	}

	public void setScList(List<SpecificationChapter> scList) {
		this.scList = scList;
	}

	

}
