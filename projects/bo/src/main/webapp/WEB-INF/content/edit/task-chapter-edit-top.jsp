<%--
Description:规程编制 --》 规程章节编辑的主页面-top页面
author:darkmi
CreateDate:2012/08/21
UpdateDate:2012/08/27
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<div class="top">
	<div class="left">
	  当前编辑的作业规程名称：${tc.task.taskName} &nbsp;|&nbsp;
	当前编辑的章节：${tc.chapterName}
	<!--
	<a href="#" onclick="showServerMenu(this);return false;">工具1 </a>| 
	<a href="#" onclick="showServerMenu(this);return false;">工具2</a>
	--> 
	</div>
	<div class="right">
	<% if(!"anonymousUser".equals(SpringSecurityUtils.getCurrentUserName())){ %>
	当前用户： <%=SpringSecurityUtils.getCurrentUserName()%> | 
	<% } %>
	<a href="#" onclick="javascript:parent.window.opener=null;parent.window.close();">退出</a>
	<!--
	<a href="#" onclick="showServerMenu(this);return false;">工具3 </a>| 
	<a href="#" onclick="showServerMenu(this);return false;">工具4</a> 		
	<a href="#" onclick="showManuals(this);return false;">工具3 <span style="font-size: 11px">▼</span></a> | 
	<a href="#" onclick="showServerMenu(this);return false;">退出</a>
	-->  
	</div>
	<div class=clear></div>
</div>	
