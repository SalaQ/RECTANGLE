<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="resource/icon/icon1.ico">
<title>选择测试系统</title>
<!-- Bootstrap core CSS -->
    <link href="resource/css/bootstrap.min.css" rel="stylesheet">
    <link href="resource/css/signin.css" rel="stylesheet">
    <!--  <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>-->
    <script src="resource/js/jquery.min.js"></script>
    <script type="text/javascript">
    	function wsnHome(){
    		window.location.href="wsnHome.wsn"
    	}
    	function uwHome(){
    		window.location.href="uwHome.wsn"
    	}
    </script>
</head>
<body>
        <button type="button" class="btn btn-link" onclick="wsnHome()">进入物联网测试系统</button>
        <button type="button" class="btn btn-link" onclick="uwHome()">进入水声网测试系统</button>
</body>
</html>