package com.darkmi.edit.web;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.struts2.ServletActionContext;
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
	private String sentence;

	private List<SpecificationChapter> scList = Lists.newArrayList();

	@Override
	public String execute() throws Exception {
		logger.debug("begin execute() { ...");
		logger.debug(sentence);
		logger.debug("end execute() ...} ");
		return super.execute();
	}
	
	public void testSearch() throws Exception{
		logger.debug("begin testSearch() { ...");
		HttpServletResponse response = ServletActionContext.getResponse(); 
        response.setCharacterEncoding("UTF-8");      
        response.getWriter().write(sentence);
        logger.debug("end testSearch() ...} ");
	}

	public void search() throws Exception{
		logger.debug("search begin{ ... ");		
		//logger.debug("检索关键字为 --> " + keyWords);
		StringBuffer reMessage = new StringBuffer(); 
		String url = "http://localhost:8080/solr/core0";
		HttpSolrServer server;
		try{
			server = new HttpSolrServer(url);
			SolrQuery query = new SolrQuery();
			query.setQuery("content:" + "安全");
			query.addField("id");
			query.addField("chapterName");
			query.addField("content");
			
			query.setStart(0);
			query.setRows(10);
			
			QueryResponse response = server.query(query);
			SolrDocumentList list = response.getResults();
			for (Iterator<SolrDocument> iterator = list.iterator(); iterator.hasNext();) {
				SolrDocument solrDocument = (SolrDocument) iterator.next();
				reMessage.append("<p>").append(solrDocument.getFieldValue("content").toString()).append("</p>");
				
				//SpecificationChapter chpater = new SpecificationChapter();
				//chpater.setId(Long.parseLong(solrDocument.getFieldValue("id").toString()));
				//chpater.setName(solrDocument.getFieldValue("chapterName").toString());
				//chpater.setContent(solrDocument.getFieldValue("content").toString());
				//scList.add(chpater);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		HttpServletResponse response = ServletActionContext.getResponse(); 
        response.setCharacterEncoding("UTF-8");      
        response.getWriter().write(reMessage.toString());


		logger.debug("search end ...} ");

	}

	/*~~~~~~~~~~~Setters And Getters ~~~~~~~~~~~~~~~~~*/

	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	
	public List<SpecificationChapter> getScList() {
		return scList;
	}

	public void setScList(List<SpecificationChapter> scList) {
		this.scList = scList;
	}

	

}
