<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include flush="true" page="../navigation/navigation.jsp"></jsp:include>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>丢包率测试</title>
<!-- Bootstrap core CSS -->
    <link href="resource/css/bootstrap.min.css" rel="stylesheet">
    <link href="resource/css/tuoputu.css" rel="stylesheet">
    <link href="resource/css/nodeInfo.css" rel="stylesheet">
    <!--  <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>-->
    <script src="resource/js/jquery.min.js"></script>
    <script src="resource/js/bootstrap.min.js"></script>
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
	    	getSysPacketLossInfo(1,9);
	    });
	    
	    //获取系统丢包率等相关信息
	    function getSysPacketLossInfo(page,pageSize){
	    	$.ajax( { 
			    url:'getSysPacketLossInfo.json',// 跳转到 controller 
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
				    	$("#send_packet_speed").text(sysPacketLossList[sysPacketLossList.length-1].sendSpeed);
				    	$("#system_send_packet_number").text(sysPacketLossList[sysPacketLossList.length-1].sunSendPacket);
				    	$("#system_receive_packet_number").text(sysPacketLossList[sysPacketLossList.length-1].sumRecievePacket);
				    	$("#system_packet_loss").text(sysPacketLossList[sysPacketLossList.length-1].systemPakcetLoss);
				    	
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
				    	$("#pagination").html(paginationHtml);
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
 			<h2><span>丢包率测试</span></h2>
 		</div>
 		<div class="pull-left">
	 		<ol class="breadcrumb">
			  <li><a href="node_packet_loss.wsn">节点丢包率</a></li>
			  <li class="active">系统丢包率</li>
			</ol>
		</div>
 	</div>
 </div>
 <div class="container">
 	<div class="row">
    <div class="col-md-6">
 	<fieldset>
 	<legend>当前测试结果</legend>
	 	<span>发包速率(字节/秒):</span>&nbsp;&nbsp;<span id="send_packet_speed" class="bluea">0</span>&nbsp;&nbsp;&nbsp;
	 	<span>系统总发包数:</span>&nbsp;&nbsp;<span id="system_send_packet_number" class="bluea">0</span>&nbsp;&nbsp;&nbsp;
	 	<span>系统总收包数:</span>&nbsp;&nbsp;<span id="system_receive_packet_number" class="bluea">0</span>&nbsp;&nbsp;&nbsp;&nbsp;
	 	<span>系统丢包率:</span>&nbsp;&nbsp;<span id="system_packet_loss" class="bluea">0</span>
 	</fieldset>
 	<br><br>
 	<fieldset>
 	<legend>历史测试结果</legend>
 		<div class="pull-right">
 		<button type="button" class="btn btn-success btn-sm" onclick="">刷新</button>
	 	</div>
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
			  <ul class="pagination" id="pagination">
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