package com.darkmi.edit.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;

@Namespace("/edit")
@Results({ @Result(name = "success", location = "upload.jsp") })
public class UploadAction extends ActionSupport {
	private static Logger logger = LoggerFactory.getLogger(UploadAction.class);
	private static final long serialVersionUID = 1L;
	private File file;// 对应文件域的file，封装文件内容
	private String fileContentType;// 封装文件类型
	private String fileName;// 封装文件名
	private String savePath = "company/default/task/default/";// 保存路径
	private String tempFileDir = "tempFile/";
	private String title;// 文件标题

	@Override
	public String execute() throws Exception {
		if (file != null) {
			System.out.println(getFileContentType());
			// 读取文件内容到InputStream里
			InputStream is = new FileInputStream(getFile());
			// 创建输出流，生成新文件
			String savePath = getSavePath() + "//" + getFileName()+".doc";
			logger.debug("savePath -->" + savePath);
			OutputStream os = new FileOutputStream(savePath);
			// 将InputStream里的byte拷贝到OutputStream
			IOUtils.copy(is, os);
			os.flush();
			IOUtils.closeQuietly(is);
			IOUtils.closeQuietly(os);
			return SUCCESS;
		}
		return INPUT;
	}

	public void saveFileToDisk(FileItem officeFileItem) {
		try {
			File officeFileUpload = new File(getSavePath() + "a.doc");
			officeFileItem.write(officeFileUpload);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getSavePath() {
		String aPath = ServletActionContext.getServletContext().getRealPath(savePath);
		System.out.println(aPath);
		// 将相对路径转换成绝对路径
		return aPath;
	}

	public String getTempPath() {
		String aPath = ServletActionContext.getServletContext().getRealPath(tempFileDir);
		System.out.println(aPath);
		// 将相对路径转换成绝对路径
		return aPath;
	}

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
