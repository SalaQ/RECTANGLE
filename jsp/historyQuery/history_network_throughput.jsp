<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include flush="true" page="../navigation/navigation.jsp"></jsp:include>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>历史查询网络吞吐量</title>
<!-- Bootstrap core CSS -->
    <link href="resource/css/bootstrap.min.css" rel="stylesheet">
    <link href="resource/css/tuoputu.css" rel="stylesheet">
    <link href="resource/css/nodeInfo.css" rel="stylesheet">
   <!--  <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>-->
    <script src="resource/js/jquery.min.js"></script>
    <script src="resource/js/bootstrap.min.js"></script>
    <script type="text/javascript">
    $(function(){
    	$("#max_packet_loss").val(0.1);
    	getThroughputList(1,20);
    });
    
    var throughputList;
 	  //获取节点丢包率信息
 	    function getThroughputList(page,pageSize){
 	    	 $.ajax( { 
 				    url:'getHistoryThroughputInfo.json',// 跳转到 controller 
 				    type:'post', 
 				    cache:false, 
 				    dataType:'json', 
 				    async: false,
 				    success:function(data) { 
 				    	if(data.data==true || data.data == "true"){
 				    		throughputList = data.throughputList;
	   				    	var html = "";
	   				    	var sumPage=0;
	   				    	var paginationHtml="";
	   				    	var next = page+1;
	   				    	var previous = page-1;
	   				    	var order=0;
	   				    	for(var i=(page-1)*pageSize;i<throughputList.length&&i<page*pageSize;i++){
	   				    		order = i+1;
	   				    		html +="<tr><td>"+order+"</td><td>"+throughputList[i].testNum+"</td><td>"+throughputList[i].cycleNum+"</td><td>"+throughputList[i].nodeId+"</td>";
	   				    		//根据不同的节点类型显示不同的颜色
	   				    		if(throughputList[i].nodeType=="gatewayNode"){
	   				    			html+= "<td><span class='reda'>"+throughputList[i].nodeType+"</span></td>";
	   				    		}else if(throughputList[i].nodeType=="clusterHeadNode"){
	   				    			html+= "<td><span class='bluea'>"+throughputList[i].nodeType+"</span></td>";
	   				    		}else{
	   				    			html+= "<td>"+throughputList[i].nodeType+"</td>";
	   				    		}
	   				    		html +="<td>"+throughputList[i].parentId+"</td><td>"+throughputList[i].sendPacket+"</td><td>"+throughputList[i].recievePacketFact+"</td><td>"+throughputList[i].recievePacketTheory+"</td><td>"+throughputList[i].throughput+"</td>";
								
	   				    		if(parseFloat(throughputList[i].nodePacketLoss)<=parseFloat($("#max_packet_loss").val())){
	   				    			html += "<td><span class='bluea'>有效</span></td></tr>";
	   				    		}else{
	   				    			html += "<td><span class='reda'>无效</span></td></tr>";
	   				    		}
	   				    		
	   				    	}
	   				    	$("#throughputTable").html(html);
	   				    	if(throughputList.length%pageSize==0){
	   				    		sumPage = Math.floor(throughputList.length/pageSize);
	   				    	}else{
	   				    		sumPage = Math.floor(throughputList.length/pageSize)+1;
	   				    	}
	   				    	if(page==1){
	   				    		if(previous<=0){
	   				    			paginationHtml+="<li class='disabled'><a href='#'>"+"<<"+"</a></li>";
	   				    		}else{
	   				    			paginationHtml+="<li class='disabled'><a href='#' onclick='getThroughputList("+previous+","+pageSize+")'>"+"<<"+"</a></li>";
	   				    		}
	   				    	}else{
	   				    		paginationHtml+="<li><a href='#' onclick='getThroughputList("+previous+","+pageSize+")'>"+"<<"+"</a></li>";
	   				    	}
	   				    	for(var j=1;j<=sumPage;j++){
	   				    		if(j==page){
	   				    			paginationHtml+="<li class='active'><a href='#' onclick='getThroughputList("+j+","+pageSize+")'>"+j+"</a></li>";
	   				    		}else{
	   				    			paginationHtml+="<li><a href='#' onclick='getThroughputList("+j+","+pageSize+")'>"+j+"</a></li>";
	   				    		}
	   				    	}
	   				    	if(page==sumPage){
	   				    		if(next>sumPage){
	   				    			paginationHtml+="<li class='disabled'><a href='#'>"+">>"+"</a></li>";
	   				    		}else{
	   				    			paginationHtml+="<li class='disabled'><a href='#' onclick='getThroughputList("+next+","+pageSize+")'>"+">>"+"</a></li>";
	   				    		}
	   				    	}else{
	   				    		paginationHtml+="<li><a href='#' onclick='getThroughputList("+next+","+pageSize+")'>"+">>"+"</a></li>";
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
 			<h2><span>网络吞吐量测试历史查询结果</span></h2>
 		</div>
 		<div class="pull-left">
	 		<ol class="breadcrumb">
	 		  <li><a href="hqNetworking.wsn">网路拓扑</a></li>
			  <li><a href="hqPacketLoss.wsn">丢包率</a></li>
			  <li><a href="hqTimeDelay.wsn">时延测试</a></li>
			  <li class="active">吞吐量测试</li>
			  <li><a href="hqPathLength.wsn">平均路径长度测试</a></li>
			  <li><a href="hqConnectivityDegree.wsn">联通度</a></li>
			</ol>
		</div>
 	</div>
 </div>
 
  <div class="container">
 	<fieldset>
 	<legend>测试结果列表</legend>
 		<span>允许的最大丢包率:</span> <input type="text"  id="max_packet_loss"/>
	 	<br><br>
	 	<table class="table table-bordered table-hover">
	  		<thead>
	            <tr>
	              <th>序号</th>
	              <th>测试次数</th>
	              <th>周期数</th>
	              <th>节点ID</th>
	              <th>节点类型</th>
	              <th>父节点ID</th>
	              <th>发包数</th>
	              <th>实际收包数</th>
	              <th>理论收包数</th>
	              <th>吞吐量(字节)</th>
	              <th>吞吐量结果有效性</th>
	            </tr>
	        </thead>
	        <tbody id="throughputTable">
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