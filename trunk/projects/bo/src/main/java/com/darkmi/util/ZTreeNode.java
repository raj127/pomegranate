package com.darkmi.util;

public class ZTreeNode {
	private Object id;
	private String name;
	private Object parentId;
	
	private boolean isParent=false; 
	
	private boolean open;

	public ZTreeNode(Object id, String name,Object parentId){
		this.id=id;
		this.name=name;
		this.parentId=parentId;
	}
	
	public ZTreeNode(Object id, String name, Object parentId,boolean open,boolean isParent){
		this.id=id;
		this.name=name;
		this.parentId=parentId;
		this.open=open;
		this.isParent=isParent;
	}
	
	public Object getId() {
		return id;
	}
	public void setId(Object id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Object getParentId() {
		return parentId;
	}
	public void setParentId(Object parentId) {
		this.parentId = parentId;
	}

	public boolean isIsParent() {
		return isParent;
	}
	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
    
}