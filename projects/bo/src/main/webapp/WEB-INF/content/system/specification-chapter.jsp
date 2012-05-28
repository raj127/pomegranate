<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=7" />
    <meta http-equiv="Content-Language" content="zh-cn" />
    <meta http-equiv="x-ua-compatible" content="ie=7" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>Style/zTreeStyle/zTreeStyle.css" rel="stylesheet" rev="stylesheet" type="text/css" media="all" />
    <title>ZTree--测试</title>
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.4.2.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery.ztree-2.6.js"></script>
    <script type="text/javascript">
        var demoTree;
        $(document).ready(function(){
            var setting;
            
            //参数设置
            setting={
                 expandSpeed: "fast",
		         checkType : { "Y":"", "N":""},
		         async: true,
		         asyncUrl:"department.action",
		         asyncParam: ["id"],
		         isSimpleData : true,
		         editable : false,
		         treeNodeKey : "id",
		         treeNodeParentKey : "parentId",
		         nameCol : "name",
		         fontCss: setFont,
		         callback: {
			           rename:zTreeOnRename,
			           click:zTreeOnClick,
			           change:zTreeOnChange,
			           remove:zTreeOnRemove
			     }
            };
            
            //创建对象
            demoTree = $("#deptTree").zTree(setting);
            
            
        });
        
        //动态更新参数
        function setEditable(){
              var xSetting=demoTree.getSetting();
              xSetting.editable=true;
              demoTree.updateSetting(xSetting);
        }
        
        function setFont(treeId, treeNode) {
		   if (treeNode && treeNode.isParent) {
			      return {color: "blue"};
		   } else {
			      return null;
		   }
	   }
	   
	   function zTreeOnChange(event, treeId, treeNode) {
		    alert("发生改变的节点的ID值是："+treeNode["id"]);
	   }
	   
	   function zTreeOnRename(event, treeId, treeNode){
	       alert("重命名的节点的ID值是："+treeNode["id"]);
	   }
	   
	   function zTreeOnClick(event, treeId, treeNode){
	        var node=demoTree.getSelectedNode();
	        alert(node["name"]);
	        //alert("你单击的节点是："+treeNode["id"]+"    "+treeNode["name"]);
	   }
	   
        
       function zTreeOnRemove(event, treeId, treeNode){
           alert("你删除的节点是："+treeNode["id"]+"    "+treeNode["name"]);
       }
        
    </script>
  </head>
  
  <body>
     <div id="demo">
            <ul style="margin-bottom:2px;margin-left:5px;font:normal 14px Verdana;"><li>部门列表</li></ul>
            <ul id="deptTree" class="tree"></ul>
     </div>
     <div>
         <input type="button" value="编辑树" onclick="setEditable();"/>
     </div>
  </body>
</html>
