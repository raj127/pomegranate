package com.darkmi.system.web;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.darkmi.entity.system.SpecificationChapter;
import com.darkmi.system.service.SpecificationChapterManager;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Description: 规范树状展示Action.
 * Copyright (c) darkmi
 * All Rights Reserved.
 * @version 1.0  2012-05-23 下午01:36:31 darkmi created
 */
@Namespace("/system")
public class ZtreeAction extends ActionSupport {
	private static final long serialVersionUID = -1832062843458845519L;
	private Logger logger = LoggerFactory.getLogger(ZtreeAction.class);
	private Long id;
	private String name;
	private String content;
	private SpecificationChapter sc;
	private SpecificationChapterManager scManager;

	/**
	 * 默认的执行方法.
	 */
	public String execute() throws Exception {
		logger.debug("execute begin {...");
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
			List<SpecificationChapter> scs = scManager.getSpecifications();
			for (Iterator<SpecificationChapter> iterator = scs.iterator(); iterator.hasNext();) {
				SpecificationChapter sc = (SpecificationChapter) iterator.next();
				sb.append("{id:" + sc.getId() + ", pId:1, name: \"");
				sb.append(sc.getName() + "\", isParent:true, click:false},");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("]");
		} else {
			List<SpecificationChapter> scs = scManager.getChildSC(id);
			for (Iterator<SpecificationChapter> iterator = scs.iterator(); iterator.hasNext();) {
				SpecificationChapter sc = (SpecificationChapter) iterator.next();
				List<SpecificationChapter> childSCS = scManager.getChildSC(sc.getId());
				if (childSCS.size() > 0) {
					sb.append("{id:" + sc.getId() + ", pId:1, name: \"");
					sb.append(sc.getName() + "\", isParent:true, click:false},");
				} else {
					sb.append("{id:" + sc.getId() + ", pId:1, name: \"");
					sb.append(sc.getName() + "\", isParent:false, click:true},");
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
		SpecificationChapter sc = scManager.getChapter(id);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(sc.getContent());
		logger.debug("get tree data end ...}");
	}

	/**
	 * 添加树节点.
	 * @throws Exception
	 */
	public void addTreeNode() throws Exception {
		logger.debug("addTreeNode begin {...");
		logger.debug("id --> " + id);
		logger.debug("name --> " + name);
		logger.debug("content --> " + content);
		scManager.addSC(id, sc);
		
		//返回响应
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("节点添加成功");
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
		scManager.modSC(id, sc);
		//返回响应
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("节点修改成功");
		//addActionMessage("节点修改成功");
		logger.debug("modTreeNode end ...}");
	}

	/**
	 * 删除树节点.
	 * @throws Exception
	 */
	public void delTreeNode() throws Exception {
		logger.debug("delTreeNode begin {...");
		logger.debug("id --> " + id);
		scManager.delSC(id);
		//返回响应
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("节点删除成功");
		//addActionMessage("节点删除成功");
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



	public SpecificationChapter getSc() {
		return sc;
	}

	public void setSc(SpecificationChapter sc) {
		this.sc = sc;
	}

	/*~~~~~~~~~~~业务逻辑类注入~~~~~~~~~~~~~~~~~*/
	@Autowired
	public void setScManager(SpecificationChapterManager scManager) {
		this.scManager = scManager;
	}
}
