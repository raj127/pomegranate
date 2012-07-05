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
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.darkmi.edit.service.TaskChapterManager;
import com.darkmi.entity.system.SpecificationChapter;
import com.darkmi.entity.task.TaskChapter;
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
		@Result(name = "right-success", location = "task-verify-right.jsp") })
public class TaskVerifyAction extends ActionSupport {
	private static final long serialVersionUID = -2907389496513631586L;
	private Logger logger = LoggerFactory.getLogger(TaskVerifyAction.class);
	private Long id; //作业规程的ID
	private String sentence; //选中的一行文本
	private TaskChapter tc;
	private List<SpecificationChapter> scList = Lists.newArrayList(); //检索的结果
	private TaskChapterManager tcManager;

	@Override
	public String execute() throws Exception {
		logger.debug("begin execute() { ...");
		logger.debug(sentence);
		logger.debug("end execute() ...} ");
		return super.execute();
	}

	/**
	 * 进入规程校验页面.
	 * @return
	 * @throws Exception
	 */
	public String main() throws Exception {
		logger.debug("begin main() { ...");
		logger.debug("id --> {}", id);
		tc = tcManager.getTaskChapter(id);
		logger.debug(tc.toString());
		logger.debug("end main() ...} ");
		return "main-success";
	}

	/**
	 * 进入右帧.
	 * @return
	 * @throws Exception
	 */
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

		String queryStr = "content:" + sentence;
		logger.debug("查询字符串为-->" + queryStr);
		StringBuffer reMessage = new StringBuffer();
		String url = "http://localhost:8080/solr/core1";
		HttpSolrServer server;
		try {
			server = new HttpSolrServer(url);
			SolrQuery query = new SolrQuery(queryStr);
			//SolrQuery query = new SolrQuery(SolrClient.prepareKeyWords(keyWords));
			query.setHighlight(true); //开启高亮组件 
			//query.addHighlightField("id");
			//query.addHighlightField("chapterName");  
			query.addHighlightField("content");
			query.setHighlightSimplePre("<font color='red'>");
			query.setHighlightSimplePost("</font>");
			query.set("hl.usePhraseHighlighter", true);
			query.set("hl.highlightMultiTerm", true);
			query.set("hl.snippets", 3);
			query.set("hl.fragsize", 9000);

			query.setStart(0);
			query.setRows(10);

			QueryResponse response = server.query(query);
			SolrDocumentList list = response.getResults();
			logger.debug("检索结果的数量为 --》{}", list.size());

			Map<String, Map<String, List<String>>> highlightMap = response.getHighlighting();

			int counter = 0;
			for (Iterator<SolrDocument> iterator = list.iterator(); iterator.hasNext();) {
				//------------------
				//String id = doc.getFieldValue("id").toString();
				
				//logger.debug(hlContent);
				//logger.debug("-->" + doc.getFieldValue("content").toString());
				//reMessage.append("<p>").append(content).append("</p>");
				//reMessage.append("<p>章节：第一节</p>");
				//reMessage.append("<p>来源：煤矿安全作业规范（2010版）</p>");
				//reMessage.append("</div>");
				//------------------
				SolrDocument doc = (SolrDocument) iterator.next();
				String id = prepareResult(doc.getFieldValue("id").toString());
				logger.debug("id --> {}", id);
				String content = prepareResult(doc.getFieldValue("content").toString());
				logger.debug("conent --> {}", content);
				String chapterName = prepareResult(doc.getFieldValue("chapterName").toString());
				String specificationName = prepareResult(doc.getFieldValue("specificationName").toString());
				String hlContent = prepareResult(highlightMap.get(id).get("content").get(0).toString());
				logger.debug("hlContent --> {}", hlContent);

				reMessage.append("<div class=\"record\" onmouseover=\"showOperationButtons('" + counter
						+ "')\" onmouseout=\"hideOperationButtons('" + counter + "')\">");
				reMessage.append("<table id=\"object_" + counter + "\" border=\"0\" width=\"100%\">");
				reMessage.append("<tbody>");
				reMessage.append("<tr>");
				reMessage.append("<td vAlign=\"top\" width=\"50\">#" + counter + "</td>");
				reMessage.append("<td vAlign=\"top\">");
				reMessage.append("<div id=\"operate_" + counter + "\" class=\"operation\">");
				reMessage.append("<a href=\"#\">复制</a> | <a id=\"expand_" + counter + "\" onclick=\"expandText('" + counter
						+ "');return false;\" href=\"#\">隐藏</a>");
				reMessage.append("</div>");
				reMessage.append("<div id=\"text_" + counter + "\" class=\"record_row\">");
				//reMessage.append("<font color=\"#FFF\">" + content + "</font><br><br>");
				reMessage.append(hlContent + "<br><br>");
				reMessage.append("<font color=\"green\">" + "章节：" + chapterName + "</font><br>");
				reMessage.append("<font color=\"green\">" + "来源：" + specificationName + "</font><br>");
				reMessage.append("</div>");
				reMessage.append("</td>");
				reMessage.append("</tr>");
				reMessage.append("</tbody>");
				reMessage.append("</table>");
				reMessage.append("</div>");
				counter++;
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
	
	private String prepareResult(String result){
		return result.replace("[", "").replace("]", "");
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public TaskChapter getTc() {
		return tc;
	}

	public void setTc(TaskChapter tc) {
		this.tc = tc;
	}

	/*~~~~~~~~~~~业务逻辑类注入~~~~~~~~~~~~~~~~~*/
	@Autowired
	public void setTcManager(TaskChapterManager tcManager) {
		this.tcManager = tcManager;
	}

}
