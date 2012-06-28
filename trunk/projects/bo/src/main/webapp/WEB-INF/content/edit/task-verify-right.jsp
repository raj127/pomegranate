<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>作业规程校验</title>
	<%@ include file="/common/meta.jsp" %>
	<script type="text/javascript" src="${ctx}/js/temp/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/temp/collection.js"></script>
	<script type="text/javascript" src="${ctx}/js/temp/jquery-ui-1.8.4.custom.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/js/temp/collection.css" media="all" />
	<link rel="stylesheet" type="text/css" href="${ctx}/js/temp/jquery-ui-1.8.4.smoothness.css" media="all" />
	<link rel="stylesheet" type="text/css" href="${ctx}/js/temp/global.css" media="all" />
	<style type="text/css">
	body {
		background-color:#FFFFFF;
		padding: 5px 
	}
	
	div.record{
		border: #ccc 1px solid;  
		margin-bottom: 5px; 
	}
	
	div.record_row{
		OVERFLOW-Y: hidden; 
		WIDTH: 99%; 
		MAX-HEIGHT: 150px
	}
	</style>
	<script type="text/javascript">
		function showData(data){
			$('body').html(data); 
		}
		
		function test(){
			alert("right");
		}
	
	</script>
</head>

<body>
	<div id="result"></div>
</body>
</html>
