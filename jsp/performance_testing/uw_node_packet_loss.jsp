<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include flush="true" page="../navigation/uwNavigation.jsp"></jsp:include>
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
    <script src="resource/js/echarts.js"></script>
    <script type="text/javascript">
    var flag1 = false;
  	$(document).ready(function(){
        setTimeout(function(){
      		myChart.hideLoading();
         },1000);
        setInterval(function(){
        	if (flag1 == true) {
        		startChart();
        	}
        },2000);
  	});
  	function startOrStop() {
  		if ($("#startOrStop").text()=="开始绘图") {
      		$("#startOrStop").text("暂停绘图");
      		flag1 = true;
  		}
  		else if ($("#startOrStop").text()=="暂停绘图") {
      		$("#startOrStop").text("开始绘图");
      		flag1 = false;
  		}
  	}
  	function startChart() {
  		$.ajax({
  			url:'getUwData.json',
  			type:'post',
  			cache:false,
  			dataType:'json',
  			async:false,
  			success:function(data) {
  				var uwDataList = data.uwDataList;
  				var rtsData   = [];
  				var ctsData   = [];
  				var dataData  = [];
  				var ackData   = [];
  				var crashData = [];
  				var bwData    = [];
  				var quietData = [];
  				var tbdData   = [];
  				for (var i=0; i<uwDataList.length; i++) {
  					if (uwDataList[i].dataType == 'R') {
  						rtsData.push(uwDataList[i].point);
  					} else if (uwDataList[i].dataType == 'C') {
  						ctsData.push(uwDataList[i].point);
  					} else if (uwDataList[i].dataType == 'D') {
  						dataData.push(uwDataList[i].point);
  					} else if (uwDataList[i].dataType == 'A') {
  						ackData.push(uwDataList[i].point);
  					} else if (uwDataList[i].dataType == 'P') {
  						crashData.push(uwDataList[i].point);
  					} else if (uwDataList[i].dataType == 'B') {
  						bwData.push(uwDataList[i].point);
  					} else if (uwDataList[i].dataType == 'Q') {
  						quietData.push(uwDataList[i].point);
  					} else if (uwDataList[i].dataType == 'T') {
  						tbdData.push(uwDataList[i].point);
  					}
  				}
  	  		  	myChart.setOption({
  				  	series: [
  				  		{name: 'RTS',data: rtsData},
  				  		{name: 'CTS',data: ctsData},
  				  		{name: 'DATA',data: dataData},
  				  		{name: 'ACK',data: ackData},
  				  		{name: 'CRASH',data: crashData},
  				  		{name: 'BW',data: bwData},
  				  		{name: 'QUIET',data: quietData},
  				  		{name: 'TBD',data: tbdData},
  				    ],
  		    	    xAxis: {
  		    	        min: 0,
  		    	        max: data.max
  		    	    },
  		    	    dataZoom: [
  		    	        {
  		    	            type: 'slider',
  		    	            xAxisIndex: [0],
  		    	            startValue: data.startValue,
  		    	            endValue: data.endValue
  		    	        }
  		    	    ],
  			    });
  			}
  		});
  	}
    	var flag = false;
	    var nodePacketLossList;
	  //获取节点丢包率信息
	    function getNodePacketLossList(page,pageSize){
	    	 $.ajax( { 
				    url:'getUwNodePacektLossInfo.json',// 跳转到 controller 
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
				    		html +="<tr><td>"+order+"</td><td>"+nodePacketLossList[i].testNum+"</td><td>"+nodePacketLossList[i].nodeId+"</td><td>"+nodePacketLossList[i].parentId+"</td>";
				    		//根据不同的节点类型显示不同的颜色
				    		if(nodePacketLossList[i].nodeType=="gatewayNode"){
				    			html+= "<td><span class='reda'>"+nodePacketLossList[i].nodeType+"</span></td>";
				    		}else if(nodePacketLossList[i].nodeType=="buoyNode"){
				    			html+= "<td><span class='bluea'>"+nodePacketLossList[i].nodeType+"</span></td>";
				    		}else{
				    			html+= "<td>"+nodePacketLossList[i].nodeType+"</td>";
				    		}
				    		html += "<td>"+nodePacketLossList[i].sendPacket+"</td><td>"+nodePacketLossList[i].successPacket+"</td><td>"+nodePacketLossList[i].receivePacket+"</td><td>"+nodePacketLossList[i].packetInterval+"</td><td>"+nodePacketLossList[i].nodePacketLoss+"</td></tr>";

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
    		$('#myModal').modal();
    		$('#packet_number').val(5);
    		$('#packet_interval').val(5);
    	}
    	//刷新列表
    	function fresh(){
    		getNodePacketLossList(1,20);
    	}
    	
    	function start(){
    		
    		/***************************************************/
    		if(!($('#packet_number').val()!='' &&  3<=$('#packet_number').val() && 10>=$('#packet_number').val())){
    			$('#span_packet_number').attr('class','reda');
    			$('#span_packet_number').text("*测试包的个数，范围[3-10]");
    			return;
    		}else{
    			$('#span_packet_number').attr('class','bluea');
    			$('#span_packet_number').text("测试周期的个数，范围[3-10]");
    		}
    		/***************************************************************/
    		if(!($('#packet_interval').val()!='' &&  3<=$('#packet_interval').val() && 10>=$('#packet_interval').val())){
    			$('#span_packet_interval').attr('class','reda');
    			$('#span_packet_interval').text("*一个周期内相邻数据包发送的间隔，以秒为单位，范围[3-10]");
    			return;
    		}else{
    			$('#span_packet_interval').attr('class','bluea');
    			$('#span_packet_interval').text("一个周期内相邻数据包发送的间隔，以秒为单位，范围[3-10]");
    		}
    		
    		//$('#packet_send_speed').val(parseInt(($('#packet_length').val())/parseInt($('#packet_interval').val())));
    		
    		$.ajax( { 
			    url:'uwPacketLossTesting.json',// 跳转到 controller 
			    type:'post', 
			    cache:false, 
			    data:{ 
		             orderType:'packetLossTesting',
		             packetNumber: $('#packet_number').val(),
		             packetInterval: $('#packet_interval').val()
		    	}, 
			    async: false,
			    success:function(data) { 
			    	window.location.href="uw_node_packet_loss.wsn?packetLossing=true";
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
      <label for="packet_number">测试包数（个）</label><span class="bluea" id="span_packet_number">测试包的个数，范围[3-10]</span>
      <input type="text" class="form-control" id="packet_number" >
     </div>
     <div class="form-group">
      <label for="packet_interval">测试包发送间隔（秒）</label><span class="bluea" id="span_packet_interval">相邻数据包发送的间隔，以秒为单位，范围[3-10]</span>
      <input type="text" class="form-control" id="packet_interval" >
     </div>
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
	              <th>节点ID</th>
	              <th>父节点ID</th>
	              <th>节点类型</th>
	              <th>发包数</th>
	              <th>成功发包数</th>
	              <th>收包数</th>
	              <th>数据包发送间隔</th>
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
 	<div class="container theme-showcase" role="main">
	  <div class="pull-right">
	    <button type="button" class="btn btn-primary btn-sm" onclick="startOrStop()" id="startOrStop">开始绘图</button>
	  </div>
	</div>
    <div id="chart" style="width:1900px;height:650px;"></div>
    <script type="text/javascript">
      var myChart = echarts.init(document.getElementById('chart'));
      myChart.setOption({
    	    xAxis: {
    	    	name: '时间',
    	        min: 0,
    	        max: 60,
    	        interval: 10,
    	        type: 'value',
    	        axisLine: {onZero : false},
    	        splitLine: {show: false}
    	    },
    	    yAxis: {
    	    	name: '节点',
    	    	nameLocation: 'start',
    	    	inverse: true,
    	        min: 0,
    	        max: 100,
    	        interval: 1,
    	        type: 'value',
    	        axisLine: {onZero : false}
    	    },
    	    legend: {
    	    	data: [
    	    		{
	    	    	    name: 'RTS',
	    	    	    icon: 'rect',
	    	    	    textStyle: {color: 'blue'}
    	    		},
    	    		{
	    	    	    name: 'CTS',
	    	    	    icon: 'rect',
	    	    	    textStyle: {color: 'red'}
    	    		},
    	    		{
	    	    	    name: 'DATA',
	    	    	    icon: 'rect',
	    	    	    textStyle: {color: 'black'}
    	    		},
    	    		{
	    	    	    name: 'ACK',
	    	    	    icon: 'rect',
	    	    	    textStyle: {color: 'green'}
    	    		},
    	    		{
	    	    	    name: 'CRASH',
	    	    	    icon: 'rect',
	    	    	    textStyle: {color: 'grey'}
    	    		},
    	    		{
	    	    	    name: 'BW',
	    	    	    icon: 'circle',
	    	    	    textStyle: {color: 'brown'}
    	    		},
    	    		{
	    	    	    name: 'QUIET',
	    	    	    icon: 'circle',
	    	    	    textStyle: {color: 'orange'}
    	    		},
    	    		{
	    	    	    name: 'TBD',
	    	    	    icon: 'rect',
	    	    	    textStyle: {color: 'grey'}
    	    		}
    	    	],
    	    	left: 'center'
    	    },
    	    tooltip : {
    	        showDelay : 0,
    	        formatter : function (params) {
    	        	return '时间：' + params.value[0] + 's<br/>' + '节点id：' + params.value[1] + '<br/>'
    	        	+ '数据类型：' + params.seriesName;
    	        },
    	        axisPointer:{
    	            show: true,
    	            type: 'cross',
    	            lineStyle: {
    	                type: 'dashed',
    	                width: 1
    	            }
    	        }
    	    },
    	    dataZoom: [
    	        {
    	            type: 'slider',
    	            show: true,
    	            zoomLock: true,
    	            showDetail: false,
    	            xAxisIndex: [0],
    	            startValue: 0,
    	            endValue: 60
    	        },
    	        {
    	            type: 'slider',
    	            show: true,
    	            zoomLock: true,
    	            showDetail: false,
    	            left: '120',
    	            yAxisIndex: [0],
    	            start: 0,
    	            end: 10
    	        }
    	    ],
    	    series: [
    	    	{
	    	        name: 'RTS',
	    	        type: 'scatter',
	    	        symbol: 'rect',
	    	        symbolSize: [30,10],
	    	        symbolOffset: ['-50%',0],
	    	        itemStyle: {
	    	        	normal: {
	    	        		color: 'blue'
	    	        	}
	    	        },
	    	        label: {
	    	        	normal: {
	    	        		show: true,
	    	        		position: 'top',
	    	        		color: 'blue',
	    	    	        formatter : function (params) {
	    	    	        	return params.value[2] + '→' + params.value[3] + '\n' + params.seriesName;
	    	    	        },
	    	        	}
	    	        },
	    	        data: []
    	    	},
    	    	{
	    	        name: 'CTS',
	    	        type: 'scatter',
	    	        symbol: 'rect',
	    	        symbolSize: [30,10],
	    	        symbolOffset: ['-50%',0],
	    	        itemStyle: {
	    	        	normal: {
	    	        		color: 'red'
	    	        	}
	    	        },
	    	        label: {
	    	        	normal: {
	    	        		show: true,
	    	        		position: 'top',
	    	        		color: 'red',
	    	    	        formatter : function (params) {
	    	    	        	return params.value[2] + '→' + params.value[3] + '\n' + params.seriesName;
	    	    	        },
	    	        	}
	    	        },
	    	        data: []
    	    	},
    	    	{
	    	        name: 'DATA',
	    	        type: 'scatter',
	    	        symbol: 'rect',
	    	        symbolSize: [90,10],
	    	        symbolOffset: ['-50%',0],
	    	        itemStyle: {
	    	        	normal: {
	    	        		color: 'black'
	    	        	}
	    	        },
	    	        label: {
	    	        	normal: {
	    	        		show: true,
	    	        		position: 'top',
	    	        		color: 'black',
	    	    	        formatter : function (params) {
	    	    	        	return params.value[2] + '→' + params.value[3] + '\n' + params.seriesName;
	    	    	        },
	    	        	}
	    	        },
	    	        data: []
    	    	},
    	    	{
	    	        name: 'ACK',
	    	        type: 'scatter',
	    	        symbol: 'rect',
	    	        symbolSize: [30,10],
	    	        symbolOffset: ['-50%',0],
	    	        itemStyle: {
	    	        	normal: {
	    	        		color: 'green'
	    	        	}
	    	        },
	    	        label: {
	    	        	normal: {
	    	        		show: true,
	    	        		position: 'top',
	    	        		color: 'green',
	    	    	        formatter : function (params) {
	    	    	        	return params.value[2] + '→' + params.value[3] + '\n' + params.seriesName;
	    	    	        },
	    	        	}
	    	        },
	    	        data: []
    	    	},
    	    	{
	    	        name: 'CRASH',
	    	        type: 'scatter',
	    	        symbol: 'rect',
	    	        symbolSize: [30,10],
	    	        symbolOffset: ['-50%',0],
	    	        itemStyle: {
	    	        	normal: {
	    	        		color: 'gray'
	    	        	}
	    	        },
	    	        label: {
	    	        	normal: {
	    	        		show: true,
	    	        		position: 'top',
	    	        		color: 'gray',
	    	    	        formatter : function (params) {
	    	    	        	return 'PZ';
	    	    	        },
	    	        	}
	    	        },
	    	        data: []
    	    	},
    	    	{
	    	        name: 'BW',
	    	        type: 'scatter',
	    	        symbol: 'circle',
	    	        //symbolSize: [30,10],
	    	        //symbolOffset: ['-50%',0],
	    	        itemStyle: {
	    	        	normal: {
	    	        		color: 'brown'
	    	        	}
	    	        },
	    	        label: {
	    	        	normal: {
	    	        		show: true,
	    	        		position: 'top',
	    	        		color: 'brown',
	    	    	        formatter : function (params) {
	    	    	        	return 'B\nW';
	    	    	        },
	    	        	}
	    	        },
	    	        data: []
    	    	},
    	    	{
	    	        name: 'QUIET',
	    	        type: 'scatter',
	    	        symbol: 'circle',
	    	        //symbolSize: [30,10],
	    	        //symbolOffset: ['-50%',0],
	    	        itemStyle: {
	    	        	normal: {
	    	        		color: 'orange'
	    	        	}
	    	        },
	    	        label: {
	    	        	normal: {
	    	        		show: true,
	    	        		position: 'top',
	    	        		color: 'orange',
	    	    	        formatter : function (params) {
	    	    	        	return 'Q\nT';
	    	    	        },
	    	        	}
	    	        },
	    	        data: []
    	    	},
    	    	{
	    	        name: 'TBD',
	    	        type: 'scatter',
	    	        symbol: 'rect',
	    	        symbolSize: [30,10],
	    	        symbolOffset: ['-50%',0],
	    	        itemStyle: {
	    	        	normal: {
	    	        		color: 'white',
	    	    			borderColor: 'black',
	    	    			borderWidth: 1
	    	        	}
	    	        },
	    	        data: []
    	    	}
    	    ]
    	});
      myChart.showLoading();
    </script>
</body>
</html>