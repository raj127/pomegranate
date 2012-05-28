package com.darkmi.system.web;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;

import com.darkmi.util.Department;
import com.darkmi.util.JsonActionSupport;
import com.darkmi.util.ZTreeNode;

@Namespace("/system")
public class SpecificationChapterAction extends JsonActionSupport{

	private static final long serialVersionUID = -7045274476717428369L;
	
	private JSONArray json;
	private List<Department> departments;
	
	public JSONArray getJson() {
		return json;
	}

	public void setJson(JSONArray json) {
		this.json = json;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}
	
	public void executeDepartments(){
		departments=new ArrayList<Department>();
		
		Department dept1=new Department(-1,"部门",null,true,"部门的根节点");
		Department dept2=new Department(1,"财务部",dept1,false,"财务部的根节点");
		Department dept3=new Department(2,"技术部",dept1,true,"技术部的根节点");
		Department dept4=new Department(3,"销售部",dept1,true,"销售部的根节点");
		Department dept5=new Department(4,"车间",dept1,true,"车间的根节点");
		
		Department dept6=new Department(5,"研发中心",dept3,false,"研发中心的根节点");
		Department dept7=new Department(6,"江苏地区",dept4,true,"江苏地区的根节点");
		Department dept8=new Department(7,"浙江地区",dept4,false,"浙江地区的根节点");
		Department dept9=new Department(8,"电子车间",dept5,true,"电子车间的根节点");
		Department dept10=new Department(9,"机械车间",dept5,false,"机械车间的根节点");
		
		Department dept11=new Department(10,"销售一部",dept7,false,"销售一部的根节点");
		Department dept12=new Department(11,"销售二部",dept7,false,"销售二部的根节点");
		Department dept13=new Department(12,"电子一班",dept9,true,"电子一班的根节点");
		Department dept14=new Department(13,"电子二班",dept9,false,"电子二班的根节点");
		
		
		Department dept15=new Department(14,"一班一号线",dept13,false,"一班一号线的根节点");
		Department dept16=new Department(15,"一班二号线",dept13,false,"一班二号线的根节点");
		
		
		departments.add(dept1);
		departments.add(dept3);
		departments.add(dept4);
		departments.add(dept5);
		departments.add(dept6);
		departments.add(dept7);
		departments.add(dept8);
		departments.add(dept9);
		departments.add(dept10);
		departments.add(dept11);
		departments.add(dept12);
		departments.add(dept13);
		departments.add(dept14);
		departments.add(dept15);
		departments.add(dept16);
		departments.add(dept2);
	}
	
	
	public String loadDepartmentTree(){
		//此处的参数id即为 setting中的asyncParam: ["id"]此处得来，由于本例为涉及数据库，故不需充数据库取值，
		//此处id值只做打印显示
		String id = ServletActionContext.getRequest().getParameter("id");
		System.out.println("当前获取到的节点id值为："+id);
		int treeId=-1;
		if(id!=null&&!id.equals("")){
			treeId=Integer.parseInt(id);
		}
		
		List<ZTreeNode> nodes = new ArrayList<ZTreeNode>();
		
		executeDepartments();
		
		/*for(Department dept:departments){
			if((dept.getParent()!=null)&&(dept.getParent().getId()==treeId))
			    nodes.add(new ZTreeNode(dept.getId(),dept.getName(),dept.getParent().getId(),dept.getHasChild()));
		}*/
		
		for(Department dept:departments){
			if(treeId==-1){
				if(dept.getParent()==null){
					nodes.add(new ZTreeNode(dept.getId(),dept.getName(),0,true,dept.getHasChild()));
				}else{
					if(dept.getParent().getId()==treeId){
						nodes.add(new ZTreeNode(dept.getId(),dept.getName(),dept.getParent().getId(),false,dept.getHasChild()));
					}
					
				}
			}else{
				if(dept.getParent()!=null&&dept.getParent().getId()==treeId){
					nodes.add(new ZTreeNode(dept.getId(),dept.getName(),dept.getParent().getId(),false,dept.getHasChild()));
				}
			}
		}
		json = JSONArray.fromObject(nodes);
		return SUCCESS;
	}
	

}

