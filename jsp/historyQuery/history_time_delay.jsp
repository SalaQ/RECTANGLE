<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include flush="true" page="../navigation/navigation.jsp"></jsp:include>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>历史查询网络时延测试</title>
<!-- Bootstrap core CSS -->
    <link href="resource/css/bootstrap.min.css" rel="stylesheet">
    <link href="resource/css/tuoputu.css" rel="stylesheet">
    <link href="resource/css/nodeInfo.css" rel="stylesheet">
    <!--  <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>-->
    <script src="resource/js/jquery.min.js"></script>
    <script src="resource/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="resource/js/jquery-confirm.js"></script>
    <script src="resource/js/acharts-min.js"></script>
    <script type="text/javascript">
    var yInfo =[0,1,2,3,4];
	var yByLength = [0];
	var startByLength = 0 ;
	var intervalByLength = 1;
    $(function(){
    	   getTimeDelayList(1,11);
    	   chart();
    });
    
    var timeDelayList;
	    //获取时延测试信息
	    function getTimeDelayList(page,pageSize){
	    	 $.ajax( { 
				    url:'getHistoryTimeDelayInfo.json',// 跳转到 controller 
				    type:'post', 
				    cache:false, 
				    dataType:'json', 
				    async: false,
				    success:function(data) { 
				    	if(data.data==true || data.data == "true"){
				    		timeDelayList = data.timeDelayList;
	   				    	yInfo = data.yInfo;
	   				    	yByLength = data.yInfoByLength;
	   				    	startByLength = data.pointInfo.pointStart; 
	   				    	intervalByLength = data.pointInfo.pointInterval;
   				    	
	   				    	var html = "";
	   				    	var sumPage=0;
	   				    	var paginationHtml="";
	   				    	var next = page+1;
	   				    	var previous = page-1;
	   				    	var order=0;
	   				    	for(var i=(page-1)*pageSize;i<timeDelayList.length&&i<page*pageSize;i++){
	   				    		order = i+1;
	   				    		html +="<tr><td>"+order+"</td><td>"+timeDelayList[i].testNum+"</td><td>"+timeDelayList[i].nodeId+"</td><td>"+timeDelayList[i].packetLength+"</td><td>"+timeDelayList[i].timeDelay+"</td></tr>";
	   				    	}
	   				    	$("#timeDelayTable").html(html);
	   				    	if(timeDelayList.length%pageSize==0){
	   				    		sumPage = Math.floor(timeDelayList.length/pageSize);
	   				    	}else{
	   				    		sumPage = Math.floor(timeDelayList.length/pageSize)+1;
	   				    	}
	   				    	if(page==1){
	   				    		if(previous<=0){
	   				    			paginationHtml+="<li class='disabled'><a href='#'>"+"<<"+"</a></li>";
	   				    		}else{
	   				    			paginationHtml+="<li class='disabled'><a href='#' onclick='getTimeDelayList("+previous+","+pageSize+")'>"+"<<"+"</a></li>";
	   				    		}
	   				    	}else{
	   				    		paginationHtml+="<li><a href='#' onclick='getTimeDelayList("+previous+","+pageSize+")'>"+"<<"+"</a></li>";
	   				    	}
	   				    	for(var j=1;j<=sumPage;j++){
	   				    		if(j==page){
	   				    			paginationHtml+="<li class='active'><a href='#' onclick='getTimeDelayList("+j+","+pageSize+")'>"+j+"</a></li>";
	   				    		}else{
	   				    			paginationHtml+="<li><a href='#' onclick='getTimeDelayList("+j+","+pageSize+")'>"+j+"</a></li>";
	   				    		}
	   				    	}
	   				    	if(page==sumPage){
	   				    		if(next>sumPage){
	   				    			paginationHtml+="<li class='disabled'><a href='#'>"+">>"+"</a></li>";
	   				    		}else{
	   				    			paginationHtml+="<li class='disabled'><a href='#' onclick='getTimeDelayList("+next+","+pageSize+")'>"+">>"+"</a></li>";
	   				    		}
	   				    	}else{
	   				    		paginationHtml+="<li><a href='#' onclick='getTimeDelayList("+next+","+pageSize+")'>"+">>"+"</a></li>";
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
	
	  //画曲线图的函数
	    function chart(){
	    	$("#canvas").html("");
	    	var pointStart; var pointInterval; var data;
	    	pointStart = startByLength; pointInterval = intervalByLength;
	    	data = yByLength;
	    	
	    	var chart = new AChart({
	            theme : AChart.Theme.SmoothBase,
	            id : 'canvas',
	            width : 600,
	            height : 500,
	            plotCfg : {
	              margin : [50,50,80] //画板的边距
	            },
	            title: {
	                text: '网络时延'
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
	              name: '时延（ms）',
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
 			<h2><span>网络时延测试历史查询结果</span></h2>
 		</div>
 		<div class="pull-left">
	 		<ol class="breadcrumb">
	 		  <li><a href="hqNetworking.wsn">网路拓扑</a></li>
			  <li><a href="hqPacketLoss.wsn">丢包率</a></li>
			  <li class="active">时延测试</li>
			  <li><a href="hqThroughput.wsn">吞吐量测试</a></li>
			  <li><a href="hqPathLength.wsn">平均路径长度测试</a></li>
			  <li><a href="hqConnectivityDegree.wsn">联通度</a></li>
			</ol>
		</div>
 	</div>
 </div>
 
  <div class="container">
 	<div class="row">
    <div class="col-md-6">
 	<fieldset>
 	<legend>测试结果列表</legend>
	 	<table class="table table-bordered table-hover">
	  		<thead>
	            <tr>
	              <th>序号</th>
	              <th>测试次数</th>
	              <th>节点ID</th>
	              <th>测试包长度</th>
	              <th>节点时延</th>
	            </tr>
	        </thead>
	        <tbody id="timeDelayTable">
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
 	<div class="col-md-6">
 	<fieldset>
 	<legend>绘图区</legend>
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