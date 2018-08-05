<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include flush="true" page="../navigation/navigation.jsp"></jsp:include>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>历史查询丢包率测试</title>
<!-- Bootstrap core CSS -->
    <link href="resource/css/bootstrap.min.css" rel="stylesheet">
    <link href="resource/css/nodeInfo.css" rel="stylesheet">
     <link href="resource/css/tuoputu.css" rel="stylesheet">
    <link href="resource/css/jquery-confirm.css" rel="stylesheet">
    <script src="resource/js/jquery.min.js"></script>
    <script src="resource/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="resource/js/jquery-confirm.js"></script>
    <script src="resource/js/acharts-min.js"></script>
    <script type="text/javascript">
    var yInfo=[0,1,2,3,4];
	var yByLength = [0];
	var yByInterval= [0];
	var startByLength = 0;
	var intervalByLength = 1;
	var startByInterval = 0;
	var intervalByInterval = 1;
	    $(function(){
	    	getNodePacketLossList(1,15);
	    	getSysPacketLossInfo(1,10);
	    });
    	
	    var nodePacketLossList;
	  //获取节点丢包率信息
	    function getNodePacketLossList(page,pageSize){
	    	 $.ajax( { 
				    url:'getHistoryNodePacektLossInfo.json',// 跳转到 controller 
				    type:'post', 
				    cache:false, 
				    dataType:'json', 
				    async: false,
				    success:function(data) { 
				    	nodePacketLossList = data.nodePacketLossList;
				    	var html = "";
				    	var sumPage=0;
				    	var paginationHtml="";
				    	var next = page+1;
				    	var previous = page-1;
				    	var order=0;
				    	for(var i=(page-1)*pageSize;i<nodePacketLossList.length&&i<page*pageSize;i++){
				    		order = i+1;
				    		html +="<tr><td>"+order+"</td><td>"+nodePacketLossList[i].testNum+"</td><td>"+nodePacketLossList[i].sendSpeed+"</td><td>"+nodePacketLossList[i].cycleNum+"</td><td>"+nodePacketLossList[i].nodeId+"</td>";
				    		//根据不同的节点类型显示不同的颜色
				    		if(nodePacketLossList[i].nodeType=="gatewayNode"){
				    			html+= "<td><span class='reda'>"+nodePacketLossList[i].nodeType+"</span></td>";
				    		}else if(nodePacketLossList[i].nodeType=="clusterHeadNode"){
				    			html+= "<td><span class='bluea'>"+nodePacketLossList[i].nodeType+"</span></td>";
				    		}else{
				    			html+= "<td>"+nodePacketLossList[i].nodeType+"</td>";
				    		}
				    		html +="<td>"+nodePacketLossList[i].parentId+"</td><td>"+nodePacketLossList[i].recievePacket+"</td><td>"+nodePacketLossList[i].sendPacket+"</td><td>"+nodePacketLossList[i].nodePacketLoss+"</td></tr>";

				    	}
				    	$("#nodePacketLossList").html(html);
				    	if(nodePacketLossList.length%pageSize==0){
				    		sumPage = Math.floor(nodePacketLossList.length/pageSize);
				    	}else{
				    		sumPage = Math.floor(nodePacketLossList.length/pageSize)+1;
				    	}
				    	if(page==1){
				    		if(previous<=0){
				    			paginationHtml+="<li class='disabled'><a href='#'>"+"<<"+"</a></li>";
				    		}else{
				    			paginationHtml+="<li class='disabled'><a href='#' onclick='getNodePacketLossList("+previous+","+pageSize+")'>"+"<<"+"</a></li>";
				    		}
				    	}else{
				    		paginationHtml+="<li><a href='#' onclick='getNodePacketLossList("+previous+","+pageSize+")'>"+"<<"+"</a></li>";
				    	}
				    	for(var j=1;j<=sumPage;j++){
				    		if(j==page){
				    			paginationHtml+="<li class='active'><a href='#' onclick='getNodePacketLossList("+j+","+pageSize+")'>"+j+"</a></li>";
				    		}else{
				    			paginationHtml+="<li><a href='#' onclick='getNodePacketLossList("+j+","+pageSize+")'>"+j+"</a></li>";
				    		}
				    	}
				    	if(page==sumPage){
				    		if(next>sumPage){
				    			paginationHtml+="<li class='disabled'><a href='#'>"+">>"+"</a></li>";
				    		}else{
				    			paginationHtml+="<li class='disabled'><a href='#' onclick='getNodePacketLossList("+next+","+pageSize+")'>"+">>"+"</a></li>";
				    		}
				    	}else{
				    		paginationHtml+="<li><a href='#' onclick='getNodePacketLossList("+next+","+pageSize+")'>"+">>"+"</a></li>";
				    	}
				    	$("#pagination").html(paginationHtml);
				    	
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
	  
	    //获取系统丢包率等相关信息
	    function getSysPacketLossInfo(page,pageSize){
	    	$.ajax( { 
			    url:'getHistorySysPacketLossInfo.json',// 跳转到 controller 
			    type:'post', 
			    cache:false, 
			    dataType:'json', 
			    async: false,
			    success:function(data) {
			    	if(data.data==true || data.data=="true"){
				    	yInfo = data.yInfo;
				    	yByLength = data.yInfoByLength;
				    	yByInterval = data.yInfoByInterval;
				    	startByLength = data.pointInfo[0].pointStart; intervalByLength = data.pointInfo[0].pointInterval;
				    	startByInterval = data.pointInfo[1].pointStart; intervalByInterval = data.pointInfo[1].pointInterval;
				    	
				    	var sysPacketLossList = data.sysPacketLossList;
				    	
				    	var html = "";
				    	var sumPage=0;
				    	var paginationHtml="";
				    	var next = page+1;
				    	var previous = page-1;
				    	var order=0;
				    	for(var i=(page-1)*pageSize;i<sysPacketLossList.length&&i<page*pageSize;i++){
				    		order = i+1;
				    		html +="<tr><td>"+order+"</td><td>"+sysPacketLossList[i].testNum+"</td><td>"+sysPacketLossList[i].sendSpeed+"</td><td>"+sysPacketLossList[i].cycleNum+"</td><td>"+sysPacketLossList[i].sunSendPacket+"</td><td>"+sysPacketLossList[i].sumRecievePacket+"</td><td>"+sysPacketLossList[i].systemPakcetLoss+"</td></tr>";
				    	}
				    	$("#sysPacketLossTable").html(html);
				    	if(sysPacketLossList.length%pageSize==0){
				    		sumPage = Math.floor(sysPacketLossList.length/pageSize);
				    	}else{
				    		sumPage = Math.floor(sysPacketLossList.length/pageSize)+1;
				    	}
				    	if(page==1){
				    		if(previous<=0){
				    			paginationHtml+="<li class='disabled'><a href='#'>"+"<<"+"</a></li>";
				    		}else{
				    			paginationHtml+="<li class='disabled'><a href='#' onclick='getSysPacketLossInfo("+previous+","+pageSize+")'>"+"<<"+"</a></li>";
				    		}
				    	}else{
				    		paginationHtml+="<li><a href='#' onclick='getSysPacketLossInfo("+previous+","+pageSize+")'>"+"<<"+"</a></li>";
				    	}
				    	for(var j=1;j<=sumPage;j++){
				    		if(j==page){
				    			paginationHtml+="<li class='active'><a href='#' onclick='getSysPacketLossInfo("+j+","+pageSize+")'>"+j+"</a></li>";
				    		}else{
				    			paginationHtml+="<li><a href='#' onclick='getSysPacketLossInfo("+j+","+pageSize+")'>"+j+"</a></li>";
				    		}
				    	}
				    	if(page==sumPage){
				    		if(next>sumPage){
				    			paginationHtml+="<li class='disabled'><a href='#'>"+">>"+"</a></li>";
				    		}else{
				    			paginationHtml+="<li class='disabled'><a href='#' onclick='getSysPacketLossInfo("+next+","+pageSize+")'>"+">>"+"</a></li>";
				    		}
				    	}else{
				    		paginationHtml+="<li><a href='#' onclick='getSysPacketLossInfo("+next+","+pageSize+")'>"+">>"+"</a></li>";
				    	}
				    	$("#pagination_sys").html(paginationHtml);
				    	chart();
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
	    
	    //画曲线图的函数
	    function chart(){
	    	$("#canvas").html("");
	    	var pointStart; var pointInterval; var data;
	    	if($('#packet_loss_x option:first').val() == $('#packet_loss_x').val()){
	    		pointStart = startByLength; pointInterval = intervalByLength;
	    		data = yByLength;
	    	}else{
	    		pointStart = startByInterval; pointInterval = intervalByInterval;
	    		data = yByInterval;
	    	}
	    	
	    	var chart = new AChart({
	            theme : AChart.Theme.SmoothBase,
	            id : 'canvas',
	            width : 600,
	            height : 500,
	            plotCfg : {
	              margin : [50,50,80] //画板的边距
	            },
	            title: {
	                text: '系统丢包率'
	            },
	            yAxis : {
	              ticks : yInfo
	            },
	            seriesOptions : { //设置多个序列共同的属性
	              lineCfg : { 
	                smooth : true,
	                pointStart : pointStart,
	                pointInterval : pointInterval
	              }
	            },
	            
	            series : [{
	              name: '丢包率',
	              data: data
	            }]
	   
	          });
	   
	          chart.render();
	    }
	    
    	
    </script>
</head>
<body>

 <div class="jumbotron">
 	<div class="container">
 		<div align="center">
 			<h2><span>丢包率测试历史查询结果</span></h2>
 		</div>
 		<div class="pull-left">
	 		<ol class="breadcrumb">
	 		  <li><a href="hqNetworking.wsn">网路拓扑</a></li>
			  <li class="active">丢包率</li>
			  <li><a href="hqTimeDelay.wsn">时延测试</a></li>
			  <li><a href="hqThroughput.wsn">吞吐量测试</a></li>
			  <li><a href="hqPathLength.wsn">平均路径长度测试</a></li>
			  <li><a href="hqConnectivityDegree.wsn">联通度</a></li>
			</ol>
		</div>
 	</div>
 </div>
 
 
 <div class="container">
 	<fieldset>
	 	<legend>测试结果列表</legend>
	 	
	 	<table class="table table-bordered table-hover">
	  		<thead>
	            <tr>
	              <th>序号</th>
	              <th>测试次数</th>
	              <th>测试包发送速率(字节/秒)</th>
	              <th>周期数</th>
	              <th>节点ID</th>
	              <th>节点类型</th>
	              <th>父节点ID</th>
	              <th>收包数</th>
	              <th>发包数</th>
	              <th>节点丢包率</th>
	            </tr>
	        </thead>
	        <tbody id="nodePacketLossList">
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
 
 <!-- ------------------------------系统丢包率测试查询-------------------------------- -->
 
 <div class="container">
 	<div class="row">
    <div class="col-md-6">
 	
 	<fieldset>
 	<legend>系统丢包率测试结果</legend>
 		
	 	<table class="table table-bordered table-hover">
	  		<thead>
	            <tr>
	              <th>序号</th>
	              <th>测试次数</th>
	              <th>测试包发送速率(字节/秒)</th>
	              <th>周期数</th>
	              <th>系统总发包数</th>
	              <th>系统总收包数</th>
	              <th>网络系统丢包率</th>
	            </tr>
	        </thead>
	        <tbody id="sysPacketLossTable">
	        </tbody>
		</table>
		<div class="pull-right">
			<nav>
			  <ul class="pagination" id="pagination_sys">
			  </ul>
			</nav>
		</div>
 	</fieldset>
 	</div>
 	<div class="col-md-6">
 	<fieldset>
 	<legend>绘图区</legend>
 		<span>选择横坐标：</span>
 		<select class="form-control" id="packet_loss_x" onChange="chart()">
		    <option >数据包长度（字节）</option>
		    <option>数据包发送间隔（秒）</option>
	    </select>
	    <br>
	    <div id='lineChart'>
	    	<div class="detail-section">
    			<div id="canvas">
 
			    </div>
			</div>
    	</div>
 	</fieldset>
 	</div>
 </div>
 </div>
 
</body>
</html>