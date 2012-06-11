package com.darkmi.solr.test;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.solr.client.solrj.beans.Field;

@Entity
@Table(name = "blogs")
public class BlogsDO implements Serializable {
	private static final long serialVersionUID = -4721368786493126226L;
	@Field
	private String id;
	@Field("blogId")
	private Integer blogsId;
	@Field
	private String title;
	@Field
	private String content = "";
	@Field("createTime")
	private Date gmtCreate;
	@Field
	private String nickName;
	@Field
	private String bTypeId;
	@Field
	private String bTypeName;
	private Date gmtModified;
	private String revDate;
	private String sDate = "";
	private String eDate = "";

	@Transient
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getBlogsId() {
		return blogsId;
	}

	public void setBlogsId(Integer blogsId) {
		this.blogsId = blogsId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getbTypeId() {
		return bTypeId;
	}

	public void setbTypeId(String bTypeId) {
		this.bTypeId = bTypeId;
	}

	@Column(name = "gmt_create")
	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	@Column(name = "gmt_modified")
	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	@Transient
	public String getRevDate() {
		if (this.gmtCreate == null) {
			return null;
		}
		return DateUtils.formatDate(gmtCreate, "yyyy-MM-dd HH:mm:ss");
	}

	public void setRevDate(String revDate) {
		this.revDate = revDate;
	}

	@Transient
	public String getbTypeName() {
		return bTypeName;
	}

	public void setbTypeName(String bTypeName) {
		this.bTypeName = bTypeName;
	}

	@Transient
	public String getsDate() {
		return sDate;
	}

	public void setsDate(String sDate) {
		this.sDate = sDate;
	}

	@Transient
	public String geteDate() {
		return eDate;
	}

	public void seteDate(String eDate) {
		this.eDate = eDate;
	}

	@Override
	public String toString() {
		return "BlogsDO [id=" + id + ", blogsId=" + blogsId + ", title=" + title + ", content=" + content
				+ ", gmtCreate=" + gmtCreate + ", nickName=" + nickName + ", bTypeId=" + bTypeId + ", bTypeName="
				+ bTypeName + ", gmtModified=" + gmtModified + ", revDate=" + revDate + ", sDate=" + sDate + ", eDate="
				+ eDate + "]";
	}
}