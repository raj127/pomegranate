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

import org.apache.cxf.helpers.FileUtils;
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
		@Result(name = CrudActionSupport.RELOAD, location = "template-chapter.action?templateId=${templateId}", type = "redirect"),
		@Result(name = "edit", location = "edit.jsp") })
public class TemplateChapterAction extends CrudActionSupport<TemplateChapter> {
	private static final long serialVersionUID = 4387918912684322626L;
	private Long id; //目录Id
	private Long templateId; //模板Id
	private Long parentId; //目录的父Id
	private String fileName; //模板文件名
	private String filePath; //模板的保存路径
	private TemplateChapter tc;
	private List<TemplateChapter> tcs = Lists.newArrayList();

	private File upload; //上传文件
	private String uploadFileName;//上传文件名
	private String uploadContentType;//上传文件类型

	private boolean viewOnly = false;

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
		logger.debug("文件名称为--{}", uploadFileName);
		logger.debug("文件类型为--{}", uploadContentType);

		//设置所属模板
		if (null == tc.getTemplate()) {
			logger.debug("tc.getTemplate() is null.");
			HttpServletRequest request = ServletActionContext.getRequest();
			templateId = Long.parseLong(request.getParameter("templateId"));
			Template template = templateManager.getTemplate(templateId);
			tc.setTemplate(template);
		}
		tc.setFileName(uploadFileName);
		//保存目录信息
		tcManager.saveTemplateChapter(tc);

		//上传目录文件
		if (parentId != 0) {
			File serverFile = new File(getAbsolutePath());
			saveFileToDisk(upload, serverFile);
		}

		addActionMessage("保存作业规程模板目录成功！");
		logger.debug("end save ...}");
		return RELOAD;
	}

	/**
	 * 进入目录录入界面.
	 */
	@Override
	public String input() throws Exception {
		logger.debug("templateId --> {}", templateId);
		if (true == viewOnly) {
			filePath = tc.getTemplate().getPath() + tc.getFileName();
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
			tc = tcManager.getTemplateChapter(id);
			//删除DB中的信息
			tcManager.deleteTemplateChapter(id);

			//删除文件
			if (!tc.getParentId().equals(new Long(0))) {
				logger.debug("parentId -->{}", tc.getParentId());
				String savePath = tc.getTemplate().getPath() + tc.getFileName();
				ServletContext sc = ServletActionContext.getServletContext();
				String aPath = FileHelper.getAbsolutePath(sc, savePath);
				logger.debug("准备删除文件的绝对路径为 -->{}", aPath);
				FileUtils.delete(new File(aPath));
			}

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
	 * 将文件流存在后台的本地磁盘.
	 * @param src
	 * @param dst
	 */
	private void saveFileToDisk(File src, File dst) {
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

	/**
	 * 获取文件保存的绝对路径.
	 * @return
	 */
	private String getAbsolutePath() {
		if (null != upload) {
			Template template = templateManager.getTemplate(templateId);
			String savePath = template.getPath() + uploadFileName;
			ServletContext sc = ServletActionContext.getServletContext();
			String aPath = FileHelper.getAbsolutePath(sc, savePath);
			logger.debug("文件保存的绝对路径为 -->{}", aPath);
			return aPath;
		}
		return null;
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
			tc = tcManager.getTemplateChapter(id);
		} else {
			tc = new TemplateChapter();
		}
		logger.debug("prepareModel end ...}");
	}

	@Override
	public TemplateChapter getModel() {
		return tc;
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

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public boolean isViewOnly() {
		return viewOnly;
	}

	public void setViewOnly(boolean viewOnly) {
		this.viewOnly = viewOnly;
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
