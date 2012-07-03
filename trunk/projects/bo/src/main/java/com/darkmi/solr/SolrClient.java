package com.darkmi.solr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;

import com.darkmi.solr.dto.SpecificationChapterDto;
import com.google.common.collect.Lists;

/**
 * Description: Solr客户端.
 * Copyright (c) www.darkmi.com
 * All Rights Reserved.
 * @version 1.0  2012-06-11 上午09:20:11 DarkMi created
 */
public class SolrClient {
	private static SolrClient solrClient = null;
	private static HttpSolrServer server = null;
	private String url = "http://localhost:8080/solr/core1/";
	private static Logger logger = LoggerFactory.getLogger(SolrClient.class);

	/**
	 * 获取Solr客户端.<br>
	 * 单例模式,同步方法.
	 * @return
	 */
	public static synchronized SolrClient getInstance() {
		if (solrClient == null) {
			solrClient = new SolrClient();
		}
		return solrClient;
	}

	/**
	 * 连接到Solr服务器.
	 * @return
	 */
	public HttpSolrServer connect() {
		logger.debug("开始连接 {...");
		try {
			if (server == null) {
				logger.debug("url -->{}", url);
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
		logger.debug("结束连接...}");
		return server;
	}

	/**
	 * 启动Solr客户端 
	 */
	public void start() {
		logger.debug("开始启动Solr客服端  {");
		logger.debug("结束启动Solr客服端  }");
	}

	/**
	 * 为单个文档创建索引.
	 */
	public static void createIndex(SpecificationChapterDto scDto) {
		try {
			//获取连接服务
			HttpSolrServer solrServer = SolrClient.getInstance().connect();
			SolrInputDocument doc1 = new SolrInputDocument();
			doc1.addField("id", scDto.getId());
			doc1.addField("chapterName", scDto.getChapterName());
			doc1.addField("content", scDto.getContent());
			doc1.addField("specificationName", scDto.getSpecificationName());
			logger.debug("doc -->{}", doc1.toString());
			solrServer.add(doc1);
			solrServer.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 为单个文档创建索引.
	 */
	public static void createIndex(List<SpecificationChapterDto> scDtos) {
		try {
			List<SolrInputDocument> siDocs = Lists.newArrayList();
			for (SpecificationChapterDto scDto : scDtos) {
				SolrInputDocument doc = new SolrInputDocument();
				doc.addField("id", scDto.getId());
				doc.addField("chapterName", scDto.getChapterName());
				doc.addField("content", scDto.getContent());
				doc.addField("specificationName", scDto.getSpecificationName());
				//logger.debug("doc -->{}", doc.toString());				
				siDocs.add(doc);
			}

			//获取连接服务
			HttpSolrServer solrServer = SolrClient.getInstance().connect();
			solrServer.add(siDocs);
			solrServer.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除指定ID的索引.
	 */
	public static void deleteIndexById(String id) {
		HttpSolrServer solrServer = SolrClient.getInstance().connect();
		try {
			solrServer.deleteById(id);
			solrServer.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除指定ID 列表的索引.
	 */
	public static void deleteIndexByIds(List<String> ids) {
		HttpSolrServer solrServer = SolrClient.getInstance().connect();
		try {
			solrServer.deleteById(ids);
			solrServer.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除所有索引.
	 */
	public static void deleteAllIndex() {
		HttpSolrServer solrServer = SolrClient.getInstance().connect();
		try {
			solrServer.deleteByQuery("*:*");
			solrServer.commit();
			logger.debug("所有索引删除完毕..............");
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 检索.
	 */
	public static Page<SpecificationChapterDto> search(Page<SpecificationChapterDto> page, String keyWords) {
		List<SpecificationChapterDto> scList = new ArrayList<SpecificationChapterDto>();
		SpecificationChapterDto scDto = null;

		//获取Solr Server实例
		HttpSolrServer solrServer = SolrClient.getInstance().connect();
		//创建一个查询实体
		SolrQuery sQuery = new SolrQuery();

		//预处理关键词
		String queryStr = prepareKeyWords(keyWords);
		//查询参数处理
		sQuery.setQuery(queryStr);
		//分页设置
		sQuery.setStart(page.getFirst() - 1);
		sQuery.setRows(page.getPageSize());

		//设置排序字段
		//sQuery.addSortField("blogId", ORDER.asc);

		//设置高亮
		sQuery.setHighlight(true); // 开启高亮组件
		sQuery.addHighlightField("content");// 高亮字段
		sQuery.setHighlightSimplePre("<font color='red'>");//标记，高亮关键字前缀
		sQuery.setHighlightSimplePost("</font>");//后缀
		sQuery.setHighlightSnippets(2);//结果分片数，默认为1
		sQuery.setHighlightFragsize(1000);//每个分片的最大长度，默认为100
		//分片信息
		sQuery.setFacet(true).setFacetMinCount(1).setFacetLimit(5).addFacetField("content");//分片字段

		try {
			QueryResponse response = solrServer.query(sQuery);
			SolrDocumentList list = response.getResults();
			Integer totalCount = (int) list.getNumFound();
			logger.debug("totalCount:" + totalCount);
			page.setTotalCount(totalCount);
			//获取所有高亮的字段
			Map<String, Map<String, List<String>>> highlightMap = response.getHighlighting();
			String scId = "";
			for (SolrDocument solrDocument : list) {
				scDto = new SpecificationChapterDto();
				scId = solrDocument.getFieldValue("id").toString();
				scDto.setId(scId);
				scDto.setSpecificationName(solrDocument.getFieldValue("chapterName").toString());
				scDto.setChapterName(solrDocument.getFieldValue("specificationName").toString());

				List<String> contentList = highlightMap.get(scId).get("content");
				if (contentList != null && contentList.size() > 0) {
					scDto.setContent(contentList.get(0));
				} else {
					//获取并设置高亮的字段content
					scDto.setContent(solrDocument.getFieldValue("content").toString());
				}
				scList.add(scDto);
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		page.setResult(scList);
		return page;
	}

	public static String prepareKeyWords(String keyWords) {
		String result = "";
		if (StringUtils.isBlank(keyWords)) {
			logger.debug("关键词为空,输出所有索引..........");
			return "content";
		} else {
			String[] words = StringUtils.split(keyWords, ' ');
			for (String word : words) {
				result = result + "content:" + word + " AND ";
			}
			result = StringUtils.substringBeforeLast(result, " AND ");
			logger.debug("查询字符串为 --> {}", result);
			return result;
		}
	}

	/*~~~~~~~~~~~业务逻辑类注入~~~~~~~~~~~~~~~~~*/
	@Autowired
	public void setUrl(String url) {
		logger.debug("set url --> {}", url);
		this.url = url;
	}

}
