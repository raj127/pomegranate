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
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
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
@Results({ @Result(name = "main-success", location = "task-verify-main.jsp"),
		@Result(name = "top-success", location = "task-verify-top.jsp"),
		@Result(name = "left-success", location = "task-verify-left.jsp"),
		@Result(name = "right-success", location = "task-verify-right.jsp") })
public class TaskVerifyAction extends ActionSupport {
	private static final long serialVersionUID = -2907389496513631586L;
	private Logger logger = LoggerFactory.getLogger(TaskVerifyAction.class);
	private String sentence;

	private List<SpecificationChapter> scList = Lists.newArrayList();

	@Override
	public String execute() throws Exception {
		logger.debug("begin execute() { ...");
		logger.debug(sentence);
		logger.debug("end execute() ...} ");
		return super.execute();
	}

	public String main() throws Exception {
		logger.debug("begin main() { ...");
		logger.debug("end main() ...} ");
		return "main-success";
	}

	public String top() throws Exception {
		logger.debug("begin top() { ...");

		logger.debug("end top() ...} ");
		return "top-success";
	}

	public String left() throws Exception {
		logger.debug("begin left() { ...");

		logger.debug("end left() ...} ");
		return "left-success";
	}

	public String right() throws Exception {
		logger.debug("begin right() { ...");

		logger.debug("end right() ...} ");
		return "right-success";
	}

	/**
	 * 查询
	 * @throws Exception
	 */
	public void search() throws Exception {
		logger.debug("search begin{ ... ");
		//String keyWords = new Complex().doit(sentence);

		//logger.debug("检索关键字为 --> " + keyWords);
		StringBuffer reMessage = new StringBuffer();
		String url = "http://localhost:8080/solr/core0";
		HttpSolrServer server;
		try {
			server = new HttpSolrServer(url);

			SolrQuery query = new SolrQuery("content:煤矿");
			//SolrQuery query = new SolrQuery(SolrClient.prepareKeyWords(keyWords));
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

			//Map<String, Map<String, List<String>>> highlightMap = response.getHighlighting();

			int id = 0;
			for (Iterator<SolrDocument> iterator = list.iterator(); iterator.hasNext();) {
				//------------------
				//String id = doc.getFieldValue("id").toString();
				//String hlContent = highlightMap.get(id).get("content").get(0).toString();
				//logger.debug(hlContent);
				//logger.debug("-->" + doc.getFieldValue("content").toString());
				//reMessage.append("<p>").append(content).append("</p>");
				//reMessage.append("<p>章节：第一节</p>");
				//reMessage.append("<p>来源：煤矿安全作业规范（2010版）</p>");
				//reMessage.append("</div>");
				//------------------
				SolrDocument doc = (SolrDocument) iterator.next();
				String content = doc.getFieldValue("content").toString();

				reMessage.append("<div class=\"record\" onmouseover=\"showOperationButtons('" + id
						+ "')\" onmouseout=\"hideOperationButtons('" + id + "')\">");
				reMessage.append("<table id=\"object_" + id + "\" border=\"0\" width=\"100%\">");
				reMessage.append("<tbody>");
				reMessage.append("<tr>");
				reMessage.append("<td vAlign=\"top\" width=\"50\">#" + id + "</td>");
				reMessage.append("<td vAlign=\"top\">");
				reMessage.append("<div id=\"operate_" + id + "\" class=\"operation\">");
				reMessage.append("<a href=\"#\">复制</a> | <a id=\"expand_" + id + "\" onclick=\"expandText('" + id
						+ "');return false;\" href=\"#\">隐藏</a>");
				reMessage.append("</div>");
				reMessage.append("<div id=\"text_" + id + "\" class=\"record_row\">");
				reMessage.append("<font color=\"#dd0000\">" + content + "</font><br><br>");
				reMessage.append("<font color=\"green\">" + "章节：第一节" + "</font><br>");
				reMessage.append("<font color=\"green\">" + "来源：煤矿安全作业规范（2010版）" + "</font><br>");
				reMessage.append("</div>");
				reMessage.append("</td>");
				reMessage.append("</tr>");
				reMessage.append("</tbody>");
				reMessage.append("</table>");
				reMessage.append("</div>");
				id++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug(reMessage.toString());
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
