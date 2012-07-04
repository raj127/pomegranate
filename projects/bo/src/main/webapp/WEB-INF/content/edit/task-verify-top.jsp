<%@ page contentType="text/html;charset=UTF-8" %>
<div class="top">
	<div class="left">
	  当前编辑的作业规程名称：${taskName} &nbsp;|&nbsp;
	当前编辑的章节：${chapterName}
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
