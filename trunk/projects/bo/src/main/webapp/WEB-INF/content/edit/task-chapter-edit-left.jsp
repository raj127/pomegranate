<%--
Description:规程编制 --》 规程章节编辑的主页面-left页面
author:darkmi
CreateDate:2012/08/21
UpdateDate:2012/08/27
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<form id="form1" method="post" action="task-chapter-edit-upload!upload.action" enctype="multipart/form-data">
<input id="fileName" name="fileName" type="hidden" value="测试" />
<input id="fileId" name="fileId" type="hidden" value="" />
<%-- 
<script type="text/javascript" src="${ctx}/officecontrol/ntkoofficecontrol.js"></script>
--%>
<script type="text/javascript" src="${ctx}/officecontrol/pgNtkoGenObjJsForEdit.js"></script>
</form>