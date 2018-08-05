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
    <link href="resource/css/nodeInfo.css" rel="stylesheet">
    <link href="resource/css/jquery-confirm.css" rel="stylesheet">
   <!--  <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>-->
    <script src="resource/js/jquery.min.js"></script>
    <script src="resource/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="resource/js/jquery-confirm.js"></script>
    <script type="text/javascript">
    	var flag = false;
	    $(function(){
	    	if($("#packetLossing").val()=='true'){
				$.dialog({
	        	    title: '正在进行丢包率测试...',
	        	    content: "<div class='progress'><div class='progress-bar progress-bar-info progress-bar-striped active' role='progressbar' aria-valuenow='100' aria-valuemin='0' aria-valuemax='100' style='width: 100%''><span class='sr-only'>100% Complete</span></div></div>",
	        	});
				refresh(0);
			}
	    	getNodePacketLossList(1,20);
	    });
    	
	    var nodePacketLossList;
	  //获取节点丢包率信息
	    function getNodePacketLossList(page,pageSize){
	    	 $.ajax( { 
				    url:'getNodePacektLossInfo.json',// 跳转到 controller 
				    type:'post', 
				    cache:false, 
				    dataType:'json', 
				    async: false,
				    success:function(data) { 
				    	flag = data.testingEndFlag;
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
	    //刷新节点丢包率信息列表
	    var num = 0;
	    function refresh(i){
			if(i<parseInt($("#testTime").val())+100){
				num = num+1;
				getNodePacketLossList(1,20);
				if(flag == true || flag=="true"){
					window.location.href="node_packet_loss.wsn";
				}else{
					setTimeout('refresh('+num+')',1000);
				}
			}else{
				window.location.href="node_packet_loss.wsn";
			}
		}
	    
	    
	    
	    
    	//显示隐藏对话框
    	function show(){
    		if($("#netWorkingFlag").val()=="false" || $("#netWorkingFlag").val()==false){
	   			 $.alert({
		                      title: '提示',
		                      type: 'red',
		                      content: '请先组网！'
		              });
	   			 return;
  			}
    		$('#myModal').modal();
    		$('#cycle_length').val(15);
    		$('#cycle_number').val(2);
    		$('#cycle_interval').val(2);
    		$('#packet_length').val(15);
    		$('#packet_interval').val(2);
    	}
    	//刷新列表
    	function fresh(){
    		getNodePacketLossList(1,20);
    	}
    	
    	function start(){
    		
    		/**************************************************/
    		if(!($('#cycle_length').val()!='' &&  1<=$('#cycle_length').val() && 180>=$('#cycle_length').val())){
    			$('#span_cycle_length').attr('class','reda');
    			$('#span_cycle_length').text("*每个测试周期所经历的时间，以秒为单位，范围[1-180]");
    			return;
    		}else{
    			$('#span_cycle_length').attr('class','bluea');
    			$('#span_cycle_length').text("每个测试周期所经历的时间，以秒为单位，范围[1-180]");
    		}
    		/***************************************************/
    		if(!($('#cycle_number').val()!='' &&  2<=$('#cycle_number').val() && 10>=$('#cycle_number').val())){
    			$('#span_cycle_number').attr('class','reda');
    			$('#span_cycle_number').text("*测试周期的个数，范围[2-10]");
    			return;
    		}else{
    			$('#span_cycle_number').attr('class','bluea');
    			$('#span_cycle_number').text("测试周期的个数，范围[2-10]");
    		}
    		/*********************************************************/
    		if(!($('#cycle_interval').val() !='' &&  1<=$('#cycle_interval').val() && 120>=$('#cycle_interval').val())){
    			$('#span_cycle_interval').attr('class','reda');
    			$('#span_cycle_interval').text("*相邻两周期的时间，以秒为单位，范围[1-120]");
    			return;
    		}else{
    			$('#span_cycle_interval').attr('class','bluea');
    			$('#span_cycle_interval').text("相邻两周期的时间，以秒为单位，范围[1-120]");
    		}
    		/***********************************************************/
    		if(!($('#packet_length').val()!='' &&  12<=$('#packet_length').val() && 116>=$('#packet_length').val())){
    			$('#span_packet_length').attr('class','reda');
    			$('#span_packet_length').text("*丢包率测试数据包负载长度，以字节为单位，范围[12-116]");
    			return;
    		}else{
    			$('#span_packet_length').attr('class','bluea');
    			$('#span_packet_length').text("丢包率测试数据包负载长度，以字节为单位，范围[12-116]");
    		}
    		/***************************************************************/
    		if(!($('#packet_interval').val()!='' &&  1<=$('#packet_interval').val() && (parseInt($('#cycle_length').val())>=$('#packet_interval').val()))){
    			$('#span_packet_interval').attr('class','reda');
    			$('#span_packet_interval').text("*一个周期内相邻数据包发送的间隔，以秒为单位，范围[1-周期长度]");
    			return;
    		}else{
    			$('#span_packet_interval').attr('class','bluea');
    			$('#span_packet_interval').text("一个周期内相邻数据包发送的间隔，以秒为单位，范围[1-周期长度]");
    		}
    		
    		//$('#packet_send_speed').val(parseInt(($('#packet_length').val())/parseInt($('#packet_interval').val())));
    		
    		$.ajax( { 
			    url:'packetLossTesting.json',// 跳转到 controller 
			    type:'post', 
			    cache:false, 
			    data:{ 
		             orderType:'packetLossTesting',
		             cycleLength: $('#cycle_length').val(),
		             cycleNumber: $('#cycle_number').val(),
		             cycleInterval: $('#cycle_interval').val(),
		             packetLength: $('#packet_length').val(),
		             packetInterval: $('#packet_interval').val(),
		             packetSendSpeed: parseInt(($('#packet_length').val())/parseInt($('#packet_interval').val()))
		    	}, 
			    async: false,
			    success:function(data) { 
			    	window.location.href="node_packet_loss.wsn?packetLossing=true";
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
<!-- 隐藏的变量 -->
	<input type="hidden" id="packetLossing" value=${packetLossing}>
	<input type="hidden" id="testTime" value=${testTime}>
	<input type="hidden" id="netWorkingFlag" value=${netWorkingFlag}>

	<!-- 隐藏修改节点信息窗口 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
   <div class="modal-content">
    <div class="modal-header">
     <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
     <h4 class="modal-title" id="myModalLabel">丢包率测试参数</h4>
    </div>
    <div class="modal-body">
    <div class="form-group">
      <label for="cycle_length">周期长度（秒）</label><span class="bluea" id="span_cycle_length">每个测试周期所经历的时间，以秒为单位，范围[1-180]</span>
      <input type="text" class="form-control" id="cycle_length">
     </div>
     <div class="form-group">
      <label for="cycle_number">测试周期数（个）</label><span class="bluea" id="span_cycle_number">测试周期的个数，范围[2-10]</span>
      <input type="text" class="form-control" id="cycle_number" >
     </div>
     <div class="form-group">
      <label for="cycle_interval">周期间隔（秒）</label><span class="bluea" id="span_cycle_interval">相邻两周期的时间，以秒为单位，范围[1-120]</span>
      <input type="text" class="form-control" id="cycle_interval" >
     </div>
     <div class="form-group">
      <label for="packet_length">测试包长度（字节）</label><span class="bluea" id="span_packet_length">丢包率测试数据包负载长度，以字节为单位，范围[12-116]</span>
      <input type="text" class="form-control" id="packet_length" >
     </div>
     <div class="form-group">
      <label for="packet_interval">测试包发送间隔（秒）</label><span class="bluea" id="span_packet_interval">一个周期内相邻数据包发送的间隔，以秒为单位，范围[1-周期长度]</span>
      <input type="text" class="form-control" id="packet_interval" >
     </div>
    <!--  <div class="form-group">
      <label for="packet_send_speed">测试包发送速率（字节/秒）</label><span class="bluea" id="span_packet_send_speed">测试包发送速率由测试包长度除以测试包发送间隔，计算而出</span>
      <input type="text" class="form-control" id="packet_send_speed" >
     </div>-->
    </div>
    <div class="modal-footer">
     <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
     <button type="button" id="btn_submit" class="btn btn-primary"  onclick="start()">测试开始</button>
    </div>
   </div>
  </div>
 </div>
 <div class="jumbotron">
 	<div class="container">
 		<div align="center">
 			<h2><span>丢包率测试</span></h2>
 		</div>
 		<div class="pull-right">
	 		<ol class="breadcrumb">
			  <li class="active">节点丢包率</li>
			  <li><a href="system_packet_loss.wsn">系统丢包率</a></li>
			</ol>
		</div>
 		
 		<div class="pull-left">
 			<button type="button" class="btn btn-primary btn-lg" onclick="show()">参数设置</button>
 		</div>
 	</div>
 </div>
 
 
 <div class="container">
 	<fieldset>
	 	<legend>测试结果列表</legend>
	 	<div class="pull-right">
	 		<button type="button" class="btn btn-success btn-sm" onclick="fresh()">刷新</button>
	 	</div>
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
</body>
</html>