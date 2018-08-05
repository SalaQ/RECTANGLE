<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include flush="true" page="../navigation/navigation.jsp"></jsp:include>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网络时延</title>
<!-- Bootstrap core CSS -->
    <link href="resource/css/bootstrap.min.css" rel="stylesheet">
    <link href="resource/css/tuoputu.css" rel="stylesheet">
    <link href="resource/css/nodeInfo.css" rel="stylesheet">
    <link href="resource/css/jquery-confirm.css" rel="stylesheet">
    <script src="resource/js/jquery.min.js"></script>
    <script src="resource/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="resource/js/jquery-confirm.js"></script>
    <script src="resource/js/acharts-min.js"></script>
    <script type="text/javascript">
	    var yInfo =[0,1,2,3,4];
		var yByLength = [0];
		var startByLength = 0 ;
		var intervalByLength = 1;
	    var flag = false;
	    $(function(){
	    	if($("#timeDelaying").val()=='true'){
				$.dialog({
	        	    title: '正在进行网络时延测试...',
	        	    content: "<div class='progress'><div class='progress-bar progress-bar-info progress-bar-striped active' role='progressbar' aria-valuenow='100' aria-valuemin='0' aria-valuemax='100' style='width: 100%''><span class='sr-only'>100% Complete</span></div></div>",
	        	});
				refresh(0);
			}
	    	getTimeDelayList(1,11);
	    	chart();
	    });
    	
    	//显示隐藏的参数设置窗口
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
    		$('#packet_length').val(15);
    	}
    	 //刷新时延测试结果信息列表
	    var num = 0;
	    function refresh(i){
			if(i<100){
				num = num+1;
				getTimeDelayList(1,11);
				if(flag == true || flag=="true"){
					window.location.href="time_delay.wsn.wsn";
				}else{
					setTimeout('refresh('+num+')',1000);
				}
			}else{
				window.location.href="time_delay.wsn.wsn";
			}
		}
    	
	  //刷新列表
    	function fresh(){
    		getTimeDelayList(1,11);
    	}
	    
    	var timeDelayList;
   	    //获取时延测试信息
   	    function getTimeDelayList(page,pageSize){
   	    	 $.ajax( { 
   				    url:'getTimeDelayInfo.json',// 跳转到 controller 
   				    type:'post', 
   				    cache:false, 
   				    dataType:'json', 
   				    async: false,
   				    success:function(data) { 
   				    	flag = data.testingEndFlag;
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
	   				    	$("#time_delay").text(data.netTimeDelay);
	   				    	$("#send_packet_length").text(timeDelayList[timeDelayList.length-1].packetLength);
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
    	
    	
    	
    	
    	
    	//开始时延测试
    	function startTest(){
    		
    		
    		/**************************************************/
    		if(!($('#cycle_length').val()!='' &&  1<=$('#cycle_length').val() && 180>=$('#cycle_length').val())){
    			$('#span_cycle_length').attr('class','reda');
    			$('#span_cycle_length').text("*每个测试周期所经历的时间，以秒为单位，范围[1-180]");
    			return;
    		}else{
    			$('#span_cycle_length').attr('class','bluea');
    			$('#span_cycle_length').text("每个测试周期所经历的时间，以秒为单位，范围[1-180]");
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
    		
    		$.ajax( { 
			    url:'timeDelayTesting.json',// 跳转到 controller 
			    type:'post', 
			    cache:false, 
			    data:{ 
		             orderType:'timeDelayTesting',
			         cycleLength: $('#cycle_length').val(),
			         packetLength: $('#packet_length').val()
		    	}, 
			    dataType:'json', 
			    async: false,
			    success:function(data) { 
			    	window.location.href="time_delay.wsn?timeDelay=true";
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
<!-- 隐藏的变量 -->
	<input type="hidden" id="timeDelaying" value=${timeDelaying}>
	<input type="hidden" id="testTime" value=${testTime}>
	<input type="hidden" id="netWorkingFlag" value=${netWorkingFlag}>

	<!-- 隐藏修改节点信息窗口 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
   <div class="modal-content">
    <div class="modal-header">
     <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
     <h4 class="modal-title" id="myModalLabel">网络时延测试参数</h4>
    </div>
    <div class="modal-body">
    <div class="form-group">
      <label for="cycle_length">周期长度（秒）</label><span class="bluea" id="span_cycle_length">每个测试周期所经历的时间，以秒为单位，范围[1-180]</span>
      <input type="text" class="form-control" id="cycle_length" >
     </div>
     <div class="form-group">
      <label for="packet_length">测试包长度（字节）</label><span class="bluea" id="span_packet_length">丢包率测试数据包负载长度，以字节为单位，范围[12-116]</span>
      <input type="text" class="form-control" id="packet_length" >
     </div>
    </div>
    <div class="modal-footer">
     <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
     <button type="button" id="btn_submit" class="btn btn-primary" onclick="startTest()">测试开始</button>
    </div>
   </div>
  </div>
 </div>
 
  <div class="jumbotron">
 	<div class="container">
 		<div align="center">
 			<h2><span>网络时延测试</span></h2>
 		</div>
 		<div class="pull-left">
 			<button type="button" class="btn btn-primary btn-lg" onclick="show()">参数设置</button>
 		</div>
 	</div>
 </div>
 
  <div class="container">
 	<div class="row">
    <div class="col-md-6">
 	<fieldset>
 	<legend>测试结果列表</legend>
	 	<span>网络时延(微秒):</span>&nbsp;&nbsp;&nbsp;<span id="time_delay" class="bluea">0</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 	<span>数据包长度:</span>&nbsp;&nbsp;&nbsp;<span id="send_packet_length" class="bluea">0</span>
	 	<br>
	 	<div class="pull-right">
 		<button type="button" class="btn btn-success btn-sm" onclick="fresh()">刷新</button>
	 	</div>
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