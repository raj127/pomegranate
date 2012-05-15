package cn.gd.lucas.API;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import org.apache.solr.common.util.ContentStream;
import org.apache.solr.core.CoreContainer;
import org.apache.solr.core.SolrCore;
import org.apache.solr.core.SolrException;
import org.apache.solr.request.MultiMapSolrParams;
import org.apache.solr.request.QueryResponseWriter;
import org.apache.solr.request.SolrQueryRequest;
import org.apache.solr.request.SolrQueryResponse;
import org.apache.solr.request.SolrRequestHandler;
import org.apache.solr.request.XMLResponseWriter;
import org.apache.solr.servlet.SolrRequestParsers;

import cn.gd.lucas.util.XmlTool;




public class DirectAPI {
	public static Logger log = Logger.getLogger(DirectAPI.class
			.getName());
	
   public static String  addIndex(String db,String id,String url,String title,String text) throws Throwable{
	   String xmlQA = "<add><doc><field name=\"id\">" + id
		+ "</field><field name=\"url\">" + url
		+ "</field><field name=\"title\">" + title
		+ "</field><field name=\"text\">" + text
		+ "</field></doc></add>";
	   InputStream inputStream=new ByteArrayInputStream(xmlQA.getBytes("utf-8"));
	   return updateIndexByXmlInputStream(inputStream, db);
	   
	   
   }
  
   public static String query(String db,String q,String start,String rows,String ident) throws Throwable{
	   
		Map<String, String[]> params = new HashMap<String, String[]>();
		String[] qArray = { q };
		params.put("q", qArray);
		String[] startArray = { start };
		params.put("start", startArray);
		String[] identArray = { ident };
		params.put("indent", identArray);
		String[] dbArray = { db };
		params.put("db", dbArray);
		String[] versionArray = { "2.2" };
		params.put("version", versionArray);
		String[] rowsArray = { rows };
		params.put("rows", rowsArray);
		// get core
		SolrCore core = null;
		core = CoreContainer.getSolrCores().getCore(db);
		if (null != core) {
			// TODO 可能还要在这里添加qt参数，然后下面用QT获取处理器
			MySolrQueryRequest req = new MySolrQueryRequest(core, params);
			//System.out.println(req.toString());
			// 调用处理请求的方法并返回结果
			return handleAndReturnXml(req);
		} else {
			throw new Exception("unknowed core " + db);
		}
	   
   }
   private static String handleAndReturnXml(MySolrQueryRequest req ) throws Throwable{
	    String db = req.getParam("db");
		SolrCore core = null;
		try {
			core = CoreContainer.getSolrCores().getCore(db);
			SolrQueryResponse rsp = new SolrQueryResponse();

			SolrRequestHandler handler = core.getRequestHandler("standard");

			if (handler == null) {
				log.warning("Unknown Request Handler '" + req.getQueryType()
						+ "' :" + req);
				throw new SolrException(SolrException.ErrorCode.BAD_REQUEST,
						"Unknown Request Handler ", true);

			}
			core.execute(handler, req, rsp);

			if (rsp.getException() == null) {
				// 没有错误的情况的处理，将结果填充到out中
				QueryResponseWriter responseWriter = core
						.getQueryResponseWriter(req);
				StringWriter out = new StringWriter();
				
				responseWriter.write(out, req, rsp);
				return out.toString();

			} else {
				// 出错的时候，添加出错信息
				Exception e = rsp.getException();
				throw e;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			req.close();
		}
   }
   public static MyQueryResult queryAndReturnMyQueryResult(String db,String q,String start,String rows,String ident) throws Throwable{
	   String result=query(db, q, start, rows, ident);
	  // System.out.println("结果："+result);
	   return XmlTool.getMyQueryResult(result);
   }
   public static void  deleteIndex(String db) throws Throwable{
	   String xml="<delete><query>*:*</query></delete>";
	   InputStream inputStream=new ByteArrayInputStream(xml.getBytes("utf-8"));
	   updateIndexByXmlInputStream(inputStream, db);
	   
   }
   public static String updateIndexByXmlInputStream(InputStream inputStream,
			String db) throws Throwable{
	  
	    Map<String, String[]> map = new HashMap<String, String[]>();
		MultiMapSolrParams params = new MultiMapSolrParams(map);
		MyContentStream contentStream = new MyContentStream(inputStream);
		ArrayList<ContentStream> streams = new ArrayList<ContentStream>(1);
		streams.add(contentStream);
		SolrCore core = null;//与库名有关的核
		SolrQueryRequest req = null;//更新请求
		SolrQueryRequest commitReq = null;//提交请求
		String result = "";
		try {
			core = CoreContainer.getSolrCores().getCore(db);
			//更新部分 
			//根据core params streams 构建查询对象
			req = new SolrRequestParsers(core.getSolrConfig())
					.buildRequestFrom(core, params, streams);
			SolrQueryResponse rsp = new SolrQueryResponse();
			SolrRequestHandler handler = core.getRequestHandler("/update");
			core.execute(handler, req, rsp);
			XMLResponseWriter writer = new XMLResponseWriter();
			StringWriter sw = new StringWriter();
			writer.write(sw, req, rsp);
			result = sw.toString();
			// 提交部分
			MultiMapSolrParams commitParams = new MultiMapSolrParams(map);
			ArrayList<ContentStream> commitStreams = new ArrayList<ContentStream>(
					1);
			InputStream commitInputStream = new ByteArrayInputStream(
					"<commit waitFlush=\"true\" waitSearcher=\"false\"/>"
							.getBytes("utf-8"));
			commitStreams.add(new MyContentStream(commitInputStream));
			commitReq = new SolrRequestParsers(core.getSolrConfig())
					.buildRequestFrom(core, commitParams, commitStreams);
			SolrQueryResponse commiRsp = new SolrQueryResponse();
			core.execute(handler, commitReq, commiRsp);
			StringWriter sw2 = new StringWriter();
			new XMLResponseWriter().write(sw2, commitReq, commiRsp);
			result = sw2.toString();
			return result;
		} catch (Throwable e) {
			throw e;
		} finally {
			if (null != req)
				req.close();
			if (null != core)
				core.close();

		}
	   
   }

}
