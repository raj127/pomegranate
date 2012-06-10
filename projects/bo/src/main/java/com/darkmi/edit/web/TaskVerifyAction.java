package com.darkmi.edit.web;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

	/**
	 * 查询
	 * @throws Exception
	 */
	public void search() throws Exception {
		logger.debug("search begin{ ... ");
		//logger.debug("检索关键字为 --> " + keyWords);
		StringBuffer reMessage = new StringBuffer();
		String url = "http://localhost:8080/solr/core0";
		HttpSolrServer server;
		try {
			server = new HttpSolrServer(url);

			//SolrQuery query = new SolrQuery(getKey("安全"));
			SolrQuery query = new SolrQuery("content:安全");
			query.setHighlight(true); //开启高亮组件 
			query.addHighlightField("id");
			query.addHighlightField("chapterName");//高亮字段  
			query.addHighlightField("content");
			query.setHighlightSimplePre("<font color='red'>");//前缀  
			query.setHighlightSimplePost("</font>");//后缀  
			query.set("hl.usePhraseHighlighter", true);
			query.set("hl.highlightMultiTerm", true);
			query.set("hl.snippets", 3);//三个片段,默认是1  
			query.set("hl.fragsize", 50);//每个片段50个字，默认是100  

			query.setStart(0);
			query.setRows(10);

			QueryResponse response = server.query(query);
			SolrDocumentList list = response.getResults();
			Map<String, Map<String, List<String>>> highlightMap = response.getHighlighting();

			for (Iterator<SolrDocument> iterator = list.iterator(); iterator.hasNext();) {
				SolrDocument doc = (SolrDocument) iterator.next();
				String id = doc.getFieldValue("id").toString();
				String hlContent = highlightMap.get(id).get("content").get(0).toString();
				logger.debug(hlContent);

				//logger.debug("-->" + doc.getFieldValue("content").toString());
				reMessage.append("<p>").append(hlContent).append("</p>");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(reMessage.toString());

		logger.debug("search end ...} ");

	}

	public String getKey(String strWord) {
		if (strWord.indexOf(" ") > 0) {
			String wordAnd = strWord.replace(" ", "* AND *");
			String wordOr = strWord.replace(" ", "* *");
			String rt = "(*" + wordAnd + "*) *" + wordOr + "* " + strWord;
			logger.debug("查询字符为 --> {}", rt);
			return rt;
		} else {
			String rt = "*" + strWord + "* " + strWord;
			logger.debug("查询字符为 --> {}", rt);
			return rt;
		}

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
