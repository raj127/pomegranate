package com.darkmi.solr;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestSolrServer {
	private static Logger logger = LoggerFactory.getLogger(TestSolrServer.class);
	private static TestSolrServer solrServer = null;
	private static HttpSolrServer server = null;
	private static String url = "http://localhost:8080/solr/core3/";

	public static synchronized TestSolrServer getInstance() {
		if (solrServer == null) {
			solrServer = new TestSolrServer();
		}
		return solrServer;
	}

	public static HttpSolrServer getServer() {
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

	public void writerBlog(BlogsDO blog) {
		try {
			blog.setId(SerialNumberUtil.getRandomNum(4));
			//获取连接服务
			HttpSolrServer solrServer = TestSolrServer.getInstance().getServer();
			SolrInputDocument doc1 = new SolrInputDocument();
			doc1.addField("id", SerialNumberUtil.getRandomNum(4));
			doc1.addField("blogId", blog.getBlogsId());
			doc1.addField("title", blog.getTitle());
			doc1.addField("bTypeId", blog.getbTypeId());
			doc1.addField("bTypeName", blog.getbTypeName());
			doc1.addField("content", blog.getContent());
			String createTime = DateUtils.formatDate(blog.getGmtCreate(), "yyyyMMddHHmmss");
			doc1.addField("createTime", createTime);
			doc1.addField("nickName", blog.getNickName());
			solrServer.add(doc1);
			solrServer.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<BlogsDO> searchBlogsList(String content, String bTypeId, String sDate, String eDate, Page page)
			throws Exception {
		List<BlogsDO> blogList = new ArrayList<BlogsDO>();
		BlogsDO blogsDO = null;
		HttpSolrServer solrServer = TestSolrServer.getInstance().getServer();
		SolrQuery sQuery = new SolrQuery();
		String para = "";
		//OR 或者  OR 一定要大写
		if (StringUtils.isNotEmpty(content)) {
			para = para + "(title:" + content + " OR content:" + content + ")";
			//空格 等同于 OR
			// para=para+"(title:"+content+"  content:"+content+")";
		}
		//AND 并且  AND一定要大写
		if (!bTypeId.equals("-1")) {
			if (StringUtils.isNotEmpty(para)) {
				para = para + " AND bTypeId:" + bTypeId;
			} else {
				para = para + "  bTypeId:" + bTypeId;
			}
		}
		if (StringUtils.isNotEmpty(sDate) && StringUtils.isNotEmpty(eDate)) {
			if (StringUtils.isNotEmpty(para)) {
				para = para + " AND createTime:[" + sDate + " TO " + eDate + "]";
			} else {
				para = para + " createTime:[" + sDate + " TO " + eDate + "]";
			}
		}
		//查询name包含solr apple
		//sQuery.setQuery("name:solr,apple");
		//manu不包含inc
		//sQuery.setQuery("name:solr,apple NOT manu:inc");
		//50 <= price <= 200
		//sQuery.setQuery("price:[50 TO 200]");
		//sQuery.setQuery("popularity:[5 TO 6]");
		//params.setQuery("price:[50 TO 200] - popularity:[5 TO 6]");
		//params.setQuery("price:[50 TO 200] + popularity:[5 TO 6]");
		//50 <= price <= 200 AND 5 <= popularity <= 6
		//sQuery.setQuery("price:[50 TO 200] AND popularity:[5 TO 6]");
		//sQuery.setQuery("price:[50 TO 200] OR popularity:[5 TO 6]");

		// 查询关键词，*:*代表所有属性、所有值，即所有index
		if (!StringUtils.isNotEmpty(para)) {
			para = "*:*";
		}
		logger.info("para:" + para);
		sQuery.setQuery(para);
		//设置分页  start=0就是从0开始，，rows=5当前返回5条记录，第二页就是变化start这个值为5就可以了。
		sQuery.setStart((page.getCurrentPage() - 1) * page.getPerPageSize());
		sQuery.setRows(page.getPerPageSize());
		//排序 如果按照blogId 排序，，那么将blogId desc(or asc) 改成 id desc(or asc)
		sQuery.addSortField("blogId", ORDER.asc);

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
			page.setCounts(counts);
			//获取所有高亮的字段
			Map<String, Map<String, List<String>>> highlightMap = response.getHighlighting();
			String blogId = "";
			for (SolrDocument solrDocument : list) {
				
				blogsDO = new BlogsDO();
				blogId = solrDocument.getFieldValue("blogId").toString();
				blogsDO.setBlogsId(Integer.valueOf(blogId));
				blogsDO.setbTypeId(solrDocument.getFieldValue("bTypeId").toString());
				blogsDO.setbTypeName(solrDocument.getFieldValue("bTypeName").toString());
				blogsDO.setNickName(solrDocument.getFieldValue("nickName").toString());
				
				List<String> titleList = highlightMap.get(blogId).get("title");
				List<String> contentList = highlightMap.get(blogId).get("content");
				
				if (titleList != null && titleList.size() > 0) {
					blogsDO.setTitle(titleList.get(0));
				} else {
					//获取并设置高亮的字段title
					blogsDO.setTitle(solrDocument.getFieldValue("title").toString());
				}
				
				if (contentList != null && contentList.size() > 0) {
					blogsDO.setContent(contentList.get(0));
				} else {
					//获取并设置高亮的字段content
					blogsDO.setContent(solrDocument.getFieldValue("content").toString());
				}
				
				blogsDO.setRevDate(solrDocument.getFieldValue("createTime").toString());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				try {
					blogsDO.setGmtCreate(sdf.parse(solrDocument.getFieldValue("createTime").toString()));
				} catch (java.text.ParseException e) {
					e.printStackTrace();
				}
				blogList.add(blogsDO);
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		return blogList;
	}
}
