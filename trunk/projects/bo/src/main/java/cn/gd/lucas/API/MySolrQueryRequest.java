package cn.gd.lucas.API;
import org.apache.solr.request.SolrQueryRequestBase;
import org.apache.solr.request.MultiMapSolrParams;
import org.apache.solr.core.SolrCore;
/**
 * 该类表示查询请求，它直接继承自SolrQueryRequestBase,不过为了方便这里的构造函数的
 * 的参数是查询参数（如：q=solr start=5)的MAP对象。
 * 
 */
import java.util.HashMap;
import java.util.Map;
public class MySolrQueryRequest extends SolrQueryRequestBase{
	public MySolrQueryRequest(SolrCore core,Map<String,String[]> map) {
		super(core,new MultiMapSolrParams(map));		
	}
	public MySolrQueryRequest(SolrCore core,String [] keys,String []values){
		this(core,getMapByArray(keys, values));
		
	}
	private static Map<String,String[]> getMapByArray(String [] keys,String []values){
		Map<String,String[]> map=new HashMap<String, String[]>();
		int length=(keys.length>values.length)?values.length:keys.length;
		for(int a=0;a<length;a++){
			map.put(keys[a], new String []{values[a]});
		}
		return map;
	}

}
