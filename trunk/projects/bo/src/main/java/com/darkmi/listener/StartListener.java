package com.darkmi.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.darkmi.solr.SolrClient;

/**
 * Description: 启动监听器.
 * Copyright (c) www.darkmi.com
 * All Rights Reserved.
 * @version 1.0  2012-06-11 上午09:20:11 DarkMi created.
 */
public class StartListener implements ServletContextListener {
	private static Logger logger = LoggerFactory.getLogger(StartListener.class);

	/**
	 * 上下文初始化.
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		//启动RM SEERVER
		try {
			ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
			SolrClient solrClient = (SolrClient) context.getBean("solrClient");
			solrClient.start();
			logger.debug("Solr Client 启动。。。。。。。。。。。");
		} catch (Exception e) {
			logger.error("Solr Client 启动错误", e);
		}
	}

	/**
	 * 上下文卸载.
	 */
	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}

}