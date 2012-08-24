package com.darkmi.template.web;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.darkmi.entity.StateEnum;
import com.darkmi.entity.template.Template;
import com.darkmi.entity.template.TemplateChapter;
import com.darkmi.template.service.TemplateChapterManager;
import com.darkmi.template.service.TemplateManager;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Description: 模板树状展示Action.
 * Copyright (c) darkmi
 * All Rights Reserved.
 * @version 1.0  2012-08-23 下午01:36:31 darkmi created
 */
@Namespace("/template")
public class TemplateChapterTreeAction extends ActionSupport {
	private static final long serialVersionUID = -1832062843458845519L;
	private Logger logger = LoggerFactory.getLogger(TemplateChapterTreeAction.class);
	private Long id; //对应章节的ID
	private Long templateId; //模板Id
	private Long parentId; //目录的父Id
	private String name; //章节命长
	private String content; //章节描述
	private TemplateManager templateManager;
	private TemplateChapterManager tcManager;

	/**
	 * 默认的执行方法.
	 */
	public String execute() throws Exception {
		logger.debug("execute begin {...");
		logger.debug("templateId --》 {}", templateId);
		logger.debug("parentId --》 {}", parentId);
		logger.debug("execute end ...}");
		return SUCCESS;
	}

	/**
	 * 获取树.
	 * @throws Exception
	 */
	public void getTree() throws Exception {
		logger.debug("get tree begin {...");
		logger.debug("id --> " + id);

		StringBuffer sb = new StringBuffer();
		sb.append("[");
		if (id == null) {
			logger.debug("templateId --》 {}", templateId);
			List<TemplateChapter> scs = tcManager.getTcLevelOne(templateId);
			logger.debug("获取到的章节数量 --》{}", scs.size());
			for (Iterator<TemplateChapter> iterator = scs.iterator(); iterator.hasNext();) {
				TemplateChapter tc = (TemplateChapter) iterator.next();
				sb.append("{id:" + tc.getId() + ", pId:0, name: \"");
				sb.append(tc.getChapterName() + "\", isParent:true, click:false},");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("]");
		} else {
			//获得指定模板的一级目录
			List<TemplateChapter> tcs = tcManager.getChildTemplateChapter(id);
			for (Iterator<TemplateChapter> iterator = tcs.iterator(); iterator.hasNext();) {
				TemplateChapter tc = (TemplateChapter) iterator.next();
				List<TemplateChapter> childTcs = tcManager.getChildTemplateChapter(tc.getId());
				if (childTcs.size() > 0) {
					sb.append("{id:" + tc.getId() + ", pId:1, name: \"");
					sb.append(tc.getChapterName() + "\", isParent:true, click:false},");
				} else {
					sb.append("{id:" + tc.getId() + ", pId:1, name: \"");
					sb.append(tc.getChapterName() + "\", isParent:false, click:true},");
				}
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("]");
		}

		logger.debug(" treeData --> \n" + sb.toString());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(sb.toString());

		logger.debug("get tree end ...}");
	}

	/**
	 * 获取指定的树节点.
	 * @throws Exception
	 */
	public void getTreeData() throws Exception {
		logger.debug("get tree data begin {...");
		logger.debug("id --> " + id);
		TemplateChapter sc = tcManager.getTemplateChapter(id);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(sc.getDescription());
		logger.debug("get tree data end ...}");
	}

	/**
	 * 添加树节点.
	 * @throws Exception
	 */
	public void addTreeNode() throws Exception {
		logger.debug("addTreeNode begin {...");
		logger.debug("templateId --> " + templateId);
		logger.debug("id --> " + id);
		logger.debug("name --> " + name);
		logger.debug("content --> " + content);

		TemplateChapter tc = new TemplateChapter();
		Template template = templateManager.getTemplate(templateId);
		tc.setTemplate(template);
		tc.setChapterName(name);
		tc.setDescription(content);
		tc.setParentId(id);
		tc.setIsLeaf(true);
		tc.setDisplayOrder(1);
		tc.setState(StateEnum.NORMAL);
		tcManager.saveTemplateChapter(tc);
		logger.debug("sc --> " + tc.toString());

		TemplateChapter parentTc = tcManager.getTemplateChapter(id);
		parentTc.setIsLeaf(false);
		tcManager.saveTemplateChapter(parentTc);

		StringBuffer sb = new StringBuffer();
		sb.append("{\"retCode\":1, \"retMessage\":\"节点添加成功\", \"id\":").append(tc.getId()).append("}");
		logger.debug("返回 --> " + sb.toString());
		//返回响应
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(sb.toString());
		//addActionMessage("节点添加成功");
		logger.debug("addTreeNode end ...}");
	}

	/**
	 * 修改树节点.
	 * @throws Exception
	 */
	public void modTreeNode() throws Exception {
		logger.debug("modTreeNode begin {...");
		logger.debug("id --> " + id);
		logger.debug("name --> " + name);
		logger.debug("content --> " + content);
		TemplateChapter sc = tcManager.getTemplateChapter(id);
		sc.setChapterName(name);
		sc.setDescription(content);
		tcManager.saveTemplateChapter(sc);

		StringBuffer sb = new StringBuffer();
		sb.append("{\"retCode\":1, \"retMessage\":\"节点修改成功\", \"id\":").append(sc.getId()).append("}");
		logger.debug("返回 --> " + sb.toString());
		//返回响应
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(sb.toString());
		logger.debug("modTreeNode end ...}");
	}

	/**
	 * 删除树节点.
	 * @throws Exception
	 */
	public void delTreeNode() throws Exception {
		logger.debug("delTreeNode begin {...");
		logger.debug("id --> " + id);
		tcManager.deleteTemplateChapter(id);

		StringBuffer sb = new StringBuffer();
		sb.append("{\"retCode\":1, \"retMessage\":\"节点删除成功\", \"id\":").append(id).append("}");
		logger.debug("返回 --> " + sb.toString());
		//返回响应
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(sb.toString());
		logger.debug("delTreeNode end ...}");
	}

	/*~~~~~~~~~~~ Getter And Setter ~~~~~~~~~~~~~~~~~*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	

	/*~~~~~~~~~~~业务逻辑类注入~~~~~~~~~~~~~~~~~*/
	@Autowired
	public void setTcManager(TemplateChapterManager tcManager) {
		this.tcManager = tcManager;
	}
	
	@Autowired
	public void setTemplateManager(TemplateManager templateManager) {
		this.templateManager = templateManager;
	}

}
