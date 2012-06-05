package com.darkmi.system.web;

import java.util.Iterator;
import java.util.List;

import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.StaleStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.darkmi.entity.system.SpecificationChapter;
import com.darkmi.system.service.SpecificationChapterManager;
import com.darkmi.util.CrudActionSupport;
import com.google.common.collect.Lists;

/**
 * 规范章节管理Action.
 * 
 * @author darkmi
 */
@Namespace("/system")
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "specification-chapter.action?page.pageNo=${page.pageNo}&page.orderBy=${page.orderBy}&page.order=${page.order}&page.pageSize=${page.pageSize}", type = "redirect") })
public class SpecificationChapterAction extends CrudActionSupport<SpecificationChapter> {
	private static final long serialVersionUID = 5159486167580353246L;
	private Long id;
	private SpecificationChapter entity;
	private Integer workingVersion;//对象版本号, 配合Hibernate的@Version防止并发修改
	private boolean viewOnly = false;
	private Page<SpecificationChapter> page = new Page<SpecificationChapter>(20);//每页20条记录

	private SpecificationChapterManager chapterManager;

	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.DESC);
		}
		page = chapterManager.searchChapter(page, filters);
		return SUCCESS;
	}

	/**
	 * 直接进入规范录入页面.
	 */
	@Override
	public String input() throws Exception {
		return INPUT;
	}

	/**
	 * 删除规范章节.
	 */
	@Override
	public String delete() throws Exception {
		return null;
	}

	/**
	 * 保存公司信息并创建公司根目录.
	 */
	@Override
	public String save() throws Exception {
		if (workingVersion != null && workingVersion < entity.getVersion()) {
			throw new StaleStateException("已经被其他人更新");
		}
		chapterManager.saveChapter(entity);
		addActionMessage("保存规范信息成功");
		return RELOAD;
	}

	public String index() {
		logger.debug("更新索引 begin { ... ");

		List<SolrInputDocument> docs = Lists.newArrayList();
		List<SpecificationChapter> scs = chapterManager.getAllChapter();
		for (Iterator<SpecificationChapter> iterator = scs.iterator(); iterator.hasNext();) {
			SpecificationChapter chapter = (SpecificationChapter) iterator.next();

			SolrInputDocument doc = new SolrInputDocument();
			doc.addField("id", chapter.getId());
			doc.addField("chapterName", chapter.getName());
			doc.addField("content", chapter.getContent());
			docs.add(doc);
		}

		String url = "http://localhost:8080/solr/core0";
		HttpSolrServer server;
		try {
			server = new HttpSolrServer(url);
			server.add(docs);
			server.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.debug("更新索引 end ... }");
		return RELOAD;
	}

	/*~~~~~~~~~~~ 重载方法 ~~~~~~~~~~~~~~~~~*/
	@Override
	public SpecificationChapter getModel() {
		return entity;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			entity = chapterManager.getChapter(id);
		} else {
			entity = new SpecificationChapter();
		}
	}

	/*~~~~~~~~~~~Setters And Getters ~~~~~~~~~~~~~~~~~*/

	public void setId(Long id) {
		this.id = id;
	}

	public Page<SpecificationChapter> getPage() {
		return page;
	}

	public boolean isViewOnly() {
		return viewOnly;
	}

	public void setViewOnly(boolean viewOnly) {
		this.viewOnly = viewOnly;
	}

	public void setWorkingVersion(Integer workingVersion) {
		this.workingVersion = workingVersion;
	}

	/*~~~~~~~~~~~业务逻辑类注入~~~~~~~~~~~~~~~~~*/

	@Autowired
	public void setChapterManager(SpecificationChapterManager chapterManager) {
		this.chapterManager = chapterManager;
	}
}