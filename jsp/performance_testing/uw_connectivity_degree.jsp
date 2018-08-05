<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include flush="true" page="../navigation/uwNavigation.jsp"></jsp:include>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网络联通度</title>
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
 
    var connectivityDegreeList;
 	  //获取节点丢包率信息
 	    function getConnectivityDegreeList(page,pageSize){
 	    	 $.ajax( { 
 				    url:'getConnectivityDegreeInfo.json',// 跳转到 controller 
 				    type:'post', 
 				    cache:false, 
 				    dataType:'json', 
 				    async: false,
 				    success:function(data) { 
 				    	flag = data.testingEndFlag;
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
 	  
 	 //刷新节点丢包率信息列表
   	    var num = 0;
   	    function refresh(i){
   			if(i<60){
   				num = num+1;
   				getConnectivityDegreeList(1,20);
   				if(flag == true || flag=="true"){
   					window.location.href="connectivity_degree.wsn";
   				}else{
   					setTimeout('refresh('+num+')',1000);
   				}
   			}else{
   				window.location.href="connectivity_degree.wsn";
   			}
   		}
        
        //刷新列表
        function fresh(){
        	getConnectivityDegreeList(1,20);
        }
    
    //开始联通度测试
    function startConnectivityDegreeTest(){
		$.ajax( { 
		    url:'uwConnectivityDegree.json',// 跳转到 controller 
		    type:'post', 
		    cache:false, 
		    data:{ 
	             orderType:'connectivityDegree'
	    	}, 
		    dataType:'json', 
		    async: false,
		    success:function(data) { 
		    	window.location.href="uw_connectivity_degree.wsn?connectivityDegree=true";
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
	<input type="hidden" id="connectivityDegreeing" value=${connectivityDegreeing}>
	<input type="hidden" id="netWorkingFlag" value=${netWorkingFlag}>

<div class="jumbotron">
 	<div class="container">
 		<div align="center">
 			<h2><span>网络联通度测试</span></h2>
 		</div>
 		<div class="pull-left">
 			<button type="button" class="btn btn-primary btn-lg" onclick="startConnectivityDegreeTest()">开始测试</button>
 		</div>
 	</div>
</div>

<div class="container">
 	<fieldset>
 	<legend>测试结果列表</legend>
	 	<span>网络连通度:</span>&nbsp;&nbsp;&nbsp;<span id="connectivity_degree" class="bluea">0</span>
	 	<br>
	 	<div class="pull-right">
 		<button type="button" class="btn btn-success btn-sm" onclick="fresh()">刷新</button>
	 	</div>
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