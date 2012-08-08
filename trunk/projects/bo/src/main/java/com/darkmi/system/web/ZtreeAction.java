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
	private Logger logger = LoggerFactory.getLogger(ZtreeAction.class);
	private static final long serialVersionUID = 1L;
	private String message;
	private Long id;
	private String level;
	private String otherParam;
	SpecificationChapterManager scManager;

	public static final String MESSAGE = "Struts is up and running ";

	public String execute() throws Exception {
		logger.debug("execute begin {...");
		setMessage(MESSAGE);
		logger.debug("execute end ...}");
		return SUCCESS;
	}

	public void getTree() throws Exception {
		logger.debug("get tree begin {...");
		logger.debug("id --> " + id);
		logger.debug("level --> " + level);
		logger.debug("otherParam --> " + otherParam);

		StringBuffer sb = new StringBuffer();
		sb.append("[");
		if (id == null) {
			List<SpecificationChapter> scs = scManager.getSpecifications();
			for (Iterator<SpecificationChapter> iterator = scs.iterator(); iterator.hasNext();) {
				SpecificationChapter sc = (SpecificationChapter) iterator.next();
				sb.append("{id:" + sc.getId() + ", pId:1, name: \"" + sc.getName() + "\", isParent:true},");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("]");
		} else {
			List<SpecificationChapter> scs = scManager.getChildSC(id);
			for (Iterator<SpecificationChapter> iterator = scs.iterator(); iterator.hasNext();) {
				SpecificationChapter sc = (SpecificationChapter) iterator.next();
				List<SpecificationChapter> childSCS = scManager.getChildSC(sc.getId());
				if (childSCS.size() > 0) {
					sb.append("{id:" + sc.getId() + ", pId:1, name: \"" + sc.getName() + "\", isParent:true},");
				} else {
					sb.append("{id:" + sc.getId() + ", pId:1, name: \"" + sc.getName() + "\", isParent:false},");
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

	/*~~~~~~~~~~~ Getter And Setter ~~~~~~~~~~~~~~~~~*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getOtherParam() {
		return otherParam;
	}

	public void setOtherParam(String otherParam) {
		this.otherParam = otherParam;
	}

	/*~~~~~~~~~~~业务逻辑类注入~~~~~~~~~~~~~~~~~*/
	@Autowired
	public void setScManager(SpecificationChapterManager scManager) {
		this.scManager = scManager;
	}

}
