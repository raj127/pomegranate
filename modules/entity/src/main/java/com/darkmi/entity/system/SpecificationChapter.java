package com.darkmi.entity.system;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import com.darkmi.entity.AuditableEntity;
import com.google.common.collect.Lists;

/**
 * Description: 规范章节实体类.
 * Copyright (c) darkmi.com
 * All Rights Reserved.
 * @version 1.0  2012-2-28 上午10:18:26 darkmi created
 */
@Entity
@Table(name = "t_specification_chapter")
public class SpecificationChapter extends AuditableEntity {
	private SpecificationChapter parentChapter;//父节点
	private String name;//名字
	private String treeIndex;//树索引
	private Integer sort;//排序
	private Boolean isLeaf;//是否叶子节点
	private Boolean state;//状态
	private String content; //内容
	//子类实体列表
	private List<SpecificationChapter> chapterList = Lists.newArrayList();
	private Integer version;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	public SpecificationChapter getParentChapter() {
		return parentChapter;
	}

	public void setParentChapter(SpecificationChapter parentChapter) {
		this.parentChapter = parentChapter;
	}

	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "tree_index", length = 100)
	public String getTreeIndex() {
		return this.treeIndex;
	}

	public void setTreeIndex(String treeIndex) {
		this.treeIndex = treeIndex;
	}

	@Column(name = "sort")
	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(name = "is_leaf")
	public Boolean getIsLeaf() {
		return this.isLeaf;
	}

	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	@Column(name = "state")
	public Boolean getState() {
		return this.state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	@Column(name = "content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parentChapter")
	public List<SpecificationChapter> getChapterList() {
		return chapterList;
	}

	public void setChapterList(List<SpecificationChapter> chapterList) {
		this.chapterList = chapterList;
	}

	@Version
	@Column(name = "version")
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Transient
	public void calculateTreeIndex() {
		if (getParentChapter() == null) {
			setTreeIndex("0001");
		} else {
			SpecificationChapter parent = getParentChapter();
			int count = parent.getChapterList() == null ? 0 : parent.getChapterList().size();
			String suffix = "0000".substring(String.valueOf(count).length());
			setTreeIndex(parent.getTreeIndex() + "-" + suffix + (count + 1));
		}
	}

	@Override
	public String toString() {
		return "SpecificationChapter [parentChapter=" + parentChapter.getId() + ", name=" + name + ", treeIndex="
				+ treeIndex + ", sort=" + sort + ", isLeaf=" + isLeaf + ", state=" + state + ", content=" + content
				+ ", chapterList=" + chapterList + ", version=" + version + ", createTime=" + createTime
				+ ", createBy=" + createBy + ", lastModifyTime=" + lastModifyTime + ", lastModifyBy=" + lastModifyBy
				+ ", id=" + id + "]";
	}

}
