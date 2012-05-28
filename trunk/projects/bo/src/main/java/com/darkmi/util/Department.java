package com.darkmi.util;

public class Department {
	
	private int id;
	private String name;
	private Department parent;
	private boolean hasChild;
	private String remark;
	
	public Department(int id,String name,Department parent,boolean hasChild,String remark){
		this.id=id;
		this.name=name;
		this.parent=parent;
		this.hasChild=hasChild;
		this.remark=remark;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Department getParent() {
		return parent;
	}
	public void setParent(Department parent) {
		this.parent = parent;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public boolean getHasChild() {
		return hasChild;
	}
	public void setHasChild(boolean hasChild) {
		this.hasChild = hasChild;
	}
	

}
