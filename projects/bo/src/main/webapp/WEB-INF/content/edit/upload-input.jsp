<%--
Description:该页面不再使用
author:darkmi
CreateDate:2012/08/21
UpdateDate:2012/08/27
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传</title>
</head>
<body>
<s:fielderror></s:fielderror>
<form action="upload.action" method="post" enctype="multipart/form-data">
标题：<input type="text" name="title"> <br />
文件：<input type="file" name="file" id="file"> <br>
<input type="submit" value="上传"></form>
</body>
</html>