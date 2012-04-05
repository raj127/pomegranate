package com.darkmi.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * Description: 去掉查询参数的空格
 * Copyright (c) www.darkmi.com
 * All Rights Reserved.
 * @version 1.0  2010-12-6 下午05:24:40 darkmi created
 */
public class SpaceInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -3685422261445581556L;
	protected Logger logger = LoggerFactory.getLogger(SpaceInterceptor.class);

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		Map<String, Object> parameters = invocation.getInvocationContext().getParameters();

		Iterator<Entry<String, Object>> iter = parameters.entrySet().iterator();
		while (iter.hasNext()) {

			Map.Entry<String, Object> entry = iter.next();

			if (entry.getValue() instanceof String[]) {

				String[] values = (String[]) entry.getValue();
				String value = values[0];

				//	logger.debug("value:{}", value);
				if (value != null && (value.endsWith(" ") || value.startsWith(" "))) {

					entry.setValue(value.trim());
					//	logger.debug("end value:{}", entry.getValue());
				}
			}

		}
		return invocation.invoke();
	}

}
