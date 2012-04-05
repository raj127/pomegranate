package com.darkmi.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class ExceptionInterceptor extends AbstractInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionInterceptor.class);

	private static final long serialVersionUID = 7891409748921269691L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String result = null;

		try {
			result = invocation.invoke();
		} catch (ServiceException e) {
			//logger.warn(e.getMessage(), e);
			((ActionSupport) invocation.getAction()).addActionMessage("操作失败!失败的原因是:" + e.getMessage());
			result = CrudActionSupport.RELOAD;
			//throw e;
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
			((ActionSupport) invocation.getAction()).addActionMessage("操作失败!失败的原因是:系统未知异常");
			result = CrudActionSupport.RELOAD;
			//throw e;
		}

		return result;
	}

}
