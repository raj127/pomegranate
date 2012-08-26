package com.darkmi.template.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.darkmi.entity.template.Template;
import com.darkmi.jacob.JacobWord;
import com.darkmi.template.service.TemplateManager;
import com.darkmi.util.FileHelper;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/template")
@Results({ @Result(name = "success", type = "stream", params = { "contentType", "application/vnd.ms-word",
		"inputName", "inputStream", "contentDisposition", "attachment;filename=\"${downloadFileName}\"", "bufferSize",
		"4096" }) })
public class TemplateDownloadAction extends ActionSupport {
	private static final long serialVersionUID = -8027132329123023377L;
	public static final String DOWNLOAD = "download";
	private Logger logger = LoggerFactory.getLogger(TemplateDownloadAction.class);

	private Long id; //模板Id
	private TemplateManager templateManager;
	@SuppressWarnings("unused")
	private JacobWord jacobWord;

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	/**
	 * 获得下载文件流.
	 * @return
	 * @throws Exception
	 */
	public InputStream getInputStream() throws Exception {
		logger.debug("获得下载文件流 begin {...");
		Template template = templateManager.getTemplate(id);
		String filePath = template.getPath() + "template.docx";
		//获取的模板的绝对路径
		ServletContext sc = ServletActionContext.getServletContext();
		String aPath = FileHelper.getAbsolutePath(sc, filePath);
		logger.debug("模板的绝对路径为 -->{}", aPath);
		File file = new File(aPath);
		InputStream is = new FileInputStream(file);
		logger.debug("获得下载文件流 end ...} ");
		return is;
	}

	/**
	 * 提供转换编码后的供下载用的文件名.
	 * @return
	 */
	public String getDownloadFileName() {
		logger.debug("获得下载文件名 begin {...");
		//String downFileName = fileName;
		String downFileName = "aaa.docx";
		try {
			downFileName = new String(downFileName.getBytes(), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		logger.debug("downFileName -->{}", downFileName);
		logger.debug("获得下载文件名 end ...}");
		return downFileName;
	}

	/*~~~~~~~~~~~Setters And Getters ~~~~~~~~~~~~~~~~~*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/*~~~~~~~~~~~业务逻辑类注入~~~~~~~~~~~~~~~~~*/
	public void setTemplateManager(TemplateManager templateManager) {
		this.templateManager = templateManager;
	}

	public void setJacobWord(JacobWord jacobWord) {
		this.jacobWord = jacobWord;
	}

}
