package com.darkmi.system.web;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.darkmi.entity.system.SpecificationChapter;
import com.darkmi.system.service.SpecificationChapterManager;
import com.darkmi.util.CrudActionSupport;
import com.darkmi.util.ServiceException;

/**
 * Description: 规范模板管理的Action
 * Copyright (c) darkmi
 * All Rights Reserved.
 * @version 1.0  2012-05-23 下午01:36:31 darkmi created
 */
@Namespace("/system")
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "chapter.action", type = "redirect") })
public class SpecificationChapterAction extends CrudActionSupport<SpecificationChapter> {

	private static final long serialVersionUID = -4327160918859025636L;
	private SpecificationChapterManager chapterManager;
	private Long id;
	private SpecificationChapter chapter;
	private String operation;
	private String title;
	private String type;

	@Override
	public String list() throws Exception {
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		return INPUT;
	}

	@Override
	public String save() throws Exception {
		if (title.length() > 20)
			Struts2Utils.renderJson("{ \"status\" : 0,\"message\":\"名字过长\" }");
		else {
			SpecificationChapter chapter = new SpecificationChapter();
			chapter.setName(title);
			boolean isLeaf = true;
			if ("default".equals(type)) {
				isLeaf = true;
			} else {
				isLeaf = false;
			}
			chapterManager.saveChapter(chapter, id, isLeaf);

			Struts2Utils.renderJson("{ \"status\" : 1, \"id\" : " + chapter.getId() + " }");
		}

		return null;
	}

	@Override
	public String delete() throws Exception {
		try {
			chapterManager.deleteChapter(id);
			Struts2Utils.renderJson("{ \"status\" : 1 }");
			return null;
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			Struts2Utils.renderJson("{ \"status\" : 0,\"message\":\"" + e.getMessage() + "\" }");
			return null;
		}
	}

	public String tree() throws Exception {
		if (!StringUtils.isBlank(operation)) {

			if ("get_children".equals(operation)) {
				getChildren();
			}
			if ("create_node".equals(operation)) {
				save();
			}
			if ("remove_node".equals(operation)) {
				delete();
			}
			if ("rename_node".equals(operation)) {
				renameNode();
			}
			if ("search".equals(operation)) {
				search();
			}
		}
		return null;
	}

	public String getChildren() {
		if (id == 0) {
			chapter = chapterManager.getRootChapter();

			StringBuilder builder = new StringBuilder();
			builder.append("[{");
			builder.append("\"").append("attr").append("\"").append(":");
			builder.append("{\"").append("id").append("\"").append(":");
			builder.append("\"node_").append(chapter.getId()).append("\"").append(",\"rel\":\"drive\"},");
			builder.append("\"").append("data").append("\":").append("\"").append(chapter.getName()).append("\"");
			builder.append(",").append("\"").append("state").append("\"").append(":").append("\"closed\"");
			builder.append("}]");

			logger.debug("tree  json={}", builder);

			Struts2Utils.renderText(builder.toString());
		} else {

			List<SpecificationChapter> ChapterList = chapterManager.getChapter(id).getChapterList();
			StringBuilder builder = new StringBuilder();
			builder.append("[");
			for (int i = 0, count = ChapterList.size(); i < count; i++) {
				SpecificationChapter c = ChapterList.get(i);
				builder.append("{\"").append("attr").append("\"").append(":");
				builder.append("{\"").append("id").append("\"").append(":");
				builder.append("\"node_").append(c.getId()).append("\"").append(",\"rel\":\"");
				builder.append(c.getIsLeaf() == true ? "default" : "folder");
				builder.append("\"},");
				builder.append("\"").append("data").append("\":").append("\"").append(c.getName()).append("\"");
				builder.append(",").append("\"").append("state").append("\"").append(":").append("\"");
				builder.append(c.getIsLeaf() == true ? "" : "closed");
				builder.append("\"}");

				if (i != count - 1) {
					builder.append(",");
				}
			}
			builder.append("]");

			logger.debug("tree  json={}", builder);

			Struts2Utils.renderJson(builder.toString());
		}

		return null;
	}

	public String renameNode() throws Exception {
		if (title.length() > 20)
			Struts2Utils.renderJson("{ \"status\" : 0,\"message\":\"名字过长\" }");
		else {
			chapter = chapterManager.getChapter(id);
			chapter.setName(title);

			chapterManager.saveChapter(chapter);

			Struts2Utils.renderJson("{ \"status\" : 1, \"id\" : " + chapter.getId() + " }");
		}
		return null;
	}

	//["#node_1","#node_3","#node_1391","#node_1397","#node_1415","#node_1429"]
	public String search() throws Exception {

		List<SpecificationChapter> chapterList = chapterManager.getChapterByName(title);
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("[");
		for (int i = 0, count = chapterList.size(); i < count; i++) {
			SpecificationChapter c = chapterList.get(i);
			sBuilder.append("\"").append("node_").append(c.getId()).append("\"");
			if (i != count - 1) {
				sBuilder.append(",");
			}
		}
		sBuilder.append("]");
		Struts2Utils.renderJson(sBuilder.toString());
		return null;
	}

	/*~~~~~~~~~~~ 重载方法 ~~~~~~~~~~~~~~~~~*/

	@Override
	public SpecificationChapter getModel() {
		return chapter;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			chapter = chapterManager.getChapter(id);
		} else {
			chapter = new SpecificationChapter();
		}
	}

	/*~~~~~~~~~~~Setters And Getters ~~~~~~~~~~~~~~~~~*/

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/*~~~~~~~~~~~业务逻辑类注入~~~~~~~~~~~~~~~~~*/
	@Autowired
	public void setChapterManager(SpecificationChapterManager chapterManager) {
		this.chapterManager = chapterManager;
	}

}
