<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include flush="true" page="../navigation/uwNavigation.jsp"></jsp:include>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>平均路径长度</title>
<!-- Bootstrap core CSS -->
    <link href="resource/css/bootstrap.min.css" rel="stylesheet">
    <link href="resource/css/tuoputu.css" rel="stylesheet">
    <link href="resource/css/nodeInfo.css" rel="stylesheet">
    <link href="resource/css/jquery-confirm.css" rel="stylesheet">
    <!--  <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>-->
    <script src="resource/js/jquery.min.js"></script>
    <script src="resource/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="resource/js/jquery-confirm.js"></script>
    <script src="resource/js/echarts.js"></script>
     <script type="text/javascript">
     var flag = false;
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
     
	    var pathLengthList;
	   	  //获取节点丢包率信息
	   	function getPathLengthList(page,pageSize){
	   	    	 $.ajax( { 
	   				    url:'getUwPathLengthInfo.json',// 跳转到 controller 
	   				    type:'post', 
	   				    cache:false, 
	   				    dataType:'json', 
	   				    async: false,
	   				    success:function(data) { 
	   				    		//flag = data.testingEndFlag;
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
		   				    		html +="<tr><td>"+order+"</td><td>"+ pathLengthList[i].testNum+"</td><td>"+ pathLengthList[i].nodeId+"</td><td>"+ pathLengthList[i].pathLength+"</td></tr>";
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
	   	  
     
     	//显示隐藏的参数设置列表
    	function show(){
    		$('#myModal').modal();
    		$('#packet_number').val(5);
    		$('#packet_interval').val(5);
    	}
    	//刷新节点丢包率信息列表
   	    var num = 0;
   	    function refresh(i){
   			if(i<parseInt($("#testTime").val())+150){
   				num = num+1;
   				getPathLengthList(1,20);
   				if(flag == true || flag=="true"){
   					window.location.href="uw_average_path_length.wsn";
   				}else{
   					setTimeout('refresh('+num+')',1000);
   				}
   			}else{
   				window.location.href="uw_average_path_length.wsn";
   			}
   		}
        
        //刷新列表
        function fresh(){
        	getPathLengthList(1,20);
        }
     	
     	
    	//开始平均路径长度测试
    	function startPathLengthTest(){
    		
    		/**************************************************/
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
    		
    	
    		$.ajax( { 
			    url:'uwAveragePathLength.json',// 跳转到 controller 
			    type:'post', 
			    cache:false, 
			    data:{ 
		             orderType:'averagePathLength',
		             packetNumber: $('#packet_number').val(),
		             packetInterval: $('#packet_interval').val()
		    	}, 
			    dataType:'json', 
			    async: false,
			    success:function(data) { 
			    	window.location.href="uw_average_path_length.wsn?pathLength=true";
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
	<input type="hidden" id="pathLengthing" value=${pathLengthing}>
	<input type="hidden" id="testTime" value=${testTime}>
	<input type="hidden" id="netWorkingFlag" value=${netWorkingFlag}>
	<!-- 隐藏修改节点信息窗口 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
   <div class="modal-content">
    <div class="modal-header">
     <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
     <h4 class="modal-title" id="myModalLabel">网络平均路径长度测试参数</h4>
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
     <button type="button" id="btn_submit" class="btn btn-primary"  onclick="startPathLengthTest()">测试开始</button>
    </div>
   </div>
  </div>
 </div>
 
 <div class="jumbotron">
 	<div class="container">
 		<div align="center">
 			<h2><span>网络平均路径长度测试</span></h2>
 		</div>
 		<div class="pull-left">
 			<button type="button" class="btn btn-primary btn-lg" onclick="show()">参数设置</button>
 		</div>
 	</div>
 </div>
 
  <div class="container">
 	<fieldset>
 	<legend>测试结果列表</legend>
	 	<span>网络平均路径长度(跳):</span>&nbsp;&nbsp;&nbsp;<span id="average_path_length" class="bluea">0</span>
	 	<br>
	 	<div class="pull-right">
 		<button type="button" class="btn btn-success btn-sm" onclick="fresh()">刷新</button>
	 	</div>
	 	<table class="table table-bordered table-hover">
	  		<thead>
	            <tr>
	              <th>序号</th>
	              <th>测试次数</th>
	              <th>普通节点ID</th>
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