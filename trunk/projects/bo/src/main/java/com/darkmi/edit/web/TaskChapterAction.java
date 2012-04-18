package com.darkmi.edit.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.darkmi.edit.service.TaskChapterManager;
import com.darkmi.entity.task.TaskChapter;
import com.darkmi.util.CrudActionSupport;

@Namespace("/edit")
@Results({
		@Result(name = CrudActionSupport.RELOAD, location = "design.action?page.pageNo=${page.pageNo}&page.orderBy=${page.orderBy}&page.order=${page.order}&page.pageSize=${page.pageSize}", type = "redirect"),
		@Result(name = "edit", location = "edit.jsp"), @Result(name = "upload", location = "upload-input.jsp"), })
public class TaskChapterAction extends CrudActionSupport<TaskChapter> {
	private static final long serialVersionUID = -2907389496513631586L;
	private Long id;
	private TaskChapter taskChapter;
	private TaskChapterManager taskChapterManager;
	private Page<TaskChapter> page = new Page<TaskChapter>(20);

	private File file;// 对应文件域的file，封装文件内容
	private String savePath = "uploadOfficeFile/";// 保存路径
	private String tempFileDir = "tempFile/";

	private String fileId;
	private String fileName;

	//---------
	public FileItem officeFileItem = null;
	public FileItem attachFileItem = null;
	public String officefileNameDisk = "";
	public String attachFileNameDisk = "";
	//---------

	//public String tempFileDir = "F:\\JavaWork\\rose\\projects\\bo\\src\\main\\webapp\\tempFile\\"; //临时文件目录
	public String absoluteOfficeFileDir = "F:\\JavaWork\\rose\\projects\\bo\\src\\main\\webapp\\uploadOfficeFile\\"; //office文档保存绝对路径
	public String absoluteHtmlFileDir = "F:\\JavaWork\\rose\\projects\\bo\\src\\main\\webapp\\uploadHtmlFile\\"; //Html文档保存绝对路径
	public String absoluteAttachFileDir = "F:\\JavaWork\\rose\\projects\\bo\\src\\main\\webapp\\uploadAttachFile\\"; //附件保存绝对路径
	public String absolutePdfFileDir = "F:\\JavaWork\\rose\\projects\\bo\\src\\main\\webapp\\uploadPdfFile\\"; //pdf文档保存绝对路径
	public String absoluteSecSignFileDir = "F:\\JavaWork\\rose\\projects\\bo\\src\\main\\webapp\\secSignFile\\"; //印章文件保存绝对路径
	public String relativeOfficeFileUrl = "uploadOfficeFile/"; //office文档相对目录
	public String relativeHtmlFileUrl = "uploadHtmlFile/"; //html文档相对目录
	public String relativeAttachFileUrl = "uploadAttachFile/"; //附件文件相对目录
	public String relativePdfFileUrl = "uploadPdfFile/"; //pdf文档相对目录
	public String relativeSecSignFileDir = "secSignFile/"; //印章文件保存相对路径

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			taskChapter = taskChapterManager.getTaskChapter(id);
		} else {
			taskChapter = new TaskChapter();
		}
	}

	@Override
	public TaskChapter getModel() {
		return taskChapter;
	}

	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.DESC);
		}
		page = taskChapterManager.searchTaskChapter(page, filters);
		return SUCCESS;
	}

	//@Action(value = "edit", results = { @Result(name = "success", location = "edit.jsp", type = "redirect") })
	public String edit() {
		logger.debug("编辑作业规程... begin{ ");
		logger.debug("编辑作业规程... end} ");
		return "edit";
	}

	public String testUpload() {
		return "upload";
	}

	public String show() {
		logger.debug("保存作业规程word文档... begin{ ");
		if (file != null) {
			try {
				// 读取文件内容到InputStream里
				InputStream is = new FileInputStream(getFile());
				// 创建输出流，生成新文件
				OutputStream os = new FileOutputStream(getSavePath() + "//" + "a.doc");
				// 将InputStream里的byte拷贝到OutputStream
				IOUtils.copy(is, os);
				os.flush();
				IOUtils.closeQuietly(is);
				IOUtils.closeQuietly(os);
				return SUCCESS;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		logger.debug("保存作业规程word文档... end} ");
		return "edit";
	}

	public String getSavePath() {
		String aPath = ServletActionContext.getServletContext().getRealPath(savePath);
		System.out.println(aPath);
		// 将相对路径转换成绝对路径
		return aPath;
	}

	/**
	 * 将word文档保存到服务器.
	 */
	public void saveOffice() throws Exception {
		int fileId = 0;
		long fileSize = 0;
		String fileName = "";
		String otherData = "";
		String attachFileDescribe = "";
		String attachFileName = "";
		String fileType = "";
		String mySqlStr = "";
		String result = "";
		attachFileNameDisk = "";
		boolean isNewRecode = true;
		officeFileItem = null;
		attachFileItem = null;

		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置最多只允许在内存中存储的数据,单位:字节
		factory.setSizeThreshold(4096);
		// 设置一旦文件大小超过setSizeThreshold()的值时数据存放在硬盘的目录
		factory.setRepository(new File(tempFileDir));
		ServletFileUpload upload = new ServletFileUpload(factory);
		//设置允许用户上传文件大小,单位:字节
		upload.setSizeMax(1024 * 1024 * 4);
		List fileItems = null;
		Iterator iter = fileItems.iterator();
		attachFileItem = null;
		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();
			//打印提交的文本域和文件域名称
			//out.println(item.getFieldName());
			if (item.isFormField()) {
				if (item.getFieldName().equalsIgnoreCase("fileId")) {
					//if fileId=0,the file is new recode
					try {
						fileId = Integer.parseInt(item.getString().trim());
					} catch (NumberFormatException e) {
						System.out.println("NumberFormatException:" + e.getMessage());
						fileId = 0;
					}
				}
				if (item.getFieldName().equalsIgnoreCase("fileName")) {
					fileName = item.getString("utf-8").trim();
				}
				if (item.getFieldName().equalsIgnoreCase("otherData")) {
					otherData = item.getString("utf-8").trim();
					otherData = otherData.equalsIgnoreCase("") ? "请输入附加数据" : otherData;
				}
				if (item.getFieldName().equalsIgnoreCase("attachFileDescribe")) {
					attachFileDescribe = item.getString("utf-8").trim();
					attachFileDescribe = attachFileDescribe.equalsIgnoreCase("") ? "请输入附件描述" : attachFileDescribe;
				}
				if (item.getFieldName().equalsIgnoreCase("fileType")) {
					fileType = item.getString("utf-8").trim();
				}
			} else {
				if (item.getFieldName().equalsIgnoreCase("attachFile")) {
					attachFileItem = item;
				}
				if (item.getFieldName().equalsIgnoreCase("upLoadFile")) {
					officeFileItem = item;
				}
			}
		}

		if (fileId == 0) {
			fileId = GetMaxID();
		}

		if (fileId != 0 && !fileName.equalsIgnoreCase("") && officeFileItem != null) {
			if (deleteDiskFile(fileId))//删除数据库中fileid对应的服务器文件
			{
				if (attachFileItem != null)//如果上传附件为空,应该使用数据库中原有的附件数据
				{
					attachFileNameDisk = attachFileItem.getName();
					attachFileNameDisk = attachFileNameDisk.substring(attachFileNameDisk.lastIndexOf("\\") + 1);
					//保存到磁盘中的附件为了防止重复,名字取为 fileId+".attacfile."+attachFileName
					attachFileNameDisk = fileId + ".attacfile." + attachFileNameDisk;
				} else {

				}

				fileSize = officeFileItem.getSize();
				//保存到磁盘中的文档为了防止重复,名字取为 fileId+".officefile."+attachFileName
				officefileNameDisk = fileId + ".officefile." + fileName;
				//out.println(officefileUrl+"<br>"+attachFileUrl+"<br>");
				if (saveFileToDisk()) {
					try {
						result = "文档保存成功。";
					} catch (Exception e) {
						result = "SQLException,updatedatabase:" + e.getMessage();
						e.printStackTrace();
					}
				} else {
					result = "save file failed,please check upload file size,the max size is 4M";
				}
			} else {
				result = "delete file failed";
			}
		} else {
			result = "wrong information";
		}
		logger.debug(result);

	}

	/*------------------------------------------------------------
	在新建文档的时候调用，返回当前文档在数据库中应该对应的id号。
	--------------------------------------------------------------*/
	public int GetMaxID() {
		return 100;
	}

	/*------------------------------------------------------------
	保存文档到服务器磁盘，返回值true，保存成功，返回值为false时，保存失败。
	--------------------------------------------------------------*/
	public boolean saveFileToDisk() {
		File officeFileUpload = null;
		File attachFileUpload = null;
		String officeFileUploadPath = "";
		String attachFileUploadPath = "";
		boolean result = true;

		try {
			System.out.println("officefilepath:" + absoluteOfficeFileDir + officefileNameDisk);
			System.out.println("attachfilepath:" + absoluteAttachFileDir + attachFileNameDisk);
			if (!officefileNameDisk.equalsIgnoreCase("") && officeFileItem != null) {
				officeFileUpload = new File(absoluteOfficeFileDir + officefileNameDisk);
				officeFileItem.write(officeFileUpload);
			}

			if (!attachFileNameDisk.equalsIgnoreCase("") && attachFileItem != null) {
				attachFileUpload = new File(absoluteAttachFileDir + attachFileNameDisk);
				attachFileItem.write(attachFileUpload);
			}

		} catch (FileNotFoundException e) {
		} catch (Exception e) {
			System.out.println("error saveFileToDisk:" + e.getMessage());
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	/*------------------------------------------------------------
	删除服务器上原有的文件，返回值true，删除成功，false，删除失败。
	--------------------------------------------------------------*/
	public boolean deleteDiskFile(int deleteFileId) {

		String officeFileNameDel = "";
		String attachFileNameDel = "";
		File officeFileDel = null;
		File attachFileDel = null;
		boolean result = true;
		try {
			//rs = stmt.executeQuery(sqlStr);
			//if (rs.next()) {
			//officeFileNameDel = rs.getString("FILENAMEDISK") == null ? "" : rs.getString("FILENAMEDISK").trim();
			//attachFileNameDel = rs.getString("ATTACHFILENAMEDISK") == null ? "" : rs.getString("ATTACHFILENAMEDISK").trim();
			if (!officeFileNameDel.equalsIgnoreCase("")) {
				officeFileDel = new File(absoluteOfficeFileDir + officeFileNameDel);
				if (officeFileDel.exists()) {
					System.out.println("officefilefound!officefilefound!");
					if (!officeFileDel.delete()) {
						result = false;
					}
				}
			}
			if (!attachFileNameDel.equalsIgnoreCase("") && attachFileItem != null) {
				attachFileDel = new File(absoluteAttachFileDir + attachFileNameDel);
				if (attachFileDel.exists()) {
					System.out.println("attachfilefound!attachfilefound!");
					if (!attachFileDel.delete()) {
						result = false;
					}
				}
			}
			//}
			//rs.close();
		} catch (Exception e) {
			System.out.println("zl error:" + e.getMessage());
			result = false;
		}
		return result;
	}

	@Override
	public String input() throws Exception {
		return INPUT;
	}

	@Override
	public String save() throws Exception {
		//designManager.saveTask(taskChapter);
		addActionMessage("保存作业规程任务成功！");
		return RELOAD;
	}

	@Override
	public String delete() throws Exception {
		//taskChapter = designManager.getTask(id);
		//designManager.deleteTask(id);
		//dbLogger.info(SpringSecurityUtils.getCurrentUserName() + ":删除" + taskChapter.getTaskName() + "任务！");
		//addActionMessage("删除作业规任务成功！");
		return RELOAD;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Page<TaskChapter> getPage() {
		return page;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Autowired
	public void setTaskChapterManager(TaskChapterManager taskChapterManager) {
		this.taskChapterManager = taskChapterManager;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
