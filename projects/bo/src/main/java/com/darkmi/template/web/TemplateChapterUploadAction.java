package com.darkmi.template.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Description: 保存模板.
 * Copyright (c) darkmi
 * All Rights Reserved.
 * @version 1.0  2012-08-24 下午01:36:31 darkmi created
 */
@Namespace("/template")
public class TemplateChapterUploadAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(TemplateChapterUploadAction.class);
	private File file;// 对应文件域的file，封装文件内容
	private String fileContentType;// 封装文件类型
	private String fileName;// 封装文件名
	private String savePath = "company/default/template/default/";// 保存路径
	//private String tempFileDir = "tempFile/";
	private String title;// 文件标题

	/**
	 * 上传文件.
	 * @throws Exception
	 */
	public void upload() throws Exception {
		if (file != null) {
			// 读取文件内容到InputStream里
			InputStream is = new FileInputStream(getFile());
			// 创建输出流，生成新文件
			String savePath = getSavePath() + "\\" + getFileName();
			logger.debug("savePath -->" + savePath);
			OutputStream os = new FileOutputStream(savePath);
			// 将InputStream里的byte拷贝到OutputStream
			IOUtils.copy(is, os);
			os.flush();
			IOUtils.closeQuietly(is);
			IOUtils.closeQuietly(os);
		}
	}

	public String getSavePath() {
		String aPath = ServletActionContext.getServletContext().getRealPath(savePath);
		// 将相对路径转换成绝对路径
		return aPath;
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	/*~~~~~~~~~~~ Getter And Setter ~~~~~~~~~~~~~~~~~*/
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
