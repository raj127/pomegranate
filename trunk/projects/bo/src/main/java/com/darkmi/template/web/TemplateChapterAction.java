package com.darkmi.template.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.darkmi.SystemConfig;
import com.darkmi.entity.template.Template;
import com.darkmi.entity.template.TemplateChapter;
import com.darkmi.template.service.TemplateChapterManager;
import com.darkmi.template.service.TemplateManager;
import com.darkmi.util.CrudActionSupport;
import com.darkmi.util.FileHelper;
import com.darkmi.util.ServiceException;
import com.google.common.collect.Lists;

/**
 * 模板目录管理Action.
 * @author darkmi
 */
@Namespace("/template")
@Results({
		@Result(name = CrudActionSupport.RELOAD, location = "template-chapter.action?templateId=${template.id}", type = "redirect"),
		@Result(name = "edit", location = "edit.jsp") })
public class TemplateChapterAction extends CrudActionSupport<TemplateChapter> {
	private static final long serialVersionUID = 4387918912684322626L;
	private Long id; //目录Id
	private Long templateId; //模板Id
	private Long parentId; //目录的父Id
	private String fileName; //模板文件名
	private String filePath; //模板的保存路径
	private File tcFile;
	private TemplateChapter templateChapter;
	private List<TemplateChapter> tcs = Lists.newArrayList();

	private TemplateChapterManager tcManager;
	private TemplateManager templateManager;
	private SystemConfig systemConfig;

	/**
	 * 显示目录列表.
	 */
	@Override
	public String list() throws Exception {
		//获取templateId
		HttpServletRequest request = ServletActionContext.getRequest();
		String templateIdStr = request.getParameter("templateId");
		if (null == templateIdStr || "".equals(templateIdStr)) {
			if (SpringSecurityUtils.getCurrentUserName().equals("admin")) {
				tcs = tcManager.getAllTemplateChapter();
			}
		} else {
			templateId = Long.parseLong(request.getParameter("templateId"));
			tcs = tcManager.getTcsByTemplateId(templateId);
		}
		return SUCCESS;
	}

	/**
	 * 保存目录信息.
	 */
	@Override
	public String save() throws Exception {
		logger.debug("begin save { ...");
		//设置上级目录
		if (null == templateChapter.getParentId()) {
			templateChapter.setParentId(new Long(0));
		}
		//设置所属模板
		if (null == templateChapter.getTemplate()) {
			logger.debug("templateChapter.getTemplate() is null.");
			HttpServletRequest request = ServletActionContext.getRequest();
			templateId = Long.parseLong(request.getParameter("templateId"));
			Template template = templateManager.getTemplate(templateId);
			templateChapter.setTemplate(template);
		}
		//保存
		tcManager.saveTemplateChapter(templateChapter);
		File serverFile = new File(getSavePath());
		//FileUtils.touch(serverFile);

		uploadFile(tcFile, serverFile);
		addActionMessage("保存作业规程模板目录成功！");
		logger.debug("end save ...}");
		return RELOAD;
	}

	//将文件流存在后台的本地磁盘
	private void uploadFile(File src, File dst) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				//输入到缓冲流
				in = new BufferedInputStream(new FileInputStream(src), 16 * 1024);
				out = new BufferedOutputStream(new FileOutputStream(dst), 16 * 1024);
				byte[] buffer = new byte[16 * 1024];
				while (in.read(buffer) > 0) {
					out.write(buffer);
				}
			} finally {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			}
		} catch (Exception e) {
			logger.error("文件上传过程中失败", e);
			throw new ServiceException("文件上传过程中失败");
		}
	}

	private String getSavePath() {
		if (null != tcFile) {
			Template template = templateManager.getTemplate(templateId);
			String savePath = template.getPath() + templateChapter.getChapterName() + ".doc";
			ServletContext sc = ServletActionContext.getServletContext();
			String realPath = FileHelper.getAbsolutePath(sc, savePath);
			logger.debug("realPath -->{}", realPath);
			return realPath;
		}
		return null;
	}

	/**
	 * 进入目录录入界面.
	 */
	@Override
	public String input() throws Exception {
		logger.debug("templateId --> {}", templateId);
		if (templateId == null) {
			templateId = new Long(0);
		}
		return INPUT;
	}

	/**
	 * 删除指定目录.
	 */
	@Override
	public String delete() throws Exception {
		int size = tcManager.getChildTemplateChapter(id).size();
		if (size > 0) {
			addActionMessage("该目录下有二级目录,不能删除！");
			return RELOAD;
		} else {
			tcManager.deleteTemplateChapter(id);
			addActionMessage("删除作业规程模板目录成功！");
			return RELOAD;
		}
	}

	/**
	 * 进入模板编辑页面
	 * @return
	 */
	//@Action(value = "edit", results = { @Result(name = "success", location = "edit.jsp", type = "redirect") })
	public String edit() {
		logger.debug("编辑作业规程... begin{ ");

		filePath = systemConfig.getTemplateDefault() + fileName;
		logger.debug("filePath --> {}", filePath);

		logger.debug("编辑作业规程... end} ");
		return "edit";
	}

	/**
	 * 获取该模板的所有一级目录
	 * @return
	 */
	public List<TemplateChapter> getAllParentChapter() {
		logger.debug("获得一级目录 begin { ...");
		List<TemplateChapter> list = tcManager.getParentTemplateChapter();
		logger.debug("获取到一级目录数量为 --》 {}", list.size());
		logger.debug("获得一级目录 end ...}");
		return list;

	}

	/*~~~~~~~~~~~ 重载方法 ~~~~~~~~~~~~~~~~~*/

	@Override
	protected void prepareModel() throws Exception {
		logger.debug("prepareModel begin { ...");
		if (id != null) {
			templateChapter = tcManager.getTemplateChapter(id);
		} else {
			templateChapter = new TemplateChapter();
		}
		logger.debug("prepareModel end ...}");
	}

	@Override
	public TemplateChapter getModel() {
		return templateChapter;
	}

	/*~~~~~~~~~~~Setters And Getters ~~~~~~~~~~~~~~~~~*/
	public void setId(Long id) {
		this.id = id;
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public List<TemplateChapter> getTcs() {
		return tcs;
	}

	public File getTcFile() {
		return tcFile;
	}

	public void setTcFile(File tcFile) {
		this.tcFile = tcFile;
	}

	/*~~~~~~~~~~~业务逻辑类注入~~~~~~~~~~~~~~~~~*/
	@Autowired
	public void setTemplateChapterManager(TemplateChapterManager templateChapterManager) {
		this.tcManager = templateChapterManager;
	}

	@Autowired
	public void setSystemConfig(SystemConfig systemConfig) {
		this.systemConfig = systemConfig;
	}

	@Autowired
	public void setTemplateManager(TemplateManager templateManager) {
		this.templateManager = templateManager;
	}

}
