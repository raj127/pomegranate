package com.darkmi.solr.dto;

import java.io.Serializable;

/**
 * Description: 规范章节DTO.<br>
 *              用于向Solr Server提交索引及在页面展示搜索结果.
 * Copyright (c) www.darkmi.com
 * All Rights Reserved.
 * @version 1.0  2012-05-10 上午09:20:11 DarkMi created
 */
public class SpecificationChapterDto implements Serializable {
	//private Logger logger = LoggerFactory.getLogger(SpecificationChapterDto.class);
	private static final long serialVersionUID = -6155352774302915876L;

	private String id;
	private String chapterName;
	private String content;
	private String specificationName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSpecificationName() {
		return specificationName;
	}

	public void setSpecificationName(String specificationName) {
		this.specificationName = specificationName;
	}

	@Override
	public String toString() {
		return "SpecificationChapterDto [id=" + id + ", chapterName=" + chapterName + ", content=" + content
				+ ", specificationName=" + specificationName + "]";
	}
}
