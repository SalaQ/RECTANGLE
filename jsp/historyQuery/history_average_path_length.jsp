<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include flush="true" page="../navigation/navigation.jsp"></jsp:include>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>历史查询平均路径长度</title>
<!-- Bootstrap core CSS -->
    <link href="resource/css/bootstrap.min.css" rel="stylesheet">
    <link href="resource/css/tuoputu.css" rel="stylesheet">
    <link href="resource/css/nodeInfo.css" rel="stylesheet">
    <!--  <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>-->
    <script src="resource/js/jquery.min.js"></script>
    <script src="resource/js/bootstrap.min.js"></script>
     <script type="text/javascript">
     $(function(){
	    	getPathLengthList(1,20);
	    });
     
     var pathLengthList;
  	  //获取节点丢包率信息
  	function getPathLengthList(page,pageSize){
  	    	 $.ajax( { 
  				    url:'getHistoryPathLengthInfo.json',// 跳转到 controller 
  				    type:'post', 
  				    cache:false, 
  				    dataType:'json', 
  				    async: false,
  				    success:function(data) {
  				    	if(data.data==true || data.data == "true"){
  				    		pathLengthList = data. pathLengthList;
	   				     	$("#average_path_length").text(data.avePathLength);
	   				    	var html = "";
	   				    	var sumPage=0;
	   				    	var paginationHtml="";
	   				    	var next = page+1;
	   				    	var previous = page-1;
	   				    	var order=0;
	   				    	for(var i=(page-1)*pageSize;i< pathLengthList.length&&i<page*pageSize;i++){
	   				    		order = i+1;
	   				    		html +="<tr><td>"+order+"</td><td>"+ pathLengthList[i].testNum+"</td><td>"+ pathLengthList[i].nodeId+"</td><td>"+ pathLengthList[i].cycleNum+"</td><td>"+ pathLengthList[i].pathLength+"</td></tr>";
	   				    	}
	   				    		
	   				    	$("#pathLengthTable").html(html);
	   				    	if(pathLengthList.length%pageSize==0){
	   				    		sumPage = Math.floor(pathLengthList.length/pageSize);
	   				    	}else{
	   				    		sumPage = Math.floor(pathLengthList.length/pageSize)+1;
	   				    	}
	   				    	if(page==1){
	   				    		if(previous<=0){
	   				    			paginationHtml+="<li class='disabled'><a href='#'>"+"<<"+"</a></li>";
	   				    		}else{
	   				    			paginationHtml+="<li class='disabled'><a href='#' onclick='getPathLengthList("+previous+","+pageSize+")'>"+"<<"+"</a></li>";
	   				    		}
	   				    	}else{
	   				    		paginationHtml+="<li><a href='#' onclick='getPathLengthList("+previous+","+pageSize+")'>"+"<<"+"</a></li>";
	   				    	}
	   				    	for(var j=1;j<=sumPage;j++){
	   				    		if(j==page){
	   				    			paginationHtml+="<li class='active'><a href='#' onclick='getPathLengthList("+j+","+pageSize+")'>"+j+"</a></li>";
	   				    		}else{
	   				    			paginationHtml+="<li><a href='#' onclick='getPathLengthList("+j+","+pageSize+")'>"+j+"</a></li>";
	   				    		}
	   				    	}
	   				    	if(page==sumPage){
	   				    		if(next>sumPage){
	   				    			paginationHtml+="<li class='disabled'><a href='#'>"+">>"+"</a></li>";
	   				    		}else{
	   				    			paginationHtml+="<li class='disabled'><a href='#' onclick='getPathLengthList("+next+","+pageSize+")'>"+">>"+"</a></li>";
	   				    		}
	   				    	}else{
	   				    		paginationHtml+="<li><a href='#' onclick='getPathLengthList("+next+","+pageSize+")'>"+">>"+"</a></li>";
	   				    	}
	   				    	$("#pagination").html(paginationHtml);
  				       }	
  				    },
  				    error : function() { 
  				    	 $.alert({
  		                      title: 'Oh no',
  		                      type: 'red',
  		                      content: '系统异常'
  		                  });  
  				     } 
  				});
     		}
  	  
    </script>
</head>
<body>
 
 <div class="jumbotron">
 	<div class="container">
 		<div align="center">
 			<h2><span>网络平均路径长度测试历史查询结果</span></h2>
 		</div>
 		<div class="pull-left">
	 		<ol class="breadcrumb">
	 		  <li><a href="hqNetworking.wsn">网路拓扑</a></li>
			  <li><a href="hqPacketLoss.wsn">丢包率</a></li>
			  <li><a href="hqTimeDelay.wsn">时延测试</a></li>
			  <li><a href="hqThroughput.wsn">吞吐量测试</a></li>
			  <li class="active">平均路径长度测试</li>
			  <li><a href="hqConnectivityDegree.wsn">联通度</a></li>
			</ol>
		</div>
 	</div>
 </div>
 
  <div class="container">
 	<fieldset>
 	<legend>测试结果列表</legend>
	 	<span>网络平均路径长度(跳):</span>&nbsp;&nbsp;&nbsp;<span id="average_path_length" class="bluea">0</span>
	 	<br>
	 	<table class="table table-bordered table-hover">
	  		<thead>
	            <tr>
	              <th>序号</th>
	              <th>测试次数</th>
	              <th>普通节点ID</th>
	              <th>测试周期数</th>
	              <th>路径长度</th>
	            </tr>
	        </thead>
	        <tbody id="pathLengthTable">
	        </tbody>
		</table>
		<div class="pull-right">
			<nav>
			  <ul class="pagination" id="pagination">
			  </ul>
			</nav>
		</div>
 	</fieldset>
 </div>
 
</body>
</html>