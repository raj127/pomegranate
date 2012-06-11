package com.darkmi.solr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import com.darkmi.solr.dto.SpecificationChapterDto;
import com.darkmi.solr.test.StringUtils;

/**
 * Description: Solr客户端.
 * Copyright (c) www.darkmi.com
 * All Rights Reserved.
 * @version 1.0  2012-06-11 上午09:20:11 DarkMi created
 */
public class SolrClient {
	private static SolrClient solrClient = null;
	private static HttpSolrServer server = null;
	private String url;
	private Logger logger = LoggerFactory.getLogger(SolrClient.class);

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
		return server;
	}

	/**
	 * 启动Solr客户端 
	 */
	public void start() {
		logger.debug("开始启动Solr客服端  {");
		//		try {
		//			if (server == null) {
		//				server = new HttpSolrServer(url);
		//				server.setSoTimeout(1000); // socket read timeout
		//				server.setConnectionTimeout(1000);
		//				server.setDefaultMaxConnectionsPerHost(100);
		//				server.setMaxTotalConnections(100);
		//				server.setFollowRedirects(false); // defaults to false
		//				//allowCompression defaults to false.
		//				//Server side must support gzip or deflate for this to have any effect.
		//				server.setAllowCompression(true);
		//				server.setMaxRetries(1); // defaults to 0.  > 1 not recommended.
		//			}
		//		} catch (Exception e) {
		//			e.printStackTrace();
		//		}
		logger.debug("结束启动Solr客服端  }");
	}

	/**
	 * 创建索引.
	 */
	public static void createIndex(SpecificationChapterDto scDto) {
		try {
			//获取连接服务
			HttpSolrServer solrServer = SolrClient.getInstance().connect();
			SolrInputDocument doc1 = new SolrInputDocument();
			doc1.addField("id", scDto.getId());
			doc1.addField("blogId", scDto.getChapterName());
			doc1.addField("title", scDto.getContent());
			solrServer.add(doc1);
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
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 检索.
	 */
	public List<SpecificationChapterDto> search(String sentence) {
		List<SpecificationChapterDto> scList = new ArrayList<SpecificationChapterDto>();
		SpecificationChapterDto scDto = null;

		HttpSolrServer solrServer = SolrClient.getInstance().connect();
		SolrQuery sQuery = new SolrQuery();
		String para = "";
		//OR 或者  OR 一定要大写
		if (StringUtils.isNotEmpty(sentence)) {
		}

		//AND 并且  AND一定要大写

		// 查询关键词，*:*代表所有属性、所有值，即所有index
		if (!StringUtils.isNotEmpty(para)) {
			para = "*:*";
		}

		logger.info("para:" + para);
		sQuery.setQuery(para);
		//设置分页  start=0就是从0开始，，rows=5当前返回5条记录，第二页就是变化start这个值为5就可以了。
		//sQuery.setStart((page.getCurrentPage() - 1) * page.getPerPageSize());
		//sQuery.setRows(page.getPerPageSize());
		//排序 如果按照blogId 排序，，那么将blogId desc(or asc) 改成 id desc(or asc)
		//sQuery.addSortField("blogId", ORDER.asc);

		//设置高亮
		sQuery.setHighlight(true); // 开启高亮组件
		sQuery.addHighlightField("content");// 高亮字段
		sQuery.addHighlightField("title");// 高亮字段
		sQuery.setHighlightSimplePre("<font color='red'>");//标记，高亮关键字前缀
		sQuery.setHighlightSimplePost("</font>");//后缀
		sQuery.setHighlightSnippets(2);//结果分片数，默认为1
		sQuery.setHighlightFragsize(1000);//每个分片的最大长度，默认为100

		//分片信息
		sQuery.setFacet(true).setFacetMinCount(1).setFacetLimit(5).addFacetField("content");//分片字段

		try {
			QueryResponse response = solrServer.query(sQuery);
			SolrDocumentList list = response.getResults();
			Integer counts = (int) list.getNumFound();
			logger.info("counts:" + counts);
			//page.setCounts(counts);
			//获取所有高亮的字段
			Map<String, Map<String, List<String>>> highlightMap = response.getHighlighting();
			String scId = "";
			for (SolrDocument solrDocument : list) {
				scDto = new SpecificationChapterDto();
				scId = solrDocument.getFieldValue("id").toString();
				scDto.setId(scId);
				scDto.setChapterName(solrDocument.getFieldValue("chapterName").toString());
				scDto.setChapterName(solrDocument.getFieldValue("content").toString());

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
		return scList;

	}

	/*~~~~~~~~~~~业务逻辑类注入~~~~~~~~~~~~~~~~~*/
	@Autowired
	public void setUrl(String url) {
		this.url = url;
	}

}
