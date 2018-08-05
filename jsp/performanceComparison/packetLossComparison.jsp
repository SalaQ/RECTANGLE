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
	    	getHistoryTestInfoList(1,15);
	    });
    	
	    //获取系统丢包率等相关信息
	    function getHistoryTestInfoList(page,pageSize){
	    	$.ajax( { 
			    url:'getHistoryTestInfoList.json',// 跳转到 controller 
			    type:'post', 
			    cache:false, 
			    dataType:'json', 
			    async: false,
			    success:function(data) {
			    	if(data.data==true || data.data=="true"){
			    		var testInfoList = 	data.testInfoList;	
				    	
				    	var html = "";
				    	var sumPage=0;
				    	var paginationHtml="";
				    	var next = page+1;
				    	var previous = page-1;
				    	var order=0;
				    	for(var i=(page-1)*pageSize;i<testInfoList.length&&i<page*pageSize;i++){
				    		order = i+1;
				    		html +="<tr><td>"+order+"</td><td>"+testInfoList[i].testName+"</td><td>"+testInfoList[i].testTime+"</td><td><input name='checkbox' type='checkbox' onclick='change(this)' value="+testInfoList[i].testId+">"+"</td></tr>";
				    	}
				    	$("#testNameTable").html(html);
				    	if(testInfoList.length%pageSize==0){
				    		sumPage = Math.floor(testInfoList.length/pageSize);
				    	}else{
				    		sumPage = Math.floor(testInfoList.length/pageSize)+1;
				    	}
				    	if(page==1){
				    		if(previous<=0){
				    			paginationHtml+="<li class='disabled'><a href='#'>"+"<<"+"</a></li>";
				    		}else{
				    			paginationHtml+="<li class='disabled'><a href='#' onclick='getHistoryTestInfoList("+previous+","+pageSize+")'>"+"<<"+"</a></li>";
				    		}
				    	}else{
				    		paginationHtml+="<li><a href='#' onclick='getHistoryTestInfoList("+previous+","+pageSize+")'>"+"<<"+"</a></li>";
				    	}
				    	for(var j=1;j<=sumPage;j++){
				    		if(j==page){
				    			paginationHtml+="<li class='active'><a href='#' onclick='getHistoryTestInfoList("+j+","+pageSize+")'>"+j+"</a></li>";
				    		}else{
				    			paginationHtml+="<li><a href='#' onclick='getHistoryTestInfoList("+j+","+pageSize+")'>"+j+"</a></li>";
				    		}
				    	}
				    	if(page==sumPage){
				    		if(next>sumPage){
				    			paginationHtml+="<li class='disabled'><a href='#'>"+">>"+"</a></li>";
				    		}else{
				    			paginationHtml+="<li class='disabled'><a href='#' onclick='getHistoryTestInfoList("+next+","+pageSize+")'>"+">>"+"</a></li>";
				    		}
				    	}else{
				    		paginationHtml+="<li><a href='#' onclick='getHistoryTestInfoList("+next+","+pageSize+")'>"+">>"+"</a></li>";
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
	    //复选框改变启动绘图
	    function change(obj){
    	 	var smObj = document.getElementsByName("checkbox");
    	 	var objId = "";
            for (var i = 0; i < smObj.length; i++){
            	if(smObj[i].checked==true){
                	objId = objId+"&"+smObj[i].value;
            	}
            }
            if(objId==""){
            	$("#canvas").html("");
            	return;
            }
            $.ajax( { 
			    url:'getHistoryPacketLossList.json',// 跳转到 controller 
			    type:'post', 
			    cache:false, 
			    dataType:'json', 
			    async: false,
			    data:{
			    	testIdList:objId
			    },
			    success:function(data) {
			    	yInfo = data.yInfo;
			    	yByLength = data.dataListByLength;
			    	yByInterval = data.dataListByInterval;
			    	startByLength = data.pointInfo[0].pointStart; intervalByLength = data.pointInfo[0].pointInterval;
			    	startByInterval = data.pointInfo[1].pointStart; intervalByInterval = data.pointInfo[1].pointInterval;
			    	chart();
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
		              margin : [50,50,80] //画板的边距,
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
		            
		            series : data
		   
		          });
	          
	            chart.render();
	          
	          
	    }
	    
    	
    </script>
</head>
<body>

 <div class="jumbotron">
 	<div class="container">
 		<div align="center">
 			<h2><span>丢包率测试结果对比</span></h2>
 		</div>
 		<div class="pull-left">
	 		<ol class="breadcrumb">
			  <li class="active">丢包率</li>
			  <li><a href="comparisonTimeDelay.wsn">时延测试</a></li>
			</ol>
		</div>
 	</div>
 </div>
 
 <!-- ------------------------------系统丢包率测试查询-------------------------------- -->
 
 <div class="container">
 	<div class="row">
    <div class="col-md-5">
 	<fieldset>
 	<legend>测试名称列表</legend>
 		
	 	<table class="table table-bordered table-hover">
	  		<thead>
	            <tr>
	              <th>序号</th>
	              <th>测试名称</th>
	              <th>测试时间</th>
	              <th>操作</th>
	            </tr>
	        </thead>
	        <tbody id="testNameTable">
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
 	<div class="col-md-7">
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