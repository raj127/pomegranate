package cn.gd.lucas.API;

import java.util.HashMap;
import java.util.Map;

import org.apache.solr.core.SolrCore;
import org.apache.solr.request.MultiMapSolrParams;
import org.apache.solr.request.SolrQueryRequestBase;

public class MySolrQueryRequest extends SolrQueryRequestBase {
	public MySolrQueryRequest(SolrCore core, Map<String, String[]> map) {
		super(core, new MultiMapSolrParams(map));
	}

	public MySolrQueryRequest(SolrCore core, String[] keys, String[] values) {
		this(core, getMapByArray(keys, values));

	}

	private static Map<String, String[]> getMapByArray(String[] keys, String[] values) {
		Map<String, String[]> map = new HashMap<String, String[]>();
		int length = (keys.length > values.length) ? values.length : keys.length;
		for (int a = 0; a < length; a++) {
			map.put(keys[a], new String[] { values[a] });
		}
		return map;
	}

}
