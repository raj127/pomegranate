package com.darkmi.template.web;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.darkmi.entity.template.Template;
import com.darkmi.entity.template.TemplateChapter;
import com.darkmi.template.service.TemplateChapterManager;
import com.darkmi.template.service.TemplateManager;
import com.darkmi.util.CrudActionSupport;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Description: 任务章节管理Action.
 * Copyright (c) www.darkmi.com
 * All Rights Reserved.
 * @version 1.0  2012-05-10 上午09:20:11 DarkMi created
 */
@Namespace("/template")
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "template-chapter-table.action?templateId=${templateId}", type = "redirect") })
public class TemplateChapterTableAction extends ActionSupport {
	private static final long serialVersionUID = -2907389496513631586L;
	private Logger logger = LoggerFactory.getLogger(TemplateChapterTableAction.class);
	private String taskTree;
	//---------------
	private TemplateManager templateManager;
	private TemplateChapterManager tcManager;
	private Long templateId;

	//---------------

	@Override
	public String execute() throws Exception {
		logger.debug("begin TemplateChapterTableAction execute { ...");
		logger.debug("templateId --> {}", templateId);
		getTaskChapter();
		logger.debug("end TemplateChapterTableAction execute ...} ");
		return super.execute();
	}

	private void getTaskChapter() throws Exception {

		Template template = templateManager.getTemplate(templateId);

		StringBuffer sb = new StringBuffer();
		sb.append("<table id=\"taskTree\">");
		sb.append("<thead>");
		sb.append("<th>名称</th>");
		sb.append("<th>文件名称</th>");
		sb.append("<th>章节描述</th>");
		sb.append("<th>章节状态</th>");
		sb.append("<th>章节类型</th>");
		sb.append("<th>操作</th>");
		sb.append("</thead>");
		sb.append("<tbody>");

		sb.append("<tr id=\"ex3-node-" + 1 + "\">");
		sb.append("<td>" + template.getTemplateName() + "</td>");
		sb.append("<td>&nbsp;</td>");
		sb.append("<td>&nbsp;</td>");
		sb.append("<td>&nbsp;</td>");
		sb.append("<td>&nbsp;</td>");
		sb.append("<td>&nbsp;</td>");
		sb.append("</tr>");

		//获取一级目录
		List<TemplateChapter> levelOnes = tcManager.getTcLevelOne(templateId);
		int levelOneCounter = 1;
		for (TemplateChapter levelOne : levelOnes) {
			sb.append("<tr id=\"ex3-node-" + 1 + "-" + levelOneCounter + "\" class=\"child-of-ex3-node-" + 1 + "\">");
			sb.append("<td>" + levelOne.getChapterName() + "</td>");
			sb.append("<td>" + levelOne.getFileName() + "</td>");
			sb.append("<td>" + levelOne.getDescription() + "</td>");
			sb.append("<td>&nbsp;</td>");
			sb.append("<td>&nbsp;</td>");
			sb.append("<td>&nbsp;</td>");
			sb.append("</tr>");

			//获取二级目录
			List<TemplateChapter> levelTwos = tcManager.getLevelTwo(levelOne.getId());
			int levelTwoCounter = 1;
			for (TemplateChapter levelTwo : levelTwos) {
				sb.append("<tr id=\"ex3-node-" + 1 + "-" + levelOneCounter + "-" + levelTwoCounter
						+ "\" class=\"child-of-ex3-node-" + 1 + "-" + levelOneCounter + "\">");
				sb.append("<td>" + levelTwo.getChapterName() + "</td>");
				sb.append("<td>" + levelTwo.getFileName() + "</td>");
				sb.append("<td>" + levelTwo.getDescription() + "</td>");
				sb.append("<td>&nbsp;</td>");
				sb.append("<td>&nbsp;</td>");
				sb.append("<td>" + "<a href=\"task-verify!main.action?id=" + levelTwo.getId()
						+ "\" target=\"_blank\">编辑</a>" + "</td>");
				sb.append("</tr>");
				levelTwoCounter++;
			}
			levelOneCounter++;
		}

		sb.append("</tbody>");
		sb.append("</table>");
		logger.debug(sb.toString());
		taskTree = sb.toString();
	}

	/*~~~~~~~~~~~Setters And Getters ~~~~~~~~~~~~~~~~~*/

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public String getTaskTree() {
		return taskTree;
	}

	public void setTaskTree(String taskTree) {
		this.taskTree = taskTree;
	}

	/*~~~~~~~~~~~业务逻辑类注入~~~~~~~~~~~~~~~~~*/

	@Autowired
	public void setTemplateManager(TemplateManager templateManager) {
		this.templateManager = templateManager;
	}

	@Autowired
	public void setTcManager(TemplateChapterManager tcManager) {
		this.tcManager = tcManager;
	}
}
