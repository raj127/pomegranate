package com.darkmi.system.web;

import java.util.Iterator;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;

import com.darkmi.entity.system.SpecificationChapter;
import com.darkmi.solr.SolrClient;
import com.darkmi.solr.dto.SpecificationChapterDto;
import com.darkmi.system.service.SpecificationChapterManager;
import com.google.common.collect.Lists;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Description: 系统管理--》规范索引维护Action.
 * Copyright (c) darkmi
 * All Rights Reserved.
 * @version 1.0  2012-05-23 下午01:36:31 darkmi created
 */
@Namespace("/system")
public class SpecificationChapterIndexAction extends ActionSupport {
	private static final long serialVersionUID = -2075969594416637932L;
	private Logger logger = LoggerFactory.getLogger(SpecificationChapterIndexAction.class);
	private String keyWords;
	private SpecificationChapterManager scManager;
	private Page<SpecificationChapterDto> page = new Page<SpecificationChapterDto>(20);//每页20条记录

	/**
	 * 默认方法.
	 */
	public String execute() throws Exception {
		logger.debug("begin execute() {...");
		page = SolrClient.search(page, keyWords);
		logger.debug("end execute() ... }");
		return SUCCESS;
	}

	/**
	 * 默认方法.
	 */
	public String search() throws Exception {
		logger.debug("begin 检索 {...");
		logger.debug("检索关键词为-->{}", keyWords);
		logger.debug("end 检索 ... }");
		return SUCCESS;
	}

	/**
	 * 创建索引.
	 * @return
	 */
	public String createIndex() {
		logger.debug("生成索引 begin { ... ");
		List<SpecificationChapterDto> scDtos = Lists.newArrayList();
		List<SpecificationChapter> scs = scManager.getAllChapter();
		for (Iterator<SpecificationChapter> iterator = scs.iterator(); iterator.hasNext();) {
			SpecificationChapter chapter = (SpecificationChapter) iterator.next();

			if (null == chapter.getContent() || "".equals(chapter.getContent())) {
				logger.debug("content is null.");
			} else {
				SpecificationChapterDto scDto = new SpecificationChapterDto();
				scDto.setId(String.valueOf(chapter.getId()));
				scDto.setChapterName(scManager.getParentChapterName(chapter));
				scDto.setContent(chapter.getContent());
				scDto.setSpecificationName(scManager.getSpecificationName(chapter));
				scDtos.add(scDto);
			}
		}
		SolrClient.createIndex(scDtos);
		addActionMessage("索引创建成功");
		logger.debug("生成索引 end ... }");
		return SUCCESS;
	}

	/**
	 * 更新索引.
	 * @return
	 * @throws Exception
	 */
	public String updateIndex() throws Exception {
		logger.debug("begin 更新索引 {...");
		addActionMessage("成功更新所有索引");
		logger.debug("end 更新索引 ... }");
		return SUCCESS;
	}

	/**
	 * 删除所有索引.
	 * @return
	 * @throws Exception
	 */
	public String deleteAllIndex() throws Exception {
		logger.debug("begin 删除所有索引 {...");
		SolrClient.deleteAllIndex();
		addActionMessage("成功删除所有索引");
		logger.debug("end 删除所有索引 ... }");
		return SUCCESS;
	}

	/*~~~~~~~~~~~Setters And Getters ~~~~~~~~~~~~~~~~~*/
	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public Page<SpecificationChapterDto> getPage() {
		return page;
	}

	/*~~~~~~~~~~~注入业务类 ~~~~~~~~~~~~~~~~~*/
	
	@Autowired
	public void setScManager(SpecificationChapterManager scManager) {
		this.scManager = scManager;
	}
}
