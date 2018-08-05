<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include flush="true" page="../navigation/navigation.jsp"></jsp:include>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>历史查询网络联通度</title>
<!-- Bootstrap core CSS -->
    <link href="resource/css/bootstrap.min.css" rel="stylesheet">
    <link href="resource/css/tuoputu.css" rel="stylesheet">
    <link href="resource/css/nodeInfo.css" rel="stylesheet">
    <script src="resource/js/jquery.min.js"></script>
    <script src="resource/js/bootstrap.min.js"></script>
    <script type="text/javascript">
    $(function(){
    	getConnectivityDegreeList(1,20);
    });
    
    var connectivityDegreeList;
  //获取节点丢包率信息
    function getConnectivityDegreeList(page,pageSize){
    	 $.ajax( { 
			    url:'getHistoryConnectivityDegreeInfo.json',// 跳转到 controller 
			    type:'post', 
			    cache:false, 
			    dataType:'json', 
			    async: false,
			    success:function(data) { 
			    	if(data.data==true || data.data == "true"){
			    		connectivityDegreeList = data.connectivityDegreeList;
 				    	var html = "";
 				    	var sumPage=0;
 				    	var paginationHtml="";
 				    	var next = page+1;
 				    	var previous = page-1;
 				    	var order=0;
 				    	var connectivity = 0;
 				    	for(var i=(page-1)*pageSize;i<connectivityDegreeList.length&&i<page*pageSize;i++){
 				    		order = i+1;
 				    		html +="<tr><td>"+order+"</td><td>"+connectivityDegreeList[i].testNum+"</td><td>"+connectivityDegreeList[i].nodeId+"</td>";
 				    		if(connectivityDegreeList[i].recieveFlag=="false" || connectivityDegreeList[i].recieveFlag==false){
 				    			html+="<td><span class='reda'>"+connectivityDegreeList[i].recieveFlag+"</span></td></tr>"
 				    		}else{
 				    			html+="<td><span class='bluea'>"+connectivityDegreeList[i].recieveFlag+"</span></td></tr>"
 				    		}
 				    	}
 				    	for(var i =0;i<connectivityDegreeList.length;i++){
 				    		if(connectivityDegreeList[i].recieveFlag=="true" || connectivityDegreeList[i].recieveFlag==true){
 				    			connectivity = connectivity+1;
 				    		}
 				    	}
 				    	if(parseInt(connectivityDegreeList.length)!=0){
 				    		$("#connectivity_degree").text(parseInt(connectivity)/parseInt(connectivityDegreeList.length));
 				    	}
 				    	$("#connectivityDegreeTable").html(html);
 				    	if(connectivityDegreeList.length%pageSize==0){
 				    		sumPage = Math.floor(connectivityDegreeList.length/pageSize);
 				    	}else{
 				    		sumPage = Math.floor(connectivityDegreeList.length/pageSize)+1;
 				    	}
 				    	if(page==1){
 				    		if(previous<=0){
 				    			paginationHtml+="<li class='disabled'><a href='#'>"+"<<"+"</a></li>";
 				    		}else{
 				    			paginationHtml+="<li class='disabled'><a href='#' onclick='getConnectivityDegreeList("+previous+","+pageSize+")'>"+"<<"+"</a></li>";
 				    		}
 				    	}else{
 				    		paginationHtml+="<li><a href='#' onclick='getConnectivityDegreeList("+previous+","+pageSize+")'>"+"<<"+"</a></li>";
 				    	}
 				    	for(var j=1;j<=sumPage;j++){
 				    		if(j==page){
 				    			paginationHtml+="<li class='active'><a href='#' onclick='getConnectivityDegreeList("+j+","+pageSize+")'>"+j+"</a></li>";
 				    		}else{
 				    			paginationHtml+="<li><a href='#' onclick='getConnectivityDegreeList("+j+","+pageSize+")'>"+j+"</a></li>";
 				    		}
 				    	}
 				    	if(page==sumPage){
 				    		if(next>sumPage){
 				    			paginationHtml+="<li class='disabled'><a href='#'>"+">>"+"</a></li>";
 				    		}else{
 				    			paginationHtml+="<li class='disabled'><a href='#' onclick='getConnectivityDegreeList("+next+","+pageSize+")'>"+">>"+"</a></li>";
 				    		}
 				    	}else{
 				    		paginationHtml+="<li><a href='#' onclick='getConnectivityDegreeList("+next+","+pageSize+")'>"+">>"+"</a></li>";
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
 			<h2><span>网络联通度测试历史查询结果</span></h2>
 		</div>
 		<div class="pull-left">
	 		<ol class="breadcrumb">
	 		  <li><a href="hqNetworking.wsn">网路拓扑</a></li>
			  <li><a href="hqPacketLoss.wsn">丢包率</a></li>
			  <li><a href="hqTimeDelay.wsn">时延测试</a></li>
			  <li><a href="hqThroughput.wsn">吞吐量测试</a></li>
			  <li><a href="hqPathLength.wsn">平均路径长度测试</a></li>
			  <li class="active">联通度</li>
			</ol>
		</div>
 	</div>
</div>

<div class="container">
 	<fieldset>
 	<legend>测试结果列表</legend>
	 	<span>网络连通度:</span>&nbsp;&nbsp;&nbsp;<span id="connectivity_degree" class="bluea">0</span>
	 	<br><br>
	 	<table class="table table-bordered table-hover">
	  		<thead>
	            <tr>
	              <th>序号</th>
	              <th>测试次数</th>
	              <th>参与测试节点ID</th>
	              <th>是否收到该节点信息</th>
	            </tr>
	        </thead>
	        <tbody id="connectivityDegreeTable">
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