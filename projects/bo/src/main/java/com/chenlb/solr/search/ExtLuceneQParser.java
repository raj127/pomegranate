package com.chenlb.solr.search;

import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.solr.common.params.CommonParams;
import org.apache.solr.common.params.SolrParams;
import org.apache.solr.request.SolrQueryRequest;
import org.apache.solr.search.QParser;
import org.apache.solr.search.QueryParsing;
import org.apache.solr.search.SolrQueryParser;

public class ExtLuceneQParser extends QParser {

	private SolrQueryParser lparser;

	public ExtLuceneQParser(String qstr, SolrParams localParams, SolrParams params, SolrQueryRequest req) {
		super(qstr, localParams, params, req);
	}

	//===============copy from solr 1.4 org.apache.solr.search.LuceneQParser ===============//
	public Query parse() throws ParseException {
		String qstr = getString();

		String defaultField = getParam(CommonParams.DF);
		if (defaultField == null) {
			defaultField = getReq().getSchema().getDefaultSearchFieldName();
		}
		lparser = new ExtSolrQueryParser(this, defaultField);//change by chenlb

		// these could either be checked & set here, or in the SolrQueryParser constructor
		String opParam = getParam(QueryParsing.OP);
		if (opParam != null) {
			lparser.setDefaultOperator("AND".equals(opParam) ? QueryParser.Operator.AND : QueryParser.Operator.OR);
		} else {
			// try to get default operator from schema
			@SuppressWarnings("deprecation")
			QueryParser.Operator operator = getReq().getSchema().getSolrQueryParser(null).getDefaultOperator();
			lparser.setDefaultOperator(null == operator ? QueryParser.Operator.OR : operator);
		}

		return lparser.parse(qstr);
	}

	public String[] getDefaultHighlightFields() {
		return new String[] { lparser.getField() };
	}
	//================end=======================//
}
